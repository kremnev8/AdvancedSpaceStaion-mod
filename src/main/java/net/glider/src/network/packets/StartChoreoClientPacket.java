package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.entity.choreo.ChoreoScene;
import net.glider.src.entity.choreo.RocketUndockChoreoScene;
import net.glider.src.utils.GLoger;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StartChoreoClientPacket implements IMessage
{
	private EntityRocketFakeTiered ent;
	private int entid;
	private String choreoName;
	
	public StartChoreoClientPacket() {}
	
	public StartChoreoClientPacket(EntityRocketFakeTiered rocket,ChoreoScene choreo)
	{
		ent = rocket;
		choreoName = choreo.GetChoreoName();
	}


	@Override
	public void fromBytes(ByteBuf buf) 
	{
		entid = buf.readInt();
		choreoName = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(ent.getEntityId());
		ByteBufUtils.writeUTF8String(buf, choreoName);
	}
	
	

	public static class Handler implements IMessageHandler<StartChoreoClientPacket, IMessage>
	{
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(StartChoreoClientPacket pkt, MessageContext ctx)
		{
			
		        	List<Entity> Entlist = Minecraft.getMinecraft().thePlayer.worldObj.getLoadedEntityList();
		        	
		        	for (int i=0;i < Entlist.size();i++)
		        	{
		        		Entity ent = Entlist.get(i);
		        		if (ent.getEntityId()==pkt.entid)
		        		{
		        		if (ent instanceof EntityRocketFakeTiered)
		        		{
		        			pkt.ent = (EntityRocketFakeTiered)ent;
		        		}
		        		}
		        	}
		        	
		        	if (pkt.ent != null)
		        	{
		        		RocketUndockChoreoScene scene = new RocketUndockChoreoScene(pkt.ent);
		        		pkt.ent.startChoreoScene(scene);
		        	}else
		        	{
		        		GLoger.logWarn("An error on handling dismount packet. report this to dev!");
						GLoger.logWarn("info: Entity is null");
		        	}

					
			

			return null;
		}
		
		
	}
}