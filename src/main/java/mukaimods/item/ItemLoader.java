package mukaimods.item;

import java.util.ArrayList;

import mukaimods.createtab.CreativeTabsLoader;
import mukaimods.item.equipments.*;
import mukaimods.sounds.ArcMusic;
import mukaimods.sounds.SoundsMusicGame;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemLoader {
	private static ArrayList<Item> allItems = new ArrayList<Item>(); // 必须写在所有物品前，否则会报错
	
	public static Item itemLost = ItemFactory.create(ItemLost.class, "grass", CreativeTabsLoader.tabItems, 64);
	public static Item itemFar = ItemFactory.create(ItemFar.class, "far", CreativeTabsLoader.tabItems, 64);
	public static Item itemPure = ItemFactory.create(ItemPure.class, "pure", CreativeTabsLoader.tabItems, 64);
	public static Item itemPureMax = ItemFactory.create(ItemPureMax.class, "puremax", CreativeTabsLoader.tabItems, 64);
	public static Item itemGemMusic = ItemFactory.create(Item.class, "music_emerald", CreativeTabsLoader.tabItems, 64);
	public static Item itemTrackLost = ItemFactory.create(ItemScore.class, "track_lost", CreativeTabsLoader.tabItems, 1);
	public static Item itemTrackComplete = ItemFactory.create(ItemScore.class, "track_complete", CreativeTabsLoader.tabItems, 1);
	public static Item itemFullRecall = ItemFactory.create(ItemScore.class, "full_recall", CreativeTabsLoader.tabItems, 1);
	public static Item itemPM = ItemFactory.create(ItemScore.class, "pm", CreativeTabsLoader.tabItems, 1);
	public static Item itemPMMax = ItemFactory.create(ItemScore.class, "pmmax", CreativeTabsLoader.tabItems, 1);
	static {
		((ItemScore)itemTrackLost).setMusic(SoundsMusicGame.Lost);
		((ItemScore)itemTrackComplete).setMusic(SoundsMusicGame.Complete);
		((ItemScore)itemFullRecall).setMusic(SoundsMusicGame.Full);
		((ItemScore)itemPM).setMusic(SoundsMusicGame.PM);
		((ItemScore)itemPMMax).setMusic(SoundsMusicGame.PMmax);
	}
	public static Item itemPTTGreen = ItemFactory.create(ItemPTT.class, "ptt_green", CreativeTabsLoader.tabItems, 1);
	public static Item itemPTTBlue = ItemFactory.create(ItemPTT.class, "ptt_blue", CreativeTabsLoader.tabItems, 1);
	public static Item itemPTTGay = ItemFactory.create(ItemPTT.class, "ptt_gay", CreativeTabsLoader.tabItems, 1);
	public static Item itemPTTFlowerlyGay = ItemFactory.create(ItemPTT.class, "ptt_gay1", CreativeTabsLoader.tabItems, 1);
	public static Item itemPTTRed = ItemFactory.create(ItemPTT.class, "ptt_red", CreativeTabsLoader.tabItems, 1);
	public static Item itemPTTStar = ItemFactory.create(ItemPTT.class, "ptt_star", CreativeTabsLoader.tabItems, 1);
	public static Item itemPTTStar2 = ItemFactory.create(ItemPTT.class, "ptt_star2", CreativeTabsLoader.tabItems, 1);
	public static Item itemPTTStar3 = ItemFactory.create(ItemPTT.class, "ptt_star3", CreativeTabsLoader.tabItems, 1);
	public static Item itemIcon = ItemFactory.create(ItemIcon.class, "icon", CreativeTabsLoader.tabItems, 1);
	public static Item itemYitai = ItemFactory.create(ItemYitai.class, "yitai", CreativeTabsLoader.tabItems, 64);
	public static Item itemVoidRecord = ItemFactory.create(Item.class, "songs", CreativeTabsLoader.tabItems, 1);
	public static Item itemCZM = ItemFactory.create(ItemCZM.class, "czm", null, 1);
	public static Item itemFragment = ItemFactory.create(ItemFragment.class, "suipian", CreativeTabsLoader.tabItems, 64);

	public static Item itemMusicStick = ItemFactory.create(Item.class, "items_music_stick", CreativeTabsLoader.tabItems, 64);
	public static Item itemMusicIngot = ItemFactory.create(Item.class, "Items_Music_iron", CreativeTabsLoader.tabItems, 64);
	public static Item itemMusicNugget = ItemFactory.create(Item.class, "Items_Music_littleiron", CreativeTabsLoader.tabItems, 64);
	public static Item itemMusicDiamond = ItemFactory.create(Item.class, "Items_Music_zuanshi", CreativeTabsLoader.tabItems, 64);
	public static Item itemMusicApple = ItemFactory.create(ItemMusicApple.class, "Items_Music_apple", CreativeTabsLoader.tabItems, 64);

	public static Item itemMusicSword = ItemFactory.create(ItemMusicSword.class, "Items_Music_sword", CreativeTabsLoader.tabItems, 1);
	public static Item itemMusicAxe = ItemFactory.create(ItemMusicAxe.class, "Items_Music_axe", CreativeTabsLoader.tabItems, 1);
	public static Item itemMusicPickaxe = ItemFactory.create(ItemMusicPickaxe.class, "Items_Music_pickaxe", CreativeTabsLoader.tabItems, 1);
	public static Item itemMusicShovel = ItemFactory.create(ItemMusicShovel.class, "Items_Music_shovel", CreativeTabsLoader.tabItems, 1);
	public static Item itemMusicHoe = ItemFactory.create(ItemMusicHoe.class, "Items_Music_hoe", CreativeTabsLoader.tabItems, 1);
	public static Item itemMusicBow = ItemFactory.create(ItemMusicBow.class, "music_bow", CreativeTabsLoader.tabItems, 1);
	public static Item itemMusicGoodtek = ItemFactory.create(ItemMusicGOODTEK.class, "items_music_goodtek", CreativeTabsLoader.tabItems, 1);
	public static Item itemFireKingSword = ItemFactory.create(ItemFireKingSword.class, "FireKingSword", CreativeTabsLoader.tabItems, 1);
	
	public static Item itemMusicHelmet = ItemFactory.create(ItemMusicHelmet.class, "Items_Music_hat", CreativeTabsLoader.tabItems, 1);
	public static Item itemMusicChestplate = ItemFactory.create(ItemMusicChestplate.class, "Items_Music_xiongjia", CreativeTabsLoader.tabItems, 1);
	public static Item itemsMusicLeggings = ItemFactory.create(ItemMusicLeggings.class, "Items_Music_pants", CreativeTabsLoader.tabItems, 1);
	public static Item itemMusicBoots = ItemFactory.create(ItemMusicBoots.class, "Items_Music_boots", CreativeTabsLoader.tabItems, 1);

	public static Item itemRubber = ItemFactory.create(Item.class, "blackball", CreativeTabsLoader.tabItems, 64);
	public static Item itemIronPlate = ItemFactory.create(Item.class, "tiepian", CreativeTabsLoader.tabItems, 64);
	public static Item itemDianluban = ItemFactory.create(Item.class, "dianluban", CreativeTabsLoader.tabItems, 64);
	public static Item itemDianron = ItemFactory.create(Item.class, "dianron", CreativeTabsLoader.tabItems, 64);
	public static Item itemShizhong = ItemFactory.create(Item.class, "shizhong", CreativeTabsLoader.tabItems, 64);
	public static Item itemDanpianji = ItemFactory.create(Item.class, "danpianji", CreativeTabsLoader.tabItems, 64);
	public static Item itemZhen = ItemFactory.create(Item.class, "zhen", CreativeTabsLoader.tabItems, 64);
	public static Item itemSanre = ItemFactory.create(Item.class, "sanre", CreativeTabsLoader.tabItems, 64);
	public static Item itemDianchi = ItemFactory.create(Item.class, "dianchi", CreativeTabsLoader.tabItems, 64);
	public static Item itemCore = ItemFactory.create(Item.class, "hexin", CreativeTabsLoader.tabItems, 64);

	public int id;

	public final static Item[] song = new ItemMKRecord[ArcMusic.allsong];
	{
		for (int i = 1; i <= ArcMusic.allsong - 1; i++) {
			int id = i;
			song[i] = new ItemMKRecord("mukaismod", ArcMusic.songs1[i], id) {};
			this.id = id;
		}
	}

	public int getint() {
		return this.id;
	}
	
	public ItemLoader() {
		allItems.forEach(i -> register(i));

		for (int i = 1; i <= ArcMusic.allsong - 1; i++) {
			register(song[i], "song" + i);
		}
	}

	public static void registerRenders() {
		allItems.forEach(i -> registerRender(i));
		for (int i = 1; i <= ArcMusic.allsong - 1; i++) {
			registerRender(song[i], "mukaimods:song");
		}
	}

	private static void register(Item item) {
		String[] arr = item.getUnlocalizedName().toString().split("\\.");
		ForgeRegistries.ITEMS.register(item.setRegistryName(arr[1]));
	}
	
	private void register(Item item, String name) {
		ForgeRegistries.ITEMS.register(item.setRegistryName(name));
	}

	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	protected static void registerRender(Item item, int id) {
		ModelLoader.setCustomModelResourceLocation(item, id, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void registerRender(Item item, String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
	}
	
	public static void addItem(Item i) {
		allItems.add(i);
	}

}