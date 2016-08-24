package net.glider.src.items;

import net.glider.src.GliderCore;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemArmorMod extends ItemArmor{

	
	protected final String name;
	
	public ItemArmorMod(String uln,ArmorMaterial material,int type) {
		this(uln, CreativeTabs.tabCombat,material,type);
	}
	
	/**
	 * @param type
       0 - шлем
       1 - кираса
       2 - поножи
       3 - ботинки
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
	public String getItemStackDisplayName(ItemStack is){
		return StatCollector.translateToLocal(GliderModInfo.MOD_ID + ":" + name + ".item");
	}
}
