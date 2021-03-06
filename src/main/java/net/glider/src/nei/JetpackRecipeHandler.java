
package net.glider.src.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class JetpackRecipeHandler extends TemplateRecipeHandler {
	private static final ResourceLocation jetpackGuiTexture = new ResourceLocation(GliderModInfo.ModTestures, "textures/gui/schematic_jetpack.png");
	
	public String getRecipeId()
	{
		return GliderModInfo.MOD_ID + ".jetpack";
	}
	
	@Override
	public int recipiesPerPage()
	{
		return 1;
	}
	
	public Set<Entry<ArrayList<PositionedStack>, PositionedStack>> getRecipes()
	{
		HashMap<ArrayList<PositionedStack>, PositionedStack> recipes = new HashMap<ArrayList<PositionedStack>, PositionedStack>();
		
		for (Entry<HashMap<Integer, PositionedStack>, PositionedStack> stack : NEIGliderConfig.getJetpackRecipes())
		{
			ArrayList<PositionedStack> inputStacks = new ArrayList<PositionedStack>();
			
			for (Map.Entry<Integer, PositionedStack> input : stack.getKey().entrySet())
			{
				inputStacks.add(input.getValue());
			}
			
			recipes.put(inputStacks, stack.getValue());
		}
		
		return recipes.entrySet();
	}
	
	@Override
	public void drawBackground(int recipe)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiDraw.changeTexture(JetpackRecipeHandler.jetpackGuiTexture);
		GuiDraw.drawTexturedModalRect(0, -8, 3, 32, 168, 104);
	}
	
	@Override
	public void loadTransferRects()
	{}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if (outputId.equals(this.getRecipeId()))
		{
			for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
			{
				this.arecipes.add(new CachedRocketRecipe(irecipe));
			}
		} else
		{
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
		{
			if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getValue().item, result))
			{
				this.arecipes.add(new CachedRocketRecipe(irecipe));
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
		{
			for (final PositionedStack pstack : irecipe.getKey())
			{
				if (NEIServerUtils.areStacksSameTypeCrafting(ingredient, pstack.item))
				{
					this.arecipes.add(new CachedRocketRecipe(irecipe));
					break;
				}
			}
		}
	}
	
	public class CachedRocketRecipe extends TemplateRecipeHandler.CachedRecipe {
		public ArrayList<PositionedStack> input;
		public PositionedStack output;
		
		@Override
		public ArrayList<PositionedStack> getIngredients()
		{
			return this.input;
		}
		
		@Override
		public PositionedStack getResult()
		{
			return this.output;
		}
		
		public CachedRocketRecipe(ArrayList<PositionedStack> pstack1, PositionedStack pstack2)
		{
			super();
			this.input = pstack1;
			this.output = pstack2;
		}
		
		public CachedRocketRecipe(Map.Entry<ArrayList<PositionedStack>, PositionedStack> recipe)
		{
			this(recipe.getKey(), recipe.getValue());
		}
	}
	
	@Override
	public String getRecipeName()
	{
		return GCCoreUtil.translate("tile.rocketWorkbench.name");
	}
	
	@Override
	public String getGuiTexture()
	{
		return GliderModInfo.ModTestures + "textures/gui/schematic_jetpack.png";
	}
	
	@Override
	public void drawForeground(int recipe)
	{}
}
