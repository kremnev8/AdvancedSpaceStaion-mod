
package net.glider.src.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.glider.src.MCACommonLibrary.IMCAnimatedEntity;
import net.glider.src.MCACommonLibrary.animation.AnimationHandler;
import net.glider.src.network.PacketHandler;
import net.glider.src.renderer.animations.AnimationHandlerJetpack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties, IMCAnimatedEntity {
	/*
	Here I create a constant EXT_PROP_NAME for this class of properties
	You need a unique name for every instance of IExtendedEntityProperties
	you make, and doing it at the top of each class as a constant makes
	it very easy to organize and avoid typos. It's easiest to keep the same
	constant name in every class, as it will be distinguished by the class
	name: ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME
	
	Note that a single entity can have multiple extended properties, so each
	property should have a unique name. Try to come up with something more
	unique than the tutorial example.
	*/
	public final static String EXT_PROP_NAME = "AdvSS";

	// I always include the entity to which the properties belong for easy access
	// It's final because we won't be changing which player it is
	public final EntityPlayer player;

	// Declare other variables you want to add here
	public AnimationHandlerJetpack animH = new AnimationHandlerJetpack(this);

	//public boolean ClientInit;

	/*
	The default constructor takes no arguments, but I put in the Entity
	so I can initialize the above variable 'player'
	
	Also, it's best to initialize any other variables you may have added,
	just like in any constructor.
	*/
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
	{
		// We only want to send from the server to the client
		if (FMLCommonHandler.instance().getEffectiveSide().isServer() && player != null)
		{
			//	ClientInit = true;
			//			PacketHandler.sendTo(new PlayerPacket(this), (EntityPlayerMP) player);
		}
	}

	/**
	 * Used to register these extended properties for the player during EntityConstructing event
	 * This method is for convenience only; it will make your code look nicer
	 */
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}

	/**
	 * Returns ExtendedPlayer properties for player
	 * This method is for convenience only; it will make your code look nicer
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
						//	gravityS.add(nbtlist.func_150309_d(i));
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
