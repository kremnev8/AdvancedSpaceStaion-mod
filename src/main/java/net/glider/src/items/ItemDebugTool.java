package net.glider.src.items;

import java.util.ArrayList;
import java.util.List;

import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.strucures.Structure;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDebugTool extends ItemMod {

	public IIcon BuilderIconRed;
	public IIcon BuilderIconGreen;

	public ItemDebugTool(String uln)
	{
		super(uln);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	boolean test;

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3)
	{
		Block b = world.getBlock(x, y, z);

		if (world.getBlock(x, y, z) == BlockContainerMod.BlockRemoveInfo)
		{
			if (!world.isRemote)
			{
				player.addChatMessage(new ChatComponentText("Meta:" + world.getBlockMetadata(x, y, z)));
				TileEntityRemoveInfo te = (TileEntityRemoveInfo) world.getTileEntity(x, y, z);
				if (te != null && te.infoBlocks != null && te.infoBlocks.size() > 0)
				{
					player.addChatMessage(new ChatComponentText("Info Point Pos:"));
					player.addChatMessage(new ChatComponentText(te.infoBlocks.get(0).xCoord + " " + te.infoBlocks.get(0).yCoord + " " + te.infoBlocks.get(0).zCoord));

					TileEntityInfo teI = te.infoBlocks.get(0);

					if (teI.Object != null && teI.Object.placementDir != null)
					{
						player.addChatMessage(new ChatComponentText("OBJ:" + teI.Object.getUnlocalizedName()));
						player.addChatMessage(new ChatComponentText("DIR:" + teI.Object.placementDir.name()));
						player.addChatMessage(new ChatComponentText("ROT:" + teI.Object.placementRotation + ""));
						player.addChatMessage(new ChatComponentText("POS:" + teI.Object.placementPos[0] + " " + teI.Object.placementPos[1] + " " + teI.Object.placementPos[2]));
					}

					if (te.infoBlocks.get(0).Object != null && te.infoBlocks.get(0).Object.getUnlocalizedName() == Structure.BIGHHALL)
					{
						Structure Object = te.infoBlocks.get(0).Object.copy();
						List<Structure> ChildObjects = new ArrayList();
						ChildObjects.addAll(te.infoBlocks.get(0).ChildObjects);
						List<Structure> AddObjects = new ArrayList();
						AddObjects.addAll(te.infoBlocks.get(0).AddObjects);
						if (te.infoBlocks.size() > 1)
						{
							for (int i = 1; i < te.infoBlocks.size(); i++)
							{
								TileEntityInfo te2 = te.infoBlocks.get(i);
								ChildObjects.addAll(te2.ChildObjects);
								AddObjects.addAll(te2.AddObjects);

							}
						}
						if (ChildObjects != null && ChildObjects.size() > 0)
						{
							player.addChatMessage(new ChatComponentText("Child Structures:"));
							for (int i = 0; i < ChildObjects.size(); i++)
								player.addChatMessage(new ChatComponentText(ChildObjects.get(i).getUnlocalizedName() + " " + ChildObjects.get(i).placementRotation));
						}
						if (AddObjects != null && AddObjects.size() > 0)
						{
							player.addChatMessage(new ChatComponentText("Additional Structures:"));
							for (int i = 0; i < AddObjects.size(); i++)
								player.addChatMessage(new ChatComponentText(AddObjects.get(i).getUnlocalizedName() + " " + AddObjects.get(i).placementRotation));
						}
					} else
					{

						if (teI.ChildObjects != null && teI.ChildObjects.size() > 0)
						{
							player.addChatMessage(new ChatComponentText("Child Structures:"));
							for (int i = 0; i < teI.ChildObjects.size(); i++)
								player.addChatMessage(new ChatComponentText(teI.ChildObjects.get(i).getUnlocalizedName() + " " + teI.ChildObjects.get(i).placementRotation));
						}
						if (teI.AddObjects != null && teI.AddObjects.size() > 0)
						{
							player.addChatMessage(new ChatComponentText("Additional Structures:"));
							for (int i = 0; i < teI.AddObjects.size(); i++)
								player.addChatMessage(new ChatComponentText(teI.AddObjects.get(i).getUnlocalizedName() + " " + teI.AddObjects.get(i).placementRotation));
						}
					}

				}
				player.addChatMessage(new ChatComponentText("----"));
				return true;
			}
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister registry)
	{
		itemIcon = registry.registerIcon(GliderModInfo.ModTestures + ":" + "DebugTool");
		BuilderIconRed = registry.registerIcon(GliderModInfo.ModTestures + ":" + "Builder_slot_icon0");
		BuilderIconGreen = registry.registerIcon(GliderModInfo.ModTestures + ":" + "Builder_slot_icon1");
	}

}
