package com.kashdeya.crazymobs.main;

import com.kashdeya.crazymobs.proxy.CommonProxy;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class CrazyMobs {

	@Instance(Reference.MOD_ID)
	public static CrazyMobs INSTANCE;

	@SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_COMMON)
	public static CommonProxy PROXY;

	public static final SoundEvent ENTITY_JOHN_FUSE = new SoundEvent(new ResourceLocation("cm:entities.john.fuse"));
	public static final SoundEvent ENTITY_JOHN_BLOW = new SoundEvent(new ResourceLocation("cm:entities.john.blow"));
	  
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ModEntities.init();
		ModEntities.initSpawns();
		PROXY.registerRenderers();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}
}
