package net.glider.src.handlers.hooks;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.ReturnCondition;
import micdoodle8.mods.galacticraft.core.entities.player.FreefallHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.glider.src.items.ItemMod;
import net.glider.src.items.ItemSpaceJetpack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class FreefallHook {

	private static double pPrevMotionX;
	public static double pPrevMotionY;
	private static double pPrevMotionZ;
	private static float jetpackBoost;
	private static double pPrevdY;
	public static boolean sneakLast;

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static boolean testFreefall(FreefallHandler fallh,EntityPlayer player)
	{
        //Test whether feet are on a block, also stops the login glitch
        int playerFeetOnY = (int) (player.boundingBox.minY - 0.01D);
        int xx = MathHelper.floor_double(player.posX);
        int zz = MathHelper.floor_double(player.posZ);
        Block b = player.worldObj.getBlock(xx, playerFeetOnY, zz);
        if (b.getMaterial() != Material.air && !(b instanceof BlockLiquid))
        {
        	double blockYmax = playerFeetOnY + b.getBlockBoundsMaxY();
            if (player.boundingBox.minY - blockYmax < 0.01D && player.boundingBox.minY - blockYmax > -0.5D)
            {
                player.onGround = true;
                if (player.boundingBox.minY - blockYmax > 0D)
                {
                    player.posY -= player.boundingBox.minY - blockYmax;
                    player.boundingBox.offset(0, blockYmax - player.boundingBox.minY, 0);              	
                }
                else if (b.canCollideCheck(player.worldObj.getBlockMetadata(xx, playerFeetOnY, zz), false))
                {
                    AxisAlignedBB collisionBox = b.getCollisionBoundingBoxFromPool(player.worldObj, xx, playerFeetOnY, zz);
                    if (collisionBox != null && collisionBox.intersectsWith(player.boundingBox))
                    {
                        player.posY -= player.boundingBox.minY - blockYmax;
	                    player.boundingBox.offset(0, blockYmax - player.boundingBox.minY, 0);
                    }
                }
                return false;
            }
        }
        return true;
	}
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void setupFreefallPre(FreefallHandler fallh,EntityPlayerSP p)
	{
        double dY = p.motionY - pPrevMotionY;
        jetpackBoost = 0F;
        pPrevdY = dY;
		pPrevMotionX = p.motionX;
        pPrevMotionY = p.motionY;
        pPrevMotionZ = p.motionZ;
	}
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void freefallMotion(FreefallHandler fallh,EntityPlayerSP p)
	{
        boolean jetpackUsed = false;
		double dX = p.motionX - pPrevMotionX;
        double dY = p.motionY - pPrevMotionY;
        double dZ = p.motionZ - pPrevMotionZ;

        double posOffsetX = - p.motionX;
        double posOffsetY = - p.motionY;// + WorldUtil.getGravityForEntity(p);
        double posOffsetZ = - p.motionZ;
        //if (p.capabilities.isFlying)

        ///Undo whatever vanilla tried to do to our y motion
        if (dY < 0D && p.motionY != 0.0D)
        {
        	p.motionY = pPrevMotionY;
        }
        else if (dY > 0.01D && GCPlayerStatsClient.get(p).inFreefallLast)
        {
    		//Impulse upwards - it's probably a jetpack from another mod
        	if (dX < 0.01D && dZ < 0.01D)
        	{
        		float pitch = p.rotationPitch / 57.29578F;
       			jetpackBoost = (float) dY * MathHelper.cos(pitch) * 0.1F;
        		float factor = 1 + MathHelper.sin(pitch) / 5;
        		p.motionY -= dY * factor;
        		jetpackUsed = true;
        	}
        	else
        	{
            	p.motionY -= dY / 2;
        	}
        }

        p.motionX -= dX;
//        p.motionY -= dY;    //Enabling this will disable jetpacks
        p.motionZ -= dZ;

        if (p.movementInput.moveForward != 0)
        {
            p.motionX -= p.movementInput.moveForward * MathHelper.sin(p.rotationYaw / 57.29578F) / (ConfigManagerCore.hardMode ? 600F : 200F);
            p.motionZ += p.movementInput.moveForward * MathHelper.cos(p.rotationYaw / 57.29578F) / (ConfigManagerCore.hardMode ? 600F : 200F);
        }

        if (jetpackBoost != 0)
        {
            p.motionX -= jetpackBoost * MathHelper.sin(p.rotationYaw / 57.29578F);
            p.motionZ += jetpackBoost * MathHelper.cos(p.rotationYaw / 57.29578F);
        }

        if (p.movementInput.sneak)
        {
            if (!sneakLast)
            {
            	posOffsetY += 0.0268;
            	sneakLast = true;
            }
            p.motionY -= ConfigManagerCore.hardMode ? 0.002D : 0.0032D;
        } else if (sneakLast)
        {
        	sneakLast = false;
        	posOffsetY -= 0.0268;
        }

        if (!jetpackUsed && p.movementInput.jump)
        {
            p.motionY += ConfigManagerCore.hardMode ? 0.002D : 0.0032D;
        }
        
        float speedLimit = ConfigManagerCore.hardMode ? 0.9F : 0.7F;

        if (p.motionX > speedLimit)
        {
            p.motionX = speedLimit;
        }
        if (p.motionX < -speedLimit)
        {
            p.motionX = -speedLimit;
        }
        if (p.motionY > speedLimit)
        {
            p.motionY = speedLimit;
        }
        if (p.motionY < -speedLimit)
        {
            p.motionY = -speedLimit;
        }
        if (p.motionZ > speedLimit)
        {
            p.motionZ = speedLimit;
        }
        if (p.motionZ < -speedLimit)
        {
            p.motionZ = -speedLimit;
        }
        
        if (p.getCurrentArmor(2) != null && p.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack)
        {
        	ItemSpaceJetpack jetpack = (ItemSpaceJetpack) p.getCurrentArmor(2).getItem();
			ItemStack is = p.getCurrentArmor(2);
			if (is.hasTagCompound() && is.stackTagCompound.getBoolean("Enabled") && !jetpack.isDisabled(p,true))
			{
        	if (p.motionX > 0.001D || p.motionX < 0.001D)
        	{
        		double prewM = p.motionX;
        		p.motionX /= 1.025;
        		double dV = prewM - p.motionX;
        		jetpack.usedFuelAm += dV;
        	}
        	if (p.motionZ > 0.001D || p.motionZ < 0.001D)
        	{
        		double prewM = p.motionZ;
        		if (ItemSpaceJetpack.KeysPressed.contains(4) || ItemSpaceJetpack.KeysPressed.contains(5))
        		{
        			p.motionZ /= 1.2;
        		}else p.motionZ /= 1.025;
        		double dV = prewM - p.motionZ;
        		jetpack.usedFuelAm += dV;
        	}
        	if (p.motionY > 0.001D || p.motionY < 0.001D)
        	{
        		double prewM = p.motionY;
        		p.motionY /= 1.025;
        		double dV = prewM - p.motionY;
        		jetpack.usedFuelAm += dV;
        	}
			}
        }
        
        pPrevMotionX = p.motionX;
        pPrevMotionY = p.motionY;
        pPrevMotionZ = p.motionZ;
        p.moveEntity(p.motionX + posOffsetX, p.motionY + posOffsetY, p.motionZ + posOffsetZ);
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void updateFreefall(FreefallHandler fallh,EntityPlayer p)
	{
	    pPrevMotionX = p.motionX;
	    pPrevMotionY = p.motionY;
	    pPrevMotionZ = p.motionZ;
	}
}