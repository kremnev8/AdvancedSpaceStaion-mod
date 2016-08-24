package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import net.glider.src.items.ItemMod;
import net.glider.src.items.ItemSpaceJetpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class JetpackUseFuelPacket implements IMessage {
	public JetpackUseFuelPacket()
	{
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
	}

	public static class Handler implements IMessageHandler<JetpackUseFuelPacket, IMessage> {
		@Override
		public IMessage onMessage(JetpackUseFuelPacket pkt, MessageContext ctx)
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
						
						jetpack.RCSFuel.drain(10, true);
						if (is.hasTagCompound())
						{
							jetpack.writeToNBT(is.stackTagCompound);
						}else
						{
							is.stackTagCompound = new NBTTagCompound();
							jetpack.writeToNBT(is.stackTagCompound);
						}
					}
				}
			}

			return null;
		}

	}
}