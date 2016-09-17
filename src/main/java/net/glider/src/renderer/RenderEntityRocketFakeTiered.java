package net.glider.src.renderer;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.mars.client.model.ModelTier2Rocket;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.renderer.models.ModelRocketTier1;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityRocketFakeTiered extends Render
{
    private ResourceLocation texT1 = new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/model/rocketT1.png");
    private ResourceLocation texT2 = new ResourceLocation(MarsModule.ASSET_PREFIX, "textures/model/rocketT2.png");
    private ResourceLocation texT3 = new ResourceLocation(AsteroidsModule.ASSET_PREFIX,"textures/model/tier3rocket.png");
    // new RenderTier1Rocket(new ModelTier2Rocket(), MarsModule.ASSET_PREFIX, "rocketT2"));
    //new RenderTier3Rocket(rocketModel, AsteroidsModule.ASSET_PREFIX, "tier3rocket"))

    protected ModelRocketTier1 modelT1 = new ModelRocketTier1();
    protected ModelTier2Rocket modelT2 = new ModelTier2Rocket();
    protected IModelCustom modelT3 = AdvancedModelLoader.loadModel(new ResourceLocation(AsteroidsModule.ASSET_PREFIX, "models/tier3rocket.obj"));

    public RenderEntityRocketFakeTiered()
    {
    	this.shadowSize = 2F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
    	EntityRocketFakeTiered rocket = (EntityRocketFakeTiered)par1Entity;
    	if (rocket.getTier() == 1)
    	{
    		return texT1;
    	}else if (rocket.getTier() == 2)
    	{
    		return texT2;
    	}else if (rocket.getTier() == 3)
    	{
    		return texT3;
    	}
    		
        return texT3;
    }

    public void renderSpaceship(EntityRocketFakeTiered entity, double par2, double par4, double par6, float par8, float par9)
    {
    	EntityRocketFakeTiered rocket = (EntityRocketFakeTiered)entity;
    	if (rocket.getTier() == 1 || rocket.getTier() == 2)
    	{
        GL11.glPushMatrix();
        final float var24 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9;
        final float var25 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9;

        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-var25, 0.0F, 1.0F, 0.0F);
        
        if (rocket.getTier() == 2)
        {
        	 GL11.glTranslatef(0.0F,-0.7F,0.0F);
        }
        
      //  GL11.glTranslatef(0.0F, 1.0F, 0.0F);
        float var30 = entity.shipDamage - par9;

        if (var30 < 0.0F)
        {
            var30 = 0.0F;
        }


        this.bindEntityTexture(entity);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);

        if (rocket.getTier() == 1)
    	{
        	 this.modelT1.renderParts(entity, modelT1.getVals());
    	}else if (rocket.getTier() == 2)
    	{
    		 this.modelT2.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    	}

        GL11.glPopMatrix();
    	}else
    	{
    		 GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    	        GL11.glPushMatrix();
    	        final float var24 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9 + 180;
    	        final float var25 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9 + 45;

    	        GL11.glTranslatef((float) par2, (float) par4 - 0.4F, (float) par6);
    	        GL11.glTranslatef(0.0F,-1.5F,0.0F);
    	        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
    	        GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
    	        float var30 = entity.shipDamage - par9;

    	        if (var30 < 0.0F)
    	        {
    	            var30 = 0.0F;
    	        }

    	        this.bindEntityTexture(entity);
    	        GL11.glScalef(-1.0F, -1.0F, 1.0F);
    	        GL11.glScalef(0.9F, 0.9F, 0.9F);

    	        this.modelT3.renderOnly("Boosters", "Rocket");
    	        Vector3 teamColor = ClientUtil.updateTeamColor(FMLClientHandler.instance().getClient().thePlayer.getCommandSenderName(), true);
    	        if (teamColor != null)
    	        {
    	            GL11.glColor3f(teamColor.floatX(), teamColor.floatY(), teamColor.floatZ());
    	        }
    	        this.modelT3.renderPart("NoseCone");

    	        if (FMLClientHandler.instance().getClient().thePlayer.ticksExisted / 10 % 2 < 1)
    	        {
    	            GL11.glColor3f(1, 0, 0);
    	        }
    	        else
    	        {
    	            GL11.glColor3f(0, 1, 0);
    	        }

    	        GL11.glDisable(GL11.GL_TEXTURE_2D);
    	        GL11.glDisable(GL11.GL_LIGHTING);
    	        this.modelT3.renderPart("Cube");
    	        GL11.glEnable(GL11.GL_TEXTURE_2D);
    	        GL11.glEnable(GL11.GL_LIGHTING);

    	        GL11.glColor3f(1, 1, 1);

    	        GL11.glPopMatrix();
    	}
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderSpaceship((EntityRocketFakeTiered) par1Entity, par2, par4, par6, par8, par9);
    }
}
