
package net.glider.src.blocks;

import java.util.Random;

import net.glider.src.GliderCore;
import net.glider.src.dimensions.DockingPortSaveData;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.glider.src.gui.GuiHandler;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDockingPoint extends BlockContainerMod {
	
	private static String name;
	
	@SideOnly(Side.CLIENT)
	IIcon top;
	IIcon side;
	IIcon bottom;
	
	public BlockDockingPoint(String uln)
	{
		super(uln);
		this.name = uln;
		this.setCreativeTab(CreativeTabs.tabRedstone);
		GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float lx, float ly, float lz)
	{
		if (world.isRemote)
		{
			return true;
		} else
		{
			player.openGui(GliderCore.instance, GuiHandler.DOCKINGPORTGUI, world, x, y, z);
			return true;
		}
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			
			if (world.provider instanceof WorldProviderOrbitModif)
			{
				DockingPortSaveData savef = DockingPortSaveData.forWorld(world);
				savef.DockingPorts.add(new int[] { x, y, z });
				savef.markDirty();
			}
		}
	}
	
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta)
	{
		if (!world.isRemote)
		{
			
			if (world.provider instanceof WorldProviderOrbitModif)
			{
				DockingPortSaveData savef = DockingPortSaveData.forWorld(world);
				for (int i = 0; i < savef.DockingPorts.size(); i++)
				{
					if (savef.DockingPorts.get(i)[0] == x && savef.DockingPorts.get(i)[1] == y && savef.DockingPorts.get(i)[2] == z)
					{
						savef.DockingPorts.remove(i);
						savef.markDirty();
						
					}
				}
			}
			dropEntireInventory(world, x, y, z);
			TileEntityDockingPort te = (TileEntityDockingPort) world.getTileEntity(x, y, z);
			if (te != null && te.rocket != null)
			{
				te.rocket.setDead();
			}
		}
	}
	
	public void dropEntireInventory(World world, int x, int y, int z)
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		top = par1IconRegister.registerIcon(GliderModInfo.ModTestures + ":machine_fuel_input");
		side = par1IconRegister.registerIcon(GliderModInfo.ModTestures + ":deco_aluminium");
		bottom = par1IconRegister.registerIcon(GliderModInfo.ModTestures + ":DockImg");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int Bside, int meta)
	{
		return Bside == 1 ? top : (Bside == 0 ? bottom : (Bside == 2 ? side : (Bside == 5 ? side : (Bside == 3 ? side : (Bside == 4 ? side : side)))));
	}
	
	@Override
	public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityDockingPort();
	}
	
}
