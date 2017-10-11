package com.kashdeya.crazymobs.proxy;

import com.kashdeya.crazymobs.entities.EntityBlazeCreeper;
import com.kashdeya.crazymobs.entities.EntityZombieCreeper;
import com.kashdeya.crazymobs.main.CrazyMobs;
import com.kashdeya.crazymobs.main.Reference;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonProxy {
	
	public void preInit(){
		EntityRegistry.registerModEntity(getEntityResource("zombie_creeper"), EntityZombieCreeper.class, (String)"zombie_creeper", (int)1, (Object)CrazyMobs.instance, (int)64, (int)1, (boolean)true, (int)4365323, (int)6051800);
		EntityRegistry.registerModEntity(getEntityResource("blaze_creeper"), EntityBlazeCreeper.class, (String)"blaze_creeper", (int)2, (Object)CrazyMobs.instance, (int)64, (int)1, (boolean)true, (int)4375424, (int)5041701);
	}

	private ResourceLocation getEntityResource(String entityName) {
		// TODO Auto-generated method stub
		return new ResourceLocation(Reference.MOD_ID, entityName);
	}

	public void init() {
		EntityRegistry.addSpawn(EntityZombieCreeper.class, 100, 2, 5, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntityZombieCreeper.class, 100, 2, 5, EnumCreatureType.MONSTER, Biomes.PLAINS);
    }

	public void registerRenderers() {
		//unused - only called clientside
	}

}
