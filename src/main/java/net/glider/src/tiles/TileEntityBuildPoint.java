package net.glider.src.tiles;

import java.util.ArrayList;
import java.util.List;

import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.BuildPointSyncPacket;
import net.glider.src.strucures.BuildHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBuildPoint extends TileEntity {

	// public TileEntityInfo infoBlock;
	// private int x,y,z;
	//
	/**
	 * 0 - everything, 1 - everything excluding pierce, 2 - only add
	 * structures, 3 - only window(only rot == 0), 4 - solar panels, 5 -
	 * greenhouse, 6 - pierce
	 */
	public int type;
	private int lasttype;

	public TileEntityBuildPoint()
	{
		super();
		type = 0;
		lasttype = -1;
	}

	public List<String> getPossibleStructures()
	{
		List<String> list = new ArrayList();
		if (type == 0)
		{
			list.add(BuildHandler.str2.getUnlocalizedName());
			list.add(BuildHandler.str3.getUnlocalizedName());
			list.add(BuildHandler.str4.getUnlocalizedName());
			list.add(BuildHandler.str5.getUnlocalizedName());
			list.add(BuildHandler.str6.getUnlocalizedName());
			list.add(BuildHandler.str7.getUnlocalizedName());
			list.add(BuildHandler.str8.getUnlocalizedName());
			list.add(BuildHandler.str9.getUnlocalizedName());
			list.add(BuildHandler.str10.getUnlocalizedName());
			list.add(BuildHandler.str11.getUnlocalizedName());
			list.add(BuildHandler.str12.getUnlocalizedName());
			list.add(BuildHandler.str13.getUnlocalizedName());
		}
		if (type == 1)
		{
			list.add(BuildHandler.str2.getUnlocalizedName());
			list.add(BuildHandler.str3.getUnlocalizedName());
			list.add(BuildHandler.str4.getUnlocalizedName());
			list.add(BuildHandler.str5.getUnlocalizedName());
			list.add(BuildHandler.str6.getUnlocalizedName());
			list.add(BuildHandler.str7.getUnlocalizedName());
			list.add(BuildHandler.str8.getUnlocalizedName());
			list.add(BuildHandler.str9.getUnlocalizedName());
			list.add(BuildHandler.str10.getUnlocalizedName());
			list.add(BuildHandler.str11.getUnlocalizedName());
			list.add(BuildHandler.str12.getUnlocalizedName());
		}
		if (type == 2)
		{
			list.add(BuildHandler.str13.getUnlocalizedName());
			list.add(BuildHandler.str6.getUnlocalizedName());
			list.add(BuildHandler.str7.getUnlocalizedName());
			list.add(BuildHandler.str8.getUnlocalizedName());
			list.add(BuildHandler.str9.getUnlocalizedName());
		}
		if (type == 3)
		{
			list.add(BuildHandler.str6.getUnlocalizedName());
		}
		if (type == 4)
		{
			list.add(BuildHandler.str9.getUnlocalizedName());
		}
		if (type == 5)
		{
			list.add(BuildHandler.str12.getUnlocalizedName());
			list.add(BuildHandler.str9.getUnlocalizedName());
		}
		if (type == 6)
		{
			list.add(BuildHandler.str13.getUnlocalizedName());
		}
		return list;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (type != lasttype)
		{
			PacketHandler.sendToDimension(new BuildPointSyncPacket(type, xCoord, yCoord, zCoord), worldObj.provider.dimensionId);
			lasttype = type;
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		type = tag.getInteger("TYPE");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("TYPE", type);
	}
}
