package net.glider.src.items;

import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.blocks.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemBlockMod extends ItemBlock{
	
	private final BlockMod blockMod;
	
	public ItemBlockMod(Block block) {
		super(block);
		blockMod = (BlockMod)block;
		blockMod.onItemBlockCreation(this);
	}
	
	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	@Override
	public IIcon getIconFromDamage(int meta){
		return blockMod.getIcon(2, meta);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is){
		return StatCollector.translateToLocal(blockMod.getUnlocalizedName(is));
	}
}
