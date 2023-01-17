package mukaimods.common;

import mukaimods.Mukaimods;
import mukaimods.block.BlockLoader;
import mukaimods.item.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingLoader {

	private static final ResourceLocation DEFAULT = new ResourceLocation("mukaimods");
	public static int nextid = 0;

	public static ResourceLocation nextLocation() {
		return new ResourceLocation(Mukaimods.MODID, "recipes" + nextid++);
	}

	public CraftingLoader() {
		ForgeRegistries.RECIPES
				.register(new ShapedOreRecipe(DEFAULT, new ItemStack(Item.getItemFromBlock(BlockLoader.blockMusicIron)),
						new Object[] { "AAA", "A A", "AAA", 'A', ItemLoader.itemCZM }).setRegistryName(nextLocation()));
	}

}