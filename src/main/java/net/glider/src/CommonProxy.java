
package net.glider.src;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.glider.src.handlers.PlayerEvents;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {
	public void PreInit(FMLPreInitializationEvent event)
	{	
		
	}
	
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new PlayerEvents());
	}
	
	public void PostInit(FMLPostInitializationEvent event)
	{	
		
	}
	
	public void spawnParticle(String particleID, Vector3 position, Vector3 motion, Object[] otherInfo)
	{}
	
	/**
	 * Returns a side-appropriate EntityPlayer for use during message handling
	 */
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
	
}