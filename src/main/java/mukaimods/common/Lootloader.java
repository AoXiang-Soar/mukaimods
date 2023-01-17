package mukaimods.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class Lootloader {
	public Lootloader() {
		LootTableList.register(new ResourceLocation("mukaimods" , "simple_dungeon"));
	}
}
