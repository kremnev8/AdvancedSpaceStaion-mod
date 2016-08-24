package net.glider.src.gui;

import net.glider.src.gui.GuiButtonBuilder.GuiIconsUtil;
import net.glider.src.strucures.Structure;
import net.glider.src.strucures.StructureRotatable;
import net.glider.src.utils.ForgeDirectionUtils;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonRemover extends GuiButton {
	protected static final ResourceLocation buttonTextures = new ResourceLocation(GliderModInfo.ModTestures, "textures/Remover.png");

	protected static final ResourceLocation Icons = new ResourceLocation(GliderModInfo.ModTestures, "textures/Icons.png");
	/** Button width in pixels */
	public int width = 127;
	/** Button height in pixels */
	public int height = 24;
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
	public boolean Enabled;

	public int rot = 0;
	public ForgeDirection dir = ForgeDirection.UNKNOWN;
	public String strName;
	private int NyPos;

	private int ZeroPos;

	public int[] strPos;
	public boolean visSelf = true;

	public GuiButtonRemover(int id, int xpos, int ypos, String Dispstring, Structure str, int y)
	{
		super(id, xpos, ypos, 127, 24, Dispstring);
		super.visible = false;
		this.enabled = true;
		this.visible = true;
		this.id = id;
		this.xPosition = xpos;
		this.yPosition = ypos;
		this.displayString = Dispstring;
		if (str != null)
		{
			this.rot = str.placementRotation;
			this.dir = str.placementDir;
			this.strName = str.getUnlocalizedName();
			ZeroPos = y;
			strPos = str.placementPos;
		}
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

		NyPos = this.yPosition - (28 * GuiRemover.move);
		if (visSelf)
		{
			if (NyPos < ZeroPos + 16 || NyPos > ZeroPos + 99)
			{
				this.visible = false;
			} else this.visible = true;
		}
		if (this.visible)
		{
			FontRenderer fontrenderer = p_146112_1_.fontRenderer;
			p_146112_1_.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= NyPos && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < NyPos + this.height;
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			if (getHoverState(this.field_146123_n) == 0)
			{
				this.drawTexturedModalRect(this.xPosition, NyPos, 0, 127, this.width, this.height);//disable
			} else if (getHoverState(this.field_146123_n) != 2 && Enabled)
			{
				this.drawTexturedModalRect(this.xPosition, NyPos, 0, 127, this.width, this.height);//disable
				// this.drawTexturedModalRect(this.xPosition, NyPos, 0, 177, this.width , this.height);//enable
			} else if (getHoverState(this.field_146123_n) == 2)
			{
				this.drawTexturedModalRect(this.xPosition, NyPos, 0, 152, this.width, this.height);//hover
			} else this.drawTexturedModalRect(this.xPosition, NyPos, 0, 127, this.width, this.height);//disable

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

			String NewDispStr = "";
			Structure str = Structure.FindStructure(strName);
			if (str instanceof StructureRotatable)
			{
				((StructureRotatable) str).setRotation(rot);
			}
			if (str != null)
			{
				NewDispStr = str.getName();
			}

			fontrenderer.drawString(NewDispStr, (this.xPosition + this.width / 2) - 40, NyPos + (this.height - 20) - 3 / 2, l, false);
			if (this.dir != ForgeDirection.UP && this.dir != ForgeDirection.DOWN)
			{
				NewDispStr = "ForgeDir: " + ForgeDirectionUtils.GetLocolizedName(dir);
				fontrenderer.drawString(NewDispStr, (this.xPosition + this.width / 2) - 40, NyPos + (this.height - 11) - 3 / 2, l, false);
			}
			//  this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}

	private void renderIcons()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(Icons);
		if (strName.equals(Structure.HOLLID))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "hall", 0);
		} else if (strName.equals(Structure.CORNERID))
		{
			if (dir == ForgeDirection.WEST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 0);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 1);
				} else DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 0);
			} else if (dir == ForgeDirection.EAST)
			{
				if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 0);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 1);
				} else
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 0);
				}
			} else if (dir == ForgeDirection.NORTH)
			{
				if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 0);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 1);
				} else
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 0);
				}
			} else if (dir == ForgeDirection.SOUTH)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 0);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 1);
				} else DrawGuiIcon(this.xPosition + 3, NyPos + 3, "corner", 1);
			}
		} else if (strName.equals(Structure.CROSSROADID))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "crossroad", 0);
		} else if (strName.equals(Structure.HALLAIRLOCKID))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "airlock", 0);
		} else if (strName.equals(Structure.WINDOWID))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "window", rot);
		} else if (strName.equals(Structure.CUPOLAID))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "cupola", 0);
		} else if (strName.equals(Structure.DOCKPORTID))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "dockport", 0);
		} else if (strName.equals(Structure.SOLARPANELID))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "solarpanel", rot);
		} else if (strName.equals(Structure.THALLID))
		{
			if (dir == ForgeDirection.WEST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 3);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 2);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 1);
				}
			} else if (dir == ForgeDirection.EAST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 3);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 1);
				}
			} else if (dir == ForgeDirection.NORTH)
			{
				if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 3);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 1);
				} else
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 3);
				}
			} else if (dir == ForgeDirection.SOUTH)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 3);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.xPosition + 3, NyPos + 3, "thall", 1);
				}
			}
		} else if (strName.equals(Structure.BIGHHALL))
		{
			if (rot == 1)
			{
				DrawGuiIcon(this.xPosition + 3, NyPos + 3, "bighall_normal", 2);
			} else if (rot == 0)
			{
				DrawGuiIcon(this.xPosition + 3, NyPos + 3, "bighall_normal", 3);
			}
		} else if (strName.equals(Structure.GREENHOUSE))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "greenhouse", 0);
		} else if (strName.equals(Structure.PIERCE))
		{
			DrawGuiIcon(this.xPosition + 3, NyPos + 3, "pierce", 0);
		}
		if (Enabled)
		{
			DrawGuiIcon(this.xPosition + 2, NyPos + 2, "redcross", 0);
		}
		if (GuiRemover.Iselected[0] == true && !Enabled)
		{
			DrawGuiIcon(this.xPosition + 115, NyPos + 7, "redstar", 0);
		}

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
		return this.enabled && this.visible && p_146116_2_ >= this.xPosition && p_146116_3_ >= NyPos && p_146116_2_ < this.xPosition + this.width && p_146116_3_ < NyPos + this.height;
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

}