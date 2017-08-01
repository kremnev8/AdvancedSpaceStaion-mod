
package net.glider.src.handlers.hooks;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.ReturnCondition;
import java.util.Iterator;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.overlay.OverlayOxygenWarning;
import micdoodle8.mods.galacticraft.core.client.model.ModelPlayerBaseGC;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeSpaceStation;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOrbit;
import micdoodle8.mods.galacticraft.core.entities.player.FreefallHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCEntityClientPlayerMP;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.glider.src.dimensions.WorldProviderOrbitEarth;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.items.ItemMod;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.SyncPlayerFallPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Hooks {
	
	public static boolean ignoreThis = false;
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE)
	public static boolean registerProvider(GalacticraftRegistry reg, int id, Class<? extends WorldProvider> provider, boolean keepLoaded, int defaultID)
	{
		if ((id == ConfigManagerCore.idDimensionOverworldOrbit || id == ConfigManagerCore.idDimensionOverworldOrbitStatic) && !ignoreThis)
		{
			return true;
		}
		return false;
	}
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE)
	public static boolean registerSpaceStation(GalacticraftRegistry reg, SpaceStationType sst)
	{
		if (sst.getSpaceStationID() == ConfigManagerCore.idDimensionOverworldOrbit && !ignoreThis)
		{
			return true;
		}
		return false;
	}
	
	@Hook(returnCondition = ReturnCondition.NEVER)
	public static void registerSatellite(GalaxyRegistry galReg, Satellite satellite)
	{
		if (satellite == GalacticraftCore.satelliteSpaceStation)
		{
			satellite.setDimensionInfo(ConfigManagerCore.idDimensionOverworldOrbit, ConfigManagerCore.idDimensionOverworldOrbitStatic, WorldProviderOrbitEarth.class)
					.setTierRequired(1);
			ignoreThis = true;
			GalacticraftRegistry.registerProvider(ConfigManagerCore.idDimensionOverworldOrbit, WorldProviderOrbitEarth.class, false, 0);
			GalacticraftRegistry.registerProvider(ConfigManagerCore.idDimensionOverworldOrbitStatic, WorldProviderOrbitEarth.class, true, 0);
			ignoreThis = false;
			GalacticraftRegistry.registerTeleportType(WorldProviderOrbitEarth.class, new TeleportTypeSpaceStation());
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Hook(returnCondition = ReturnCondition.ON_TRUE)
	public static boolean afterRender(ModelPlayerBaseGC model, Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
	{
		return true;
	}
	
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static double getItemGravity(WorldUtil util, EntityItem e)
	{
		if (e.worldObj.provider instanceof IGalacticraftWorldProvider)
		{
			final IGalacticraftWorldProvider customProvider = (IGalacticraftWorldProvider) e.worldObj.provider;
			return Math.max(0.002D, 0.03999999910593033D - customProvider.getGravity() / 1.75D);
		} else
		{
			return 0.03999999910593033D;
		}
	}
	
	@Hook
	public static void onEntityFall(EventHandlerGC evhand, LivingFallEvent event)
	{
		if (event.entity.worldObj.isRemote && event.entityLiving instanceof EntityPlayerMP && ((EntityPlayerMP) event.entityLiving).getCurrentArmor(2) != null
				&& ((EntityPlayerMP) event.entityLiving).getCurrentArmor(2).getItem() != ItemMod.spaceJetpack)
		{
			PacketHandler.sendToServer(new SyncPlayerFallPacket(event.distance * ((IGalacticraftWorldProvider) event.entityLiving.worldObj.provider).getFallDamageModifier()));
		}
		
	}
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE)
	@SideOnly(Side.CLIENT)
	public static boolean freefallMotion(FreefallHandler freefall, EntityPlayerSP p)
	{
		
		double sum = 0;
		Iterator<Double> forces = WorldProviderOrbitModif.ArtificialForces.iterator();
		for (int i = 0; forces.hasNext(); i++)
		{
			sum += forces.next();
		}
		
		if (sum > 0.5D)
		{
			return true;
		}
		return false;
	}
	
	@Hook
	@SideOnly(Side.CLIENT)
	public static void orientCamera(ClientProxyCore proxy, float partialTicks)
	{
		GCPlayerStatsClient stats = GCPlayerStatsClient.get(Minecraft.getMinecraft().thePlayer);
		if (stats.landingTicks > 0 && Minecraft.getMinecraft().thePlayer.worldObj != null && Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderOrbit)
		{
			stats.landingTicks = 0;
		}
	}
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE, returnAnotherMethod = "sheak")
	@SideOnly(Side.CLIENT)
	public static boolean isSneaking(GCEntityClientPlayerMP player)
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean sheak(GCEntityClientPlayerMP player)
	{
		if (player.worldObj.provider instanceof WorldProviderOrbit && !(player.worldObj.provider instanceof WorldProviderOrbitModif))
		{
			if (FreefallHandler.testFreefall(player)) return false;
			GCPlayerStatsClient stats = GCPlayerStatsClient.get(player);
			if (stats.inFreefall) return false;
		}
		return player.movementInput.sneak && !player.isPlayerSleeping();
	}
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE)
	@SideOnly(Side.CLIENT)
	public static boolean renderOxygenWarningOverlay(OverlayOxygenWarning overlay)
	{
		if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntityRocketFakeTiered)
		{
			return true;
		}
		return false;
	}
}
