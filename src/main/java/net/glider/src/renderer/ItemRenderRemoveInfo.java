package net.glider.src.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.glider.src.renderer.models.ModelNotepad;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class ItemRenderRemoveInfo implements IItemRenderer {

	private static final ResourceLocation rl = new ResourceLocation(GliderModInfo.ModTestures, "textures/blocks/Notepad.png");
    private ModelNotepad model = new ModelNotepad();
	
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
		if (type == ItemRenderType.ENTITY)
        {
    	GL11.glTranslatef(-0.5F, 1.0F, -0.5F);
    	GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
        }else if (type == ItemRenderType.INVENTORY)
        {
        GL11.glTranslatef(0.9F, 1.4F, 0.5F);
        GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
        }else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
        GL11.glTranslatef(0.9F, 1.4F, 0.5F);
        GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
        }else if (type == ItemRenderType.EQUIPPED)
        {
        GL11.glTranslatef(0.7F, 1.7F, 1.0F);
        GL11.glRotatef(180, 1.0F, 0.0F, -1.0F);
        }	
        Minecraft.getMinecraft().renderEngine.bindTexture(rl);
    	model.render();
    	GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
	}

}
