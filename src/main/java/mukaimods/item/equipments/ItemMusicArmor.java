package mukaimods.item.equipments;

import mukaimods.Mukaimods;
import mukaimods.sounds.Archit;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemMusicArmor extends ItemArmor {
	public static final ItemArmor.ArmorMaterial musiciron = EnumHelper.addArmorMaterial("musiciron",
			Mukaimods.MODID + ":" + "musiciron", -1, new int[] { 3, 6, 8, 3 }, 15, Archit.Archit, 5);

	public ItemMusicArmor(EntityEquipmentSlot armorType) {
		super(musiciron, musiciron.ordinal(), armorType);
	}

}
