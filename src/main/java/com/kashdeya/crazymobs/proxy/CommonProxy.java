package com.kashdeya.crazymobs.proxy;

import java.util.ArrayList;
import java.util.List;

import com.kashdeya.crazymobs.entities.EntityBlazeCreeper;
import com.kashdeya.crazymobs.entities.EntityZombieCreeper;
import com.kashdeya.crazymobs.main.CrazyMobs;
import com.kashdeya.crazymobs.main.Reference;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.EntityRegistry;

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
		
		List<BiomeEntry> biomeEntries = new ArrayList<BiomeEntry>();
		biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.COOL));
		biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.DESERT));
		biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.ICY));
		biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.WARM));
		List<Biome> biomes = new ArrayList<Biome>();
		for (BiomeEntry b : biomeEntries){
			biomes.add(b.biome);
		}
		
		EntityRegistry.addSpawn(EntityZombieCreeper.class, 100, 2, 5, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
		EntityRegistry.addSpawn(EntityBlazeCreeper.class, 100, 2, 5, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
    }

	public void registerRenderers() {
		//unused - only called clientside
	}

}
