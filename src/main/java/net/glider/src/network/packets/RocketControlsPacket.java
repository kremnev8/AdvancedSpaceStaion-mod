package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RocketControlsPacket implements IMessage
{
	
	
	public RocketControlsPacket() {}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
	}
	
	

	public static class Handler implements IMessageHandler<RocketControlsPacket, IMessage>
	{
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(RocketControlsPacket pkt, MessageContext ctx)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				
	            player.addChatMessage(new ChatComponentText(GameSettings.getKeyDisplayString(KeyHandlerClient.spaceKey.getKeyCode()) + "  - " + GCCoreUtil.translate("gui.rocket.launch.name")));
	       //     player.addChatMessage(new ChatComponentText(GameSettings.getKeyDisplayString(KeyHandlerClient.leftKey.getKeyCode()) + " / " + GameSettings.getKeyDisplayString(KeyHandlerClient.rightKey.getKeyCode()) + "  - " + GCCoreUtil.translate("gui.rocket.turn.name")));
	       //     player.addChatMessage(new ChatComponentText(GameSettings.getKeyDisplayString(KeyHandlerClient.accelerateKey.getKeyCode()) + " / " + GameSettings.getKeyDisplayString(KeyHandlerClient.decelerateKey.getKeyCode()) + "  - " + GCCoreUtil.translate("gui.rocket.updown.name")));
	            player.addChatMessage(new ChatComponentText(GameSettings.getKeyDisplayString(KeyHandlerClient.openFuelGui.getKeyCode()) + "       - " + GCCoreUtil.translate("gui.rocket.inv.name")));
	            return null;
		}
		
		
	}
}