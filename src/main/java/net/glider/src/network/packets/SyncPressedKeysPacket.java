package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.items.ItemMod;
import net.glider.src.items.ItemSpaceJetpack;
import net.glider.src.utils.GLoger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SyncPressedKeysPacket implements IMessage
{
	
	private boolean active;
	
	public SyncPressedKeysPacket(){}
	
	public SyncPressedKeysPacket(boolean act) 
	{
		this.active = act;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		active = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(active);
	}
	
	

	public static class Handler implements IMessageHandler<SyncPressedKeysPacket, IMessage> {
		@Override
		public IMessage onMessage(SyncPressedKeysPacket pkt, MessageContext ctx)
		{

			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;

				if (player != null && player.worldObj != null)
				{
					if (player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack)
					{
						ItemSpaceJetpack jetpack = (ItemSpaceJetpack) player.getCurrentArmor(2).getItem();
						ItemStack is = player.getCurrentArmor(2);
						
						jetpack.activated = pkt.active;
						if (is.hasTagCompound())
						{
							jetpack.writeToNBT(is.stackTagCompound);
						} else
						{
							is.stackTagCompound = new NBTTagCompound();
							jetpack.writeToNBT(is.stackTagCompound);
						}
					}
				}
				
			}else
			{
				GLoger.logWarn("An error on handling Key sync packet. report this to dev!");
				GLoger.logWarn("info: Player is null");
			}
					
			

			return null;
		}
		
		
	}
}