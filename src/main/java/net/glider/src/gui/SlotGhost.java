package net.glider.src.gui;

import net.glider.src.items.ItemMod;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class SlotGhost extends Slot
{
	boolean state = false;
	
	public SlotGhost(IInventory inv, int slotIndex, int xPos, int yPost) 
	{
		super(inv, slotIndex, xPos, yPost);
		this.slotNumber = slotIndex;
	}
	
	public SlotGhost setState(boolean state)
	{
		this.state = state;
		this.putStack(new ItemStack(Blocks.leaves));
		return this;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		if (stack != null)
		{
			this.putStack(stack);
		}
		
		return false;
	}

	@Override
	public boolean canTakeStack(EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public int getSlotStackLimit() 
	{
		return 64;
	}
	
	@Override
	public IIcon getBackgroundIconIndex()
	{
		if (state)
		{
			return ItemMod.getBuilderIcons(1);
		}else 
		{
			return ItemMod.getBuilderIcons(0);
		}
	}
}