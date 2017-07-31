package net.glider.src.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiMLabel extends GuiButton {
	/** Button width in pixels */
	public int width;
	/** Button height in pixels */
	public int height;
	/** The x position of this control. */
	public int xPosition;
	/** The y position of this control. */
	public int yPosition;
	/** The string displayed on this control. */
	public String displayString;
	public int id;
	/** True if this control is enabled, false to disable. */
	public boolean enabled;
	/** Hides the button completely if false. */
	public boolean visible;
	protected boolean field_146123_n;
	public int packedFGColour;
	
	public GuiMLabel(int id, int x, int y, String text)
	{
		super(id, x, y, 0, 0, text);
		super.enabled = false;
		super.visible = false;
		this.width = 200;
		this.height = 20;
		this.enabled = true;
		this.visible = true;
		this.id = id;
		this.xPosition = x;
		this.yPosition = y;
		this.width = 1;
		this.height = 1;
		this.displayString = text;
	}
	
	/**
	 * Draws this button to the screen.
	 */
	public void drawButton(Minecraft mine, int x, int y)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = mine.fontRenderer;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			int l = 14737632;
			
			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}
	
	public boolean mousePressed(Minecraft mine, int x, int y)
	{
		return false;
	}
	
	public int getButtonWidth()
	{
		return this.width;
	}
	
	public int func_154310_c()
	{
		return this.height;
	}
}