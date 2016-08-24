package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.glider.src.dimensions.DockingPortSaveData;
import net.glider.src.network.PacketHandler;
import net.glider.src.tiles.TileEntityGravitySource;
import net.glider.src.utils.GLoger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class GetWorldGravityDataPacket implements IMessage
{
	
	
	public GetWorldGravityDataPacket() {}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
	}
	
	

	public static class Handler implements IMessageHandler<GetWorldGravityDataPacket, IMessage>
	{
		@Override
		public IMessage onMessage(GetWorldGravityDataPacket pkt, MessageContext ctx)
		{
			
			
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player != null && player.worldObj != null)
				{
					DockingPortSaveData savef = DockingPortSaveData.forWorld(player.worldObj);
					if (savef != null)
					{
					if (savef.GraviySources.size() > 0)
					{
						List<Double> sources = new ArrayList();
						for (int i=0;i < savef.GraviySources.size();i++)
						{
						if (player.worldObj.getTileEntity(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]) != null)
						{
							TileEntity te = player.worldObj.getTileEntity(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]);
							if (te instanceof TileEntityGravitySource)
							{
								sources.add(((TileEntityGravitySource)te).gravityAddition);
							}
						}
						}
						PacketHandler.sendTo(new ClientGravityDataRecivePacket(sources), (EntityPlayerMP) player);
					}
						
					}
				}
				
			}else
			{
				GLoger.logWarn("An error on handling dismount packet. report this to dev!");
				GLoger.logWarn("info: Player is null");
			}
					
			

			return null;
		}
		
		
	}
}