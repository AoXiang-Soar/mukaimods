package mukaimods.item.equipments;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mukaimods.enchantment.EnchantmentLoader;
import mukaimods.sounds.SoundsMusicGame;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemMusicShovel extends ItemSpade {
	public static final Item.ToolMaterial Musiciron = EnumHelper.addToolMaterial("Musiciron", 3, 114514, 15.0F, 7.0F,
			22);

	public ItemMusicShovel() {
		super(Musiciron);
	}

	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		stack.addEnchantment(Enchantments.EFFICIENCY, 4);
		stack.addEnchantment(EnchantmentLoader.musickill, 3);

	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("PVPMusicShovel"));
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		worldIn.playSound((EntityPlayer) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsMusicGame.pa,
				SoundCategory.PLAYERS, 3.0F, 1.0F);
		playerIn.getCooldownTracker().setCooldown(this, 7 * 20);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				int duration = 12;
				int amplifier = 100;

				playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration, amplifier));
			}
		}, 1700);

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

	}
}