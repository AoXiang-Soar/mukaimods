package mukaimods.item.equipments;

import java.util.List;

import mukaimods.enchantment.EnchantmentLoader;
import mukaimods.sounds.SoundsDaisuke;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemMusicPickaxe extends ItemPickaxe {
	public static final Item.ToolMaterial Musiciron = EnumHelper.addToolMaterial("Musiciron", 3, 114514, 15.0F, 8.0F,
			22);

	public ItemMusicPickaxe() {
		super(Musiciron);
	}

	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		stack.addEnchantment(Enchantments.EFFICIENCY, 4);
		stack.addEnchantment(EnchantmentLoader.musickill, 3);
		stack.addEnchantment(Enchantments.FORTUNE, 2);

	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {

		tooltips.add(I18n.format("PVPMusicPickaxe"));

	}

	@Override

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		int duration = 15;
		int amplifier = 1;
		playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, duration, amplifier));
		playerIn.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, duration, amplifier));
		playerIn.getCooldownTracker().setCooldown(this, 3 * 20);
		worldIn.playSound((EntityPlayer) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsDaisuke.daisuke,
				SoundCategory.PLAYERS, 3.0F, 1.0F);

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

	}
}