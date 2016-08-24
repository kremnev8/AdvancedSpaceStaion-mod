package net.glider.src.gui;

import ic2.api.item.IElectricItem;
import ic2.api.item.ISpecialElectricItem;

import java.lang.reflect.Method;

import micdoodle8.mods.galacticraft.api.item.ElectricItemHelper;
import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.items.ItemCanisterGeneric;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import net.glider.src.items.ItemMod;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.SyncFuelLoaderPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class ContainerHandedFuelLoader extends Container
{
    public HandedFuelLoaderInventory inventory;
    private EntityPlayer player;
    private int tickfromOpen = 0;

    public ContainerHandedFuelLoader(InventoryPlayer par1InventoryPlayer, HandedFuelLoaderInventory inv)
    {
        this.inventory = inv;
        this.player = par1InventoryPlayer.player;
        tickfromOpen =player.ticksExisted;
        inventory.openInventory();
        armor = null;
        this.addSlotToContainer(new SlotSpecific(inventory, 0, 51, 55, IItemElectric.class));
        this.addSlotToContainer(new Slot(inventory, 1, 7, 12));

        int var6;
        int var7;

        // Player inv:

        for (var6 = 0; var6 < 3; ++var6)
        {
            for (var7 = 0; var7 < 9; ++var7)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 31 + 58 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 9; ++var6)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 31 + 116));
        }
    }
    @Override
    public void onContainerClosed(EntityPlayer player)
    {
    	super.onContainerClosed(player);
    	inventory.closeInventory();
    	player.inventory.setInventorySlotContents(player.inventory.currentItem,inventory.getContainerStack());
    }
    
    private boolean loadedFuelLastTick = false;
	private boolean hasEnoughEnergyToRun;
	private ItemStack armor;
    
    @Override
    public void detectAndSendChanges()
    {
    	super.detectAndSendChanges();
    	
   // /*	ItemStack test = IC2Items.getItem("FluidCell");
    /*	
    	 if (!player.worldObj.isRemote)
         {
    		 
			boolean pow = inventory.getEnergyStoredGC() > 0;

			int fuelLevel1 = this.inventory.fuelTank.getFluid() == null ? 0 : this.inventory.fuelTank.getFluid().amount;

			int fuelLevel2 = (int) (fuelLevel1 * 12 / this.inventory.fuelTank.getCapacity());
			if (fuelLevel1 > 0) fuelLevel2++;

			String texname = "idle_" + (pow ? "pow_" : "unpow_") + fuelLevel2;
			if (ItemFuelLoader.sybtypes.containsKey(texname))
			{
				int meta = ItemFuelLoader.sybtypes.get(texname);
				ItemStack is = inventory.getContainerStack();
				if (is.getItemDamage() != meta)
				{
					is.setItemDamage(meta);
				}
			}
    		 
             this.loadedFuelLastTick = false;

             if (this.inventory.getStackInSlot(1) != null)
             {
                 if (this.inventory.getStackInSlot(1).getItem() instanceof ItemCanisterGeneric)
                 {
 	                if (this.inventory.getStackInSlot(1).getItem() == GCItems.fuelCanister)
 	                {
 	                	int originalDamage = this.inventory.getStackInSlot(1).getItemDamage();
 	                	int used = this.inventory.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, ItemCanisterGeneric.EMPTY - originalDamage), true);
 	                	if (originalDamage + used == ItemCanisterGeneric.EMPTY)
 	                	this.inventory.setInventorySlotContents(1, new ItemStack(GCItems.oilCanister, 1, ItemCanisterGeneric.EMPTY));
 	                	else
 	                		this.inventory.setInventorySlotContents(1, new ItemStack(GCItems.fuelCanister, 1, originalDamage + used));
 	                }
             	}
                 else
                 {
                 	final FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(this.inventory.getStackInSlot(1));
                 	ItemStack is = this.inventory.getStackInSlot(1);

                 	if (liquid != null)
                 	{
                 		boolean isFuel = FluidUtil.testFuel(FluidRegistry.getFluidName(liquid)); 

                 		if (isFuel)
                 		{
                 			if (this.inventory.fuelTank.getFluid() == null || this.inventory.fuelTank.getFluid().amount + liquid.amount <= this.inventory.fuelTank.getCapacity())
                 			{
                 				this.inventory.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, liquid.amount), true);

                 				if (FluidContainerRegistry.isBucket(is) && FluidContainerRegistry.isFilledContainer(is))
                 				{
                 					final int amount = is.stackSize;
                 					if (amount > 1) this.inventory.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, (amount - 1) * FluidContainerRegistry.BUCKET_VOLUME), true);
                 					is = new ItemStack(Items.bucket, amount);
                 					this.inventory.setInventorySlotContents(1, is);
                 				}
                 				else
                 				{
                 					is.stackSize--;

                 					if (is.stackSize == 0)
                 					{
                 						is = null;
                 					}
                 					this.inventory.setInventorySlotContents(1, is);
                 				}
                 			}
                 		}
                 	}else if (EnergyConfigHandler.isIndustrialCraft2Loaded())
                 	{
                 		is = this.inventory.getStackInSlot(1);
                 		if (is.stackSize == 1 && is.hasTagCompound())
                 		{//{Fluid:{FluidName:"fuel",Amount:1000}}
                 			FluidTank cell = new FluidTank(1000);
                 			cell.readFromNBT(is.stackTagCompound.getCompoundTag("Fluid"));
                 			
                 			
                 			if (cell.getFluid() != null)
                         	{
                         		boolean isFuel = FluidUtil.testFuel(FluidRegistry.getFluidName(cell.getFluid())); 

                         		if (isFuel)
                         		{
                         			if (this.inventory.fuelTank.getFluid() == null || this.inventory.fuelTank.getFluid().amount + cell.getFluid().amount <= this.inventory.fuelTank.getCapacity())
                         			{
                         				this.inventory.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, cell.getFluid().amount), true);
                         				cell.drain(cell.getFluid().amount, true);
                         				is.stackTagCompound.setTag("Fluid", cell.writeToNBT(new NBTTagCompound()));
                         				this.inventory.setInventorySlotContents(1, is);
                         				
                         			}else if (this.inventory.fuelTank.getFluid() == null || this.inventory.fuelTank.getFluid().amount + cell.getFluid().amount >= this.inventory.fuelTank.getCapacity())
                         			{
                         				
                         				int amount = this.inventory.fuelTank.getCapacity() - this.inventory.fuelTank.getFluid().amount;
                         				this.inventory.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, amount), true);
                         				cell.drain(amount, true);
                         				is.stackTagCompound.setTag("Fluid", cell.writeToNBT(new NBTTagCompound()));
                         				this.inventory.setInventorySlotContents(1, is);
                         			}
                         		}
                         	}
                 		}else if (is.hasTagCompound())
                 		{
                 			FluidTank cell = new FluidTank(1000*is.stackSize);
                 			cell.readFromNBT(is.stackTagCompound.getCompoundTag("Fluid"));
                 			
                 			if (cell.getFluid() != null)
                         	{
                 				cell.getFluid().amount *= is.stackSize;
                         		boolean isFuel = FluidUtil.testFuel(FluidRegistry.getFluidName(cell.getFluid())); 

                         		if (isFuel)
                         		{
                         			if (this.inventory.fuelTank.getFluid() == null || this.inventory.fuelTank.getFluid().amount + cell.getFluid().amount <= this.inventory.fuelTank.getCapacity())
                         			{
                         				this.inventory.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, cell.getFluid().amount), true);
                         				cell.drain(cell.getFluid().amount, true);
                         				is.stackTagCompound.setTag("Fluid", cell.writeToNBT(new NBTTagCompound()));
                         				this.inventory.setInventorySlotContents(1, is);
                         				
                         			}
                         		}
                         	}
                 		}
                 	}
                 }
             }
             
             if (this.inventory.getEnergyStoredGC() < this.inventory.getCapacityGC() && this.inventory.getStackInSlot(0) != null)
             {
            	 ItemStack is = this.inventory.getStackInSlot(0);
                 this.discharge(is);
                 this.inventory.setInventorySlotContents(0, is);
             }

             if (this.inventory.getEnergyStoredGC() > this.inventory.storage.getMaxExtract())
             {
                 this.hasEnoughEnergyToRun = true;
                 if (this.inventory.fuelTank.getFluid() != null && this.inventory.fuelTank.getFluid().amount > 0 && loadedFuelLastTick)
                 {
                     this.inventory.storage.extractEnergyGC(this.inventory.storage.getMaxExtract(), false);
                 }
                 else
                 	this.slowDischarge();
             }
             else
             {
                 this.hasEnoughEnergyToRun = false;
             	this.slowDischarge();
             }

             if ((this.tickfromOpen-player.ticksExisted) % 20 == 0 && armor == null)
             {
            	 if (player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack && (player.getCurrentArmor(2).hasTagCompound() && !player.getCurrentArmor(2).stackTagCompound.getBoolean("Enabled")))
            	 {
            		 armor = player.getCurrentArmor(2);
            	 }

             }

			if (this.inventory.fuelTank != null && this.inventory.fuelTank.getFluid() != null && this.inventory.fuelTank.getFluid().amount > 0)
			{
				final FluidStack liquid = new FluidStack(GalacticraftCore.fluidFuel, 2);

				if (this.armor != null && this.hasEnoughEnergyToRun)
				{
					FluidTank jetpack = new FluidTank(1750  * ConfigManagerCore.rocketFuelFactor);
					if (armor.hasTagCompound())
					{
						if (armor.stackTagCompound.hasKey("fuelTank"))
						{
							jetpack.readFromNBT(armor.stackTagCompound.getCompoundTag("fuelTank"));
						}
					}
					int filled = jetpack.fill(liquid, true);
					this.loadedFuelLastTick = filled > 0;
				    this.inventory.fuelTank.drain(filled, true);
				    
				    if (armor.hasTagCompound())
					{
				    	if (jetpack.getFluid() != null)
						{
							armor.stackTagCompound.setTag("fuelTank", jetpack.writeToNBT(new NBTTagCompound()));
						}
					}
				}
			}
             
             if ((this.tickfromOpen-player.ticksExisted) % 20 == 0)
             {
            	 inventory.closeInventory();
				PacketHandler.sendTo(new SyncFuelLoaderPacket(inventory.getContainerStack()), (EntityPlayerMP) player);
             }
         }
        			 
    }*/
    }
    
    public void slowDischarge()
    {
       	this.inventory.storage.extractEnergyGC(0.5F, false);
    }
    
    public void discharge(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            Item item = itemStack.getItem();
            float energyToDischarge = Math.min(this.inventory.storage.getCapacityGC() - this.inventory.storage.getEnergyStoredGC(), this.inventory.storage.getMaxReceive());

            if (item instanceof IItemElectric)
            {
                this.inventory.storage.receiveEnergyGC(ElectricItemHelper.dischargeItem(itemStack, energyToDischarge));
            }
            else if (EnergyConfigHandler.isIndustrialCraft2Loaded())
            {
                if (item instanceof IElectricItem)
                {
                    IElectricItem electricItem = (IElectricItem) item;
                    if (electricItem.canProvideEnergy(itemStack))
                    {
                        double result = 0;
                        int energyDischargeIC2 = (int) (energyToDischarge / EnergyConfigHandler.IC2_RATIO);
                        try
                        {
                        	result = (int)ic2.api.item.ElectricItem.manager.discharge(itemStack, energyDischargeIC2, 4, false, false, false);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        float energyDischarged = (float) result * EnergyConfigHandler.IC2_RATIO;
                        this.inventory.storage.receiveEnergyGC(energyDischarged);
                    }
                }
                else if (item instanceof ISpecialElectricItem)
                {
                    ISpecialElectricItem electricItem = (ISpecialElectricItem) item;
                    if (electricItem.canProvideEnergy(itemStack))
                    {
                        double result = 0;
                        int energyDischargeIC2 = (int) (energyToDischarge / EnergyConfigHandler.IC2_RATIO);
                        //Do this by reflection:
                        //result = electricItem.getManager(itemStack).discharge(itemStack, energyDischargeIC2, 4, false, false, false)
                        try
                        {
                            Class<?> clazz = Class.forName("ic2.api.item.IElectricItemManager");
                            Method dischargeMethod = clazz.getMethod("discharge", ItemStack.class, int.class, int.class, boolean.class, boolean.class);
                            result = (Integer) dischargeMethod.invoke(electricItem.getManager(itemStack), itemStack, energyDischargeIC2, 4, false, false);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        float energyDischarged = (float) result * EnergyConfigHandler.IC2_RATIO;
                        this.inventory.storage.receiveEnergyGC(energyDischarged);
                    }
                }
            }
            //			else if (GCCoreCompatibilityManager.isTELoaded() && itemStack.getItem() instanceof IEnergyContainerItem)
            //			{
            //				float given = ((IEnergyContainerItem) itemStack.getItem()).extractEnergy(itemStack, (int) Math.floor(this.getRequest(ForgeDirection.UNKNOWN) * EnergyConfigHandler.TO_TE_RATIO), false);
            //				this.receiveElectricity(given * EnergyConfigHandler.TE_RATIO, true);
            //			}
        }
    }
    
    
    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.inventory.isUseableByPlayer(var1);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        final Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            final ItemStack var5 = slot.getStack();
            var3 = var5.copy();

            if (par2 < 2)
            {
                if (!this.mergeItemStack(var5, 2, 38, true))
                {
                    return null;
                }
            }
            else
            {
                if (var5.getItem() instanceof IItemElectric)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else
                {
                    if (FluidUtil.isFuelContainerAny(var5))
                    {
                        if (!this.mergeItemStack(var5, 1, 2, false))
                        {
                            return null;
                        }
                    }
                    else if (par2 < 29)
                    {
                        if (!this.mergeItemStack(var5, 29, 38, false))
                        {
                            return null;
                        }
                    }
                    else if (!this.mergeItemStack(var5, 2, 29, false))
                    {
                        return null;
                    }
                }
            }

            if (var5.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
}