
package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureStub extends Structure {
	
	private boolean hiddenS;
	
	public StructureStub(boolean hidden)
	{
		super(hidden);
		this.hiddenS = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureStub Nstr = new StructureStub(hiddenS);
		Nstr.Configure(placementPos, placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public void deconstruct(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.WEST)
		{// WEST
			Block Bblock = Blocks.air;
			Block Bpoint = Blocks.air;
			Block SHalf = Blocks.air;
			Block Stairs = Blocks.air;
			world.setBlock(x - 1, y - 2, z - 1, Stairs, 4, 2);
			world.setBlock(x - 1, y - 2, z + 0, Stairs, 4, 2);
			world.setBlock(x - 1, y - 2, z + 1, Stairs, 4, 2);
			world.setBlock(x - 1, y - 1, z - 1, Bblock, 4, 2);
			world.setBlock(x - 1, y - 1, z + 0, Bblock, 4, 2);
			world.setBlock(x - 1, y - 1, z + 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 0, z - 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 0, z + 0, Bpoint, 0, 2);
			world.setBlock(x - 1, y + 0, z + 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 1, z - 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 1, z + 0, Bblock, 4, 2);
			world.setBlock(x - 1, y + 1, z + 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 2, z - 1, Stairs, 0, 2);
			world.setBlock(x - 1, y + 2, z + 0, Stairs, 0, 2);
			world.setBlock(x - 1, y + 2, z + 1, Stairs, 0, 2);
			world.setBlock(x + 0, y - 1, z - 2, Bblock, 4, 2);
			world.setBlock(x + 0, y - 1, z - 1, SHalf, 0, 2);
			world.setBlock(x + 0, y - 1, z + 0, SHalf, 0, 2);
			world.setBlock(x + 0, y - 1, z + 1, SHalf, 0, 2);
			world.setBlock(x + 0, y - 2, z - 2, Stairs, 6, 2);
			world.setBlock(x + 0, y - 2, z - 1, Bblock, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, Bblock, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, Bblock, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, Stairs, 7, 2);
			world.setBlock(x + 0, y - 1, z + 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 0, z - 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 1, z - 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 1, z - 1, SHalf, 8, 2);
			world.setBlock(x + 0, y + 1, z + 0, SHalf, 8, 2);
			world.setBlock(x + 0, y + 1, z + 1, SHalf, 8, 2);
			world.setBlock(x + 0, y + 1, z + 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 2, z - 2, Stairs, 2, 2);
			world.setBlock(x + 0, y + 2, z - 1, Bblock, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, Bblock, 4, 2);
			world.setBlock(x + 0, y + 2, z + 1, Bblock, 4, 2);
			world.setBlock(x + 0, y + 2, z + 2, Stairs, 3, 2);
		} else if (dir == ForgeDirection.EAST)
		{
			Block block5 = Blocks.air;
			
			Block block6 = Blocks.air;
			
			Block block8 = Blocks.air;
			
			Block block9 = Blocks.air;
			
			world.setBlock(x + 0, y + -2, z + -2, block5, 6, 2);
			world.setBlock(x + 0, y + -2, z + -1, block6, 4, 2);
			world.setBlock(x + 0, y + -2, z + 0, block6, 4, 2);
			world.setBlock(x + 0, y + -2, z + 1, block6, 4, 2);
			world.setBlock(x + 0, y + -2, z + 2, block5, 7, 2);
			world.setBlock(x + 0, y + -1, z + -2, block6, 4, 2);
			world.setBlock(x + 0, y + -1, z + -1, block9, 0, 2);
			world.setBlock(x + 0, y + -1, z + 0, block9, 0, 2);
			world.setBlock(x + 0, y + -1, z + 1, block9, 0, 2);
			world.setBlock(x + 0, y + -1, z + 2, block6, 4, 2);
			world.setBlock(x + 0, y + 0, z + -2, block6, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block6, 4, 2);
			world.setBlock(x + 0, y + 1, z + -2, block6, 4, 2);
			world.setBlock(x + 0, y + 1, z + -1, block9, 8, 2);
			world.setBlock(x + 0, y + 1, z + 0, block9, 8, 2);
			world.setBlock(x + 0, y + 1, z + 1, block9, 8, 2);
			world.setBlock(x + 0, y + 1, z + 2, block6, 4, 2);
			world.setBlock(x + 0, y + 2, z + -2, block5, 2, 2);
			world.setBlock(x + 0, y + 2, z + -1, block6, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block6, 4, 2);
			world.setBlock(x + 0, y + 2, z + 1, block6, 4, 2);
			world.setBlock(x + 0, y + 2, z + 2, block5, 3, 2);
			world.setBlock(x + 1, y + -2, z + -1, block5, 5, 2);
			world.setBlock(x + 1, y + -2, z + 0, block5, 5, 2);
			world.setBlock(x + 1, y + -2, z + 1, block5, 5, 2);
			world.setBlock(x + 1, y + -1, z + -1, block6, 4, 2);
			world.setBlock(x + 1, y + -1, z + 0, block6, 4, 2);
			world.setBlock(x + 1, y + -1, z + 1, block6, 4, 2);
			world.setBlock(x + 1, y + 0, z + -1, block6, 4, 2);
			world.setBlock(x + 1, y + 0, z + 0, block8, 0, 2);
			world.setBlock(x + 1, y + 0, z + 1, block6, 4, 2);
			world.setBlock(x + 1, y + 1, z + -1, block6, 4, 2);
			world.setBlock(x + 1, y + 1, z + 0, block6, 4, 2);
			world.setBlock(x + 1, y + 1, z + 1, block6, 4, 2);
			world.setBlock(x + 1, y + 2, z + -1, block5, 1, 2);
			world.setBlock(x + 1, y + 2, z + 0, block5, 1, 2);
			world.setBlock(x + 1, y + 2, z + 1, block5, 1, 2);
			
		} else if (dir == ForgeDirection.SOUTH)
		{
			Block block2 = Blocks.air;
			world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
			Block block3 = Blocks.air;
			world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
			world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 1, block2, 7, 2);
			Block block4 = Blocks.air;
			world.setBlock(x + -1, y + -1, z + 0, block4, 0, 2);
			world.setBlock(x + -1, y + -1, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + 0, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + 1, z + 0, block4, 8, 2);
			world.setBlock(x + -1, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 1, block2, 3, 2);
			world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 1, block2, 7, 2);
			world.setBlock(x + 0, y + -1, z + 0, block4, 0, 2);
			world.setBlock(x + 0, y + -1, z + 1, block3, 4, 2);
			Block block5 = Blocks.air;
			world.setBlock(x + 0, y + 0, z + 1, block5, 0, 2);
			world.setBlock(x + 0, y + 1, z + 0, block4, 8, 2);
			world.setBlock(x + 0, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 1, block2, 3, 2);
			world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 1, block2, 7, 2);
			world.setBlock(x + 1, y + -1, z + 0, block4, 0, 2);
			world.setBlock(x + 1, y + -1, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 0, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 1, z + 0, block4, 8, 2);
			world.setBlock(x + 1, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 1, block2, 3, 2);
			world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
			world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
		} else if (dir == ForgeDirection.NORTH)
		{
			Block block1 = Blocks.air;
			world.setBlock(x + -2, y + -2, z + 0, block1, 4, 2);
			Block block2 = Blocks.air;
			world.setBlock(x + -2, y + -1, z + 0, block2, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + -2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + -2, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + -2, z + -1, block1, 6, 2);
			world.setBlock(x + -1, y + -2, z + 0, block2, 4, 2);
			world.setBlock(x + -1, y + -1, z + -1, block2, 4, 2);
			Block block3 = Blocks.air;
			world.setBlock(x + -1, y + -1, z + 0, block3, 0, 2);
			world.setBlock(x + -1, y + 0, z + -1, block2, 4, 2);
			world.setBlock(x + -1, y + 1, z + -1, block2, 4, 2);
			world.setBlock(x + -1, y + 1, z + 0, block3, 8, 2);
			world.setBlock(x + -1, y + 2, z + -1, block1, 2, 2);
			world.setBlock(x + -1, y + 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y + -2, z + -1, block1, 6, 2);
			world.setBlock(x + 0, y + -2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y + -1, z + -1, block2, 4, 2);
			world.setBlock(x + 0, y + -1, z + 0, block3, 0, 2);
			Block block4 = Blocks.air;
			world.setBlock(x + 0, y + 0, z + -1, block4, 0, 2);
			world.setBlock(x + 0, y + 1, z + -1, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 0, block3, 8, 2);
			world.setBlock(x + 0, y + 2, z + -1, block1, 2, 2);
			world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + -2, z + -1, block1, 6, 2);
			world.setBlock(x + 1, y + -2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + -1, z + -1, block2, 4, 2);
			world.setBlock(x + 1, y + -1, z + 0, block3, 0, 2);
			world.setBlock(x + 1, y + 0, z + -1, block2, 4, 2);
			world.setBlock(x + 1, y + 1, z + -1, block2, 4, 2);
			world.setBlock(x + 1, y + 1, z + 0, block3, 8, 2);
			world.setBlock(x + 1, y + 2, z + -1, block1, 2, 2);
			world.setBlock(x + 1, y + 2, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + -2, z + 0, block1, 5, 2);
			world.setBlock(x + 2, y + -1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block1, 1, 2);
		}
		
	}
	
	@Override
	public void Build(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.WEST)
		{// WEST
			Block Bblock = GCBlocks.basicBlock;
			
			Block SHalf = GCBlocks.slabGCHalf;
			Block Stairs = GCBlocks.tinStairs1;
			world.setBlock(x - 1, y - 2, z - 1, Stairs, 4, 2);
			world.setBlock(x - 1, y - 2, z + 0, Stairs, 4, 2);
			world.setBlock(x - 1, y - 2, z + 1, Stairs, 4, 2);
			world.setBlock(x - 1, y - 1, z - 1, Bblock, 4, 2);
			world.setBlock(x - 1, y - 1, z + 0, Bblock, 4, 2);
			world.setBlock(x - 1, y - 1, z + 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 0, z - 1, Bblock, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x - 1, y, z, 1);
			world.setBlock(x - 1, y + 0, z + 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 1, z - 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 1, z + 0, Bblock, 4, 2);
			world.setBlock(x - 1, y + 1, z + 1, Bblock, 4, 2);
			world.setBlock(x - 1, y + 2, z - 1, Stairs, 0, 2);
			world.setBlock(x - 1, y + 2, z + 0, Stairs, 0, 2);
			world.setBlock(x - 1, y + 2, z + 1, Stairs, 0, 2);
			world.setBlock(x + 0, y - 1, z - 2, Bblock, 4, 2);
			world.setBlock(x + 0, y - 1, z - 1, SHalf, 0, 2);
			world.setBlock(x + 0, y - 1, z + 0, SHalf, 0, 2);
			world.setBlock(x + 0, y - 1, z + 1, SHalf, 0, 2);
			world.setBlock(x + 0, y - 2, z - 2, Stairs, 6, 2);
			world.setBlock(x + 0, y - 2, z - 1, Bblock, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, Bblock, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, Bblock, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, Stairs, 7, 2);
			world.setBlock(x + 0, y - 1, z + 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 0, z - 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 1, z - 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 1, z - 1, SHalf, 8, 2);
			world.setBlock(x + 0, y + 1, z + 0, SHalf, 8, 2);
			world.setBlock(x + 0, y + 1, z + 1, SHalf, 8, 2);
			world.setBlock(x + 0, y + 1, z + 2, Bblock, 4, 2);
			world.setBlock(x + 0, y + 2, z - 2, Stairs, 2, 2);
			world.setBlock(x + 0, y + 2, z - 1, Bblock, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, Bblock, 4, 2);
			world.setBlock(x + 0, y + 2, z + 1, Bblock, 4, 2);
			world.setBlock(x + 0, y + 2, z + 2, Stairs, 3, 2);
		} else if (dir == ForgeDirection.EAST)
		{
			Block block5 = GCBlocks.tinStairs1;
			
			Block block6 = GCBlocks.basicBlock;
			
			Block block9 = GCBlocks.slabGCHalf;
			
			world.setBlock(x + 0, y + -2, z + -2, block5, 6, 2);
			world.setBlock(x + 0, y + -2, z + -1, block6, 4, 2);
			world.setBlock(x + 0, y + -2, z + 0, block6, 4, 2);
			world.setBlock(x + 0, y + -2, z + 1, block6, 4, 2);
			world.setBlock(x + 0, y + -2, z + 2, block5, 7, 2);
			world.setBlock(x + 0, y + -1, z + -2, block6, 4, 2);
			world.setBlock(x + 0, y + -1, z + -1, block9, 0, 2);
			world.setBlock(x + 0, y + -1, z + 0, block9, 0, 2);
			world.setBlock(x + 0, y + -1, z + 1, block9, 0, 2);
			world.setBlock(x + 0, y + -1, z + 2, block6, 4, 2);
			world.setBlock(x + 0, y + 0, z + -2, block6, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block6, 4, 2);
			world.setBlock(x + 0, y + 1, z + -2, block6, 4, 2);
			world.setBlock(x + 0, y + 1, z + -1, block9, 8, 2);
			world.setBlock(x + 0, y + 1, z + 0, block9, 8, 2);
			world.setBlock(x + 0, y + 1, z + 1, block9, 8, 2);
			world.setBlock(x + 0, y + 1, z + 2, block6, 4, 2);
			world.setBlock(x + 0, y + 2, z + -2, block5, 2, 2);
			world.setBlock(x + 0, y + 2, z + -1, block6, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block6, 4, 2);
			world.setBlock(x + 0, y + 2, z + 1, block6, 4, 2);
			world.setBlock(x + 0, y + 2, z + 2, block5, 3, 2);
			world.setBlock(x + 1, y + -2, z + -1, block5, 5, 2);
			world.setBlock(x + 1, y + -2, z + 0, block5, 5, 2);
			world.setBlock(x + 1, y + -2, z + 1, block5, 5, 2);
			world.setBlock(x + 1, y + -1, z + -1, block6, 4, 2);
			world.setBlock(x + 1, y + -1, z + 0, block6, 4, 2);
			world.setBlock(x + 1, y + -1, z + 1, block6, 4, 2);
			world.setBlock(x + 1, y + 0, z + -1, block6, 4, 2);
			BuildHandler.buildBuildPoint(world, x + 1, y, z, 1);
			world.setBlock(x + 1, y + 0, z + 1, block6, 4, 2);
			world.setBlock(x + 1, y + 1, z + -1, block6, 4, 2);
			world.setBlock(x + 1, y + 1, z + 0, block6, 4, 2);
			world.setBlock(x + 1, y + 1, z + 1, block6, 4, 2);
			world.setBlock(x + 1, y + 2, z + -1, block5, 1, 2);
			world.setBlock(x + 1, y + 2, z + 0, block5, 1, 2);
			world.setBlock(x + 1, y + 2, z + 1, block5, 1, 2);
			
		} else if (dir == ForgeDirection.SOUTH)
		{
			Block block2 = GCBlocks.tinStairs1;
			world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
			Block block3 = GCBlocks.basicBlock;
			world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
			world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 1, block2, 7, 2);
			Block block4 = GCBlocks.slabGCHalf;
			world.setBlock(x + -1, y + -1, z + 0, block4, 0, 2);
			world.setBlock(x + -1, y + -1, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + 0, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + 1, z + 0, block4, 8, 2);
			world.setBlock(x + -1, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 1, block2, 3, 2);
			world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 1, block2, 7, 2);
			world.setBlock(x + 0, y + -1, z + 0, block4, 0, 2);
			world.setBlock(x + 0, y + -1, z + 1, block3, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z + 1, 1);
			world.setBlock(x + 0, y + 1, z + 0, block4, 8, 2);
			world.setBlock(x + 0, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 1, block2, 3, 2);
			world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 1, block2, 7, 2);
			world.setBlock(x + 1, y + -1, z + 0, block4, 0, 2);
			world.setBlock(x + 1, y + -1, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 0, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 1, z + 0, block4, 8, 2);
			world.setBlock(x + 1, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 1, block2, 3, 2);
			world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
			world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
		} else if (dir == ForgeDirection.NORTH)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x + -2, y + -2, z + 0, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x + -2, y + -1, z + 0, block2, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + -2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + -2, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + -2, z + -1, block1, 6, 2);
			world.setBlock(x + -1, y + -2, z + 0, block2, 4, 2);
			world.setBlock(x + -1, y + -1, z + -1, block2, 4, 2);
			Block block3 = GCBlocks.slabGCHalf;
			world.setBlock(x + -1, y + -1, z + 0, block3, 0, 2);
			world.setBlock(x + -1, y + 0, z + -1, block2, 4, 2);
			world.setBlock(x + -1, y + 1, z + -1, block2, 4, 2);
			world.setBlock(x + -1, y + 1, z + 0, block3, 8, 2);
			world.setBlock(x + -1, y + 2, z + -1, block1, 2, 2);
			world.setBlock(x + -1, y + 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y + -2, z + -1, block1, 6, 2);
			world.setBlock(x + 0, y + -2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y + -1, z + -1, block2, 4, 2);
			world.setBlock(x + 0, y + -1, z + 0, block3, 0, 2);
			BuildHandler.buildBuildPoint(world, x, y, z - 1, 1);
			world.setBlock(x + 0, y + 1, z + -1, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 0, block3, 8, 2);
			world.setBlock(x + 0, y + 2, z + -1, block1, 2, 2);
			world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + -2, z + -1, block1, 6, 2);
			world.setBlock(x + 1, y + -2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + -1, z + -1, block2, 4, 2);
			world.setBlock(x + 1, y + -1, z + 0, block3, 0, 2);
			world.setBlock(x + 1, y + 0, z + -1, block2, 4, 2);
			world.setBlock(x + 1, y + 1, z + -1, block2, 4, 2);
			world.setBlock(x + 1, y + 1, z + 0, block3, 8, 2);
			world.setBlock(x + 1, y + 2, z + -1, block1, 2, 2);
			world.setBlock(x + 1, y + 2, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + -2, z + 0, block1, 5, 2);
			world.setBlock(x + 2, y + -1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block1, 1, 2);
		}
		
	}
	
	@Override
	public void ClearWay(World world, ForgeDirection dir, int x, int y, int z)
	{}
	
	@Override
	public boolean Check(World world, ForgeDirection dir, int x, int y, int z, int meta)
	{
		if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST || dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH)
			return true;
		else
			return false;
	}
	
	@Override
	public String getName()
	{
		return "_Stub";
	}
	
	@Override
	public boolean isHidden()
	{
		return hiddenS;
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "stub";
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		return items;
	}
	
}
