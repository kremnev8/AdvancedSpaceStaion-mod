
package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.entity.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OtherPlayerAnimationPacket implements IMessage {
	private String name;
	private String Plname;
	private boolean act;
	
	public OtherPlayerAnimationPacket()
	{}
	
	public OtherPlayerAnimationPacket(String name, String plName, boolean action)
	{
		this.name = name;
		this.Plname = plName;
		this.act = action;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, name);
		ByteBufUtils.writeUTF8String(buf, Plname);
		buf.writeBoolean(act);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		name = ByteBufUtils.readUTF8String(buf);
		Plname = ByteBufUtils.readUTF8String(buf);
		act = buf.readBoolean();
	}
	
	public static class Handler implements IMessageHandler<OtherPlayerAnimationPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(OtherPlayerAnimationPacket pkt, MessageContext ctx)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null)
			{
				if (player.getCommandSenderName().equals(pkt.Plname))
				{
					return null;
				}
				World world = player.worldObj;
				EntityPlayer otherPlayer = world.getPlayerEntityByName(pkt.Plname);
				if (otherPlayer != null)
				{
					ExtendedPlayer Otherprop = ExtendedPlayer.get(otherPlayer);
					if (pkt.act)
					{
						Otherprop.animH.activateAnimation(pkt.name, 0, false);
					} else
					{
						Otherprop.animH.stopAnimation(pkt.name, false);
					}
				}
			}
			
			return null;
		}
		
	}
}