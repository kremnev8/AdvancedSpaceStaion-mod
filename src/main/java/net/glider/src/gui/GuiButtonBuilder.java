package net.glider.src.gui;

import java.util.HashMap;

import net.glider.src.strucures.Structure;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonBuilder extends GuiButton {
	protected static final ResourceLocation buttonTextures = new ResourceLocation(GliderModInfo.ModTestures, "textures/Builder.png");

	protected static final ResourceLocation Icons = new ResourceLocation(GliderModInfo.ModTestures, "textures/Icons.png");
	/** Button width in pixels */
	public int width = 64;
	/** Button height in pixels */
	public int height = 32;
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
	private static final String __OBFID = "CL_00000668";
	public int packedFGColour;
	private boolean Enabled;

	public int rot = 0;
	private ForgeDirection dir = ForgeDirection.UNKNOWN;

	public GuiButtonBuilder(int id, int xpos, int ypos, String Dispstring)
	{
		super(id, xpos, ypos, 64, 32, Dispstring);
		super.visible = false;
		this.enabled = true;
		this.visible = true;
		this.id = id;
		this.xPosition = xpos;
		this.yPosition = ypos;
		this.displayString = Dispstring;
	}

	public void setEnabled(boolean e)
	{
		this.Enabled = e;
	}

	public void setRotation(int rot)
	{
		this.rot = rot;
	}

	public void setDirection(ForgeDirection dir)
	{
		this.dir = dir;
	}

	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
	 * this button and 2 if it IS hovering over this button.
	 */
	public int getHoverState(boolean p_146114_1_)
	{
		byte b0 = 1;

		if (!this.enabled)
		{
			b0 = 0;
		} else if (p_146114_1_)
		{
			b0 = 2;
		}

		return b0;
	}

	/**
	 * Draws this button to the screen.
	 */
	public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = p_146112_1_.fontRenderer;
			p_146112_1_.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			if (getHoverState(this.field_146123_n) == 0)
			{
				this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 162, this.width, this.height + 2);//disable
			} else if (getHoverState(this.field_146123_n) != 2 && Enabled)
			{
				this.drawTexturedModalRect(this.xPosition, this.yPosition, 160, 162, this.width, this.height + 2);//enable
			} else if (getHoverState(this.field_146123_n) == 2)
			{
				this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 196, this.width, this.height + 2);//hover
			} else this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 162, this.width, this.height + 2);//disable

			try
			{
				renderIcons();
			} catch (Throwable error)
			{
				GLoger.logWarn("An error ocured in GuiButtonBuilder:", error);
			}
			this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
			int l = 4210752;

			if (packedFGColour != 0)
			{
				l = packedFGColour;
			} else if (!this.enabled)
			{
				l = 10526880;
			} else if (this.field_146123_n)
			{
				l = 16777120;
			}
			// while (true)
			//  {
			//  GLoger.logInfo(Minecraft.getMinecraft().getLanguageManager().getLanguages().iterator().next());
			//  if (Minecraft.getMinecraft().getLanguageManager().getLanguages().iterator().hasNext())
			//  {
			//	   break;
			// }
			//  }
			int a;
			if (Minecraft.getMinecraft().getLanguageManager().isCurrentLocaleUnicode()) a = 5;
			else a = 0;
			fontrenderer.drawString(this.displayString, this.xPosition + 15 + (int) (width / 4.5D) - (fontrenderer.getStringWidth(this.displayString) / 2), this.yPosition + (this.height - 8) - 3 / 2, l, false);
			//(int) (width / 4.5D) - (fontrenderer.getStringWidth(this.displayString)) / 2)
			//  this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}

	private void renderIcons()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(Icons);
		if (GuiBuilder.AstG.size() != 0)
		{
			if (GuiBuilder.AstG.containsKey(Structure.HOLLID) && id == (int) GuiBuilder.AstG.get(Structure.HOLLID))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "hall", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.CORNERID) && id == (int) GuiBuilder.AstG.get(Structure.CORNERID))
			{
				if (dir == ForgeDirection.WEST)
				{
					if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 0);
					} else if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 1);
					}
				} else if (dir == ForgeDirection.EAST)
				{
					if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 0);
					} else if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 1);
					} else
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 0);
					}
				} else if (dir == ForgeDirection.NORTH)
				{
					if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 0);
					} else if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 1);
					} else
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 0);
					}
				} else if (dir == ForgeDirection.SOUTH)
				{
					if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 0);
					} else if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "corner", 1);
					}
				}
			} else if (GuiBuilder.AstG.containsKey(Structure.CROSSROADID) && id == (int) GuiBuilder.AstG.get(Structure.CROSSROADID))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "crossroad", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.HALLAIRLOCKID) && id == (int) GuiBuilder.AstG.get(Structure.HALLAIRLOCKID))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "airlock", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.WINDOWID) && id == (int) GuiBuilder.AstG.get(Structure.WINDOWID))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "window", rot);
			} else if (GuiBuilder.AstG.containsKey(Structure.CUPOLAID) && id == (int) GuiBuilder.AstG.get(Structure.CUPOLAID))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "cupola", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.DOCKPORTID) && id == (int) GuiBuilder.AstG.get(Structure.DOCKPORTID))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "dockport", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.SOLARPANELID) && id == (int) GuiBuilder.AstG.get(Structure.SOLARPANELID))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "solarpanel", rot);
			} else if (GuiBuilder.AstG.containsKey(Structure.THALLID) && id == (int) GuiBuilder.AstG.get(Structure.THALLID))
			{
				if (dir == ForgeDirection.WEST)
				{
					if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 3);
					} else if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 2);
					} else if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 1);
					}
				} else if (dir == ForgeDirection.EAST)
				{
					if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 3);
					} else if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 2);
					} else if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 1);
					}
				} else if (dir == ForgeDirection.NORTH)
				{
					if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 3);
					} else if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 2);
					} else if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 1);
					} else
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 3);
					}
				} else if (dir == ForgeDirection.SOUTH)
				{
					if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 3);
					} else if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 2);
					} else if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "thall", 1);
					}
				}
			} else if (GuiBuilder.AstG.containsKey(Structure.BIGHHALL) && id == (int) GuiBuilder.AstG.get(Structure.BIGHHALL))
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "bighall_normal", 3);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "bighall_normal", 2);
				}
			} else if (GuiBuilder.AstG.containsKey(Structure.GREENHOUSE) && id == (int) GuiBuilder.AstG.get(Structure.GREENHOUSE))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "greenhouse", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.PIERCE) && id == (int) GuiBuilder.AstG.get(Structure.PIERCE))
			{
				DrawGuiIcon(this.xPosition + 3, this.yPosition + 3, "pierce", 0);
			}
		}
		//  Minecraft.getMinecraft().getTextureManager().bindTexture(buttonTextures);
	}

	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_)
	{
	}

	/**
	 * Fired when the mouse button is released. Equivalent of
	 * MouseListener.mouseReleased(MouseEvent e).
	 */
	public void mouseReleased(int p_146118_1_, int p_146118_2_)
	{
	}

	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
	{
		return this.enabled && this.visible && p_146116_2_ >= this.xPosition && p_146116_3_ >= this.yPosition && p_146116_2_ < this.xPosition + this.width && p_146116_3_ < this.yPosition + this.height;
	}

	public boolean func_146115_a()
	{
		return this.field_146123_n;
	}

	public void func_146111_b(int p_146111_1_, int p_146111_2_)
	{
	}

	public void func_146113_a(SoundHandler p_146113_1_)
	{
		p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
	}

	public int getButtonWidth()
	{
		return this.width;
	}

	public int func_154310_c()
	{
		return this.height;
	}

	public void DrawGuiIcon(int x, int y, String name, int rot)
	{
		GuiIconsUtil.initMap();

		int[] Iconpos = GuiIconsUtil.Icons.get(name + "_" + rot);
		int[] Iconsize = new int[] { 16, 16 };
		if (name.equals("redcross"))
		{
			Iconsize = new int[] { 18, 18 };
		} else if (name.equals("redstar"))
		{
			Iconsize = new int[] { 9, 9 };
		}
		this.drawTexturedModalRect(x, y, Iconpos[0], Iconpos[1], Iconsize[0], Iconsize[1]);

	}

	public static class GuiIconsUtil {
		public static HashMap<String, int[]> Icons = new HashMap<String, int[]>();
		private static boolean init = false;

		public static void initMap()
		{
			if (!init)
			{
				Icons.clear();
				Icons.put("hall_0", new int[] { 1, 1 });
				Icons.put("hall_1", new int[] { 1 + (18 * 1), 1 });
				Icons.put("crossroad_0", new int[] { 1 + (18 * 2), 1 });
				Icons.put("cupola_0", new int[] { 1 + (18 * 3), 1 });
				Icons.put("bighall_small_0", new int[] { 1 + (18 * 4), 1 });
				Icons.put("pierce_0", new int[] { 1 + (18 * 5), 1 });

				Icons.put("corner_0", new int[] { 1 + (18 * 0), 1 + (18 * 1) });
				Icons.put("corner_1", new int[] { 1 + (18 * 1), 1 + (18 * 1) });
				Icons.put("corner_2", new int[] { 1 + (18 * 2), 1 + (18 * 1) });
				Icons.put("corner_3", new int[] { 1 + (18 * 3), 1 + (18 * 1) });
				Icons.put("greenhouse_0", new int[] { 1 + (18 * 4), 1 + (18 * 1) });

				Icons.put("airlock_0", new int[] { 1 + (18 * 0), 1 + (18 * 2) });
				Icons.put("airlock_1", new int[] { 1 + (18 * 1), 1 + (18 * 2) });
				Icons.put("window_1", new int[] { 1 + (18 * 2), 1 + (18 * 2) });

				Icons.put("redcross_0", new int[] { 54, 36 });
				Icons.put("redstar_0", new int[] { 1 + (18 * 4), 37 });

				Icons.put("window_0", new int[] { 1 + (18 * 0), 1 + (18 * 3) });
				Icons.put("dockport_0", new int[] { 1 + (18 * 1), 1 + (18 * 3) });
				Icons.put("solarpanel_0", new int[] { 1 + (18 * 2), 1 + (18 * 3) });
				Icons.put("solarpanel_1", new int[] { 1 + (18 * 3), 1 + (18 * 3) });

				Icons.put("thall_3", new int[] { 1 + (18 * 0), 1 + (18 * 4) });
				Icons.put("thall_2", new int[] { 1 + (18 * 1), 1 + (18 * 4) });
				Icons.put("thall_1", new int[] { 1 + (18 * 2), 1 + (18 * 4) });
				Icons.put("thall_0", new int[] { 1 + (18 * 3), 1 + (18 * 4) });

				Icons.put("bighall_normal_0", new int[] { 1 + (18 * 4), 57 });
				Icons.put("bighall_normal_1", new int[] { 1 + (18 * 4) + 16, 57 });
				Icons.put("bighall_normal_2", new int[] { 1 + (18 * 4), 73 });
				Icons.put("bighall_normal_3", new int[] { 1 + (18 * 4) + 16, 73 });
				init = true;
			}
		}
	}
}