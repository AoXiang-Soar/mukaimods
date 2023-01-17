package mukaimods.block;

import java.util.ArrayList;

import mukaimods.createtab.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class BlockLoader {
	private static ArrayList<Block> allBlocks = new ArrayList<Block>(); // 必须写在所有方块对象前，否则会报错

	public static Block blockLost = BlockFactory.create(BlockGlass.class, "blocklost", null, 0.5F, null, null, CreativeTabsLoader.tabItems);
	public static Block blockFar = BlockFactory.create(BlockGlass.class, "blockfar", null, 0.5F, null, null, CreativeTabsLoader.tabItems);
	public static Block blockPure = BlockFactory.create(BlockGlass.class, "blockpure", null, 0.5F, null, null, CreativeTabsLoader.tabItems);
	public static Block blockMusicOre = BlockFactory.create(BlockMusicOre.class, "block_music_ore", null, 5F, "pickaxe", 2, CreativeTabsLoader.tabItems);
	public static Block blockSongsMaker = BlockFactory.create(BlockSongsMaker.class, "blockmakemusic", null, 0.5F, null, null, CreativeTabsLoader.tabItems);
	public static Block blockAutoSongPlayer = BlockFactory.create(BlockAutoSongPlayer.class, "blockplaymusic", null, 0.5F, null, null, CreativeTabsLoader.tabItems);
	public static Block blockMusicEmerald  = BlockFactory.create(null, "music_emerald_block", null, 0.5F, "pickaxe", 2, CreativeTabsLoader.tabItems);
	public static Block blockMusicIron  = BlockFactory.create(null, "music_iron_block", null, 0.5F, "pickaxe", 2, CreativeTabsLoader.tabItems);
	public static Block blockBYDNoteGetter  = BlockFactory.create(BlockAbstractNoteGetter.BlockBYDNoter.class, "block_make_bydmusic", null, 0.5F, "axe", 0, CreativeTabsLoader.tabItems);
	public static Block blockFTRNoteGetter  = BlockFactory.create(BlockAbstractNoteGetter.BlockFTRNoter.class, "block_make_ftrmusic", null, 0.5F, "axe", 0, CreativeTabsLoader.tabItems);
	public static Block blockPRSNoteGetter  = BlockFactory.create(BlockAbstractNoteGetter.BlockPRSNoter.class, "block_make_prsmusic", null, 0.5F, "axe", 0, CreativeTabsLoader.tabItems);
	public static Block blockPSTNoteGetter  = BlockFactory.create(BlockAbstractNoteGetter.BlockPSTNoter.class, "block_make_pstmusic", null, 0.5F, "axe", 0, CreativeTabsLoader.tabItems);
	
	// 批量注册物品
	public BlockLoader() {
		allBlocks.forEach(b -> register(b));
	}

	// 批量注册物品材质
	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		allBlocks.forEach(b -> registerRender(b));
	}

	private static void register(Block block) {
		String[] arr = block.getUnlocalizedName().toString().split("\\.");
		ForgeRegistries.BLOCKS.register(block.setRegistryName(arr[1]));
		ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Block block) {
		ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
	}
	
	// 使用这个方法手动添加方块到本mod，但一般情况下你应该通过BlockFactory.create()来批量添加
	static void addBlock(Block b) {
		allBlocks.add(b);
    }
}
