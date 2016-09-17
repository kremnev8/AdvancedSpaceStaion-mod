
package net.glider.src.blocks;

import java.lang.reflect.Method;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import net.glider.src.GliderCore;
import net.glider.src.gui.GuiHandler;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.utils.ChatUtils;
import net.glider.src.utils.LocalizedChatComponent;
import net.glider.src.utils.LocalizedString;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockArmorStand extends BlockContainerMod {
	
	public BlockArmorStand(String uln)
	{
		super(uln);
		this.setStepSound(soundTypeMetal);
		this.setShowDescr(true);
		GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(GalacticraftCore.TEXTURE_PREFIX + "machine_blank");
	}
	
	@Override
	public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6)
	{
		TileEntity tileEntity = var1.getTileEntity(var2, var3, var4);
		
		if (tileEntity instanceof TileEntityArmorStand)
		{
			((TileEntityArmorStand) tileEntity).onDestroy(null);
		}
		dropEntireInventory(var1, var2, var3, var4, var5, var6);
		super.breakBlock(var1, var2, var3, var4, var5, var6);
	}
	
	public void dropEntireInventory(World world, int x, int y, int z, Block par5, int par6)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if (tileEntity != null)
		{
			if (tileEntity instanceof IInventory)
			{
				IInventory inventory = (IInventory) tileEntity;
				
				for (int var6 = 0; var6 < inventory.getSizeInventory(); ++var6)
				{
					ItemStack var7 = inventory.getStackInSlot(var6);
					
					if (var7 != null)
					{
						Random random = new Random();
						float var8 = random.nextFloat() * 0.8F + 0.1F;
						float var9 = random.nextFloat() * 0.8F + 0.1F;
						float var10 = random.nextFloat() * 0.8F + 0.1F;
						
						while (var7.stackSize > 0)
						{
							int var11 = random.nextInt(21) + 10;
							
							if (var11 > var7.stackSize)
							{
								var11 = var7.stackSize;
							}
							
							var7.stackSize -= var11;
							EntityItem var12 = new EntityItem(world, x + var8, y + var9, z + var10, new ItemStack(var7.getItem(), var11, var7.getItemDamage()));
							
							if (var7.hasTagCompound())
							{
								var12.getEntityItem().setTagCompound((NBTTagCompound) var7.getTagCompound().copy());
							}
							
							float var13 = 0.05F;
							var12.motionX = (float) random.nextGaussian() * var13;
							var12.motionY = (float) random.nextGaussian() * var13 + 0.2F;
							var12.motionZ = (float) random.nextGaussian() * var13;
							world.spawnEntityInWorld(var12);
						}
					}
				}
			}
		}
	}
	
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		return this.blockIcon;
	}
	
	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		
		int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		
		if (!this.canPlaceChamberAt(world, x, y, z, entityLiving))
		{
			if (entityLiving instanceof EntityPlayer)
			{
				if (!world.isRemote)
				{
					ChatUtils.SendChatMessageOnClient(((EntityPlayer) entityLiving), new LocalizedChatComponent(new LocalizedString("gui.warning.noroom", EnumChatFormatting.RED)));
				}
				
				world.setBlockToAir(x, y, z);
				((EntityPlayer) entityLiving).inventory.addItemStackToInventory(new ItemStack(BlockContainerMod.BlockArmorStand, 1));
				return;
			}
		} else
		{
			
			world.setBlockMetadataWithNotify(x, y, z, angle > 3 ? 0 : angle, 3);
		}
		
		TileEntity var8 = world.getTileEntity(x, y, z);
		
		if (var8 instanceof IMultiBlock)
		{
			((IMultiBlock) var8).onCreate(new BlockVec3(x, y, z));
		}
		
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		
		if (this.isUsableWrench(entityPlayer, entityPlayer.inventory.getCurrentItem(), x, y, z))
		{
			this.damageWrench(entityPlayer, entityPlayer.inventory.getCurrentItem(), x, y, z);
			if (this.onUseWrench(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ))
			{
				return true;
			}
		}
		return this.onMachineActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
	}
	
	public boolean isUsableWrench(EntityPlayer entityPlayer, ItemStack itemStack, int x, int y, int z)
	{
		if (entityPlayer != null && itemStack != null)
		{
			Class<? extends Item> wrenchClass = itemStack.getItem().getClass();
			
			/**
			 * UE and Buildcraft
			 */
			try
			{
				Method methodCanWrench = wrenchClass.getMethod("canWrench", EntityPlayer.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
				return (Boolean) methodCanWrench.invoke(itemStack.getItem(), entityPlayer, x, y, z);
			} catch (NoClassDefFoundError e)
			{
			} catch (Exception e)
			{
			}
			
			/**
			 * Industrialcraft
			 */
			try
			{
				if (wrenchClass == Class.forName("ic2.core.item.tool.ItemToolWrench") || wrenchClass == Class.forName("ic2.core.item.tool.ItemToolWrenchElectric"))
				{
					return itemStack.getItemDamage() < itemStack.getMaxDamage();
				}
			} catch (Exception e)
			{
			}
		}
		
		return false;
	}
	
	public boolean damageWrench(EntityPlayer entityPlayer, ItemStack itemStack, int x, int y, int z)
	{
		Class<? extends Item> wrenchClass = itemStack.getItem().getClass();
		
		/**
		 * UE and Buildcraft
		 */
		try
		{
			Method methodWrenchUsed = wrenchClass.getMethod("wrenchUsed", EntityPlayer.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
			methodWrenchUsed.invoke(itemStack.getItem(), entityPlayer, x, y, z);
			return true;
		} catch (Exception e)
		{
		}
		
		/**
		 * Industrialcraft
		 */
		try
		{
			if (wrenchClass == Class.forName("ic2.core.item.tool.ItemToolWrench") || wrenchClass == Class.forName("ic2.core.item.tool.ItemToolWrenchElectric"))
			{
				Method methodWrenchDamage = wrenchClass.getMethod("damage", ItemStack.class, Integer.TYPE, EntityPlayer.class);
				methodWrenchDamage.invoke(itemStack.getItem(), itemStack, 1, entityPlayer);
				return true;
			}
		} catch (Exception e)
		{
		}
		
		return false;
	}
	
	public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		int metadata = par1World.getBlockMetadata(x, y, z);
		int original = metadata;
		
		int change = 0;
		
		// Re-orient the block
		switch (original)
		{
		case 0:
			change = 1;
			break;
		case 1:
			change = 2;
			break;
		case 2:
			change = 3;
			break;
		case 3:
			change = 0;
			break;
		}
		
		par1World.setBlockMetadataWithNotify(x, y, z, change, 3);
		return true;
	}
	
	/**
	 * Called when the block is right clicked by the player
	 */
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		par5EntityPlayer.openGui(GliderCore.instance, GuiHandler.ARMORSTANDGUI, world, x, y, z);
		return true;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityArmorStand();
	}
	
	private boolean canPlaceChamberAt(World world, int x0, int y0, int z0, EntityLivingBase player)
	{
		for (int y = 0; y < 2; y++)
		{
			Block blockAt = world.getBlock(x0, y0 + y, z0);
			int metaAt = world.getBlockMetadata(x0, y0 + y, z0);
			
			if (y == 0)
			{
				continue;
			}
			if (!blockAt.getMaterial().isReplaceable())
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int damageDropped(int metadata)
	{
		return 0;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	public static ChunkCoordinates getNearestEmptyChunkCoordinates(World par0World, int par1, int par2, int par3, int par4)
	{
		for (int k1 = 0; k1 <= 1; ++k1)
		{
			int l1 = par1 - 1;
			int i2 = par3 - 1;
			int j2 = l1 + 2;
			int k2 = i2 + 2;
			
			for (int l2 = l1; l2 <= j2; ++l2)
			{
				for (int i3 = i2; i3 <= k2; ++i3)
				{
					if (World.doesBlockHaveSolidTopSurface(par0World, l2, par2 - 1, i3) && !par0World.getBlock(l2, par2, i3).getMaterial().isOpaque() && !par0World.getBlock(l2, par2 + 1, i3).getMaterial().isOpaque())
					{
						if (par4 <= 0)
						{
							return new ChunkCoordinates(l2, par2, i3);
						}
						
						--par4;
					}
				}
			}
		}
		
		return null;
	}
	
}
