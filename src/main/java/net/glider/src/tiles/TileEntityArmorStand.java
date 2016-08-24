
package net.glider.src.tiles;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IElectricItemManager;

import java.util.EnumSet;

import li.cil.oc.common.item.traits.Chargeable;
import li.cil.oc.integration.util.ItemCharge;
import micdoodle8.mods.galacticraft.api.entity.IFuelable;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlockWithInventory;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.items.ItemSpaceJetpack;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.ArmorStandItemSyncPacket;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityArmorStand extends TileBaseElectricBlockWithInventory implements IMultiBlock, IFuelable {
	
	@NetworkedField(targetSide = Side.CLIENT)
	public BlockVec3 mainBlockPosition;
	
	public ItemStack[] items = new ItemStack[4];
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2);
		
	}
	
	public TileEntityArmorStand()
	{
		this.storage.setCapacity(STANDARD_CAPACITY * 8);
	}
	
	@Override
	public boolean onActivated(EntityPlayer player)
	{
		
		ItemStack[] stand = this.items.clone();
		ItemStack[] playerS = player.inventory.armorInventory.clone();
		
		if (stand != null)
		{
			player.inventory.setInventorySlotContents(39, stand[0]);
			player.inventory.setInventorySlotContents(38, stand[1]);
			player.inventory.setInventorySlotContents(37, stand[2]);
			player.inventory.setInventorySlotContents(36, stand[3]);
		}
		if (playerS != null)
		{
			this.setInventorySlotContents(3, playerS[0]);
			this.setInventorySlotContents(2, playerS[1]);
			this.setInventorySlotContents(1, playerS[2]);
			this.setInventorySlotContents(0, playerS[3]);
		}
		
		return true;
	}
	
	@Override
	public boolean canUpdate()
	{
		return true;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (mainBlockPosition == null)
		{
			mainBlockPosition = new BlockVec3(this);
		}
		
		if ((ticks % 300 == 0 || ticks <= 20) && !worldObj.isRemote)
		{
			PacketHandler.sendToDimension(new ArmorStandItemSyncPacket(items, xCoord, yCoord, zCoord), worldObj.provider.dimensionId);
		}
		
		for (int i = 0; i < items.length; i++)
		{
			ItemStack item = items[i];
			if (item != null)
			{
				if (Loader.isModLoaded("OpenComputers"))
				{
					if (item.getItem() instanceof Chargeable)
					{
						if (ItemCharge.canCharge(item))
						{
							Chargeable charg = (Chargeable) item.getItem();
							ItemCharge.charge(item, 10);
							double charged = charg.receiveEnergy(item, 10, false);
							this.storage.extractEnergyGC((float) (EnergyConfigHandler.RF_RATIO * charged), false);
						}
					}
				}
				if (Loader.isModLoaded("IC2"))
				{
					if (item.getItem() instanceof IElectricItem)
					{
						IElectricItemManager manager = ElectricItem.manager;
						double charge = manager.charge(item, storage.getEnergyStoredGC() * EnergyConfigHandler.TO_IC2_RATIO, 4, false, false);
						this.storage.extractEnergyGC((float) (EnergyConfigHandler.IC2_RATIO * charge), false);
					}
				}
				if (Loader.isModLoaded("CoFHCore"))
				{
					if (item.getItem() instanceof IEnergyContainerItem)
					{
						IEnergyContainerItem cont = (IEnergyContainerItem) item.getItem();
						int used = cont.receiveEnergy(item, 500, false);
						this.storage.extractEnergyGC((float) (EnergyConfigHandler.RF_RATIO * used), false);
					}
				}
			}
		}
	}
	
	@Override
	public void onCreate(BlockVec3 placedPosition)
	{
		this.mainBlockPosition = placedPosition;
		this.markDirty();
		int buildHeight = this.worldObj.getHeight() - 1;
		
		for (int y = 0; y < 2; y++)
		{
			if (placedPosition.y + y > buildHeight)
				return;
			final BlockVec3 vecToAdd = new BlockVec3(placedPosition.x, placedPosition.y + y, placedPosition.z);
			
			if (!vecToAdd.equals(placedPosition))
			{
				BlockContainerMod.BlockFake.makeFakeBlock(this.worldObj, vecToAdd, placedPosition, 0);
			}
		}
	}
	
	@Override
	public void onDestroy(TileEntity callingBlock)
	{
		final BlockVec3 thisBlock = new BlockVec3(this);
		int fakeBlockCount = 0;
		
		for (int y = 0; y < 2; y++)
		{
			if (y == 0)
			{
				continue;
			}
			
			if (this.worldObj.getBlock(thisBlock.x, thisBlock.y + y, thisBlock.z) == BlockContainerMod.BlockFake)
			{
				fakeBlockCount++;
			}
		}
		
		if (fakeBlockCount == 0)
		{
			if (this.worldObj.isRemote && this.worldObj.rand.nextDouble() < 0.1D)
			{
				FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.x, thisBlock.y, thisBlock.z, BlockContainerMod.BlockArmorStand, Block.getIdFromBlock(BlockContainerMod.BlockArmorStand) >> 12 & 255);
			}
			this.worldObj.func_147480_a(thisBlock.x, thisBlock.y, thisBlock.z, true);
			return;
		}
		
		for (int y = 0; y < 2; y++)
		{
			if (this.worldObj.isRemote && this.worldObj.rand.nextDouble() < 0.1D)
			{
				FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(thisBlock.x, thisBlock.y + y, thisBlock.z, BlockContainerMod.BlockArmorStand, Block.getIdFromBlock(BlockContainerMod.BlockArmorStand) >> 12 & 255);
			}
			this.worldObj.func_147480_a(thisBlock.x, thisBlock.y + y, thisBlock.z, true);
		}
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		this.items = this.readStandardItemsFromNBT(par1NBTTagCompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		this.writeStandardItemsToNBT(par1NBTTagCompound);
	}
	
	@Override
	public String getInventoryName()
	{
		return "Armor Stand";
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}
	
	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return true;
	}
	
	@Override
	protected ItemStack[] getContainingItems()
	{
		return items;
	}
	
	@Override
	public boolean shouldUseEnergy()
	{
		return false;
	}
	
	@Override
	public boolean canConnect(ForgeDirection direction, NetworkType type)
	{
		return true;
	}
	
	public EnumSet<ForgeDirection> getElectricalInputDirections()
	{
		return EnumSet.allOf(ForgeDirection.class);
	}
	
	@Override
	public void slowDischarge()
	{
		// this.storage.extractEnergyGC(0.5F, false);
	}
	
	public ItemStack getArmor(int i)
	{
		if (i < items.length)
		{
			return items[i];
		}
		return null;
	}
	
	@Override
	public int addFuel(FluidStack fluid, boolean doFill)
	{
		if (items.length >= 2)
		{
			ItemStack armor = items[1];
			if (armor != null)
			{
				Item i = armor.getItem();
				if (i instanceof ItemSpaceJetpack)
				{
					FluidTank tank = new FluidTank(1750 * ConfigManagerCore.rocketFuelFactor);
					if (armor.hasTagCompound())
					{
						if (armor.stackTagCompound.hasKey("fuelTank"))
						{
							tank.readFromNBT(armor.stackTagCompound.getCompoundTag("fuelTank"));
						}
					}
					int amount = tank.fill(fluid, doFill);
					if (!doFill)
					{
						return amount;
					} else
					{
						if (armor.hasTagCompound())
						{
							if (tank.getFluid() != null)
							{
								armor.stackTagCompound.setTag("fuelTank", tank.writeToNBT(new NBTTagCompound()));
							}
						} else
						{
							armor.stackTagCompound = new NBTTagCompound();
							if (tank.getFluid() != null)
							{
								armor.stackTagCompound.setTag("fuelTank", tank.writeToNBT(new NBTTagCompound()));
							}
						}
						return amount;
					}
				}
			}
		}
		return 0;
	}
	
	@Override
	public FluidStack removeFuel(int amount)
	{
		return null;
	}
	
	@Override
	public ItemStack getBatteryInSlot()
	{
		return null;
	}
}
