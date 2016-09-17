
package net.glider.src.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.tile.TileEntityMulti;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFake extends BlockContainerMod implements IPartialSealableBlock, ITileEntityProvider {
	
	private IIcon[] fakeIcons;
	
	public BlockFake(String uln)
	{
		super(uln);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundTypeMetal);
		this.setBlockTextureName(GliderModInfo.ModTestures + ":none");
		this.setResistance(1000000000000000.0F);
		GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion)
	{
		return false;
	}
	
	public void makeFakeBlock(World worldObj, BlockVec3 position, BlockVec3 mainBlock, int meta)
	{
		worldObj.setBlock(position.x, position.y, position.z, this, meta, 3);
		((TileEntityMulti) worldObj.getTileEntity(position.x, position.y, position.z)).setMainBlock(mainBlock);
	}
	
	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
		
		if (tileEntity instanceof TileEntityMulti)
		{
			BlockVec3 mainBlockPosition = ((TileEntityMulti) tileEntity).mainBlockPosition;
			
			if (mainBlockPosition != null)
			{
				return mainBlockPosition.getBlock(par1World).getBlockHardness(par1World, par2, par3, par4);
			}
		}
		
		return this.blockHardness;
	}
	
	@Override
	public boolean isSealed(World world, int x, int y, int z, ForgeDirection direction)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (metadata == 0)
		{
			return direction == ForgeDirection.DOWN;
		}
		
		return false;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if (tileEntity instanceof TileEntityMulti)
		{
			((TileEntityMulti) tileEntity).onBlockRemoval();
		}
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntityMulti tileEntity = (TileEntityMulti) par1World.getTileEntity(x, y, z);
		return tileEntity.onBlockActivated(par1World, x, y, z, par5EntityPlayer);
	}
	
	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int meta)
	{
		return new TileEntityMulti();
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityMulti)
		{
			BlockVec3 mainBlockPosition = ((TileEntityMulti) tileEntity).mainBlockPosition;
			
			if (mainBlockPosition != null)
			{
				Block mainBlockID = world.getBlock(mainBlockPosition.x, mainBlockPosition.y, mainBlockPosition.z);
				
				if (Blocks.air != mainBlockID)
				{
					return mainBlockID.getPickBlock(target, world, mainBlockPosition.x, mainBlockPosition.y, mainBlockPosition.z);
				}
			}
		}
		
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
	{
		TileEntity tileEntity = worldObj.getTileEntity(target.blockX, target.blockY, target.blockZ);
		
		if (tileEntity instanceof TileEntityMulti)
		{
			BlockVec3 mainBlockPosition = ((TileEntityMulti) tileEntity).mainBlockPosition;
			
			if (mainBlockPosition != null)
			{
				effectRenderer.addBlockHitEffects(mainBlockPosition.x, mainBlockPosition.y, mainBlockPosition.z, target);
			}
		}
		
		return super.addHitEffects(worldObj, target, effectRenderer);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
	{
		return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
	}
}
