package com.kashdeya.crazymobs.handlers;

import com.kashdeya.crazymobs.entities.EntityCakeCreeper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCakeCreeper extends RenderLiving<EntityCakeCreeper> {

	private static ResourceLocation TEXTURE = new ResourceLocation("cm:textures/entity/cake_creeper.png");

	public RenderCakeCreeper(RenderManager managerIn) {
		super(managerIn, (ModelBase) new ModelCreeper(), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCakeCreeper entity) {
		return TEXTURE;
	}
}