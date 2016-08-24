package net.glider.src.blocks;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.glider.src.GliderCore;
import net.glider.src.dimensions.DockingPortSaveData;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.glider.src.gui.GuiHandler;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDockingPoint extends BlockContainerMod {
	
	private static String name;
	
	@SideOnly(Side.CLIENT)
	IIcon top;
	IIcon side;
	IIcon bottom;

	public BlockDockingPoint(String uln) {
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
        }
        else
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
    			savef.DockingPorts.add(new int[]{x,y,z});
    			savef.markDirty();
    			GLoger.logInfo("Added a new value in dim data");
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
    			for (int i=0;i < savef.DockingPorts.size();i++)
    			{
    			if (savef.DockingPorts.get(i)[0] == x && savef.DockingPorts.get(i)[1] == y && savef.DockingPorts.get(i)[2] == z)
    			{
    			savef.DockingPorts.remove(i);
    			savef.markDirty();
    			GLoger.logInfo("delited mine value in dim data");
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
	        return Bside == 1 
	        		? top 
	        		: (Bside == 0 
	        		? bottom
	        		: (Bside == 2
	        		? side
	        		: (Bside == 5 
	        		? side
	        		: (Bside == 3 
	        		? side
	        		: (Bside == 4 
	        		? side
	        		: side)))));
	}
	
    @Override
    public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_) {
    	return new TileEntityDockingPort();
    }

}
