
package net.glider.src.nei;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.items.ItemMod;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.item.ItemStack;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIGliderConfig implements IConfigureNEI {
	private static HashMap<HashMap<Integer, PositionedStack>, PositionedStack> jetpackRecipes = new HashMap<HashMap<Integer, PositionedStack>, PositionedStack>();
	
	@Override
	public void loadConfig()
	{
		this.registerRecipes();
		
		API.hideItem(new ItemStack(BlockContainerMod.BlockInfo));
		API.hideItem(new ItemStack(BlockContainerMod.BlockRemoveInfo));
		API.hideItem(new ItemStack(BlockContainerMod.BlockFake));
		
		API.registerRecipeHandler(new JetpackRecipeHandler());
		API.registerUsageHandler(new JetpackRecipeHandler());
		/*  API.registerRecipeHandler(new BuggyRecipeHandler());
		  API.registerUsageHandler(new BuggyRecipeHandler());
		  API.registerRecipeHandler(new RefineryRecipeHandler());
		  API.registerUsageHandler(new RefineryRecipeHandler());
		  API.registerRecipeHandler(new CircuitFabricatorRecipeHandler());
		  API.registerUsageHandler(new CircuitFabricatorRecipeHandler());
		  API.registerRecipeHandler(new IngotCompressorRecipeHandler());
		  API.registerUsageHandler(new IngotCompressorRecipeHandler());
		  API.registerRecipeHandler(new ElectricIngotCompressorRecipeHandler());
		  API.registerUsageHandler(new ElectricIngotCompressorRecipeHandler());
		  API.registerHighlightIdentifier(GCBlocks.basicBlock, new GCNEIHighlightHandler());
		  API.registerHighlightIdentifier(GCBlocks.blockMoon, new GCNEIHighlightHandler());
		  API.registerHighlightIdentifier(GCBlocks.fakeBlock, new GCNEIHighlightHandler());*/
	}
	
	@Override
	public String getName()
	{
		return GliderModInfo.MOD_ID + " NEI Plugin";
	}
	
	@Override
	public String getVersion()
	{
		return GliderModInfo.Version;
	}
	
	public void registerJetpackRecipe(HashMap<Integer, PositionedStack> input, PositionedStack output)
	{
		NEIGliderConfig.jetpackRecipes.put(input, output);
	}
	
	public static Set<Entry<HashMap<Integer, PositionedStack>, PositionedStack>> getJetpackRecipes()
	{
		return NEIGliderConfig.jetpackRecipes.entrySet();
	}
	
	public void registerRecipes()
	{
		
		this.addRocketRecipes();
	}
	
	private void addRocketRecipes()
	{
		HashMap<Integer, PositionedStack> input = new HashMap<Integer, PositionedStack>();
		
		int x = 5;
		int y = -15;
		input.put(1, new PositionedStack(new ItemStack(ItemMod.OD_engines_set), x + (18 * 1), y + (18 * 1)));
		input.put(2, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 2), y + (18 * 1)));
		input.put(3, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 3), y + (18 * 1)));
		input.put(4, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 4), y + (18 * 1)));
		input.put(5, new PositionedStack(new ItemStack(ItemMod.OD_engines_set), x + (18 * 5), y + (18 * 1)));
		input.put(6, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 1), y + (18 * 2)));
		input.put(7, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 8), x + (18 * 2), y + (18 * 2)));
		input.put(8, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 8), x + (18 * 3), y + (18 * 2)));
		input.put(9, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 13), x + (18 * 4), y + (18 * 2)));
		input.put(10, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 5), y + (18 * 2)));
		input.put(11, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 1), y + (18 * 3)));
		input.put(12, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 8), x + (18 * 2), y + (18 * 3)));
		input.put(13, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 8), x + (18 * 3), y + (18 * 3)));
		input.put(14, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 14), x + (18 * 4), y + (18 * 3)));
		input.put(15, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 5), y + (18 * 3)));
		input.put(16, new PositionedStack(new ItemStack(ItemMod.OD_engines_set), x + (18 * 1), y + (18 * 4)));
		input.put(17, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 2), y + (18 * 4)));
		input.put(18, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 3), y + (18 * 4)));
		input.put(19, new PositionedStack(new ItemStack(GCItems.basicItem, 1, 9), x + (18 * 4), y + (18 * 4)));
		input.put(20, new PositionedStack(new ItemStack(ItemMod.OD_engines_set), x + (18 * 5), y + (18 * 4)));
		input.put(21, new PositionedStack(new ItemStack(GCItems.flagPole), 5, 57));
		input.put(22, new PositionedStack(new ItemStack(GCItems.flagPole), 5, 75));
		input.put(23, new PositionedStack(new ItemStack(GCItems.flagPole), 113, 57));
		input.put(24, new PositionedStack(new ItemStack(GCItems.flagPole), 113, 75));
		
		this.registerJetpackRecipe(input, new PositionedStack(new ItemStack(ItemMod.spaceJetpack, 1, 0), 139, 58));
		
	}
}