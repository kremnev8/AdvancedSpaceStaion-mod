
package net.glider.src.tiles;

import java.util.EnumSet;

import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import micdoodle8.mods.galacticraft.core.util.RedstoneUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;

public class TileEntityGravitySource extends TileBaseElectricBlock {
	@NetworkedField(targetSide = Side.CLIENT)
	public double gravityAddition = 0F;
	@NetworkedField(targetSide = Side.CLIENT)
	public double SettedGA = 1.0F;
	@NetworkedField(targetSide = Side.SERVER)
	public double ClientVal = 0F;
	
	public TileEntityGravitySource()
	{
		storage.setCapacity(4000F);
		storage.setMaxExtract(500F);
		ClientVal = SettedGA;
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
	
	/**
	 * actually main
	 */
	@Override
	public void slowDischarge()
	{
		if (ClientVal != SettedGA)
		{
			SettedGA = ClientVal;
		}
		if (this.hasEnoughEnergyToRun && !RedstoneUtil.isBlockReceivingRedstone(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
		{
			gravityAddition = SettedGA;
		} else
		{
			gravityAddition = 0;
		}
		if (gravityAddition > 0)
		{
			float extract = 0F;
			if (gravityAddition <= 1)
			{
				extract = (float) (125 * gravityAddition);
			} else
			{
				extract = (float) (125 + 500 * (gravityAddition - 1));
			}
			this.storage.extractEnergyGC(extract, false);
		}
	}
	
	@Override
	public ItemStack getBatteryInSlot()
	{
		return null;
	}
	
	@Override
	public ForgeDirection getElectricInputDirection()
	{
		return ForgeDirection.getOrientation(2);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setDouble("GValue", SettedGA);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		SettedGA = nbt.getDouble("GValue");
	}
	
}
