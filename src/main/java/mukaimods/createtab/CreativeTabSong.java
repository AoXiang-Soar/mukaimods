package mukaimods.createtab;

import mukaimods.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabSong extends CreativeTabs
{
	
	public CreativeTabSong()
    {
        super("Mukaismod Songs");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ItemLoader.itemVoidRecord);
    }
}