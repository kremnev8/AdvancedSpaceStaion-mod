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
	
	private int NyPos;
	private int ZeroPos;
	public boolean visSelf = true;
	
	public GuiMLabel(int id, int x, int y, String text, int zp)
	{
		super(id, x, y, 1, 1, text);
		super.enabled = false;
		super.visible = false;
		this.enabled = true;
		this.visible = true;
		this.id = id;
		this.xPosition = x;
		this.yPosition = y;
		this.displayString = text;
		ZeroPos = zp;
	}
	
	/**
	 * Draws this button to the screen.
	 */
	public void drawButton(Minecraft mine, int x, int y)
	{
		NyPos = this.yPosition - (11 * GuiModificator.move);
		if (visSelf)
		{//133
			if (NyPos < ZeroPos - 25 || NyPos > ZeroPos + 157)
			{
				this.visible = false;
			} else this.visible = true;
		}
		if (this.visible)
		{
			zLevel = 10;
			FontRenderer fontrenderer = mine.fontRenderer;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			int l = 4210752;
			
			fontrenderer.drawString(this.displayString, this.xPosition + (int) (133 / 2) - (fontrenderer.getStringWidth(this.displayString) / 2), NyPos, l, false);
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