
package net.glider.src.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOrbit;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.core.tile.TileEntityParaChest;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.glider.src.ClientProxy;
import net.glider.src.dimensions.DockingPortSaveData;
import net.glider.src.dimensions.SkyProviderOrbitEarth;
import net.glider.src.dimensions.SkyProviderOrbitModif;
import net.glider.src.dimensions.WorldProviderOrbitEarth;
import net.glider.src.dimensions.WorldProviderOrbitModif;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.items.ItemMod;
import net.glider.src.items.ItemSpaceJetpack;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.LaunchRocketPacket;
import net.glider.src.network.packets.SetThirdPersonPacket;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ForgeEvents {
	
	//SERVER  
	
	@SubscribeEvent
	public void cancelSuffucationInRocket(GCCoreOxygenSuffocationEvent.Pre event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.ridingEntity instanceof EntityRocketFakeTiered)
			{
				if (((EntityRocketFakeTiered) player.ridingEntity).canBreath())
				{
					event.setCanceled(true);
				}
			}
		}
	}
	
	//CLIENT
	
	protected static final ResourceLocation Fuel = new ResourceLocation(GliderModInfo.ModTestures, "textures/FuelTank.png");
	
	public void drawTexturedModalRect(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_)
	{
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (p_73729_1_ + 0), (double) (p_73729_2_ + p_73729_6_), (double) 1, (double) ((float) (p_73729_3_ + 0) * f),
				(double) ((float) (p_73729_4_ + p_73729_6_) * f1));
		tessellator.addVertexWithUV((double) (p_73729_1_ + p_73729_5_), (double) (p_73729_2_ + p_73729_6_), (double) 1, (double) ((float) (p_73729_3_ + p_73729_5_) * f),
				(double) ((float) (p_73729_4_ + p_73729_6_) * f1));
		tessellator.addVertexWithUV((double) (p_73729_1_ + p_73729_5_), (double) (p_73729_2_ + 0), (double) 1, (double) ((float) (p_73729_3_ + p_73729_5_) * f),
				(double) ((float) (p_73729_4_ + 0) * f1));
		tessellator.addVertexWithUV((double) (p_73729_1_ + 0), (double) (p_73729_2_ + 0), (double) 1, (double) ((float) (p_73729_3_ + 0) * f),
				(double) ((float) (p_73729_4_ + 0) * f1));
		tessellator.draw();
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		int xPos = event.resolution.getScaledWidth() - 10 - 19;
		int yPos = 63;
		
		if (event.type != ElementType.EXPERIENCE)
		{
			return;
		}
		if (!OxygenUtil.shouldDisplayTankGui(mc.currentScreen))
		{
			return;
		}
		if (mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(Fuel);
			
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			
			drawTexturedModalRect(xPos, yPos, 0, 0, 19, 47);
			
			ItemSpaceJetpack jetpack = (ItemSpaceJetpack) mc.thePlayer.getCurrentArmor(2).getItem();
			
			int fuelLevel;
			
			if (jetpack.RCSFuel.getCapacity() <= 0)
			{
				fuelLevel = 0;
			} else
			{
				fuelLevel = jetpack.RCSFuel.getFluidAmount() * 44 / jetpack.RCSFuel.getCapacity() / ConfigManagerCore.rocketFuelFactor;
			}
			drawTexturedModalRect(xPos + 1, yPos + 1 + 44 - fuelLevel, 19, 45 - fuelLevel, 44, fuelLevel);
			
		}
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayerPre(RenderPlayerEvent.Pre event)
	{
		GL11.glPushMatrix();
		
		final EntityPlayer player = event.entityPlayer;
		
		if (player.ridingEntity instanceof EntityRocketFakeTiered && player == Minecraft.getMinecraft().thePlayer)
		{
			EntityRocketFakeTiered entity = (EntityRocketFakeTiered) player.ridingEntity;
			GL11.glTranslatef(0, -entity.getRotateOffset() - 1.6200000047683716F, 0);
			float anglePitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * event.partialRenderTick;
			float angleYaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * event.partialRenderTick;
			GL11.glRotatef(-angleYaw, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(anglePitch, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0, entity.getRotateOffset() + 1.6200000047683716F, 0);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayerPost(RenderPlayerEvent.Post event)
	{
		GL11.glPopMatrix();
	}
	
}
