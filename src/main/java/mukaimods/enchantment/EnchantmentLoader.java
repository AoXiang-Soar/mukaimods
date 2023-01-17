package mukaimods.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EnchantmentLoader {
	
	public static Enchantment musickill=new EnchantmentMusicKill(Enchantment.Rarity.RARE, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND});
	public static Enchantment musichit=new EnchantmentMusicHit(Enchantment.Rarity.UNCOMMON, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND});
	public EnchantmentLoader() {
		register( new ResourceLocation("musickill"), musickill);
		register( new ResourceLocation("musichit"), musichit);
	}
	
	private void register(ResourceLocation key,Enchantment value) {
		ForgeRegistries.ENCHANTMENTS.register(value.setRegistryName(key));
	}
	
}
