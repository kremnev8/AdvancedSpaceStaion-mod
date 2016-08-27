
package net.glider.src.items;

import net.glider.src.GliderCore;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.blocks.BlockMod;
import net.glider.src.gui.GuiHandler;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.OpenGuiOnServerPacket;
import net.glider.src.utils.ChatUtils;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBuilder extends ItemMod {
	
	public ItemBuilder(String uln)
	{
		super(uln);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setShowDescr(true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (world.isRemote)
		{
			MovingObjectPosition mop = getMovingObjectPositionFromPlayer((World) Minecraft.getMinecraft().theWorld, player, false);
			if (mop != null)
			{
				int x = mop.blockX;
				int y = mop.blockY;
				int z = mop.blockZ;
				if (world.getBlock(x, y, z) == BlockMod.BuildpPoint)
				{
					player.motionX = 0;
					player.motionY = 0;
					player.motionZ = 0;
					player.openGui(GliderCore.instance, GuiHandler.BUILDERGUI, world, x, y, z);
					PacketHandler.sendToServer(new OpenGuiOnServerPacket(GuiHandler.BUILDERGUI, x, y, z));
				} else if (world.getBlock(x, y, z) == BlockContainerMod.BlockRemoveInfo)
				{
					player.motionX = 0;
					player.motionY = 0;
					player.motionZ = 0;
					//PacketHandler.sendToServer(new OpenGuiPacket(x, y, z));
					player.openGui(GliderCore.instance, GuiHandler.REMOVERGUI, world, x, y, z);
					PacketHandler.sendToServer(new OpenGuiOnServerPacket(GuiHandler.REMOVERGUI, x, y, z));
				}
			}
		}
		
		return stack;
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3)
	{
		if (!world.isRemote && player.isSneaking())
		{
			if (world.getBlock(x, y, z) != null && world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof IInventory)
			{
				TileEntity te = world.getTileEntity(x, y, z);
				int[] pos = new int[] { x, y, z };
				if (is.hasTagCompound())
				{
					NBTTagCompound tag = is.stackTagCompound;
					boolean delete = false;
					if (tag.hasKey("chests"))
					{
						NBTTagList list = tag.getTagList("chests", 11);
						if (list.tagCount() > 0)
						{
							if (te instanceof TileEntityChest)
							{
								TileEntityChest te2 = (TileEntityChest) te;
								if (te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
								{
									TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg : te2.adjacentChestXPos != null ? te2.adjacentChestXPos : te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
									
									int[] spos = new int[] { ajte.xCoord, ajte.yCoord, ajte.zCoord };
									for (int i2 = 0; i2 < list.tagCount(); i2++)
									{
										int[] strpos = list.func_150306_c(i2);
										if (strpos.length > 2 && strpos[0] == spos[0] && strpos[1] == spos[1] && strpos[2] == spos[2])
										{
											list.removeTag(i2);
											break;
										}
									}
									for (int i = 0; i < list.tagCount(); i++)
									{
										int[] strpos = list.func_150306_c(i);
										if (strpos.length > 2 && strpos[0] == pos[0] && strpos[1] == pos[1] && strpos[2] == pos[2])
										{
											
											list.removeTag(i);
											player.addChatComponentMessage(new ChatComponentText("deleted chest with pos: " + x + " " + y + " " + z));
											delete = true;
											break;
										}
									}
									
								} else
								{
									for (int i = 0; i < list.tagCount(); i++)
									{
										int[] strpos = list.func_150306_c(i);
										if (strpos.length > 2 && strpos[0] == pos[0] && strpos[1] == pos[1] && strpos[2] == pos[2])
										{
											
											list.removeTag(i);
											player.addChatComponentMessage(new ChatComponentText("deleted chest with pos: " + x + " " + y + " " + z));
											delete = true;
											break;
										}
									}
								}
							} else
							{
								for (int i = 0; i < list.tagCount(); i++)
								{
									int[] strpos = list.func_150306_c(i);
									if (strpos.length > 2 && strpos[0] == pos[0] && strpos[1] == pos[1] && strpos[2] == pos[2])
									{
										
										list.removeTag(i);
										player.addChatComponentMessage(new ChatComponentText("deleted chest with pos: " + x + " " + y + " " + z));
										delete = true;
										break;
									}
								}
							}
							if (!delete && list.tagCount() < 4)
							{
								if (te instanceof TileEntityChest)
								{
									TileEntityChest te2 = (TileEntityChest) te;
									if ((te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null) && list.tagCount() < 3)
									{
										TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg : te2.adjacentChestXPos != null ? te2.adjacentChestXPos : te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
										list.appendTag(new NBTTagIntArray(new int[] { te2.xCoord, te2.yCoord, te2.zCoord }));
										list.appendTag(new NBTTagIntArray(new int[] { ajte.xCoord, ajte.yCoord, ajte.zCoord }));
										player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
									} else if (list.tagCount() < 4)
									{
										list.appendTag(new NBTTagIntArray(pos));
										player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
									}
								} else
								{
									list.appendTag(new NBTTagIntArray(pos));
									player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
								}
							} else if (!delete)
							{
								player.addChatComponentMessage(ChatUtils.modifyColor(new ChatComponentText("You're reached max chest marks count!"), EnumChatFormatting.RED));
							}
							tag.setTag("chests", list);
							
						} else
						{
							if (te instanceof TileEntityChest)
							{
								TileEntityChest te2 = (TileEntityChest) te;
								if (te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
								{
									TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg : te2.adjacentChestXPos != null ? te2.adjacentChestXPos : te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
									list.appendTag(new NBTTagIntArray(new int[] { te2.xCoord, te2.yCoord, te2.zCoord }));
									list.appendTag(new NBTTagIntArray(new int[] { ajte.xCoord, ajte.yCoord, ajte.zCoord }));
									player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
								} else
								{
									list.appendTag(new NBTTagIntArray(pos));
									player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
								}
							} else
							{
								list.appendTag(new NBTTagIntArray(pos));
								player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
							}
							tag.setTag("chests", list);
						}
					} else
					{
						NBTTagList list = new NBTTagList();
						
						if (te instanceof TileEntityChest)
						{
							TileEntityChest te2 = (TileEntityChest) te;
							if (te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
							{
								TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg : te2.adjacentChestXPos != null ? te2.adjacentChestXPos : te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
								list.appendTag(new NBTTagIntArray(new int[] { te2.xCoord, te2.yCoord, te2.zCoord }));
								list.appendTag(new NBTTagIntArray(new int[] { ajte.xCoord, ajte.yCoord, ajte.zCoord }));
								player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
							} else
							{
								list.appendTag(new NBTTagIntArray(pos));
								player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
							}
						} else
						{
							list.appendTag(new NBTTagIntArray(pos));
							player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
						}
						tag.setTag("chests", list);
					}
					is.stackTagCompound = tag;
				} else
				{
					is.stackTagCompound = new NBTTagCompound();
					NBTTagList list = new NBTTagList();
					if (te instanceof TileEntityChest)
					{
						TileEntityChest te2 = (TileEntityChest) te;
						if (te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
						{
							TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg : te2.adjacentChestXPos != null ? te2.adjacentChestXPos : te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
							list.appendTag(new NBTTagIntArray(new int[] { te2.xCoord, te2.yCoord, te2.zCoord }));
							list.appendTag(new NBTTagIntArray(new int[] { ajte.xCoord, ajte.yCoord, ajte.zCoord }));
							player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
						} else
						{
							list.appendTag(new NBTTagIntArray(pos));
							player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
						}
					} else
					{
						list.appendTag(new NBTTagIntArray(pos));
						player.addChatComponentMessage(new ChatComponentText("added new chest with pos: " + x + " " + y + " " + z));
					}
					is.stackTagCompound.setTag("chests", list);
				}
			}
		}
		
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister registry)
	{
		itemIcon = registry.registerIcon(GliderModInfo.ModTestures + ":" + "Builder");
	}
	
}
