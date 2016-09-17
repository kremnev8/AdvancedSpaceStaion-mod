package net.glider.src.strucures.matrix;

import java.util.HashMap;
import java.util.Map;

import net.glider.src.blocks.BlockContainerMod;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MatrixHelper {

	private static Map totalMatrix = new HashMap<Integer, int[]>();
	private static Map toScan = new HashMap<Integer, int[]>();
	
	public static int[] findPointForAddOBJ(World world,ForgeDirection dir,int x,int y,int z)
	{
		int Nx,Ny,Nz;
		int[] ret;
		if (dir == ForgeDirection.DOWN)
		{
		Ny = y-1;
		ret = new int[]{x,Ny,z};
		if (isMatrixPoint(world,ret[0],ret[1],ret[2]))
		{
		return ret;
		}
		}else if (dir == ForgeDirection.UP)
		{
		Ny = y-5;
		ret = new int[]{x,Ny,z};
		if (isMatrixPoint(world,ret[0],ret[1],ret[2]))
		{
		return ret;
		}else
		{
			Ny = y-6;
			ret = new int[]{x,Ny,z};
			if (isMatrixPoint(world,ret[0],ret[1],ret[2]))
			{
			return ret;
			}
		}
		}else
		{
		for (int i=0;i < 2;i++)
		{
			if (i == 0)
			{
				Nx = x;
				Nz = z;
				if (dir == ForgeDirection.NORTH)
				{
				Nz = Nz + 1;
				}else if (dir == ForgeDirection.SOUTH)
				{
				Nz = Nz - 1;	
				}else if (dir == ForgeDirection.WEST)
				{
				Nx = Nx + 1;
				}else
				{
				Nx = Nx - 1;	
				}
				Ny = y-3;
				ret = new int[]{Nx,Ny,Nz};
				if (isMatrixPoint(world,ret[0],ret[1],ret[2]))
				{
				return ret;
				}
			}else if (i == 1)
			{
				Nx = x;
				Nz = z;
				if (dir == ForgeDirection.NORTH)
				{
				Nz = Nz + 5;
				}else if (dir == ForgeDirection.SOUTH)
				{
				Nz = Nz - 5;	
				}else if (dir == ForgeDirection.WEST)
				{
				Nx = Nx + 5;
				}else
				{
				Nx = Nx - 5;	
				}
				Ny = y-3;
				ret = new int[]{Nx,Ny,Nz};
				if (isMatrixPoint(world,ret[0],ret[1],ret[2]))
				{
				return ret;
				}
				
			}
		}
		}
		
		return new int[]{};
	}
	
	public static boolean isMatrixPoint(World world,int x,int y,int z)
	{
		return world.getBlock(x, y, z) == BlockContainerMod.BlockInfo;
	}
 
	public static int[] findMatrixPoint(World world,ForgeDirection dir,int x,int y,int z)
	{
		if (dir != ForgeDirection.DOWN && dir != ForgeDirection.UP)
		{
			//GLoger.logInfo("OK");
			int Py = y - 3;
			int Px;
			int Pz;
				if (dir == ForgeDirection.NORTH)
				{
					Pz = z + 5;
					Px = x;
				}else if (dir == ForgeDirection.SOUTH)
				{
					Pz = z - 5;
					Px = x;
				}else if (dir == ForgeDirection.WEST)
				{
					Pz = z;
					Px = x + 5;
				}else if (dir == ForgeDirection.EAST)
				{
					Pz = z;
					Px = x - 5;
				}else
				{
				Px = x;
				Pz = z;
				}
				//GLoger.logInfo(Px+" "+Py+" "+Pz);
				if (world.getBlock(Px, Py, Pz) == BlockContainerMod.BlockInfo) 
					return new int[] {Px,Py,Pz};
		}
		return null;
	}
	
	
	
	public static Map<Integer,int[]> findTotalMatrix(World world,int[] Fpoint)
	{
		totalMatrix.clear();
		toScan.clear();
		
		Map lastMatrix = new HashMap<Integer, int[]>();
		if (Fpoint != null && Fpoint.length == 3)
		{
			addIfNotAdded(Fpoint);
			
			lastMatrix = findNextMatrixPoints(world,Fpoint);
			for (int i=0;i < lastMatrix.size();i++)
			{
				addIfNotAdded((int[])lastMatrix.get(i));
				addIfNotAddedToScan((int[])lastMatrix.get(i));
			}
			
			while (toScan.size() != 0)
			{
				lastMatrix.clear();
				lastMatrix = findNextMatrixPoints(world,(int[])toScan.get(0));
				for (int i=0;i < lastMatrix.size();i++)
				{
					if (addIfNotAdded((int[])lastMatrix.get(i)))
					addIfNotAddedToScan((int[])lastMatrix.get(i));
				}
				MoveNextToPrev();
			}
			
			return totalMatrix;
			
		}
		
		return null;
	}
	
	public static void MoveNextToPrev()
	{
		for (int i=0;i < toScan.size();i++)
		{
			if (toScan.containsKey(i+1))
			toScan.put(i, toScan.get(i+1));
		}
		toScan.remove(toScan.size()-1);
	}

	
	public static boolean addIfNotAdded(int[] point)
	{
		for (int i=0;i < totalMatrix.size();i++)
		{
			if (((int[])totalMatrix.get(i))[0] == point[0] && ((int[])totalMatrix.get(i))[1] == point[1] && ((int[])totalMatrix.get(i))[2] == point[2])
			{
				return false;
			}
		}
		totalMatrix.put(totalMatrix.size(),point);
		return true;
	}
	
	public static void addIfNotAddedToScan(int[] point)
	{
		for (int i=0;i < toScan.size();i++)
		{
			if (((int[])toScan.get(i))[0] == point[0] && ((int[])toScan.get(i))[1] == point[1] && ((int[])toScan.get(i))[2] == point[2])
			{
				return;
			}
		}
		toScan.put(toScan.size(),point);
	}
		
	public static int[] FindPointInMatrix(Map<Integer,int[]> M,int[] point)
	{
		for (int i=0;i < M.size();i++)
		{
			if (((int[])totalMatrix.get(i))[0] == point[0] && ((int[])totalMatrix.get(i))[1] == point[1] && ((int[])totalMatrix.get(i))[2] == point[2])
			{
				return (int[])totalMatrix.get(i);
			}
		}
		return null;
	}
			
	
	public static Map<Integer,int[]> findNextMatrixPoints(World world,int[] pos)
	{
		Map matrix = new HashMap<Integer, int[]>();
		if (pos != null && pos.length == 3)
		{
		int x = pos[0];
		int y = pos[1];
		int z = pos[2];
		int Px;
		int Pz;
	
		for (int i=2;i < 6;i++)
		{
			if (ForgeDirection.getOrientation(i) == ForgeDirection.WEST)
			{
				Px = x - 9;
				Pz = z;
			}else if (ForgeDirection.getOrientation(i) == ForgeDirection.EAST)
			{
				Px = x + 9;
				Pz = z;
			}else if (ForgeDirection.getOrientation(i) == ForgeDirection.SOUTH)
			{
				Px = x;
				Pz = z - 9;
			}else if (ForgeDirection.getOrientation(i) == ForgeDirection.NORTH)
			{
				Px = x;
				Pz = z + 9;
			}else
			{
				Px = x;
				Pz = z;
			}
			if (world.getBlock(Px, y, Pz) == BlockContainerMod.BlockInfo)
			{
				matrix.put(matrix.size(), new int[] {Px,y,Pz});
			}
		}
		return matrix;
		}else
		return null;
	}

}
