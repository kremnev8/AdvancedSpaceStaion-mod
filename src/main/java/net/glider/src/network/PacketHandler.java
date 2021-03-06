package net.glider.src.network;

import net.glider.src.network.packets.AnimationTellServerPacket;
import net.glider.src.network.packets.ArmorStandItemSyncPacket;
import net.glider.src.network.packets.BuildPacket;
import net.glider.src.network.packets.BuildPointSyncPacket;
import net.glider.src.network.packets.ClientGravityDataRecivePacket;
import net.glider.src.network.packets.CloseScreenPacket;
import net.glider.src.network.packets.DeconstructPacket;
import net.glider.src.network.packets.DismountPacket;
import net.glider.src.network.packets.DockItemSyncPacket;
import net.glider.src.network.packets.GetWorldGravityDataPacket;
import net.glider.src.network.packets.GravityChangePacket;
import net.glider.src.network.packets.InvScalePacket;
import net.glider.src.network.packets.JetpackUseFuelPacket;
import net.glider.src.network.packets.LaunchRocketPacket;
import net.glider.src.network.packets.MountPacket;
import net.glider.src.network.packets.OpenBuilderGuiPacket;
import net.glider.src.network.packets.OpenGuiOnServerPacket;
import net.glider.src.network.packets.OpenGuiPacket;
import net.glider.src.network.packets.OpenRocketFuelGuiPacket;
import net.glider.src.network.packets.OtherPlayerAnimationPacket;
import net.glider.src.network.packets.PlayerChatMessagePacket;
import net.glider.src.network.packets.RocketControlsPacket;
import net.glider.src.network.packets.SendUUIDPacket;
import net.glider.src.network.packets.SetThirdPersonPacket;
import net.glider.src.network.packets.StartChoreoClientPacket;
import net.glider.src.network.packets.SwapArmorPacket;
import net.glider.src.network.packets.SyncPlayerFallPacket;
import net.glider.src.network.packets.SyncPressedKeysPacket;
import net.glider.src.network.packets.SyncRocketTierPacket;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraftforge.common.util.FakePlayer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public final class PacketHandler {
	private static final int MAX_PKT_SIZE = 256;
	private static final SimpleNetworkWrapper HANDLER = NetworkRegistry.INSTANCE.newSimpleChannel(GliderModInfo.ChannelName);
	
	public static void register()
	{
		HANDLER.registerMessage(BuildPacket.Handler.class, BuildPacket.class, 0, Side.SERVER);
		HANDLER.registerMessage(OpenGuiPacket.Handler.class, OpenGuiPacket.class, 1, Side.CLIENT);
		HANDLER.registerMessage(DeconstructPacket.Handler.class, DeconstructPacket.class, 2, Side.SERVER);
		HANDLER.registerMessage(SetThirdPersonPacket.Handler.class, SetThirdPersonPacket.class, 3, Side.CLIENT);
		HANDLER.registerMessage(InvScalePacket.Handler.class, InvScalePacket.class, 4, Side.CLIENT);
		HANDLER.registerMessage(DockItemSyncPacket.Handler.class, DockItemSyncPacket.class, 5, Side.CLIENT);
		HANDLER.registerMessage(DismountPacket.Handler.class, DismountPacket.class, 6, Side.SERVER);
		HANDLER.registerMessage(SendUUIDPacket.Handler.class, SendUUIDPacket.class, 7, Side.CLIENT);
		HANDLER.registerMessage(MountPacket.Handler.class, MountPacket.class, 8, Side.SERVER);
		HANDLER.registerMessage(LaunchRocketPacket.Handler.class, LaunchRocketPacket.class, 9, Side.SERVER);
		HANDLER.registerMessage(SyncRocketTierPacket.Handler.class, SyncRocketTierPacket.class, 10, Side.CLIENT);
		HANDLER.registerMessage(RocketControlsPacket.Handler.class, RocketControlsPacket.class, 11, Side.CLIENT);
		HANDLER.registerMessage(OpenRocketFuelGuiPacket.Handler.class, OpenRocketFuelGuiPacket.class, 12, Side.SERVER);
		HANDLER.registerMessage(StartChoreoClientPacket.Handler.class, StartChoreoClientPacket.class, 13, Side.CLIENT);
		
		HANDLER.registerMessage(GetWorldGravityDataPacket.Handler.class, GetWorldGravityDataPacket.class, 14, Side.SERVER);
		HANDLER.registerMessage(ClientGravityDataRecivePacket.Handler.class, ClientGravityDataRecivePacket.class, 15, Side.CLIENT);
		
		HANDLER.registerMessage(SyncPlayerFallPacket.Handler.class, SyncPlayerFallPacket.class, 16, Side.SERVER);
		HANDLER.registerMessage(SyncPressedKeysPacket.Handler.class, SyncPressedKeysPacket.class, 17, Side.SERVER);
		
		HANDLER.registerMessage(JetpackUseFuelPacket.Handler.class, JetpackUseFuelPacket.class, 18, Side.SERVER);
		
		HANDLER.registerMessage(OpenBuilderGuiPacket.Handler.class, OpenBuilderGuiPacket.class, 19, Side.CLIENT);
		
		HANDLER.registerMessage(OpenGuiOnServerPacket.Handler.class, OpenGuiOnServerPacket.class, 20, Side.SERVER);
		
		HANDLER.registerMessage(CloseScreenPacket.Handler.class, CloseScreenPacket.class, 21, Side.CLIENT);
		HANDLER.registerMessage(BuildPointSyncPacket.Handler.class, BuildPointSyncPacket.class, 22, Side.CLIENT);
		
		HANDLER.registerMessage(SwapArmorPacket.Handler.class, SwapArmorPacket.class, 23, Side.SERVER);
		
		HANDLER.registerMessage(AnimationTellServerPacket.Handler.class, AnimationTellServerPacket.class, 24, Side.SERVER);
		HANDLER.registerMessage(OtherPlayerAnimationPacket.Handler.class, OtherPlayerAnimationPacket.class, 25, Side.CLIENT);
		
		HANDLER.registerMessage(ArmorStandItemSyncPacket.Handler.class, ArmorStandItemSyncPacket.class, 26, Side.CLIENT);
		HANDLER.registerMessage(PlayerChatMessagePacket.Handler.class, PlayerChatMessagePacket.class, 27, Side.CLIENT);
		
		HANDLER.registerMessage(GravityChangePacket.Handler.class, GravityChangePacket.class, 28, Side.SERVER);
	}
	
	public static Packet getMCPacket(IMessage message)
	{
		return HANDLER.getPacketFrom(message);
	}
	
	/**
	 * Sends a packet to the server.<br>
	 * Must be called Client side.
	 */
	public static void sendToServer(IMessage msg)
	{
		HANDLER.sendToServer(msg);
	}
	
	/**
	 * Sends a packet to all the clients.<br>
	 * Must be called Server side.
	 */
	public static void sendToAll(IMessage msg)
	{
		HANDLER.sendToAll(msg);
	}
	
	/**
	 * Send a packet to all players around a specific point.<br>
	 * Must be called Server side.
	 */
	public static void sendToAllAround(IMessage msg, TargetPoint point)
	{
		HANDLER.sendToAllAround(msg, point);
	}
	
	/**
	 * Send a packet to a specific player.<br>
	 * Must be called Server side.
	 */
	public static void sendTo(IMessage msg, EntityPlayerMP player)
	{
		if (!(player instanceof FakePlayer))
		{
			HANDLER.sendTo(msg, player);
		}
	}
	
	/**
	 * Send a packet to all the players in the specified dimension.<br>
	 * Must be called Server side.
	 */
	public static void sendToDimension(IMessage msg, int dimension)
	{
		HANDLER.sendToDimension(msg, dimension);
	}
}