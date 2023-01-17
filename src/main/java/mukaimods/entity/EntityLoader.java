package mukaimods.entity;

import mukaimods.Mukaimods;
import mukaimods.player.EntityGodczm;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityLoader {
	private static int czmID = 0;

	public EntityLoader() {
		registerEntity(new ResourceLocation(Mukaimods.MODID, "Godczm"), EntityGodczm.class, "Godczm", 80, 3, true);
		EntityRegistry.addSpawn(EntityGodczm.class, 1, 1, 5, EnumCreatureType.CREATURE, Biomes.JUNGLE);
		registerEntityEgg(new ResourceLocation(Mukaimods.MODID, "Godczm"), 0xEEEEEE, 0x222222);
	}

	private static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String name,
			int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(registryName, entityClass, name, czmID++, Mukaimods.instance, trackingRange,
				updateFrequency, sendsVelocityUpdates);
	}

	private static void registerEntityEgg(ResourceLocation entityClass, int eggPrimary, int eggSecondary) {
		EntityRegistry.registerEgg(entityClass, eggPrimary, eggSecondary);
	}

}
