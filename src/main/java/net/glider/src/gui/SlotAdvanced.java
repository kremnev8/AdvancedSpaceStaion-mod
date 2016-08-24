package net.glider.src.gui;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import net.glider.src.tiles.TileEntityDockingPort;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class SlotAdvanced extends Slot
{

    public SlotAdvanced(IInventory inv, int id, int x, int y)
    {
    	super(inv,id,x,y);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
	public boolean isItemValid(ItemStack is)
	{
		if (FluidUtil.isValidContainer(is))
		{
			return true;
		}
		return false;
	}

    public int getSlotStackLimit()
    {
        return this.inventory.getInventoryStackLimit();
    }


    public boolean canTakeStack(EntityPlayer p_82869_1_)
    {
        return true;
    }

}
