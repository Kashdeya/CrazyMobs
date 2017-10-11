package com.kashdeya.crazymobs.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBlazeCreeper extends EntityBlaze {
	
	private float heightOffset = 0.5F;
	private int heightOffsetUpdateTime;
	  
	  public EntityBlazeCreeper(World worldIn) {
		  super(worldIn);
		  this.isImmuneToFire = true;
	      this.experienceValue = 20;
	      this.setSize(0.6F, 1.7F);
	  }
	  
	  @Override
	  protected void initEntityAI()
	    {
	        this.tasks.addTask(4, new EntityBlazeCreeper.AIFireballAttack(this));
	        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 2.0D));
	        this.tasks.addTask(6, new EntityAIWander(this, 0.8D));
	        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	        this.tasks.addTask(7, new EntityAILookIdle(this));
	        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	    }
	  
	  @Override
	  protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.5D);
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
	    }
	  
	  @Override
	  protected ResourceLocation getLootTable() {
		  return LootTableList.ENTITIES_CREEPER;
	  }
	  
	  @SideOnly(Side.CLIENT)
	  public int getBrightnessForRender(float partialTicks) {
		  return 15000000;
	  }

	  @Override
	  public float getBrightness() {
		  return 0.5F;
	  }
	  
	  @Override
	  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		  return SoundEvents.ENTITY_BLAZE_HURT;
	  }
	  
	  @Override
	  protected SoundEvent getDeathSound() {
		  return SoundEvents.ENTITY_BLAZE_DEATH;
	  }
	  
	  @Override
	  protected void updateAITasks()
	    {
	        if (this.isWet())
	        {
	            this.attackEntityFrom(DamageSource.DROWN, 0.0F);
	        }

	        --this.heightOffsetUpdateTime;

	        if (this.heightOffsetUpdateTime <= 0)
	        {
	            this.heightOffsetUpdateTime = 100;
	            this.heightOffset = 0.75F + (float)this.rand.nextGaussian() * 3.5F;
	        }

	        EntityLivingBase entitylivingbase = this.getAttackTarget();

	        if (entitylivingbase != null && entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset)
	        {
	            this.motionY += (0.31D - this.motionY) * 0.31D;
	            this.isAirBorne = true;
	        }

	        super.updateAITasks();
	    }
	  
	  @Override
	  public void onLivingUpdate() {
		  if (!this.onGround && this.motionY < 0.0D) {
			  this.motionY *= 0.31D;
	      }
		  
		  if (this.world.isRemote) {
			  for (int i = 0; i < 4; ++i) {
				  this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D, new int[0]);
			  }
		  }
		  
		  super.onLivingUpdate();
	  }
	  
	  static class AIFireballAttack extends EntityAIBase
      {
          private final EntityBlazeCreeper blaze;
          private int attackStep;
          private int attackTime;

          public AIFireballAttack(EntityBlazeCreeper blazeIn)
          {
              this.blaze = blazeIn;
              this.setMutexBits(3);
          }

          /**
           * Returns whether the EntityAIBase should begin execution.
           */
          public boolean shouldExecute()
          {
              EntityLivingBase entitylivingbase = this.blaze.getAttackTarget();
              return entitylivingbase != null && entitylivingbase.isEntityAlive();
          }

          /**
           * Execute a one shot task or start executing a continuous task
           */
          public void startExecuting()
          {
              this.attackStep = 0;
          }

          /**
           * Resets the task
           */
          public void resetTask()
          {
              this.blaze.setOnFire(false);
          }

          /**
           * Updates the task
           */
          public void updateTask()
          {
              --this.attackTime;
              EntityLivingBase entitylivingbase = this.blaze.getAttackTarget();
              double d0 = this.blaze.getDistanceSqToEntity(entitylivingbase);

              if (d0 < 4.0D)
              {
                  if (this.attackTime <= 0)
                  {
                      this.attackTime = 5;
                      this.blaze.attackEntityAsMob(entitylivingbase);
                  }

                  this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
              }
              else if (d0 < 256.0D)
              {
                  double d1 = entitylivingbase.posX - this.blaze.posX;
                  double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.blaze.posY + (double)(this.blaze.height / 2.0F));
                  double d3 = entitylivingbase.posZ - this.blaze.posZ;

                  if (this.attackTime <= 0)
                  {
                      ++this.attackStep;

                      if (this.attackStep == 1)
                      {
                          this.attackTime = 10;
                          this.blaze.setOnFire(true);
                      }
                      else if (this.attackStep <= 4)
                      {
                          this.attackTime = 3;
                      }
                      else
                      {
                          this.attackTime = 20;
                          this.attackStep = 0;
                          this.blaze.setOnFire(false);
                      }

                      if (this.attackStep > 1)
                      {
                          float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                          this.blaze.world.playEvent((EntityPlayer)null, 1018, new BlockPos((int)this.blaze.posX, (int)this.blaze.posY, (int)this.blaze.posZ), 0);

                          for (int i = 0; i < 1; ++i)
                          {
                              EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.blaze.world, this.blaze, d1 + this.blaze.getRNG().nextGaussian() * (double)f, d2, d3 + this.blaze.getRNG().nextGaussian() * (double)f);
                              entitysmallfireball.posY = this.blaze.posY + (double)(this.blaze.height / 5.0F) + 0.5D;
                              this.blaze.world.spawnEntity(entitysmallfireball);
                          }
                      }
                  }

                  this.blaze.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
              }
              else
              {
                  this.blaze.getNavigator().clearPathEntity();
                  this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
              }

              super.updateTask();
          }
      }
	  
}