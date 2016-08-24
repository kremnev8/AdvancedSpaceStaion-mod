package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.gui.ContainerHandedFuelLoader;
import net.glider.src.utils.GLoger;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SyncFuelLoaderPacket implements IMessage
{
	private ItemStack stack;
	
	public SyncFuelLoaderPacket() {}
	
	public SyncFuelLoaderPacket(ItemStack is)
	{
		this.stack = is;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		stack = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeItemStack(buf, stack);
	}
	
	

	public static class Handler implements IMessageHandler<SyncFuelLoaderPacket, IMessage>
	{
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(SyncFuelLoaderPacket pkt, MessageContext ctx)
		{
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.thePlayer != null)
			{
				EntityPlayer player = mc.thePlayer;
				if (player.openContainer != null && player.openContainer instanceof ContainerHandedFuelLoader)
				{
					ContainerHandedFuelLoader fuelloader = (ContainerHandedFuelLoader)player.openContainer;
					fuelloader.inventory.ContainerItem = pkt.stack;
					fuelloader.inventory.openInventory();
				}
			}else
			{
				GLoger.logWarn("An error on handling sync Fuel loader packet. report this to dev!");
				GLoger.logWarn("info: Player is null");
			}
					
			

			return null;
		}
		
		
	}
}