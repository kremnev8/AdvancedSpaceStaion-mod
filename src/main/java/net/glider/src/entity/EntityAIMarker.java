package net.glider.src.entity;

import scala.reflect.internal.Trees.This;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAIMarker extends EntityLiving {

	public EntityAIMarker(World p_i1681_1_) {
		super(p_i1681_1_);
		this.setSize(0.1F, 0.5F); 
		System.out.println("SPAWNED!");
		this.hurtResistantTime = -1;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource ds, float damage) {
		if (ds.isExplosion() || ds.isFireDamage())
		{
			this.setDead();
			System.out.println("DEAD!");
			return true;
		}else
		{
			return false;
		}
	}
	
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
    }
	
	@Override
	protected float getSoundVolume() 
	{
	   return 0.0F;
	}
	
	@Override
	public boolean isPotionApplicable(PotionEffect p_70687_1_) {
		return false;
	}
	@Override
	public boolean canEntityBeSeen(Entity p_70685_1_) {
		return false;
	}
	@Override
	public boolean canBePushed() {
		return false;
	}
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	
    public boolean isAIEnabled()
    {
        return false;
    }
   
}
