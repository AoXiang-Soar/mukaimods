package mukaimods.item.equipments;

import java.util.List;

import mukaimods.enchantment.EnchantmentLoader;
import mukaimods.sounds.SoundsMusicGame;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemMusicAxe extends ItemAxe {
	public static final Item.ToolMaterial Musiciron = EnumHelper.addToolMaterial("Musiciron", 3, 114514, 15.0F, 12.0F,
			22);
	// private static final Enchantment EnchantmentMusic = new
	// Enchantment_Music_Kill(1, 3);

	public ItemMusicAxe() {
		super(Musiciron, 15, -3);
	}

	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		stack.addEnchantment(Enchantments.SHARPNESS, 4);
		stack.addEnchantment(Enchantments.EFFICIENCY, 4);
		stack.addEnchantment(EnchantmentLoader.musickill, 3);

	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {

		tooltips.add(I18n.format("PVPMusicAxe"));

	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack itemstack = playerIn.getHeldItem(handIn);
		int duration = 80;
		int amplifier = 0;
		playerIn.addPotionEffect(new PotionEffect(MobEffects.GLOWING, duration, amplifier));
		playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, duration, amplifier));
		playerIn.getCooldownTracker().setCooldown(this, 5 * 20);
		worldIn.playSound((EntityPlayer) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsMusicGame.ice,
				SoundCategory.PLAYERS, 3.0F, 1.0F);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

	}

}
