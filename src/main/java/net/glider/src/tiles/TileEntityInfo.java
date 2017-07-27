package net.glider.src.tiles;

import java.util.ArrayList;
import java.util.List;
import scala.Array;
import net.glider.src.strucures.Structure;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityInfo extends TileEntity {
	
	public Structure Object;
	public List<Structure> ChildObjects = new ArrayList<Structure>();
	public List<Structure> AddObjects = new ArrayList<Structure>();
	
	public TileEntityInfo()
	{
		super();
	}
	
	private int FindDir(ForgeDirection dir)
	{
		for (int i = 0; i < 6; i++)
		{
			if (dir == ForgeDirection.getOrientation(i))
			{
				return i;
			}
		}
		return -1;
	}
	
	public void configureTileEntity(ForgeDirection Pldir, int rot, Structure strc, int[] PlPos)
	{
		this.Object = strc;
		Object.placementDir = Pldir;
		Object.placementRotation = rot;
		Object.placementPos = PlPos;
	}
	
	/**
	 * Configire what you need. for clild object use "CHILD". for additional
	 * structure use "ADD".
	 */
	public void configureTileEntity(String type, Structure Str)
	{
		if (Str != null)
		{
			if (type == "ADD")
			{
				this.AddObjects.add(Str);
			} else if (type == "CHILD")
			{
				this.ChildObjects.add(Str);
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		if (tag.getBoolean("OBJWRT"))
		{
			Object = Structure.FindStructure(tag.getString("OBJ"));
			Object.Configure(tag.getIntArray("POS"), tag.getInteger("ROT"), ForgeDirection.getOrientation(tag.getInteger("DIR")));
			if (Object.connections == null)
			{
				Object.connections = new ArrayList();
			}
		}
		
		if (tag.getBoolean("CHILD"))
		{
			for (int i = 0; i < tag.getInteger("N_CHILD"); i++)
			{
				Structure str = Structure.FindStructure(tag.getString("CH" + i + "_OBJ"));
				int[] t1 = tag.getIntArray("CH" + i + "_POS");
				int t2 = tag.getInteger("CH" + i + "_ROT");
				ForgeDirection t3 = ForgeDirection.getOrientation(tag.getInteger("CH" + i + "_DIR"));
				str.Configure(t1, t2, t3);
				
				if (tag.getBoolean("CHCN"))
				{
					for (int j = 0; j < tag.getInteger("N_CHCN"); j++)
					{
						Structure CNstr = Structure.FindStructure(tag.getString("CHCN" + j + "_OBJ"));
						int[] CNt1 = tag.getIntArray("CHCN" + j + "_POS");
						int CNt2 = tag.getInteger("CHCN" + j + "_ROT");
						ForgeDirection CNt3 = ForgeDirection.getOrientation(tag.getInteger("CHCN" + j + "_DIR"));
						str.Configure(CNt1, CNt2, CNt3);
						str.connections.add(CNstr);
					}
				}
				
				ChildObjects.add(str);
			}
		}
		
		if (tag.getBoolean("ADDOBJ"))
		{
			for (int i = 0; i < tag.getInteger("N_ADD_O"); i++)
			{
				Structure str = Structure.FindStructure(tag.getString("A" + i + "_OBJ"));
				int[] t1 = tag.getIntArray("A" + i + "_POS");
				int t2 = tag.getInteger("A" + i + "_ROT");
				ForgeDirection t3 = ForgeDirection.getOrientation(tag.getInteger("A" + i + "_DIR"));
				str.Configure(t1, t2, t3);
				AddObjects.add(str);
			}
		}
		
		if (tag.getBoolean("CONN"))
		{
			for (int i = 0; i < tag.getInteger("N_CONN"); i++)
			{
				Structure str = Structure.FindStructure(tag.getString("CN" + i + "_OBJ"));
				int[] t1 = tag.getIntArray("CN" + i + "_POS");
				int t2 = tag.getInteger("CN" + i + "_ROT");
				ForgeDirection t3 = ForgeDirection.getOrientation(tag.getInteger("CN" + i + "_DIR"));
				str.Configure(t1, t2, t3);
				this.Object.connections.add(str);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		if (Object != null)
		{
			tag.setBoolean("OBJWRT", true);
			tag.setInteger("DIR", FindDir(Object.placementDir));
			tag.setInteger("ROT", Object.placementRotation);
			tag.setIntArray("POS", Object.placementPos);
			tag.setString("OBJ", Object.getUnlocalizedName());
		} else
		{
			tag.setBoolean("OBJWRT", false);
		}
		if (ChildObjects != null && ChildObjects.size() > 0)
		{
			tag.setInteger("N_CHILD", ChildObjects.size());
			tag.setBoolean("CHILD", true);
			
			for (int i = 0; i < ChildObjects.size(); i++)
			{
				tag.setInteger("CH" + i + "_DIR", FindDir(ChildObjects.get(i).placementDir));
				tag.setInteger("CH" + i + "_ROT", ChildObjects.get(i).placementRotation);
				tag.setIntArray("CH" + i + "_POS", ChildObjects.get(i).placementPos);
				tag.setString("CH" + i + "_OBJ", ChildObjects.get(i).getUnlocalizedName());
				
				if (ChildObjects.get(i).connections != null && ChildObjects.get(i).connections.size() > 0)
				{
					tag.setInteger("N_CHCN", ChildObjects.get(i).connections.size());
					tag.setBoolean("CHCN", true);
					
					for (int j = 0; j < ChildObjects.get(i).connections.size(); j++)
					{
						tag.setInteger("CHCN" + j + "_DIR", FindDir(ChildObjects.get(j).connections.get(j).placementDir));
						tag.setInteger("CHCN" + j + "_ROT", ChildObjects.get(i).connections.get(j).placementRotation);
						tag.setIntArray("CHCN" + j + "_POS", ChildObjects.get(i).connections.get(j).placementPos);
						tag.setString("CHCN" + j + "_OBJ", ChildObjects.get(i).connections.get(j).getUnlocalizedName());
					}
				} else
				{
					tag.setBoolean("CHCN", false);
				}
				
			}
		} else
		{
			tag.setBoolean("CHILD", false);
		}
		
		if (AddObjects != null && AddObjects.size() > 0)
		{
			tag.setInteger("N_ADD_O", AddObjects.size());
			tag.setBoolean("ADDOBJ", true);
			
			for (int i = 0; i < AddObjects.size(); i++)
			{
				tag.setInteger("A" + i + "_DIR", FindDir(AddObjects.get(i).placementDir));
				tag.setInteger("A" + i + "_ROT", AddObjects.get(i).placementRotation);
				tag.setIntArray("A" + i + "_POS", AddObjects.get(i).placementPos);
				tag.setString("A" + i + "_OBJ", AddObjects.get(i).getUnlocalizedName());
			}
		} else
		{
			tag.setBoolean("ADDOBJ", false);
		}
		
		if (Object != null && Object.connections != null && Object.connections.size() > 0)
		{
			tag.setInteger("N_CONN", Object.connections.size());
			tag.setBoolean("CONN", true);
			
			for (int i = 0; i < Object.connections.size(); i++)
			{
				tag.setInteger("CN" + i + "_DIR", FindDir(Object.connections.get(i).placementDir));
				tag.setInteger("CN" + i + "_ROT", Object.connections.get(i).placementRotation);
				tag.setIntArray("CN" + i + "_POS", Object.connections.get(i).placementPos);
				tag.setString("CN" + i + "_OBJ", Object.connections.get(i).getUnlocalizedName());
			}
		} else
		{
			tag.setBoolean("CONN", false);
		}
	}
	
}
