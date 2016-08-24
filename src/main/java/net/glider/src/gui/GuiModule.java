package net.glider.src.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// a sub-gui. Mostly the same as a separate GuiContainer, but doesn't do the calls that affect the game as if this were the only gui
@SideOnly(Side.CLIENT)
public abstract class GuiModule extends GuiContainer {

  protected final GuiModular parent;

  // left or right of the parent
  protected final boolean right;
  // top or bottom of the parent
  protected final boolean bottom;

  public int yOffset = 0;
  public int xOffset = 0;
  public int GuiLeft = this.guiLeft;
  public int GuiTop = this.guiTop;

  public GuiModule(GuiModular parent, Container container, boolean right, boolean bottom) {
    super(container);

    this.parent = parent;
    this.right = right;
    this.bottom = bottom;
  }

  public int guiRight() {
    return guiLeft + xSize;
  }

  public int guiBottom() {
    return guiTop + ySize;
  }

  public Rectangle getArea() {
    return new Rectangle(guiLeft, guiTop, xSize, ySize);
  }

  @Override
  public void initGui() {
    this.guiLeft = (this.width - this.xSize) / 2;
    this.guiTop = (this.height - this.ySize) / 2;
    this.GuiLeft = this.guiLeft;
    this.GuiTop = this.guiTop;
  }

  public void updatePosition(int parentX, int parentY, int parentSizeX, int parentSizeY) {
    if(right) {
      this.guiLeft = parentX + parentSizeX;
    }
    else {
      this.guiLeft = parentX - this.xSize;
    }

    if(bottom) {
      this.guiTop = parentY + parentSizeY - this.ySize;
    }
    else {
      this.guiTop = parentY;
    }

    this.guiLeft += xOffset;
    this.guiTop += yOffset;
    this.GuiLeft = this.guiLeft;
    this.GuiTop = this.guiTop;
  }

  public boolean shouldDrawSlot(Slot slot) {
    return true;
  }

  public boolean isMouseInModule(int mouseX, int mouseY) {
    return mouseX >= this.guiLeft && mouseX < this.guiRight() &&
           mouseY >= this.guiTop && mouseY < this.guiBottom();
  }

  public boolean isMouseOverFullSlot(int mouseX, int mouseY) {
    for(Slot slot : (List<Slot>)inventorySlots.inventorySlots) {
      if(parent.isMouseOverSlot(slot, mouseX, mouseY) && slot.getHasStack()) {
        return true;
      }
    }
    return false;
  }

  /*
    public void updateDragged(boolean dragSplitting, Set draggedSlots) {
      this.dragSplitting = dragSplitting;
      this.dragSplittingSlots.clear();
      for(Object o : draggedSlots) {
        if(o instanceof SlotWrapper)
          this.dragSplittingSlots.add(((SlotWrapper) o).parent);
        else
          this.dragSplittingSlots.add(o);
      }
    }
  */
  public void handleDrawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
  }

  public void handleDrawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    this.drawGuiContainerForegroundLayer(mouseX, mouseY);
  }


  /**
   * Custom mouse click handling.
   *
   * @return True to prevent the main container handling the mouseclick
   */
  public boolean handleMouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    return false;
  }

  /**
   * Custom mouse click handling.
   *
   * @return True to prevent the main container handling the mouseclick
   */
  public boolean handleMouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    return false;
  }

  /**
   * Custom mouse click handling.
   *
   * @return True to prevent the main container handling the mouseclick
   */
  public boolean handleMouseReleased(int mouseX, int mouseY, int state) {
    return false;
  }

}