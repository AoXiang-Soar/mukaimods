package mukaimods.recipe;

import mukaimods.block.BlockLoader;
import mukaimods.item.ItemLoader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MeltRecipe {
	public MeltRecipe()
    {
		GameRegistry.addSmelting(BlockLoader.blockMusicOre, new ItemStack(ItemLoader.itemGemMusic), 1.5F);
		GameRegistry.addSmelting(Items.SLIME_BALL, new ItemStack(ItemLoader.itemRubber), 1.5F);
    }

}
