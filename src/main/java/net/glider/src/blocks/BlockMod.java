
package net.glider.src.blocks;

import net.glider.src.items.ItemBlockMod;
import net.glider.src.utils.GliderModInfo;
import net.glider.src.utils.IDescrObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockMod extends Block implements IDescrObject {
	
	private String name;
	public boolean showDescr = false;
	
	public static BlockMod BuildpPoint;
	
	public static void init()
	{
		BuildpPoint = new BlockBuildPoint("buildPoint");
	}
	
	public BlockMod(String uln)
	{
		this(uln, GliderModInfo.ModTestures + ":" + uln, Block.soundTypeStone, Material.rock, 0.5F, 10.0F, true);
	}
	
	public BlockMod(String uln, boolean reg)
	{
		super(Material.rock);
		this.name = uln;
		this.setBlockName(uln);
		if (reg)
			GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}
	
	public BlockMod(String uln, String texture)
	{
		this(uln, texture, Block.soundTypeStone, Material.rock, 0.5F, 10.0F, true);
	}
	
	public BlockMod(String uln, String texture, Block.SoundType sound, Material material, float har, float res, boolean reg)
	{
		super(material);
		this.name = uln;
		this.setBlockName(uln);
		this.setStepSound(sound);
		this.setResistance(res);
		this.setHardness(har);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockTextureName(texture);
		if (reg)
			GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}
	
	public boolean getItem(EntityPlayer ep, int size)
	{
		if (ep.getCurrentEquippedItem().stackSize >= size)
		{
			if (!ep.capabilities.isCreativeMode)
			{
				ep.getCurrentEquippedItem().stackSize = ep.getCurrentEquippedItem().stackSize - size;
			}
			ep.swingItem();
			return true;
		}
		return false;
	}
	
	public void onItemBlockCreation(ItemBlockMod ibm)
	{}
	
	public Class<? extends ItemBlockMod> getItemBlockClass()
	{
		return ItemBlockMod.class;
	}
	
	//  @SideOnly(Side.CLIENT)
	// public void registerBlockIcons(IIconRegister p_149651_1_)
	//  {
	//     this.blockIcon = p_149651_1_.registerIcon(GliderModInfo.ModTestures+":"+getUnlocalizedName());
	// }
	
	@Override
	public String getLocalizedName()
	{
		return StatCollector.translateToLocal(getUnlocalizedName() + ".name");
	}
	
	public String getUnlocalizedName(ItemStack is)
	{
		return getUnlocalizedName();
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return GliderModInfo.MOD_ID + ":block." + name;
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
