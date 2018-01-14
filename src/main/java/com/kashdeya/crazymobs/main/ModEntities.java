package com.kashdeya.crazymobs.main;

import java.util.ArrayList;
import java.util.List;

import com.kashdeya.crazymobs.entities.EntityBlazeCreeper;
import com.kashdeya.crazymobs.entities.EntityCakeCreeper;
import com.kashdeya.crazymobs.entities.EntityJohnCenaCreeper;
import com.kashdeya.crazymobs.entities.EntityZombieCreeper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	
	public static void init() {
		registerEntity(0, EntityZombieCreeper.class, "zombie_creeper", 4365323, 6051800);
		registerEntity(1, EntityBlazeCreeper.class, "blaze_creeper", 4375424, 5041701);
		registerEntity(2, EntityJohnCenaCreeper.class, "john_cena_creeper", 4374424, 5021701);
		registerEntity(3, EntityCakeCreeper.class, "cake_creeper", 4474424, 5221701);

	}

	//Entities without spawn eggs - eg. projetiles etc
	private static final void registerEntity(int id, Class<? extends Entity> entityClass, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), entityClass, name, id, CrazyMobs.INSTANCE, 256, 3, true);
	}

	// Entities with spawn eggs
	private static final void registerEntity(int id, Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), entityClass, name, id, CrazyMobs.INSTANCE, 256, 3, true, eggBackgroundColor, eggForegroundColor);
	}
	
	public static void initSpawns() {
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
		EntityRegistry.addSpawn(EntityJohnCenaCreeper.class, 100, 2, 5, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
		EntityRegistry.addSpawn(EntityCakeCreeper.class, 100, 2, 5, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
    }

}
