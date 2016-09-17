
package net.glider.src.recipes;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
import net.glider.src.gui.ContainerSchematicJetpack;
import net.glider.src.gui.GuiSchematicJetpack;
import net.glider.src.items.ItemMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SchematicJetpack implements ISchematicPage {
	@Override
	public int getPageID()
	{
		return 10 + SchematicsUtil.JetPackGuiID;
	}
	
	@Override
	public int getGuiID()
	{
		return SchematicsUtil.JetPackGuiID + 10;
	}
	
	@Override
	public ItemStack getRequiredItem()
	{
		return new ItemStack(ItemMod.schematicjetpack);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public GuiScreen getResultScreen(EntityPlayer player, int x, int y, int z)
	{
		return new GuiSchematicJetpack(player.inventory, x, y, z);
	}
	
	@Override
	public Container getResultContainer(EntityPlayer player, int x, int y, int z)
	{
		return new ContainerSchematicJetpack(player.inventory, x, y, z);
	}
	
	@Override
	public int compareTo(ISchematicPage o)
	{
		if (this.getPageID() > o.getPageID())
		{
			return 1;
		} else
		{
			return -1;
		}
	}
}