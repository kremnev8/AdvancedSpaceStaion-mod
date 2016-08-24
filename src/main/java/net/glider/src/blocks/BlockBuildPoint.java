package net.glider.src.blocks;

import java.util.List;

import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBuildPoint extends BlockMod {

	IIcon iconBuffer;

	public BlockBuildPoint(String uln)
	{
		super(uln);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.iconBuffer = iconRegister.registerIcon(GliderModInfo.ModTestures + ":buildPoint");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return iconBuffer;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		return 6000000.0F;
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		return -1;
	}

	/**
	 * 0 - everything, 1 - everything excluding pierce, 2 - only add
	 * structures, 3 - only window(only rot == 0), 4 - solar panels, 5 -
	 * greenhouse, 6 - pierce
	 */
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}



}
