package net.glider.src.utils;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

public class ForgeDirectionUtils {

	public static String GetLocolizedName(ForgeDirection dir)
	{
		
		return StatCollector.translateToLocal("ForgeDirs."+dir.name()+".name");
	}
	
	public static int[] IncreaseByDir(ForgeDirection dir, int[] pos, int io)
	{
		int[] ret = pos;
		if (dir == ForgeDirection.DOWN)
		{
			ret[1] -= io;
		} else if (dir == ForgeDirection.UP)
		{
			ret[1] += io;
		} else if (dir == ForgeDirection.NORTH)
		{
			ret[2] -= io;
		} else if (dir == ForgeDirection.SOUTH)
		{
			ret[2] += io;
		} else if (dir == ForgeDirection.WEST)
		{
			ret[0] -= io;
		} else if (dir == ForgeDirection.EAST)
		{
			ret[0] += io;
		}
		return ret;
	}
	
	public static ForgeDirection turnClockwise(ForgeDirection dir,int i)
	{
		if (i == 4) return dir;
		ForgeDirection ret = dir;
		for (int i2=0;i2 < i;i2++)
		{
		ret = turnClockwise(ret); 
		}
		return ret;
	}
	
	public static ForgeDirection turnClockwise(ForgeDirection dir)
	{
		if (dir == ForgeDirection.WEST)
		{
		return ForgeDirection.NORTH;
		}else if (dir == ForgeDirection.NORTH)
		{
		return ForgeDirection.EAST;
		}else if (dir == ForgeDirection.EAST)
		{
		return ForgeDirection.SOUTH;
		}else if (dir == ForgeDirection.SOUTH)
		{
		return ForgeDirection.WEST;
		}else return ForgeDirection.UNKNOWN;
	}
	
	public static ForgeDirection turnAgainstClockwise(ForgeDirection dir,int i)
	{
		if (i == 4) return dir;
		ForgeDirection ret = dir;
		for (int i2=0;i2 < i;i2++)
		{
		ret = turnAgainstClockwise(ret); 
		}
		return ret;
	}
	
	public static ForgeDirection turnAgainstClockwise(ForgeDirection dir)
	{
		if (dir == ForgeDirection.WEST)
		{
		return ForgeDirection.SOUTH;
		}else if (dir == ForgeDirection.SOUTH)
		{
		return ForgeDirection.EAST;
		}else if (dir == ForgeDirection.EAST)
		{
		return ForgeDirection.NORTH;
		}else if (dir == ForgeDirection.NORTH)
		{
		return ForgeDirection.WEST;
		}else return ForgeDirection.UNKNOWN;
	}
}
