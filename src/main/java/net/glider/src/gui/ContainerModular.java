
package net.glider.src.gui;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ContainerModular extends Container {
	
	public List<Container> subContainers = Lists.newArrayList();
	
	// lookup used to redirect slot specific things to the appropriate container
	protected Map<Integer, Container> slotContainerMap = Maps.newHashMap();
	protected Map<Container, Pair<Integer, Integer>> subContainerSlotRanges = Maps.newHashMap();
	protected int subContainerSlotStart = -1;
	protected Set<Container> shiftClickContainers = Sets.newHashSet();
	
	protected int playerInventoryStart = -1;
	
	public ContainerModular()
	{
		super();
	}
	
	/**
	 * @param subcontainer
	 *            The container to add
	 * @param preferForShiftClick
	 *            If true shift clicking on slots of the main-container will try to move to this module before the player inventory
	 */
	public void addSubContainer(Container subcontainer, boolean preferForShiftClick)
	{
		if (subContainers.isEmpty())
		{
			subContainerSlotStart = inventorySlots.size();
		}
		subContainers.add(subcontainer);
		
		if (preferForShiftClick)
		{
			shiftClickContainers.add(subcontainer);
		}
		
		int begin = inventorySlots.size();
		for (Object slot : subcontainer.inventorySlots)
		{
			SlotWrapper wrapper = new SlotWrapper((Slot) slot);
			addSlotToContainer(wrapper);
			slotContainerMap.put(wrapper.slotNumber, subcontainer);
		}
		int end = inventorySlots.size();
		subContainerSlotRanges.put(subcontainer, Pair.of(begin, end));
	}
	
	public <TC extends Container> TC getSubContainer(Class<TC> clazz)
	{
		return getSubContainer(clazz, 0);
	}
	
	public <TC extends Container> TC getSubContainer(Class<TC> clazz, int index)
	{
		for (Container sub : subContainers)
		{
			if (clazz.isAssignableFrom(sub.getClass()))
			{
				index--;
			}
			if (index < 0)
			{
				return (TC) sub;
			}
		}
		
		return null;
	}
	
	public Container getSlotContainer(int slotNumber)
	{
		if (slotContainerMap.containsKey(slotNumber))
		{
			return slotContainerMap.get(slotNumber);
		}
		
		return this;
	}
	
	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
	{
		// check if subcontainers are valid
		for (Container sub : subContainers)
		{
			if (!sub.canInteractWith(playerIn))
			{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		for (Container sub : subContainers)
		{
			sub.onContainerClosed(playerIn);
		}
		
		super.onContainerClosed(playerIn);
	}
	
	@Override
	public ItemStack slotClick(int id, int dragtype, int type, EntityPlayer player)
	{
		if (id == -999 && (type == 0 || type == 1))
		{
			for (Container container : subContainers)
			{
				container.slotClick(id, type, type, player);
			}
		}
		
		return super.slotClick(id, type, type, player);
	}
	
	// More sophisticated version of the one in BaseContainer
	// Takes submodules into account when shiftclicking!
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		Slot slot = (Slot) this.inventorySlots.get(index);
		
		if (slot == null || !slot.getHasStack())
		{
			return null;
		}
		
		ItemStack ret = slot.getStack().copy();
		ItemStack itemstack = slot.getStack().copy();
		
		Container container = getSlotContainer(index);
		boolean nothingDone = true;
		
		// Is the slot from a module?
		if (container != this)
		{
			// Try moving module -> tile inventory
			nothingDone &= moveToTileInventory(itemstack);
			
			// Try moving module -> player inventory
			nothingDone &= moveToPlayerInventory(itemstack);
		}
		// Is the slot from the tile?
		else if (index < subContainerSlotStart || (index < playerInventoryStart && subContainerSlotStart < 0))
		{
			// Try moving tile -> preferred modules
			nothingDone &= refillAnyContainer(itemstack, subContainers);
			
			// Try moving module -> player inventory
			nothingDone &= moveToPlayerInventory(itemstack);
			
			// Try moving module -> all submodules
			nothingDone &= moveToAnyContainer(itemstack, subContainers);
		}
		// Slot is from the player inventory (if present)
		else if (index >= playerInventoryStart && playerInventoryStart >= 0)
		{
			// Try moving player -> tile inventory
			nothingDone &= moveToTileInventory(itemstack);
			
			// try moving player -> modules
			nothingDone &= moveToAnyContainer(itemstack, subContainers);
		}
		// you violated some assumption or something. Shame on you.
		else
		{
			return null;
		}
		
		if (nothingDone)
		{
			return null;
		}
		
		return notifySlotAfterTransfer(playerIn, itemstack, ret, slot);
	}
	
	protected ItemStack notifySlotAfterTransfer(EntityPlayer player, ItemStack stack, ItemStack original, Slot slot)
	{
		// notify slot
		slot.onSlotChange(stack, original);
		
		if (stack.stackSize == original.stackSize)
		{
			return null;
		}
		
		// update slot we pulled from
		slot.putStack(stack);
		slot.onPickupFromSlot(player, stack);
		
		if (slot.getHasStack() && slot.getStack().stackSize == 0)
		{
			slot.putStack(null);
		}
		
		return original;
	}
	
	protected boolean moveToTileInventory(ItemStack itemstack)
	{
		if (itemstack == null || itemstack.stackSize == 0)
		{
			return false;
		}
		
		int end = subContainerSlotStart;
		if (end < 0)
		{
			end = playerInventoryStart;
		}
		return !this.mergeItemStack(itemstack, 0, end, false);
	}
	
	protected boolean moveToPlayerInventory(ItemStack itemstack)
	{
		if (itemstack == null || itemstack.stackSize == 0)
		{
			return false;
		}
		
		return playerInventoryStart > 0 && !this.mergeItemStack(itemstack, playerInventoryStart, this.inventorySlots.size(), true);
	}
	
	protected boolean moveToAnyContainer(ItemStack itemstack, Collection<Container> containers)
	{
		if (itemstack == null || itemstack.stackSize == 0)
		{
			return false;
		}
		
		for (Container submodule : containers)
		{
			if (moveToContainer(itemstack, submodule))
			{
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean moveToContainer(ItemStack itemstack, Container container)
	{
		Pair<Integer, Integer> range = subContainerSlotRanges.get(container);
		return !this.mergeItemStack(itemstack, range.getLeft(), range.getRight(), false);
	}
	
	protected boolean refillAnyContainer(ItemStack itemstack, Collection<Container> containers)
	{
		if (itemstack == null || itemstack.stackSize == 0)
		{
			return false;
		}
		
		for (Container submodule : containers)
		{
			if (refillContainer(itemstack, submodule))
			{
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean refillContainer(ItemStack itemstack, Container container)
	{
		Pair<Integer, Integer> range = subContainerSlotRanges.get(container);
		return !this.mergeItemStack(itemstack, range.getLeft(), range.getRight(), false);
	}
	
	public IChatComponent getInventoryDisplayName()
	{
		return new ChatComponentText("");
	}
	
}