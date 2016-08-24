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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ContainerRemover extends Container {

	public InventoryPlayer inventory;
	public World world;
	TileEntityRemoveInfo te;

	public ContainerRemover(InventoryPlayer inv, TileEntityRemoveInfo tile)
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
					PacketHandler.sendTo(new OpenGuiPacket(Object, AddObjects, ChildObjects), (EntityPlayerMP) player);
				} else
				{
					PacketHandler.sendTo(new OpenGuiPacket(te.infoBlocks.get(0)), (EntityPlayerMP) player);
				}

			} else
			{
				PacketHandler.sendTo(new CloseScreenPacket(), (EntityPlayerMP) player);
				player.addChatMessage(ChatUtils.modifyColor(new ChatComponentText(StatCollector.translateToLocal("remover.no_linked_tile.name")), EnumChatFormatting.RED));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

	//anti-crash lines
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
    public void onContainerClosed(EntityPlayer p_75134_1_) {
    	super.onContainerClosed(p_75134_1_);
    	
    }

}
