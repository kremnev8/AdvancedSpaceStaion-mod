package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.glider.src.strucures.DeconstructHandler;
import net.glider.src.strucures.Structure;
import net.glider.src.utils.ChatUtils;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.LocalizedChatComponent;
import net.glider.src.utils.LocalizedString;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class DeconstructPacket implements IMessage {
	private List<Structure> objects = new ArrayList<Structure>();
	
	private int[] pos;
	
	public DeconstructPacket()
	{
	}
	
	public DeconstructPacket(List<Structure> objs, int[] pos)
	{
		objects = objs;
		this.pos = pos;
	}
	
	public DeconstructPacket(Structure obj, int[] pos)
	{
		objects.add(obj);
		this.pos = pos;
	}
	
	private int FindDir(ForgeDirection dir)
	{
		for (int i = 0; i < 6; i++)
		{
			if (dir == ForgeDirection.getOrientation(i))
			{
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		pos = tag.getIntArray("INF_POS");
		if (tag.getBoolean("OBJ"))
		{
			for (int i = 0; i < tag.getInteger("N_O"); i++)
			{
				Structure str = Structure.FindStructure(tag.getString("O" + i + "_OBJ"));
				int[] t1 = tag.getIntArray("O" + i + "_POS");
				int t2 = tag.getInteger("O" + i + "_ROT");
				ForgeDirection t3 = ForgeDirection.getOrientation(tag.getInteger("O" + i + "_DIR"));
				str.Configure(t1, t2, t3);
				objects.add(str);
			}
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		
		if (objects != null && objects.size() > 0)
		{
			tag.setInteger("N_O", objects.size());
			tag.setBoolean("OBJ", true);
			
			for (int i = 0; i < objects.size(); i++)
			{
				tag.setInteger("O" + i + "_DIR", objects.get(i).placementDir.ordinal());
				tag.setInteger("O" + i + "_ROT", objects.get(i).placementRotation);
				tag.setIntArray("O" + i + "_POS", objects.get(i).placementPos);
				tag.setString("O" + i + "_OBJ", objects.get(i).getUnlocalizedName());
			}
		} else
		{
			tag.setBoolean("OBJ", false);
		}
		tag.setIntArray("INF_POS", pos);
		
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<DeconstructPacket, IMessage> {
		@Override
		public IMessage onMessage(DeconstructPacket pkt, MessageContext ctx)
		{
			
			if (pkt.objects != null && pkt.objects.size() > 0)
			{
				GLoger.logInfo("Deconstruct Packet Sucsessfuly recived!");
				EntityPlayerMP player = ctx.getServerHandler().playerEntity;
				if (player == null) return null;
				World world = player.worldObj;
				
				int deconstruct = DeconstructHandler.HandleDeconstruct(world, pkt.objects, player, pkt.pos);
				if (deconstruct == 0)
				{
					ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.deconstruct.failed", EnumChatFormatting.RED)));
				} else if (deconstruct == 1)
				{
					ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.deconstruct.partfailed", EnumChatFormatting.YELLOW)));
				} else
				{
					ChatUtils
							.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.deconstruct.successfully", EnumChatFormatting.GREEN))
									.appendSibling(new ChatComponentText("!")));
				}
				// player.addChatMessage(ChatUtils.modifyColor(new
				// ChatComponentText(StatCollector.translateToLocal("builder.deconstruct.successfully")+"!"),EnumChatFormatting.GREEN));
				// System.out.println("Building on server was failed!");
				
			} else
			{
				GLoger.logWarn("An error on handling deconstruct packet. report this to dev!");
				GLoger.logWarn("info: Structures List null or empty");
			}
			
			return null;
		}
		
	}
}