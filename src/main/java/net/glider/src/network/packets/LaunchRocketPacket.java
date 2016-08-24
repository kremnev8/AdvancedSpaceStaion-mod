package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.items.ItemParaChute;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.entity.choreo.RocketUndockChoreoScene;
import net.glider.src.utils.GLoger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class LaunchRocketPacket implements IMessage
{
	
	
	public LaunchRocketPacket() {}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
	}
	
	

	public static class Handler implements IMessageHandler<LaunchRocketPacket, IMessage> {
		@Override
		public IMessage onMessage(LaunchRocketPacket pkt, MessageContext ctx)
		{

			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;

				if (!player.isDead && player.ridingEntity != null && !player.ridingEntity.isDead && player.ridingEntity instanceof EntityRocketFakeTiered)
				{
					EntityRocketFakeTiered ship = (EntityRocketFakeTiered) player.ridingEntity;
					GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
					if (ship.hasValidFuel())
					{
						ItemStack stack2 = stats.extendedInventory.getStackInSlot(4);
						boolean havePad = false;
						if (ship.dockport != null)
						{
							ItemStack pad = ship.dockport.getStackInSlot(ship.dockport.getSizeInventory() - 3);
							if (pad != null && pad.getItem() == new ItemStack(GCBlocks.landingPad).getItem())
							{
								if (pad.stackSize == 9) havePad = true;
							}
						}

						if ((stack2 != null && stack2.getItem() instanceof ItemParaChute && havePad) || stats.launchAttempts > 1 || stats.launchAttempts > 0 && havePad || stats.launchAttempts > 0 && stack2 != null && stack2.getItem() instanceof ItemParaChute)
						{
							ship.startChoreoScene(new RocketUndockChoreoScene(ship));
							stats.launchAttempts = 0;
						} else if (stats.chatCooldown == 0 && !(stack2 != null && stack2.getItem() instanceof ItemParaChute))
						{//StatCollector.translateToLocal(key);
							player.addChatMessage(new ChatComponentText(GCCoreUtil.translate("gui.rocket.warning.noparachute")));
							stats.chatCooldown = 50;
							stats.launchAttempts++;
						} else if (stats.chatCooldown == 0 && !havePad)
						{
							player.addChatMessage(new ChatComponentText("I don't have a landing pad! If I press launch again, there's no going back!"));
							stats.chatCooldown = 50;
							stats.launchAttempts++;
						}
					} else if (stats.chatCooldown == 0)
					{
						player.addChatMessage(new ChatComponentText(GCCoreUtil.translate("gui.rocket.warning.nofuel")));
						stats.chatCooldown = 250;
					}

				}

			} else
			{
				GLoger.logWarn("An error on handling rocket launch packet. report this to dev!");
				GLoger.logWarn("info: Player is null");
			}

			return null;
		}

	}
}