package net.glider.src.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.blocks.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemContainerBlockMod extends ItemBlock{
	
	private final BlockContainerMod blockMod;
	
	public ItemContainerBlockMod(Block block) {
		super(block);
		blockMod = (BlockContainerMod)block;
		blockMod.onItemBlockCreation(this);
	}
	
	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) 
	{
		if (stack.hasTagCompound()&& stack.stackTagCompound.hasKey("sa"))
		{
			list.add("contains "+stack.stackTagCompound.getInteger("sa")+" solarpanels");
		}
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
