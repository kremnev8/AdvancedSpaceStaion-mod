
package net.glider.src.renderer;

import net.glider.src.renderer.models.ModelJetpack;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRenderJetpack implements IItemRenderer {
	
	private static final ResourceLocation rl = new ResourceLocation(GliderModInfo.ModTestures, "textures/JetPack.png");
	private ModelJetpack model = new ModelJetpack();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		if (type == ItemRenderType.ENTITY)
		{
			GL11.glTranslatef(0F, 0.8F, 0F);
			GL11.glRotatef(180, 0.5F, 0.0F, 0.5F);
		} else if (type == ItemRenderType.INVENTORY)
		{
			GL11.glTranslatef(1.1F, 1.4F, 0.6F);
			GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		} else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			GL11.glTranslatef(1.2F, 1.3F, 0.4F);
			GL11.glRotatef(180, 1.0F, 0.0F, -1.0F);
		} else if (type == ItemRenderType.EQUIPPED)
		{
			GL11.glTranslatef(0.25F, 1.4F, 0.25F);
			GL11.glRotatef(180, 1.0F, 0.0F, 8.0F);
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(rl);
		GL11.glScalef(1.4F, 1.4F, 1.4F);
		model.renderAsItem(0.0625F);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
	}
	
}
