package com.kashdeya.crazymobs.handlers;

import com.kashdeya.crazymobs.entities.EntityJohnCenaCreeper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJohnCenaCreeper extends RenderLiving<EntityJohnCenaCreeper> {

	private static ResourceLocation TEXTURE = new ResourceLocation("cm:textures/entity/john_creeper.png");

	public RenderJohnCenaCreeper(RenderManager managerIn) {
		super(managerIn, (ModelBase) new ModelCreeper(), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityJohnCenaCreeper entity) {
		return TEXTURE;
	}
}