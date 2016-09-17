
package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient;
import net.glider.src.utils.ChatUtils;
import net.glider.src.utils.LocalizedChatComponent;
import net.glider.src.utils.LocalizedString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RocketControlsPacket implements IMessage {
	
	public RocketControlsPacket()
	{}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{}
	
	@Override
	public void toBytes(ByteBuf buf)
	{}
	
	public static class Handler implements IMessageHandler<RocketControlsPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(RocketControlsPacket pkt, MessageContext ctx)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			
			ChatUtils.SendChatMessageOnClient(player, new ChatComponentText(GameSettings.getKeyDisplayString(KeyHandlerClient.spaceKey.getKeyCode()) + "  - ").appendSibling(new LocalizedChatComponent(new LocalizedString("gui.rocket.launch.name", null))));
			ChatUtils.SendChatMessageOnClient(player, new ChatComponentText(GameSettings.getKeyDisplayString(KeyHandlerClient.openFuelGui.getKeyCode()) + "  - ").appendSibling(new LocalizedChatComponent(new LocalizedString("gui.rocket.inv.name", null))));
			return null;
		}
		
	}
}