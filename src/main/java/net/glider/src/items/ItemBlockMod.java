
package net.glider.src.items;

import net.glider.src.blocks.BlockMod;
import net.glider.src.utils.IDescrObject;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemBlockMod extends ItemBlock implements IDescrObject {
	
	private final BlockMod blockMod;
	
	public ItemBlockMod(Block block)
	{
		super(block);
		blockMod = (BlockMod) block;
		blockMod.onItemBlockCreation(this);
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return blockMod.getIcon(2, meta);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		return StatCollector.translateToLocal(blockMod.getUnlocalizedName(is));
	}
	
	@Override
	public String getDescription(int meta)
	{
		return blockMod.getDescription(meta);
	}
	
	@Override
	public String getShiftDescription(int meta)
	{
		return blockMod.getShiftDescription(meta);
	}
	
	@Override
	public boolean showDescription(int meta)
	{
		return blockMod.showDescription(meta);
	}
}
