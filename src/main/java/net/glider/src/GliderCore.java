package net.glider.src;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeSpaceStation;
import micdoodle8.mods.galacticraft.core.recipe.RecipeManagerGC;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.blocks.BlockMod;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.glider.src.entity.EntityMod;
import net.glider.src.gui.GuiHandler;
import net.glider.src.handlers.Events;
import net.glider.src.handlers.ItemsToolTips;
import net.glider.src.items.ItemMod;
import net.glider.src.network.PacketHandler;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.tiles.TileEntityGravitySource;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.Config;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = GliderModInfo.MOD_ID, name = GliderModInfo.MOD_NAME, version = GliderModInfo.Version, acceptedMinecraftVersions = "1.7.10", dependencies = 
				"required-after:OpenComputers@[1.5,);" 
			  + "required-after:GalacticraftCore@[3.0,);" 
			  + "required-after:GalacticraftMars@[3.0,);" 
			  + "required-after:RenderPlayerAPI;")
//-Dfml.coreMods.load=net.glider.src.handlers.hooks.GliderHookLoader

public class GliderCore {
	
	//TODO before compile make total cleanup
	//TODO Find all client-classes calls on server(and back i think)

	@Instance(GliderModInfo.MOD_ID)
	public static GliderCore instance;

	@SidedProxy(clientSide = "net.glider.src.ClientProxy", serverSide = "net.glider.src.CommonProxy")
	public static CommonProxy proxy;

	public static Satellite satelliteAdvancedSpaceStation;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.PreInit(event);
		PacketHandler.register();
		//TODO fix domain and naming problem!
		Config.init("Glider");
		BlockMod.init();
		BlockContainerMod.init();
		ItemMod.init();
		EntityMod.init(Side.SERVER);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
		//TODO After finishing add recipes to ALL items. with compactability(IC2, etc)
		// GameRegistry.addRecipe(new ItemStack(ItemMod.HalfGlider, 1), new
		// Object[] {"001", "012","122", ('1'), Items.stick,'2',
		// Items.leather});
		// GameRegistry.addRecipe(new ItemStack(ItemMod.Glider, 1), new Object[]
		// {"212", ('1'), Items.slime_ball,'2', ItemMod.HalfGlider});
		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandler());
		GameRegistry.registerTileEntity(TileEntityInfo.class, "Info");
		GameRegistry.registerTileEntity(TileEntityRemoveInfo.class, "RemoveInfo");
		GameRegistry.registerTileEntity(TileEntityDockingPort.class, "DockingPort");
		GameRegistry.registerTileEntity(TileEntityGravitySource.class, "GravitySource");
		GameRegistry.registerTileEntity(TileEntityArmorStand.class, "ArmorStand");
		// EntityList.addMapping(EntityIndustrialCreeper.class,
		// "IndustrialCreeper", 100);

//		GameRegistry.addSmelting(ItemMod.DebugTool, new ItemStack(ItemMod.fuelloader, 1, 0), 0); 
		
		GLoger.logInfo("*****START REGISTERING ASS*****");
		satelliteAdvancedSpaceStation = (Satellite) new Satellite("advSpaceStation.mars").setParentBody(MarsModule.planetMars).setRelativeSize(0.2667F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(10F, 10F)).setRelativeOrbitTime(1 / 0.055F);
		satelliteAdvancedSpaceStation.setDimensionInfo(40, 41, WorldProviderOrbitModif.class).setTierRequired(1);
		satelliteAdvancedSpaceStation.setBodyIcon(new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/gui/celestialbodies/spaceStation.png"));

		GalaxyRegistry.registerSatellite(satelliteAdvancedSpaceStation);
		GalacticraftRegistry.registerProvider(40, WorldProviderOrbitModif.class, false, 0);
		GalacticraftRegistry.registerProvider(41, WorldProviderOrbitModif.class, true, 0);
		GalacticraftRegistry.registerTeleportType(WorldProviderOrbitModif.class, new TeleportTypeSpaceStation());

		final HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
		inputMap.put("ingotTin", 32);
		inputMap.put(RecipeManagerGC.aluminumIngots, 16);
		inputMap.put("waferAdvanced", 1);
		inputMap.put(Items.iron_ingot, 24);
		GalacticraftRegistry.registerSpaceStation(new SpaceStationType(40, MarsModule.planetMars.getDimensionID(), new SpaceStationRecipe(inputMap)));

		MinecraftForge.EVENT_BUS.register(new Events());
		MinecraftForge.EVENT_BUS.register(new ItemsToolTips());

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.PostInit(event);
	}
}