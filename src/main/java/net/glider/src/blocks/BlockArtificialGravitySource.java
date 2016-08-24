package net.glider.src.blocks;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.glider.src.GliderCore;
import net.glider.src.dimensions.DockingPortSaveData;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.glider.src.gui.GuiHandler;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.ClientGravityDataRecivePacket;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.tiles.TileEntityGravitySource;
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

public class BlockArtificialGravitySource extends BlockContainerMod {
	
	private static String name;
	
	@SideOnly(Side.CLIENT)
	IIcon iconBuffer;

	public BlockArtificialGravitySource(String uln) {
		super(uln);
		this.name = uln;
		this.setCreativeTab(CreativeTabs.tabRedstone);
		GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float lx, float ly, float lz)
    {
    	return false;
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
    	if (!world.isRemote)
    	{
    		
    		if (world.provider instanceof WorldProviderOrbitModif)
    		{
    			DockingPortSaveData savef = DockingPortSaveData.forWorld(world);
    			savef.GraviySources.add(new int[]{x,y,z});
    			savef.markDirty();
    			
    			List<Double> gforces = new ArrayList();
    			for (int i=0;i < savef.GraviySources.size();i++)
    			{
    				if (world.getTileEntity(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]) != null)
					{
						TileEntity te = world.getTileEntity(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]);
						if (te instanceof TileEntityGravitySource)
						{
							gforces.add(((TileEntityGravitySource)te).gravityAddition);
						}
					}
    			}
    			
    			PacketHandler.sendToDimension(new ClientGravityDataRecivePacket(gforces), world.provider.dimensionId);
    			//GLoger.logInfo("Added a new value in dim data");
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
    			for (int i=0;i < savef.GraviySources.size();i++)
    			{
    			if (savef.GraviySources.get(i)[0] == x && savef.GraviySources.get(i)[1] == y && savef.GraviySources.get(i)[2] == z)
    			{
    			savef.GraviySources.remove(i);
    			savef.markDirty();
    			
    			List<Double> gforces = new ArrayList();
    			for (int i2=0;i2 < savef.GraviySources.size();i2++)
    			{
    				if (world.getTileEntity(savef.GraviySources.get(i2)[0], savef.GraviySources.get(i2)[1], savef.GraviySources.get(i2)[2]) != null)
					{
						TileEntity te = world.getTileEntity(savef.GraviySources.get(i2)[0], savef.GraviySources.get(i2)[1], savef.GraviySources.get(i2)[2]);
						if (te instanceof TileEntityGravitySource)
						{
							gforces.add(((TileEntityGravitySource)te).gravityAddition);
						}
					}
    			}
    			
    			PacketHandler.sendToDimension(new ClientGravityDataRecivePacket(gforces), world.provider.dimensionId);
    		//	GLoger.logInfo("delited mine value in dim data");
    			}
    			}
    		}
    	}
    }
	
	//TODO add tileentity and gui
	
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.iconBuffer = iconRegister.registerIcon(GliderModInfo.ModTestures+":empty");
    }

	@SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return iconBuffer;
    }
	
    @Override
    public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_) {
    	return new TileEntityGravitySource();
    }
	

}
