package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.glider.src.gui.GuiModificator;
import net.glider.src.gui.GuiRemover;
import net.glider.src.strucures.Structure;
import net.glider.src.tiles.TileEntityInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OpenGuiPacket implements IMessage {
	private Structure object;
	private List<Structure> addObjects = new ArrayList<Structure>();
	private List<Structure> ChildObjects = new ArrayList<Structure>();
	private boolean isRemover;
	
	public OpenGuiPacket()
	{
	}
	
	public OpenGuiPacket(TileEntityInfo te, boolean isRemover)
	{
		object = te.Object;
		addObjects = te.AddObjects;
		ChildObjects = te.ChildObjects;
		this.isRemover = isRemover;
	}
	
	public OpenGuiPacket(Structure str, List<Structure> addObj, List<Structure> childobj, boolean isRemover)
	{
		object = str;
		addObjects = addObj;
		ChildObjects = childobj;
		this.isRemover = isRemover;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		isRemover = tag.getBoolean("REMOVER");
		if (tag.getBoolean("OBJWRT"))
		{
			object = Structure.FindStructure(tag.getString("OBJ"));
			object.Configure(tag.getIntArray("POS"), tag.getInteger("ROT"), ForgeDirection.getOrientation(tag.getInteger("DIR")));
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
				addObjects.add(str);
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
		
		if (tag.getBoolean("CONN"))
		{
			for (int i = 0; i < tag.getInteger("N_CONN"); i++)
			{
				Structure str = Structure.FindStructure(tag.getString("CN" + i + "_OBJ"));
				int[] t1 = tag.getIntArray("CN" + i + "_POS");
				int t2 = tag.getInteger("CN" + i + "_ROT");
				ForgeDirection t3 = ForgeDirection.getOrientation(tag.getInteger("CN" + i + "_DIR"));
				str.Configure(t1, t2, t3);
				object.connections.add(str);
			}
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("REMOVER", isRemover);
		if (object != null)
		{
			tag.setBoolean("OBJWRT", true);
			tag.setInteger("DIR", object.placementDir.ordinal());
			tag.setInteger("ROT", object.placementRotation);
			tag.setIntArray("POS", object.placementPos);
			tag.setString("OBJ", object.getUnlocalizedName());
		} else
		{
			tag.setBoolean("OBJWRT", false);
		}
		
		if (addObjects != null && addObjects.size() > 0)
		{
			tag.setInteger("N_ADD_O", addObjects.size());
			tag.setBoolean("ADDOBJ", true);
			
			for (int i = 0; i < addObjects.size(); i++)
			{
				tag.setInteger("A" + i + "_DIR", addObjects.get(i).placementDir.ordinal());
				tag.setInteger("A" + i + "_ROT", addObjects.get(i).placementRotation);
				tag.setIntArray("A" + i + "_POS", addObjects.get(i).placementPos);
				tag.setString("A" + i + "_OBJ", addObjects.get(i).getUnlocalizedName());
			}
		} else
		{
			tag.setBoolean("ADDOBJ", false);
		}
		
		if (ChildObjects != null && ChildObjects.size() > 0)
		{
			tag.setInteger("N_CHILD", ChildObjects.size());
			tag.setBoolean("CHILD", true);
			
			for (int i = 0; i < ChildObjects.size(); i++)
			{
				tag.setInteger("CH" + i + "_DIR", ChildObjects.get(i).placementDir.ordinal());
				tag.setInteger("CH" + i + "_ROT", ChildObjects.get(i).placementRotation);
				tag.setIntArray("CH" + i + "_POS", ChildObjects.get(i).placementPos);
				tag.setString("CH" + i + "_OBJ", ChildObjects.get(i).getUnlocalizedName());
				
				if (ChildObjects.get(i).connections != null && ChildObjects.get(i).connections.size() > 0)
				{
					tag.setInteger("N_CHCN", ChildObjects.get(i).connections.size());
					tag.setBoolean("CHCN", true);
					
					for (int j = 0; j < ChildObjects.get(i).connections.size(); j++)
					{
						tag.setInteger("CHCN" + j + "_DIR", ChildObjects.get(i).connections.get(j).placementDir.ordinal());
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
		
		if (object != null && object.connections != null && object.connections.size() > 0)
		{
			tag.setInteger("N_CONN", object.connections.size());
			tag.setBoolean("CONN", true);
			
			for (int i = 0; i < object.connections.size(); i++)
			{
				tag.setInteger("CN" + i + "_DIR", object.connections.get(i).placementDir.ordinal());
				tag.setInteger("CN" + i + "_ROT", object.connections.get(i).placementRotation);
				tag.setIntArray("CN" + i + "_POS", object.connections.get(i).placementPos);
				tag.setString("CN" + i + "_OBJ", object.connections.get(i).getUnlocalizedName());
			}
		} else
		{
			tag.setBoolean("CONN", false);
		}
		
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<OpenGuiPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(OpenGuiPacket pkt, MessageContext ctx)
		{
			if (pkt.isRemover)
			{
				GuiRemover.prepareToOpen();
				GuiRemover.object = pkt.object;
				GuiRemover.addObjects = pkt.addObjects;
				GuiRemover.ChildObjects = pkt.ChildObjects;
			} else
			{
				GuiModificator.prepareToOpen();
				GuiModificator.object = pkt.object;
				GuiModificator.addObjects = pkt.addObjects;
				GuiModificator.ChildObjects = pkt.ChildObjects;
			}
			return null;
		}
		
	}
}