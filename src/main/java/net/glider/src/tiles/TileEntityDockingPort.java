
package net.glider.src.tiles;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.IScaleableFuelLevel;
import micdoodle8.mods.galacticraft.core.inventory.IInventorySettable;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.items.ItemCanisterGeneric;
import micdoodle8.mods.galacticraft.core.network.IPacketReceiver;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.glider.src.blocks.BlockDockingPoint;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.entity.EntityRocketFakeTiered.EnumLaunchPhase;
import net.glider.src.gui.ContainerDockingPort;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.DockItemSyncPacket;
import net.glider.src.network.packets.InvScalePacket;
import net.glider.src.network.packets.SendUUIDPacket;
import net.glider.src.utils.GLoger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;

public class TileEntityDockingPort extends TileEntityAdvanced implements IInventorySettable, IPacketReceiver, IScaleableFuelLevel, ISidedInventory {
	private final int tankCapacity = 5000;
	@NetworkedField(targetSide = Side.CLIENT)
	public FluidTank fuelTank = new FluidTank(this.tankCapacity);
	
	public ItemStack[] chestContents = new ItemStack[4];
	
	public boolean adjacentChestChecked = false;
	
	public float lidAngle;
	
	public float prevLidAngle;
	
	public int numUsingPlayers;
	
	public int addSlots = 0;
	public int lastSlots = 0;
	// public int numNonEmpty = 0;
	public ItemStack[] oldStacks;
	// public boolean hasCredit = false;
	// public int vantedSize;
	public EntityRocketFakeTiered rocket;
	private UUID entUUID;
	private int lastItemUniqueId = -1;
	private Random rand = new Random();
	
	@Override
	public void validate()
	{
		super.validate();
		
		if (this.worldObj != null && this.worldObj.isRemote)
		{
			// Request size + contents information from server
			// GalacticraftCore.packetPipeline.sendToServer(new
			// PacketDynamicInventory(this));
		}
	}
	
	@Override
	public int getScaledFuelLevel(int i)
	{
		final double fuelLevel = this.fuelTank.getFluid() == null ? 0 : this.fuelTank.getFluid().amount;
		
		return (int) (fuelLevel * i / this.tankCapacity);
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.chestContents.length;
	}
	
	@Override
	public void setSizeInventory(int size)
	{
		if ((size - 4) % 18 != 0)
		{
			GCLog.debug("Strange TileEntityDockport inventory size received from server " + size);
		}
		this.chestContents = new ItemStack[size];
	}
	
	public void setLastID(int id)
	{
		this.lastItemUniqueId = id;
	}
	
	public void setAddSlots(int size)
	{
		this.addSlots = size;
		oldStacks = chestContents;
		
		this.setSizeInventory(size + 4);
		this.setInventorySlotContents(this.getSizeInventory() - 1, oldStacks[oldStacks.length - 1]);
		this.setInventorySlotContents(this.getSizeInventory() - 2, oldStacks[oldStacks.length - 2]);
		this.setInventorySlotContents(this.getSizeInventory() - 3, oldStacks[oldStacks.length - 3]);
		this.setInventorySlotContents(this.getSizeInventory() - 4, oldStacks[oldStacks.length - 4]);
		if (size != 0)
		{
			for (int i = 0; i < oldStacks.length - 4; i++)
			{
				this.setInventorySlotContents(i, oldStacks[i]);
			}
		}
		lastSlots = addSlots;
		
		if (!worldObj.isRemote)
		{
			PacketHandler.sendToAllAround(new InvScalePacket(chestContents.length, this.xCoord, this.yCoord, this.zCoord), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 20));
			PacketHandler.sendToAllAround(new DockItemSyncPacket(chestContents, this.xCoord, this.yCoord, this.zCoord), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 20));
		}
	}
	
	@Override
	public ItemStack getStackInSlot(int par1)
	{
		if (par1 < this.getSizeInventory())
		{
			return this.chestContents[par1];
		} else
			return null;
	}
	
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (par1 < this.getSizeInventory())
		{
			if (this.chestContents[par1] != null)
			{
				ItemStack itemstack;
				
				if (this.chestContents[par1].stackSize <= par2)
				{
					itemstack = this.chestContents[par1];
					this.chestContents[par1] = null;
					this.markDirty();
					return itemstack;
				} else
				{
					itemstack = this.chestContents[par1].splitStack(par2);
					
					if (this.chestContents[par1].stackSize == 0)
					{
						this.chestContents[par1] = null;
					}
					
					this.markDirty();
					return itemstack;
				}
			} else
			{
				return null;
			}
		} else
			return null;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (par1 < this.getSizeInventory())
		{
			if (this.chestContents[par1] != null)
			{
				ItemStack itemstack = this.chestContents[par1];
				this.chestContents[par1] = null;
				return itemstack;
			} else
			{
				return null;
			}
		} else
			return null;
	}
	
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		if (par1 < this.getSizeInventory())
		{
			this.chestContents[par1] = par2ItemStack;
			
			if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
			{
				par2ItemStack.stackSize = this.getInventoryStackLimit();
			}
			
			this.markDirty();
		}
	}
	
	@Override
	public String getInventoryName()
	{
		return GCCoreUtil.translate("container.parachest.name");
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		lastItemUniqueId = nbt.getInteger("LASTITEMID");
		if (nbt.hasKey("RocketUUIDMost", 4) && nbt.hasKey("RocketUUIDLeast", 4))
		{
			this.entUUID = new UUID(nbt.getLong("RocketUUIDMost"), nbt.getLong("RocketUUIDLeast"));
		}
		
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		
		if (nbt.getInteger("chestContentLength") == 3)
		{
			this.invalidate();
		} else
		{
			
			this.chestContents = new ItemStack[nbt.getInteger("chestContentLength")];
			
			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;
				
				if (j < this.chestContents.length)
				{
					this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
				}
			}
			
			if (nbt.hasKey("fuelTank"))
			{
				this.fuelTank.readFromNBT(nbt.getCompoundTag("fuelTank"));
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger("LASTITEMID", lastItemUniqueId);
		
		nbt.setInteger("chestContentLength", this.chestContents.length);
		
		if (rocket != null)
		{
			nbt.setLong("RocketUUIDMost", rocket.getUniqueID().getMostSignificantBits());
			nbt.setLong("RocketUUIDLeast", rocket.getUniqueID().getLeastSignificantBits());
		}
		
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < this.chestContents.length; ++i)
		{
			if (this.chestContents[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		
		nbt.setTag("Items", nbttaglist);
		
		if (this.fuelTank.getFluid() != null)
		{
			nbt.setTag("fuelTank", this.fuelTank.writeToNBT(new NBTTagCompound()));
		}
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}
	
	@Override
	public void updateContainingBlockInfo()
	{
		super.updateContainingBlockInfo();
		this.adjacentChestChecked = false;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		boolean anotherItem = false;
		float f;
		// finish loading entity from NBT
		if (entUUID != null && rocket == null)
		{
			List<Entity> Entlist = worldObj.loadedEntityList;
			
			for (int i = 0; i < Entlist.size(); i++)
			{
				Entity ent = Entlist.get(i);
				if (ent.getUniqueID().equals(entUUID))
				{
					if (ent instanceof EntityRocketFakeTiered)
					{
						GLoger.logInfo("Find writen entity from UUID");
						rocket = (EntityRocketFakeTiered) ent;
						if (rocket != null)
							PacketHandler.sendToAllAround(new SendUUIDPacket(rocket.getUniqueID()), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 10));
					}
				}
			}
		}
		// alfa compactability. deleting all invalid entities
		if (this.isInvalid())
		{
			this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air, 0, 2);
		}
		boolean preFueled = false;
		// clearing slot if rocket is dead
		if (rocket != null && rocket.isDead && !worldObj.isRemote)
		{
			if (rocket.launchPhase == EnumLaunchPhase.FLYAWAY.ordinal() || rocket.launchPhase == EnumLaunchPhase.LAUNCHED.ordinal())
			{
				this.setInventorySlotContents(this.getSizeInventory() - 3, null);
			}
			this.setInventorySlotContents(this.getSizeInventory() - 2, null);
			this.fuelTank.drain(this.fuelTank.getCapacity(), true);
			rocket = null;
		}
		if (this.getStackInSlot(this.getSizeInventory() - 2) != null)
		{
			int type = this.getStackInSlot(this.getSizeInventory() - 2).getItemDamage();
			// Checking type
			if (type == IRocketType.EnumRocketType.PREFUELED.getIndex())
			{
				preFueled = true;
			}
		}
		
		//checking for replaced item (dirty code, it creates useless int in NBT) 
		if (this.getStackInSlot(this.getSizeInventory() - 2) != null && rocket != null)
		{
			boolean writeID = false;
			if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
			{
				NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
				if (tag.hasKey("UniqueID"))
				{
					if (tag.getInteger("UniqueID") != lastItemUniqueId)
					{
						writeID = true;
						anotherItem = true;
					}
				} else
				{
					writeID = true;
					anotherItem = true;
				}
			} else
			{
				writeID = true;
				anotherItem = true;
			}
			if (writeID)
			{
				NBTTagCompound tag;
				if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
				{
					tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
				} else
				{
					tag = new NBTTagCompound();
				}
				lastItemUniqueId = rand.nextInt(10000000);
				tag.setInteger("UniqueID", lastItemUniqueId);
				ItemStack is = this.getStackInSlot(this.getSizeInventory() - 2);
				is.setTagCompound(tag);
				this.setInventorySlotContents(this.getSizeInventory() - 2, is);
			}
		}
		
		// Checking and saving fuel data in rocket item NBT
		if (this.getStackInSlot(this.getSizeInventory() - 2) != null && rocket != null && !anotherItem && !preFueled)
		{
			int tier = EntityRocketFakeTiered.getTierFromItem(this.getStackInSlot(this.getSizeInventory() - 2));
			ItemStack is = this.getStackInSlot(this.getSizeInventory() - 2);
			if (tier == rocket.getTier())
			{
				if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
				{
					NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
					int fuel = tag.getInteger("RocketFuel");
					if (fuel != this.fuelTank.getFluidAmount())
					{
						if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
						{
							tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
						} else
						{
							tag = new NBTTagCompound();
						}
						if (this.fuelTank.getFluidAmount() != 0)
							tag.setInteger("RocketFuel", this.fuelTank.getFluidAmount());
						is = this.getStackInSlot(this.getSizeInventory() - 2);
						is.setTagCompound(tag);
						this.setInventorySlotContents(this.getSizeInventory() - 2, is);
					}
				} else
				{
					NBTTagCompound tag;
					if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
					{
						tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
					} else
					{
						tag = new NBTTagCompound();
					}
					if (this.fuelTank.getFluidAmount() != 0)
						tag.setInteger("RocketFuel", this.fuelTank.getFluidAmount());
					is = this.getStackInSlot(this.getSizeInventory() - 2);
					is.setTagCompound(tag);
					this.setInventorySlotContents(this.getSizeInventory() - 2, is);
				}
			}
		}
		
		// start massive check for rocket item
		if (this.getStackInSlot(this.getSizeInventory() - 2) != null)
		{
			if (this.getStackInSlot(this.getSizeInventory() - 2).getItem() == GCItems.rocketTier1 || this.getStackInSlot(this.getSizeInventory() - 2).getItem() == MarsItems.spaceship || this.getStackInSlot(this.getSizeInventory() - 2).getItem() == AsteroidsItems.tier3Rocket)
			{
				int type = this.getStackInSlot(this.getSizeInventory() - 2).getItemDamage();
				// Checking type
				if (type == IRocketType.EnumRocketType.DEFAULT.getIndex() || type == IRocketType.EnumRocketType.PREFUELED.getIndex())
				{
					addSlots = 0;
					
				} else
				{
					// getting inventory size from IRocketType
					addSlots = IRocketType.EnumRocketType.values()[type].getInventorySpace() - 2;
				}
				// setting up rocket if yet not
				if (rocket == null && !worldObj.isRemote)
				{
					int tier = EntityRocketFakeTiered.getTierFromItem(this.getStackInSlot(this.getSizeInventory() - 2));
					double hight = 1.8D;
					if (tier == 2)
						hight = 1.9D;
					else if (tier == 3)
						hight = 2.4D;
					rocket = new EntityRocketFakeTiered(worldObj, this.xCoord + 0.5D, this.yCoord - hight, this.zCoord + 0.5D, tier, this);
					// creating fuel tank for rocket fuel size
					this.fuelTank = new FluidTank(rocket.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
					
					if (preFueled)
					{
						// if rocket is prefueld, fueling it.
						this.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, this.fuelTank.getCapacity()), true);
					} else if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
					{
						// reading fuel from item NBT
						NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
						int fuel = tag.getInteger("RocketFuel");
						this.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, fuel), true);
					}
					
					// spawning rocket in world
					rocket.fuelTank = fuelTank;
					worldObj.spawnEntityInWorld(rocket);
					if (rocket != null)
						PacketHandler.sendToAllAround(new SendUUIDPacket(rocket.getUniqueID()), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 10));
				} else if (!worldObj.isRemote)
				{
					// Updating rocket tier if needed. also need update if item changed(swapped to the same)
					int tier = EntityRocketFakeTiered.getTierFromItem(this.getStackInSlot(this.getSizeInventory() - 2));
					if (rocket.getTier() != tier || anotherItem)
					{
						rocket.setTier(tier);
						// updating fuel tank also
						this.fuelTank = new FluidTank(rocket.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
						
						if (preFueled)
						{
							// if rocket is prefueld, fueling it.
							this.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, this.fuelTank.getCapacity()), true);
						} else if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
						{
							// reading fuel from item NBT
							NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
							int fuel = tag.getInteger("RocketFuel");
							this.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, fuel), true);
						}
						rocket.fuelTank = fuelTank;
						// and repositioning
						double hight = 1.8D;
						if (tier == 2)
							hight = 1.9D;
						else if (tier == 3)
							hight = 2.4D;
						rocket.setPositionAndRotation(this.xCoord + 0.5D, this.yCoord - hight, this.zCoord + 0.5D, rocket.rotationYaw, rocket.rotationPitch);
					}
				}
				
			}
			
		} else
		{
			addSlots = 0;
			if (!worldObj.isRemote && rocket != null)
			{
				if (rocket.riddenByEntity != null)
				{
					// before delete rocket from world, make player quit it
					EntityPlayer player = (EntityPlayer) rocket.riddenByEntity;
					rocket.QuitRocket(player);
					
				}
				rocket.setDead();
				this.fuelTank.drain(this.fuelTank.getCapacity(), true);
				
				rocket = null;
			}
		}
		// checking additional slots and if needed reloading them
		if (lastSlots != addSlots)
		{
			this.setAddSlots(addSlots);
		}
		
		if (!this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticks + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
		{
			this.numUsingPlayers = 0;
			f = 5.0F;
			List<?> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.xCoord - f, this.yCoord - f, this.zCoord - f, this.xCoord + 1 + f, this.yCoord + 1 + f, this.zCoord + 1 + f));
			Iterator<?> iterator = list.iterator();
			
			while (iterator.hasNext())
			{
				EntityPlayer entityplayer = (EntityPlayer) iterator.next();
				
				if (entityplayer.openContainer instanceof ContainerDockingPort)
				{
					++this.numUsingPlayers;
				}
			}
		}
		
		this.prevLidAngle = this.lidAngle;
		f = 0.1F;
		double d0;
		
		if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F)
		{
			double d1 = this.xCoord + 0.5D;
			d0 = this.zCoord + 0.5D;
			
		}
		
		if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
		{
			float f1 = this.lidAngle;
			
			if (this.numUsingPlayers > 0)
			{
				this.lidAngle += f;
			} else
			{
				this.lidAngle -= f;
			}
			
			if (this.lidAngle > 1.0F)
			{
				this.lidAngle = 1.0F;
			}
			
			float f2 = 0.5F;
			
			if (this.lidAngle < f2 && f1 >= f2)
			{
				d0 = this.xCoord + 0.5D;
				double d2 = this.zCoord + 0.5D;
				
			}
			
			if (this.lidAngle < 0.0F)
			{
				this.lidAngle = 0.0F;
			}
		}
		// tank fueling and draining code
		//TODO add compacability with IC2, forestry, thermal foundation...
		//FIXME filling more than capacity of rocket!
		if (!this.worldObj.isRemote && rocket != null)
		{
			// drain
			this.checkFluidTankTransfer(this.chestContents.length - 1, this.fuelTank);
			
			// fuel (code from fuel loader)
			ItemStack stack = getStackInSlot(this.chestContents.length - 4);
			
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemCanisterGeneric)
				{
					if (stack.getItem() == GCItems.fuelCanister)
					{
						int originalDamage = stack.getItemDamage();
						int used = this.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, ItemCanisterGeneric.EMPTY - originalDamage), true);
						if (originalDamage + used == ItemCanisterGeneric.EMPTY)
							stack = new ItemStack(GCItems.oilCanister, 1, ItemCanisterGeneric.EMPTY);
						else
							stack = new ItemStack(GCItems.fuelCanister, 1, originalDamage + used);
					}
				} else
				{
					final FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(stack);
					
					if (liquid != null)
					{
						boolean isFuel = FluidUtil.testFuel(FluidRegistry.getFluidName(liquid));
						
						if (isFuel)
						{
							if (this.fuelTank.getFluid() == null || this.fuelTank.getFluid().amount + liquid.amount <= this.fuelTank.getCapacity())
							{
								this.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, liquid.amount), true);
								
								if (FluidContainerRegistry.isBucket(stack) && FluidContainerRegistry.isFilledContainer(stack))
								{
									final int amount = stack.stackSize;
									if (amount > 1)
										this.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, (amount - 1) * FluidContainerRegistry.BUCKET_VOLUME), true);
									stack = new ItemStack(Items.bucket, amount);
								} else
								{
									stack.stackSize--;
									
									if (stack.stackSize == 0)
									{
										stack = null;
									}
								}
							}
						}
					}
				}
			}
			// reloading item
			this.setInventorySlotContents(this.chestContents.length - 4, stack);
		}
	}
	
	private void checkFluidTankTransfer(int slot, FluidTank tank)
	{
		FluidUtil.tryFillContainerFuel(tank, this.chestContents, slot);
	}
	
	@Override
	public boolean receiveClientEvent(int par1, int par2)
	{
		if (par1 == 1)
		{
			this.numUsingPlayers = par2;
			return true;
		} else
		{
			return super.receiveClientEvent(par1, par2);
		}
	}
	
	@Override
	public void openInventory()
	{
		if (this.numUsingPlayers < 0)
		{
			this.numUsingPlayers = 0;
		}
		
		++this.numUsingPlayers;
		if (!this.worldObj.isRemote)
		{
			// Updating code
			PacketHandler.sendToAllAround(new InvScalePacket(chestContents.length, this.xCoord, this.yCoord, this.zCoord), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 20));
			PacketHandler.sendToAllAround(new DockItemSyncPacket(chestContents, this.xCoord, this.yCoord, this.zCoord), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 20));
			if (rocket != null)
				PacketHandler.sendToAllAround(new SendUUIDPacket(rocket.getUniqueID()), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 10));
		}
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
	}
	
	@Override
	public void closeInventory()
	{
		if (this.getBlockType() != null && this.getBlockType() instanceof BlockDockingPoint)
		{
			--this.numUsingPlayers;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return true;
	}
	
	@Override
	public void invalidate()
	{
		super.invalidate();
		this.updateContainingBlockInfo();
	}
	
	@Override
	public double getPacketRange()
	{
		return 12.0D;
	}
	
	@Override
	public int getPacketCooldown()
	{
		return 3;
	}
	
	@Override
	public boolean isNetworkedTile()
	{
		return true;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		if (addSlots == 0)
		{
			return new int[] {};
		} else
		{
			int[] ret = new int[addSlots];
			for (int i = 0; i < addSlots; i++)
			{
				ret[i] = i;
			}
			return ret;
		}
	}
	
	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
	{
		return true;
	}
	
	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
	{
		return true;
	}
}
