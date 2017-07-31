package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.blocks.BlockSolar;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.tile.TileEntitySolar;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureSolarPanel extends StructureRotatable {
	
	private boolean hidden;
	private int rot;
	
	public StructureSolarPanel(boolean hidden)
	{
		super(hidden);
		this.hidden = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureSolarPanel Nstr = new StructureSolarPanel(hidden);
		Nstr.Configure(placementPos != null ? placementPos.clone() : new int[] { 0, 0, 0 }, placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public void setRotation(int rot)
	{
		this.rot = rot;
	}
	
	@Override
	public int getRotation()
	{
		return rot;
	}
	
	@Override
	public boolean isPossible(ForgeDirection dir, int rot, int meta)
	{
		if (dir == ForgeDirection.UP)
		{
			if (rot == 0 || rot == 1) return true;
		}
		return false;
	}
	
	int ret = 4;
	
	@Override
	public void deconstruct(World world, ForgeDirection dir, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.air, 0, 2);
		if (world.getBlock(x, y, z + 1) == Blocks.glowstone)
		{
			world.setBlock(x - 1, y, z, GCBlocks.basicBlock, 4, 2);
		} else world.setBlock(x, y, z + 1, GCBlocks.basicBlock, 4, 2);
		BuildHandler.buildBuildPoint(world, x, y, z, ret);
	}
	
	@Override
	public void Build(World world, ForgeDirection dir, int x, int y, int z)
	{
		if (rot == 0)
		{
			world.setBlock(x, y, z, GCBlocks.solarPanel, 0, 2);
			if (world.getBlock(x, y, z + 1) == Blocks.glowstone)
			{
				((BlockSolar) world.getBlock(x, y, z)).onUseWrench(world, x, y, z, null, 0, x, y, z);
				world.setBlock(x - 1, y, z, GCBlocks.sealableBlock, 15, 2);
			} else world.setBlock(x, y, z + 1, GCBlocks.sealableBlock, 15, 2);
			TileEntitySolar tile = (TileEntitySolar) world.getTileEntity(x, y, z);
			tile.onCreate(new BlockVec3(x, y, z));
		} else
		{
			world.setBlock(x, y, z, GCBlocks.solarPanel, 4, 2);
			if (world.getBlock(x, y, z + 1) == Blocks.glowstone)
			{
				((BlockSolar) world.getBlock(x, y, z)).onUseWrench(world, x, y, z, null, 0, x, y, z);
				world.setBlock(x - 1, y, z, GCBlocks.sealableBlock, 15, 2);
			} else world.setBlock(x, y, z + 1, GCBlocks.sealableBlock, 15, 2);
			TileEntitySolar tile = (TileEntitySolar) world.getTileEntity(x, y, z);
			tile.onCreate(new BlockVec3(x, y, z));
		}
	}
	
	@Override
	public boolean Check(World world, ForgeDirection dir, int x, int y, int z, int meta)
	{
		if (meta != 2 && meta != 4 && meta != 5 && meta != 0 && meta != -1)
		{
			return false;
		}
		if (dir == ForgeDirection.UP)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void ClearWay(World world, ForgeDirection dir, int x, int y, int z)
	{
		
	}
	
	@Override
	public boolean isHidden()
	{
		return hidden;
	}
	
	@Override
	public String getName()
	{
		return StatCollector.translateToLocal("builder.solarpanel.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "solarpanel";
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		if (this.rot == 0)
		{
			items.add(new OreDictItemStack(new ItemStack(GCBlocks.solarPanel, 1, 0)));
			items.add(new OreDictItemStack(new ItemStack(GCBlocks.sealableBlock, 1, 14)));
		} else
		{
			items.add(new OreDictItemStack(new ItemStack(GCBlocks.solarPanel, 1, 4)));
			items.add(new OreDictItemStack(new ItemStack(GCBlocks.sealableBlock, 1, 15)));
		}
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.specialFunc = StatCollector.translateToLocal("builder.side_info.funcs.solar.name");
		data.name = StatCollector.translateToLocal("builder.solarpanel.rot" + rot) + StatCollector.translateToLocal("builder.solarpanel.name").toLowerCase();
		return data;
	}
	
}
