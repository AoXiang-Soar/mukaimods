package mukaimods.item;

import java.lang.reflect.Constructor;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemFactory {
	public static Item create(Class<? extends Item> cls, String unlocalizedName, @Nullable CreativeTabs tab,
			int maxStack) {
		try {
			Constructor<? extends Item> con = cls.getConstructor();
			Item i = con.newInstance();
			i.setUnlocalizedName(unlocalizedName);
			i.setCreativeTab(tab);
			i.setMaxStackSize(maxStack);
			ItemLoader.addItem(i);
			return i;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
