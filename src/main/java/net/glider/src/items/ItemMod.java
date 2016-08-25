
package net.glider.src.items;

import ic2.api.item.IC2Items;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMod extends Item {
	
	protected final String name;
	
	public static ItemMod Builder;
	public static ItemDebugTool DebugTool;
	public static ItemArmorMod spaceJetpack;
	public static Item ironScaffold;
	public static int scaffold_meta = 0;
	public static ItemMod dockingPortComp;
	
	public static ItemMod brokenTin;
	public static ItemMod brokenSteel;
	public static ItemMod brokenAluminum;
	
	public static void init()
	{
		
		Builder = new ItemBuilder("Builder");
		DebugTool = new ItemDebugTool("debugTool");
		spaceJetpack = new ItemSpaceJetpack("Jetpack");
		if (Loader.isModLoaded("IC2"))
		{
			ironScaffold = IC2Items.getItem("ironScaffold").getItem();
			scaffold_meta = IC2Items.getItem("ironScaffold").getItemDamage();
		} else
		{
			ironScaffold = new ItemMod("ironScaffold");
		}
		dockingPortComp = new ItemMod("dockingComponent");
		
		brokenTin = new ItemBrokenPlate("brokenTin", 0);
		brokenSteel = new ItemBrokenPlate("brokenSteel", 1);
		brokenAluminum = new ItemBrokenPlate("brokenAluminum", 2);
	}
	
	@SideOnly(Side.CLIENT)
	public static IIcon getBuilderIcons(int i)
	{
		switch (i)
		{
		case 0:
			return ItemMod.DebugTool.BuilderIconRed;
		case 1:
			return ItemMod.DebugTool.BuilderIconGreen;
		}
		return null;
	}
	
	public ItemMod(String uln)
	{
		this(uln, CreativeTabs.tabMaterials);
		
	}
	
	public ItemMod(String uln, int i)
	{
		this.name = uln;
		this.setTextureName(GliderModInfo.ModTestures + ":" + name);
		this.setUnlocalizedName(uln);
		GameRegistry.registerItem(this, uln);
		
	}
	
	public ItemMod(String uln, CreativeTabs tab)
	{
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
}
