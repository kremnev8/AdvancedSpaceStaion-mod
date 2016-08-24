package net.glider.src.renderer;

import net.glider.src.items.ItemMod;
import net.glider.src.renderer.models.GalactiCraftitemsmodel;
import net.glider.src.renderer.models.ModelJetpack;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;

public class RendererPlayer extends RenderPlayerBase {

	private Minecraft mc = Minecraft.getMinecraft();

	private static final ResourceLocation texture = new ResourceLocation(GliderModInfo.ModTestures, "textures/JetPack.png");

	private ModelJetpack model = new ModelJetpack();
	
	private GalactiCraftitemsmodel modelGC = new GalactiCraftitemsmodel();

	public RendererPlayer(RenderPlayerAPI renderPlayerAPI)
	{
		super(renderPlayerAPI);
		
	}

	public void renderSpecials(AbstractClientPlayer par1AbstractClientPlayer, float par2)
	{
		super.renderSpecials(par1AbstractClientPlayer, par2);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		ModelBiped main = super.renderPlayerAPI.getModelBipedMainField();
		ModelBiped armor = super.renderPlayerAPI.getModelArmorField();
		boolean valid_jetpack = false;
		
		if (par1AbstractClientPlayer.getCurrentArmor(2) != null)
		{
			if (par1AbstractClientPlayer.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack)
			{
				valid_jetpack = true;
			}
		}
		main.isSneak = par1AbstractClientPlayer.isSneaking();
		modelGC.renderHead(par1AbstractClientPlayer, main);
		
		main.bipedBody.postRender(0.0625F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		modelGC.render(par1AbstractClientPlayer,0,0,0,0,0,0.0625F,valid_jetpack);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
}