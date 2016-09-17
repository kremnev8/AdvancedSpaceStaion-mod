package net.glider.src.renderer.models;

import net.glider.src.entity.ExtendedPlayer;
import net.glider.src.items.ItemMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;

public class GliderModelPlayerBase extends ModelPlayerBase {

	public GliderModelPlayerBase(ModelPlayerAPI modelPlayerAPI)
	{
		super(modelPlayerAPI);
	}

	@Override
	public void afterSetRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		super.afterSetRotationAngles(par1, par2, par3, par4, par5, par6, entity);
		if (!(entity instanceof EntityPlayer)) return; 
		
		final EntityPlayer player = (EntityPlayer) entity;
		final ItemStack currentItemStack = player.inventory.getCurrentItem();

		
		if (player.getCurrentArmor(2) != null && (Minecraft.getMinecraft().gameSettings.thirdPersonView != 0 || Minecraft.getMinecraft().currentScreen != null || !entity.getCommandSenderName().equals(Minecraft.getMinecraft().thePlayer.getCommandSenderName())))
		{
			if (player.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack)
			{
				ExtendedPlayer prop = ExtendedPlayer.get(player);
				
				if (prop.getAnimationHandler().isAnimationActive("Enable") || prop.getAnimationHandler().isAnimationActive("Disable"))
				{
					this.modelPlayer.bipedLeftArm.rotateAngleX = 0;
	    			this.modelPlayer.bipedLeftArm.rotateAngleZ = 0;
					
					this.modelPlayer.bipedLeftArm.rotateAngleX += (float) Math.PI + 1.5;
	    			this.modelPlayer.bipedLeftArm.rotateAngleZ += (float) Math.PI / 10;
	    			
	    			this.modelPlayer.bipedRightArm.rotateAngleX = 0;
	    			this.modelPlayer.bipedRightArm.rotateAngleZ = 0;

	    			this.modelPlayer.bipedRightArm.rotateAngleX += (float) Math.PI + 1.5;
	    			this.modelPlayer.bipedRightArm.rotateAngleZ -= (float) Math.PI / 10;
				}
				if (prop.getAnimationHandler().isAnimationActive("Enabled idle"))
				{
					this.modelPlayer.bipedLeftArm.rotateAngleX = 0;
	    		//	this.modelPlayer.bipedLeftArm.rotateAngleZ = 0;
					
					this.modelPlayer.bipedLeftArm.rotateAngleX += (float) Math.PI + 2 + (player.isSneaking() ? 0.4 : 0);
	    		//	this.modelPlayer.bipedLeftArm.rotateAngleZ += (float) Math.PI / 10;
	    			
	    			this.modelPlayer.bipedRightArm.rotateAngleX = 0;
	    		//	this.modelPlayer.bipedRightArm.rotateAngleZ = 0;

	    			this.modelPlayer.bipedRightArm.rotateAngleX += (float) Math.PI + 2 + (player.isSneaking() ? 0.4 : 0);
	    		//	this.modelPlayer.bipedRightArm.rotateAngleZ -= (float) Math.PI / 10;
				}
			}
		}
		

	}

}
