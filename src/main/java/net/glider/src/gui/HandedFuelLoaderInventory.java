package net.glider.src.gui;

import micdoodle8.mods.galacticraft.api.power.IEnergyStorageGC;
import micdoodle8.mods.galacticraft.core.energy.tile.EnergyStorage;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class HandedFuelLoaderInventory extends ItemStackInventory implements IFluidHandler,IEnergyStorageGC {

	private final int tankCapacity = 6000;
	public FluidTank fuelTank = new FluidTank(this.tankCapacity);
	public static final float STANDARD_CAPACITY = 16000F;
    public EnergyStorage storage = new EnergyStorage(STANDARD_CAPACITY, 10);
	
	public HandedFuelLoaderInventory(ItemStack stack)
	{
		super(stack, 2);
		storage.setMaxReceive(50);
	}
	
	public void readFromNBT(NBTTagCompound nbt)
    {
		super.readFromNBT(nbt); 
        
        if (nbt.hasKey("fuelTank"))
        {
            this.fuelTank.readFromNBT(nbt.getCompoundTag("fuelTank"));
        }
        storage.readFromNBT(nbt);
        
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
    	super.writeToNBT(nbt);
    	
        if (this.fuelTank.getFluid() != null)
        {
            nbt.setTag("fuelTank", this.fuelTank.writeToNBT(new NBTTagCompound()));
        }
        storage.writeToNBT(nbt); 
    }

    /**
     * you must call it before using inventory 
     */
    @Override
    public void openInventory()
    {
    	if (ContainerItem != null && ContainerItem.hasTagCompound())
    	{
    		readFromNBT(ContainerItem.stackTagCompound);
    	}
    }

    /**
     * you must call this to save data. also you need to grab stack on end and update it in original place otherwise everything will disappear
     */
    @Override
    public void closeInventory()
    {
    	if (ContainerItem != null)
    	{
    		if (ContainerItem.hasTagCompound())
    		{
    			writeToNBT(ContainerItem.stackTagCompound);
				//	player.inventory.setInventorySlotContents(player.inventory.currentItem,stack);
    		}else
    		{
    			ContainerItem.stackTagCompound = new NBTTagCompound();
    			writeToNBT(ContainerItem.stackTagCompound);
    		//	player.inventory.setInventorySlotContents(player.inventory.currentItem,stack);
    		}
    	}
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        int used = 0;

            if (FluidUtil.testFuel(FluidRegistry.getFluidName(resource)))
            {
                used = this.fuelTank.fill(resource, doFill);
            }

        return used;
    }

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[]{new FluidTankInfo(fuelTank)};
	}

	@Override
	public String getInventoryName()
	{
		return "Fuel Loader";
	}

	@Override
	public float receiveEnergyGC(float amount, boolean simulate)
	{
		return this.storage.receiveEnergyGC(amount, simulate);
	}

	@Override
	public float extractEnergyGC(float amount, boolean simulate)
	{
		return this.storage.extractEnergyGC(amount, simulate);
	}

	@Override
	public float getEnergyStoredGC()
	{
		 return this.storage.getEnergyStoredGC();
	}

	@Override
	public float getCapacityGC()
	{
		return this.storage.getCapacityGC();
	}
	
	 public String getGUIstatus()
	    {
	        if (this.getEnergyStoredGC() == 0)
	        {
	            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.missingpower.name");
	        }

	        if (this.getEnergyStoredGC() < this.storage.getMaxExtract())
	        {
	            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.missingpower.name");
	        }

	       	return EnumColor.DARK_GREEN + GCCoreUtil.translate("gui.status.active.name");
	    }
	
}