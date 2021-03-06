
package net.glider.src.blocks;

import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockInfo extends BlockContainerMod {
	
	IIcon iconBuffer;
	public static final Material invis = new MaterialInvisble(MapColor.airColor);
	
	public BlockInfo(String uln)
	{
		super(uln, invis);
		this.setHardness(-1F);
		this.setResistance(6000000.0F);
		this.setBlockUnbreakable();
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.setBlockTextureName(GliderModInfo.ModTestures + ":empty");
		this.setBlockName(uln);
		GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}
	
	public int getRenderType()
	{
		return -1;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.iconBuffer = iconRegister.registerIcon(GliderModInfo.ModTestures + ":empty");
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return iconBuffer;
	}
	
	//	@Override
	//	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	//	{
	//		return 1.0F;s
	///	}
	
	//	@Override
	//	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	//	{
	//		return 100.0F;
	//	}
	
	@Override
	public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityInfo();
	}
	
}
