package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.gui.ContainerArmorStand;
import net.glider.src.gui.ContainerArtificialGSource;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.tiles.TileEntityGravitySource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class GravityChangePacket implements IMessage {
	
	public GravityChangePacket()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
	}
	
	public static class Handler implements IMessageHandler<GravityChangePacket, IMessage> {
		@Override
		public IMessage onMessage(GravityChangePacket pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player.openContainer instanceof ContainerArtificialGSource)
				{
					if (((ContainerArtificialGSource) player.openContainer).tileEntity instanceof TileEntityGravitySource) ((TileEntityGravitySource) ((ContainerArtificialGSource) player.openContainer).tileEntity).gA_changed = true;
				}
			}
			
			return null;
		}
		
	}
}