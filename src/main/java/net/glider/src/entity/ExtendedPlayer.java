
package net.glider.src.entity;

import java.util.HashMap;
import java.util.Set;

import net.glider.src.MCACommonLibrary.IMCAnimatedEntity;
import net.glider.src.MCACommonLibrary.animation.AnimationHandler;
import net.glider.src.renderer.animations.AnimationHandlerJetpack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties, IMCAnimatedEntity {
	
	public final static String EXT_PROP_NAME = "AdvSS";
	
	public final EntityPlayer player;
	
	public AnimationHandlerJetpack animH = new AnimationHandlerJetpack(this);
	
	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
	}
	
	public void copy(ExtendedPlayer props)
	{
		this.animH = props.animH;
	}
	
	public String getPlayerDispName()
	{
		return player.getDisplayName();
	}
	
	public String getCurrentAnim()
	{
		if (animH != null)
		{
			if (animH.animCurrentChannels != null && animH.animCurrentChannels.size() > 0)
			{
				return animH.animCurrentChannels.get(0).name;
			}
		}
		return "";
	}
	
	public final void sync()
	{}
	
	/**
	 * Used to register these extended properties for the player during EntityConstructing event This method is for convenience only; it will make your code look nicer
	 */
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}
	
	/**
	 * Returns ExtendedPlayer properties for player This method is for convenience only; it will make your code look nicer
	 */
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		// We need to create a new tag compound that will save everything for our Extended Properties
		NBTTagCompound properties = new NBTTagCompound();
		
		HashMap<String, Float> anims = animH.getCurrentActiveAnimations();
		if (!anims.isEmpty())
		{
			Set an = anims.keySet();
			NBTTagList nbtlist = new NBTTagList();
			for (int i = 0; i < an.size(); i++)
			{
				String k = (String) an.toArray()[i];
				nbtlist.appendTag(new NBTTagString(k + "-" + String.valueOf(anims.get(k))));
				
			}
			properties.setTag("ANIMATIONS", nbtlist);
		} else
		{
			properties.setBoolean("NO_ANIM", true);
		}
		
		compound.setTag(EXT_PROP_NAME, properties);
		
	}
	
	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		
		if (properties != null)
		{
			if (!properties.hasKey("NO_ANIM"))
			{
				NBTTagList nbtlist = properties.getTagList("ANIMATIONS", 8);
				if (nbtlist.tagCount() != 0)
				{
					for (int i = 0; i < nbtlist.tagCount(); i++)
					{
						String kv = nbtlist.getStringTagAt(i);
						String[] kkvv = kv.split("-");
						String k = kkvv[0];
						Float v = Float.valueOf(kkvv[1]);
						animH.activateAnimation(k, v);
					}
				}
			}
		}
	}
	
	/*
	I personally have yet to find a use for this method. If you know of any,
	please let me know and I'll add it in! 
	*/
	@Override
	public void init(Entity entity, World world)
	{}
	
	@Override
	public AnimationHandler getAnimationHandler()
	{
		return animH;
	}
}
