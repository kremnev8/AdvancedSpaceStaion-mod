package net.glider.src.renderer;

import net.glider.src.renderer.models.ModelArmorStand;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRenderArmorStand implements IItemRenderer {

	private static final ResourceLocation rl = new ResourceLocation(GliderModInfo.ModTestures, "textures/blocks/ArmorStand.png");
	private ModelArmorStand model = new ModelArmorStand();
	
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
		switch (type.ordinal())
		{
		case 0:
			renderArmorStand(0.1F, 0.47F, 0.0F, true);
			break;
		case 1:
			renderArmorStand(0.5F, 1.45F, 0.5F, true);
			break;
		case 2:
			
			renderArmorStand(0.6F, 1.2F, 0.8F, true);
			break;
		case 3:
			renderArmorStand(0.6F, 0.9F, 0.6F, false);
			break;
		case 4:
			renderArmorStand(0.8F, 1.05F, 0.76F, false);
			break;
		}
		/*
		 * GL11.glPushMatrix();
		 * if (type == ItemRenderType.ENTITY)
		 * {
		 * GL11.glTranslatef(-0.5F, 1.0F, -0.5F);
		 * GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
		 * }else if (type == ItemRenderType.INVENTORY)
		 * {
		 * GL11.glTranslatef(0.9F, 1.4F, 0.5F);
		 * GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		 * }else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		 * {
		 * GL11.glTranslatef(0.9F, 1.4F, 0.5F);
		 * GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
		 * }else if (type == ItemRenderType.EQUIPPED)
		 * {
		 * GL11.glTranslatef(0.7F, 1.7F, 1.0F);
		 * GL11.glRotatef(180, 1.0F, 0.0F, -1.0F);
		 * }
		 * Minecraft.getMinecraft().renderEngine.bindTexture(rl);
		 * model.render(0.04166667F);
		 * GL11.glPopMatrix();
		 * GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		 */
		
	}
	
	private void renderArmorStand(float i, float j, float k, boolean entity)
	{
		float scale = 0.04166667F;
		Minecraft.getMinecraft().renderEngine.bindTexture(rl);
		GL11.glPushMatrix();
		GL11.glTranslatef(i, j, k);
		if (entity)
		{
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		}
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		// GL11.glRotatef(95.0F, 0.0F, 1.0F, 0.0F);
		model.render(scale);
		GL11.glPopMatrix();
	}

}
