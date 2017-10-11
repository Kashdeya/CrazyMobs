package com.kashdeya.crazymobs.proxy;

import com.kashdeya.crazymobs.entities.EntityBlazeCreeper;
import com.kashdeya.crazymobs.entities.EntityZombieCreeper;
import com.kashdeya.crazymobs.handlers.BlazeCreeperRenderHandler;
import com.kashdeya.crazymobs.handlers.ZombieCreeperRenderHandler;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
	public void registerRenderers() {
    	// Renders
    	super.registerRenderers();
    	RenderingRegistry.registerEntityRenderingHandler(EntityZombieCreeper.class, ZombieCreeperRenderHandler::new);
    	RenderingRegistry.registerEntityRenderingHandler(EntityBlazeCreeper.class, BlazeCreeperRenderHandler::new);
	}
}
