
package net.glider.src.utils;

import java.util.Iterator;

import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.IChatComponent;

public class LocalizedChatComponent extends ChatComponentStyle {
	private final IString text;
	private static final String __OBFID = "CL_00001269";
	
	public LocalizedChatComponent(String input)
	{
		this.text = new LocalizedString(input, null);
		this.getChatStyle().setColor(text.getColor());
	}
	
	public LocalizedChatComponent(IString input)
	{
		this.text = input;
		this.getChatStyle().setColor(input.getColor());
	}
	
	/**
	 * Gets the text value of this ChatComponentText.
	 */
	public IString getChatComponentText_TextValue()
	{
		return this.text;
	}
	
	/**
	 * Gets the text of this component, without any special formatting codes added, for chat.
	 */
	public String getUnformattedTextForChat()
	{
		IChatComponent comp = text.getFormatedText();
		return comp.getUnformattedTextForChat();
	}
	
	/**
	 * Creates a copy of this component. Almost a deep copy, except the style is shallow-copied.
	 */
	public LocalizedChatComponent createCopy()
	{
		LocalizedChatComponent chatcomponenttext = new LocalizedChatComponent(this.text);
		chatcomponenttext.setChatStyle(this.getChatStyle().createShallowCopy());
		Iterator iterator = this.getSiblings().iterator();
		
		while (iterator.hasNext())
		{
			IChatComponent ichatcomponent = (IChatComponent) iterator.next();
			chatcomponenttext.appendSibling(ichatcomponent.createCopy());
		}
		
		return chatcomponenttext;
	}
	
	public boolean equals(Object p_equals_1_)
	{
		if (this == p_equals_1_)
		{
			return true;
		} else if (!(p_equals_1_ instanceof LocalizedChatComponent))
		{
			return false;
		} else
		{
			LocalizedChatComponent chatcomponenttext = (LocalizedChatComponent) p_equals_1_;
			return this.text.equals(chatcomponenttext.getChatComponentText_TextValue()) && super.equals(p_equals_1_);
		}
	}
	
	public String toString()
	{
		return "TextComponent{text=\'" + this.text + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatStyle() + '}';
	}
}