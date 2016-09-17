
package net.glider.src.utils;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public abstract class IString {
	
	public abstract String getInput();
	
	public abstract EnumChatFormatting getColor();
	
	public abstract IChatComponent getFormatedText();
	
}