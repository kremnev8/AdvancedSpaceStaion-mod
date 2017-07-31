package net.glider.src.gui;

import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import net.glider.src.tiles.TileEntityGravitySource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerArtificialGSource extends Container {
	public TileBaseElectricBlock tileEntity;
	
	public ContainerArtificialGSource(InventoryPlayer par1InventoryPlayer, TileEntityGravitySource fuelLoader)
	{
		this.tileEntity = fuelLoader;
		
		int var6;
		int var7;
		
		// Player inv:
		
		for (var6 = 0; var6 < 3; ++var6)
		{
			for (var7 = 0; var7 < 9; ++var7)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 67 + var6 * 18));
			}
		}
		
		for (var6 = 0; var6 < 9; ++var6)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 125));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return this.tileEntity.isUseableByPlayer(var1);
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
			
			if (par2 < 27)
			{
				if (!this.mergeItemStack(var5, 27, 36, false))
				{
					return null;
				}
			} else if (!this.mergeItemStack(var5, 0, 27, false))
			{
				return null;
			}
			
			if (var5.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			} else
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