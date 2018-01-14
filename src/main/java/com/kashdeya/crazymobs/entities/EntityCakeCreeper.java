package com.kashdeya.crazymobs.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityCakeCreeper extends EntityCreeper {
	
	  private int timeSinceIgnited;
	  private int fuseTime = 30;// config option
	  private int explosionRadius = 3;// config option
	  
	  public EntityCakeCreeper(World worldIn) {
		  super(worldIn);
		  this.setSize(0.6F, 1.7F);
		  this.experienceValue = 20;// config option
		  this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	  }
	  
	  @Override
	  public int getMaxFallHeight() {
		  return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
	  }
	  
	  @Override
	  public void fall(float distance, float damageMultiplier) {
		  super.fall(distance, damageMultiplier);
		  this.timeSinceIgnited = ((int)((float)this.timeSinceIgnited + distance * 1.5F));
		  if (this.timeSinceIgnited > this.fuseTime - 5) {
			  this.timeSinceIgnited = (this.fuseTime - 5);
		  }
	  }
	  
	  @Override
	  public void onUpdate() {
		  if (this.isEntityAlive()) {
			  int i;
	          if (this.hasIgnited()) {
	        	  this.setCreeperState(1);
	            }
	            if ((i = this.getCreeperState()) > 0 && this.timeSinceIgnited == 0) {
	            	this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);// config option volume
	                this.timeSinceIgnited = 1;
	            }
	            this.timeSinceIgnited += i;
	            if (this.timeSinceIgnited < 0) {
	                this.timeSinceIgnited = 0;
	            }
	            if (this.timeSinceIgnited >= this.fuseTime) {
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
	  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		  return SoundEvents.ENTITY_CREEPER_HURT;
	  }
	  
	  @Override
	  protected SoundEvent getDeathSound() {
		  return SoundEvents.ENTITY_CREEPER_DEATH;
	  }
	  
	  @Override
	  public void onDeath(DamageSource cause)
	    {
	        super.onDeath(cause);

	        if (this.world.getGameRules().getBoolean("doMobLoot"))
	        {
	        	if (cause.getTrueSource() instanceof EntityPlayer)
	            {
	        		int i = Item.getIdFromItem(Items.CAKE);
		            this.dropItem(Item.getItemById(i), 1);
	            }
	        	else if (cause.getTrueSource() instanceof EntityCreeper && cause.getTrueSource() != this && ((EntityCreeper)cause.getTrueSource()).getPowered() && ((EntityCreeper)cause.getTrueSource()).ableToCauseSkullDrop())
	            {
	                ((EntityCreeper)cause.getTrueSource()).incrementDroppedSkulls();
	                this.entityDropItem(new ItemStack(Items.SKULL, 1, 4), 0.0F);
	            }
	        }
	    }
	  
	}