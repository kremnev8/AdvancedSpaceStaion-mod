package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.glider.src.dimensions.GravityManager;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientGravityDataRecivePacket implements IMessage {
	private List<Double> souces;
	
	public ClientGravityDataRecivePacket()
	{
	}
	
	public ClientGravityDataRecivePacket(List<Double> S)
	{
		this.souces = S;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagList nbtlist = new NBTTagList();
		for (int i = 0; i < souces.size(); i++)
		{
			nbtlist.appendTag(new NBTTagDouble(souces.get(i)));
		}
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("GRAVITYSOURCES", nbtlist);
		ByteBufUtils.writeTag(buf, tag);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		NBTTagList nbtlist = tag.getTagList("GRAVITYSOURCES", 6);
		List<Double> gravityS = new ArrayList();
		gravityS.clear();
		if (nbtlist.tagCount() != 0)
		{
			
			for (int i = 0; i < nbtlist.tagCount(); i++)
			{
				gravityS.add(nbtlist.func_150309_d(i));
			}
		}
		this.souces = gravityS;
	}
	
	public static class Handler implements IMessageHandler<ClientGravityDataRecivePacket, IMessage> {
		@Override
		public IMessage onMessage(ClientGravityDataRecivePacket pkt, MessageContext ctx)
		{
			if (pkt.souces != null)
			{
				GravityManager.ArtificialForces = pkt.souces;
				GravityManager.updatedList = true;
				GravityManager.updateddouble = false;
				
			}
			
			return null;
		}
		
	}
}