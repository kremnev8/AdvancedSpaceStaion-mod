
package net.glider.src;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.entity.EntityMod;
import net.glider.src.gui.GuiBuilder;
import net.glider.src.handlers.Events;
import net.glider.src.handlers.KeyHandlerClient;
import net.glider.src.items.ItemMod;
import net.glider.src.renderer.ItemRenderArmorStand;
import net.glider.src.renderer.ItemRenderInfo;
import net.glider.src.renderer.ItemRenderJetpack;
import net.glider.src.renderer.ItemRenderRemoveInfo;
import net.glider.src.renderer.RendererPlayer;
import net.glider.src.renderer.TileEntityArmorStandRenderer;
import net.glider.src.renderer.TileEntityInfoRenderer;
import net.glider.src.renderer.TileEntityRemoveInfoRenderer;
import net.glider.src.renderer.fx.EffectHandler;
import net.glider.src.renderer.models.GliderModelPlayerBase;
import net.glider.src.renderer.models.ModelJetpack;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import api.player.model.ModelPlayerAPI;
import api.player.render.RenderPlayerAPI;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	
	public static boolean lastSpacebarDown;
	public static ModelBiped model;
	
	@Override
	public void PreInit(FMLPreInitializationEvent event)
	{
		super.PreInit(event);
		
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		model = new ModelJetpack();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRemoveInfo.class, new TileEntityRemoveInfoRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockContainerMod.BlockRemoveInfo), new ItemRenderRemoveInfo());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfo.class, new TileEntityInfoRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockContainerMod.BlockInfo), new ItemRenderInfo());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockContainerMod.BlockArmorStand), new ItemRenderArmorStand());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArmorStand.class, new TileEntityArmorStandRenderer());
		
		MinecraftForgeClient.registerItemRenderer(ItemMod.spaceJetpack, new ItemRenderJetpack());
		
		FMLCommonHandler.instance().bus().register(new Events());
		FMLCommonHandler.instance().bus().register(new KeyHandlerClient());
		
		RenderPlayerAPI.register(GliderModInfo.MOD_ID, RendererPlayer.class);
		ModelPlayerAPI.register(GliderModInfo.MOD_ID, GliderModelPlayerBase.class);
		
		ClientRegistry.registerKeyBinding(KeyHandlerClient.TestAnim);
		
		EntityMod.init(Side.CLIENT);
		GuiBuilder.init();
	}
	
	@Override
	public void PostInit(FMLPostInitializationEvent event)
	{
		super.PostInit(event);
	}
	
	@Override
	public void spawnParticle(String particleID, Vector3 position, Vector3 motion, Object[] otherInfo)
	{
		EffectHandler.spawnParticle(particleID, position, motion, otherInfo);
	}
	
	// In your client proxy:
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work because you will be getting a client
		// player even when you are on the server! Sounds absurd, but it's true.
		
		// Solution is to double-check side before returning the player:
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
