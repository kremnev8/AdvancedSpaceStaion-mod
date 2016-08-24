package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.gui.GuiRemover;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CloseScreenPacket implements IMessage {

	public CloseScreenPacket()
	{
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
	}

	public static class Handler implements IMessageHandler<CloseScreenPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(CloseScreenPacket pkt, MessageContext ctx)
		{
			GuiRemover.CloseThis = true;
			return null;
		}

	}
}