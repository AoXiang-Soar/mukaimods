package mukaimods.createtab;

import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabsLoader {
	public static CreativeTabs tabItems;
	public static CreativeTabs tabSongs;

	public CreativeTabsLoader() {
		tabItems = new CreateTabItems();
		tabSongs = new CreativeTabSong();
	}
}
