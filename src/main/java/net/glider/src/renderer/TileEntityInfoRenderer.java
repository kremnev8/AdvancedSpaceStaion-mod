package net.glider.src.renderer;

import net.glider.src.items.ItemMod;
import net.glider.src.renderer.models.ModelInfoBlock;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class TileEntityInfoRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation rl = new ResourceLocation(GliderModInfo.ModTestures, "textures/blocks/InfoBlock.png");
    private ModelInfoBlock model = new ModelInfoBlock();
        public long oldtime = 0;
        public int rot;
        
        public void RenderRemoveInfo() 
        {
        }
        
        
        
        
        public void renderTileEntityAt(TileEntity te, double dx,double dy, double dz, float f1) {
            renderTE((TileEntityInfo)te, dx, dy, dz, f1);
        }
        
        public void renderTE(TileEntityInfo te, double dx, double dy, double dz, float f) 
        {
        	if (Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null && Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() == ItemMod.DebugTool)
        	{
        	long time = System.currentTimeMillis();
        	GL11.glPushMatrix();
        	GL11.glTranslated(dx, dy, dz);
        	if (time - oldtime > 3)
    		{
        		rot++;
        		if (rot >= 360) rot = 0;
        		oldtime = time;
    		}
        	
        	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
        	GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
        	
	/*	if (time - oldtime > 2000)
		{// 4
			GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
			oldtime = time;
		} else if (time - oldtime > 1500)
		{// 3
			GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			GL11.glRotatef(180, 0.0F, 0.0F, -1.0F);
		} else if (time - oldtime > 1000)
		{// 2
			GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			GL11.glRotatef(180, -1.0F, 0.0F, 1.0F);
		} else if (time - oldtime > 500)
		{// 1
			GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
		}else
		{
			GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
		}*/
        	
       // 	if (time-oldtime > 250){//1
       // 	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
       // 	GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
       // 	}else if (time-oldtime > 500){//2
       // 	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
       // 	GL11.glRotatef(180, -1.0F, 0.0F, 1.0F);
       // 	}else if (time-oldtime > 750){//3
        //	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
       // 	GL11.glRotatef(180, 0.0F, 0.0F, -1.0F);
        //	}else if (time-oldtime > 1000){//4
       // 	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
       // 	GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
       // 	oldtime = time;
       // 	}
            
        	GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        	bindTexture(rl);
        	model.render();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }
        }

}