
package net.glider.src.items;

import java.util.List;

import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.utils.IDescrObject;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemContainerBlockMod extends ItemBlock implements IDescrObject {
	
	private final BlockContainerMod blockMod;
	
	public ItemContainerBlockMod(Block block)
	{
		super(block);
		blockMod = (BlockContainerMod) block;
		blockMod.onItemBlockCreation(this);
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{}
	
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
