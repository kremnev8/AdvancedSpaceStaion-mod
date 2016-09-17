
package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.strucures.Structure;
import net.glider.src.utils.IString;
import net.glider.src.utils.LocalizedChatComponent;
import net.glider.src.utils.LocalizedString;
import net.glider.src.utils.StructureLocalizedString;
import net.glider.src.utils.USString;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerChatMessagePacket implements IMessage {
	
	private IChatComponent comp;
	
	public PlayerChatMessagePacket()
	{}
	
	public PlayerChatMessagePacket(IChatComponent comp)
	{
		this.comp = comp;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		int comps = 0;
		if (comp != null)
		{
			comps = 1;
			NBTTagCompound tag2 = new NBTTagCompound();
			if (comp instanceof LocalizedChatComponent)
			{
				tag2.setBoolean("LOCALIZED", true);
				IString str = ((LocalizedChatComponent) comp).getChatComponentText_TextValue();
				tag2.setBoolean("ISSTR", str instanceof StructureLocalizedString);
				tag2.setBoolean("ISBASIC", str instanceof USString);
				tag2.setString("INPUT", str.getInput());
				tag2.setString("COLOR", str.getColor().getFriendlyName());
				
			} else
			{
				tag2.setBoolean("LOCALIZED", false);
				tag2.setString("INPUT", comp.getUnformattedText());
				tag2.setString("COLOR", comp.getChatStyle().getColor().getFriendlyName());
			}
			tag.setTag("MAINICHAT", tag2);
			if (comp.getSiblings() != null && comp.getSiblings().size() > 0)
			{
				for (int i = 0; i < comp.getSiblings().size(); i++)
				{
					IChatComponent subL = (IChatComponent) comp.getSiblings().get(i);
					if (subL != null)
					{
						comps++;
						NBTTagCompound tagSub = new NBTTagCompound();
						if (subL instanceof LocalizedChatComponent)
						{
							tagSub.setBoolean("LOCALIZED", true);
							IString str = ((LocalizedChatComponent) subL).getChatComponentText_TextValue();
							tagSub.setBoolean("ISSTR", str instanceof StructureLocalizedString);
							tagSub.setBoolean("ISBASIC", str instanceof USString);
							tagSub.setString("INPUT", str.getInput());
							tagSub.setString("COLOR", str.getColor().getFriendlyName());
							
						} else
						{
							tagSub.setBoolean("LOCALIZED", false);
							tagSub.setString("INPUT", subL.getUnformattedText());
							tagSub.setString("COLOR", subL.getChatStyle().getColor().getFriendlyName());
						}
						tag.setTag("SUBICHAT" + i, tagSub);
					}
					
				}
			}
			tag.setInteger("ICHATN", comps);
		}
		ByteBufUtils.writeTag(buf, tag);
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		if (tag.hasKey("ICHATN") && tag.getInteger("ICHATN") > 0)
		{
			NBTTagCompound tagM = tag.getCompoundTag("MAINICHAT");
			IChatComponent MainChat;
			if (tagM.getBoolean("LOCALIZED"))
			{
				IString str;
				if (tagM.getBoolean("ISSTR"))
				{
					str = new StructureLocalizedString(Structure.FindStructure(tagM.getString("INPUT")), EnumChatFormatting.getValueByName(tagM.getString("COLOR")));
				} else if (tagM.getBoolean("ISBASIC"))
				{
					str = new USString(tagM.getString("INPUT"), EnumChatFormatting.getValueByName(tagM.getString("COLOR")));
				} else
				{
					str = new LocalizedString(tagM.getString("INPUT"), EnumChatFormatting.getValueByName(tagM.getString("COLOR")));
				}
				
				MainChat = new LocalizedChatComponent(str);
			} else
			{
				MainChat = new ChatComponentText(tagM.getString("INPUT"));
				MainChat.getChatStyle().setColor(EnumChatFormatting.getValueByName(tagM.getString("COLOR")));
			}
			if (tag.getInteger("ICHATN") > 1)
			{
				for (int i = 0; i < tag.getInteger("ICHATN"); i++)
				{
					if (tag.hasKey("SUBICHAT" + i))
					{
						NBTTagCompound tagSub = tag.getCompoundTag("SUBICHAT" + i);
						IChatComponent SubChat;
						if (tagSub.getBoolean("LOCALIZED"))
						{
							IString str;
							if (tagSub.getBoolean("ISSTR"))
							{
								str = new StructureLocalizedString(Structure.FindStructure(tagSub.getString("INPUT")), EnumChatFormatting.getValueByName(tagSub.getString("COLOR")));
							} else if (tagSub.getBoolean("ISBASIC"))
							{
								str = new USString(tagSub.getString("INPUT"), EnumChatFormatting.getValueByName(tagSub.getString("COLOR")));
							} else
							{
								str = new LocalizedString(tagSub.getString("INPUT"), EnumChatFormatting.getValueByName(tagSub.getString("COLOR")));
							}
							
							SubChat = new LocalizedChatComponent(str);
						} else
						{
							SubChat = new ChatComponentText(tagSub.getString("INPUT"));
							SubChat.getChatStyle().setColor(EnumChatFormatting.getValueByName(tagSub.getString("COLOR")));
						}
						MainChat.appendSibling(SubChat);
						
					}
					
				}
			}
			comp = MainChat;
		}
	}
	
	public static class Handler implements IMessageHandler<PlayerChatMessagePacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(PlayerChatMessagePacket pkt, MessageContext ctx)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null)
			{
				if (pkt.comp != null)
				{
					player.addChatComponentMessage(pkt.comp);
				}
			}
			
			return null;
		}
		
	}
}