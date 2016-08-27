
package net.glider.src.handlers;

import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.glider.src.items.ItemMod;
import net.glider.src.utils.IDescrObject;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

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
						event.toolTip.add(jetpack.getFluid().getLocalizedName() + ": " + jetpack.getFluidAmount() + " / " + jetpack.getCapacity());
					}
				}
			}
		} else if (stack.getItem() == ItemMod.DebugTool)
		{
			event.toolTip.add(EnumChatFormatting.RED + "Used only by devs!");
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
								event.toolTip.add(EnumChatFormatting.BLUE + "*" + EnumChatFormatting.GRAY + " - x: " + strpos[0] + " y: " + strpos[1] + " z: " + strpos[2]);
						}
					}
				}
			}
		}
		if (stack.getItem() instanceof IDescrObject)
		{
			IDescrObject obj = (IDescrObject) stack.getItem();
			if (obj.showDescription(stack.getItemDamage()))
			{
				if (!obj.getDescription(stack.getItemDamage()).equals(""))
				{
					event.toolTip.addAll(FMLClientHandler.instance().getClient().fontRenderer.listFormattedStringToWidth(obj.getDescription(stack.getItemDamage()), 150));
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
				{
					event.toolTip.addAll(FMLClientHandler.instance().getClient().fontRenderer.listFormattedStringToWidth(obj.getShiftDescription(stack.getItemDamage()), 150));
				} else
				{
					if (!obj.getShiftDescription(stack.getItemDamage()).equals(""))
					{
						event.toolTip.add(GCCoreUtil.translateWithFormat("itemDesc.shift.name", GameSettings.getKeyDisplayString(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.getKeyCode())));
					}
				}
			}
		}
	}
	
}
