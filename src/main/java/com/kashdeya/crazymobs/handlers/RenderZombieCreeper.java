package com.kashdeya.crazymobs.handlers;

import com.kashdeya.crazymobs.entities.EntityZombieCreeper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZombieCreeper extends RenderLiving<EntityZombieCreeper> {

	private static ResourceLocation TEXTURE = new ResourceLocation("cm:textures/entity/zombie_creeper.png");

	public RenderZombieCreeper(RenderManager managerIn) {
		super(managerIn, (ModelBase) new ModelCreeper(), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityZombieCreeper entity) {
		return TEXTURE;
	}
}