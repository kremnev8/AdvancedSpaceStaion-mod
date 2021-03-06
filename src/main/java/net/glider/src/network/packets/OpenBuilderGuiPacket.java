
package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.glider.src.gui.GuiBuilderSide;
import net.glider.src.utils.GLoger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class OpenBuilderGuiPacket implements IMessage {
	private List<ItemStack> items = new ArrayList();
	
	public OpenBuilderGuiPacket()
	{
	}
	
	public OpenBuilderGuiPacket(List<ItemStack> items)
	{
		this.items = items;
	}
	
	public static NBTTagCompound writeToNBT(NBTTagCompound tag, ItemStack is)
	{
		tag.setShort("id", (short) Item.getIdFromItem(is.getItem()));
		tag.setInteger("Count", is.stackSize);
		tag.setInteger("Damage", is.getItemDamage());
		
		if (is.stackTagCompound != null)
		{
			tag.setTag("tag", is.stackTagCompound);
		}
		
		return tag;
	}
	
	/**
	 * Read the stack fields from a NBT object.
	 * 
	 * @return
	 */
	public static ItemStack readFromNBT(NBTTagCompound tag)
	{
		Item it = Item.getItemById(tag.getShort("id"));
		int stacks = tag.getInteger("Count");
		int damage = tag.getInteger("Damage");
		
		if (damage < 0)
		{
			damage = 0;
		}
		ItemStack is = new ItemStack(it, stacks, damage);
		
		if (tag.hasKey("tag", 10))
		{
			is.stackTagCompound = tag.getCompoundTag("tag");
		}
		return is;
	}
	
	public static ItemStack loadItemStackFromNBT(NBTTagCompound tag)
	{
		ItemStack itemstack = readFromNBT(tag);
		return itemstack.getItem() != null ? itemstack : null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		
		final NBTTagList list = tag.getTagList("Items", 10);
		items = new ArrayList();
		
		for (int var3 = 0; var3 < list.tagCount(); ++var3)
		{
			final NBTTagCompound var4 = list.getCompoundTagAt(var3);
			items.add(loadItemStackFromNBT(var4));
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		
		final NBTTagList list = new NBTTagList();
		int length = items.size();
		
		for (int i = 0; i < length; ++i)
		{
			if (items.get(i) != null)
			{
				final NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) i);
				writeToNBT(var4, items.get(i));
				list.appendTag(var4);
			}
		}
		
		tag.setTag("Items", list);
		
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<OpenBuilderGuiPacket, IMessage> {
		@Override
		public IMessage onMessage(OpenBuilderGuiPacket pkt, MessageContext ctx)
		{
			
			if (pkt.items != null)
			{
				GuiBuilderSide.foundItems = pkt.items;
				GuiBuilderSide.dataRecived = true;
				
			} else
			{
				GLoger.logWarn("An error on handling open GUI packet. report this to dev!");
				GLoger.logWarn("info: Items list is null");
			}
			
			return null;
		}
		
	}
}