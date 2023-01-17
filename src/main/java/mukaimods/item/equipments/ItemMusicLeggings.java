package mukaimods.item.equipments;

import mukaimods.item.ItemLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMusicLeggings extends ItemMusicArmor {

	public ItemMusicLeggings() {
		super(EntityEquipmentSlot.LEGS);
	}

	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		stack.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 4);
		stack.addEnchantment(Enchantments.THORNS, 3);

	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack item) {

		ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		ItemStack pants = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack hats = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack xiongjia = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		if (boots.getItem() == ItemLoader.itemMusicBoots && pants.getItem() == ItemLoader.itemsMusicLeggings
				&& hats.getItem() == ItemLoader.itemMusicHelmet
				&& xiongjia.getItem() == ItemLoader.itemMusicChestplate) {
			int level = 3;
			int time = 200;
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, time, level));
		}
	}
}
