package mukaimods.item;

import mukaimods.createtab.CreativeTabsLoader;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class ItemMKRecord extends ItemRecord {
	public int id;

	public ItemMKRecord(String modid, SoundEvent soundIn, int id) {
		super(modid, soundIn);
		this.id = id;
		this.setCreativeTab(CreativeTabsLoader.tabSongs).setMaxDamage(100).setUnlocalizedName("song" + id);
	}

	public int getint() {
		return this.id;
	}

}
