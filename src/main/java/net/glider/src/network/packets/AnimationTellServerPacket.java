
package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.glider.src.dimensions.DockingPortSaveData;
import net.glider.src.entity.ExtendedPlayer;
import net.glider.src.network.PacketHandler;
import net.glider.src.tiles.TileEntityGravitySource;
import net.glider.src.utils.GLoger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class AnimationTellServerPacket implements IMessage {
	private String name;
	private boolean act;

	public AnimationTellServerPacket()
	{}

	public AnimationTellServerPacket(String name, boolean act)
	{
		this.name = name;
		this.act = act;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		name = ByteBufUtils.readUTF8String(buf);
		act = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, name);
		buf.writeBoolean(act);
	}

	public static class Handler implements IMessageHandler<AnimationTellServerPacket, IMessage> {
		@Override
		public IMessage onMessage(AnimationTellServerPacket pkt, MessageContext ctx)
		{

			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				ExtendedPlayer prop = ExtendedPlayer.get(player);
				if (pkt.act)
				{
					prop.getAnimationHandler().activateAnimation(pkt.name, 0);
				} else
				{
					prop.getAnimationHandler().stopAnimation(pkt.name);
				}
				PacketHandler.sendToDimension(new OtherPlayerAnimationPacket(pkt.name, player.getCommandSenderName(), pkt.act), player.worldObj.provider.dimensionId);
				GLoger.logInfo("packet+1");
			} else
			{
				GLoger.logWarn("An error on handling dismount packet. report this to dev!");
				GLoger.logWarn("info: Player is null");
			}

			return null;
		}

	}
}