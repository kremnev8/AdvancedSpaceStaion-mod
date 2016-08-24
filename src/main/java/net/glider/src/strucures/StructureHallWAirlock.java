package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAirLockController;
import net.glider.src.items.ItemMod;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureHallWAirlock extends Structure {

	private boolean hidden;
	private String OW;
	
	public StructureHallWAirlock(boolean hidden) {
		super(hidden);
		this.hidden = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureHallWAirlock Nstr = new StructureHallWAirlock(hidden);
		Nstr.Configure(placementPos, placementRotation, placementDir);
		return Nstr;
	}
	
	public void setOwner(String ow)
	{
		this.OW = ow;
		GLoger.logInfo(OW);
	}
	
	public void setAirLock(World world,int x,int y,int z,int meta,int flag,String Owner)
	{
		Block block3 = GCBlocks.airLockFrame;
		world.setBlock(x, y, z, block3,1,2);
		TileEntityAirLockController tile = (TileEntityAirLockController)world.getTileEntity(x, y, z);
		tile.ownerName = Owner;
	}
	
	@Override
	public void deconstruct(World world, ForgeDirection dir, int x, int y, int z) 
	{
		
	}

	@Override
	public void Build(World world, ForgeDirection dir, int x, int y, int z) {

		if (dir == ForgeDirection.NORTH)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x+-2, y+-2, z+-8, block1,4,2);
			world.setBlock(x+-2, y+-2, z+-7, block1,4,2);
			world.setBlock(x+-2, y+-2, z+-6, block1,4,2);
			world.setBlock(x+-2, y+-2, z+-5, block1,4,2);
			world.setBlock(x+-2, y+-2, z+-4, block1,4,2);
			world.setBlock(x+-2, y+-2, z+-3, block1,4,2);
			world.setBlock(x+-2, y+-2, z+-2, block1,4,2);
			world.setBlock(x+-2, y+-2, z+-1, block1,4,2);
			world.setBlock(x+-2, y+-2, z+0, block1,4,2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x+-2, y+-1, z+-8, block2,4,2);
			world.setBlock(x+-2, y+-1, z+-7, block2,4,2);
			Block block3 = GCBlocks.airLockFrame;
			world.setBlock(x+-2, y+-1, z+-6, block3,0,2);
			world.setBlock(x+-2, y+-1, z+-5, block2,4,2);
			world.setBlock(x+-2, y+-1, z+-4, block2,4,2);
			world.setBlock(x+-2, y+-1, z+-3, block2,4,2);
			world.setBlock(x+-2, y+-1, z+-2, block3,0,2);
			world.setBlock(x+-2, y+-1, z+-1, block2,4,2);
			world.setBlock(x+-2, y+-1, z+0, block2,4,2);
			world.setBlock(x+-2, y+0, z+-8, block2,4,2);
			world.setBlock(x+-2, y+0, z+-7, block2,4,2);
			world.setBlock(x+-2, y+0, z+-6, block3,0,2);
			world.setBlock(x+-2, y+0, z+-5, block2,4,2);
			world.setBlock(x+-2, y+0, z+-4, block2,4,2);
			world.setBlock(x+-2, y+0, z+-3, block2,4,2);
			world.setBlock(x+-2, y+0, z+-2, block3,0,2);
			world.setBlock(x+-2, y+0, z+-1, block2,4,2);
			world.setBlock(x+-2, y+0, z+0, block2,4,2);
			world.setBlock(x+-2, y+1, z+-8, block2,4,2);
			world.setBlock(x+-2, y+1, z+-7, block2,4,2);
			world.setBlock(x+-2, y+1, z+-6, block3,0,2);
			world.setBlock(x+-2, y+1, z+-5, block2,4,2);
			world.setBlock(x+-2, y+1, z+-4, block2,4,2);
			world.setBlock(x+-2, y+1, z+-3, block2,4,2);
			world.setBlock(x+-2, y+1, z+-2, block3,0,2);
			world.setBlock(x+-2, y+1, z+-1, block2,4,2);
			world.setBlock(x+-2, y+1, z+0, block2,4,2);
			world.setBlock(x+-2, y+2, z+-8, block1,0,2);
			world.setBlock(x+-2, y+2, z+-7, block1,0,2);
			world.setBlock(x+-2, y+2, z+-6, block1,0,2);
			world.setBlock(x+-2, y+2, z+-5, block1,0,2);
			world.setBlock(x+-2, y+2, z+-4, block1,0,2);
			world.setBlock(x+-2, y+2, z+-3, block1,0,2);
			world.setBlock(x+-2, y+2, z+-2, block1,0,2);
			world.setBlock(x+-2, y+2, z+-1, block1,0,2);
			world.setBlock(x+-2, y+2, z+0, block1,0,2);
			world.setBlock(x+-1, y+-2, z+-8, block2,4,2);
			world.setBlock(x+-1, y+-2, z+-7, block2,4,2);
			world.setBlock(x+-1, y+-2, z+-6, block3,0,2);
			world.setBlock(x+-1, y+-2, z+-5, block2,4,2);
			world.setBlock(x+-1, y+-2, z+-4, block2,4,2);
			world.setBlock(x+-1, y+-2, z+-3, block2,4,2);
			world.setBlock(x+-1, y+-2, z+-2, block3,0,2);
			world.setBlock(x+-1, y+-2, z+-1, block2,4,2);
			world.setBlock(x+-1, y+-2, z+0, block2,4,2);
			world.setBlock(x+-1, y+2, z+-8, block2,4,2);
			world.setBlock(x+-1, y+2, z+-7, block2,4,2);
			world.setBlock(x+-1, y+2, z+-6, block3,0,2);
			world.setBlock(x+-1, y+2, z+-5, block2,4,2);
			world.setBlock(x+-1, y+2, z+-4, block2,4,2);
			world.setBlock(x+-1, y+2, z+-3, block2,4,2);
			world.setBlock(x+-1, y+2, z+-2, block3,0,2);
			world.setBlock(x+-1, y+2, z+-1, block2,4,2);
			world.setBlock(x+-1, y+2, z+0, block2,4,2);
			world.setBlock(x+0, y+-2, z+-8, block2,4,2);
			world.setBlock(x+0, y+-2, z+-7, block2,4,2);
			setAirLock(world, x+0, y+-2, z+-6, 1, 2, OW);
		//--	world.setBlock(x+0, y+-2, z+-6, block3,1,2);
			world.setBlock(x+0, y+-2, z+-5, block2,4,2);
			world.setBlock(x+0, y+-2, z+-4, block2,4,2);
			world.setBlock(x+0, y+-2, z+-3, block2,4,2);
			setAirLock(world, x+0, y+-2, z+-2, 1, 2, OW);
		//--	world.setBlock(x+0, y+-2, z+-2, block3,1,2);
			world.setBlock(x+0, y+-2, z+-1, block2,4,2);
			world.setBlock(x+0, y+-2, z+0, block2,4,2);
			world.setBlock(x+0, y+2, z+-8, block2,4,2);
			Block block4= Blocks.glowstone;
			world.setBlock(x+0, y+2, z+-7, block4,0,2);
			world.setBlock(x+0, y+2, z+-6, block3,0,2);
			world.setBlock(x+0, y+2, z+-5, block2,4,2);
			world.setBlock(x+0, y+2, z+-4, block4,0,2);
			world.setBlock(x+0, y+2, z+-3, block2,4,2);
			world.setBlock(x+0, y+2, z+-2, block3,0,2);
			world.setBlock(x+0, y+2, z+-1, block4,0,2);
			world.setBlock(x+0, y+2, z+0, block2,4,2);
			world.setBlock(x+1, y+-2, z+-8, block2,4,2);
			world.setBlock(x+1, y+-2, z+-7, block2,4,2);
			world.setBlock(x+1, y+-2, z+-6, block3,0,2);
			world.setBlock(x+1, y+-2, z+-5, block2,4,2);
			world.setBlock(x+1, y+-2, z+-4, block2,4,2);
			world.setBlock(x+1, y+-2, z+-3, block2,4,2);
			world.setBlock(x+1, y+-2, z+-2, block3,0,2);
			world.setBlock(x+1, y+-2, z+-1, block2,4,2);
			world.setBlock(x+1, y+-2, z+0, block2,4,2);
			world.setBlock(x+1, y+2, z+-8, block2,4,2);
			world.setBlock(x+1, y+2, z+-7, block2,4,2);
			world.setBlock(x+1, y+2, z+-6, block3,0,2);
			world.setBlock(x+1, y+2, z+-5, block2,4,2);
			world.setBlock(x+1, y+2, z+-4, block2,4,2);
			world.setBlock(x+1, y+2, z+-3, block2,4,2);
			world.setBlock(x+1, y+2, z+-2, block3,0,2);
			world.setBlock(x+1, y+2, z+-1, block2,4,2);
			world.setBlock(x+1, y+2, z+0, block2,4,2);
			world.setBlock(x+2, y+-2, z+-8, block1,5,2);
			world.setBlock(x+2, y+-2, z+-7, block1,5,2);
			world.setBlock(x+2, y+-2, z+-6, block1,5,2);
			world.setBlock(x+2, y+-2, z+-5, block1,5,2);
			world.setBlock(x+2, y+-2, z+-4, block1,5,2);
			world.setBlock(x+2, y+-2, z+-3, block1,5,2);
			world.setBlock(x+2, y+-2, z+-2, block1,5,2);
			world.setBlock(x+2, y+-2, z+-1, block1,5,2);
			world.setBlock(x+2, y+-2, z+0, block1,5,2);
			world.setBlock(x+2, y+-1, z+-8, block2,4,2);
			world.setBlock(x+2, y+-1, z+-7, block2,4,2);
			world.setBlock(x+2, y+-1, z+-6, block3,0,2);
			world.setBlock(x+2, y+-1, z+-5, block2,4,2);
			world.setBlock(x+2, y+-1, z+-4, block2,4,2);
			world.setBlock(x+2, y+-1, z+-3, block2,4,2);
			world.setBlock(x+2, y+-1, z+-2, block3,0,2);
			world.setBlock(x+2, y+-1, z+-1, block2,4,2);
			world.setBlock(x+2, y+-1, z+0, block2,4,2);
			world.setBlock(x+2, y+0, z+-8, block2,4,2);
			world.setBlock(x+2, y+0, z+-7, block2,4,2);
			world.setBlock(x+2, y+0, z+-6, block3,0,2);
			world.setBlock(x+2, y+0, z+-5, block2,4,2);
			world.setBlock(x+2, y+0, z+-4, block2,4,2);
			world.setBlock(x+2, y+0, z+-3, block2,4,2);
			world.setBlock(x+2, y+0, z+-2, block3,0,2);
			world.setBlock(x+2, y+0, z+-1, block2,4,2);
			world.setBlock(x+2, y+0, z+0, block2,4,2);
			world.setBlock(x+2, y+1, z+-8, block2,4,2);
			world.setBlock(x+2, y+1, z+-7, block2,4,2);
			world.setBlock(x+2, y+1, z+-6, block3,0,2);
			world.setBlock(x+2, y+1, z+-5, block2,4,2);
			world.setBlock(x+2, y+1, z+-4, block2,4,2);
			world.setBlock(x+2, y+1, z+-3, block2,4,2);
			world.setBlock(x+2, y+1, z+-2, block3,0,2);
			world.setBlock(x+2, y+1, z+-1, block2,4,2);
			world.setBlock(x+2, y+1, z+0, block2,4,2);
			world.setBlock(x+2, y+2, z+-8, block1,1,2);
			world.setBlock(x+2, y+2, z+-7, block1,1,2);
			world.setBlock(x+2, y+2, z+-6, block1,1,2);
			world.setBlock(x+2, y+2, z+-5, block1,1,2);
			world.setBlock(x+2, y+2, z+-4, block1,1,2);
			world.setBlock(x+2, y+2, z+-3, block1,1,2);
			world.setBlock(x+2, y+2, z+-2, block1,1,2);
			world.setBlock(x+2, y+2, z+-1, block1,1,2);
			world.setBlock(x+2, y+2, z+0, block1,1,2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(),x, y-3, z-4, 0,x,y,z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x+1, y, z-4, 0, x, y-3, z-4);
			
		}else if (dir == ForgeDirection.SOUTH)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x+-2, y+-2, z+0, block1,4,2);
			world.setBlock(x+-2, y+-2, z+1, block1,4,2);
			world.setBlock(x+-2, y+-2, z+2, block1,4,2);
			world.setBlock(x+-2, y+-2, z+3, block1,4,2);
			world.setBlock(x+-2, y+-2, z+4, block1,4,2);
			world.setBlock(x+-2, y+-2, z+5, block1,4,2);
			world.setBlock(x+-2, y+-2, z+6, block1,4,2);
			world.setBlock(x+-2, y+-2, z+7, block1,4,2);
			world.setBlock(x+-2, y+-2, z+8, block1,4,2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x+-2, y+-1, z+0, block2,4,2);
			world.setBlock(x+-2, y+-1, z+1, block2,4,2);
			Block block3 = GCBlocks.airLockFrame;
			world.setBlock(x+-2, y+-1, z+2, block3,0,2);
			world.setBlock(x+-2, y+-1, z+3, block2,4,2);
			world.setBlock(x+-2, y+-1, z+4, block2,4,2);
			world.setBlock(x+-2, y+-1, z+5, block2,4,2);
			world.setBlock(x+-2, y+-1, z+6, block3,0,2);
			world.setBlock(x+-2, y+-1, z+7, block2,4,2);
			world.setBlock(x+-2, y+-1, z+8, block2,4,2);
			world.setBlock(x+-2, y+0, z+0, block2,4,2);
			world.setBlock(x+-2, y+0, z+1, block2,4,2);
			world.setBlock(x+-2, y+0, z+2, block3,0,2);
			world.setBlock(x+-2, y+0, z+3, block2,4,2);
			world.setBlock(x+-2, y+0, z+4, block2,4,2);
			world.setBlock(x+-2, y+0, z+5, block2,4,2);
			world.setBlock(x+-2, y+0, z+6, block3,0,2);
			world.setBlock(x+-2, y+0, z+7, block2,4,2);
			world.setBlock(x+-2, y+0, z+8, block2,4,2);
			world.setBlock(x+-2, y+1, z+0, block2,4,2);
			world.setBlock(x+-2, y+1, z+1, block2,4,2);
			world.setBlock(x+-2, y+1, z+2, block3,0,2);
			world.setBlock(x+-2, y+1, z+3, block2,4,2);
			world.setBlock(x+-2, y+1, z+4, block2,4,2);
			world.setBlock(x+-2, y+1, z+5, block2,4,2);
			world.setBlock(x+-2, y+1, z+6, block3,0,2);
			world.setBlock(x+-2, y+1, z+7, block2,4,2);
			world.setBlock(x+-2, y+1, z+8, block2,4,2);
			world.setBlock(x+-2, y+2, z+0, block1,0,2);
			world.setBlock(x+-2, y+2, z+1, block1,0,2);
			world.setBlock(x+-2, y+2, z+2, block1,0,2);
			world.setBlock(x+-2, y+2, z+3, block1,0,2);
			world.setBlock(x+-2, y+2, z+4, block1,0,2);
			world.setBlock(x+-2, y+2, z+5, block1,0,2);
			world.setBlock(x+-2, y+2, z+6, block1,0,2);
			world.setBlock(x+-2, y+2, z+7, block1,0,2);
			world.setBlock(x+-2, y+2, z+8, block1,0,2);
			world.setBlock(x+-1, y+-2, z+0, block2,4,2);
			world.setBlock(x+-1, y+-2, z+1, block2,4,2);
			world.setBlock(x+-1, y+-2, z+2, block3,0,2);
			world.setBlock(x+-1, y+-2, z+3, block2,4,2);
			world.setBlock(x+-1, y+-2, z+4, block2,4,2);
			world.setBlock(x+-1, y+-2, z+5, block2,4,2);
			world.setBlock(x+-1, y+-2, z+6, block3,0,2);
			world.setBlock(x+-1, y+-2, z+7, block2,4,2);
			world.setBlock(x+-1, y+-2, z+8, block2,4,2);
			world.setBlock(x+-1, y+2, z+0, block2,4,2);
			world.setBlock(x+-1, y+2, z+1, block2,4,2);
			world.setBlock(x+-1, y+2, z+2, block3,0,2);
			world.setBlock(x+-1, y+2, z+3, block2,4,2);
			world.setBlock(x+-1, y+2, z+4, block2,4,2);
			world.setBlock(x+-1, y+2, z+5, block2,4,2);
			world.setBlock(x+-1, y+2, z+6, block3,0,2);
			world.setBlock(x+-1, y+2, z+7, block2,4,2);
			world.setBlock(x+-1, y+2, z+8, block2,4,2);
			world.setBlock(x+0, y+-2, z+0, block2,4,2);
			world.setBlock(x+0, y+-2, z+1, block2,4,2);
			setAirLock(world, x+0, y+-2, z+2, 1, 2, OW);
		//--	world.setBlock(x+0, y+-2, z+2, block3,1,2);
			world.setBlock(x+0, y+-2, z+3, block2,4,2);
			world.setBlock(x+0, y+-2, z+4, block2,4,2);
			world.setBlock(x+0, y+-2, z+5, block2,4,2);
			setAirLock(world, x+0, y+-2, z+6, 1, 2, OW);
		//--	world.setBlock(x+0, y+-2, z+6, block3,1,2);
			world.setBlock(x+0, y+-2, z+7, block2,4,2);
			world.setBlock(x+0, y+-2, z+8, block2,4,2);
			world.setBlock(x+0, y+2, z+0, block2,4,2);
			Block block4= Blocks.glowstone;
			world.setBlock(x+0, y+2, z+1, block4,0,2);
			world.setBlock(x+0, y+2, z+2, block3,0,2);
			world.setBlock(x+0, y+2, z+3, block2,4,2);
			world.setBlock(x+0, y+2, z+4, block4,0,2);
			world.setBlock(x+0, y+2, z+5, block2,4,2);
			world.setBlock(x+0, y+2, z+6, block3,0,2);
			world.setBlock(x+0, y+2, z+7, block4,0,2);
			world.setBlock(x+0, y+2, z+8, block2,4,2);
			world.setBlock(x+1, y+-2, z+0, block2,4,2);
			world.setBlock(x+1, y+-2, z+1, block2,4,2);
			world.setBlock(x+1, y+-2, z+2, block3,0,2);
			world.setBlock(x+1, y+-2, z+3, block2,4,2);
			world.setBlock(x+1, y+-2, z+4, block2,4,2);
			world.setBlock(x+1, y+-2, z+5, block2,4,2);
			world.setBlock(x+1, y+-2, z+6, block3,0,2);
			world.setBlock(x+1, y+-2, z+7, block2,4,2);
			world.setBlock(x+1, y+-2, z+8, block2,4,2);
			world.setBlock(x+1, y+2, z+0, block2,4,2);
			world.setBlock(x+1, y+2, z+1, block2,4,2);
			world.setBlock(x+1, y+2, z+2, block3,0,2);
			world.setBlock(x+1, y+2, z+3, block2,4,2);
			world.setBlock(x+1, y+2, z+4, block2,4,2);
			world.setBlock(x+1, y+2, z+5, block2,4,2);
			world.setBlock(x+1, y+2, z+6, block3,0,2);
			world.setBlock(x+1, y+2, z+7, block2,4,2);
			world.setBlock(x+1, y+2, z+8, block2,4,2);
			world.setBlock(x+2, y+-2, z+0, block1,5,2);
			world.setBlock(x+2, y+-2, z+1, block1,5,2);
			world.setBlock(x+2, y+-2, z+2, block1,5,2);
			world.setBlock(x+2, y+-2, z+3, block1,5,2);
			world.setBlock(x+2, y+-2, z+4, block1,5,2);
			world.setBlock(x+2, y+-2, z+5, block1,5,2);
			world.setBlock(x+2, y+-2, z+6, block1,5,2);
			world.setBlock(x+2, y+-2, z+7, block1,5,2);
			world.setBlock(x+2, y+-2, z+8, block1,5,2);
			world.setBlock(x+2, y+-1, z+0, block2,4,2);
			world.setBlock(x+2, y+-1, z+1, block2,4,2);
			world.setBlock(x+2, y+-1, z+2, block3,0,2);
			world.setBlock(x+2, y+-1, z+3, block2,4,2);
			world.setBlock(x+2, y+-1, z+4, block2,4,2);
			world.setBlock(x+2, y+-1, z+5, block2,4,2);
			world.setBlock(x+2, y+-1, z+6, block3,0,2);
			world.setBlock(x+2, y+-1, z+7, block2,4,2);
			world.setBlock(x+2, y+-1, z+8, block2,4,2);
			world.setBlock(x+2, y+0, z+0, block2,4,2);
			world.setBlock(x+2, y+0, z+1, block2,4,2);
			world.setBlock(x+2, y+0, z+2, block3,0,2);
			world.setBlock(x+2, y+0, z+3, block2,4,2);
			world.setBlock(x+2, y+0, z+4, block2,4,2);
			world.setBlock(x+2, y+0, z+5, block2,4,2);
			world.setBlock(x+2, y+0, z+6, block3,0,2);
			world.setBlock(x+2, y+0, z+7, block2,4,2);
			world.setBlock(x+2, y+0, z+8, block2,4,2);
			world.setBlock(x+2, y+1, z+0, block2,4,2);
			world.setBlock(x+2, y+1, z+1, block2,4,2);
			world.setBlock(x+2, y+1, z+2, block3,0,2);
			world.setBlock(x+2, y+1, z+3, block2,4,2);
			world.setBlock(x+2, y+1, z+4, block2,4,2);
			world.setBlock(x+2, y+1, z+5, block2,4,2);
			world.setBlock(x+2, y+1, z+6, block3,0,2);
			world.setBlock(x+2, y+1, z+7, block2,4,2);
			world.setBlock(x+2, y+1, z+8, block2,4,2);
			world.setBlock(x+2, y+2, z+0, block1,1,2);
			world.setBlock(x+2, y+2, z+1, block1,1,2);
			world.setBlock(x+2, y+2, z+2, block1,1,2);
			world.setBlock(x+2, y+2, z+3, block1,1,2);
			world.setBlock(x+2, y+2, z+4, block1,1,2);
			world.setBlock(x+2, y+2, z+5, block1,1,2);
			world.setBlock(x+2, y+2, z+6, block1,1,2);
			world.setBlock(x+2, y+2, z+7, block1,1,2);
			world.setBlock(x+2, y+2, z+8, block1,1,2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(),x, y-3, z+4, 0,x,y,z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x-1, y, z+4, 0, x, y-3, z+4);
			
		}else if (dir == ForgeDirection.WEST)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x+-8, y+-2, z+-2, block1,6,2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x+-8, y+-2, z+-1, block2,4,2);
			world.setBlock(x+-8, y+-2, z+0, block2,4,2);
			world.setBlock(x+-8, y+-2, z+1, block2,4,2);
			world.setBlock(x+-8, y+-2, z+2, block1,7,2);
			world.setBlock(x+-8, y+-1, z+-2, block2,4,2);
			world.setBlock(x+-8, y+-1, z+2, block2,4,2);
			world.setBlock(x+-8, y+0, z+-2, block2,4,2);
			world.setBlock(x+-8, y+0, z+2, block2,4,2);
			world.setBlock(x+-8, y+1, z+-2, block2,4,2);
			world.setBlock(x+-8, y+1, z+2, block2,4,2);
			world.setBlock(x+-8, y+2, z+-2, block1,2,2);
			world.setBlock(x+-8, y+2, z+-1, block2,4,2);
			world.setBlock(x+-8, y+2, z+0, block2,4,2);
			world.setBlock(x+-8, y+2, z+1, block2,4,2);
			world.setBlock(x+-8, y+2, z+2, block1,3,2);
			world.setBlock(x+-7, y+-2, z+-2, block1,6,2);
			world.setBlock(x+-7, y+-2, z+-1, block2,4,2);
			world.setBlock(x+-7, y+-2, z+0, block2,4,2);
			world.setBlock(x+-7, y+-2, z+1, block2,4,2);
			world.setBlock(x+-7, y+-2, z+2, block1,7,2);
			world.setBlock(x+-7, y+-1, z+-2, block2,4,2);
			world.setBlock(x+-7, y+-1, z+2, block2,4,2);
			world.setBlock(x+-7, y+0, z+-2, block2,4,2);
			world.setBlock(x+-7, y+0, z+2, block2,4,2);
			world.setBlock(x+-7, y+1, z+-2, block2,4,2);
			world.setBlock(x+-7, y+1, z+2, block2,4,2);
			world.setBlock(x+-7, y+2, z+-2, block1,2,2);
			world.setBlock(x+-7, y+2, z+-1, block2,4,2);
			Block block3= Blocks.glowstone;
			world.setBlock(x+-7, y+2, z+0, block3,0,2);
			world.setBlock(x+-7, y+2, z+1, block2,4,2);
			world.setBlock(x+-7, y+2, z+2, block1,3,2);
			world.setBlock(x+-6, y+-2, z+-2, block1,6,2);
		Block block4 = GCBlocks.airLockFrame;
			world.setBlock(x+-6, y+-2, z+-1, block4,0,2);
			setAirLock(world, x-6, y-2, z, 1, 2, OW);
		//--	world.setBlock(x+-6, y+-2, z+0, block4,1,2);
			world.setBlock(x+-6, y+-2, z+1, block4,0,2);
			world.setBlock(x+-6, y+-2, z+2, block1,7,2);
			world.setBlock(x+-6, y+-1, z+-2, block4,0,2);
			world.setBlock(x+-6, y+-1, z+2, block4,0,2);
			world.setBlock(x+-6, y+0, z+-2, block4,0,2);
			world.setBlock(x+-6, y+0, z+2, block4,0,2);
			world.setBlock(x+-6, y+1, z+-2, block4,0,2);
			world.setBlock(x+-6, y+1, z+2, block4,0,2);
		//	setAirLock(world, x-6, y+2, z+-2, 1, 2, OW);
			world.setBlock(x+-6, y+2, z+-2, block1,2,2);
			world.setBlock(x+-6, y+2, z+-1, block4,0,2);
			world.setBlock(x+-6, y+2, z+0, block4,0,2);
			world.setBlock(x+-6, y+2, z+1, block4,0,2);
		//	setAirLock(world, x-6, y+2, z+2, 1, 2, OW);
			world.setBlock(x+-6, y+2, z+2, block1,3,2);
			world.setBlock(x+-5, y+-2, z+-2, block1,6,2);
			world.setBlock(x+-5, y+-2, z+-1, block2,4,2);
			world.setBlock(x+-5, y+-2, z+0, block2,4,2);
			world.setBlock(x+-5, y+-2, z+1, block2,4,2);
			world.setBlock(x+-5, y+-2, z+2, block1,7,2);
			world.setBlock(x+-5, y+-1, z+-2, block2,4,2);
			world.setBlock(x+-5, y+-1, z+2, block2,4,2);
			world.setBlock(x+-5, y+0, z+-2, block2,4,2);
			world.setBlock(x+-5, y+0, z+2, block2,4,2);
			world.setBlock(x+-5, y+1, z+-2, block2,4,2);
			world.setBlock(x+-5, y+1, z+2, block2,4,2);
			world.setBlock(x+-5, y+2, z+-2, block1,2,2);
			world.setBlock(x+-5, y+2, z+-1, block2,4,2);
			world.setBlock(x+-5, y+2, z+0, block2,4,2);
			world.setBlock(x+-5, y+2, z+1, block2,4,2);
			world.setBlock(x+-5, y+2, z+2, block1,3,2);
			world.setBlock(x+-4, y+-2, z+-2, block1,6,2);
			world.setBlock(x+-4, y+-2, z+-1, block2,4,2);
			world.setBlock(x+-4, y+-2, z+0, block2,4,2);
			world.setBlock(x+-4, y+-2, z+1, block2,4,2);
			world.setBlock(x+-4, y+-2, z+2, block1,7,2);
			world.setBlock(x+-4, y+-1, z+-2, block2,4,2);
			world.setBlock(x+-4, y+-1, z+2, block2,4,2);
			world.setBlock(x+-4, y+0, z+-2, block2,4,2);
			world.setBlock(x+-4, y+0, z+2, block2,4,2);
			world.setBlock(x+-4, y+1, z+-2, block2,4,2);
			world.setBlock(x+-4, y+1, z+2, block2,4,2);
			world.setBlock(x+-4, y+2, z+-2, block1,2,2);
			world.setBlock(x+-4, y+2, z+-1, block2,4,2);
			world.setBlock(x+-4, y+2, z+0, block3,0,2);
			world.setBlock(x+-4, y+2, z+1, block2,4,2);
			world.setBlock(x+-4, y+2, z+2, block1,3,2);
			world.setBlock(x+-3, y+-2, z+-2, block1,6,2);
			world.setBlock(x+-3, y+-2, z+-1, block2,4,2);
			world.setBlock(x+-3, y+-2, z+0, block2,4,2);
			world.setBlock(x+-3, y+-2, z+1, block2,4,2);
			world.setBlock(x+-3, y+-2, z+2, block1,7,2);
			world.setBlock(x+-3, y+-1, z+-2, block2,4,2);
			world.setBlock(x+-3, y+-1, z+2, block2,4,2);
			world.setBlock(x+-3, y+0, z+-2, block2,4,2);
			world.setBlock(x+-3, y+0, z+2, block2,4,2);
			world.setBlock(x+-3, y+1, z+-2, block2,4,2);
			world.setBlock(x+-3, y+1, z+2, block2,4,2);
			world.setBlock(x+-3, y+2, z+-2, block1,2,2);
			world.setBlock(x+-3, y+2, z+-1, block2,4,2);
			world.setBlock(x+-3, y+2, z+0, block2,4,2);
			world.setBlock(x+-3, y+2, z+1, block2,4,2);
			world.setBlock(x+-3, y+2, z+2, block1,3,2);
			world.setBlock(x+-2, y+-2, z+-2, block1,6,2);
			world.setBlock(x+-2, y+-2, z+-1, block4,0,2);
			setAirLock(world, x-2, y-2, z, 1, 2, OW);
		//--	world.setBlock(x+-2, y+-2, z+0, block4,1,2);
			world.setBlock(x+-2, y+-2, z+1, block4,0,2);
			world.setBlock(x+-2, y+-2, z+2, block1,7,2);
			world.setBlock(x+-2, y+-1, z+-2, block4,0,2);
			world.setBlock(x+-2, y+-1, z+2, block4,0,2);
			world.setBlock(x+-2, y+0, z+-2, block4,0,2);
			world.setBlock(x+-2, y+0, z+2, block4,0,2);
			world.setBlock(x+-2, y+1, z+-2, block4,0,2);
			world.setBlock(x+-2, y+1, z+2, block4,0,2);
			world.setBlock(x+-2, y+2, z+-2, block1,2,2);
			world.setBlock(x+-2, y+2, z+-1, block4,0,2);
			world.setBlock(x+-2, y+2, z+0, block4,0,2);
			world.setBlock(x+-2, y+2, z+1, block4,0,2);
			world.setBlock(x+-2, y+2, z+2, block1,3,2);
			world.setBlock(x+-1, y+-2, z+-2, block1,6,2);
			world.setBlock(x+-1, y+-2, z+-1, block2,4,2);
			world.setBlock(x+-1, y+-2, z+0, block2,4,2);
			world.setBlock(x+-1, y+-2, z+1, block2,4,2);
			world.setBlock(x+-1, y+-2, z+2, block1,7,2);
			world.setBlock(x+-1, y+-1, z+-2, block2,4,2);
			world.setBlock(x+-1, y+-1, z+2, block2,4,2);
			world.setBlock(x+-1, y+0, z+-2, block2,4,2);
			world.setBlock(x+-1, y+0, z+2, block2,4,2);
			world.setBlock(x+-1, y+1, z+-2, block2,4,2);
			world.setBlock(x+-1, y+1, z+2, block2,4,2);
			world.setBlock(x+-1, y+2, z+-2, block1,2,2);
			world.setBlock(x+-1, y+2, z+-1, block2,4,2);
			world.setBlock(x+-1, y+2, z+0, block3,0,2);
			world.setBlock(x+-1, y+2, z+1, block2,4,2);
			world.setBlock(x+-1, y+2, z+2, block1,3,2);
			world.setBlock(x+0, y+-2, z+-2, block1,6,2);
			world.setBlock(x+0, y+-2, z+-1, block2,4,2);
			world.setBlock(x+0, y+-2, z+0, block2,4,2);
			world.setBlock(x+0, y+-2, z+1, block2,4,2);
			world.setBlock(x+0, y+-2, z+2, block1,7,2);
			world.setBlock(x+0, y+-1, z+-2, block2,4,2);
			world.setBlock(x+0, y+-1, z+2, block2,4,2);
			world.setBlock(x+0, y+0, z+-2, block2,4,2);
			world.setBlock(x+0, y+0, z+2, block2,4,2);
			world.setBlock(x+0, y+1, z+-2, block2,4,2);
			world.setBlock(x+0, y+1, z+2, block2,4,2);
			world.setBlock(x+0, y+2, z+-2, block1,2,2);
			world.setBlock(x+0, y+2, z+-1, block2,4,2);
			world.setBlock(x+0, y+2, z+0, block2,4,2);
			world.setBlock(x+0, y+2, z+1, block2,4,2);
			world.setBlock(x+0, y+2, z+2, block1,3,2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(),x-4, y-3, z, 0,x,y,z);

			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x-4, y, z-1, 0, x-4, y-3, z);

		}else if (dir == ForgeDirection.EAST)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x+0, y+-2, z+-2, block1,6,2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x+0, y+-2, z+-1, block2,4,2);
			world.setBlock(x+0, y+-2, z+0, block2,4,2);
			world.setBlock(x+0, y+-2, z+1, block2,4,2);
			world.setBlock(x+0, y+-2, z+2, block1,7,2);
			world.setBlock(x+0, y+-1, z+-2, block2,4,2);
			world.setBlock(x+0, y+-1, z+2, block2,4,2);
			world.setBlock(x+0, y+0, z+-2, block2,4,2);
			world.setBlock(x+0, y+0, z+2, block2,4,2);
			world.setBlock(x+0, y+1, z+-2, block2,4,2);
			world.setBlock(x+0, y+1, z+2, block2,4,2);
			world.setBlock(x+0, y+2, z+-2, block1,2,2);
			world.setBlock(x+0, y+2, z+-1, block2,4,2);
			world.setBlock(x+0, y+2, z+0, block2,4,2);
			world.setBlock(x+0, y+2, z+1, block2,4,2);
			world.setBlock(x+0, y+2, z+2, block1,3,2);
			world.setBlock(x+1, y+-2, z+-2, block1,6,2);
			world.setBlock(x+1, y+-2, z+-1, block2,4,2);
			world.setBlock(x+1, y+-2, z+0, block2,4,2);
			world.setBlock(x+1, y+-2, z+1, block2,4,2);
			world.setBlock(x+1, y+-2, z+2, block1,7,2);
			world.setBlock(x+1, y+-1, z+-2, block2,4,2);
			world.setBlock(x+1, y+-1, z+2, block2,4,2);
			world.setBlock(x+1, y+0, z+-2, block2,4,2);
			world.setBlock(x+1, y+0, z+2, block2,4,2);
			world.setBlock(x+1, y+1, z+-2, block2,4,2);
			world.setBlock(x+1, y+1, z+2, block2,4,2);
			world.setBlock(x+1, y+2, z+-2, block1,2,2);
			world.setBlock(x+1, y+2, z+-1, block2,4,2);
			Block block3= Blocks.glowstone;
			world.setBlock(x+1, y+2, z+0, block3,0,2);
			world.setBlock(x+1, y+2, z+1, block2,4,2);
			world.setBlock(x+1, y+2, z+2, block1,3,2);
			world.setBlock(x+2, y+-2, z+-2, block1,6,2);
			Block block4 = GCBlocks.airLockFrame;
			world.setBlock(x+2, y+-2, z+-1, block4,0,2);
			setAirLock(world, x+2, y-2, z, 1, 2, OW);
		//--	world.setBlock(x+2, y+-2, z+0, block4,1,2);
			world.setBlock(x+2, y+-2, z+1, block4,0,2);
			world.setBlock(x+2, y+-2, z+2, block1,7,2);
			world.setBlock(x+2, y+-1, z+-2, block4,0,2);
			world.setBlock(x+2, y+-1, z+2, block4,0,2);
			world.setBlock(x+2, y+0, z+-2, block4,0,2);
			world.setBlock(x+2, y+0, z+2, block4,0,2);
			world.setBlock(x+2, y+1, z+-2, block4,0,2);
			world.setBlock(x+2, y+1, z+2, block4,0,2);
		//	setAirLock(world, x+2, y+2, z+-2, 1, 2, OW);
			world.setBlock(x+2, y+2, z+-2, block1,2,2);
			world.setBlock(x+2, y+2, z+-1, block4,0,2);
			world.setBlock(x+2, y+2, z+0, block4,0,2);
			world.setBlock(x+2, y+2, z+1, block4,0,2);
		//	setAirLock(world, x+2, y+2, z+2, 1, 2, OW);
			world.setBlock(x+2, y+2, z+2, block1,3,2);
			world.setBlock(x+3, y+-2, z+-2, block1,6,2);
			world.setBlock(x+3, y+-2, z+-1, block2,4,2);
			world.setBlock(x+3, y+-2, z+0, block2,4,2);
			world.setBlock(x+3, y+-2, z+1, block2,4,2);
			world.setBlock(x+3, y+-2, z+2, block1,7,2);
			world.setBlock(x+3, y+-1, z+-2, block2,4,2);
			world.setBlock(x+3, y+-1, z+2, block2,4,2);
			world.setBlock(x+3, y+0, z+-2, block2,4,2);
			world.setBlock(x+3, y+0, z+2, block2,4,2);
			world.setBlock(x+3, y+1, z+-2, block2,4,2);
			world.setBlock(x+3, y+1, z+2, block2,4,2);
			world.setBlock(x+3, y+2, z+-2, block1,2,2);
			world.setBlock(x+3, y+2, z+-1, block2,4,2);
			world.setBlock(x+3, y+2, z+0, block2,4,2);
			world.setBlock(x+3, y+2, z+1, block2,4,2);
			world.setBlock(x+3, y+2, z+2, block1,3,2);
			world.setBlock(x+4, y+-2, z+-2, block1,6,2);
			world.setBlock(x+4, y+-2, z+-1, block2,4,2);
			world.setBlock(x+4, y+-2, z+0, block2,4,2);
			world.setBlock(x+4, y+-2, z+1, block2,4,2);
			world.setBlock(x+4, y+-2, z+2, block1,7,2);
			world.setBlock(x+4, y+-1, z+-2, block2,4,2);
			world.setBlock(x+4, y+-1, z+2, block2,4,2);
			world.setBlock(x+4, y+0, z+-2, block2,4,2);
			world.setBlock(x+4, y+0, z+2, block2,4,2);
			world.setBlock(x+4, y+1, z+-2, block2,4,2);
			world.setBlock(x+4, y+1, z+2, block2,4,2);
			world.setBlock(x+4, y+2, z+-2, block1,2,2);
			world.setBlock(x+4, y+2, z+-1, block2,4,2);
			world.setBlock(x+4, y+2, z+0, block3,0,2);
			world.setBlock(x+4, y+2, z+1, block2,4,2);
			world.setBlock(x+4, y+2, z+2, block1,3,2);
			world.setBlock(x+5, y+-2, z+-2, block1,6,2);
			world.setBlock(x+5, y+-2, z+-1, block2,4,2);
			world.setBlock(x+5, y+-2, z+0, block2,4,2);
			world.setBlock(x+5, y+-2, z+1, block2,4,2);
			world.setBlock(x+5, y+-2, z+2, block1,7,2);
			world.setBlock(x+5, y+-1, z+-2, block2,4,2);
			world.setBlock(x+5, y+-1, z+2, block2,4,2);
			world.setBlock(x+5, y+0, z+-2, block2,4,2);
			world.setBlock(x+5, y+0, z+2, block2,4,2);
			world.setBlock(x+5, y+1, z+-2, block2,4,2);
			world.setBlock(x+5, y+1, z+2, block2,4,2);
			world.setBlock(x+5, y+2, z+-2, block1,2,2);
			world.setBlock(x+5, y+2, z+-1, block2,4,2);
			world.setBlock(x+5, y+2, z+0, block2,4,2);
			world.setBlock(x+5, y+2, z+1, block2,4,2);
			world.setBlock(x+5, y+2, z+2, block1,3,2);
			world.setBlock(x+6, y+-2, z+-2, block1,6,2);
			world.setBlock(x+6, y+-2, z+-1, block4,0,2);
			setAirLock(world, x+6, y-2, z, 1, 2, OW);
		//--	world.setBlock(x+6, y+-2, z+0, block4,1,2);
			world.setBlock(x+6, y+-2, z+1, block4,0,2);
			world.setBlock(x+6, y+-2, z+2, block1,7,2);
			world.setBlock(x+6, y+-1, z+-2, block4,0,2);
			world.setBlock(x+6, y+-1, z+2, block4,0,2);
			world.setBlock(x+6, y+0, z+-2, block4,0,2);
			world.setBlock(x+6, y+0, z+2, block4,0,2);
			world.setBlock(x+6, y+1, z+-2, block4,0,2);
			world.setBlock(x+6, y+1, z+2, block4,0,2);
			world.setBlock(x+6, y+2, z+-2, block1,2,2);
			world.setBlock(x+6, y+2, z+-1, block4,0,2);
			world.setBlock(x+6, y+2, z+0, block4,0,2);
			world.setBlock(x+6, y+2, z+1, block4,0,2);
			world.setBlock(x+6, y+2, z+2, block1,3,2);
			world.setBlock(x+7, y+-2, z+-2, block1,6,2);
			world.setBlock(x+7, y+-2, z+-1, block2,4,2);
			world.setBlock(x+7, y+-2, z+0, block2,4,2);
			world.setBlock(x+7, y+-2, z+1, block2,4,2);
			world.setBlock(x+7, y+-2, z+2, block1,7,2);
			world.setBlock(x+7, y+-1, z+-2, block2,4,2);
			world.setBlock(x+7, y+-1, z+2, block2,4,2);
			world.setBlock(x+7, y+0, z+-2, block2,4,2);
			world.setBlock(x+7, y+0, z+2, block2,4,2);
			world.setBlock(x+7, y+1, z+-2, block2,4,2);
			world.setBlock(x+7, y+1, z+2, block2,4,2);
			world.setBlock(x+7, y+2, z+-2, block1,2,2);
			world.setBlock(x+7, y+2, z+-1, block2,4,2);
			world.setBlock(x+7, y+2, z+0, block3,0,2);
			world.setBlock(x+7, y+2, z+1, block2,4,2);
			world.setBlock(x+7, y+2, z+2, block1,3,2);
			world.setBlock(x+8, y+-2, z+-2, block1,6,2);
			world.setBlock(x+8, y+-2, z+-1, block2,4,2);
			world.setBlock(x+8, y+-2, z+0, block2,4,2);
			world.setBlock(x+8, y+-2, z+1, block2,4,2);
			world.setBlock(x+8, y+-2, z+2, block1,7,2);
			world.setBlock(x+8, y+-1, z+-2, block2,4,2);
			world.setBlock(x+8, y+-1, z+2, block2,4,2);
			world.setBlock(x+8, y+0, z+-2, block2,4,2);
			world.setBlock(x+8, y+0, z+2, block2,4,2);
			world.setBlock(x+8, y+1, z+-2, block2,4,2);
			world.setBlock(x+8, y+1, z+2, block2,4,2);
			world.setBlock(x+8, y+2, z+-2, block1,2,2);
			world.setBlock(x+8, y+2, z+-1, block2,4,2);
			world.setBlock(x+8, y+2, z+0, block2,4,2);
			world.setBlock(x+8, y+2, z+1, block2,4,2);
			world.setBlock(x+8, y+2, z+2, block1,3,2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(),x+5, y-3, z, 0,x,y,z);

			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x+4, y, z+1, 0, x+5, y-3, z);

		}
		
	}

	@Override
	public boolean Check(World world, ForgeDirection dir, int x, int y, int z, int meta)
	{
		if (meta != 0 && meta != 1 && meta != -1)
		{
			return false;
		}
		if (dir == ForgeDirection.WEST|| dir == ForgeDirection.EAST || dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH)
		return true;
		else
		return false;
	}

	@Override
	public void ClearWay(World world, ForgeDirection dir, int x, int y, int z) {

	}

	@Override
	public boolean isHidden() {
		return hidden;
	}

	@Override
	public String getName() {
		return StatCollector.translateToLocal("builder.airlock.name");
	}

	@Override
	public String getUnlocalizedName() {
		return "hallairlock";
	}

	@Override
	public List<OreDictItemStack> getRequiredItems() {
		List<OreDictItemStack> items = new ArrayList();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 48, 7),"plateTin"));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 24, ItemMod.scaffold_meta)));
		items.add(new OreDictItemStack(new ItemStack(Items.glowstone_dust, 12)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 3, 13)));
		
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 36, 8)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 24, 9),"plateSteel"));
		items.add(new OreDictItemStack(new ItemStack(GCItems.oxygenConcentrator, 6, 0)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.meteoricIronIngot, 4, 1)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.mainConnect = 1;
		data.addConnect = 0;
		data.specialFunc = StatCollector.translateToLocal("builder.side_info.funcs.airlock.name");
		return data;
	}

}
