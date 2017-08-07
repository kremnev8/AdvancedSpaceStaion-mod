package net.glider.src.network.packets;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.glider.src.items.ItemMod;
import net.glider.src.strucures.BuildHandler;
import net.glider.src.strucures.DeconstructHandler;
import net.glider.src.strucures.Structure;
import net.glider.src.strucures.StructureRotatable;
import net.glider.src.utils.ChatUtils;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.LocalizedChatComponent;
import net.glider.src.utils.LocalizedString;
import net.glider.src.utils.OreDictItemStack;
import net.glider.src.utils.StructureLocalizedString;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class BuildPacket implements IMessage {
	private ForgeDirection dir;
	private String Fname;
	private int x;
	private int y;
	private int z;
	private int rot;
	private NBTTagList list = new NBTTagList();
	
	public BuildPacket()
	{
	}
	
	public BuildPacket(ForgeDirection dir, String name, int x, int y, int z)
	{
		this.dir = dir;
		this.Fname = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.rot = -1;
	}
	
	public BuildPacket(ForgeDirection dir, String name, int x, int y, int z, int rot, ItemStack stack)
	{
		this.dir = dir;
		this.Fname = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.rot = rot;
		
		if (stack.getItem() == ItemMod.Builder)
		{
			if (stack.hasTagCompound())
			{
				NBTTagCompound tag = stack.stackTagCompound;
				if (tag.hasKey("chests"))
				{
					list = tag.getTagList("chests", 11);
				}
			}
		}
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
		dir = ForgeDirection.getOrientation(ByteBufUtils.readVarInt(buf, 5));
		Fname = ByteBufUtils.readUTF8String(buf);
		x = ByteBufUtils.readVarInt(buf, 5);
		y = ByteBufUtils.readVarInt(buf, 5);
		z = ByteBufUtils.readVarInt(buf, 5);
		rot = ByteBufUtils.readVarInt(buf, 5);
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		list = tag.getTagList("chests", 11);
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeVarInt(buf, FindDir(dir), 5);
		ByteBufUtils.writeUTF8String(buf, Fname);
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
		ByteBufUtils.writeVarInt(buf, rot, 5);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("chests", list);
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<BuildPacket, IMessage> {
		@Override
		public IMessage onMessage(BuildPacket pkt, MessageContext ctx)
		{
			
			if (pkt.dir.name() != ForgeDirection.UNKNOWN.name() && pkt.Fname != "")
			{
				// GLoger.logInfo("Build Packet Sucsessfuly recived!");
				EntityPlayerMP player = ctx.getServerHandler().playerEntity;
				if (player == null) return null;
				World world = player.worldObj;
				boolean items = BuildHandler.CheckItems(world, pkt.Fname, pkt.list, player, pkt.rot);
				if (!items) ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.failed.noitems", EnumChatFormatting.RED)));
				else
				{
					boolean build = BuildHandler.HandleBuild(world, pkt.dir, pkt.Fname, pkt.x, pkt.y, pkt.z, pkt.rot, player);
					if (!build) ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.failed", EnumChatFormatting.RED)));
					else ChatUtils.SendChatMessageOnClient(player,
							new LocalizedChatComponent(new LocalizedString("builder.successfully", EnumChatFormatting.GREEN)).appendSibling(new ChatComponentText(" "))
									.appendSibling(new LocalizedChatComponent(new StructureLocalizedString(Structure.FindStructure(pkt.Fname), EnumChatFormatting.GREEN))
											.appendSibling(new ChatComponentText("!"))));
					
					if (!build)
					{
						if (!player.capabilities.isCreativeMode)
						{
							Structure str = Structure.FindStructure(pkt.Fname);
							if (str instanceof StructureRotatable)
							{
								((StructureRotatable) str).setRotation(pkt.rot);
							}
							List<OreDictItemStack> I = str.getRequiredItems();
							List<ItemStack> afterI = new ArrayList();
							afterI.addAll(DeconstructHandler.modificateRetItems(I));
							
							for (int k = 0; k < afterI.size(); k++)
							{
								ItemStack curr = afterI.get(k);
								EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
								item.delayBeforeCanPickup = 0;
								world.spawnEntityInWorld(item);
							}
							ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.failed.itemsreturn", EnumChatFormatting.GRAY)));
						}
					}
				}
			} else
			{
				GLoger.logWarn("An error on handling build packet. report this to dev!");
				GLoger.logWarn("info", pkt.dir.name(), pkt.Fname);
			}
			
			return null;
		}
		
	}
}