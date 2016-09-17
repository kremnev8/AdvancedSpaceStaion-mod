
package net.glider.src.utils;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class LocalizedString extends IString {
	
	public String input;
	public EnumChatFormatting modifier;
	
	public LocalizedString(String input, EnumChatFormatting color)
	{
		this.input = input;
		this.modifier = color;
	}
	
	public String getInput()
	{
		return input;
	}
	
	public EnumChatFormatting getColor()
	{
		return modifier;
	}
	
	public IChatComponent getFormatedText()
	{
		IChatComponent comp = new ChatComponentText(StatCollector.translateToLocal(input));
		if (modifier != null)
		{
			comp = ChatUtils.modifyColor(comp, modifier);
		}
		return comp;
	}
	
}
