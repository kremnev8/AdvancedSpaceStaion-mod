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
import net.glider.src.network.packets.SwapArmorPacket;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiArmorStand extends GuiContainerGC
{
    private static ResourceLocation texture = new ResourceLocation(GliderModInfo.ModTestures, "textures/gui/Armor_stand.png");

    private IInventory standInventory;
    private IInventory playerInventory;

    public GuiArmorStand(EntityPlayer player, TileEntityArmorStand inventory)
    {
        super(new ContainerArmorStand(player, inventory));
        this.standInventory = inventory;
        this.playerInventory = player.inventory;
        xSize = 176;
        ySize = 166;
    }
    
    @Override
    public void initGui()
    {
    	super.initGui();
    	xSize = 176;
        ySize = 166;
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        buttonList.add(new GuiButtonSwap(0, x+81, y+61));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
    	
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
    	int id = button.id;
    	switch(id)
    	{
    	case 0:
    		PacketHandler.sendToServer(new SwapArmorPacket()); 
    		break;
    	}
    }
}