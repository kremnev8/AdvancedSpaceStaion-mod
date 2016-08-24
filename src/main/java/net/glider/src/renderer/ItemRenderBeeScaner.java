package net.glider.src.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderBeeScaner implements IItemRenderer {

	private static final ResourceLocation rl = new ResourceLocation(GliderModInfo.ModTestures, "textures/blocks/beescaner.png");
    private ModelChest model = new ModelChest();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
    	GL11.glTranslatef(0.0F, 1.0F, 1.0F);
    	GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(rl);
    	model.chestLid.rotateAngleX = -0;
    	model.renderAll();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
	}

}
