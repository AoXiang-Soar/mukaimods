package mukaimods.createtab;

import mukaimods.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreateTabItems extends CreativeTabs {

	public CreateTabItems() {
		super("Mukaimods");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemLoader.itemIcon);
	}

}
