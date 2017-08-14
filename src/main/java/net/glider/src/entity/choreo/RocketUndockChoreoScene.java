
package net.glider.src.entity.choreo;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.glider.src.GliderCore;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.entity.EntityRocketFakeTiered.EnumLaunchPhase;
import net.glider.src.utils.ChatUtils;
import net.glider.src.utils.LocalizedChatComponent;
import net.glider.src.utils.LocalizedString;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumChatFormatting;

public class RocketUndockChoreoScene extends ChoreoScene {
	
	public RocketUndockChoreoScene(Entity actingEntity)
	{
		super(actingEntity);
	}
	
	@Override
	public void TickChoreo(int time)
	{
		EntityRocketFakeTiered rocket = (EntityRocketFakeTiered) entity;
		
		//	double x1 = 2 * Math.cos(entity.rotationYaw * Math.PI / 180.0D) * Math.sin(entity.rotationPitch * Math.PI / 180.0D);
		//	double z1 = 2 * Math.sin(entity.rotationYaw * Math.PI / 180.0D) * Math.sin(entity.rotationPitch * Math.PI / 180.0D);
		//	double y1 = 2 * Math.cos((entity.rotationPitch - 180) * Math.PI / 180.0D);
		
		final double y = entity.prevPosY + (entity.posY - entity.prevPosY);
		final double x2 = entity.posX;
		final double z2 = entity.posZ;
		
		switch (time) {
		case 0:
			//setup
			if (entity.riddenByEntity != null && entity.worldObj.isRemote)
			{
				EntityPlayer player = (EntityPlayer) entity.riddenByEntity;
				ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("choreo.messages.undock.name", EnumChatFormatting.RESET)));
			}
			rocket.setLaunchPhase(EnumLaunchPhase.UNDOCKED);
			if (entity.worldObj.isRemote)
			{
				GliderCore.proxy.spawnParticle("whiteSmokeLargeIdle", new Vector3(x2 + 0.1, y + 2, z2 + 0.1), new Vector3(0, 0, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteSmokeLargeIdle", new Vector3(x2 - 0.1, y + 2, z2 + 0.1), new Vector3(0, 0, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteSmokeLargeIdle", new Vector3(x2 - 0.1, y + 2, z2 - 0.1), new Vector3(0, 0, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteSmokeLargeIdle", new Vector3(x2 + 0.1, y + 2, z2 - 0.1), new Vector3(0, 0, 0), new Object[] { null });
			}
			break;
		case 10:
			this.SetEntitySpeedInSec(-0.2D, false, true, false);
			break;
		case 350:
			//this.SetEntitySpeedInSec(0D, true, true, true);
			rocket.setLaunchPhase(EnumLaunchPhase.NOTROTATED);
			
			if (entity.worldObj.isRemote)
			{
				GliderCore.proxy.spawnParticle("whiteShortSmokeIdle", new Vector3(x2 - 1, y - 1.5, z2 + 0.2), new Vector3(-0.1, 0, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteShortSmokeIdle", new Vector3(x2 - 1, y - 1.5, z2 - 0.2), new Vector3(-0.1, 0, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteShortSmokeIdle", new Vector3(x2 + 1, y + 1.5, z2), new Vector3(0.1, 0, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteShortSmokeIdle", new Vector3(x2 + 1, y + 1.5, z2), new Vector3(0.1, 0, 0), new Object[] { null });
			}
			break;
		case 532:
			if (entity.worldObj.isRemote)
			{
				GliderCore.proxy.spawnParticle("whiteShortSmokeIdle", new Vector3(x2 + 0.1 - 3, y - 2.7, z2 + 0.1), new Vector3(0, -0.1, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteShortSmokeIdle", new Vector3(x2 - 0.1 - 3, y - 2.7, z2 + 0.1), new Vector3(0, -0.1, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteShortSmokeIdle", new Vector3(x2 - 0.1 - 0.3, y - 0.8, z2 + 0.2), new Vector3(0, 0.1, 0), new Object[] { null });
				GliderCore.proxy.spawnParticle("whiteShortSmokeIdle", new Vector3(x2 + 0.1 - 0.3, y - 0.8, z2 - 0.2), new Vector3(0, 0.1, 0), new Object[] { null });
			}
			break;
		case 540:
			rocket.ignite();
			rocket.setLaunchPhase(EnumLaunchPhase.FLYAWAY);
			break;
		case 550:
			rocket.unignite();
			break;
		case 590:
			rocket.ignite();
			if (entity.riddenByEntity != null && entity.worldObj.isRemote)
			{
				EntityPlayer player = (EntityPlayer) entity.riddenByEntity;
				ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("choreo.messages.burn.name", EnumChatFormatting.RESET)));
			}
			break;
		case 790:
			rocket.unignite();
			this.SetEntitySpeedInSec(0D, true, true, true);
			rocket.setLaunchPhase(EnumLaunchPhase.LAUNCHED);
			if (!rocket.worldObj.isRemote && rocket.riddenByEntity instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP) rocket.riddenByEntity;
				
				GCPlayerStats stats = GCPlayerStats.get(player);
				rocket.onReachAtmosphere();
				WorldUtil.toCelestialSelection(player, stats, rocket.getTier());
				
			}
			rocket.setDead();
			break;
		default:
			if (time > 350 && time < 531)
			{
				rocket.turnPitch(0.5F);
			}
			break;
		}
		
	}
	
	public void SetEntitySpeedInSec(double speed, boolean x, boolean y, boolean z)
	{
		double speedInTicks = speed / 20;
		if (x) this.entity.motionX = speedInTicks;
		if (y) this.entity.motionY = speedInTicks;
		if (z) this.entity.motionZ = speedInTicks;
	}
	
	@Override
	public List<Integer> GetChoreoMoments()
	{
		List<Integer> ret = new ArrayList();
		ret.add(0);
		return ret;
	}
	
	@Override
	public void UpdateChoreoEntity()
	{
		
	}
	
	@Override
	public String GetChoreoName()
	{
		return "undock";
	}
	
}
