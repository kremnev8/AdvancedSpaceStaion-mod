package net.glider.src.gui;

import java.util.ArrayList;
import java.util.List;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.CloseScreenPacket;
import net.glider.src.network.packets.OpenGuiPacket;
import net.glider.src.strucures.Structure;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.ChatUtils;
import net.glider.src.utils.ForgeDirectionUtils;
import net.glider.src.utils.LocalizedChatComponent;
import net.glider.src.utils.LocalizedString;
import net.glider.src.utils.MatrixHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ContainerModificator extends Container {
	
	public InventoryPlayer inventory;
	public World world;
	TileEntityRemoveInfo te;
	
	public ContainerModificator(InventoryPlayer inv, TileEntityRemoveInfo tile)
	{
		this.inventory = inv;
		world = inv.player.worldObj;
		if (!world.isRemote)
		{
			te = tile;
			EntityPlayer player = world.getPlayerEntityByName(inv.player.getDisplayName());
			if (te.infoBlocks != null && te.infoBlocks.size() > 0 && te.infoBlocks.get(0) != null)
			{
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
						for (int i = 0; i < ChildObjects.size(); i++)
						{
							Structure str = ChildObjects.get(i);
							if (str.placementDir != ForgeDirection.UNKNOWN)
							{
								int[] Ipos = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
								if (Ipos != null && Ipos.length > 0)
								{
									ForgeDirectionUtils.IncreaseByDir(str.placementDir, Ipos, 9);
									TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]);
									if (Ite.Object.connections != null && Ite.Object.connections.size() > 0)
									{
										ChildObjects.get(i).connections = Ite.Object.connections;
									}
								}
							}
						}
					}
					
					PacketHandler.sendTo(new OpenGuiPacket(Object, AddObjects, ChildObjects, false), (EntityPlayerMP) player);
				} else
				{
					if (te.infoBlocks.get(0).ChildObjects != null && te.infoBlocks.get(0).ChildObjects.size() > 0)
					{
						for (int i = 0; i < te.infoBlocks.get(0).ChildObjects.size(); i++)
						{
							Structure str = te.infoBlocks.get(0).ChildObjects.get(i);
							if (str.placementDir != ForgeDirection.UNKNOWN)
							{
								int[] Ipos = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
								if (Ipos != null && Ipos.length > 0)
								{
									ForgeDirectionUtils.IncreaseByDir(str.placementDir, Ipos, 9);
									TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]);
									if (Ite != null && Ite.Object.connections != null && Ite.Object.connections.size() > 0)
									{
										te.infoBlocks.get(0).ChildObjects.get(i).connections = Ite.Object.connections;
									}
								}
							}
						}
					}
					PacketHandler.sendTo(new OpenGuiPacket(te.infoBlocks.get(0), false), (EntityPlayerMP) player);
				}
				
			} else
			{
				PacketHandler.sendTo(new CloseScreenPacket(), (EntityPlayerMP) player);
				ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("remover.no_linked_tile.name", EnumChatFormatting.RED)));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}
	
	// anti-crash lines
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotRaw)
	{
		return null;
	}
	
	public void addCraftingToCrafters(ICrafting p_75132_1_)
	{
		
	}
	
	public Slot getSlot(int p_75139_1_)
	{
		return null;
	}
	
	public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
	{
		return null;
	}
	
	public void putStacksInSlots(ItemStack[] p_75131_1_)
	{
	}
	
	public void putStackInSlot(int p_75141_1_, ItemStack p_75141_2_)
	{
	}
	
	protected boolean mergeItemStack(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_)
	{
		return false;
	}
	
	public boolean canDragIntoSlot(Slot p_94531_1_)
	{
		return false;
	}
	
	public List getInventory()
	{
		return null;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
	}
	
	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_)
	{
		super.onContainerClosed(p_75134_1_);
		
	}
	
}
