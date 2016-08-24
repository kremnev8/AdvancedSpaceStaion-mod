package net.glider.src.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.glider.src.items.ItemMod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidTank;

public class ItemsToolTips {
	
	@SubscribeEvent
	public void showToolTips(ItemTooltipEvent event)
	{
		ItemStack stack = event.itemStack;
		if (stack.getItem() == ItemMod.spaceJetpack)
		{
			FluidTank jetpack = new FluidTank(1750 * ConfigManagerCore.rocketFuelFactor);
			if (stack.hasTagCompound())
			{
				if (stack.stackTagCompound.hasKey("fuelTank"))
				{
					jetpack.readFromNBT(stack.stackTagCompound.getCompoundTag("fuelTank"));
					
					if (jetpack.getFluid() != null && jetpack.getFluidAmount() > 0)
					{
						event.toolTip.add(jetpack.getFluid().getLocalizedName()+": "+jetpack.getFluidAmount()+" / "+jetpack.getCapacity());
					}
				}
			}
		}else if (stack.getItem() == ItemMod.DebugTool)
		{
			event.toolTip.add(EnumChatFormatting.RED+"Used only by devs!");
		} else if (stack.getItem() == ItemMod.Builder)
		{
			if (stack.hasTagCompound())
			{
				NBTTagCompound tag = stack.stackTagCompound;
				if (tag.hasKey("chests"))
				{
					NBTTagList list = tag.getTagList("chests", 11);
					if (list.tagCount() > 0)
					{
						event.toolTip.add("Chest marked positions:");
						for (int i = 0; i < list.tagCount(); i++)
						{
							int[] strpos = list.func_150306_c(i);
							if (strpos.length > 2)
							event.toolTip.add(EnumChatFormatting.BLUE+"*"+EnumChatFormatting.GRAY+" - x: "+strpos[0]+" y: "+strpos[1]+" z: "+strpos[2]);
						}
					}
				}
			}
		}
	}

}
