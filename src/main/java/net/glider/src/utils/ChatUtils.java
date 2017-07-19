
package net.glider.src.utils;

import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.PlayerChatMessagePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

/**
 * Helper class for chat messages and formatting 
 */
public final class ChatUtils {
	/**
	 * Alters color of a IChatComponent and returns it. Returning the param allows for a chat message to be constructed and colored in one line.
	 */
	public static IChatComponent modifyColor(IChatComponent chat, EnumChatFormatting format)
	{
		if (format.isColor())
		{
			chat.getChatStyle().setColor(format);
		}
		return chat;
	}
	
	public static void SendChatMessageOnClient(EntityPlayer player, IChatComponent comp)
	{
	//	PacketHandler.sendTo(new PlayerChatMessagePacket(comp), (EntityPlayerMP) player);
		player.addChatComponentMessage(comp);
	}
}
