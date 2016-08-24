package net.glider.src.blocks;

import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRemoveInfo extends BlockContainerMod {

	public BlockRemoveInfo(String uln)
	{
		super(uln);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setBlockTextureName(GliderModInfo.ModTestures+":empty");
        GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}
	
	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
            int m = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_);

            if (m == 1)
            {
                this.setBlockBounds(0.94F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }else if (m == 0)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.06F);
            }else if (m == 3)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.06F, 1.0F, 1.0F);
            }else if (m == 2)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.94F, 1.0F, 1.0F, 1.0F);
            }
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
		this.setBlockBoundsBasedOnState(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    }
    
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2int, int par3int, int par4int, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
	    int i = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	    par1World.setBlockMetadataWithNotify(par2int, par3int, par4int, i, 2);
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
    
    @Override
    public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_) {
    	return new TileEntityRemoveInfo();
    }

}
