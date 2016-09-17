
package net.glider.src;

import ic2.api.item.IC2Items;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeSpaceStation;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.blocks.BlockMod;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.glider.src.entity.EntityMod;
import net.glider.src.gui.GuiHandler;
import net.glider.src.handlers.Events;
import net.glider.src.handlers.ItemsToolTips;
import net.glider.src.handlers.hooks.Hooks;
import net.glider.src.items.ItemMod;
import net.glider.src.network.PacketHandler;
import net.glider.src.recipes.SchematicJetpack;
import net.glider.src.recipes.SchematicsUtil;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.tiles.TileEntityGravitySource;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.Config;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Loader;
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

@Mod(modid = GliderModInfo.MOD_ID, name = GliderModInfo.MOD_NAME, version = GliderModInfo.Version, acceptedMinecraftVersions = "1.7.10", dependencies = "required-after:OpenComputers@[1.5,);" + "required-after:GalacticraftCore@[3.0,);" + "required-after:GalacticraftMars@[3.0,);" + "required-after:RenderPlayerAPI;")
//-Dfml.coreMods.load=net.glider.src.handlers.hooks.GliderHookLoader
public class GliderCore {
	
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
		
		SchematicRegistry.registerSchematicRecipe(new SchematicJetpack());
		GameRegistry.addRecipe(new ItemStack(ItemMod.smallEngine, 1), new Object[] { "012", "343", "353", '1', Items.flint_and_steel, '2', Blocks.stone_button, '3', new ItemStack(GCItems.basicItem, 1, 9), '4', GCItems.canister, '5', GCItems.oxygenVent });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.OD_engines_set, 1), new Object[] { "101", "020", "101", '1', ItemMod.smallEngine, '2', new ItemStack(GCItems.basicItem, 1, 13) });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.Builder, 1), new Object[] { "121", "343", "151", '1', new ItemStack(GCItems.basicItem, 1, 11), '2', new ItemStack(GCItems.basicItem, 1, 0), '3', Blocks.stone_button, '4', new ItemStack(GCItems.basicItem, 1, 14), '5', GCItems.battery });
		
		GameRegistry.addRecipe(new ItemStack(BlockContainerMod.BlockArmorStand, 1), new Object[] { "010", "010", "222", '1', GCItems.flagPole, '2', new ItemStack(GCBlocks.landingPad, 1, 0) });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.dockingPortComp, 1), new Object[] { "121", "342", "131", '1', new ItemStack(GCItems.basicItem, 1, 7), '2', new ItemStack(GCBlocks.aluminumWire, 1, 1), '3', new ItemStack(GCItems.basicItem, 1, 9), '4', new ItemStack(GCItems.basicItem, 1, 14) });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.emptyIdea, 1), new Object[] { "010", "101", "020", '1', Blocks.glass, '2', Items.iron_ingot });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.filledIdea, 1), new Object[] { "415", "232", "617", '1', Blocks.gold_block, '2', Blocks.iron_block, '3', ItemMod.emptyIdea, '4', new ItemStack(GCItems.basicItem, 1, 14), '5', new ItemStack(GCItems.basicItem, 1, 9), '6', ItemMod.smallEngine, '7', ItemMod.OD_engines_set });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.schematicjetpack, 1), new Object[] { "314", "121", "413", '1', Items.paper, '2', ItemMod.filledIdea, '3', new ItemStack(Items.dye, 1, 0), '4', new ItemStack(Items.dye, 1, 14) });
		
		if (Loader.isModLoaded("IC2"))
		{
			GameRegistry.addRecipe(new ItemStack(ItemMod.rotatingRing, 1), new Object[] { "121", "232", "121", '1', new ItemStack(GCItems.basicItem, 1, 8), '2', new ItemStack(GCItems.basicItem, 1, 9), '3', IC2Items.getItem("elemotor") });
		} else
		{
			GameRegistry.addRecipe(new ItemStack(ItemMod.rotatingRing, 1), new Object[] { "121", "232", "121", '1', new ItemStack(GCItems.basicItem, 1, 8), '2', new ItemStack(GCItems.basicItem, 1, 9), '3', ItemMod.motor });
			
			GameRegistry.addRecipe(new ItemStack(ItemMod.coil, 1), new Object[] { "010", "121", "010", '1', new ItemStack(GCItems.basicItem, 1, 3), '2', GCItems.flagPole });
			
			GameRegistry.addRecipe(new ItemStack(ItemMod.motor, 1), new Object[] { "010", "323", "010", '1', new ItemStack(GCItems.basicItem, 1, 7), '2', GCItems.flagPole, '3', ItemMod.coil });
		}
		
		GameRegistry.addRecipe(new ItemStack(BlockContainerMod.BlockArticialGsource, 1), new Object[] { "121", "343", "212", '1', new ItemStack(GCItems.basicItem, 1, 9), '2', ItemMod.rotatingRing, '3', new ItemStack(GCItems.basicItem, 1, 14), '4', new ItemStack(GCBlocks.aluminumWire, 1, 1) });
		
		GameRegistry.addSmelting(ItemMod.brokenTin, new ItemStack(GCItems.basicItem, 1, 4), 0);
		GameRegistry.addSmelting(ItemMod.brokenSteel, new ItemStack(ItemMod.ingSteel, 1, ItemMod.ingSteelMeta), 0);
		GameRegistry.addSmelting(ItemMod.brokenAluminum, new ItemStack(GCItems.basicItem, 1, 5), 0);
		
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		
		input.put(1, new ItemStack(ItemMod.OD_engines_set));
		input.put(5, new ItemStack(ItemMod.OD_engines_set));
		input.put(16, new ItemStack(ItemMod.OD_engines_set));
		input.put(20, new ItemStack(ItemMod.OD_engines_set));
		
		input.put(2, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(3, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(4, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(6, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(10, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(11, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(15, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(17, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(18, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(19, new ItemStack(GCItems.basicItem, 1, 9));
		
		input.put(7, new ItemStack(GCItems.basicItem, 1, 8));
		input.put(8, new ItemStack(GCItems.basicItem, 1, 8));
		input.put(9, new ItemStack(GCItems.basicItem, 1, 13));
		
		input.put(12, new ItemStack(GCItems.basicItem, 1, 8));
		input.put(13, new ItemStack(GCItems.basicItem, 1, 8));
		input.put(14, new ItemStack(GCItems.basicItem, 1, 14));
		
		input.put(21, new ItemStack(GCItems.flagPole));
		input.put(22, new ItemStack(GCItems.flagPole));
		input.put(23, new ItemStack(GCItems.flagPole));
		input.put(24, new ItemStack(GCItems.flagPole));
		
		SchematicsUtil.addJetpackRecipe(new NasaWorkbenchRecipe(new ItemStack(ItemMod.spaceJetpack), input));
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandler());
		GameRegistry.registerTileEntity(TileEntityInfo.class, "Info");
		GameRegistry.registerTileEntity(TileEntityRemoveInfo.class, "RemoveInfo");
		GameRegistry.registerTileEntity(TileEntityDockingPort.class, "DockingPort");
		GameRegistry.registerTileEntity(TileEntityGravitySource.class, "GravitySource");
		GameRegistry.registerTileEntity(TileEntityArmorStand.class, "ArmorStand");
		
		satelliteAdvancedSpaceStation = (Satellite) new Satellite("advSpaceStation.mars").setParentBody(MarsModule.planetMars).setRelativeSize(0.2667F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(10F, 10F)).setRelativeOrbitTime(1 / 0.055F);
		satelliteAdvancedSpaceStation.setDimensionInfo(40, 41, WorldProviderOrbitModif.class).setTierRequired(1);
		satelliteAdvancedSpaceStation.setBodyIcon(new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/gui/celestialbodies/spaceStation.png"));
		
		GalaxyRegistry.registerSatellite(satelliteAdvancedSpaceStation);
		GalacticraftRegistry.registerProvider(40, WorldProviderOrbitModif.class, false, 0);
		GalacticraftRegistry.registerProvider(41, WorldProviderOrbitModif.class, true, 0);
		GalacticraftRegistry.registerTeleportType(WorldProviderOrbitModif.class, new TeleportTypeSpaceStation());
		
		final HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
		inputMap.put(new ItemStack(GCItems.basicItem, 1, 7), 64);
		inputMap.put(new ItemStack(Items.glowstone_dust), 8);
		inputMap.put(new ItemStack(GCItems.basicItem, 1, 13), 5);
		inputMap.put(new ItemStack(ItemMod.ironScaffold, 1, ItemMod.scaffold_meta), 48);
		inputMap.put(new ItemStack(MarsItems.marsItemBasic, 1, 2), 8);
		Hooks.ignoreThis = true;
		GalacticraftRegistry.registerSpaceStation(new SpaceStationType(ConfigManagerCore.idDimensionOverworldOrbit, ConfigManagerCore.idDimensionOverworld, new SpaceStationRecipe(inputMap)));
		Hooks.ignoreThis = false;
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