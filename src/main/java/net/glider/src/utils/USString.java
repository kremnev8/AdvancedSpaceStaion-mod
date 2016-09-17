
package net.glider.src.utils;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class USString extends IString {
	
	public String input;
	public EnumChatFormatting modifier;
	
	public USString(String input, EnumChatFormatting color)
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
		IChatComponent comp = new ChatComponentText(input);
		if (modifier != null)
		{
			comp = ChatUtils.modifyColor(comp, modifier);
		}
		return comp;
	}
	
}