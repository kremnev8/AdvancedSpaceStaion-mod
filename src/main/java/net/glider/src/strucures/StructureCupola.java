
package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.glider.src.items.ItemMod;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureCupola extends Structure {
	
	private boolean hidden;
	
	public StructureCupola(boolean hidden)
	{
		super(hidden);
		this.hidden = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureCupola Nstr = new StructureCupola(hidden);
		Nstr.Configure(placementPos != null ? placementPos.clone() : new int[] { 0, 0, 0 }, placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public void deconstruct(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.DOWN)
		{
			Block block1 = Blocks.air;
			world.setBlock(x + -3, y + -1, z + -1, block1, 4, 2);
			world.setBlock(x + -3, y + -1, z + 0, block1, 4, 2);
			world.setBlock(x + -3, y + -1, z + 1, block1, 4, 2);
			world.setBlock(x + -3, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + -3, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + -3, y + 0, z + 1, block1, 4, 2);
			Block block2 = Blocks.air;
			world.setBlock(x + -2, y + -2, z + -2, block2, 8, 2);
			Block block3 = Blocks.air;
			world.setBlock(x + -2, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + -2, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + -2, y + -2, z + 2, block2, 8, 2);
			world.setBlock(x + -2, y + -1, z + -2, block1, 4, 2);
			world.setBlock(x + -2, y + -1, z + -1, block3, 1, 2);
			world.setBlock(x + -2, y + -1, z + 0, block3, 1, 2);
			world.setBlock(x + -2, y + -1, z + 1, block3, 1, 2);
			world.setBlock(x + -2, y + -1, z + 2, block1, 4, 2);
			world.setBlock(x + -2, y + 0, z + -2, block1, 3, 2);
			world.setBlock(x + -2, y + 0, z + 2, block1, 3, 2);
			world.setBlock(x + -1, y + -2, z + -2, block3, 6, 2);
			Block block4 = Blocks.air;
			world.setBlock(x + -1, y + -2, z + -1, block4, 0, 2);
			world.setBlock(x + -1, y + -2, z + 0, block4, 0, 2);
			world.setBlock(x + -1, y + -2, z + 1, block4, 0, 2);
			world.setBlock(x + -1, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -1, y + -1, z + -3, block1, 4, 2);
			world.setBlock(x + -1, y + -1, z + -2, block3, 3, 2);
			world.setBlock(x + -1, y + -1, z + 2, block3, 2, 2);
			world.setBlock(x + -1, y + -1, z + 3, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + -3, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + 3, block1, 4, 2);
			world.setBlock(x + 0, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + 0, y + -2, z + -1, block4, 0, 2);
			world.setBlock(x + 0, y + -2, z + 0, block4, 0, 2);
			world.setBlock(x + 0, y + -2, z + 1, block4, 0, 2);
			world.setBlock(x + 0, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + 0, y + -1, z + -3, block1, 4, 2);
			world.setBlock(x + 0, y + -1, z + -2, block3, 3, 2);
			world.setBlock(x + 0, y + -1, z + 2, block3, 2, 2);
			world.setBlock(x + 0, y + -1, z + 3, block1, 4, 2);
			world.setBlock(x + 0, y + 0, z + -3, block1, 4, 2);
			world.setBlock(x + 0, y + 0, z + 3, block1, 4, 2);
			world.setBlock(x + 1, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + 1, y + -2, z + -1, block4, 0, 2);
			world.setBlock(x + 1, y + -2, z + 0, block4, 0, 2);
			world.setBlock(x + 1, y + -2, z + 1, block4, 0, 2);
			world.setBlock(x + 1, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + 1, y + -1, z + -3, block1, 4, 2);
			world.setBlock(x + 1, y + -1, z + -2, block3, 3, 2);
			world.setBlock(x + 1, y + -1, z + 2, block3, 2, 2);
			world.setBlock(x + 1, y + -1, z + 3, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + -3, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + 3, block1, 4, 2);
			world.setBlock(x + 2, y + -2, z + -2, block2, 8, 2);
			world.setBlock(x + 2, y + -2, z + -1, block3, 5, 2);
			world.setBlock(x + 2, y + -2, z + 0, block3, 5, 2);
			world.setBlock(x + 2, y + -2, z + 1, block3, 5, 2);
			world.setBlock(x + 2, y + -2, z + 2, block2, 8, 2);
			world.setBlock(x + 2, y + -1, z + -2, block1, 4, 2);
			world.setBlock(x + 2, y + -1, z + -1, block3, 0, 2);
			world.setBlock(x + 2, y + -1, z + 0, block3, 0, 2);
			world.setBlock(x + 2, y + -1, z + 1, block3, 0, 2);
			world.setBlock(x + 2, y + -1, z + 2, block1, 4, 2);
			world.setBlock(x + 2, y + 0, z + -2, block1, 3, 2);
			world.setBlock(x + 2, y + 0, z + 2, block1, 3, 2);
			world.setBlock(x + 3, y + -1, z + -1, block1, 4, 2);
			world.setBlock(x + 3, y + -1, z + 0, block1, 4, 2);
			world.setBlock(x + 3, y + -1, z + 1, block1, 4, 2);
			world.setBlock(x + 3, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + 3, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + 3, y + 0, z + 1, block1, 4, 2);
			
			block1 = GCBlocks.basicBlock;
			world.setBlock(x + -2, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + -2, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + -2, y + 0, z + 1, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + -2, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + 1, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + 2, block1, 4, 2);
			world.setBlock(x + 0, y + 0, z + -2, block1, 4, 2);
			world.setBlock(x + 0, y + 0, z + -1, block1, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z, 2);
			world.setBlock(x + 0, y + 0, z + 1, block1, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + -2, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + 1, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + 2, block1, 4, 2);
			world.setBlock(x + 2, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + 2, y + 0, z + 1, block1, 4, 2);
			
			world.setBlock(x + -3, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + -3, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + -3, y + 0, z + 1, block1, 4, 2);
			
			world.setBlock(x + -1, y + 0, z + -3, block1, 4, 2);
			world.setBlock(x + 0, y + 0, z + -3, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + -3, block1, 4, 2);
			
			world.setBlock(x + 0, y + 0, z + 3, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + 3, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + 3, block1, 4, 2);
			
			world.setBlock(x + 3, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + 3, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + 3, y + 0, z + 1, block1, 4, 2);
			
			world.setBlock(x + 2, y + 0, z + 2, block1, 4, 2);
			world.setBlock(x - 2, y + 0, z - 2, block1, 4, 2);
			world.setBlock(x + 2, y + 0, z - 2, block1, 4, 2);
			world.setBlock(x - 2, y + 0, z + 2, block1, 4, 2);
			
		}
	}
	
	@Override
	public void Build(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.DOWN)
		{
			Block block1 = GCBlocks.basicBlock;
			world.setBlock(x + -3, y + -1, z + -1, block1, 4, 2);
			world.setBlock(x + -3, y + -1, z + 0, block1, 4, 2);
			world.setBlock(x + -3, y + -1, z + 1, block1, 4, 2);
			world.setBlock(x + -3, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + -3, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + -3, y + 0, z + 1, block1, 4, 2);
			Block block2 = GCBlocks.slabGCHalf;
			world.setBlock(x + -2, y + -2, z + -2, block2, 8, 2);
			Block block3 = GCBlocks.tinStairs1;
			world.setBlock(x + -2, y + -2, z + -1, block3, 4, 2);
			world.setBlock(x + -2, y + -2, z + 0, block3, 4, 2);
			world.setBlock(x + -2, y + -2, z + 1, block3, 4, 2);
			world.setBlock(x + -2, y + -2, z + 2, block2, 8, 2);
			world.setBlock(x + -2, y + -1, z + -2, block1, 4, 2);
			world.setBlock(x + -2, y + -1, z + -1, block3, 1, 2);
			world.setBlock(x + -2, y + -1, z + 0, block3, 1, 2);
			world.setBlock(x + -2, y + -1, z + 1, block3, 1, 2);
			world.setBlock(x + -2, y + -1, z + 2, block1, 4, 2);
			world.setBlock(x + -2, y + 0, z + -2, block1, 3, 2);
			world.setBlock(x + -2, y + 0, z + 2, block1, 3, 2);
			world.setBlock(x + -1, y + -2, z + -2, block3, 6, 2);
			Block block4 = Blocks.glass;
			world.setBlock(x + -1, y + -2, z + -1, block4, 0, 2);
			world.setBlock(x + -1, y + -2, z + 0, block4, 0, 2);
			world.setBlock(x + -1, y + -2, z + 1, block4, 0, 2);
			world.setBlock(x + -1, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + -1, y + -1, z + -3, block1, 4, 2);
			world.setBlock(x + -1, y + -1, z + -2, block3, 3, 2);
			world.setBlock(x + -1, y + -1, z + 2, block3, 2, 2);
			world.setBlock(x + -1, y + -1, z + 3, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + -3, block1, 4, 2);
			world.setBlock(x + -1, y + 0, z + 3, block1, 4, 2);
			world.setBlock(x + 0, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + 0, y + -2, z + -1, block4, 0, 2);
			world.setBlock(x + 0, y + -2, z + 0, block4, 0, 2);
			world.setBlock(x + 0, y + -2, z + 1, block4, 0, 2);
			world.setBlock(x + 0, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + 0, y + -1, z + -3, block1, 4, 2);
			world.setBlock(x + 0, y + -1, z + -2, block3, 3, 2);
			world.setBlock(x + 0, y + -1, z + 2, block3, 2, 2);
			world.setBlock(x + 0, y + -1, z + 3, block1, 4, 2);
			world.setBlock(x + 0, y + 0, z + -3, block1, 4, 2);
			world.setBlock(x + 0, y + 0, z + 3, block1, 4, 2);
			world.setBlock(x + 1, y + -2, z + -2, block3, 6, 2);
			world.setBlock(x + 1, y + -2, z + -1, block4, 0, 2);
			world.setBlock(x + 1, y + -2, z + 0, block4, 0, 2);
			world.setBlock(x + 1, y + -2, z + 1, block4, 0, 2);
			world.setBlock(x + 1, y + -2, z + 2, block3, 7, 2);
			world.setBlock(x + 1, y + -1, z + -3, block1, 4, 2);
			world.setBlock(x + 1, y + -1, z + -2, block3, 3, 2);
			world.setBlock(x + 1, y + -1, z + 2, block3, 2, 2);
			world.setBlock(x + 1, y + -1, z + 3, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + -3, block1, 4, 2);
			world.setBlock(x + 1, y + 0, z + 3, block1, 4, 2);
			world.setBlock(x + 2, y + -2, z + -2, block2, 8, 2);
			world.setBlock(x + 2, y + -2, z + -1, block3, 5, 2);
			world.setBlock(x + 2, y + -2, z + 0, block3, 5, 2);
			world.setBlock(x + 2, y + -2, z + 1, block3, 5, 2);
			world.setBlock(x + 2, y + -2, z + 2, block2, 8, 2);
			world.setBlock(x + 2, y + -1, z + -2, block1, 4, 2);
			world.setBlock(x + 2, y + -1, z + -1, block3, 0, 2);
			world.setBlock(x + 2, y + -1, z + 0, block3, 0, 2);
			world.setBlock(x + 2, y + -1, z + 1, block3, 0, 2);
			world.setBlock(x + 2, y + -1, z + 2, block1, 4, 2);
			world.setBlock(x + 2, y + 0, z + -2, block1, 3, 2);
			world.setBlock(x + 2, y + 0, z + 2, block1, 3, 2);
			world.setBlock(x + 3, y + -1, z + -1, block1, 4, 2);
			world.setBlock(x + 3, y + -1, z + 0, block1, 4, 2);
			world.setBlock(x + 3, y + -1, z + 1, block1, 4, 2);
			world.setBlock(x + 3, y + 0, z + -1, block1, 4, 2);
			world.setBlock(x + 3, y + 0, z + 0, block1, 4, 2);
			world.setBlock(x + 3, y + 0, z + 1, block1, 4, 2);
			
		}
	}
	
	@Override
	public boolean Check(World world, ForgeDirection dir, int x, int y, int z, int meta)
	{
		if (meta != 2 && meta != 0 && meta != -1)
		{
			return false;
		}
		if (dir == ForgeDirection.DOWN)
		{
			return true;
		} else return false;
	}
	
	@Override
	public void ClearWay(World world, ForgeDirection dir, int x, int y, int z)
	{
		Block block1 = Blocks.air;
		world.setBlock(x + -2, y + 0, z + -1, block1, 0, 2);
		world.setBlock(x + -2, y + 0, z + 0, block1, 0, 2);
		world.setBlock(x + -2, y + 0, z + 1, block1, 0, 2);
		world.setBlock(x + -1, y + 0, z + -2, block1, 0, 2);
		world.setBlock(x + -1, y + 0, z + -1, block1, 0, 2);
		world.setBlock(x + -1, y + 0, z + 0, block1, 0, 2);
		world.setBlock(x + -1, y + 0, z + 1, block1, 0, 2);
		world.setBlock(x + -1, y + 0, z + 2, block1, 0, 2);
		world.setBlock(x + 0, y + 0, z + -2, block1, 0, 2);
		world.setBlock(x + 0, y + 0, z + -1, block1, 0, 2);
		world.setBlock(x + 0, y + 0, z + 0, block1, 0, 2);
		world.setBlock(x + 0, y + 0, z + 1, block1, 0, 2);
		world.setBlock(x + 0, y + 0, z + 2, block1, 0, 2);
		world.setBlock(x + 1, y + 0, z + -2, block1, 0, 2);
		world.setBlock(x + 1, y + 0, z + -1, block1, 0, 2);
		world.setBlock(x + 1, y + 0, z + 0, block1, 0, 2);
		world.setBlock(x + 1, y + 0, z + 1, block1, 0, 2);
		world.setBlock(x + 1, y + 0, z + 2, block1, 0, 2);
		world.setBlock(x + 2, y + 0, z + -1, block1, 0, 2);
		world.setBlock(x + 2, y + 0, z + 0, block1, 0, 2);
		world.setBlock(x + 2, y + 0, z + 1, block1, 0, 2);
		
	}
	
	@Override
	public boolean isHidden()
	{
		return hidden;
	}
	
	@Override
	public String getName()
	{
		return StatCollector.translateToLocal("builder.cupola.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "cupola";
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 18, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 8, ItemMod.scaffold_meta)));
		items.add(new OreDictItemStack(new ItemStack(Blocks.glass, 9)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.specialFunc = StatCollector.translateToLocal("builder.side_info.funcs.cupola.name");
		return data;
	}
	
}
