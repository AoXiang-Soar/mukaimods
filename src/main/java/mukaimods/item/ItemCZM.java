package mukaimods.item;

import mukaimods.Mukaimods;
import mukaimods.gui.container.GuiElementLoader;
import mukaimods.sounds.SoundsCZM;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemCZM extends Item {
	public ItemCZM() {
		super();
		this.setUnlocalizedName("czm");
		// this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}

	@Override

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			playerIn.sendMessage(new TextComponentTranslation("CZM is the god"));

			BlockPos pos = playerIn.getPosition();
			int id = GuiElementLoader.czm1;
			playerIn.openGui(Mukaimods.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
			worldIn.playSound((EntityPlayer) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsCZM.CZM,
					SoundCategory.PLAYERS, 3.0F, 1.0F);
			int duration = 200;
			int amplifier = 5;
			int a = 20;
			int c = 2;

			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, duration, amplifier));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.GLOWING, duration, amplifier));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, duration, amplifier));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, duration, amplifier));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, duration, amplifier));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, duration, amplifier));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, duration, amplifier));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, duration, amplifier));

			playerIn.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, a, c));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, a, c));

			for (Entity b : playerIn.world.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(
					playerIn.getPosition().add(-32, -32, -32), playerIn.getPosition().add(+32, +32, +32)))) {

				if (b instanceof EntityLivingBase) {

					((EntityLivingBase) b).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, a, c));
					((EntityLivingBase) b).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, a, c));
				}
			}

		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

	}

}
