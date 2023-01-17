package mukaimods.item;

import java.util.List;

import mukaimods.sounds.Archit;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemPureMax extends Item {

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		int duration = 72;
		int amplifier = 2;
		int time = 1;
		int lv = 1;
		playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, time, lv));
		playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, duration, amplifier));
		worldIn.playSound((EntityPlayer) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, Archit.Archit,
				SoundCategory.PLAYERS, 3.0F, 1.0F);

		itemstack.shrink(1);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("ItemMusicPureMax"));
	}
}
