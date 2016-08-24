package net.glider.src.gui;

import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Optional.Interface(iface = "codechicken.nei.api.INEIGuiHandler", modid = "NotEnoughItems")
// todo: NEI
public class GuiModular extends GuiContainer implements INEIGuiHandler {

	// NEI-stuff >:(
	private static Field NEI_Manager;

	static
	{
		try
		{
			NEI_Manager = GuiContainer.class.getDeclaredField("manager");
		}
		catch (NoSuchFieldException e)
		{
			NEI_Manager = null;
		}
	}

	protected List<GuiModule> modules = Lists.newArrayList();

	public int cornerX;
	public int cornerY;
	public int realWidth;
	public int realHeight;

	public GuiModular(ContainerModular container)
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
			// has to be reset before calling initGui so the position is getting
			// retained
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
		
		// this.guiLeft = this.guiTop = 0;
		// this.xSize = width;
		// this.ySize = height;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		for (GuiModule module : modules)
		{
			module.handleDrawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		// drawContainerName();
		// drawPlayerInventoryName();

		for (GuiModule module : modules)
		{
			// set correct state for the module
			GL11.glPushMatrix();
			GL11.glTranslatef(-this.guiLeft, -this.guiTop, 0.0F);
			GL11.glTranslatef(module.GuiLeft, module.GuiTop, 0.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			module.handleDrawGuiContainerForegroundLayer(mouseX, mouseY);
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
		ContainerModular multiContainer = (ContainerModular) this.inventorySlots;
		IChatComponent localizedName = multiContainer.getInventoryDisplayName();
		if (localizedName != null)
		{
			this.fontRendererObj.drawString(localizedName.getUnformattedText(), 8, 6, 0x404040);
		}
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

		// workaround for NEIs ASM hax. sigh.
		try
		{
			for (GuiModule module : modules)
			{
				module.setWorldAndResolution(mc, width, height);
				if (NEI_Manager != null)
				{
					NEI_Manager.set(module, NEI_Manager.get(this));
				}
				updateSubmodule(module);
			}
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	public void onResize(Minecraft mc, int width, int height)
	{
		// super.onResize(mc, width, height);

		for (GuiModule module : modules)
		{
			// module.onResize(mc, width, height);
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

	// needed to get the correct slot on clicking
	protected boolean isPointInRegion(int left, int top, int right, int bottom, int pointX, int pointY)
	{
		pointX -= this.cornerX;
		pointY -= this.cornerY;
		return pointX >= left - 1 && pointX < left + right + 1 && pointY >= top - 1 && pointY < top + bottom + 1;
	}

	protected void updateSubmodule(GuiModule module)
	{
		module.updatePosition(this.cornerX, this.cornerY, this.realWidth, this.realHeight);

		if (module.GuiLeft < this.guiLeft)
		{
			this.xSize += this.guiLeft - module.GuiLeft;
			this.guiLeft = module.GuiLeft;
		}
		if (module.GuiTop < this.guiTop)
		{
			this.ySize += this.guiTop - module.GuiTop;
			this.guiTop = module.GuiTop;
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
	
	/*
	 * @Override
	 * public void drawslot(Slot slotIn) {
	 * GuiModule module = getModuleForSlot(slotIn.slotNumber);
	 * 
	 * if(module != null) {
	 * Slot slot = slotIn;
	 * // unwrap for the call to the module
	 * if(slotIn instanceof SlotWrapper) {
	 * slot = ((SlotWrapper) slotIn).parent;
	 * }
	 * if(!module.shouldDrawSlot(slot)) {
	 * return;
	 * }
	 * }
	 * 
	 * // update slot positions
	 * if(slotIn instanceof SlotWrapper) {
	 * slotIn.xDisplayPosition = ((SlotWrapper) slotIn).parent.xDisplayPosition;
	 * slotIn.yDisplayPosition = ((SlotWrapper) slotIn).parent.yDisplayPosition;
	 * }
	 * 
	 * super.drawSlot(slotIn);
	 * }
	 */

	public boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY)
	{
		GuiModule module = getModuleForSlot(slotIn.slotNumber);

		// mouse inside the module of the slot?
		if (module != null)
		{
			Slot slot = slotIn;
			// unwrap for the call to the module
			if (slotIn instanceof SlotWrapper)
			{
				slot = ((SlotWrapper) slotIn).parent;
			}
			if (!module.shouldDrawSlot(slot))
			{
				return false;
			}
		}

		return this.func_146978_c(slotIn.xDisplayPosition, slotIn.yDisplayPosition, 16, 16, mouseX, mouseY);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		GuiModule module = getModuleForPoint(mouseX, mouseY);
		if (module != null)
		{
			try
			{
				if (module.handleMouseClicked(mouseX, mouseY, mouseButton))
				{
					return;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
		GuiModule module = getModuleForPoint(mouseX, mouseY);
		if (module != null)
		{
			if (module.handleMouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick))
			{
				return;
			}
		}

		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}

	protected void mouseMovedOrUp(int mouseX, int mouseY, int state)
	{
		for (GuiModule module : modules)
		{
			if (module.handleMouseReleased(mouseX, mouseY, state))
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
			if (this.isPointInRegion(module.GuiLeft, module.GuiTop, module.guiRight(), module.guiBottom(), x + this.cornerX, y + this.cornerY))
			{
				return module;
			}
		}

		return null;
	}

	protected GuiModule getModuleForSlot(int slotNumber)
	{
		return getModuleForContainer(getContainer().getSlotContainer(slotNumber));
	}

	protected GuiModule getModuleForContainer(Container container)
	{
		for (GuiModule module : modules)
		{
			if (module.inventorySlots == container)
			{
				return module;
			}
		}

		return null;
	}

	protected ContainerModular getContainer()
	{
		return (ContainerModular) inventorySlots;
	}

	/* NEI INTEGRATION */
	// todo: NEI

	@Override
	@Optional.Method(modid = "NotEnoughItems")
	public VisiblityData modifyVisiblity(GuiContainer guiContainer, VisiblityData visiblityData)
	{
		/*
		 * int x = LayoutManager.stateButtons[0].x +
		 * LayoutManager.stateButtons[0].w;
		 * int x2 = LayoutManager.timeButtons[3].x +
		 * LayoutManager.timeButtons[3].w;
		 * int y2 = LayoutManager.heal.y + LayoutManager.heal.h;
		 * for (GuiModule module : modules)
		 * {
		 * if (x > module.GuiLeft)
		 * {
		 * visiblityData.showStateButtons = false;
		 * }
		 * if (x2 > module.GuiLeft && y2 > module.GuiTop)
		 * {
		 * visiblityData.showUtilityButtons = false;
		 * }
		 * }
		 */
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
			if (module.GuiLeft < x + w && module.guiRight() > x && module.GuiTop < y + h && module.guiBottom() > y)
			{
				return true;
			}
		}
		return false;
	}

}