package mukaimods.common;

import mukaimods.block.BlockLoader;
import mukaimods.createtab.CreativeTabsLoader;
import mukaimods.enchantment.EnchantmentLoader;
import mukaimods.entity.EntityLoader;
import mukaimods.gui.container.GuiElementLoader;
import mukaimods.internet.NetworkManagers;
import mukaimods.item.ItemLoader;
import mukaimods.player.CapabilityLoader;
import mukaimods.player.EventLoader;
import mukaimods.potion.PotionLoader;
import mukaimods.recipe.MeltRecipeLoader;
import mukaimods.tileentity.TileEntityLoader;
import mukaimods.world.WorldGeneratorLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;;

public class Common {
	public void preInit(FMLPreInitializationEvent event) {
		new EventLoader();
		new CreativeTabsLoader();
		new BlockLoader();
		new CapabilityLoader();
		new ItemLoader();
		new EnchantmentLoader();
		new MeltRecipeLoader();
		new OreDictionaryLoader();
		new EntityLoader();
		new TileEntityLoader();
		new GuiElementLoader();
		new NetworkManagers();

		new PotionLoader();

	}

	public void init(FMLInitializationEvent event) {

		new WorldGeneratorLoader();
	}

	public void postInit(FMLPostInitializationEvent event) {
		new CraftingLoader();
	}

	public void onStart(FMLServerStartingEvent event) {
		new CommandLoader(event);
	}
}