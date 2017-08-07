
package net.glider.src.gui;

import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Optional.Interface(iface = "codechicken.nei.api.INEIGuiHandler", modid = "NotEnoughItems")
public class GuiModular extends GuiContainer implements INEIGuiHandler {
	
	protected List<GuiModule> modules = Lists.newArrayList();
	
	public int cornerX;
	public int cornerY;
	public int realWidth;
	public int realHeight;
	
	public GuiModular(Container container)
	{
		super(container);
		
		realWidth = -1;
		realHeight = -1;
	}
	
	protected void addModule(GuiModule module)
	{
		modules.add(module);
	}
	
	public List<Rectangle> getModuleAreas()
	{
		List<Rectangle> areas = new ArrayList<Rectangle>(modules.size());
		for (GuiModule module : modules)
		{
			areas.add(module.getArea());
		}
		return areas;
	}
	
	@Override
	public void initGui()
	{
		if (realWidth > -1)
		{
			xSize = realWidth;
			ySize = realHeight;
		}
		super.initGui();
		
		this.cornerX = this.guiLeft;
		this.cornerY = this.guiTop;
		this.realWidth = xSize;
		this.realHeight = ySize;
		
		for (GuiModule module : modules)
		{
			updateSubmodule(module);
		}
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		for (GuiModule module : modules)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(-this.guiLeft, -this.guiTop, 0.0F);
			GL11.glTranslatef(module.guiLeft, module.guiTop, 0.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			module.drawGuiForegroundLayer(mouseX, mouseY);
			GL11.glPopMatrix();
		}
	}
	
	protected void drawBackground(ResourceLocation background)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(background);
		this.drawTexturedModalRect(cornerX, cornerY, 0, 0, realWidth, realHeight);
	}
	
	protected void drawContainerName()
	{
	}
	
	protected void drawPlayerInventoryName()
	{
		String localizedName = Minecraft.getMinecraft().thePlayer.inventory.getInventoryName();
		this.fontRendererObj.drawString(localizedName, 8, this.ySize - 96 + 2, 0x404040);
	}
	
	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height)
	{
		super.setWorldAndResolution(mc, width, height);
		
		//try
		//{
		for (GuiModule module : modules)
		{
			module.setWorldAndResolution(mc, width, height);
			updateSubmodule(module);
		}
		//}// catch (IllegalAccessException e)
		//{
		//	e.printStackTrace();
		//}
	}
	
	public void onResize(Minecraft mc, int width, int height)
	{
		for (GuiModule module : modules)
		{
			updateSubmodule(module);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		int oldX = guiLeft;
		int oldY = guiTop;
		int oldW = xSize;
		int oldH = ySize;
		
		guiLeft = cornerX;
		guiTop = cornerY;
		xSize = realWidth;
		ySize = realHeight;
		super.drawScreen(mouseX, mouseY, partialTicks);
		guiLeft = oldX;
		guiTop = oldY;
		xSize = oldW;
		ySize = oldH;
	}
	
	protected boolean isPointInRegion(int left, int top, int right, int bottom, int pointX, int pointY)
	{
		pointX -= this.cornerX;
		pointY -= this.cornerY;
		return pointX >= left - 1 && pointX < left + right + 1 && pointY >= top - 1 && pointY < top + bottom + 1;
	}
	
	protected void updateSubmodule(GuiModule module)
	{
		module.updatePosition(this.cornerX, this.cornerY, this.realWidth, this.realHeight);
		
		if (module.guiLeft < this.guiLeft)
		{
			this.xSize += this.guiLeft - module.guiLeft;
			this.guiLeft = module.guiLeft;
		}
		if (module.guiTop < this.guiTop)
		{
			this.ySize += this.guiTop - module.guiTop;
			this.guiTop = module.guiTop;
		}
		if (module.guiRight() > this.guiLeft + this.xSize)
		{
			xSize = module.guiRight() - this.guiLeft;
		}
		if (module.guiBottom() > this.guiTop + this.ySize)
		{
			ySize = module.guiBottom() - this.guiTop;
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		GuiModule module = getModuleForPoint(mouseX, mouseY);
		if (module != null)
		{
			module.mouseClicked(mouseX, mouseY, mouseButton);
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
		GuiModule module = getModuleForPoint(mouseX, mouseY);
		if (module != null)
		{
			module.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		}
		
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
	
	protected void mouseMovedOrUp(int mouseX, int mouseY, int state)
	{
		for (GuiModule module : modules)
		{
			if (module.mouseReleased(mouseX, mouseY, state))
			{
				return;
			}
		}
		
		super.mouseMovedOrUp(mouseX, mouseY, state);
	}
	
	protected GuiModule getModuleForPoint(int x, int y)
	{
		for (GuiModule module : modules)
		{
			if (this.isPointInRegion(module.guiLeft, module.guiTop, module.guiRight(), module.guiBottom(), x + this.cornerX, y + this.cornerY))
			{
				return module;
			}
		}
		
		return null;
	}
	
	@Override
	@Optional.Method(modid = "NotEnoughItems")
	public VisiblityData modifyVisiblity(GuiContainer guiContainer, VisiblityData visiblityData)
	{
		return visiblityData;
	}
	
	@Override
	@Optional.Method(modid = "NotEnoughItems")
	public Iterable<Integer> getItemSpawnSlots(GuiContainer guiContainer, ItemStack itemStack)
	{
		return new ArrayList();
	}
	
	@Override
	@Optional.Method(modid = "NotEnoughItems")
	public List<TaggedInventoryArea> getInventoryAreas(GuiContainer guiContainer)
	{
		return Collections.EMPTY_LIST;
	}
	
	@Override
	@Optional.Method(modid = "NotEnoughItems")
	public boolean handleDragNDrop(GuiContainer guiContainer, int x, int y, ItemStack itemStack, int k)
	{
		return false;
	}
	
	@Override
	@Optional.Method(modid = "NotEnoughItems")
	public boolean hideItemPanelSlot(GuiContainer guiContainer, int x, int y, int w, int h)
	{
		if (w == 1 && h == 1)
		{
			return false;
		}
		for (GuiModule module : modules)
		{
			if (module.guiLeft < x + w && module.guiRight() > x && module.guiTop < y + h && module.guiBottom() > y && module.isEnabled)
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float time, int x, int y)
	{
		for (GuiModule module : modules)
		{
			module.drawGuiBackgroundLayer(x, y);
		}
	}
	
}