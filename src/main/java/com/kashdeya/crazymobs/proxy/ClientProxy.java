package com.kashdeya.crazymobs.proxy;

import com.kashdeya.crazymobs.entities.EntityBlazeCreeper;
import com.kashdeya.crazymobs.entities.EntityZombieCreeper;
import com.kashdeya.crazymobs.handlers.RenderBlazeCreeper;
import com.kashdeya.crazymobs.handlers.RenderZombieCreeper;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
	public void registerRenderers() {
    	RenderingRegistry.registerEntityRenderingHandler(EntityZombieCreeper.class, RenderZombieCreeper::new);
    	RenderingRegistry.registerEntityRenderingHandler(EntityBlazeCreeper.class, RenderBlazeCreeper::new);
	}
}
