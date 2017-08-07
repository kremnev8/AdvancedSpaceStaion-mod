
package net.glider.src.entity;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.api.entity.ICameraZoomEntity;
import micdoodle8.mods.galacticraft.api.entity.IEntityNoisy;
import micdoodle8.mods.galacticraft.api.entity.IIgnoreShift;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.sounds.SoundUpdaterRocket;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.network.IPacketReceiver;
import micdoodle8.mods.galacticraft.core.network.PacketDynamic;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.glider.src.GliderCore;
import net.glider.src.entity.choreo.ChoreoScene;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.RocketControlsPacket;
import net.glider.src.network.packets.StartChoreoClientPacket;
import net.glider.src.network.packets.SyncRocketTierPacket;
import net.glider.src.tiles.TileEntityDockingPort;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityRocketFakeTiered extends Entity implements IIgnoreShift, ICameraZoomEntity, IEntityNoisy, IPacketReceiver {
	public static enum EnumLaunchPhase
	{
		DOCKED, UNDOCKED,
		
		NOTROTATED, ROTATED,
		
		FLYAWAY, LAUNCHED
	}
	
	public static enum EnumEngineState
	{
		UNIGNITED, IGNITED
	}
	
	protected long ticks = 0;
	public float shipDamage;
	public int launchPhase;
	public int engineState;
	public FluidTank fuelTank = new FluidTank(this.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
	
	public int rocketTier = 1;
	private int lasttier = 0;
	private boolean justSpw;
	public TileEntityDockingPort dockport;
	private int[] dockPos;
	
	public long choreoStartTick = 0;
	
	public double ActialMotionX = 0;
	public double ActialMotionZ = 0;
	
	public ChoreoScene currentChoreo;
	
	protected IUpdatePlayerListBox rocketSoundUpdater;
	
	public EntityRocketFakeTiered(World par1World)
	{
		super(par1World);
		this.launchPhase = EnumLaunchPhase.DOCKED.ordinal();
		this.preventEntitySpawning = true;
		this.ignoreFrustumCheck = true;
		this.yOffset = 2.2F;
	}
	
	public EntityRocketFakeTiered(World world, double posX, double posY, double posZ, int tier)
	{
		this(world);
		this.setTier(tier);
		if (tier == 1)
		{
			this.setSize(1.1F, 3.0F);
		} else if (tier == 2)
		{
			this.setSize(1.2F, 5.0F);
		} else if (tier == 3)
		{
			this.setSize(1.8F, 6F);
		}
		this.yOffset = 2.2F;
		this.setPosition(posX, posY, posZ);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.prevPosZ = posZ;
		
		this.isImmuneToFire = true;
		this.justSpw = true;
		
	}
	
	public EntityRocketFakeTiered(World world, double posX, double posY, double posZ, int tier, TileEntityDockingPort te)
	{
		this(world, posX, posY, posZ, tier);
		dockport = te;
	}
	
	public void setTier(int tier)
	{
		this.rocketTier = tier;
	}
	
	public int getTier()
	{
		return rocketTier;
	}
	
	public static int getTierFromItem(ItemStack is)
	{
		if (is.getItem() == GCItems.rocketTier1)
		{
			return 1;
		} else if (is.getItem() == MarsItems.spaceship)
		{
			return 2;
		} else if (is.getItem() == AsteroidsItems.tier3Rocket)
		{
			return 3;
		} else return -1;
	}
	
	public int getFuelTankCapacity()
	{
		return 1000 + (this.getTier() > 1 ? 500 : 0);
	}
	
	public int getScaledFuelLevel(int scale)
	{
		if (this.getFuelTankCapacity() <= 0)
		{
			return 0;
		}
		if (dockport != null)
		{
			return this.dockport.fuelTank.getFluidAmount() * scale / this.getFuelTankCapacity() / ConfigManagerCore.rocketFuelFactor;
		}
		return 0;
	}
	
	public boolean hasValidFuel()
	{
		if (dockport != null)
		{
			return this.dockport.fuelTank.getFluidAmount() > 500;
		}
		return false;
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		if (this.launchPhase == EnumLaunchPhase.LAUNCHED.ordinal())
		{
			return false;
		}
		
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayerMP)
		{
			if (!this.worldObj.isRemote && !shouldIgnoreShiftExit())
			{
				GalacticraftCore.packetPipeline.sendTo(new PacketSimple(EnumSimplePacket.C_RESET_THIRD_PERSON, new Object[] {}), (EntityPlayerMP) player);
				GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
				stats.chatCooldown = 0;
				player.mountEntity(null);
				if (dockport != null)
				{
					player.setPositionAndUpdate(player.posX, dockport.yCoord + 1, player.posZ);
				} else player.setPositionAndUpdate(player.posX, player.posY + 4, player.posZ);
			}
			
			return true;
		} else if (player instanceof EntityPlayerMP)
		{
			if (!this.worldObj.isRemote)
			{
				PacketHandler.sendTo(new RocketControlsPacket(), (EntityPlayerMP) player);
				GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
				stats.chatCooldown = 0;
				player.mountEntity(this);
				
			}
			
			return true;
		}
		
		return false;
	}
	
	public void QuitRocket(EntityPlayer player)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayerMP)
		{
			if (!this.worldObj.isRemote)
			{
				GalacticraftCore.packetPipeline.sendTo(new PacketSimple(EnumSimplePacket.C_RESET_THIRD_PERSON, new Object[] {}), (EntityPlayerMP) player);
				GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
				stats.chatCooldown = 0;
				player.mountEntity(null);
				if (dockport != null)
				{
					player.setPositionAndUpdate(player.posX, dockport.yCoord + 1, player.posZ);
				} else player.setPositionAndUpdate(player.posX, player.posY + 4, player.posZ);
			}
		}
	}
	
	public void EnterRocket(EntityPlayer player)
	{
		if (player instanceof EntityPlayerMP)
		{
			if (!this.worldObj.isRemote)
			{
				PacketHandler.sendTo(new RocketControlsPacket(), (EntityPlayerMP) player);
				//   GalacticraftCore.packetPipeline.sendTo(new PacketSimple(EnumSimplePacket.C_DISPLAY_ROCKET_CONTROLS, new Object[] { }), (EntityPlayerMP) player);
				GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
				stats.chatCooldown = 0;
				player.mountEntity(this);
			}
		}
	}
	
	@Override
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
		}
	}
	
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return null;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return null;
	}
	
	@Override
	public boolean canBePushed()
	{
		return false;
	}
	
	public float getRotateOffset()
	{
		if (this.getTier() == 1)
		{
			return -1.5F;
		} else if (this.getTier() == 2)
		{
			return -0.6F;
		} else if (this.getTier() == 3)
		{
			return 0.25F;
		} else return 0;
	}
	
	protected void spawnParticles(boolean launched)
	{
		if (!this.isDead)
		{
			double x1 = 2 * Math.cos(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * Math.PI / 180.0D);
			double z1 = 2 * Math.sin(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * Math.PI / 180.0D);
			double y1 = 2 * Math.cos((this.rotationPitch - 180) * Math.PI / 180.0D);
			
			final double y = this.prevPosY + (this.posY - this.prevPosY) + y1 - 2.3;
			
			final double x2 = this.posX + x1;
			final double z2 = this.posZ + z1;
			
			EntityLivingBase riddenByEntity = this.riddenByEntity instanceof EntityLivingBase ? (EntityLivingBase) this.riddenByEntity : null;
			
			if (this.getLaunched())
			{
				Vector3 motionVec = new Vector3(x1, y1 - 1, z1);
				GliderCore.proxy.spawnParticle("launchFlameSmall", new Vector3(x2 + 0.1 - this.rand.nextDouble() / 10, y + 0.5D, z2 + 0.1 - this.rand.nextDouble() / 10), motionVec,
						new Object[] { riddenByEntity });
				GliderCore.proxy.spawnParticle("launchFlameSmall", new Vector3(x2 - 0.1 + this.rand.nextDouble() / 10, y + 0.5D, z2 + 0.1 - this.rand.nextDouble() / 10), motionVec,
						new Object[] { riddenByEntity });
				GliderCore.proxy.spawnParticle("launchFlameSmall", new Vector3(x2 - 0.1 + this.rand.nextDouble() / 10, y + 0.5D, z2 - 0.1 + this.rand.nextDouble() / 10), motionVec,
						new Object[] { riddenByEntity });
				GliderCore.proxy.spawnParticle("launchFlameSmall", new Vector3(x2 + 0.1 - this.rand.nextDouble() / 10, y + 0.5D, z2 - 0.1 + this.rand.nextDouble() / 10), motionVec,
						new Object[] { riddenByEntity });
				
			}
			
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (!this.isDead && !par1DamageSource.isFireDamage())
		{
			boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode;
			Entity e = par1DamageSource.getEntity();
			if (this.isEntityInvulnerable() || this.posY > 300 || (e instanceof EntityLivingBase && !(e instanceof EntityPlayer)))
			{
				return false;
			} else
			{
				this.setBeenAttacked();
				this.shipDamage += par2 * 10;
				
				if (e instanceof EntityPlayer && ((EntityPlayer) e).capabilities.isCreativeMode)
				{
					this.shipDamage = 100;
				}
				
				if (flag || this.shipDamage > 90)
				{
					if (this.riddenByEntity != null)
					{
						this.riddenByEntity.mountEntity(null);
					}
					
					if (flag)
					{
						this.setDead();
					} else
					{
						this.setDead();
						this.dropShipAsItem();
					}
					return true;
				}
				
				return true;
			}
		}
		return true;
	}
	
	public void dropShipAsItem()
	{
		if (this.worldObj.isRemote)
		{
			return;
		}
		
		for (final ItemStack item : this.getItemsDropped(new ArrayList<ItemStack>()))
		{
			EntityItem entityItem = this.entityDropItem(item, 0);
			
			if (item.hasTagCompound())
			{
				entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
			}
		}
	}
	
	public List<ItemStack> getItemsDropped(List<ItemStack> droppedItemList)
	{
		if (this.getTier() == 1) droppedItemList.add(new ItemStack(GCItems.rocketTier1));
		else if (this.getTier() == 2) droppedItemList.add(new ItemStack(MarsItems.spaceship));
		else if (this.getTier() == 3) droppedItemList.add(new ItemStack(AsteroidsItems.tier3Rocket));
		return droppedItemList;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}
	
	@Override
	public boolean shouldRiderSit()
	{
		return false;
	}
	
	@Override
	public void onUpdate()
	{
		
		if (this.ticks >= Long.MAX_VALUE)
		{
			this.ticks = 0;
		}
		this.ticks++;
		
		if (currentChoreo != null && currentChoreo.choreoStarted)
		{
			currentChoreo.TickChoreo((int) (ticks - choreoStartTick));
		}
		
		if (lasttier != rocketTier && !this.worldObj.isRemote)
		{
			PacketHandler.sendToAllAround(new SyncRocketTierPacket(this), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 20));
			lasttier = rocketTier;
		}
		
		super.onUpdate();
		
		if (this.riddenByEntity != null)
		{
			this.riddenByEntity.fallDistance = 0.0F;
			
			if (!this.worldObj.isRemote && this.dockport == null && dockPos != null && dockPos.length > 0)
			{
				dockport = (TileEntityDockingPort) this.worldObj.getTileEntity(dockPos[0], dockPos[1], dockPos[2]);
			}
			
		}
		
		if ((this.getLaunched() || this.engineState == EnumEngineState.IGNITED.ordinal()) && !ConfigManagerCore.disableSpaceshipParticles)
		{
			if (this.worldObj.isRemote)
			{
				this.spawnParticles(this.getLaunched());
			}
		}
		
		if (this.shipDamage > 0)
		{
			this.shipDamage--;
		}
		
		if (!this.worldObj.isRemote)
		{
			if (this.posY < 0.0D)
			{
				this.kill();
			}
			
		}
		
		AxisAlignedBB box = null;
		
		box = this.boundingBox.expand(0.5D, 2.0D, 0.5D);
		
		final List<?> var15 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, box);
		
		if (var15 != null && !var15.isEmpty())
		{
			for (int var52 = 0; var52 < var15.size(); ++var52)
			{
				final Entity var17 = (Entity) var15.get(var52);
				
				if (var17 != this.riddenByEntity)
				{
					var17.applyEntityCollision(this);
				}
			}
		}
		
		if (this.rotationPitch > 90)
		{
			this.rotationPitch = 90;
		}
		
		if (this.rotationPitch < -90)
		{
			this.rotationPitch = -90;
		}
		
		if (this.engineState == EnumEngineState.IGNITED.ordinal())
		{
			this.motionX = -(50 * Math.cos(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * 0.01 * Math.PI / 180.0D)) * 5;
			this.motionZ = -(50 * Math.sin(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * 0.01 * Math.PI / 180.0D)) * 5;
		} else
		{
			if (this.launchPhase != EnumLaunchPhase.FLYAWAY.ordinal())
			{
				this.motionX = ActialMotionX;
				this.motionZ = ActialMotionZ;
			}
		}
		
		if (this.riddenByEntity != null && launchPhase != EnumLaunchPhase.DOCKED.ordinal())
		{
			EntityPlayer player = (EntityPlayer) this.riddenByEntity;
		}
		if (this.worldObj.isRemote)
		{
			this.setPosition(this.posX, this.posY, this.posZ);
			
			if (this.shouldMoveClientSide())
			{
				this.moveEntity(this.motionX, this.motionY, this.motionZ);
			}
		} else
		{
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
		}
		
		this.setRotation(this.rotationYaw, this.rotationPitch);
		
		if (this.worldObj.isRemote)
		{
			this.setPosition(this.posX, this.posY, this.posZ);
		}
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.launchPhase != EnumLaunchPhase.DOCKED.ordinal() || this.getLaunched())
		{
			if (this.rocketSoundUpdater != null)
			{
				this.rocketSoundUpdater.update();
			}
		}
		if (!this.worldObj.isRemote && (this.ticks % 100 == 0 || this.ticks < 5))
		{
			GalacticraftCore.packetPipeline.sendToDimension(new PacketDynamic(this), this.worldObj.provider.dimensionId);
		}
	}
	
	protected boolean shouldMoveClientSide()
	{
		return true;
	}
	
	public void turnYaw(float f)
	{
		this.rotationYaw += f;
	}
	
	public void turnPitch(float f)
	{
		this.rotationPitch += f;
	}
	
	public void ActMoveEntity(double x, double y, double z)
	{
		this.ActialMotionX += x;
		this.ActialMotionZ += z;
		this.motionY += z;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	{
		this.setRotation(par7, par8);
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("launchPhase", this.launchPhase + 1);
		nbt.setInteger("TIER", rocketTier);
		if (dockport != null) nbt.setIntArray("TILE", new int[] { dockport.xCoord, dockport.yCoord, dockport.zCoord });
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.setLaunchPhase(EnumLaunchPhase.values()[nbt.getInteger("launchPhase") - 1]);
		rocketTier = nbt.getInteger("TIER");
		dockPos = nbt.getIntArray("TILE");
	}
	
	public void startChoreoScene(ChoreoScene scene)
	{
		if (!this.worldObj.isRemote)
		{
			PacketHandler.sendToDimension(new StartChoreoClientPacket(this, scene), this.worldObj.provider.dimensionId);
		}
		scene.StartChoreo();
		this.currentChoreo = scene;
		this.choreoStartTick = ticks;
		currentChoreo.TickChoreo(0);
	}
	
	public boolean getLaunched()
	{
		return this.engineState == EnumEngineState.IGNITED.ordinal();
	}
	
	public void ignite()
	{
		this.engineState = EnumEngineState.IGNITED.ordinal();
	}
	
	public void unignite()
	{
		this.engineState = EnumEngineState.UNIGNITED.ordinal();
	}
	
	@Override
	public double getMountedYOffset()
	{
		return -0.9D;
	}
	
	public void onReachAtmosphere()
	{
		if (dockport != null && this.riddenByEntity != null)
		{
			EntityPlayer player = (EntityPlayer) this.riddenByEntity;
			GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
			ItemStack[] rcStacks = new ItemStack[2 + dockport.addSlots];
			
			if (dockport.getStackInSlot(dockport.getSizeInventory() - 3) != null)
			{
				rcStacks[rcStacks.length - 2] = dockport.getStackInSlot(dockport.getSizeInventory() - 3).copy();
				dockport.setInventorySlotContents(dockport.getSizeInventory() - 3, null);
			}
			if (dockport.getStackInSlot(dockport.getSizeInventory() - 2) != null)
			{
				rcStacks[rcStacks.length - 1] = dockport.getStackInSlot(dockport.getSizeInventory() - 2).copy();
				dockport.setInventorySlotContents(dockport.getSizeInventory() - 2, null);
			}
			
			if (dockport.addSlots != 0)
			{
				for (int i = 0; i < dockport.addSlots; i++)
				{
					ItemStack stack = dockport.getStackInSlot(i);
					if (stack != null)
					{
						stack = stack.copy();
						if (i < rcStacks.length)
						{
							rcStacks[i] = stack;
						}
						dockport.setInventorySlotContents(i, null);
					}
					
				}
			}
			stats.rocketStacks = rcStacks;
			stats.fuelLevel = dockport.fuelTank.getFluidAmount();
			
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void spawnParticle(String var1, double var2, double var4, double var6, double var8, double var10, double var12)
	{
	}
	
	@Override
	public boolean canRiderInteract()
	{
		return true;
	}
	
	public void setLaunchPhase(EnumLaunchPhase phase)
	{
		this.launchPhase = phase.ordinal();
	}
	
	@Override
	public boolean shouldIgnoreShiftExit()
	{
		return this.launchPhase != EnumLaunchPhase.DOCKED.ordinal();
	}
	
	public void adjustDisplay(int[] data)
	{
		GL11.glRotatef(data[4], -1, 0, 0);
		GL11.glTranslatef(0, this.height / 4, 0);
	}
	
	@Override
	public float getCameraZoom()
	{
		return 15.0F;
	}
	
	@Override
	public boolean defaultThirdPerson()
	{
		return true;
	}
	
	public boolean canBreath()
	{
		return true;
	}
	
	@Override
	public void setDead()
	{
		super.setDead();
		
		if (this.rocketSoundUpdater != null)
		{
			this.rocketSoundUpdater.update();
		}
	}
	
	public void stopRocketSound()
	{
		if (this.rocketSoundUpdater != null)
		{
			((SoundUpdaterRocket) this.rocketSoundUpdater).stopRocketSound();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public IUpdatePlayerListBox getSoundUpdater()
	{
		return this.rocketSoundUpdater;
	}
	
	@SideOnly(Side.CLIENT)
	public ISound setSoundUpdater(EntityPlayerSP player)
	{
		this.rocketSoundUpdater = new SoundUpdateRocketFake(player, this);
		return (ISound) this.rocketSoundUpdater;
	}
	
	@Override
	public void getNetworkedData(ArrayList<Object> sendData)
	{
		sendData.add(this.getTier());
		sendData.add(this.posX);
		sendData.add(this.posY);
		sendData.add(this.posZ);
		
	}
	
	@Override
	public void decodePacketdata(ByteBuf buffer)
	{
		this.setTier(buffer.readInt());
		this.posX = buffer.readDouble();
		this.posY = buffer.readDouble();
		this.posZ = buffer.readDouble();
	}
	
	@Override
	public void handlePacketData(Side side, EntityPlayer player)
	{
	}
	
}