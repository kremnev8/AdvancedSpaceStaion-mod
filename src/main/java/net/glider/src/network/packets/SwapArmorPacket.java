
package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.UUID;

import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.gui.ContainerArmorStand;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.utils.GLoger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SwapArmorPacket implements IMessage {

	public SwapArmorPacket()
	{}

	@Override
	public void fromBytes(ByteBuf buf)
	{}

	@Override
	public void toBytes(ByteBuf buf)
	{}

	public static class Handler implements IMessageHandler<SwapArmorPacket, IMessage> {
		@Override
		public IMessage onMessage(SwapArmorPacket pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player.openContainer instanceof ContainerArmorStand)
				{
					ContainerArmorStand cont = (ContainerArmorStand) player.openContainer;
					ItemStack[] stand = cont.Standnventory.items.clone();
					ItemStack[] playerS = player.inventory.armorInventory.clone();
					
					if(stand != null)
					{
						player.inventory.setInventorySlotContents(39, stand[0]); 
						player.inventory.setInventorySlotContents(38, stand[1]); 
						player.inventory.setInventorySlotContents(37, stand[2]); 
						player.inventory.setInventorySlotContents(36, stand[3]); 
					}
					if(playerS != null)
					{
						TileEntityArmorStand te = cont.Standnventory;
						te.setInventorySlotContents(3, playerS[0]); 
						te.setInventorySlotContents(2, playerS[1]); 
						te.setInventorySlotContents(1, playerS[2]); 
						te.setInventorySlotContents(0, playerS[3]); 
					}
				}
			}

			return null;
		}

	}
}