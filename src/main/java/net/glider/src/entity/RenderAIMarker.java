package net.glider.src.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderAIMarker extends RenderLiving{

	private static final ResourceLocation texture = new ResourceLocation(GliderModInfo.ModTestures + ":textures/blocks/empty.png");
	private static final String __OBFID = "CL_00000984";
    private ModelBase model = new ModelBook();
	
	public RenderAIMarker() {
		super(new ModelBook(), 0.5F);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}

}
