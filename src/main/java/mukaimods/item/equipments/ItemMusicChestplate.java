package mukaimods.item.equipments;

import mukaimods.item.ItemLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMusicChestplate extends ItemMusicArmor {

	public ItemMusicChestplate() {
		super(EntityEquipmentSlot.CHEST);
	}

	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		stack.addEnchantment(Enchantments.PROTECTION, 4);
		stack.addEnchantment(Enchantments.FIRE_PROTECTION, 3);

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

			int level = 1;
			int time = 200;
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, time, level));
			player.capabilities.allowFlying = true;
			player.isPlayerFullyAsleep();
		} else {
			if (!player.capabilities.isCreativeMode) {
				player.capabilities.isFlying = false;
				player.capabilities.allowFlying = false;
			}
		}
	}
}
