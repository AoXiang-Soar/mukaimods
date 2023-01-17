package mukaimods.item.equipments;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.common.util.EnumHelper;

public class ItemMusicHoe extends ItemHoe {
	public static final Item.ToolMaterial Musiciron = EnumHelper.addToolMaterial("Musiciron", 3, 114514, 15.0F, 5.0F,
			22);

	public ItemMusicHoe() {
		super(Musiciron);
	}

}