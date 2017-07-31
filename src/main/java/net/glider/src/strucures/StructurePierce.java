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

public class StructurePierce extends Structure {
	
	public StructurePierce()
	{
		super(false);
	}
	
	@Override
	public void Build(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.WEST)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x - 2, y - 2, z - 1, block1, 4, 2);
			world.setBlock(x - 2, y - 2, z + 0, block1, 4, 2);
			world.setBlock(x - 2, y - 2, z + 1, block1, 4, 2);
			world.setBlock(x - 1, y - 2, z - 2, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x - 1, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 2, block1, 4, 2);
			world.setBlock(x + 0, y - 2, z - 2, block1, 6, 2);
			world.setBlock(x + 0, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 0, y - 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 2, z - 2, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 2, block1, 0, 2);
			block1 = Blocks.air;
			world.setBlock(x - 1, y + 2, z - 1, block1, 0, 2);
			world.setBlock(x - 1, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x - 1, y + 2, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z - 1, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 1, block1, 0, 2);
			
		} else if (dir == ForgeDirection.EAST)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x + 0, y - 2, z - 2, block1, 6, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x + 0, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 0, y - 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 2, z - 2, block1, 1, 2);
			world.setBlock(x + 0, y + 2, z + 2, block1, 1, 2);
			world.setBlock(x + 1, y - 2, z - 2, block1, 5, 2);
			world.setBlock(x + 1, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 2, block1, 5, 2);
			world.setBlock(x + 2, y - 2, z - 1, block1, 5, 2);
			world.setBlock(x + 2, y - 2, z + 0, block1, 5, 2);
			world.setBlock(x + 2, y - 2, z + 1, block1, 5, 2);
			
			block1 = Blocks.air;
			world.setBlock(x + 0, y + 2, z - 1, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 1, block1, 0, 2);
			world.setBlock(x + 1, y + 2, z - 1, block1, 0, 2);
			world.setBlock(x + 1, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 2, z + 1, block1, 0, 2);
		} else if (dir == ForgeDirection.NORTH)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x - 2, y - 2, z - 1, block1, 6, 2);
			world.setBlock(x - 2, y - 2, z + 0, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x - 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 2, z + 0, block1, 2, 2);
			world.setBlock(x - 1, y - 2, z - 2, block1, 6, 2);
			world.setBlock(x - 1, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z - 2, block1, 6, 2);
			world.setBlock(x + 0, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z - 2, block1, 6, 2);
			world.setBlock(x + 1, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y - 2, z - 1, block1, 6, 2);
			world.setBlock(x + 2, y - 2, z + 0, block1, 5, 2);
			world.setBlock(x + 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block1, 2, 2);
			block1 = Blocks.air;
			world.setBlock(x - 1, y + 2, z - 1, block1, 0, 2);
			world.setBlock(x - 1, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z - 1, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 2, z - 1, block1, 0, 2);
			world.setBlock(x + 1, y + 2, z + 0, block1, 0, 2);
			
		} else if (dir == ForgeDirection.SOUTH)
		{
			Block block1 = GCBlocks.tinStairs1;
			world.setBlock(x - 2, y - 2, z + 0, block1, 4, 2);
			world.setBlock(x - 2, y - 2, z + 1, block1, 7, 2);
			Block block2 = GCBlocks.basicBlock;
			world.setBlock(x - 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 2, z + 0, block1, 3, 2);
			world.setBlock(x - 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 2, y - 2, z + 0, block1, 5, 2);
			world.setBlock(x + 2, y - 2, z + 1, block1, 7, 2);
			world.setBlock(x + 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block1, 3, 2);
			
			block1 = Blocks.air;
			world.setBlock(x - 1, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x - 1, y + 2, z + 1, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 1, block1, 0, 2);
			world.setBlock(x + 1, y + 2, z + 0, block1, 0, 2);
			world.setBlock(x + 1, y + 2, z + 1, block1, 0, 2);
			
		}
		
	}
	
	@Override
	public void deconstruct(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (dir == ForgeDirection.WEST)
		{
			Block block1 = Blocks.air;
			world.setBlock(x - 2, y - 2, z - 1, block1, 4, 2);
			world.setBlock(x - 2, y - 2, z + 0, block1, 4, 2);
			world.setBlock(x - 2, y - 2, z + 1, block1, 4, 2);
			world.setBlock(x - 1, y - 2, z - 2, block1, 4, 2);
			Block block2 = Blocks.air;
			world.setBlock(x - 1, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 2, block1, 4, 2);
			world.setBlock(x + 0, y - 2, z - 2, block1, 6, 2);
			world.setBlock(x + 0, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 0, y - 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 2, z - 2, block1, 0, 2);
			world.setBlock(x + 0, y + 2, z + 2, block1, 0, 2);
			
		} else if (dir == ForgeDirection.EAST)
		{
			Block block1 = Blocks.air;
			world.setBlock(x + 0, y - 2, z - 2, block1, 6, 2);
			Block block2 = Blocks.air;
			world.setBlock(x + 0, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 0, y - 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y - 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 0, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z - 2, block2, 4, 2);
			world.setBlock(x + 0, y + 1, z + 2, block2, 4, 2);
			world.setBlock(x + 0, y + 2, z - 2, block1, 1, 2);
			world.setBlock(x + 0, y + 2, z + 2, block1, 1, 2);
			world.setBlock(x + 1, y - 2, z - 2, block1, 5, 2);
			world.setBlock(x + 1, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 2, block1, 5, 2);
			world.setBlock(x + 2, y - 2, z - 1, block1, 5, 2);
			world.setBlock(x + 2, y - 2, z + 0, block1, 5, 2);
			world.setBlock(x + 2, y - 2, z + 1, block1, 5, 2);
		} else if (dir == ForgeDirection.NORTH)
		{
			Block block1 = Blocks.air;
			world.setBlock(x - 2, y - 2, z - 1, block1, 6, 2);
			world.setBlock(x - 2, y - 2, z + 0, block1, 4, 2);
			Block block2 = Blocks.air;
			world.setBlock(x - 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 2, z + 0, block1, 2, 2);
			world.setBlock(x - 1, y - 2, z - 2, block1, 6, 2);
			world.setBlock(x - 1, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z - 2, block1, 6, 2);
			world.setBlock(x + 0, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z - 2, block1, 6, 2);
			world.setBlock(x + 1, y - 2, z - 1, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y - 2, z - 1, block1, 6, 2);
			world.setBlock(x + 2, y - 2, z + 0, block1, 5, 2);
			world.setBlock(x + 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block1, 2, 2);
			
		} else if (dir == ForgeDirection.SOUTH)
		{
			Block block1 = Blocks.air;
			world.setBlock(x - 2, y - 2, z + 0, block1, 4, 2);
			world.setBlock(x - 2, y - 2, z + 1, block1, 7, 2);
			Block block2 = Blocks.air;
			world.setBlock(x - 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x - 2, y + 2, z + 0, block1, 3, 2);
			world.setBlock(x - 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x - 1, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 0, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 0, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 1, y - 2, z + 0, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 1, block2, 4, 2);
			world.setBlock(x + 1, y - 2, z + 2, block1, 7, 2);
			world.setBlock(x + 2, y - 2, z + 0, block1, 5, 2);
			world.setBlock(x + 2, y - 2, z + 1, block1, 7, 2);
			world.setBlock(x + 2, y - 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 0, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 1, z + 0, block2, 4, 2);
			world.setBlock(x + 2, y + 2, z + 0, block1, 3, 2);
			
		}
		
	}
	
	/**
	 * @param meta
	 *            0 - everything, 1 - everything excluding pierce, 2 - only add
	 *            structures, 3 - only window(only rot == 0), 4 - solar panels,
	 *            5 - greenhouse, 6 - pierce
	 */
	@Override
	public boolean Check(World world, ForgeDirection dir, int x, int y, int z, int meta)
	{
		if (meta != 0 && meta != 6 && meta != -1)
		{
			return false;
		}
		if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST || dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH) return true;
		else return false;
	}
	
	@Override
	public void ClearWay(World world, ForgeDirection dir, int x, int y, int z)
	{
	}
	
	@Override
	public boolean isHidden()
	{
		return false;
	}
	
	@Override
	public String getName()
	{
		return StatCollector.translateToLocal("builder.pierce.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "pierce";
	}
	
	@Override
	public Structure copy()
	{
		StructurePierce Nstr = new StructurePierce();
		Nstr.Configure(placementPos != null ? placementPos.clone() : new int[] { 0, 0, 0 }, placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 8, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 4, ItemMod.scaffold_meta)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.specialFunc = StatCollector.translateToLocal("builder.side_info.funcs.pierce.name");
		return data;
	}
	
}
