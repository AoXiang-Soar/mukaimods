package mukaimods.common;

import mukaimods.block.BlockLoader;
import mukaimods.item.ItemLoader;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryLoader {
	public OreDictionaryLoader() {

		OreDictionary.registerOre("ingotMusic", ItemLoader.itemMusicIngot);

		OreDictionary.registerOre("oreMusic", BlockLoader.blockMusicOre);

		OreDictionary.registerOre("gemMusic", ItemLoader.itemGemMusic);

		OreDictionary.registerOre("plateIron", ItemLoader.itemIronPlate);
		OreDictionary.registerOre("itemRubber", ItemLoader.itemRubber);
		OreDictionary.registerOre("circuitBasic", ItemLoader.itemDianluban);

	}

}
