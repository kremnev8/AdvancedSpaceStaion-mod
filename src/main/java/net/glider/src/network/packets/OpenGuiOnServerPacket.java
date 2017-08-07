package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.GliderCore;
import net.glider.src.gui.GuiHandler;
import net.glider.src.gui.GuiModificator;
import net.glider.src.gui.GuiRemover;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class OpenGuiOnServerPacket implements IMessage {
	private int guiId;
	
	private int x;
	private int y;
	private int z;
	
	public OpenGuiOnServerPacket()
	{
	}
	
	public OpenGuiOnServerPacket(int id, int x, int y, int z)
	{
		this.guiId = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		guiId = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(guiId);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
	
	public static class Handler implements IMessageHandler<OpenGuiOnServerPacket, IMessage> {
		@Override
		public IMessage onMessage(OpenGuiOnServerPacket pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				player.openGui(GliderCore.instance, pkt.guiId, player.worldObj, pkt.x, pkt.y, pkt.z);
				
			}
			return null;
		}
		
	}
}