package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.utils.GLoger;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class DismountPacket implements IMessage
{
	
	
	public DismountPacket() {}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
	}
	
	

	public static class Handler implements IMessageHandler<DismountPacket, IMessage>
	{
		@Override
		public IMessage onMessage(DismountPacket pkt, MessageContext ctx)
		{
			
			
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player.ridingEntity instanceof EntityRocketFakeTiered)
				{
					
					EntityRocketFakeTiered rocket = (EntityRocketFakeTiered)player.ridingEntity;
					if (!rocket.shouldIgnoreShiftExit())rocket.QuitRocket(player);
				}
				
			}else
			{
				GLoger.logWarn("An error on handling dismount packet. report this to dev!");
				GLoger.logWarn("info: Player is null");
			}
					
			

			return null;
		}
		
		
	}
}