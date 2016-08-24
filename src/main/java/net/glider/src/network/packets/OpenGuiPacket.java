package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

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

	public OpenGuiPacket()
	{
	}

	public OpenGuiPacket(TileEntityInfo te)
	{
		object = te.Object;
		addObjects = te.AddObjects;
		ChildObjects = te.ChildObjects;
	}

	public OpenGuiPacket(Structure str, List<Structure> addObj, List<Structure> childobj)
	{
		object = str;
		addObjects = addObj;
		ChildObjects = childobj;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
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
				ChildObjects.add(str);
			}
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();

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
			}
		} else
		{
			tag.setBoolean("CHILD", false);
		}
		ByteBufUtils.writeTag(buf, tag);
	}

	public static class Handler implements IMessageHandler<OpenGuiPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(OpenGuiPacket pkt, MessageContext ctx)
		{

			GuiRemover.object = pkt.object;
			GuiRemover.addObjects = pkt.addObjects;
			GuiRemover.ChildObjects = pkt.ChildObjects;

			return null;
		}

	}
}