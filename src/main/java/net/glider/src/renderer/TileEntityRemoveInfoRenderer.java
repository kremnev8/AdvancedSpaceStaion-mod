package net.glider.src.renderer;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Calendar;

import net.glider.src.renderer.models.ModelNotepad;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;


@SideOnly(Side.CLIENT)
public class TileEntityRemoveInfoRenderer extends TileEntitySpecialRenderer
{
		private static final ResourceLocation rl = new ResourceLocation(GliderModInfo.ModTestures, "textures/blocks/Notepad.png");
        private ModelNotepad model = new ModelNotepad();
        public int rot = 0;
        
        public void RenderRemoveInfo() 
        {
        }
        
        
        
        
        public void renderTileEntityAt(TileEntity te, double dx,double dy, double dz, float f1) {
            renderTE((TileEntityRemoveInfo)te, dx, dy, dz, f1);
        }
        
        public void renderTE(TileEntityRemoveInfo te, double dx, double dy, double dz, float f) 
        {
        	rot = te.getBlockMetadata();
        	GL11.glPushMatrix();
        	GL11.glTranslated(dx, dy, dz);
        	if (rot == 1){
        	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
        	GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
        	}else if (rot == 0){
        	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
        	GL11.glRotatef(180, -1.0F, 0.0F, 1.0F);
        	}else if (rot == 3){
        	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
        	GL11.glRotatef(180, 0.0F, 0.0F, -1.0F);
        	}else if (rot == 2){
        	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
        	GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
        	}
            
        	GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        	bindTexture(rl);
        	model.render();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }
    

}