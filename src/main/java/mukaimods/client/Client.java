package mukaimods.client;

import mukaimods.common.Common;
import mukaimods.entity.EntityRenderLoader;
import mukaimods.item.ItemRenderLoader;
import mukaimods.sounds.SoundsMusicGame;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Client extends Common {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		new ItemRenderLoader();
		new EntityRenderLoader();
		new SoundsMusicGame();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}