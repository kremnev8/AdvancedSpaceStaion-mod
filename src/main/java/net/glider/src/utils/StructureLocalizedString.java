
package net.glider.src.utils;

import net.glider.src.strucures.Structure;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class StructureLocalizedString extends IString {
	
	public Structure input;
	public EnumChatFormatting modifier;
	
	public StructureLocalizedString(Structure str, EnumChatFormatting color)
	{
		this.input = str;
		this.modifier = color;
	}
	
	public String getInput()
	{
		return input.getUnlocalizedName();
	}
	
	public EnumChatFormatting getColor()
	{
		return modifier;
	}
	
	public IChatComponent getFormatedText()
	{
		IChatComponent comp = new ChatComponentText(input.getName().toLowerCase());
		if (modifier != null)
		{
			comp = ChatUtils.modifyColor(comp, modifier);
		}
		return comp;
	}
	
}
