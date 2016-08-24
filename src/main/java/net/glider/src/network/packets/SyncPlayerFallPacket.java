package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.utils.GLoger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SyncPlayerFallPacket implements IMessage
{
	private float fallDist;
	
	public SyncPlayerFallPacket() {}
	
	public SyncPlayerFallPacket(float falldist)
	{
		this.fallDist = falldist;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		fallDist = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeFloat(fallDist);
	}
	
	

	public static class Handler implements IMessageHandler<SyncPlayerFallPacket, IMessage>
	{
		@Override
		public IMessage onMessage(SyncPlayerFallPacket pkt, MessageContext ctx)
		{
			
			
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player != null)
				{
					player.fallDistance = pkt.fallDist;
					((EntityPlayerMP)player).handleFalling(pkt.fallDist, true);
				}
				
			}else
			{
				GLoger.logWarn("An error on handling sync player fall packet. report this to dev!");
				GLoger.logWarn("info: Player is null");
			}
					
			

			return null;
		}
		
		
	}
}