package net.glider.src.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRemoveInfo extends TileEntity {

	public List<TileEntityInfo> infoBlocks = new ArrayList();
	public List<int[]> poses = new ArrayList();
	private int times_read;

	//	public TileEntityInfo infoBlock;
	//	private int x,y,z;

	public TileEntityRemoveInfo()
	{
		super();
	}

	public void configureTileEntity(TileEntityInfo te)
	{
		this.infoBlocks.add(te);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (infoBlocks.size() < poses.size() + times_read && !(poses.get(0)[0] == 0 && poses.get(0)[1] == 0 && poses.get(0)[2] == 0) && worldObj != null)
		{
			infoBlocks.add((TileEntityInfo) worldObj.getTileEntity(poses.get(0)[0], poses.get(0)[1], poses.get(0)[2]));
			times_read++;
			poses.remove(0);
		} else if (infoBlocks.size() < poses.size() + times_read && worldObj != null)
		{
			poses.remove(0);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		//backward compact
		if (!tag.hasKey("TE_S") && !tag.hasKey("TE_le") && !tag.hasKey("NO_TE"))
		{
			poses.add(new int[] { tag.getInteger("TE_x"), tag.getInteger("TE_y"), tag.getInteger("TE_z") });
		}
		if (!tag.hasKey("NO_TE"))
		{
			for (int i = 0; i < tag.getInteger("TE_le"); i++)
			{
				poses.add(new int[] { tag.getInteger("TE_x_" + i), tag.getInteger("TE_y_" + i), tag.getInteger("TE_z_" + i) });
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		if (infoBlocks != null && infoBlocks.size() > 0)
		{
			tag.setInteger("TE_le", infoBlocks.size());
			for (int i = 0; i < infoBlocks.size(); i++)
			{
				tag.setInteger("TE_x_" + i, infoBlocks.get(i).xCoord);
				tag.setInteger("TE_y_" + i, infoBlocks.get(i).yCoord);
				tag.setInteger("TE_z_" + i, infoBlocks.get(i).zCoord);
			}
		} else
		{
			if (poses != null && poses.size() > 0)
			{
				tag.setInteger("TE_le", poses.size());
				for (int i = 0; i < poses.size(); i++)
				{
					if (!(poses.get(i)[0] == 0 && poses.get(i)[1] == 0 && poses.get(i)[2] == 0))
					{
						tag.setInteger("TE_x_" + i, poses.get(i)[0]);
						tag.setInteger("TE_y_" + i, poses.get(i)[1]);
						tag.setInteger("TE_z_" + i, poses.get(i)[2]);
					}
				}
			} else
			{
				tag.setByte("NO_TE", (byte) 0);
			}
		}
	}
}
