package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SetThirdPersonPacket implements IMessage
{
	private int thirdperson;
	
	
	public SetThirdPersonPacket() {}
	
	public SetThirdPersonPacket(int tp)
	{		
		thirdperson = tp;
	}
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		thirdperson = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(thirdperson);
	}
	
	

	public static class Handler implements IMessageHandler<SetThirdPersonPacket, IMessage>
	{
		@Override
		public IMessage onMessage(SetThirdPersonPacket pkt, MessageContext ctx)
		{
			Minecraft.getMinecraft().gameSettings.thirdPersonView = pkt.thirdperson;

			return null;
		}
		
		
	}
}