package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.GliderCore;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.gui.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class OpenRocketFuelGuiPacket implements IMessage
{
	private int entId;
	
	public OpenRocketFuelGuiPacket() {}
	
	public OpenRocketFuelGuiPacket(int id)
	{
		this.entId = id;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		entId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(entId);
	}
	
	
	public static class Handler implements IMessageHandler<OpenRocketFuelGuiPacket, IMessage> {
		@Override
		public IMessage onMessage(OpenRocketFuelGuiPacket pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				if (player.worldObj.getEntityByID(pkt.entId) != null)
				{
				  EntityRocketFakeTiered rocket = (EntityRocketFakeTiered)player.worldObj.getEntityByID(pkt.entId);	
				

				if (rocket.dockport != null)
				{
					player.openGui(GliderCore.instance, GuiHandler.DOCKINGPORTGUI, player.worldObj, rocket.dockport.xCoord, rocket.dockport.yCoord, rocket.dockport.zCoord);
				}
			}
			}
			return null;
		}

	}
}