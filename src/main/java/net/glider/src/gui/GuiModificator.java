package net.glider.src.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.DeconstructPacket;
import net.glider.src.strucures.Structure;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.ChatUtils;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import cofh.core.gui.GuiTextEntry;

public class GuiModificator extends GuiContainer {
	
	private ResourceLocation texture = new ResourceLocation(GliderModInfo.ModTestures, "textures/Modificator.png");
	private int Xsize = 160;
	private int Ysize = 163;
	
	private int x;
	private int y;
	private int z;
	private World world;
	private GuiButton selectedButton;
	
	public static Structure object;
	public static List<Structure> ChildObjects = new ArrayList<Structure>();
	public static List<Structure> addObjects = new ArrayList<Structure>();
	public static boolean reInit = false;
	public static boolean CloseThis = false;
	public static int move;
	
	public GuiModificator(EntityPlayer player, TileEntityRemoveInfo te)
	{
		super(new ContainerModificator(player.inventory, te));
		
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		world = player.getEntityWorld();
		
	}
	
	public static void prepareToOpen()
	{
		reInit = false;
		object = null;
		addObjects = new ArrayList<Structure>();
		ChildObjects = new ArrayList<Structure>();
	}
	
	@Override
	public void initGui()
	{
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		
		super.initGui();
		
		this.buttonList.clear();
		
		this.buttonList.add(new GuiVerticalSlider(0, x + 144, y + 15, 10, 140, "R", "R", 0,
				(ChildObjects != null ? ChildObjects.size() * 4 : 0) + (addObjects != null ? addObjects.size() * 4 : 0) - 2, 0, true, false));
		if (((GuiVerticalSlider) this.buttonList.get(0)).maxValue < 0)
		{
			((GuiVerticalSlider) this.buttonList.get(0)).enabled = false;
		}
		if (object != null)
		{
			this.buttonList.add(new GuiButtonModificator(1, x + 7, y + 15, object, y));
			this.buttonList.add(new GuiMLabel(2, x + 7, y + 59, StatCollector.translateToLocal("modificator.children.name"), y));
			int lasti = 70;
			if (ChildObjects != null && ChildObjects.size() > 0)
			{
				for (int i = 0; i < ChildObjects.size(); i++)
				{
					this.buttonList.add(new GuiButtonModificator(3 + i, x + 7, y + 70 + 44 * i, ChildObjects.get(i), y));
					lasti += 44;
				}
			}
			this.buttonList.add(new GuiButtonModificator2(buttonList.size(), x + 7, y + lasti, y));
			lasti += 22;
			this.buttonList.add(new GuiMLabel(buttonList.size(), x + 7, y + lasti, StatCollector.translateToLocal("modificator.additional.name"), y));
			lasti += 11;
			int lid = buttonList.size();
			if (addObjects != null && addObjects.size() > 0)
			{
				for (int i = 0; i < addObjects.size(); i++)
				{
					this.buttonList.add(new GuiButtonModificator(lid + i, x + 7, y + lasti, addObjects.get(i), y));
					lasti += 44;
				}
			}
			this.buttonList.add(new GuiButtonModificator2(buttonList.size(), x + 7, y + lasti, y));
		}
		
		// this.buttonList.add(new GuiButton(1, x + 70, y + 101, 80, 20,
		// StatCollector.translateToLocal("remover.deconstruct_button.name")));
		
	}
	
	@Override
	protected void keyTyped(char ch, int num)
	{
		super.keyTyped(ch, num);
		for (int i = 0; i < this.buttonList.size(); i++)
		{
			if (this.buttonList.get(i) instanceof GuiButtonModificator)
			{
				((GuiButtonModificator) this.buttonList.get(i)).keyTyped(ch, num);
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		super.actionPerformed(button);
		
	}
	
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.drawDefaultBackground();
		int k = this.guiLeft;
		int l = this.guiTop;
		this.drawGuiContainerBackgroundLayer(p_73863_3_, p_73863_1_, p_73863_2_);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		int i;
		
		for (i = this.buttonList.size() - 1; i != -1; i--)
		{
			((GuiButton) this.buttonList.get(i)).drawButton(this.mc, p_73863_1_, p_73863_2_);
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		GL11.glPushMatrix();
		GL11.glTranslatef((float) k, (float) l, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		short short1 = 240;
		short short2 = 240;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) short1 / 1.0F, (float) short2 / 1.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int k1;
		
		//Forge: Force lighting to be disabled as there are some issue where lighting would
		//incorrectly be applied based on items that are in the inventory.
		GL11.glDisable(GL11.GL_LIGHTING);
		this.drawGuiContainerForegroundLayer(p_73863_1_, p_73863_2_);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		RenderHelper.enableStandardItemLighting();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		if (CloseThis)
		{
			CloseThis = false;
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
		
		if (object != null && reInit == false)
		{
			reInit = true;
			initGui();
		}
		
		if (buttonList.size() > 0)
		{
			move = ((GuiVerticalSlider) buttonList.get(0)).getValueInt();
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		drawTexturedModalRect(x, y, 0, 0, Xsize, Ysize);
		
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		int factor = 4;
		GL11.glScissor((x + 5) * factor, mc.displayHeight - (y + 157) * factor, 160 * factor, 142 * factor);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int MouseX, int MouseY)
	{
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		fontRendererObj.drawString(StatCollector.translateToLocal("modificator.name"),
				(int) (xSize / 4.5D) - (fontRendererObj.getStringWidth(I18n.format("modificator.name")) / 2) + 15, 6, 4210752, false);
		
	}
	
	@Override
	protected void mouseClicked(int x, int y, int but)
	{
		for (int l = 0; l < this.buttonList.size(); ++l)
		{
			GuiButton guibutton = (GuiButton) this.buttonList.get(l);
			
			if (but == 0 && !(guibutton instanceof GuiButtonModificator))
			{
				if (guibutton.mousePressed(this.mc, x, y))
				{
					ActionPerformedEvent.Pre event = new ActionPerformedEvent.Pre(this, guibutton, this.buttonList);
					if (MinecraftForge.EVENT_BUS.post(event)) break;
					this.selectedButton = event.button;
					event.button.func_146113_a(this.mc.getSoundHandler());
					this.actionPerformed(event.button);
					if (this.equals(this.mc.currentScreen)) MinecraftForge.EVENT_BUS.post(new ActionPerformedEvent.Post(this, event.button, this.buttonList));
				}
			} else if (guibutton instanceof GuiButtonModificator)
			{
				GuiButtonModificator mGuiButton = (GuiButtonModificator) guibutton;
				if (mGuiButton.mouseClicked(x, y, but))
				{
					ActionPerformedEvent.Pre event = new ActionPerformedEvent.Pre(this, guibutton, this.buttonList);
					if (MinecraftForge.EVENT_BUS.post(event)) break;
					this.selectedButton = event.button;
					event.button.func_146113_a(this.mc.getSoundHandler());
					this.actionPerformed(event.button);
					if (this.equals(this.mc.currentScreen)) MinecraftForge.EVENT_BUS.post(new ActionPerformedEvent.Post(this, event.button, this.buttonList));
				}
			}
		}
	}
	
	protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_)
	{
		if (this.selectedButton != null && p_146286_3_ == 0)
		{
			this.selectedButton.mouseReleased(p_146286_1_, p_146286_2_);
			this.selectedButton = null;
		}
	}
	
}
