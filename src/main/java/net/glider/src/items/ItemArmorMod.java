
package net.glider.src.items;

import net.glider.src.utils.GliderModInfo;
import net.glider.src.utils.IDescrObject;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemArmorMod extends ItemArmor implements IDescrObject {
	
	protected final String name;
	public boolean showDescr;
	
	public ItemArmorMod(String uln, ArmorMaterial material, int type)
	{
		this(uln, CreativeTabs.tabCombat, material, type);
	}
	
	/**
	 * @param type
	 *            0 - шлем 1 - кираса 2 - поножи 3 - ботинки
	 */
	public ItemArmorMod(String uln, CreativeTabs tab, ArmorMaterial material, int type)
	{
		super(material, 0, type);
		this.name = uln;
		this.setTextureName(GliderModInfo.ModTestures + ":" + name);
		this.setUnlocalizedName(uln);
		this.setCreativeTab(tab);
		GameRegistry.registerItem(this, uln);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		return StatCollector.translateToLocal(GliderModInfo.MOD_ID + ":item." + name + ".name");
	}
	
	@Override
	public String getDescription(int meta)
	{
		return "";
	}
	
	@Override
	public String getShiftDescription(int meta)
	{
		return StatCollector.translateToLocal(GliderModInfo.MOD_ID + ":descr_shift." + name + ".name");
	}
	
	@Override
	public boolean showDescription(int meta)
	{
		return showDescr;
	}
	
	public void setShowDescr(boolean showDescr)
	{
		this.showDescr = showDescr;
	}
}
