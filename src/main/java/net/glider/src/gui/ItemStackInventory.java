
package net.glider.src.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemStackInventory implements IInventory {
	
	private ItemStack[] containingItems = new ItemStack[2];
	
	public ItemStack ContainerItem;
	
	public ItemStackInventory(ItemStack stack, int capacity)
	{
		this.ContainerItem = stack;
		containingItems = new ItemStack[capacity];
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		final NBTTagList var2 = nbt.getTagList("Items", 10);
		int length = this.getSizeInventory();
		ItemStack[] result = new ItemStack[length];
		
		for (int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			final NBTTagCompound var4 = var2.getCompoundTagAt(var3);
			final int var5 = var4.getByte("Slot") & 255;
			
			if (var5 < length)
			{
				result[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
		containingItems = result;
		
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		final NBTTagList list = new NBTTagList();
		int length = this.getSizeInventory();
		
		for (int var3 = 0; var3 < length; ++var3)
		{
			if (containingItems[var3] != null)
			{
				final NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				containingItems[var3].writeToNBT(var4);
				list.appendTag(var4);
			}
		}
		
		nbt.setTag("Items", list);
		
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.containingItems.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int par1)
	{
		if (par1 < this.getSizeInventory())
		{
			return this.containingItems[par1];
		}
		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (containingItems[par1] != null)
		{
			ItemStack var3;
			
			if (containingItems[par1].stackSize <= par2)
			{
				var3 = containingItems[par1];
				containingItems[par1] = null;
				this.markDirty();
				return var3;
			} else
			{
				var3 = containingItems[par1].splitStack(par2);
				
				if (containingItems[par1].stackSize == 0)
				{
					containingItems[par1] = null;
				}
				
				this.markDirty();
				return var3;
			}
		}
		return null;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (containingItems[par1] != null)
		{
			final ItemStack var2 = containingItems[par1];
			containingItems[par1] = null;
			this.markDirty();
			return var2;
		}
		return null;
	}
	
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		containingItems[par1] = par2ItemStack;
		
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
		
		this.markDirty();
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return true;
	}
	
	public ItemStack getContainerStack()
	{
		return ContainerItem;
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
	 * you must call this to save data. also you need to grab stack on end and
	 * update it in original place otherwise everything will disappear
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
			} else
			{
				ContainerItem.stackTagCompound = new NBTTagCompound();
				writeToNBT(ContainerItem.stackTagCompound);
				//	player.inventory.setInventorySlotContents(player.inventory.currentItem,stack);
			}
		}
	}
	
	@Override
	public String getInventoryName()
	{
		return "";
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}
	
	@Override
	public void markDirty()
	{
	}
	
	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return true;
	}
}