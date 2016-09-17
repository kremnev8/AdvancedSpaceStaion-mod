
package net.glider.src.items;

import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSchematic extends ItemMod implements ISchematicItem {
	
	public ItemSchematic(String uln)
	{
		super(uln);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}
	
	@Override
	public CreativeTabs getCreativeTab()
	{
		return GalacticraftCore.galacticraftItemsTab;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyCore.galacticraftItem;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister registry)
	{
		itemIcon = registry.registerIcon(GliderModInfo.ModTestures + ":" + "schematic_jetpack");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if (par2EntityPlayer.worldObj.isRemote)
		{
			switch (par1ItemStack.getItemDamage())
			{
			case 0:
				par3List.add(StatCollector.translateToLocal("schematic.jetpack.name"));
				break;
			}
		}
	}
}
