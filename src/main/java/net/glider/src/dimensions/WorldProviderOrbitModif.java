package net.glider.src.dimensions;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.dimension.SpinManager;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOrbit;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import net.glider.src.GliderCore;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.ClientGravityDataRecivePacket;
import net.glider.src.tiles.TileEntityGravitySource;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderOrbitModif extends WorldProviderOrbit {
	public int spaceStationDimensionID;
	
	public GravityManager spinManager = new GravityManager(this);
	
	/**
	 * Do not return null here, the calling code does not perform a null check!
	 */
	public SpinManager getSpinManager()
	{
		return (SpinManager) spinManager;
	}
	
	public double getArtificalGravity()
	{
		return GravityManager.artificialG;
	}
	
	@Override
	public void setDimension(int var1)
	{
		this.spaceStationDimensionID = var1;
		super.setDimension(var1);
	}
	
	@Override
	public CelestialBody getCelestialBody()
	{
		return GliderCore.satelliteAdvancedSpaceStation;
	}
	
	@Override
	public Vector3 getFogColor()
	{
		return new Vector3(0, 0, 0);
	}
	
	@Override
	public Vector3 getSkyColor()
	{
		return new Vector3(0, 0, 0);
	}
	
	@Override
	public boolean canRainOrSnow()
	{
		return false;
	}
	
	@Override
	public boolean hasSunset()
	{
		return false;
	}
	
	@Override
	public long getDayLength()
	{
		return 24000L;
	}
	
	@Override
	public boolean shouldForceRespawn()
	{
		return true;
	}
	
	@Override
	public Class<? extends IChunkProvider> getChunkProviderClass()
	{
		return ChunkProviderOrbitModif.class;
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
	public void updateWeather()
	{
		super.updateWeather();
		
		if (this.worldObj != null && !this.worldObj.isRemote)
		{
			DockingPortSaveData savef = DockingPortSaveData.forWorld(this.worldObj);
			if (savef != null)
			{
				if (savef.GraviySources.size() > 0)
				{
					spinManager.ArtificialForces.clear();
					for (int i = 0; i < savef.GraviySources.size(); i++)
					{
						if (this.worldObj.getTileEntity(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]) != null)
						{
							TileEntity te = this.worldObj.getTileEntity(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]);
							if (te instanceof TileEntityGravitySource)
							{
								spinManager.ArtificialForces.add(((TileEntityGravitySource) te).gravityAddition);
							}
						}
					}
					double sum = 0;
					java.util.Iterator<Double> forces = spinManager.ArtificialForces.iterator();
					for (int i = 0; forces.hasNext(); i++)
					{
						sum += forces.next();
					}
					if (sum != spinManager.artificialG)
					{
						spinManager.artificialG = sum;
						PacketHandler.sendToDimension(new ClientGravityDataRecivePacket(spinManager.ArtificialForces), this.dimensionId);
					}
				}
				
			}
		}
		
	}
	
	@Override
	public boolean isSkyColored()
	{
		return false;
	}
	
	@Override
	public double getHorizon()
	{
		return 44.0D;
	}
	
	@Override
	public int getAverageGroundLevel()
	{
		return 64;
	}
	
	@Override
	public boolean canCoordinateBeSpawn(int var1, int var2)
	{
		return true;
	}
	
	public ChunkCoordinates getRandomizedSpawnPoint()
	{
		ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());
		
		chunkcoordinates.posX = 0;
		chunkcoordinates.posZ = 0;
		chunkcoordinates.posY = 64;
		
		return chunkcoordinates;
	}
	
	//Overriding only in case the Galacticraft API is not up-to-date
	//(with up-to-date API this makes zero difference)
	@Override
	public boolean canRespawnHere()
	{
		return true;
	}
	
	//Overriding only in case the Galacticraft API is not up-to-date
	//(with up-to-date API this makes zero difference)
	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return this.dimensionId;
	}
	
	@Override
	public String getDimensionName()
	{
		return "Space Station " + this.spaceStationDimensionID;
	}
	
	@Override
	public float getGravity()
	{
		return (float) (0.0F * (1 - getArtificalGravity()));
	}
	
	@Override
	public String getPlanetToOrbit()
	{
		return MarsModule.planetMars.getUnlocalizedName();
	}
	
	@Override
	public String getSaveFolder()
	{
		return "DIM_ADVSPACESTATION" + this.spaceStationDimensionID;
	}
	
	@Override
	public double getSolarEnergyMultiplier()
	{
		return ConfigManagerCore.spaceStationEnergyScalar;
	}
	
	@Override
	public double getYCoordinateToTeleport()
	{
		return 1200;
	}
	
	@Override
	public boolean canSpaceshipTierPass(int tier)
	{
		return tier > 1;
	}
	
	@Override
	public float getFallDamageModifier()
	{
		return (float) (getArtificalGravity() > 0.5D ? (1F * getArtificalGravity()) - 0.2D : 0.4F);
	}
	
	@Override
	public float getSoundVolReductionAmount()
	{
		return 50.0F;
	}
	
}
