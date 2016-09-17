
package net.glider.src.items;

import ic2.api.item.IC2Items;
import net.glider.src.utils.GliderModInfo;
import net.glider.src.utils.IDescrObject;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMod extends Item implements IDescrObject {
	
	protected final String name;
	
	public boolean showDescr;
	
	public static ItemMod Builder;
	public static ItemDebugTool DebugTool;
	public static ItemArmorMod spaceJetpack;
	public static Item ironScaffold;
	public static int scaffold_meta = 0;
	public static ItemMod dockingPortComp;
	
	public static ItemMod brokenTin;
	public static ItemMod brokenSteel;
	public static ItemMod brokenAluminum;
	
	public static ItemMod schematicjetpack;
	
	public static ItemMod smallEngine;
	public static ItemMod OD_engines_set;
	
	public static ItemMod emptyIdea;
	public static ItemMod filledIdea;
	
	public static ItemMod rotatingRing;
	
	public static ItemMod motor;
	public static ItemMod coil;
	
	public static Item ingSteel;
	public static int ingSteelMeta;
	
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
			motor = new ItemMod("AltMotor");
			coil = new ItemMod("AltCoil");
		}
		dockingPortComp = new ItemMod("dockingComponent");
		
		brokenTin = new ItemBrokenPlate("brokenTin", 0);
		brokenSteel = new ItemBrokenPlate("brokenSteel", 1);
		brokenAluminum = new ItemBrokenPlate("brokenAluminum", 2);
		
		schematicjetpack = new ItemSchematic("schematicJetpack");
		smallEngine = new ItemMod("smallEngine");
		OD_engines_set = new ItemMod("Omni-Dir_Engine_Set");
		
		emptyIdea = new ItemMod("emptyIdea");
		filledIdea = new ItemMod("filledIdea");
		
		rotatingRing = new ItemMod("AGMotorMap");
		if (OreDictionary.doesOreNameExist("ingotSteel"))
		{
			ItemStack stack = OreDictionary.getOres("ingotSteel").get(0);
			ingSteel = stack.getItem();
			ingSteelMeta = stack.getItemDamage();
		} else
		{
			ingSteel = new ItemMod("steelIngot");
			OreDictionary.registerOre("ingotSteel", ingSteel);
		}
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
