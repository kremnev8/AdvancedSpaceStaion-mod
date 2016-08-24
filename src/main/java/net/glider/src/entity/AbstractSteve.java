package net.glider.src.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AbstractSteve extends AbstractClientPlayer {
	
	private static GameProfile gp = new GameProfile(null, "GliderStive");
	
	public AbstractSteve(World world)
	{
		super(world, gp);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getBrightnessForRender(float p_70070_1_)
	{
		return 15728880;
	}
	
	public boolean isInvisible()
	{
		return true;
	}
	
	@Override
	public void addChatMessage(IChatComponent p_145747_1_)
	{
	}
	
	@Override
	public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_)
	{
		return false;
	}
	
	@Override
	public ChunkCoordinates getPlayerCoordinates()
	{
		return null;
	}
}
