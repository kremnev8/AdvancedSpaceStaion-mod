package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.items.ItemMod;
import net.glider.src.utils.ForgeDirectionUtils;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureCornerHall extends StructureRotatable {
	
	private boolean hiddenS;
	private int rot;
	Block block1 = BlockContainerMod.BlockInfo;
	
	public StructureCornerHall(boolean hidden)
	{
		super(hidden);
		this.hiddenS = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureCornerHall Nstr = new StructureCornerHall(hiddenS);
		Nstr.Configure(placementPos.clone(), placementRotation, placementDir);
		return Nstr;
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
	public int nextPossibleValue(int nowV, ForgeDirection dir, int meta)
	{
		int[] order = getPossibleOrder(dir);
		if (nowV == -1)
		{
			return order[0];
		}
		for (int i = 0; i < order.length; i++)
		{
			if (order[i] == nowV)
			{
				if (i + 1 == order.length)
				{
					return order[0];
				} else return order[i + 1];
			}
		}
		
		return nowV++;
	}
	
	public int[] getPossibleOrder(ForgeDirection dir)
	{
		if (dir == ForgeDirection.EAST)
		{
			return new int[] { 2, 3 };
		} else if (dir == ForgeDirection.NORTH)
		{
			return new int[] { 1, 2 };
		} else if (dir == ForgeDirection.SOUTH)
		{
			return new int[] { 3, 0 };
		} else if (dir == ForgeDirection.WEST)
		{
			return new int[] { 0, 1 };
		} else
		{
			return new int[] {};
		}
	}
	
	public int getMetaFromDirARot(ForgeDirection dir, int rot)
	{
		if (rot == 0)
		{
			if (dir == ForgeDirection.WEST)
			{
				return 2;
			} else if (dir == ForgeDirection.SOUTH)
			{
				return 3;
			}
		} else if (rot == 1)
		{
			if (dir == ForgeDirection.WEST)
			{
				return 0;
			} else if (dir == ForgeDirection.NORTH)
			{
				return 3;
			}
		} else if (rot == 2)
		{
			if (dir == ForgeDirection.EAST)
			{
				return 0;
			} else if (dir == ForgeDirection.NORTH)
			{
				return 1;
			}
		} else if (rot == 3)
		{
			if (dir == ForgeDirection.EAST)
			{
				return 2;
			} else if (dir == ForgeDirection.SOUTH)
			{
				return 1;
			}
		}
		return 0;
	}
	
	public ForgeDirection onTurn(ForgeDirection dir, int rot)
	{
		this.rot = rot;
		return getDirs(dir)[0];
	}
	
	@Override
	public ForgeDirection[] getDirs(ForgeDirection dir)
	{
		if (dir == ForgeDirection.WEST)
		{
			if (rot == 0)
			{
				return new ForgeDirection[] { ForgeDirection.NORTH };
			} else return new ForgeDirection[] { ForgeDirection.SOUTH };
		} else if (dir == ForgeDirection.EAST)
		{
			if (rot == 2)
			{
				return new ForgeDirection[] { ForgeDirection.SOUTH };
			} else return new ForgeDirection[] { ForgeDirection.NORTH };
		} else if (dir == ForgeDirection.SOUTH)
		{
			if (rot == 0)
			{
				return new ForgeDirection[] { ForgeDirection.EAST };
			} else return new ForgeDirection[] { ForgeDirection.WEST };
		} else if (dir == ForgeDirection.NORTH)
		{
			if (rot == 1)
			{
				return new ForgeDirection[] { ForgeDirection.EAST };
			} else return new ForgeDirection[] { ForgeDirection.WEST };
		}
		
		return new ForgeDirection[] { ForgeDirection.UNKNOWN };
	}
	
	@Override
	public void deconstruct(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.WEST)
		{
			if (rot == 0)
			{
				// Block block1 = id:35;
				// world.setBlock(x+-7, y+-3, z+-5, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + -6, y + -2, z + -4, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + -3, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + -2, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 1, block2, 4, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -6, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 2, z + -4, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + -3, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + -2, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -5, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 2, block2, 7, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -5, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + -5, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + 1, z + 1, block4, 8, 2);
				world.setBlock(x + -5, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + -4, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -4, y + -1, z + 2, block3, 4, 2);
				// world.setBlock(x+-4, y+0, z+-4, block1,4,2);
				world.setBlock(x + -4, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -2, block3, 4, 2);
				Block block5 = Blocks.air;
				world.setBlock(x + -4, y + 2, z + -1, block5, 0, 2);
				world.setBlock(x + -4, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 2, block2, 3, 2);
				// world.setBlock(x+-4, y+3, z+0, block1,0,2);
				world.setBlock(x + -3, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -3, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 0, block5, 0, 2);
				world.setBlock(x + -3, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + -2, y + -2, z + -4, block2, 5, 2);
				world.setBlock(x + -2, y + -2, z + -3, block2, 5, 2);
				world.setBlock(x + -2, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -2, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -2, block4, 0, 2);
				world.setBlock(x + -2, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -2, block4, 8, 2);
				world.setBlock(x + -2, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -4, block2, 1, 2);
				world.setBlock(x + -2, y + 2, z + -3, block2, 1, 2);
				world.setBlock(x + -2, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + -1, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -1, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				// world.setBlock(x+1, y+3, z+3, block1,14,2);
				
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+-4, y+-3, z+0, rot,x,y,z);
				
				world.setBlock(x + -4, y + -3, z + 0, Blocks.air, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 5);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
			} else if (rot == 1)
			{
				// world.setBlock(x+-7, y+-3, z+-3, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + -6, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 2, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 3, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 4, block2, 4, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -6, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + -6, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 2, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 3, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 4, block2, 0, 2);
				world.setBlock(x + -5, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -5, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -5, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -5, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + -5, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -5, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -5, y + 1, z + -1, block4, 8, 2);
				world.setBlock(x + -5, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -5, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -4, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -4, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+-4, y+0, z+4, block1,4,2);
				world.setBlock(x + -4, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -4, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 0, block3, 4, 2);
				Block block5 = Blocks.air;
				world.setBlock(x + -4, y + 2, z + 1, block5, 0, 2);
				world.setBlock(x + -4, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 4, block3, 4, 2);
				// world.setBlock(x+-4, y+3, z+0, block1,14,2);
				world.setBlock(x + -3, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -3, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -3, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -3, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 0, block5, 0, 2);
				world.setBlock(x + -3, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -2, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 3, block2, 5, 2);
				world.setBlock(x + -2, y + -2, z + 4, block2, 5, 2);
				world.setBlock(x + -2, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 2, block4, 0, 2);
				world.setBlock(x + -2, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 2, block4, 8, 2);
				world.setBlock(x + -2, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -2, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 3, block2, 1, 2);
				world.setBlock(x + -2, y + 2, z + 4, block2, 1, 2);
				world.setBlock(x + -1, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -1, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				// world.setBlock(x+1, y+3, z+5, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+-4, y+-3, z+0, rot,x,y,z);
				
				world.setBlock(x + -4, y + -3, z + 0, Blocks.air, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 5);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
			}
		} else if (dir == ForgeDirection.EAST)
		{
			if (rot == 2)
			{
				// world.setBlock(x+-1, y+-3, z+-3, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 1, y + -2, z + -2, block2, 6, 2);
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
				world.setBlock(x + 2, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 3, block2, 4, 2);
				world.setBlock(x + 2, y + -2, z + 4, block2, 4, 2);
				world.setBlock(x + 2, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + 2, y + -1, z + 2, block4, 0, 2);
				world.setBlock(x + 2, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 2, block4, 8, 2);
				world.setBlock(x + 2, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 2, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 3, block2, 0, 2);
				world.setBlock(x + 2, y + 2, z + 4, block2, 0, 2);
				world.setBlock(x + 3, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 3, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 3, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 3, y + 2, z + -1, block3, 4, 2);
				Block block5 = Blocks.air;
				world.setBlock(x + 3, y + 2, z + 0, block5, 0, 2);
				world.setBlock(x + 3, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 4, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 4, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+4, y+0, z+4, block1,4,2);
				world.setBlock(x + 4, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 4, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 1, block5, 0, 2);
				world.setBlock(x + 4, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 4, block3, 4, 2);
				// world.setBlock(x+4, y+3, z+0, block1,5,2);
				world.setBlock(x + 5, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 5, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 5, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 5, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + 1, z + -1, block4, 8, 2);
				world.setBlock(x + 5, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 5, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 6, y + -2, z + -1, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 1, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 2, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 3, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 4, block2, 5, 2);
				world.setBlock(x + 6, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + 6, y + 2, z + -1, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 1, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 2, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 3, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 4, block2, 1, 2);
				// world.setBlock(x+7, y+3, z+5, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+4, y+-3, z+0, rot,x,y,z);
				
				world.setBlock(x + 4, y + -3, z + 0, Blocks.air, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 5);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
			} else if (rot == 3)
			{
				// world.setBlock(x+-1, y+-3, z+-5, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 1, y + -2, z + -2, block2, 6, 2);
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
				world.setBlock(x + 2, y + -2, z + -4, block2, 4, 2);
				world.setBlock(x + 2, y + -2, z + -3, block2, 4, 2);
				world.setBlock(x + 2, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 2, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -3, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + 2, y + -1, z + -2, block4, 0, 2);
				world.setBlock(x + 2, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -2, block4, 8, 2);
				world.setBlock(x + 2, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -4, block2, 0, 2);
				world.setBlock(x + 2, y + 2, z + -3, block2, 0, 2);
				world.setBlock(x + 2, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 3, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 3, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -1, block3, 4, 2);
				Block block5 = Blocks.air;
				world.setBlock(x + 3, y + 2, z + 0, block5, 0, 2);
				world.setBlock(x + 3, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 4, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 4, y + -1, z + 2, block3, 4, 2);
				// world.setBlock(x+4, y+0, z+-4, block1,4,2);
				world.setBlock(x + 4, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -1, block5, 0, 2);
				world.setBlock(x + 4, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 2, block2, 3, 2);
				// world.setBlock(x+4, y+3, z+0, block1,4,2);
				world.setBlock(x + 5, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 5, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 5, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + 1, z + 1, block4, 8, 2);
				world.setBlock(x + 5, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 6, y + -2, z + -4, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + -3, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + -2, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + -1, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 1, block2, 5, 2);
				world.setBlock(x + 6, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 2, z + -4, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + -3, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + -2, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + -1, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 1, block2, 1, 2);
				// world.setBlock(x+6, y+3, z+2, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+4, y+-3, z+0, rot,x,y,z);
				
				world.setBlock(x + 4, y + -3, z + 0, Blocks.air, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 5);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
			}
		} else if (dir == ForgeDirection.NORTH)
		{
			if (rot == 1)
			{
				// world.setBlock(x+-3, y+-3, z+-7, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + -2, y + -2, z + -5, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + -4, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + -3, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + -2, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -2, y + -1, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -5, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + -4, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + -3, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + -2, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -1, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + -1, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -1, z + -6, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -1, y + -1, z + -5, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -5, block4, 8, 2);
				world.setBlock(x + -1, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -1, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 0, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -6, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -4, block3, 4, 2);
				Block block5 = Blocks.air;
				world.setBlock(x + 0, y + 2, z + -3, block5, 0, 2);
				world.setBlock(x + 0, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				// world.setBlock(x+0, y+3, z+-4, block1,14,2);
				world.setBlock(x + 1, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 1, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 1, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -4, block5, 0, 2);
				world.setBlock(x + 1, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 2, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -1, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -2, block4, 0, 2);
				world.setBlock(x + 2, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -2, block4, 8, 2);
				world.setBlock(x + 2, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 2, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -1, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 3, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 3, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -2, block2, 7, 2);
				world.setBlock(x + 3, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 3, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 3, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -2, block2, 3, 2);
				world.setBlock(x + 4, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 4, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -2, block2, 7, 2);
				world.setBlock(x + 4, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 4, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 0, z + -6, block3, 4, 2);
				// world.setBlock(x+4, y+0, z+-4, block1,4,2);
				world.setBlock(x + 4, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 4, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -2, block2, 3, 2);
				// world.setBlock(x+5, y+3, z+1, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+0, y+-3, z+-4, rot,x,y,z);
				
				world.setBlock(x + 0, y + -3, z + -4, Blocks.air, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 5);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
			} else if (rot == 2)
			{
				// world.setBlock(x+-5, y+-3, z+-7, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + -4, y + -2, z + -6, block2, 6, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -4, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -2, block2, 7, 2);
				world.setBlock(x + -4, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + -4, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 0, z + -6, block3, 4, 2);
				// world.setBlock(x+-4, y+0, z+-4, block1,4,2);
				world.setBlock(x + -4, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -4, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -2, block2, 3, 2);
				world.setBlock(x + -3, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + -3, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -2, block2, 7, 2);
				world.setBlock(x + -3, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + -3, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -3, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -2, block2, 3, 2);
				world.setBlock(x + -2, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + -2, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + -1, z + -6, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -2, y + -1, z + -2, block4, 0, 2);
				world.setBlock(x + -2, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -2, block4, 8, 2);
				world.setBlock(x + -2, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -2, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -1, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + -1, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -1, y + 2, z + -5, block3, 4, 2);
				Block block5 = Blocks.air;
				world.setBlock(x + -1, y + 2, z + -4, block5, 0, 2);
				world.setBlock(x + -1, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 0, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -6, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -3, block5, 0, 2);
				world.setBlock(x + 0, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				// world.setBlock(x+0, y+3, z+-4, block1,5,2);
				world.setBlock(x + 1, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 1, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + -1, z + -5, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + -5, block4, 8, 2);
				world.setBlock(x + 1, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 1, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -5, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + -4, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + -3, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + -2, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + -1, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -1, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -5, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + -4, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + -3, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + -2, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + -1, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				// world.setBlock(x+3, y+3, z+1, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+0, y+-3, z+-4, rot,x,y,z);
				
				world.setBlock(x + 0, y + -3, z + -4, Blocks.air, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 5);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
			}
		} else if (dir == ForgeDirection.SOUTH)
		{
			if (rot == 0)
			{
				// world.setBlock(x+-3, y+-3, z+-1, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 2, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 3, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 4, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 5, block2, 4, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 2, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 3, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 4, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 5, block2, 0, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 6, block2, 7, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -1, y + -1, z + 5, block4, 0, 2);
				world.setBlock(x + -1, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + 5, block4, 8, 2);
				world.setBlock(x + -1, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + 6, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block3, 4, 2);
				Block block5 = Blocks.air;
				world.setBlock(x + 0, y + 2, z + 3, block5, 0, 2);
				world.setBlock(x + 0, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 6, block2, 3, 2);
				// world.setBlock(x+0, y+3, z+4, block1,0,2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 1, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 4, block5, 0, 2);
				world.setBlock(x + 1, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 1, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 2, block4, 0, 2);
				world.setBlock(x + 2, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 2, block4, 8, 2);
				world.setBlock(x + 2, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 1, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 3, y + -2, z + 2, block2, 6, 2);
				world.setBlock(x + 3, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 3, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 2, block2, 2, 2);
				world.setBlock(x + 3, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 4, y + -2, z + 2, block2, 6, 2);
				world.setBlock(x + 4, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 4, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 4, y + 0, z + 2, block3, 4, 2);
				// world.setBlock(x+4, y+0, z+4, block1,4,2);
				world.setBlock(x + 4, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 2, block2, 2, 2);
				world.setBlock(x + 4, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 6, block2, 3, 2);
				// world.setBlock(x+5, y+3, z+7, block1,14,2);
				
				// BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(),
				// x + 0, y + -3, z + 4, rot, x, y, z, lastStr);
				
				world.setBlock(x + 0, y + -3, z + 4, Blocks.air, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 5);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
			} else if (rot == 3)
			{
				// world.setBlock(x+-4, y+-3, z+0, block1,5,2);
				Block block2 = Blocks.air;
				world.setBlock(x + -4, y + -2, z + 2, block2, 6, 2);
				Block block3 = Blocks.air;
				world.setBlock(x + -4, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + -4, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -4, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 2, block2, 2, 2);
				world.setBlock(x + -4, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + -3, y + -2, z + 2, block2, 6, 2);
				world.setBlock(x + -3, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + -3, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 2, block2, 2, 2);
				world.setBlock(x + -3, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 1, block3, 4, 2);
				Block block4 = Blocks.air;
				world.setBlock(x + -2, y + -1, z + 2, block4, 0, 2);
				world.setBlock(x + -2, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 2, block4, 8, 2);
				world.setBlock(x + -2, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + -1, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 3, block3, 4, 2);
				Block block5 = Blocks.air;
				world.setBlock(x + -1, y + 2, z + 4, block5, 0, 2);
				world.setBlock(x + -1, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + 6, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 3, block5, 0, 2);
				world.setBlock(x + 0, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 6, block2, 3, 2);
				// world.setBlock(x+0, y+3, z+4, block1,4,2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 1, y + -1, z + 5, block4, 0, 2);
				world.setBlock(x + 1, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + 5, block4, 8, 2);
				world.setBlock(x + 1, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 1, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 2, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 3, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 4, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 5, block2, 5, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 1, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 2, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 3, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 4, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 5, block2, 1, 2);
				// world.setBlock(x+2, y+3, z+6, block1,14,2);
				
				// BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(),
				// x + 0, y + -3, z + 4, rot, x, y, z, lastStr);
				
				world.setBlock(x + 0, y + -3, z + 4, Blocks.air, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 5);
				
				world.setBlock(pos[0], pos[1], pos[2], Blocks.air, 0, 2);
				
			}
			
		}
	}
	
	// ------------------------------------------------------------------------
	
	@Override
	public void Build(World world, ForgeDirection dir, int x, int y, int z)
	{
		
		if (dir == ForgeDirection.WEST)
		{
			if (rot == 0)
			{
				// Block block1 = id:35;
				// world.setBlock(x+-7, y+-3, z+-5, block1,5,2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + -6, y + -2, z + -4, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + -3, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + -2, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 1, block2, 4, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + -6, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 2, z + -4, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + -3, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + -2, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -5, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 2, block2, 7, 2);
				Block block4 = GCBlocks.slabGCHalf;
				world.setBlock(x + -5, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + -5, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + 1, z + 1, block4, 8, 2);
				world.setBlock(x + -5, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + -4, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -4, y + -1, z + 2, block3, 4, 2);
				// world.setBlock(x+-4, y+0, z+-4, block1,4,2);
				world.setBlock(x + -4, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -2, block3, 4, 2);
				Block block5 = Blocks.glowstone;
				world.setBlock(x + -4, y + 2, z + -1, block5, 0, 2);
				world.setBlock(x + -4, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 2, block2, 3, 2);
				// world.setBlock(x+-4, y+3, z+0, block1,0,2);
				world.setBlock(x + -3, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -3, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 0, block5, 0, 2);
				world.setBlock(x + -3, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + -2, y + -2, z + -4, block2, 5, 2);
				world.setBlock(x + -2, y + -2, z + -3, block2, 5, 2);
				world.setBlock(x + -2, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -2, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -2, block4, 0, 2);
				world.setBlock(x + -2, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -2, block4, 8, 2);
				world.setBlock(x + -2, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -4, block2, 1, 2);
				world.setBlock(x + -2, y + 2, z + -3, block2, 1, 2);
				world.setBlock(x + -2, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + -1, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -1, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				// world.setBlock(x+1, y+3, z+3, block1,14,2);
				
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + -4, y + -3, z + 0, rot, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], rot, x + -4, y + -3, z + 0);
				
			} else if (rot == 1)
			{
				// world.setBlock(x+-7, y+-3, z+-3, block1,5,2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + -6, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 2, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 3, block2, 4, 2);
				world.setBlock(x + -6, y + -2, z + 4, block2, 4, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + -6, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + -6, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + -6, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + -6, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + -6, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 2, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 3, block2, 0, 2);
				world.setBlock(x + -6, y + 2, z + 4, block2, 0, 2);
				world.setBlock(x + -5, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -5, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -5, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -5, y + -1, z + -2, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				world.setBlock(x + -5, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + -5, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -5, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -5, y + 1, z + -1, block4, 8, 2);
				world.setBlock(x + -5, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -5, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -5, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -4, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -4, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+-4, y+0, z+4, block1,4,2);
				world.setBlock(x + -4, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -4, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 0, block3, 4, 2);
				Block block5 = Blocks.glowstone;
				world.setBlock(x + -4, y + 2, z + 1, block5, 0, 2);
				world.setBlock(x + -4, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 4, block3, 4, 2);
				// world.setBlock(x+-4, y+3, z+0, block1,14,2);
				world.setBlock(x + -3, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -3, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -3, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -3, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 0, block5, 0, 2);
				world.setBlock(x + -3, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -2, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 3, block2, 5, 2);
				world.setBlock(x + -2, y + -2, z + 4, block2, 5, 2);
				world.setBlock(x + -2, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 2, block4, 0, 2);
				world.setBlock(x + -2, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 2, block4, 8, 2);
				world.setBlock(x + -2, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -2, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 3, block2, 1, 2);
				world.setBlock(x + -2, y + 2, z + 4, block2, 1, 2);
				world.setBlock(x + -1, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + -1, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				// world.setBlock(x+1, y+3, z+5, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + -4, y + -3, z + 0, rot, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], rot, x - 4, y - 3, z + 0);
				
			}
		} else if (dir == ForgeDirection.EAST)
		{
			if (rot == 2)
			{
				// world.setBlock(x+-1, y+-3, z+-3, block1,5,2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 1, y + -2, z + -2, block2, 6, 2);
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
				world.setBlock(x + 2, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 3, block2, 4, 2);
				world.setBlock(x + 2, y + -2, z + 4, block2, 4, 2);
				world.setBlock(x + 2, y + -1, z + -2, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				world.setBlock(x + 2, y + -1, z + 2, block4, 0, 2);
				world.setBlock(x + 2, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 2, block4, 8, 2);
				world.setBlock(x + 2, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 2, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 3, block2, 0, 2);
				world.setBlock(x + 2, y + 2, z + 4, block2, 0, 2);
				world.setBlock(x + 3, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 3, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 3, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 3, y + 2, z + -1, block3, 4, 2);
				Block block5 = Blocks.glowstone;
				world.setBlock(x + 3, y + 2, z + 0, block5, 0, 2);
				world.setBlock(x + 3, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 4, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 4, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+4, y+0, z+4, block1,4,2);
				world.setBlock(x + 4, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 4, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 1, block5, 0, 2);
				world.setBlock(x + 4, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 4, block3, 4, 2);
				// world.setBlock(x+4, y+3, z+0, block1,5,2);
				world.setBlock(x + 5, y + -2, z + -2, block2, 6, 2);
				world.setBlock(x + 5, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 5, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + -1, z + -1, block4, 0, 2);
				world.setBlock(x + 5, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + 1, z + -1, block4, 8, 2);
				world.setBlock(x + 5, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 5, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 6, y + -2, z + -1, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 1, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 2, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 3, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 4, block2, 5, 2);
				world.setBlock(x + 6, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + 6, y + 2, z + -1, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 1, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 2, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 3, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 4, block2, 1, 2);
				// world.setBlock(x+7, y+3, z+5, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 4, y + -3, z + 0, rot, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], rot, x + 4, y - 3, z + 0);
				
			} else if (rot == 3)
			{
				// world.setBlock(x+-1, y+-3, z+-5, block1,5,2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -2, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -2, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 1, y + -2, z + -2, block2, 6, 2);
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
				world.setBlock(x + 2, y + -2, z + -4, block2, 4, 2);
				world.setBlock(x + 2, y + -2, z + -3, block2, 4, 2);
				world.setBlock(x + 2, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 2, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -3, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				world.setBlock(x + 2, y + -1, z + -2, block4, 0, 2);
				world.setBlock(x + 2, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -2, block4, 8, 2);
				world.setBlock(x + 2, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -4, block2, 0, 2);
				world.setBlock(x + 2, y + 2, z + -3, block2, 0, 2);
				world.setBlock(x + 2, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 3, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 3, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -1, block3, 4, 2);
				Block block5 = Blocks.glowstone;
				world.setBlock(x + 3, y + 2, z + 0, block5, 0, 2);
				world.setBlock(x + 3, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 4, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 4, y + -1, z + 2, block3, 4, 2);
				// world.setBlock(x+4, y+0, z+-4, block1,4,2);
				world.setBlock(x + 4, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -1, block5, 0, 2);
				world.setBlock(x + 4, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 2, block2, 3, 2);
				// world.setBlock(x+4, y+3, z+0, block1,4,2);
				world.setBlock(x + 5, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 5, y + -2, z + 2, block2, 7, 2);
				world.setBlock(x + 5, y + -1, z + 1, block4, 0, 2);
				world.setBlock(x + 5, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + 1, z + 1, block4, 8, 2);
				world.setBlock(x + 5, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 5, y + 2, z + 2, block2, 3, 2);
				world.setBlock(x + 6, y + -2, z + -4, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + -3, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + -2, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + -1, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 6, y + -2, z + 1, block2, 5, 2);
				world.setBlock(x + 6, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 6, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + 6, y + 2, z + -4, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + -3, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + -2, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + -1, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 6, y + 2, z + 1, block2, 1, 2);
				// world.setBlock(x+6, y+3, z+2, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 4, y + -3, z + 0, rot, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], rot, x + 4, y - 3, z + 0);
				
			}
		} else if (dir == ForgeDirection.NORTH)
		{
			if (rot == 1)
			{
				// world.setBlock(x+-3, y+-3, z+-7, block1,5,2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + -2, y + -2, z + -5, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + -4, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + -3, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + -2, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + -2, y + -1, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -5, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + -4, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + -3, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + -2, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -1, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + -1, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -1, z + -6, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				world.setBlock(x + -1, y + -1, z + -5, block4, 0, 2);
				world.setBlock(x + -1, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -5, block4, 8, 2);
				world.setBlock(x + -1, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -1, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 0, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -6, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -4, block3, 4, 2);
				Block block5 = Blocks.glowstone;
				world.setBlock(x + 0, y + 2, z + -3, block5, 0, 2);
				world.setBlock(x + 0, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				// world.setBlock(x+0, y+3, z+-4, block1,14,2);
				world.setBlock(x + 1, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 1, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 1, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -4, block5, 0, 2);
				world.setBlock(x + 1, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 2, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -1, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -2, block4, 0, 2);
				world.setBlock(x + 2, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -2, block4, 8, 2);
				world.setBlock(x + 2, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 2, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -1, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 3, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 3, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + -2, block2, 7, 2);
				world.setBlock(x + 3, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 3, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 3, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + -2, block2, 3, 2);
				world.setBlock(x + 4, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 4, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + -2, block2, 7, 2);
				world.setBlock(x + 4, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 4, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 0, z + -6, block3, 4, 2);
				// world.setBlock(x+4, y+0, z+-4, block1,4,2);
				world.setBlock(x + 4, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 4, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + -2, block2, 3, 2);
				// world.setBlock(x+5, y+3, z+1, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 0, y + -3, z + -4, rot, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], rot, x + 0, y - 3, z - 4);
				
			} else if (rot == 2)
			{
				// world.setBlock(x+-5, y+-3, z+-7, block1,5,2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + -4, y + -2, z + -6, block2, 6, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + -4, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + -2, block2, 7, 2);
				world.setBlock(x + -4, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + -4, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 0, z + -6, block3, 4, 2);
				// world.setBlock(x+-4, y+0, z+-4, block1,4,2);
				world.setBlock(x + -4, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -4, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + -2, block2, 3, 2);
				world.setBlock(x + -3, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + -3, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + -2, block2, 7, 2);
				world.setBlock(x + -3, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + -3, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -3, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + -2, block2, 3, 2);
				world.setBlock(x + -2, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + -2, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + -1, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + -1, z + -6, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				world.setBlock(x + -2, y + -1, z + -2, block4, 0, 2);
				world.setBlock(x + -2, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + -2, block4, 8, 2);
				world.setBlock(x + -2, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -2, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + -1, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -1, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + -1, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + -1, y + 2, z + -5, block3, 4, 2);
				Block block5 = Blocks.glowstone;
				world.setBlock(x + -1, y + 2, z + -4, block5, 0, 2);
				world.setBlock(x + -1, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 0, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 0, y + 0, z + -6, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 0, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -3, block5, 0, 2);
				world.setBlock(x + 0, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				// world.setBlock(x+0, y+3, z+-4, block1,5,2);
				world.setBlock(x + 1, y + -2, z + -6, block2, 6, 2);
				world.setBlock(x + 1, y + -2, z + -5, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -4, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -3, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -2, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + -1, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -1, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + -1, z + -5, block4, 0, 2);
				world.setBlock(x + 1, y + 0, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + -6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + -5, block4, 8, 2);
				world.setBlock(x + 1, y + 2, z + -6, block2, 2, 2);
				world.setBlock(x + 1, y + 2, z + -5, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -4, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -3, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -2, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + -1, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + -5, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + -4, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + -3, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + -2, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + -1, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -1, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -5, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -4, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -3, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -2, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + -1, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + -5, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + -4, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + -3, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + -2, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + -1, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				// world.setBlock(x+3, y+3, z+1, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 0, y + -3, z + -4, rot, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], rot, x + 0, y - 3, z - 4);
				
			}
		} else if (dir == ForgeDirection.SOUTH)
		{
			if (rot == 0)
			{
				// world.setBlock(x+-3, y+-3, z+-1, block1,5,2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 2, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 3, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 4, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 5, block2, 4, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 2, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 3, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 4, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 5, block2, 0, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 6, block2, 7, 2);
				Block block4 = GCBlocks.slabGCHalf;
				world.setBlock(x + -1, y + -1, z + 5, block4, 0, 2);
				world.setBlock(x + -1, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + 5, block4, 8, 2);
				world.setBlock(x + -1, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + 6, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block3, 4, 2);
				Block block5 = Blocks.glowstone;
				world.setBlock(x + 0, y + 2, z + 3, block5, 0, 2);
				world.setBlock(x + 0, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 6, block2, 3, 2);
				// world.setBlock(x+0, y+3, z+4, block1,0,2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 1, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 4, block5, 0, 2);
				world.setBlock(x + 1, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 1, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 2, block4, 0, 2);
				world.setBlock(x + 2, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 2, block4, 8, 2);
				world.setBlock(x + 2, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 1, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 3, y + -2, z + 2, block2, 6, 2);
				world.setBlock(x + 3, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 3, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 3, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 3, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 2, block2, 2, 2);
				world.setBlock(x + 3, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 3, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 4, y + -2, z + 2, block2, 6, 2);
				world.setBlock(x + 4, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 4, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 4, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 4, y + 0, z + 2, block3, 4, 2);
				// world.setBlock(x+4, y+0, z+4, block1,4,2);
				world.setBlock(x + 4, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 4, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 2, block2, 2, 2);
				world.setBlock(x + 4, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 4, y + 2, z + 6, block2, 3, 2);
				// world.setBlock(x+5, y+3, z+7, block1,14,2);
				
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 0, y + -3, z + 4, rot, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], rot, x + 0, y - 3, z + 4);
				
			} else if (rot == 3)
			{
				// world.setBlock(x+-4, y+-3, z+0, block1,5,2);
				Block block2 = GCBlocks.tinStairs1;
				world.setBlock(x + -4, y + -2, z + 2, block2, 6, 2);
				Block block3 = GCBlocks.basicBlock;
				world.setBlock(x + -4, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -4, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + -4, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -4, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -4, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 2, block2, 2, 2);
				world.setBlock(x + -4, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -4, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + -3, y + -2, z + 2, block2, 6, 2);
				world.setBlock(x + -3, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -3, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + -3, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + -3, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 2, block2, 2, 2);
				world.setBlock(x + -3, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -3, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + -2, y + -2, z + 0, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 1, block2, 4, 2);
				world.setBlock(x + -2, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + -2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + -1, z + 1, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				world.setBlock(x + -2, y + -1, z + 2, block4, 0, 2);
				world.setBlock(x + -2, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + -2, y + 1, z + 2, block4, 8, 2);
				world.setBlock(x + -2, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 0, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 1, block2, 0, 2);
				world.setBlock(x + -2, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -2, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + -1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + -1, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + -1, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 3, block3, 4, 2);
				Block block5 = Blocks.glowstone;
				world.setBlock(x + -1, y + 2, z + 4, block5, 0, 2);
				world.setBlock(x + -1, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + -1, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 0, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 0, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 0, y + -1, z + 6, block3, 4, 2);
				// world.setBlock(x+0, y+0, z+0, block1,4,2);
				world.setBlock(x + 0, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 0, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 3, block5, 0, 2);
				world.setBlock(x + 0, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 0, y + 2, z + 6, block2, 3, 2);
				// world.setBlock(x+0, y+3, z+4, block1,4,2);
				world.setBlock(x + 1, y + -2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 1, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 2, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 3, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 4, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 5, block3, 4, 2);
				world.setBlock(x + 1, y + -2, z + 6, block2, 7, 2);
				world.setBlock(x + 1, y + -1, z + 5, block4, 0, 2);
				world.setBlock(x + 1, y + -1, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 0, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 1, z + 5, block4, 8, 2);
				world.setBlock(x + 1, y + 1, z + 6, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 0, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 1, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 2, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 3, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 4, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 5, block3, 4, 2);
				world.setBlock(x + 1, y + 2, z + 6, block2, 3, 2);
				world.setBlock(x + 2, y + -2, z + 0, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 1, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 2, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 3, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 4, block2, 5, 2);
				world.setBlock(x + 2, y + -2, z + 5, block2, 5, 2);
				world.setBlock(x + 2, y + -1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + -1, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 0, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 0, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 1, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 2, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 3, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 4, block3, 4, 2);
				world.setBlock(x + 2, y + 1, z + 5, block3, 4, 2);
				world.setBlock(x + 2, y + 2, z + 0, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 1, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 2, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 3, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 4, block2, 1, 2);
				world.setBlock(x + 2, y + 2, z + 5, block2, 1, 2);
				// world.setBlock(x+2, y+3, z+6, block1,14,2);
				
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 0, y + -3, z + 4, rot, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = ForgeDirectionUtils.IncreaseByDir(dir, pos, 4);
				pos = ForgeDirectionUtils.IncreaseByDir(onTurn(dir, rot).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], rot, x + 0, y - 3, z + 4);
				
			}
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
	public void ClearWay(World world, ForgeDirection dir, int x, int y, int z)
	{
		Block block1 = Blocks.air;
		if (dir == ForgeDirection.WEST)
		{
			
			world.setBlock(x + -2, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + -2, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + -2, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + -2, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + -2, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + -2, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + -2, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + -2, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + -2, y + 1, z + 1, block1, 0, 2);
			world.setBlock(x + -1, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + -1, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + 1, block1, 0, 2);
			
		} else if (dir == ForgeDirection.EAST)
		{
			world.setBlock(x + 0, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + 1, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + 1, block1, 0, 2);
			world.setBlock(x + 2, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + 2, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + 2, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + 2, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + 2, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + 2, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + 2, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + 2, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + 2, y + 1, z + 1, block1, 0, 2);
			
		} else if (dir == ForgeDirection.SOUTH)
		{
			world.setBlock(x + -1, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + -1, y + -1, z + 2, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + 2, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + 1, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + 2, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + 2, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + 2, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + 2, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + 1, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + 2, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + 1, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + 2, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + 1, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + 2, block1, 0, 2);
			
		} else if (dir == ForgeDirection.NORTH)
		{
			world.setBlock(x + -1, y + -1, z + -2, block1, 0, 2);
			world.setBlock(x + -1, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + -1, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + -2, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + -1, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + -2, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + -1, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + -2, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + -2, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + -2, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + 0, y + 1, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + -2, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + -1, block1, 0, 2);
			world.setBlock(x + 1, y + -1, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + -2, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + -1, block1, 0, 2);
			world.setBlock(x + 1, y + 0, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + -2, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + -1, block1, 0, 2);
			world.setBlock(x + 1, y + 1, z + 0, block1, 0, 2);
			
		}
	}
	
	@Override
	public boolean isHidden()
	{
		return hiddenS;
	}
	
	@Override
	public String getName()
	{
		return StatCollector.translateToLocal("builder.corner.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "corner";
	}
	
	@Override
	public boolean isPossible(ForgeDirection dir, int rot, int meta)
	{
		if ((rot == 0 || rot == 1) && dir == ForgeDirection.WEST)
		{
			return true;
		} else if ((rot == 2 || rot == 3) && dir == ForgeDirection.EAST)
		{
			return true;
		}
		if ((rot == 1 || rot == 2) && dir == ForgeDirection.NORTH)
		{
			return true;
		}
		if ((rot == 0 || rot == 3) && dir == ForgeDirection.SOUTH)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 48, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(Items.glowstone_dust, 8)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 1, 13)));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 24, ItemMod.scaffold_meta)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.mainConnect = 1;
		data.addConnect = 0;
		return data;
	}
	
}
