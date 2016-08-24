
package net.glider.src.gui;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.InvScalePacket;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.tiles.TileEntityDockingPort;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerArmorStand extends Container {

	public TileEntityArmorStand Standnventory;
	private InventoryPlayer playerInv;

	public ContainerArmorStand(EntityPlayer player, IInventory ArmorStand)
	{//TODO fix misterous error when holding something and opening inv
		this.Standnventory = (TileEntityArmorStand) ArmorStand;
		this.playerInv = player.inventory;
		this.inventorySlots.clear();
		this.inventoryItemStacks.clear();
		
		for (int i = 0; i < 4; i++)
		{
			this.addSlotToContainer(new SlotArmor(ArmorStand, i, 98, 7 + i * 18, i));
		}
		
		for (int j = 0; j < 3; j++)
		{
			for (int k = 0; k < 9; k++)
			{
				this.addSlotToContainer(new Slot(playerInv, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
			}
		}
		
		for (int j = 0; j < 9; j++)
		{
			this.addSlotToContainer(new Slot(playerInv, j, 8 + j * 18, 142));
		}
		
		int x = 8;
		int y = 61;
		this.addSlotToContainer(new SlotArmor(playerInv, 39, x, y,0));
		this.addSlotToContainer(new SlotArmor(playerInv, 38, x+18, y,1));
		this.addSlotToContainer(new SlotArmor(playerInv, 37, x+(18*2), y,2));
		this.addSlotToContainer(new SlotArmor(playerInv, 36, x+(18*3), y,3));
		Standnventory.openInventory();
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);
		final int b = this.inventorySlots.size();

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 < this.Standnventory.getSizeInventory())
			{
				if (!this.mergeItemStack(itemstack1, b - 36, b, true))
				{
					return null;
				}
			} else
			{
				if (itemstack1.getItem() instanceof ItemArmor)
				{
				int type = ((ItemArmor)itemstack1.getItem()).armorType;
				if (!this.mergeItemStack(itemstack1, type, type+1, false))
				{
					return null;
				}
				}else
				{
					return null;
				}

			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			} else
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		this.Standnventory.closeInventory();
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}
}