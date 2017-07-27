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
import org.lwjgl.opengl.GL11;

public class GuiRemover extends GuiContainer {
	private InventoryPlayer inventory;
	
	public static Map strucutures = new HashMap<Integer, Structure>();
	
	private ResourceLocation texture = new ResourceLocation(GliderModInfo.ModTestures, "textures/Remover.png");
	private int Xsize = 157;
	private int Ysize = 125;
	
	private int x;
	private int y;
	private int z;
	private World world;
	
	public static Structure object;
	public static List<Structure> ChildObjects = new ArrayList<Structure>();
	public static List<Structure> addObjects = new ArrayList<Structure>();
	public static boolean CloseThis = false;
	public static boolean reInit = false;
	private int objCount = 0;
	public static int move;
	public static boolean[] Iselected = new boolean[] { false, false, false, false, false, false };
	public boolean hasChilds = false;
	
	public GuiRemover(EntityPlayer player, TileEntityRemoveInfo te)
	{
		super(new ContainerRemover(player.inventory, te));
		hasChilds = false;
		/*
		 * object = null; addObjects = new ArrayList<Structure>(); ChildObjects
		 * = new ArrayList<Structure>();
		 */
		
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		world = player.getEntityWorld();
		
	}
	
	private int getLines(int size)
	{
		if (size < 3)
		{
			return 0;
		} else if (size > 2 && size < 6)
		{
			return 1;
		} else if (size > 5)
		{
			return 2;
		}
		return 0;
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
	{// TODO make deleting part possible even if it has children when children
		// have other connection to some part of station
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		
		super.initGui();
		
		this.buttonList.clear();
		
		if (addObjects != null && addObjects.size() > 0)
		{
			objCount = addObjects.size();
		}
		this.buttonList.add(new GuiVerticalSlider(0, x + 141, y + 15, 10, 82, "R", "R", 0, objCount - 2, 0, true, false));
		
		this.buttonList.add(new GuiButton(1, x + 70, y + 101, 80, 20, StatCollector.translateToLocal("remover.deconstruct_button.name")));
		if (ChildObjects != null)
		{
			if (ChildObjects.size() == 0)
			{
				if (object != null)
				{
					this.buttonList.add(new GuiButtonRemover(2, x + 9, y + 17, object.getUnlocalizedName(), object, y));
					objCount++;
				}
			} else
			{
				int connNum = 0;
				for (int i = 0; i < ChildObjects.size(); i++)
				{
					if (ChildObjects.get(i).connections.size() > 0)
					{
						connNum++;
					}
				}
				if (ChildObjects.size() != connNum)
				{
					hasChilds = true;
				} else
				{
					if (object != null)
					{
						this.buttonList.add(new GuiButtonRemover(2, x + 9, y + 17, object.getUnlocalizedName(), object, y));
						objCount++;
					}
				}
			}
			if ((objCount < 3 && !hasChilds) || (objCount < 4 && hasChilds)) ((GuiVerticalSlider) this.buttonList.get(0)).enabled = false;
			if (addObjects != null && addObjects.size() > 0)
			{
				int offs = 0;
				if (hasChilds)
				{
					this.buttonList.add(new GuiButtonRemover(2, x, y, "", object, 0));
					((GuiButtonRemover) this.buttonList.get(2)).visSelf = false;
					((GuiButtonRemover) this.buttonList.get(2)).visible = false;
					offs = 28;
				}
				for (int i = 0; i < addObjects.size(); i++)
				{
					this.buttonList.add(new GuiButtonRemover(3 + i, x + 9, y + 45 + (28 * i) - offs, addObjects.get(i).getUnlocalizedName(), addObjects.get(i), y));
					
				}
				hasChilds = false;
			}
		}
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		super.actionPerformed(button);
		if (button.id != 0 && button instanceof GuiButtonRemover)
		{
			if (((GuiButtonRemover) button).Enabled)
			{
				((GuiButtonRemover) button).setEnabled(false);
			} else
			{
				((GuiButtonRemover) button).setEnabled(true);
			}
		}
		if (button.id == 1)
		{
			List<Structure> toDobjects = new ArrayList<Structure>();
			for (int i = 2; i < buttonList.size(); i++)
			{
				GuiButtonRemover but = (GuiButtonRemover) buttonList.get(i);
				
				if (but.Enabled)
				{
					if (i == 2)
					{
						for (int i2 = 0; i2 < addObjects.size(); i2++)
						{
							toDobjects.add(addObjects.get(i2));
						}
						
						Structure obj = Structure.FindStructure(but.strName);
						obj.Configure(but.strPos, but.rot, but.dir);
						toDobjects.add(obj);
						
						break;
					} else
					{
						Structure obj = Structure.FindStructure(but.strName);
						obj.Configure(but.strPos, but.rot, but.dir);
						toDobjects.add(obj);
					}
				}
			}
			if (toDobjects.size() == 0)
			{
				Minecraft.getMinecraft().thePlayer.closeScreen();
				Minecraft.getMinecraft().thePlayer.addChatMessage(ChatUtils.modifyColor(new ChatComponentText(StatCollector.translateToLocal("remover.no_selected.name")),
						EnumChatFormatting.RED));
			} else
			{// sending packet
				PacketHandler.sendToServer(new DeconstructPacket(toDobjects, new int[] { x, y, z }));
				Minecraft.getMinecraft().thePlayer.closeScreen();
			}
		}
	}
	
	/*
	 * @Override public void drawScreen(int x, int y, float m) {
	 * super.drawScreen(x, y, m); drawDefaultBackground(); }
	 */
	
	private boolean AnyTrue(boolean[] bool)
	{
		for (int i = 0; i < bool.length; i++)
		{
			if (bool[i])
			{
				return true;
			}
		}
		return false;
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
			if (!hasChilds)
			{
				if (buttonList.size() > 2)
				{
					for (int i = 2; i < buttonList.size(); i++)
					{
						if (((GuiButtonRemover) buttonList.get(i)).Enabled)
						{
							if (i - 2 < Iselected.length)
							{
								Iselected[i - 2] = true;
							}
						} else
						{
							if (i - 2 < Iselected.length)
							{
								Iselected[i - 2] = false;
							}
						}
					}
					
					if (!AnyTrue(Iselected))
					{
						((GuiButton) buttonList.get(1)).enabled = false;
					} else
					{
						((GuiButton) buttonList.get(1)).enabled = true;
					}
				}
			} else ((GuiButton) buttonList.get(1)).enabled = false;
			
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		drawTexturedModalRect(x, y, 0, 0, Xsize, Ysize);
		
		if (hasChilds)
		{
			drawTexturedModalRect(x + 7, y + 42, 7, 18, 131, 2);
			drawTexturedModalRect(x + 7, y + 70, 7, 18, 131, 2);
		}
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int MouseX, int MouseY)
	{
		
		fontRendererObj.drawString(StatCollector.translateToLocal("remover.name"), (int) (xSize / 4.5D) - (fontRendererObj.getStringWidth(I18n.format("remover.name")) / 2) + 15,
				25, 4210752, false);
		
		if (hasChilds)
		{
			
			String tip = StatCollector.translateToLocal("remover.haschilds_tip.name"); // 125
			String[] words = tip.split("[\\s']");
			List<String> finalW = new ArrayList<>();
			String lastLine = "";
			for (int i = 0; i < words.length; i++)
			{
				if (fontRendererObj.getStringWidth(lastLine + " " + words[i]) > 125)
				{
					finalW.add(lastLine);
					lastLine = words[i];
				} else
				{
					lastLine = lastLine + " " + words[i];
				}
			}
			finalW.add(lastLine);
			for (int i = 0; i < finalW.size(); i++)
			{
				fontRendererObj.drawString(finalW.get(i), (int) (125 / 4.5D) - fontRendererObj.getStringWidth(finalW.get(i)) / 2 + 35, 50 + (i * 9), 14737632, true);
			}
			
		}
	}
	
}
