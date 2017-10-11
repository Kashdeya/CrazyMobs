package com.kashdeya.crazymobs.handlers;

import com.kashdeya.crazymobs.entities.EntityBlazeCreeper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlazeCreeperRenderHandler extends RenderLiving<EntityBlazeCreeper> {
	
	private static ResourceLocation DEFAULT = new ResourceLocation("cm:textures/entity/blaze_creeper.png");
	
	public BlazeCreeperRenderHandler(RenderManager managerIn) {
		super(managerIn, (ModelBase)new ModelCreeper(), 0.25F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBlazeCreeper entity) {
		return this.DEFAULT;
	}
}