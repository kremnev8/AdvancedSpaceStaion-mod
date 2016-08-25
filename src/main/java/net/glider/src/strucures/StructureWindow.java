
package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.glider.src.items.ItemMod;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureWindow extends StructureRotatable {
	
	private boolean hidden;
	/**
	 * 0 - normal,1 - on end
	 */
	private int rot;
	
	public StructureWindow(boolean hidden)
	{
		super(hidden);
		this.hidden = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureWindow Nstr = new StructureWindow(hidden);
		Nstr.Configure(placementPos, placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public void deconstruct(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (rot == 0)
		{
			if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST)
			{
				if (dir == ForgeDirection.EAST)
					x = x + 1;
				if (dir == ForgeDirection.WEST)
					x = x - 1;
				//w orld.setBlock(x+0, y+-3, z+-3, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + 0, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + -1, z + 0, block4, 0, 2);
				world.setBlock(x + 0, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + 0, z + 0, block4, 0, 2);
				world.setBlock(x + 0, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + 1, z + 0, block4, 0, 2);
				world.setBlock(x + 0, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				//	world.setBlock(x+0, y+3, z+3, block1,14,2);
				
			} else if (dir == ForgeDirection.SOUTH || dir == ForgeDirection.NORTH)
			{
				if (dir == ForgeDirection.NORTH)
					z = z - 1;
				if (dir == ForgeDirection.SOUTH)
					z = z + 1;
				//	world.setBlock(x+-3, y+-3, z+0, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -1, y + -1, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 0, block4, 0, 2);
				world.setBlock(x + 0, y + 0, z + 0, block4, 0, 2);
				world.setBlock(x + 0, y + 1, z + 0, block4, 0, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -1, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				//	world.setBlock(x+3, y+3, z+0, block1,14,2);
				
			}
		} else
		{
			if (dir == ForgeDirection.EAST)
			{
				Block block1 = Blocks.air;
				world.setBlock(x + 0, y + -2, z + -2, block1, 8, 2);
				Block block2 = Blocks.air;
				world.setBlock(x + 0, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block1, 8, 2);
				world.setBlock(x + 0, y + -1, z + -2, block2, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block2, 4, 2);
				world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block2, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block1, 0, 2);
				world.setBlock(x + 0, y + 2, z + -1, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block1, 0, 2);
				world.setBlock(x + 1, y + -2, z + -2, block1, 8, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + 1, y + -2, z + -1, block3, 5, 2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 5, 2);
				world.setBlock(x + 1, y + -2, z + 1, block3, 5, 2);
				world.setBlock(x + 1, y + -2, z + 2, block1, 8, 2);
				world.setBlock(x + 1, y + -1, z + -2, block2, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + 1, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + -1, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + -1, z + 2, block2, 4, 2);
				world.setBlock(x + 1, y + 0, z + -2, block2, 4, 2);
				world.setBlock(x + 1, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 2, block2, 4, 2);
				world.setBlock(x + 1, y + 1, z + -2, block2, 4, 2);
				world.setBlock(x + 1, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 2, block2, 4, 2);
				world.setBlock(x + 1, y + 2, z + -2, block1, 0, 2);
				world.setBlock(x + 1, y + 2, z + -1, block3, 1, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 1, 2);
				world.setBlock(x + 1, y + 2, z + 1, block3, 1, 2);
				world.setBlock(x + 1, y + 2, z + 2, block1, 0, 2);
				
			} else if (dir == ForgeDirection.WEST)
			{
				Block block1 = Blocks.air;
				world.setBlock(x + -1, y + -2, z + -2, block1, 8, 2);
				Block block2 = Blocks.air;
				world.setBlock(x + -1, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block1, 8, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -1, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -1, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + -1, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block1, 0, 2);
				world.setBlock(x + -1, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -1, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -1, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -1, y + 2, z + 2, block1, 0, 2);
				world.setBlock(x + 0, y + -2, z + -2, block1, 8, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block1, 8, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block1, 0, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block1, 0, 2);
				
			} else if (dir == ForgeDirection.SOUTH)
			{
				Block block1 = Blocks.air;
				world.setBlock(x + -2, y + -2, z + 0, block1, 8, 2);
				world.setBlock(x + -2, y + -2, z + 1, block1, 8, 2);
				Block block2 = Blocks.air;
				world.setBlock(x + -2, y + -1, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + -1, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 0, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 1, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block1, 0, 2);
				world.setBlock(x + -2, y + 2, z + 1, block1, 0, 2);
				world.setBlock(x + -1, y + -2, z + 0, block2, 4, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -1, y + -2, z + 1, block3, 7, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -1, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 3, 2);
				world.setBlock(x + 0, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 7, 2);
				world.setBlock(x + 0, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 3, 2);
				world.setBlock(x + 1, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y + -2, z + 1, block3, 7, 2);
				world.setBlock(x + 1, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y + 2, z + 1, block3, 3, 2);
				world.setBlock(x + 2, y + -2, z + 0, block1, 8, 2);
				world.setBlock(x + 2, y + -2, z + 1, block1, 8, 2);
				world.setBlock(x + 2, y + -1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + -1, z + 1, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + 1, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + 1, block2, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block1, 0, 2);
				world.setBlock(x + 2, y + 2, z + 1, block1, 0, 2);
				
			} else if (dir == ForgeDirection.NORTH)
			{
				Block block1 = Blocks.air;
				world.setBlock(x + -2, y + -2, z + -1, block1, 8, 2);
				world.setBlock(x + -2, y + -2, z + 0, block1, 8, 2);
				Block block2 = Blocks.air;
				world.setBlock(x + -2, y + -1, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + -1, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 0, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 1, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 2, z + -1, block1, 0, 2);
				world.setBlock(x + -2, y + 2, z + 0, block1, 0, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -1, y + -2, z + -1, block3, 6, 2);
				world.setBlock(x + -1, y + -2, z + 0, block2, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -1, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 2, 2);
				world.setBlock(x + -1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 6, 2);
				world.setBlock(x + 0, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 2, 2);
				world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y + -2, z + -1, block3, 6, 2);
				world.setBlock(x + 1, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 2, z + -1, block3, 2, 2);
				world.setBlock(x + 1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + -2, z + -1, block1, 8, 2);
				world.setBlock(x + 2, y + -2, z + 0, block1, 8, 2);
				world.setBlock(x + 2, y + -1, z + -1, block2, 4, 2);
				world.setBlock(x + 2, y + -1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + -1, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + -1, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 2, z + -1, block1, 0, 2);
				world.setBlock(x + 2, y + 2, z + 0, block1, 0, 2);
				
			}
		}
	}
	
	@Override
	public void Build(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (rot == 0)
		{
			if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST)
			{
				if (dir == ForgeDirection.EAST)
					x = x + 1;
				if (dir == ForgeDirection.WEST)
					x = x - 1;
				Block block1 = GCBlocks.tinStairs1;
				world.setBlock(x + 0, y - 2, z - 2, block1, 6, 2);
				Block block2 = GCBlocks.basicBlock;
				world.setBlock(x + 0, y - 2, z - 1, block2, 4, 2);
				world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y - 2, z + 1, block2, 4, 2);
				world.setBlock(x + 0, y - 2, z + 2, block1, 7, 2);
				world.setBlock(x + 0, y - 1, z - 2, block2, 4, 2);
				Block block3 = Blocks.glass;
				world.setBlock(x + 0, y - 1, z - 1, block3, 0, 2);
				world.setBlock(x + 0, y - 1, z + 0, block3, 0, 2);
				world.setBlock(x + 0, y - 1, z + 1, block3, 0, 2);
				world.setBlock(x + 0, y - 1, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 0, z - 2, block2, 4, 2);
				world.setBlock(x + 0, y + 0, z - 1, block3, 0, 2);
				world.setBlock(x + 0, y + 0, z + 0, block3, 0, 2);
				world.setBlock(x + 0, y + 0, z + 1, block3, 0, 2);
				world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 1, z - 2, block2, 4, 2);
				world.setBlock(x + 0, y + 1, z - 1, block3, 0, 2);
				world.setBlock(x + 0, y + 1, z + 0, block3, 0, 2);
				world.setBlock(x + 0, y + 1, z + 1, block3, 0, 2);
				world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z - 2, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z - 1, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 4, 2);
				
			} else if (dir == ForgeDirection.SOUTH || dir == ForgeDirection.NORTH)
			{
				if (dir == ForgeDirection.NORTH)
					z = z - 1;
				if (dir == ForgeDirection.SOUTH)
					z = z + 1;
				
				Block block1 = GCBlocks.tinStairs1;
				world.setBlock(x - 2, y - 2, z + 0, block1, 4, 2);
				Block block2 = GCBlocks.basicBlock;
				world.setBlock(x - 2, y - 1, z + 0, block2, 4, 2);
				world.setBlock(x - 2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x - 2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x - 2, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x - 1, y - 2, z + 0, block2, 4, 2);
				Block block3 = Blocks.glass;
				world.setBlock(x - 1, y - 1, z + 0, block3, 0, 2);
				world.setBlock(x - 1, y + 0, z + 0, block3, 0, 2);
				world.setBlock(x - 1, y + 1, z + 0, block3, 0, 2);
				world.setBlock(x - 1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y - 1, z + 0, block3, 0, 2);
				world.setBlock(x + 0, y + 0, z + 0, block3, 0, 2);
				world.setBlock(x + 0, y + 1, z + 0, block3, 0, 2);
				world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y - 2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y - 1, z + 0, block3, 0, 2);
				world.setBlock(x + 1, y + 0, z + 0, block3, 0, 2);
				world.setBlock(x + 1, y + 1, z + 0, block3, 0, 2);
				world.setBlock(x + 1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y - 2, z + 0, block1, 5, 2);
				world.setBlock(x + 2, y - 1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 4, 2);
				
			}
		} else
		{
			if (dir == ForgeDirection.EAST)
			{
				Block block1 = GCBlocks.slabGCHalf;
				world.setBlock(x + 0, y + -2, z + -2, block1, 8, 2);
				Block block2 = GCBlocks.basicBlock;
				world.setBlock(x + 0, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block1, 8, 2);
				world.setBlock(x + 0, y + -1, z + -2, block2, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block2, 4, 2);
				world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block2, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block1, 0, 2);
				world.setBlock(x + 0, y + 2, z + -1, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block1, 0, 2);
				world.setBlock(x + 1, y + -2, z + -2, block1, 8, 2);
				Block block3 = GCBlocks.tinStairs1;
				world.setBlock(x + 1, y + -2, z + -1, block3, 5, 2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 5, 2);
				world.setBlock(x + 1, y + -2, z + 1, block3, 5, 2);
				world.setBlock(x + 1, y + -2, z + 2, block1, 8, 2);
				world.setBlock(x + 1, y + -1, z + -2, block2, 4, 2);
				Block block4 = Blocks.glass;
				world.setBlock(x + 1, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + -1, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + -1, z + 2, block2, 4, 2);
				world.setBlock(x + 1, y + 0, z + -2, block2, 4, 2);
				world.setBlock(x + 1, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 2, block2, 4, 2);
				world.setBlock(x + 1, y + 1, z + -2, block2, 4, 2);
				world.setBlock(x + 1, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 0, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 2, block2, 4, 2);
				world.setBlock(x + 1, y + 2, z + -2, block1, 0, 2);
				world.setBlock(x + 1, y + 2, z + -1, block3, 1, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 1, 2);
				world.setBlock(x + 1, y + 2, z + 1, block3, 1, 2);
				world.setBlock(x + 1, y + 2, z + 2, block1, 0, 2);
				
			} else if (dir == ForgeDirection.WEST)
			{
				Block block1 = GCBlocks.slabGCHalf;
				world.setBlock(x + -1, y + -2, z + -2, block1, 8, 2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + -1, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block1, 8, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + -1, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.glass;
				world.setBlock(x + -1, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + -1, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 0, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block1, 0, 2);
				world.setBlock(x + -1, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -1, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -1, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -1, y + 2, z + 2, block1, 0, 2);
				world.setBlock(x + 0, y + -2, z + -2, block1, 8, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block1, 8, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block1, 0, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block1, 0, 2);
				
			} else if (dir == ForgeDirection.SOUTH)
			{
				Block block1 = GCBlocks.slabGCHalf;
				world.setBlock(x + -2, y + -2, z + 0, block1, 8, 2);
				world.setBlock(x + -2, y + -2, z + 1, block1, 8, 2);
				Block block2 = GCBlocks.basicBlock;
				world.setBlock(x + -2, y + -1, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + -1, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 0, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 1, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block1, 0, 2);
				world.setBlock(x + -2, y + 2, z + 1, block1, 0, 2);
				world.setBlock(x + -1, y + -2, z + 0, block2, 4, 2);
				Block block3 = GCBlocks.tinStairs1;
				world.setBlock(x + -1, y + -2, z + 1, block3, 7, 2);
				Block block4 = Blocks.glass;
				world.setBlock(x + -1, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + -1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 3, 2);
				world.setBlock(x + 0, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 7, 2);
				world.setBlock(x + 0, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 3, 2);
				world.setBlock(x + 1, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y + -2, z + 1, block3, 7, 2);
				world.setBlock(x + 1, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + 1, block4, 0, 2);
				world.setBlock(x + 1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y + 2, z + 1, block3, 3, 2);
				world.setBlock(x + 2, y + -2, z + 0, block1, 8, 2);
				world.setBlock(x + 2, y + -2, z + 1, block1, 8, 2);
				world.setBlock(x + 2, y + -1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + -1, z + 1, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + 1, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + 1, block2, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block1, 0, 2);
				world.setBlock(x + 2, y + 2, z + 1, block1, 0, 2);
				
			} else if (dir == ForgeDirection.NORTH)
			{
				Block block1 = GCBlocks.slabGCHalf;
				world.setBlock(x + -2, y + -2, z + -1, block1, 8, 2);
				world.setBlock(x + -2, y + -2, z + 0, block1, 8, 2);
				Block block2 = GCBlocks.basicBlock;
				world.setBlock(x + -2, y + -1, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + -1, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 0, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 1, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + 2, z + -1, block1, 0, 2);
				world.setBlock(x + -2, y + 2, z + 0, block1, 0, 2);
				Block block3 = GCBlocks.tinStairs1;
				world.setBlock(x + -1, y + -2, z + -1, block3, 6, 2);
				world.setBlock(x + -1, y + -2, z + 0, block2, 4, 2);
				Block block4 = Blocks.glass;
				world.setBlock(x + -1, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 2, 2);
				world.setBlock(x + -1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 6, 2);
				world.setBlock(x + 0, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 0, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 2, 2);
				world.setBlock(x + 0, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y + -2, z + -1, block3, 6, 2);
				world.setBlock(x + 1, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + 1, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 1, z + -1, block4, 0, 2);
				world.setBlock(x + 1, y + 2, z + -1, block3, 2, 2);
				world.setBlock(x + 1, y + 2, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + -2, z + -1, block1, 8, 2);
				world.setBlock(x + 2, y + -2, z + 0, block1, 8, 2);
				world.setBlock(x + 2, y + -1, z + -1, block2, 4, 2);
				world.setBlock(x + 2, y + -1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + -1, block2, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + -1, block2, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
				world.setBlock(x + 2, y + 2, z + -1, block1, 0, 2);
				world.setBlock(x + 2, y + 2, z + 0, block1, 0, 2);
				
			}
		}
	}
	
	@Override
	public boolean Check(World world, ForgeDirection dir, int x, int y, int z, int meta)
	{
		if (meta != 0 && meta != 1 && meta != 2 && meta != 3 && meta != -1)
		{
			return false;
		}
		if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST || dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH)
			return true;
		else
			return false;
	}
	
	@Override
	public void ClearWay(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.NORTH)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x - 2, y - 2, z + 0, block1, 6, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x - 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 2, z + 0, block1, 2, 2);
			world.setBlock(x - 1, y - 2, z + 0, block1, 6, 2);
			world.setBlock(x - 1, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y + 2, z + 0, block1, 2, 2);
			world.setBlock(x + 0, y - 2, z + 0, block1, 6, 2);
			world.setBlock(x + 0, y - 1, z + 0, block2, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x, y, z, 3);
			
			world.setBlock(x + 0, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block1, 2, 2);
			world.setBlock(x + 1, y - 2, z + 0, block1, 6, 2);
			world.setBlock(x + 1, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block1, 2, 2);
			world.setBlock(x + 2, y - 2, z + 0, block1, 6, 2);
			world.setBlock(x + 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block1, 2, 2);
			
		} else if (dir == ForgeDirection.SOUTH)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x - 2, y - 2, z + 0, block1, 7, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x - 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 2, z + 0, block1, 3, 2);
			world.setBlock(x - 1, y - 2, z + 0, block1, 7, 2);
			world.setBlock(x - 1, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y + 2, z + 0, block1, 3, 2);
			world.setBlock(x + 0, y - 2, z + 0, block1, 7, 2);
			world.setBlock(x + 0, y - 1, z + 0, block2, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z, 3);
			world.setBlock(x + 0, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block1, 3, 2);
			world.setBlock(x + 1, y - 2, z + 0, block1, 7, 2);
			world.setBlock(x + 1, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block1, 3, 2);
			world.setBlock(x + 2, y - 2, z + 0, block1, 7, 2);
			world.setBlock(x + 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block1, 3, 2);
			
		} else if (dir == ForgeDirection.WEST)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x + 0, y - 2, z - 2, block1, 4, 2);
			world.setBlock(x + 0, y - 2, z - 1, block1, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, block1, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, block1, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x + 0, y - 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z - 1, block2, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z, 3);
			world.setBlock(x + 0, y + 0, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 2, z - 2, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z - 1, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 2, block1, 0, 2);
			
		} else if (dir == ForgeDirection.EAST)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x + 0, y - 2, z - 2, block1, 5, 2);
			world.setBlock(x + 0, y - 2, z - 1, block1, 5, 2);
			world.setBlock(x + 0, y - 2, z + 0, block1, 5, 2);
			world.setBlock(x + 0, y - 2, z + 1, block1, 5, 2);
			world.setBlock(x + 0, y - 2, z + 2, block1, 5, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x + 0, y - 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z - 1, block2, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z, 3);
			world.setBlock(x + 0, y + 0, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 2, z - 2, block1, 1, 2);
			world.setBlock(x + 0, y + 2, z - 1, block1, 1, 2);
			world.setBlock(x + 0, y + 2, z + 0, block1, 1, 2);
			world.setBlock(x + 0, y + 2, z + 1, block1, 1, 2);
			world.setBlock(x + 0, y + 2, z + 2, block1, 1, 2);
		}
		
	}
	
	@Override
	public boolean isHidden()
	{
		return hidden;
	}
	
	@Override
	public String getName()
	{
		return StatCollector.translateToLocal("builder.window.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "window";
	}
	
	@Override
	public void setRotation(int rot)
	{
		this.rot = rot;
	}
	
	@Override
	public int getRotation()
	{
		return rot;
	}
	
	@Override
	public boolean isPossible(ForgeDirection dir, int rot, int meta)
	{
		if (meta == 3 && rot == 1)
		{
			return false;
		} else if (meta != 3 && meta != 0 && rot == 0)
		{
			return false;
		}
		if ((rot == 0 || rot == 1) && (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST || dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH))
		{
			return true;
		} else
			return false;
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		if (this.rot == 0)
		{
			items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 6, 7), "plateTin"));
			items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 4, ItemMod.scaffold_meta)));
			items.add(new OreDictItemStack(new ItemStack(Blocks.glass, 9, 0)));
		} else
		{
			items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 12, 7), "plateTin"));
			items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 6, ItemMod.scaffold_meta)));
			items.add(new OreDictItemStack(new ItemStack(Blocks.glass, 9, 0)));
		}
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.specialFunc = this.getName() + StatCollector.translateToLocal("builder.window.rot" + rot);
		return data;
	}
	
}
