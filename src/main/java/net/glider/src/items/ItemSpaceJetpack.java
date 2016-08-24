
package net.glider.src.items;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.glider.src.ClientProxy;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.glider.src.entity.ExtendedPlayer;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.JetpackUseFuelPacket;
import net.glider.src.network.packets.SyncPressedKeysPacket;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpaceJetpack extends ItemArmorMod {
	
	static final ArmorMaterial material = EnumHelper.addArmorMaterial("JETPACK", 500, new int[] { 0, 0, 0, 0 }, 0);
	
	public boolean activated = false;
	
	public static List<Integer> KeysPressed = new ArrayList();
	
	public FluidTank RCSFuel = new FluidTank(1750 * ConfigManagerCore.rocketFuelFactor);
	
	public double usedFuelAm = 0;
	
	private boolean updateValues = false;
	private boolean needSave = false;
	
	private int ticks_from_sent = 0;
	
	private ScoreObjective obj;
	
	public ItemSpaceJetpack(String uln)
	{
		super(uln, CreativeTabs.tabTools, material, 1);
		this.setNoRepair();
		updateValues = false;
		activated = false;
		this.setNoRepair();
		this.setMaxDamage(0);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return GliderModInfo.ModTestures + ":textures/JetPack.png";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyCore.galacticraftItem;
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		return ClientProxy.model;
	}
	
	//TODO create control Gui
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (ticks_from_sent > 0)
		{
			ticks_from_sent--;
			return;
		}
		ExtendedPlayer prop = ExtendedPlayer.get(player);
		
		//	if (RCSFuel.getFluid() == null)this.RCSFuel.fill(new FluidStack(GalacticraftCore.fluidFuel, RCSFuel.getCapacity()), true);
		NBTTagCompound tag = new NBTTagCompound();
		if (itemStack.hasTagCompound())
		{
			tag = itemStack.getTagCompound();
		}
		if (itemStack.hasTagCompound())
		{
			this.readFromNBT(tag);
			if (this.activated && world.isRemote && !prop.getAnimationHandler().isAnimationActive("Enabled idle") && !prop.getAnimationHandler().isAnimationActive("Enable") && !prop.getAnimationHandler().isAnimationActive("Disable"))
			{
				prop.getAnimationHandler().clearAnimations();
				prop.getAnimationHandler().activateAnimation("Enabled idle", 0);
			} else if (!this.activated && world.isRemote && !prop.getAnimationHandler().isAnimationActive("Disabled idle") && !prop.getAnimationHandler().isAnimationActive("Disable") && !prop.getAnimationHandler().isAnimationActive("Enable"))
			{
				prop.getAnimationHandler().clearAnimations();
				prop.getAnimationHandler().activateAnimation("Disabled idle", 0);
			}
		} else if (world.isRemote)
		{
			PacketHandler.sendToServer(new SyncPressedKeysPacket(false));
			prop.getAnimationHandler().clearAnimations();
			prop.getAnimationHandler().activateAnimation("Disabled idle", 0);
			ticks_from_sent = 5;
		}
		if (needSave)
		{
			if (!world.isRemote)
			{
				if (itemStack.hasTagCompound())
				{
					this.writeToNBT(itemStack.stackTagCompound);
				} else
				{
					itemStack.stackTagCompound = new NBTTagCompound();
					this.writeToNBT(itemStack.stackTagCompound);
				}
			} else
			{
				PacketHandler.sendToServer(new SyncPressedKeysPacket(activated));
				GLoger.logInfo("packet+1");
				ticks_from_sent = 5;
			}
			this.needSave = false;
		}
		
		if (world.isRemote)
		{
			boolean disable = isDisabled(player, false);
			if (!disable)
			{
				
				Vec3 vect = player.getLookVec();
				
				float vel = 0.5F;
				
				int ang = 0;
				
				if (KeysPressed.contains(2))
				{
					ang -= 90;
				} else if (KeysPressed.contains(3))
				{
					ang += 90;
				}
				
				float f4;
				float f5;
				double motionX;
				double motionZ;
				
				if (KeysPressed.contains(4))
				{
					player.moveEntity(0, 0.5F, 0);
					usedFuelAm += 0.05D;
				}
				if (KeysPressed.contains(5))
				{
					if (player.posY > 45)
					{
						player.moveEntity(0, -0.5F, 0);
						usedFuelAm += 0.05D;
					}
				}
				
				player.motionX = 0;
				player.motionZ = 0;
				if (KeysPressed.contains(0) || KeysPressed.contains(1))
				{
					f4 = MathHelper.sin((player.rotationYawHead + 45) * (float) Math.PI / 180.0F);
					f5 = MathHelper.cos((player.rotationYawHead + 45) * (float) Math.PI / 180.0F);
					motionX = (double) (0.5D * f5 - 0.5D * f4);
					motionZ = (double) (0.5D * f5 + 0.5D * f4);
					
					motionX *= vel;
					motionZ *= vel;
					
					if (KeysPressed.contains(0))
					{
						player.moveEntity(motionX, 0, motionZ);
						usedFuelAm += 0.05D;
					}
					if (KeysPressed.contains(1))
					{
						player.moveEntity(-motionX, 0, -motionZ);
						usedFuelAm += 0.05D;
					}
				}
				if (KeysPressed.contains(2) || KeysPressed.contains(3))
				{
					f4 = MathHelper.sin((player.rotationYawHead + 45 + ang) * (float) Math.PI / 180.0F);
					f5 = MathHelper.cos((player.rotationYawHead + 45 + ang) * (float) Math.PI / 180.0F);
					motionX = (double) (0.5D * f5 - 0.5D * f4);
					motionZ = (double) (0.5D * f5 + 0.5D * f4);
					
					motionX *= vel;
					motionZ *= vel;
					
					if (KeysPressed.contains(2))
					{
						player.moveEntity(motionX, 0, motionZ);
						usedFuelAm += 0.05D;
					}
					if (KeysPressed.contains(3))
					{
						player.moveEntity(motionX, 0, motionZ);
						usedFuelAm += 0.05D;
					}
				}
				player.motionX = 0;
				player.motionZ = 0;
			}
			
			if (usedFuelAm >= 10)
			{
				//	player.addChatComponentMessage(new ChatComponentText("Used 10 mb of fuel!"));
				usedFuelAm = 0;
				PacketHandler.sendToServer(new JetpackUseFuelPacket());
			}
			
		}
		
		/*	if (KeysPressed.size() > 0)
			{
			KeysPressed.clear();
			markDirty();
			}*/
		
	}
	
	public boolean isDisabled(EntityPlayer player, boolean useage)
	{
		if (player.worldObj.provider instanceof WorldProviderOrbitModif)
		{
			WorldProviderOrbitModif prow = (WorldProviderOrbitModif) player.worldObj.provider;
			if (prow.artificialG > 0.5D)
			{
				return true;
			}
		}
		if (player.onGround)
		{
			return true;
		}
		if (RCSFuel.getFluidAmount() == 0)
		{
			return true;
		}
		if (player.worldObj.isRemote && Minecraft.getMinecraft().currentScreen != null && !useage)
		{
			return true;
		}
		return !activated;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		activated = tag.getBoolean("Enabled");
		if (tag.hasKey("fuelTank"))
		{
			this.RCSFuel.readFromNBT(tag.getCompoundTag("fuelTank"));
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setBoolean("Enabled", activated);
		if (this.RCSFuel.getFluid() != null)
		{
			tag.setTag("fuelTank", this.RCSFuel.writeToNBT(new NBTTagCompound()));
		}
	}
	
	public void markDirty()
	{
		this.needSave = true;
	}
	
	public void setUpdated()
	{
		this.updateValues = true;
	}
	
	public void setActive(boolean state)
	{
		PacketHandler.sendToServer(new SyncPressedKeysPacket(state));
		activated = state;
		ticks_from_sent = 5;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister registry)
	{
		itemIcon = registry.registerIcon(GliderModInfo.ModTestures + ":" + "indev");
	}
}
