package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

import net.glider.src.gui.GuiDockingPort;
import net.glider.src.utils.GLoger;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SendUUIDPacket implements IMessage
{
	
    private UUID id;
	
	public SendUUIDPacket() {}
	
	public SendUUIDPacket(UUID id) 
	{
		this.id = id;
	}

	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		if (buf.readBoolean())
		{
		   id = new UUID(buf.readLong(), buf.readLong());
		}
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		if (id != null)
		{
			buf.writeBoolean(true);
			buf.writeLong(id.getMostSignificantBits());
			buf.writeLong(id.getLeastSignificantBits());
		}else buf.writeBoolean(false);
	}
	

	public static class Handler implements IMessageHandler<SendUUIDPacket, IMessage>
	{
		@Override
		public IMessage onMessage(SendUUIDPacket pkt, MessageContext ctx)
		{
			
			if (pkt.id == null)
			{
				GLoger.logWarn("NULL UUID reference in Send UUID packet! Please report to dev!");
			}
			else
			{
				GuiDockingPort.entId = pkt.id;

			}

			return null;
		}
	}
}