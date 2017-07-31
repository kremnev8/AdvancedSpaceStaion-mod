package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.glider.src.items.ItemMod;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureHall extends Structure {
	
	private boolean hiddenS;
	
	public StructureHall(boolean hidden)
	{
		super(hidden);
		this.hiddenS = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureHall Nstr = new StructureHall(hiddenS);
		Nstr.Configure(placementPos != null ? placementPos.clone() : new int[] { 0, 0, 0 }, placementRotation, placementDir);
		return Nstr;
	}
	
	public int getMetaFromDir(ForgeDirection dir)
	{
		if (dir == ForgeDirection.WEST)
		{
			return 0;
		} else if (dir == ForgeDirection.EAST)
		{
			return 2;
		} else if (dir == ForgeDirection.SOUTH)
		{
			return 3;
		} else if (dir == ForgeDirection.NORTH)
		{
			return 1;
		} else if (dir == ForgeDirection.UNKNOWN)
		{
			return 2;
		}
		return 0;
	}
	
	@Override
	public void deconstruct(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.WEST)
		{
			x = x + 1;
			
			Block block3 = Blocks.air;
			world.setBlock(x + -9, y + -2, z + -2, block3, 6, 2);
			Block block4 = Blocks.air;
			world.setBlock(x + -9, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -9, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -9, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -9, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -9, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -9, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -9, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -9, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -9, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -9, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -9, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -9, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -9, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -9, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -9, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -8, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -8, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -8, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -8, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -8, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -8, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -8, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -8, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -8, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -8, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -8, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -8, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -8, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -8, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -8, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -8, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -7, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -7, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -7, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -7, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -7, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -7, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -7, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -7, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -7, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -7, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -7, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -7, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -7, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -7, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -7, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -7, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -6, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -6, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -6, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -6, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -6, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -6, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -6, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -6, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -6, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -6, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -6, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -6, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -6, y + 2, z + -1, block4, 4, 2);
			Block block5 = Blocks.air;
			world.setBlock(x + -6, y + 2, z + 0, block5, 0, 2);
			world.setBlock(x + -6, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -6, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -5, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -5, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -5, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -5, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -5, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -5, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -5, y + -1, z + 2, block4, 4, 2);
			Block block6 = Blocks.air;
			world.setBlock(x + -5, y + 0, z + -2, block6, 0, 2);
			world.setBlock(x + -5, y + 0, z + 2, block6, 0, 2);
			world.setBlock(x + -5, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -5, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -5, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -5, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -5, y + 2, z + 0, block6, 0, 2);
			world.setBlock(x + -5, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -5, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -4, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -4, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -4, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -4, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -4, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -4, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -4, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -4, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -4, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -4, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -4, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -4, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -4, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -4, y + 2, z + 0, block5, 0, 2);
			world.setBlock(x + -4, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -4, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -3, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -3, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -3, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -3, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -3, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -3, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -3, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -3, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -3, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -3, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -3, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -3, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -3, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -3, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -3, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -3, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -2, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -2, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -2, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -2, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -2, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -2, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -2, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -2, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -2, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -2, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -2, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -2, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -2, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -2, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -2, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -2, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -1, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -1, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -1, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -1, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -1, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -1, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -1, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -1, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -1, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -1, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -1, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -1, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -1, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -1, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -1, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -1, y + 2, z + 2, block3, 3, 2);
			
			world.setBlock(x - 3, y, z - 1, Blocks.air, 0, 2);
			world.setBlock(x - 7, y, z + 1, Blocks.air, 0, 2);
			
			world.setBlock(x - 5, y - 3, z, Blocks.air, 0, 2);
			
			// BuildHandler.buildRemoveInfoPoint(world, dir,
			// getUnlocalizedName(), x-3, y, z-1, 0, x-5, y-3, z);
			
			// BuildHandler.buildInfoPoint(world, dir,
			// getUnlocalizedName(),x-5,
			// y-3, z, 0,x-1,y,z);
			
		} else if (dir == ForgeDirection.EAST)
		{
			x = x - 1;
			// Block block1 = id:35;
			// world.setBlock(x+0, y+-3, z+-3, block1,5,2);
			// world.setBlock(x+0, y+0, z+0, block1,4,2);
			Block block2 = Blocks.air;
			world.setBlock(x + 1, y + -2, z + -2, block2, 6, 2);
			Block block3 = Blocks.air;
			world.setBlock(x + 1, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 1, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 1, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 2, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 2, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 2, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 2, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 3, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 3, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 3, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 3, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 3, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 3, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 3, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 3, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 3, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 3, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 3, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 3, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 3, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 3, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 3, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 3, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 4, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 4, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 4, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 4, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 4, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 4, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 4, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 4, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 4, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 4, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 4, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 4, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 4, y + 2, z + -1, block3, 4, 2);
			Block block4 = Blocks.air;
			world.setBlock(x + 4, y + 2, z + 0, block4, 0, 2);
			world.setBlock(x + 4, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 4, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 5, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 5, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 5, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 5, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 5, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 5, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 5, y + -1, z + 2, block3, 4, 2);
			Block block5 = Blocks.air;
			world.setBlock(x + 5, y + 0, z + -2, block5, 0, 2);
			world.setBlock(x + 5, y + 0, z + 2, block5, 0, 2);
			world.setBlock(x + 5, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 5, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 5, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 5, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 5, y + 2, z + 0, block5, 0, 2);
			world.setBlock(x + 5, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 5, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 6, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 6, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 6, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 6, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 6, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 6, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 6, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 6, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 6, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 6, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 6, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 6, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 6, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 6, y + 2, z + 0, block4, 0, 2);
			world.setBlock(x + 6, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 6, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 7, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 7, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 7, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 7, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 7, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 7, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 7, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 7, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 7, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 7, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 7, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 7, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 7, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 7, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 7, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 7, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 8, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 8, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 8, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 8, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 8, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 8, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 8, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 8, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 8, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 8, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 8, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 8, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 8, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 8, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 8, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 8, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 9, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 9, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 9, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 9, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 9, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 9, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 9, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 9, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 9, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 9, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 9, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 9, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 9, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 9, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 9, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 9, y + 2, z + 2, block2, 3, 2);
			
			// world.setBlock(x+3, y, z+1,
			// BlockContainerMod.BlockRemoveInfo, 2,
			// 2);
			world.setBlock(x + 3, y, z + 1, Blocks.air, 0, 2);
			world.setBlock(x + 7, y, z - 1, Blocks.air, 0, 2);
			world.setBlock(x + 5, y - 3, z, Blocks.air, 0, 2);
			
			// BuildHandler.buildRemoveInfoPoint(world, dir,
			// getUnlocalizedName(), x+3, y, z+1, 0, x+5, y-3, z);
			
			// BuildHandler.buildInfoPoint(world, dir,
			// getUnlocalizedName(),x+5,
			// y-3, z, 0,x+1,y,z);
			
		} else if (dir == ForgeDirection.SOUTH)
		{
			
			Block block2 = Blocks.air;
			world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 1, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 2, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 3, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 4, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 5, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 6, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 7, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 8, block2, 4, 2);
			Block block3 = Blocks.air;
			world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 1, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 3, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 4, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 5, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 6, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 7, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 8, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 1, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 3, block3, 4, 2);
			Block block4 = Blocks.air;
			world.setBlock(x + -2, y + 0, z + 4, block4, 0, 2);
			world.setBlock(x + -2, y + 0, z + 5, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 6, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 7, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 8, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 3, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 4, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 5, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 6, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 7, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 8, block3, 4, 2);
			world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 1, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 2, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 3, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 4, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 5, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 6, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 7, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 8, block2, 0, 2);
			world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 2, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 3, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 4, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 5, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 6, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 7, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 8, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 2, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 3, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 4, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 5, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 6, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 7, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 8, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 2, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 3, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 4, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 5, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 6, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 7, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 8, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 2, block3, 4, 2);
			Block block5 = Blocks.air;
			world.setBlock(x + 0, y + 2, z + 3, block5, 0, 2);
			world.setBlock(x + 0, y + 2, z + 4, block4, 0, 2);
			world.setBlock(x + 0, y + 2, z + 5, block5, 0, 2);
			world.setBlock(x + 0, y + 2, z + 6, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 7, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 8, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 3, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 4, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 5, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 6, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 7, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 8, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 3, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 4, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 5, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 6, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 7, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 8, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 1, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 2, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 3, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 4, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 5, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 6, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 7, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 8, block2, 5, 2);
			world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 3, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 4, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 5, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 6, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 7, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 8, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 3, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 4, block4, 0, 2);
			world.setBlock(x + 2, y + 0, z + 5, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 6, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 7, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 8, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 3, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 4, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 5, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 6, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 7, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 8, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 1, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 2, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 3, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 4, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 5, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 6, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 7, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 8, block2, 1, 2);
			// world.setBlock(x+3, y+3, z+9, block1,14,2);
			
			// world.setBlock(x-1, y, z+2,
			// BlockContainerMod.BlockRemoveInfo, 3,
			// 2);
			world.setBlock(x - 1, y, z + 2, Blocks.air, 0, 2);
			world.setBlock(x + 1, y, z + 6, Blocks.air, 0, 2);
			world.setBlock(x, y - 3, z + 4, Blocks.air, 0, 2);
			
			// BuildHandler.buildRemoveInfoPoint(world, dir,
			// getUnlocalizedName(), x-1, y, z+2, 0, x, y-3, z+4);
			
			// BuildHandler.buildInfoPoint(world, dir,
			// getUnlocalizedName(),x,
			// y-3, z+4, 0,x,y,z);
			
		} else if (dir == ForgeDirection.NORTH)
		{
			
			// world.setBlock(x+-3, y+-3, z+-9, block1,5,2);
			Block block2 = Blocks.air;
			world.setBlock(x + -2, y + -2, z + -8, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -7, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -6, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -5, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -4, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -3, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -2, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -1, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
			Block block3 = Blocks.air;
			world.setBlock(x + -2, y + -1, z + -8, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -7, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -6, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -5, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -4, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -3, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -1, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -8, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -7, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -6, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -5, block3, 4, 2);
			Block block4 = Blocks.air;
			world.setBlock(x + -2, y + 0, z + -4, block4, 0, 2);
			world.setBlock(x + -2, y + 0, z + -3, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -1, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -8, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -7, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -6, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -5, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -4, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -3, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -1, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 2, z + -8, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -7, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -6, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -5, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -4, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -3, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -2, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -1, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
			world.setBlock(x + -1, y + -2, z + -8, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -7, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -6, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -5, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -4, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -3, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -2, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -8, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -7, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -6, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -5, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -4, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -3, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -2, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -8, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -7, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -6, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -5, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -4, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -3, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -2, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
			// world.setBlock(x+0, y+0, z+-8, block1,4,2);
			// z world.setBlock(x+0, y+0, z+0, block1,4,2);
			world.setBlock(x + 0, y + 2, z + -8, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + -7, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + -6, block3, 4, 2);
			Block block5 = Blocks.air;
			world.setBlock(x + 0, y + 2, z + -5, block5, 0, 2);
			world.setBlock(x + 0, y + 2, z + -4, block4, 0, 2);
			world.setBlock(x + 0, y + 2, z + -3, block5, 0, 2);
			world.setBlock(x + 0, y + 2, z + -2, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -8, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -7, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -6, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -5, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -4, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -3, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -8, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -7, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -6, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -5, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -4, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -3, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + -8, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -7, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -6, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -5, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -4, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -3, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -2, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -1, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
			world.setBlock(x + 2, y + -1, z + -8, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -7, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -6, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -5, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -4, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -3, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -8, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -7, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -6, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -5, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -4, block4, 0, 2);
			world.setBlock(x + 2, y + 0, z + -3, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -8, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -7, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -6, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -5, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -4, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -3, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + -8, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -7, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -6, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -5, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -4, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -3, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -2, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -1, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
			// world.setBlock(x+3, y+3, z+1, block1,14,2);
			
			// world.setBlock(x+1, y, z-2,
			// BlockContainerMod.BlockRemoveInfo, 1,
			// 2);
			world.setBlock(x + 1, y, z - 2, Blocks.air, 0, 2);
			world.setBlock(x - 1, y, z - 6, Blocks.air, 0, 2);
			world.setBlock(x, y - 3, z - 4, Blocks.air, 0, 2);
			
			// BuildHandler.buildRemoveInfoPoint(world, dir,
			// getUnlocalizedName(), x+1, y, z-2, 0, x, y-3, z-4);
			
			// BuildHandler.buildInfoPoint(world, dir,
			// getUnlocalizedName(),x,
			// y-3, z-4, 0,x,y,z);
			
		}
	}
	
	@Override
	public void Build(World world, ForgeDirection dir, int x, int y, int z)
	{
		// GLoger.logInfo("Start Building");
		// GLoger.logInfo("Forge direction: "+dir.name());
		// GLoger.logInfo(x+" "+y+" "+z);
		if (dir == ForgeDirection.WEST)
		{
			x = x + 1;
			
			Block block3 = GCBlocks.tinStairs1;
			world.setBlock(x + -9, y + -2, z + -2, block3, 6, 2);
			Block block4 = GCBlocks.basicBlock;
			world.setBlock(x + -9, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -9, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -9, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -9, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -9, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -9, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -9, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -9, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -9, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -9, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -9, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -9, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -9, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -9, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -9, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -8, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -8, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -8, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -8, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -8, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -8, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -8, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -8, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -8, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -8, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -8, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -8, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -8, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -8, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -8, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -8, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -7, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -7, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -7, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -7, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -7, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -7, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -7, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -7, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -7, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -7, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -7, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -7, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -7, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -7, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -7, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -7, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -6, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -6, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -6, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -6, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -6, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -6, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -6, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -6, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -6, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -6, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -6, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -6, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -6, y + 2, z + -1, block4, 4, 2);
			Block block5 = Blocks.glowstone;
			world.setBlock(x + -6, y + 2, z + 0, block5, 0, 2);
			world.setBlock(x + -6, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -6, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -5, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -5, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -5, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -5, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -5, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -5, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -5, y + -1, z + 2, block4, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x - 5, y, z - 2, 3);
			BuildHandler.buildBuildPoint(world, x - 5, y, z + 2, 3);
			
			BuildHandler.buildBuildPoint(world, x - 5, y + 2, z, 4);
			
			world.setBlock(x + -5, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -5, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -5, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -5, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -5, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -5, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -4, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -4, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -4, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -4, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -4, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -4, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -4, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -4, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -4, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -4, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -4, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -4, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -4, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -4, y + 2, z + 0, block5, 0, 2);
			world.setBlock(x + -4, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -4, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -3, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -3, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -3, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -3, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -3, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -3, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -3, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -3, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -3, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -3, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -3, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -3, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -3, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -3, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -3, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -3, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -2, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -2, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -2, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -2, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -2, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -2, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -2, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -2, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -2, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -2, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -2, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -2, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -2, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -2, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -2, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -2, y + 2, z + 2, block3, 3, 2);
			world.setBlock(x + -1, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + -1, y + -2, z + -1, block4, 4, 2);
			world.setBlock(x + -1, y + -2, z + 0, block4, 4, 2);
			world.setBlock(x + -1, y + -2, z + 1, block4, 4, 2);
			world.setBlock(x + -1, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -1, y + -1, z + -2, block4, 4, 2);
			world.setBlock(x + -1, y + -1, z + 2, block4, 4, 2);
			world.setBlock(x + -1, y + 0, z + -2, block4, 4, 2);
			world.setBlock(x + -1, y + 0, z + 2, block4, 4, 2);
			world.setBlock(x + -1, y + 1, z + -2, block4, 4, 2);
			world.setBlock(x + -1, y + 1, z + 2, block4, 4, 2);
			world.setBlock(x + -1, y + 2, z + -2, block3, 2, 2);
			world.setBlock(x + -1, y + 2, z + -1, block4, 4, 2);
			world.setBlock(x + -1, y + 2, z + 0, block4, 4, 2);
			world.setBlock(x + -1, y + 2, z + 1, block4, 4, 2);
			world.setBlock(x + -1, y + 2, z + 2, block3, 3, 2);
			
			// world.setBlock(x-3, y, z-1,
			// BlockContainerMod.BlockRemoveInfo, 0,
			// 2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x - 5, y - 3, z, 0, x - 1, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x - 3, y, z - 1, 0, x - 5, y - 3, z);
			
		} else if (dir == ForgeDirection.EAST)
		{
			x = x - 1;
			// Block block1 = id:35;
			// world.setBlock(x+0, y+-3, z+-3, block1,5,2);
			// world.setBlock(x+0, y+0, z+0, block1,4,2);
			Block block2 = GCBlocks.tinStairs1;
			world.setBlock(x + 1, y + -2, z + -2, block2, 6, 2);
			Block block3 = GCBlocks.basicBlock;
			world.setBlock(x + 1, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 1, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 1, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 2, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 2, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 2, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 2, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 3, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 3, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 3, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 3, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 3, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 3, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 3, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 3, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 3, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 3, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 3, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 3, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 3, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 3, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 3, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 3, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 4, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 4, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 4, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 4, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 4, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 4, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 4, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 4, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 4, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 4, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 4, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 4, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 4, y + 2, z + -1, block3, 4, 2);
			Block block4 = Blocks.glowstone;
			world.setBlock(x + 4, y + 2, z + 0, block4, 0, 2);
			world.setBlock(x + 4, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 4, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 5, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 5, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 5, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 5, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 5, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 5, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 5, y + -1, z + 2, block3, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x + 5, y, z - 2, 3);
			BuildHandler.buildBuildPoint(world, x + 5, y, z + 2, 3);
			
			BuildHandler.buildBuildPoint(world, x + 5, y + 2, z, 4);
			
			world.setBlock(x + 5, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 5, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 5, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 5, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 5, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 5, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 6, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 6, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 6, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 6, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 6, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 6, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 6, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 6, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 6, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 6, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 6, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 6, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 6, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 6, y + 2, z + 0, block4, 0, 2);
			world.setBlock(x + 6, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 6, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 7, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 7, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 7, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 7, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 7, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 7, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 7, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 7, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 7, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 7, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 7, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 7, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 7, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 7, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 7, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 7, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 8, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 8, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 8, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 8, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 8, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 8, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 8, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 8, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 8, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 8, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 8, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 8, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 8, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 8, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 8, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 8, y + 2, z + 2, block2, 3, 2);
			world.setBlock(x + 9, y + -2, z + -2, block2, 6, 2);
			world.setBlock(x + 9, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 9, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 9, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 9, y + -2, z + 2, block2, 7, 2);
			world.setBlock(x + 9, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 9, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 9, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 9, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 9, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 9, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 9, y + 2, z + -2, block2, 2, 2);
			world.setBlock(x + 9, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 9, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 9, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 9, y + 2, z + 2, block2, 3, 2);
			
			// world.setBlock(x+3, y, z+1,
			// BlockContainerMod.BlockRemoveInfo, 2,
			// 2);
			
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 5, y - 3, z, 0, x + 1, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x + 3, y, z + 1, 0, x + 5, y - 3, z);
			
		} else if (dir == ForgeDirection.SOUTH)
		{
			
			Block block2 = GCBlocks.tinStairs1;
			world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 1, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 2, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 3, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 4, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 5, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 6, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 7, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 8, block2, 4, 2);
			Block block3 = GCBlocks.basicBlock;
			world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 1, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 3, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 4, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 5, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 6, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 7, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 8, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 1, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 3, block3, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x - 2, y, z + 4, 3);
			BuildHandler.buildBuildPoint(world, x + 2, y, z + 4, 3);
			
			BuildHandler.buildBuildPoint(world, x, y + 2, z + 4, 4);
			
			world.setBlock(x + -2, y + 0, z + 5, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 6, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 7, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 8, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 3, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 4, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 5, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 6, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 7, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 8, block3, 4, 2);
			world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 1, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 2, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 3, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 4, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 5, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 6, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 7, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 8, block2, 0, 2);
			world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 2, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 3, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 4, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 5, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 6, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 7, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 8, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 2, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 3, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 4, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 5, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 6, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 7, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 8, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 2, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 3, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 4, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 5, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 6, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 7, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 8, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 2, block3, 4, 2);
			Block block5 = Blocks.glowstone;
			world.setBlock(x + 0, y + 2, z + 3, block5, 0, 2);
			world.setBlock(x + 0, y + 2, z + 5, block5, 0, 2);
			world.setBlock(x + 0, y + 2, z + 6, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 7, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 8, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 3, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 4, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 5, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 6, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 7, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 8, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 2, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 3, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 4, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 5, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 6, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 7, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 8, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 1, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 2, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 3, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 4, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 5, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 6, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 7, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 8, block2, 5, 2);
			world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 3, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 4, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 5, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 6, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 7, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 8, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 3, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 5, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 6, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 7, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 8, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 1, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 2, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 3, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 4, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 5, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 6, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 7, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 8, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 1, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 2, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 3, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 4, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 5, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 6, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 7, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 8, block2, 1, 2);
			// world.setBlock(x+3, y+3, z+9, block1,14,2);
			
			// world.setBlock(x-1, y, z+2,
			// BlockContainerMod.BlockRemoveInfo, 3,
			// 2);
			
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x, y - 3, z + 4, 0, x, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x - 1, y, z + 2, 0, x, y - 3, z + 4);
			
		} else if (dir == ForgeDirection.NORTH)
		{
			
			// world.setBlock(x+-3, y+-3, z+-9, block1,5,2);
			Block block2 = GCBlocks.tinStairs1;
			world.setBlock(x + -2, y + -2, z + -8, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -7, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -6, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -5, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -4, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -3, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -2, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + -1, block2, 4, 2);
			world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
			Block block3 = GCBlocks.basicBlock;
			world.setBlock(x + -2, y + -1, z + -8, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -7, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -6, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -5, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -4, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -3, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + -1, block3, 4, 2);
			world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -8, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -7, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -6, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -5, block3, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x - 2, y, z - 4, 3);
			BuildHandler.buildBuildPoint(world, x + 2, y, z - 4, 3);
			
			BuildHandler.buildBuildPoint(world, x, y + 2, z - 4, 4);
			
			world.setBlock(x + -2, y + 0, z + -3, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + -1, block3, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -8, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -7, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -6, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -5, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -4, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -3, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + -1, block3, 4, 2);
			world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + 2, z + -8, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -7, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -6, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -5, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -4, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -3, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -2, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + -1, block2, 0, 2);
			world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
			world.setBlock(x + -1, y + -2, z + -8, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -7, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -6, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -5, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -4, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -3, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -2, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -8, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -7, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -6, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -5, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -4, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -3, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -2, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -8, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -7, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -6, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -5, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -4, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -3, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -2, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
			// world.setBlock(x+0, y+0, z+-8, block1,4,2);
			// z world.setBlock(x+0, y+0, z+0, block1,4,2);
			world.setBlock(x + 0, y + 2, z + -8, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + -7, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + -6, block3, 4, 2);
			Block block5 = Blocks.glowstone;
			world.setBlock(x + 0, y + 2, z + -5, block5, 0, 2);
			world.setBlock(x + 0, y + 2, z + -3, block5, 0, 2);
			world.setBlock(x + 0, y + 2, z + -2, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -8, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -7, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -6, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -5, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -4, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -3, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -8, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -7, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -6, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -5, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -4, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -3, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -2, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + -1, block3, 4, 2);
			world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + -2, z + -8, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -7, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -6, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -5, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -4, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -3, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -2, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + -1, block2, 5, 2);
			world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
			world.setBlock(x + 2, y + -1, z + -8, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -7, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -6, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -5, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -4, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -3, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -8, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -7, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -6, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -5, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -3, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -8, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -7, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -6, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -5, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -4, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -3, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -2, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + -1, block3, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
			world.setBlock(x + 2, y + 2, z + -8, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -7, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -6, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -5, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -4, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -3, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -2, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + -1, block2, 1, 2);
			world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
			// world.setBlock(x+3, y+3, z+1, block1,14,2);
			
			// world.setBlock(x+1, y, z-2,
			// BlockContainerMod.BlockRemoveInfo, 1,
			// 2);
			
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x, y - 3, z - 4, 0, x, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x + 1, y, z - 2, 0, x, y - 3, z - 4);
			
		}
		
	}
	
	@Override
	public void ClearWay(World world, ForgeDirection dir, int x, int y, int z)
	{
		Block air = Blocks.air;
		
		if (dir == ForgeDirection.WEST)
		{
			world.setBlock(x, y + 1, z + 1, air, 0, 2);
			world.setBlock(x, y + 1, z - 1, air, 0, 2);
			world.setBlock(x, y - 1, z + 1, air, 0, 2);
			world.setBlock(x, y - 1, z - 1, air, 0, 2);
			world.setBlock(x, y + 1, z, air, 0, 2);
			world.setBlock(x, y - 1, z, air, 0, 2);
			int nx = x - 1;
			world.setBlock(nx, y, z, air, 0, 2);
			world.setBlock(nx, y, z + 1, air, 0, 2);
			world.setBlock(nx, y, z - 1, air, 0, 2);
			world.setBlock(nx, y + 1, z + 1, air, 0, 2);
			world.setBlock(nx, y + 1, z - 1, air, 0, 2);
			world.setBlock(nx, y - 1, z + 1, air, 0, 2);
			world.setBlock(nx, y - 1, z - 1, air, 0, 2);
			world.setBlock(nx, y + 1, z, air, 0, 2);
			world.setBlock(nx, y - 1, z, air, 0, 2);
		} else if (dir == ForgeDirection.EAST)
		{
			world.setBlock(x, y + 1, z + 1, air, 0, 2);
			world.setBlock(x, y + 1, z - 1, air, 0, 2);
			world.setBlock(x, y - 1, z + 1, air, 0, 2);
			world.setBlock(x, y - 1, z - 1, air, 0, 2);
			world.setBlock(x, y + 1, z, air, 0, 2);
			world.setBlock(x, y - 1, z, air, 0, 2);
			int nx = x + 1;
			world.setBlock(nx, y, z, air, 0, 2);
			world.setBlock(nx, y, z + 1, air, 0, 2);
			world.setBlock(nx, y, z - 1, air, 0, 2);
			world.setBlock(nx, y + 1, z + 1, air, 0, 2);
			world.setBlock(nx, y + 1, z - 1, air, 0, 2);
			world.setBlock(nx, y - 1, z + 1, air, 0, 2);
			world.setBlock(nx, y - 1, z - 1, air, 0, 2);
			world.setBlock(nx, y + 1, z, air, 0, 2);
			world.setBlock(nx, y - 1, z, air, 0, 2);
		} else if (dir == ForgeDirection.SOUTH)
		{
			world.setBlock(x + 1, y + 1, z, air, 0, 2);
			world.setBlock(x - 1, y + 1, z, air, 0, 2);
			world.setBlock(x + 1, y - 1, z, air, 0, 2);
			world.setBlock(x - 1, y - 1, z, air, 0, 2);
			world.setBlock(x, y + 1, z, air, 0, 2);
			world.setBlock(x, y - 1, z, air, 0, 2);
			int nz = z + 1;
			world.setBlock(x, y, nz, air, 0, 2);
			world.setBlock(x + 1, y, nz, air, 0, 2);
			world.setBlock(x - 1, y, nz, air, 0, 2);
			world.setBlock(x + 1, y + 1, nz, air, 0, 2);
			world.setBlock(x - 1, y + 1, nz, air, 0, 2);
			world.setBlock(x + 1, y - 1, nz, air, 0, 2);
			world.setBlock(x - 1, y - 1, nz, air, 0, 2);
			world.setBlock(x, y + 1, nz, air, 0, 2);
			world.setBlock(x, y - 1, nz, air, 0, 2);
		} else if (dir == ForgeDirection.NORTH)
		{
			world.setBlock(x + 1, y + 1, z, air, 0, 2);
			world.setBlock(x - 1, y + 1, z, air, 0, 2);
			world.setBlock(x + 1, y - 1, z, air, 0, 2);
			world.setBlock(x - 1, y - 1, z, air, 0, 2);
			world.setBlock(x, y + 1, z, air, 0, 2);
			world.setBlock(x, y - 1, z, air, 0, 2);
			int nz = z - 1;
			world.setBlock(x, y, nz, air, 0, 2);
			world.setBlock(x + 1, y, nz, air, 0, 2);
			world.setBlock(x - 1, y, nz, air, 0, 2);
			world.setBlock(x + 1, y + 1, nz, air, 0, 2);
			world.setBlock(x - 1, y + 1, nz, air, 0, 2);
			world.setBlock(x + 1, y - 1, nz, air, 0, 2);
			world.setBlock(x - 1, y - 1, nz, air, 0, 2);
			world.setBlock(x, y + 1, nz, air, 0, 2);
			world.setBlock(x, y - 1, nz, air, 0, 2);
		}
		
	}
	
	@Override
	public boolean Check(World world, ForgeDirection dir, int x, int y, int z, int meta)
	{
		if (meta != 0 && meta != 1 && meta != -1)
		{
			return false;
		}
		if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST || dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH) return true;
		else return false;
	}
	
	@Override
	public String getName()
	{
		return StatCollector.translateToLocal("builder.hall.name");
	}
	
	@Override
	public boolean isHidden()
	{
		return hiddenS;
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "hall";
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 54, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(Items.glowstone_dust, 8)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 4, 13)));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 32, ItemMod.scaffold_meta)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.mainConnect = 1;
		data.addConnect = 3;
		return data;
	}
	
}
