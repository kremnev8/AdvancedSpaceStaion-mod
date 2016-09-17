
package net.glider.src.blocks;

import java.util.Random;

import net.glider.src.items.ItemContainerBlockMod;
import net.glider.src.utils.GliderModInfo;
import net.glider.src.utils.IDescrObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockContainerMod extends BlockContainer implements IDescrObject {
	
	Random r = new Random();
	private String name;
	public boolean showDescr;
	
	public static BlockContainerMod DockingPoint;
	public static BlockContainerMod BlockInfo;
	public static BlockContainerMod BlockRemoveInfo;
	public static BlockContainerMod BlockArticialGsource;
	public static BlockContainerMod BlockArmorStand;
	public static BlockFake BlockFake;
	
	public static void init()
	{
		DockingPoint = new BlockDockingPoint("dockingPoint");
		BlockInfo = new BlockInfo("infoBlock");
		BlockRemoveInfo = new BlockRemoveInfo("removeInfoBlock");
		BlockArticialGsource = new BlockArtificialGravitySource("artificialGsource");
		BlockArmorStand = new BlockArmorStand("armorStand");
		BlockFake = new BlockFake("blockfake");
		
	}
	
	public BlockContainerMod(String uln)
	{
		this(uln, Block.soundTypeStone, Material.rock, 0.5F, 10.0F);
	}
	
	public BlockContainerMod(String uln, int i)
	{
		super(Material.rock);
		this.name = uln;
		this.setBlockName(uln);
		this.setStepSound(Block.soundTypeMetal);
		this.setResistance(10.0F);
		this.setHardness(0.5F);
	}
	
	public BlockContainerMod(String uln, Material material)
	{
		this(uln, Block.soundTypeStone, material, 0.5F, 10.0F);
	}
	
	public BlockContainerMod(String uln, Block.SoundType sound, Material material, float har, float res)
	{
		super(material);
		this.name = uln;
		this.setBlockName(uln);
		this.setStepSound(sound);
		this.setResistance(res);
		this.setHardness(har);
		this.setCreativeTab(CreativeTabs.tabDecorations);
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
	
	public void onItemBlockCreation(ItemContainerBlockMod itemContainerBlockMod)
	{}
	
	public Class<? extends ItemContainerBlockMod> getItemBlockClass()
	{
		return ItemContainerBlockMod.class;
	}
	
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
		return GliderModInfo.MOD_ID + ":tile." + name;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return null;
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
