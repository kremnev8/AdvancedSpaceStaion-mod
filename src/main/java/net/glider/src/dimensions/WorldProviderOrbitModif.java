
package net.glider.src.dimensions;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.IOrbitDimension;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.BlockSpinThruster;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.client.SkyProviderOrbit;
import micdoodle8.mods.galacticraft.core.dimension.OrbitSpinSaveData;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOrbit;
import micdoodle8.mods.galacticraft.core.entities.EntityLanderBase;
import micdoodle8.mods.galacticraft.core.entities.player.FreefallHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.util.RedstoneUtil;
import micdoodle8.mods.galacticraft.core.world.gen.ChunkProviderOrbit;
import micdoodle8.mods.galacticraft.core.world.gen.WorldChunkManagerOrbit;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import net.glider.src.GliderCore;
import net.glider.src.items.ItemMod;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.ClientGravityDataRecivePacket;
import net.glider.src.network.packets.GetWorldGravityDataPacket;
import net.glider.src.network.packets.SyncPlayerFallPacket;
import net.glider.src.tiles.TileEntityGravitySource;
import net.glider.src.utils.GLoger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.event.world.WorldEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.ibm.icu.util.BytesTrie.Iterator;

public class WorldProviderOrbitModif extends WorldProviderOrbit {
	public int spaceStationDimensionID;

	public static List<Double> ArtificialForces = new ArrayList();
	public static boolean updatedList = false;
	public static boolean updateddouble = false;

	public double artificialG;

	private static double pPrevMotionX;
	public static double pPrevMotionY;
	private static double pPrevMotionZ;

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
					this.ArtificialForces.clear();
					for (int i = 0; i < savef.GraviySources.size(); i++)
					{
						if (this.worldObj.getTileEntity(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]) != null)
						{
							TileEntity te = this.worldObj.getTileEntity(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]);
							if (te instanceof TileEntityGravitySource)
							{
								ArtificialForces.add(((TileEntityGravitySource) te).gravityAddition);
							}
						}
					}
					double sum = 0;
					java.util.Iterator<Double> forces = ArtificialForces.iterator();
					for (int i = 0; forces.hasNext(); i++)
					{
						sum += forces.next();
					}
					artificialG = sum;
					PacketHandler.sendToDimension(new ClientGravityDataRecivePacket(ArtificialForces), this.dimensionId);
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
		return (float) (0.08F * (1 - artificialG));
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
		return (float) (artificialG > 0.5D ? (1F * artificialG) - 0.2D : 0.4F);
	}

	@Override
	public float getSoundVolReductionAmount()
	{
		return 50.0F;
	}

	@SideOnly(Side.CLIENT)
	public void postVanillaMotion(EntityPlayerSP p)
	{
		if (updatedList && !updateddouble)
		{

			double sum = 0;
			try
			{
				if (ArtificialForces != null)
				{
					for (int i = 0; i < ArtificialForces.size(); i++)
					{
						sum += ArtificialForces.get(i);
					}
				}
			}
			catch (Exception e)
			{
				GLoger.logWarn("Someting Really strange:");
				e.printStackTrace();
			}
			artificialG = sum;
			updateddouble = true;

		}

		if (artificialG < 0.5D)
		{
			super.postVanillaMotion(p);
			GCPlayerStatsClient stats = GCPlayerStatsClient.get(p);

			if (stats.inFreefall && p.capabilities.isCreativeMode && (p.getCurrentArmor(2) != null && p.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack))
			{
				FreefallHandler.freefallMotion(p);
			}
		}

	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if (nbt.getBoolean("UPDATED_GVALUE"))
		{
			artificialG = nbt.getDouble("Artificial_GRAVITY");
			updatedList = nbt.getBoolean("UPDATED_GVALUE");
		}
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setBoolean("UPDATED_GVALUE", updatedList);
		nbt.setDouble("Artificial_GRAVITY", artificialG);

	}
}
