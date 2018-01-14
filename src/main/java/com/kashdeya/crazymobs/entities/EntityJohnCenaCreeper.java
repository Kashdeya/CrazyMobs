package com.kashdeya.crazymobs.entities;

import com.kashdeya.crazymobs.main.CrazyMobs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityJohnCenaCreeper extends EntityCreeper {
	
	  private int timeSinceIgnited;
	  private int lastActiveTime;
	  private int fuseTime = 30;// config option
	  private int explosionRadius = 16;//Config Option
	  
	  public EntityJohnCenaCreeper(World worldIn) {
		  super(worldIn);
		  this.setSize(0.6F, 1.7F);
		  this.experienceValue = 20;// config option
		  this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	  }
	  
	  @Override
	  public void onUpdate()
	    {
	        if (this.isEntityAlive())
	        {
	            this.lastActiveTime = this.timeSinceIgnited;

	            if (this.hasIgnited())
	            {
	                this.setCreeperState(1);
	            }

	            int i = this.getCreeperState();

	            if (i > 0 && this.timeSinceIgnited == 0)
	            {
	                this.playSound(CrazyMobs.ENTITY_JOHN_FUSE, 1.0F, 1.0F);// config option volume
	            }

	            this.timeSinceIgnited += i;

	            if (this.timeSinceIgnited < 0)
	            {
	                this.timeSinceIgnited = 0;
	            }

	            if (this.timeSinceIgnited >= this.fuseTime)
	            {
	                this.timeSinceIgnited = this.fuseTime;
	                this.explode();
	            }
	        }

	        super.onUpdate();
	    }
	  
	  private void explode() {
		  if (!this.world.isRemote) {
			  boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
			  float f = getPowered() ? 2.0F : 1.0F;
			  this.dead = true;
			  this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius * f, true);
			  this.setDead();
		  }
	  }
	  
	  @Override
	  protected SoundEvent getDeathSound() {
		  return CrazyMobs.ENTITY_JOHN_BLOW;
	  }
	  
	  @SideOnly(Side.CLIENT)
	  public float getCreeperFlashIntensity(float p_70831_1_)
	    {
	        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (float)(this.fuseTime - 2);
	    }
	  
	}