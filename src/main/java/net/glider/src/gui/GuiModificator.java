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
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class GuiModificator extends GuiContainer {
	
	private ResourceLocation texture = new ResourceLocation(GliderModInfo.ModTestures, "textures/Modificator.png");
	private int Xsize = 160;
	private int Ysize = 152;
	
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
		
		this.buttonList.add(new GuiVerticalSlider(0, x + 144, y + 15, 10, 129, "R", "R", 0, 5, 0, true, false));
		if (object != null) this.buttonList.add(new GuiButtonModificator(1, x + 7, y + 15, object, y + 15));
		
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
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int MouseX, int MouseY)
	{
		fontRendererObj.drawString(StatCollector.translateToLocal("modificator.name"),
				(int) (xSize / 4.5D) - (fontRendererObj.getStringWidth(I18n.format("modificator.name")) / 2) + 15, 12, 4210752, false);
		
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
