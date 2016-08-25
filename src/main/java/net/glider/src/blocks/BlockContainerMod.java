
package net.glider.src.blocks;

import java.util.Random;

import net.glider.src.items.ItemContainerBlockMod;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import codechicken.nei.api.API;

public class BlockContainerMod extends BlockContainer {
	
	Random r = new Random();
	private String name;
	
	public static BlockContainerMod DockingPoint;
	public static BlockContainerMod BlockInfo;
	public static BlockContainerMod BlockRemoveInfo;
	public static BlockContainerMod BlockArticialGsource;
	public static BlockContainerMod BlockArmorStand;
	public static BlockFake BlockFake;
	
	public static void init()
	{
		DockingPoint = new BlockDockingPoint("dockingPoint");
		BlockInfo = new BlockInfo("infoBlock");
		BlockRemoveInfo = new BlockRemoveInfo("removeInfoBlock");
		BlockArticialGsource = new BlockArtificialGravitySource("artificialGsource");
		API.hideItem(new ItemStack(BlockInfo));
		API.hideItem(new ItemStack(BlockRemoveInfo));
		BlockArmorStand = new BlockArmorStand("armorStand");
		BlockFake = new BlockFake("blockfake");
		API.hideItem(new ItemStack(BlockFake));
	}
	
	public BlockContainerMod(String uln)
	{
		this(uln, Block.soundTypeStone, Material.rock, 0.5F, 10.0F);
	}
	
	public BlockContainerMod(String uln, int i)
	{
		super(Material.rock);
		this.name = uln;
		this.setBlockName(uln);
		this.setStepSound(Block.soundTypeMetal);
		this.setResistance(10.0F);
		this.setHardness(0.5F);
	}
	
	public BlockContainerMod(String uln, Material material)
	{
		this(uln, Block.soundTypeStone, material, 0.5F, 10.0F);
	}
	
	public BlockContainerMod(String uln, Block.SoundType sound, Material material, float har, float res)
	{
		super(material);
		this.name = uln;
		this.setBlockName(uln);
		this.setStepSound(sound);
		this.setResistance(res);
		this.setHardness(har);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		//		this.setBlockTextureName(GliderModInfo.ModTestures + ":" + uln);
		//GameRegistry.registerBlock(this, this.getItemBlockClass(), uln);
	}
	
	public boolean getItem(EntityPlayer ep, int size)
	{
		if (ep.getCurrentEquippedItem().stackSize >= size)
		{
			if (!ep.capabilities.isCreativeMode)
			{
				ep.getCurrentEquippedItem().stackSize = ep.getCurrentEquippedItem().stackSize - size;
			}
			ep.swingItem();
			return true;
		}
		return false;
	}
	
	public void onItemBlockCreation(ItemContainerBlockMod itemContainerBlockMod)
	{}
	
	public Class<? extends ItemContainerBlockMod> getItemBlockClass()
	{
		return ItemContainerBlockMod.class;
	}
	
	@Override
	public String getLocalizedName()
	{
		return StatCollector.translateToLocal(getUnlocalizedName() + ".name");
	}
	
	public String getUnlocalizedName(ItemStack is)
	{
		return getUnlocalizedName();
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return GliderModInfo.ModTestures + ":tile." + name;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return null;
	}
}
