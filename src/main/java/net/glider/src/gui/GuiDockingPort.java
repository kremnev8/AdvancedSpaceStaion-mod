package net.glider.src.gui;

import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiContainerGC;
import micdoodle8.mods.galacticraft.core.entities.IScaleableFuelLevel;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.DismountPacket;
import net.glider.src.network.packets.MountPacket;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiDockingPort extends GuiContainerGC
{
    private static ResourceLocation[] parachestTexture = new ResourceLocation[4];
    public static boolean dirty = false;
    public static UUID entId;

    static
    {
        for (int i = 0; i < 4; i++)
        {
            GuiDockingPort.parachestTexture[i] = new ResourceLocation(GliderModInfo.ModTestures, "textures/gui/chest_" + i * 18 + ".png");
        }
    }

    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;

    private int inventorySlotS = 0;

    public GuiDockingPort(IInventory par1IInventory, IInventory par2IInventory)
    {
        super(new ContainerDockingPort(par1IInventory, par2IInventory));
        this.upperChestInventory = par1IInventory;
        this.lowerChestInventory = par2IInventory;
        this.allowUserInput = false;
        this.inventorySlotS = par2IInventory.getSizeInventory();
        this.ySize = 146 + this.inventorySlotS * 2;
    }
    
    @Override
    public void updateScreen()
    {
    	super.updateScreen();
    	if (dirty)
    	{
    		((ContainerDockingPort)this.inventorySlots).ReloadContainer(upperChestInventory, lowerChestInventory);
    		 this.inventorySlotS = lowerChestInventory.getSizeInventory();
    	        this.ySize = 146 + this.inventorySlotS * 2;
    	        this.initGui();
    	    dirty = false;
    	}
    	
    }
    
    @Override
    public void initGui()
    {
    	super.initGui();
    	this.buttonList.clear();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        //120 41
        int numRows = (lowerChestInventory.getSizeInventory() - 4) / 9;
        this.buttonList.add(new GuiButton(0, x+122, y+(numRows == 0 ? 41 : 43) + numRows * 18,40 , 20, "Enter"));
    }
    
	@Override
	protected void actionPerformed(GuiButton but)
	{

		if (but.id == 0)
		{
			PacketHandler.sendToServer(new MountPacket(entId));
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}

	}

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString(this.lowerChestInventory.getInventoryName(), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.upperChestInventory.hasCustomInventoryName() ? this.upperChestInventory.getInventoryName() : GCCoreUtil.translate(this.upperChestInventory.getInventoryName()), 8, this.ySize - 103 + (this.inventorySlotS == 3 ? 2 : 4), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        if (lowerChestInventory.getStackInSlot(lowerChestInventory.getSizeInventory()-2) == null)
        {
        	((GuiButton)this.buttonList.get(0)).enabled = false;
        }else
        {
        	((GuiButton)this.buttonList.get(0)).enabled =  true;
        }
    	
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiDockingPort.parachestTexture[(this.inventorySlotS - 4) / 18]);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if (this.lowerChestInventory instanceof IScaleableFuelLevel)
        {
            int fuelLevel = ((IScaleableFuelLevel) this.lowerChestInventory).getScaledFuelLevel(28);
            this.drawTexturedModalRect(k + 17, l + (this.inventorySlotS == 4 ? 38 : 40) - fuelLevel + this.inventorySlotS * 2, 176, 28 - fuelLevel, 34, fuelLevel);
        }
    }
}