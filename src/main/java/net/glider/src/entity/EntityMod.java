package net.glider.src.entity;

import net.glider.src.GliderCore;
import net.glider.src.renderer.RenderEntityRocketFakeTiered;
import net.glider.src.renderer.RenderIndustrialCreeper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityMod {

	public static void init(Side s)
	{
		if (s == Side.SERVER)
		{
			registerEntity();
		} else if (s == Side.CLIENT)
		{
			registerEntityRenderer();
		}
	}
	@SideOnly(Side.CLIENT)
	private static void registerEntityRenderer()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityIndustrialCreeper.class, new RenderIndustrialCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityAIMarker.class, new RenderAIMarker());
		RenderingRegistry.registerEntityRenderingHandler(EntityRocketFakeTiered.class, new RenderEntityRocketFakeTiered());
	}

	private static void registerEntity()
	{
		registerEntity(EntityIndustrialCreeper.class, "IndustrialCreeper", true, 0x0004FF, 0xFF00E1);
		registerEntity(EntityAIMarker.class, "EntityAIMarker", false, 0x0004FF, 0xFF00E1);

		registerEntity(EntityRocketFakeTiered.class, "EntiyRocketFakeTiered");
	}

	private static void registerEntity(Class entityClass, String entityName, boolean spawn, int solidColor, int spotColor)
	{
		int randomId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, GliderCore.instance, 64, 1, true);
		if (spawn) EntityRegistry.addSpawn(entityClass, 2, 0, 1, EnumCreatureType.creature, BiomeGenBase.forest);

		createEgg(randomId, solidColor, spotColor);
	}

	private static void registerEntity(Class entityClass, String entityName, boolean spawn)
	{
		int randomId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, GliderCore.instance, 64, 1, true);
		if (spawn) EntityRegistry.addSpawn(entityClass, 2, 0, 1, EnumCreatureType.creature, BiomeGenBase.forest);

	}

	private static void registerEntity(Class entityClass, String entityName)
	{
		int randomId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, GliderCore.instance, 64, 1, true);

	}

	private static void createEgg(int randomId, int solidColor, int spotColor)
	{
		EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColor, spotColor));

	}

}