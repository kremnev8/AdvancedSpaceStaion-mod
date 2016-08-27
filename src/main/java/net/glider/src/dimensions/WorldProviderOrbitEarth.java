
package net.glider.src.dimensions;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderOrbitEarth extends WorldProviderOrbitModif {
	
	@Override
	public CelestialBody getCelestialBody()
	{
		return GalacticraftCore.satelliteSpaceStation;
	}
	
	public boolean isDaytime()
	{
		final float a = this.worldObj.getCelestialAngle(0F);
		return a < 0.42F || a > 0.58F;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1)
	{
		final float var2 = this.worldObj.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);
		
		if (var3 < 0.0F)
		{
			var3 = 0.0F;
		}
		
		if (var3 > 1.0F)
		{
			var3 = 1.0F;
		}
		
		return var3 * var3 * 0.5F + 0.3F;
	}
	
	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return this.dimensionId;
	}
	
	@Override
	public String getPlanetToOrbit()
	{
		return "Overworld";
	}
	
	@Override
	public String getSaveFolder()
	{
		return "DIM_SPACESTATION" + this.spaceStationDimensionID;
	}
	
	@Override
	public double getSolarEnergyMultiplier()
	{
		return ConfigManagerCore.spaceStationEnergyScalar;
	}
	
	@Override
	public boolean canSpaceshipTierPass(int tier)
	{
		return tier > 0;
	}
}
