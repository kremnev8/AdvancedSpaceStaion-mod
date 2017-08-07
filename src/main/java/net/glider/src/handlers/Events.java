
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

public class Events {
	
	//SERVER  
	@SubscribeEvent
	public void onCraft(PlayerEvent.ItemCraftedEvent event)
	{
		if (event.crafting.getItem() == ItemMod.schematicjetpack)
		{
			if (event.player != null && !(event.player instanceof FakePlayer) && !event.player.worldObj.isRemote)
			{
				EntityItem item = new EntityItem(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ, new ItemStack(ItemMod.filledIdea));
				item.delayBeforeCanPickup = 0;
				event.player.worldObj.spawnEntityInWorld(item);
			}
		}
	}
	
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
	
	@SubscribeEvent
	public void onPlayerTeleportDim(PlayerEvent.PlayerChangedDimensionEvent event)
	{
		if (MinecraftServer.getServer().worldServerForDimension(event.toDim).provider instanceof WorldProviderOrbitModif)
		{
			EntityPlayer player = event.player;
			if (player != null)
			{
				GCPlayerStats playerStats = GCPlayerStats.get((EntityPlayerMP) player);
				GLoger.logInfo("Player just docked to Space Station");
				GLoger.logInfo("info:");
				
				PacketHandler.sendTo(new SetThirdPersonPacket(0), (EntityPlayerMP) player);
				
				if (player.worldObj != null)
				{
					DockingPortSaveData savef = DockingPortSaveData.forWorld(player.worldObj);
					World world = player.worldObj;
					
					try
					{
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e)
					{
					}
					boolean docked = false;
					if (savef.DockingPorts.size() > 0)
					{
						GLoger.logInfo("Dim have a docking port info");
						for (int i = 0; i < savef.DockingPorts.size(); i++)
						{
							int[] pos = savef.DockingPorts.get(i);
							
							if (world.getTileEntity(pos[0], pos[1], pos[2]) != null)
							{
								TileEntityDockingPort te = (TileEntityDockingPort) world.getTileEntity(pos[0], pos[1], pos[2]);
								
								if (te.getStackInSlot(te.getSizeInventory() - 2) == null && te.getStackInSlot(te.getSizeInventory() - 3) == null)
								{
									player.setPositionAndUpdate(pos[0] + 0.5D, pos[1] + 2, pos[2] + 0.5D);
									
									if (playerStats.rocketStacks != null)
									{
										te.setSizeInventory(playerStats.rocketStacks.length + 2);
										// te.chestContents =
										// ((WorldProviderOrbitModif)world.provider).rocketStacks;
										ItemStack[] RCis = playerStats.rocketStacks;
										
										for (int i2 = 0; i2 < RCis.length - 2; i2++)
										{
											te.setInventorySlotContents(i2, RCis[i2]);
										}
										if (RCis[RCis.length - 1] != null)
											te.setLastID(RCis[RCis.length - 1].hasTagCompound() ? RCis[RCis.length - 1].getTagCompound().getInteger("UniqueID") : -1);
										te.setInventorySlotContents(te.getSizeInventory() - 3, RCis[RCis.length - 2]);
										te.setInventorySlotContents(te.getSizeInventory() - 2, RCis[RCis.length - 1]);
										boolean preFueled = false;
										ItemStack rocketI = RCis[RCis.length - 1];
										if (rocketI != null)
										{
											int type = rocketI.getItemDamage();
											// Checking type
											if (type == IRocketType.EnumRocketType.PREFUELED.getIndex())
											{
												preFueled = true;
											}
										}
										int tier = EntityRocketFakeTiered.getTierFromItem(rocketI);
										double hight = 1.8D;
										if (tier == 2) hight = 1.9D;
										else if (tier == 3) hight = 2.4D;
										te.rocket = new EntityRocketFakeTiered(world, te.xCoord + 0.5D, te.yCoord - hight, te.zCoord + 0.5D, tier, te);
										// creating fuel tank for rocket fuel
										// size
										te.fuelTank = new FluidTank(te.rocket.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
										
										if (preFueled)
										{
											// if rocket is prefueld, fueling
											// it.
											te.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, te.fuelTank.getCapacity()), true);
										} else if (rocketI != null && rocketI.hasTagCompound())
										{
											// reading fuel from item NBT
											te.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, playerStats.fuelLevel), true);
										}
										
										// spawning rocket in world
										te.rocket.fuelTank = te.fuelTank;
										world.spawnEntityInWorld(te.rocket);
										playerStats.rocketStacks = new ItemStack[1];
										playerStats.fuelLevel = 0;
										
									}
									GLoger.logInfo("Rocket started rendezvous with docking port N" + (i + 1));
									docked = true;
									break;
								} else GLoger.logInfo("Rocket already docked in dock N" + (i + 1));
							}
						}
					}
					if (!docked)
					{
						for (int i = 0; i < 4; i++)
						{
							if (world.getBlock(i == 0 ? 1 : i == 1 ? -1 : 0, 63, i == 2 ? 1 : i == 3 ? -1 : 0) instanceof BlockAir)
							{
								world.setBlock(i == 0 ? 1 : i == 1 ? -1 : 0, 63, i == 2 ? 1 : i == 3 ? -1 : 0, GCBlocks.parachest);
								TileEntityParaChest chest = (TileEntityParaChest) world.getTileEntity(i == 0 ? 1 : i == 1 ? -1 : 0, 63, i == 2 ? 1 : i == 3 ? -1 : 0);
								if (chest == null)
								{
									chest = new TileEntityParaChest();
									world.setTileEntity(i == 0 ? 1 : i == 1 ? -1 : 0, 63, i == 2 ? 1 : i == 3 ? -1 : 0, chest);
								}
								chest.setSizeInventory(playerStats.rocketStacks.length + 1);
								ItemStack[] RCis = playerStats.rocketStacks;
								
								for (int i2 = 0; i2 < RCis.length - 2; i2++)
								{
									chest.setInventorySlotContents(i2, RCis[i2]);
								}
								chest.setInventorySlotContents(chest.getSizeInventory() - 3, RCis[RCis.length - 2]);
								chest.setInventorySlotContents(chest.getSizeInventory() - 2, RCis[RCis.length - 1]);
								boolean preFueled = false;
								ItemStack rocketI = RCis[RCis.length - 1];
								if (rocketI != null)
								{
									int type = rocketI.getItemDamage();
									if (type == IRocketType.EnumRocketType.PREFUELED.getIndex())
									{
										preFueled = true;
									}
								}
								int tier = EntityRocketFakeTiered.getTierFromItem(rocketI);
								chest.fuelTank = new FluidTank((1000 + (tier > 1 ? 500 : 0)) * ConfigManagerCore.rocketFuelFactor);
								
								if (preFueled)
								{
									
									chest.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, chest.fuelTank.getCapacity()), true);
								} else
								{
									// reading fuel from item NBT
									chest.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, playerStats.fuelLevel), true);
								}
								playerStats.rocketStacks = new ItemStack[1];
								playerStats.fuelLevel = 0;
								GLoger.logInfo("Created a chest with rocket items");
								break;
								
							}
						}
						
						if (!docked) GLoger.logInfo("Dim haven't a docking port info");
					}
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
	public void onTick(ClientTickEvent event)
	{
		if (event.side == Side.CLIENT)
		{
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.thePlayer != null && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack)
			{
				List<Integer> keys = new ArrayList<>();
				
				if (Keyboard.isKeyDown(KeyHandlerClient.accelerateKey.getKeyCode()))
				{
					keys.add(0);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.decelerateKey.getKeyCode()))
				{
					keys.add(1);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.leftKey.getKeyCode()))
				{
					keys.add(2);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.rightKey.getKeyCode()))
				{
					keys.add(3);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.spaceKey.getKeyCode()))
				{
					keys.add(4);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.leftShiftKey.getKeyCode()))
				{
					keys.add(5);
				}
				
				if (mc.thePlayer.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack)
				{
					ItemSpaceJetpack jetpack = (ItemSpaceJetpack) mc.thePlayer.getCurrentArmor(2).getItem();
					if (!(jetpack.KeysPressed.equals(keys)) && jetpack.activated)
					{
						jetpack.KeysPressed = keys;
						//jetpack.markDirty();
					}
				}
				
			}
			
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final WorldClient world = minecraft.theWorld;
		final EntityClientPlayerMP player = minecraft.thePlayer;
		
		if (world != null)
		{
			if (world.provider instanceof WorldProviderOrbitEarth)
			{
				if (world.provider.getSkyRenderer() == null)
				{
					world.provider
							.setSkyRenderer(new SkyProviderOrbitEarth(new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/gui/celestialbodies/earth.png"), true, true));
					((SkyProviderOrbitEarth) world.provider.getSkyRenderer()).spinDeltaPerTick = ((WorldProviderOrbitEarth) world.provider).getSpinRate();
					GCPlayerStatsClient.get(player).inFreefallFirstCheck = false;
				}
				
				if (world.provider.getCloudRenderer() == null)
				{
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			} else if (world.provider instanceof WorldProviderOrbitModif)
			{
				if (world.provider.getSkyRenderer() == null)
				{
					world.provider
							.setSkyRenderer(new SkyProviderOrbitModif(new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/gui/celestialbodies/mars.png"), true, true));
					((SkyProviderOrbitModif) world.provider.getSkyRenderer()).spinDeltaPerTick = ((WorldProviderOrbitModif) world.provider).getSpinRate();
					GCPlayerStatsClient.get(player).inFreefallFirstCheck = false;
				}
				
				if (world.provider.getCloudRenderer() == null)
				{
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
		}
		
		if (!KeyHandlerClient.spaceKey.getIsKeyPressed())
		{
			ClientProxy.lastSpacebarDown = false;
		}
		
		if (player != null && player.ridingEntity != null && player.ridingEntity instanceof EntityRocketFakeTiered && KeyHandlerClient.spaceKey.getIsKeyPressed()
				&& !ClientProxy.lastSpacebarDown && player.worldObj.provider instanceof WorldProviderOrbit)
		{
			PacketHandler.sendToServer(new LaunchRocketPacket());
			ClientProxy.lastSpacebarDown = true;
		}
		
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
