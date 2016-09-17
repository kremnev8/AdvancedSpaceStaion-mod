package net.glider.src.renderer.models;

import java.lang.reflect.Constructor;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.model.ModelPlayerBaseGC;
import micdoodle8.mods.galacticraft.core.client.model.ModelPlayerGC;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.wrappers.PlayerGearData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class GalactiCraftitemsmodel extends ModelBase
{
    public ModelRenderer[] parachute = new ModelRenderer[3];
    public ModelRenderer[] parachuteStrings = new ModelRenderer[4];
    public ModelRenderer[][] tubes = new ModelRenderer[2][7];
    public ModelRenderer[] greenOxygenTanks = new ModelRenderer[2];
    public ModelRenderer[] orangeOxygenTanks = new ModelRenderer[2];
    public ModelRenderer[] redOxygenTanks = new ModelRenderer[2];
    public ModelRenderer oxygenMask;

    private boolean usingParachute;

    protected static IModelCustom frequencyModule;
    public static AbstractClientPlayer playerRendering;
    protected static PlayerGearData currentGearData;

    public static final ResourceLocation oxygenMaskTexture = new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/model/oxygen.png");
    public static final ResourceLocation playerTexture = new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/model/player.png");
    public static final ResourceLocation frequencyModuleTexture = new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/model/frequencyModule.png");

    public static boolean isSmartMovingLoaded;
    private static Class modelRotationGCSmartMoving;
    private static Constructor modelRotationGCSmartMovingInit;
    
    /**
     * this class is a mostly full copy of ModelPlayerGC
     * 
     * It renders the Galacticraft equipment, if RenderPlayerAPI is installed
     *
     */
    public GalactiCraftitemsmodel()
    {
        float var1 = 0.0F;

            this.oxygenMask = new ModelRenderer(this, 0, 0);
            this.oxygenMask.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 1);
            this.oxygenMask.setRotationPoint(0.0F, 0.0F + 0.0F, 0.0F);

            this.parachute[0] = new ModelRenderer(this, 0, 0).setTextureSize(512, 256);
            this.parachute[0].addBox(-20.0F, -45.0F, -20.0F, 10, 2, 40, var1);
            this.parachute[0].setRotationPoint(15.0F, 4.0F, 0.0F);
            this.parachute[0].rotateAngleZ = (float) (30F * (Math.PI / 180F));
            this.parachute[1] = new ModelRenderer(this, 0, 42).setTextureSize(512, 256);
            this.parachute[1].addBox(-20.0F, -45.0F, -20.0F, 40, 2, 40, var1);
            this.parachute[1].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.parachute[2] = new ModelRenderer(this, 0, 0).setTextureSize(512, 256);
            this.parachute[2].addBox(-20.0F, -45.0F, -20.0F, 10, 2, 40, var1);
            this.parachute[2].setRotationPoint(11F, -11, 0.0F);
            this.parachute[2].rotateAngleZ = (float) -(30F * (Math.PI / 180F));

            this.parachuteStrings[0] = new ModelRenderer(this, 100, 0).setTextureSize(512, 256);
            this.parachuteStrings[0].addBox(-0.5F, 0.0F, -0.5F, 1, 40, 1, var1);
            this.parachuteStrings[0].rotateAngleZ = (float) (155F * (Math.PI / 180F));
            this.parachuteStrings[0].rotateAngleX = (float) (23F * (Math.PI / 180F));
            this.parachuteStrings[0].setRotationPoint(-9.0F, -7.0F, 2.0F);
            this.parachuteStrings[1] = new ModelRenderer(this, 100, 0).setTextureSize(512, 256);
            this.parachuteStrings[1].addBox(-0.5F, 0.0F, -0.5F, 1, 40, 1, var1);
            this.parachuteStrings[1].rotateAngleZ = (float) (155F * (Math.PI / 180F));
            this.parachuteStrings[1].rotateAngleX = (float) -(23F * (Math.PI / 180F));
            this.parachuteStrings[1].setRotationPoint(-9.0F, -7.0F, 2.0F);
            this.parachuteStrings[2] = new ModelRenderer(this, 100, 0).setTextureSize(512, 256);
            this.parachuteStrings[2].addBox(-0.5F, 0.0F, -0.5F, 1, 40, 1, var1);
            this.parachuteStrings[2].rotateAngleZ = (float) -(155F * (Math.PI / 180F));
            this.parachuteStrings[2].rotateAngleX = (float) (23F * (Math.PI / 180F));
            this.parachuteStrings[2].setRotationPoint(9.0F, -7.0F, 2.0F);
            this.parachuteStrings[3] = new ModelRenderer(this, 100, 0).setTextureSize(512, 256);
            this.parachuteStrings[3].addBox(-0.5F, 0.0F, -0.5F, 1, 40, 1, var1);
            this.parachuteStrings[3].rotateAngleZ = (float) -(155F * (Math.PI / 180F));
            this.parachuteStrings[3].rotateAngleX = (float) -(23F * (Math.PI / 180F));
            this.parachuteStrings[3].setRotationPoint(9.0F, -7.0F, 2.0F);

            this.tubes[0][0] = new ModelRenderer(this, 0, 0);
            this.tubes[0][0].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[0][0].setRotationPoint(2F, 3F, 5.8F);
            this.tubes[0][0].setTextureSize(128, 64);
            this.tubes[0][0].mirror = true;
            this.tubes[0][1] = new ModelRenderer(this, 0, 0);
            this.tubes[0][1].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[0][1].setRotationPoint(2F, 2F, 6.8F);
            this.tubes[0][1].setTextureSize(128, 64);
            this.tubes[0][1].mirror = true;
            this.tubes[0][2] = new ModelRenderer(this, 0, 0);
            this.tubes[0][2].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[0][2].setRotationPoint(2F, 1F, 6.8F);
            this.tubes[0][2].setTextureSize(128, 64);
            this.tubes[0][2].mirror = true;
            this.tubes[0][3] = new ModelRenderer(this, 0, 0);
            this.tubes[0][3].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[0][3].setRotationPoint(2F, 0F, 6.8F);
            this.tubes[0][3].setTextureSize(128, 64);
            this.tubes[0][3].mirror = true;
            this.tubes[0][4] = new ModelRenderer(this, 0, 0);
            this.tubes[0][4].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[0][4].setRotationPoint(2F, -1F, 6.8F);
            this.tubes[0][4].setTextureSize(128, 64);
            this.tubes[0][4].mirror = true;
            this.tubes[0][5] = new ModelRenderer(this, 0, 0);
            this.tubes[0][5].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[0][5].setRotationPoint(2F, -2F, 5.8F);
            this.tubes[0][5].setTextureSize(128, 64);
            this.tubes[0][5].mirror = true;
            this.tubes[0][6] = new ModelRenderer(this, 0, 0);
            this.tubes[0][6].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[0][6].setRotationPoint(2F, -3F, 4.8F);
            this.tubes[0][6].setTextureSize(128, 64);
            this.tubes[0][6].mirror = true;

            this.tubes[1][0] = new ModelRenderer(this, 0, 0);
            this.tubes[1][0].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[1][0].setRotationPoint(-2F, 3F, 5.8F);
            this.tubes[1][0].setTextureSize(128, 64);
            this.tubes[1][0].mirror = true;
            this.tubes[1][1] = new ModelRenderer(this, 0, 0);
            this.tubes[1][1].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[1][1].setRotationPoint(-2F, 2F, 6.8F);
            this.tubes[1][1].setTextureSize(128, 64);
            this.tubes[1][1].mirror = true;
            this.tubes[1][2] = new ModelRenderer(this, 0, 0);
            this.tubes[1][2].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[1][2].setRotationPoint(-2F, 1F, 6.8F);
            this.tubes[1][2].setTextureSize(128, 64);
            this.tubes[1][2].mirror = true;
            this.tubes[1][3] = new ModelRenderer(this, 0, 0);
            this.tubes[1][3].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[1][3].setRotationPoint(-2F, 0F, 6.8F);
            this.tubes[1][3].setTextureSize(128, 64);
            this.tubes[1][3].mirror = true;
            this.tubes[1][4] = new ModelRenderer(this, 0, 0);
            this.tubes[1][4].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[1][4].setRotationPoint(-2F, -1F, 6.8F);
            this.tubes[1][4].setTextureSize(128, 64);
            this.tubes[1][4].mirror = true;
            this.tubes[1][5] = new ModelRenderer(this, 0, 0);
            this.tubes[1][5].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[1][5].setRotationPoint(-2F, -2F, 5.8F);
            this.tubes[1][5].setTextureSize(128, 64);
            this.tubes[1][5].mirror = true;
            this.tubes[1][6] = new ModelRenderer(this, 0, 0);
            this.tubes[1][6].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, var1);
            this.tubes[1][6].setRotationPoint(-2F, -3F, 4.8F);
            this.tubes[1][6].setTextureSize(128, 64);
            this.tubes[1][6].mirror = true;

            this.greenOxygenTanks[0] = new ModelRenderer(this, 4, 0);
            this.greenOxygenTanks[0].addBox(-1.5F, 0F, -1.5F, 3, 7, 3, var1);
            this.greenOxygenTanks[0].setRotationPoint(2F, 2F, 3.8F);
            this.greenOxygenTanks[0].mirror = true;
            this.greenOxygenTanks[1] = new ModelRenderer(this, 4, 0);
            this.greenOxygenTanks[1].addBox(-1.5F, 0F, -1.5F, 3, 7, 3, var1);
            this.greenOxygenTanks[1].setRotationPoint(-2F, 2F, 3.8F);
            this.greenOxygenTanks[1].mirror = true;

            this.orangeOxygenTanks[0] = new ModelRenderer(this, 16, 0);
            this.orangeOxygenTanks[0].addBox(-1.5F, 0F, -1.5F, 3, 7, 3, var1);
            this.orangeOxygenTanks[0].setRotationPoint(2F, 2F, 3.8F);
            this.orangeOxygenTanks[0].mirror = true;
            this.orangeOxygenTanks[1] = new ModelRenderer(this, 16, 0);
            this.orangeOxygenTanks[1].addBox(-1.5F, 0F, -1.5F, 3, 7, 3, var1);
            this.orangeOxygenTanks[1].setRotationPoint(-2F, 2F, 3.8F);
            this.orangeOxygenTanks[1].mirror = true;

            this.redOxygenTanks[0] = new ModelRenderer(this, 28, 0);
            this.redOxygenTanks[0].addBox(-1.5F, 0F, -1.5F, 3, 7, 3, var1);
            this.redOxygenTanks[0].setRotationPoint(2F, 2F, 3.8F);
            this.redOxygenTanks[0].mirror = true;
            this.redOxygenTanks[1] = new ModelRenderer(this, 28, 0);
            this.redOxygenTanks[1].addBox(-1.5F, 0F, -1.5F, 3, 7, 3, var1);
            this.redOxygenTanks[1].setRotationPoint(-2F, 2F, 3.8F);
            this.redOxygenTanks[1].mirror = true;

            this.frequencyModule = AdvancedModelLoader.loadModel(new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "models/frequencyModule.obj"));
            
        
    }
    
    public void renderHead(Entity entity, ModelBiped main)
	{
    	PlayerGearData gearData = ClientProxyCore.playerItemData.get(entity.getCommandSenderName());

		if (entity instanceof AbstractClientPlayer && gearData != null)
		{
			boolean wearingMask = gearData.getMask() > -1;
			boolean wearingFrequencyModule = gearData.getFrequencyModule() > -1;

			if (wearingMask)
			{
				if (main.isSneak)
				{
					oxygenMask.rotationPointY = 1.0F;
				}else 
				{
					oxygenMask.rotationPointY = 0.0F;
				}
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(GalactiCraftitemsmodel.oxygenMaskTexture);
				GL11.glPushMatrix();
				GL11.glScalef(1.05F, 1.05F, 1.05F);
				this.oxygenMask.rotateAngleY = main.bipedHead.rotateAngleY;
				this.oxygenMask.rotateAngleX = main.bipedHead.rotateAngleX;	
			
				this.oxygenMask.render(0.0625F);
				GL11.glScalef(1F, 1F, 1F);
				GL11.glPopMatrix();
			}
			if (wearingFrequencyModule)
			{
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(ModelPlayerGC.frequencyModuleTexture);
				GL11.glPushMatrix();
				GL11.glRotatef(180, 1, 0, 0);
			//	GL11.glRotatef(180, 0, 0, 0);

				GL11.glRotatef((float) (main.bipedHead.rotateAngleY * (-180.0F / Math.PI)), 0, 1, 0);
				GL11.glRotatef((float) (main.bipedHead.rotateAngleX * (180.0F / Math.PI)), 1, 0, 0);
				GL11.glScalef(0.3F, 0.3F, 0.3F);
				GL11.glTranslatef(-1.1F, 1.2F, 0);
				this.frequencyModule.renderPart("Main");
				GL11.glTranslatef(0, 1.2F, 0);
				GL11.glRotatef((float) (Math.sin(entity.ticksExisted * 0.05) * 50.0F), 1, 0, 0);
				GL11.glRotatef((float) (Math.cos(entity.ticksExisted * 0.1) * 50.0F), 0, 1, 0);
				GL11.glTranslatef(0, -1.2F, 0);
				this.frequencyModule.renderPart("Radar");
				GL11.glPopMatrix();

			}
		}
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean oxyTanks)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		float var7 = f5;

		PlayerGearData gearData = ClientProxyCore.playerItemData.get(entity.getCommandSenderName());

		if (entity instanceof AbstractClientPlayer && gearData != null)
		{
			this.usingParachute = gearData.getParachute() != null;
			boolean wearingGear = gearData.getGear() > -1;
			boolean wearingLeftTankGreen = gearData.getLeftTank() == 0;
			boolean wearingLeftTankOrange = gearData.getLeftTank() == 1;
			boolean wearingLeftTankRed = gearData.getLeftTank() == 2;
			boolean wearingRightTankGreen = gearData.getRightTank() == 0;
			boolean wearingRightTankOrange = gearData.getRightTank() == 1;
			boolean wearingRightTankRed = gearData.getRightTank() == 2;

			FMLClientHandler.instance().getClient().renderEngine.bindTexture(ModelPlayerBaseGC.playerTexture);

			if (wearingGear)
			{
				for (int i = 0; i < 7; i++)
				{
					for (int k = 0; k < 2; k++)
					{
						this.tubes[k][i].render(var7);
					}
				}
			}

			if (!oxyTanks)
			{
				if (wearingLeftTankRed)
				{
					this.redOxygenTanks[0].render(var7);
				}

				if (wearingLeftTankOrange)
				{
					this.orangeOxygenTanks[0].render(var7);
				}

				if (wearingLeftTankGreen)
				{
					this.greenOxygenTanks[0].render(var7);
				}

				if (wearingRightTankRed)
				{
					this.redOxygenTanks[1].render(var7);
				}

				if (wearingRightTankOrange)
				{
					this.orangeOxygenTanks[1].render(var7);
				}

				if (wearingRightTankGreen)
				{
					this.greenOxygenTanks[1].render(var7);
				}
			}

			if (usingParachute)
			{
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(gearData.getParachute());

				this.parachute[0].render(var7);
				this.parachute[1].render(var7);
				this.parachute[2].render(var7);

				this.parachuteStrings[0].render(var7);
				this.parachuteStrings[1].render(var7);
				this.parachuteStrings[2].render(var7);
				this.parachuteStrings[3].render(var7);
			}
		}

	}
    public void render()
    {
  	  /*notepad.render(0.0625F);
  	  ring1.render(0.0625F);
  	  ring2.render(0.0625F);
  	  ring3.render(0.0625F);
  	  ring4.render(0.0625F);
  	  ring5.render(0.0625F);
  	  pencil.render(0.0625F);*/
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
    {
      super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    }
}