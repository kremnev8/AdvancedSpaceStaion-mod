package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.gui.GuiDockingPort;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.utils.GLoger;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class InvScalePacket implements IMessage
{
	
	private int x;
	private int y;
	private int z;
	private int newSize;
	
	public InvScalePacket() {}
	
	public InvScalePacket(int s, int x, int y, int z) 
	{
		this.newSize = s;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		x = ByteBufUtils.readVarInt(buf, 5);
		y = ByteBufUtils.readVarInt(buf, 5);
		z = ByteBufUtils.readVarInt(buf, 5);
		newSize = ByteBufUtils.readVarInt(buf, 5);
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
		ByteBufUtils.writeVarInt(buf, newSize, 5);
	}
	

	public static class Handler implements IMessageHandler<InvScalePacket, IMessage>
	{
		@Override
		public IMessage onMessage(InvScalePacket pkt, MessageContext ctx)
		{
			TileEntityDockingPort tile = (TileEntityDockingPort)Minecraft.getMinecraft().theWorld.getTileEntity(pkt.x, pkt.y, pkt.z);

			if (tile == null)
			{
				GLoger.logInfo("NULL tile entity reference in Docking port scaling update packet! Please report to dev!");
			}
			else
			{
				tile.setSizeInventory(pkt.newSize);
				GuiDockingPort.dirty = true;

			}

			return null;
		}
	}
}