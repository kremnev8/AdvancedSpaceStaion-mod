
package net.glider.src.entity;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.glider.src.entity.EntityRocketFakeTiered.EnumEngineState;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SoundUpdateRocketFake extends MovingSound {
	private final EntityPlayerSP thePlayer;
	private final EntityRocketFakeTiered theRocket;
	private boolean soundStopped;
	private boolean ignition = false;
	
	public SoundUpdateRocketFake(EntityPlayerSP par1EntityPlayerSP, EntityRocketFakeTiered par2Entity)
	{
		super(new ResourceLocation(GalacticraftCore.TEXTURE_PREFIX + "shuttle.shuttle"));
		this.theRocket = par2Entity;
		this.thePlayer = par1EntityPlayerSP;
		this.field_147666_i = ISound.AttenuationType.NONE;
		this.volume = 0.00001F; //If it's zero it won't start playing
		this.field_147663_c = 0.0F; //pitch
		this.repeat = true;
		this.field_147665_h = 0; //repeat delay
		this.updateSoundLocation(par2Entity);
	}
	
	/**
	 * Updates the JList with a new model.
	 */
	@Override
	public void update()
	{
		if (!this.theRocket.isDead)
		{
			if (this.theRocket.engineState == EnumEngineState.IGNITED.ordinal())
			{
				if (!ignition)
				{
					this.field_147663_c = 0.0F;
					ignition = true;
				}
				
			} else
				this.field_147663_c = 1.0F;
			
			if (this.theRocket.engineState == EnumEngineState.IGNITED.ordinal())
			{
				if (this.theRocket.posY > 1000)
				{
					this.volume = 0F;
					//	if (!this.theRocket.landing) this.donePlaying = true;
				} else if (this.theRocket.posX > 200)
				{
					this.volume = (1000F - (float) this.theRocket.posX) * 0.001F;
				} else
					this.volume = 1.0F;
			}
			
			this.updateSoundLocation(this.theRocket);
		} else
		{
			this.donePlaying = true;
		}
	}
	
	public void stopRocketSound()
	{
		this.donePlaying = true;
		this.soundStopped = true;
	}
	
	public void updateSoundLocation(Entity e)
	{
		this.xPosF = (float) e.posX;
		this.yPosF = (float) e.posY;
		this.zPosF = (float) e.posZ;
	}
}