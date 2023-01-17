package mukaimods.item.equipments;

import mukaimods.enchantment.EnchantmentLoader;
import mukaimods.sounds.SoundsMusicGame;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.ForgeEventFactory;
import java.util.*;

public class ItemMusicBow extends ItemBow {
	public static final Item.ToolMaterial Musiciron = EnumHelper.addToolMaterial("Musiciron", 3, 114514, 15.0F, 5.0F,
			22);

	public ItemMusicBow() {
		this.setMaxDamage(-1);
	}

	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		stack.addEnchantment(Enchantments.POWER, 5);
		stack.addEnchantment(EnchantmentLoader.musickill, 3);

	}

	@Override
	public void setDamage(ItemStack stack, int damage) {

		super.setDamage(stack, -1);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (this.isInCreativeTab(tab)) {
			ItemStack istack = new ItemStack(this);
			istack.addEnchantment(Enchantments.POWER, 5);
			list.add(istack);
		}
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if (count == 1) {
			fire(stack, player.world, player, 0);
		}
	}

	public void fire(ItemStack stack, World world, EntityLivingBase player, int useCount) {
		int max = getMaxItemUseDuration(stack);
		float maxf = (float) max;
		int j = max - useCount;

		float f = j / maxf;
		f = (f * f + f * 2.0F) / 3.0F;

		if (f < 0.1) {
			return;
		}

		if (f > 1.0) {
			f = 1.0F;
		}

		if (player instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) player;
			boolean flag = entityplayer.capabilities.isCreativeMode
					|| EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = this.findAmmo(entityplayer);

			int i = this.getMaxItemUseDuration(stack) - useCount;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, entityplayer, i,
					!itemstack.isEmpty() || flag);
			if (i < 0)
				return;

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(Items.ARROW);
				}

				float f1 = getArrowVelocity(i);

				if ((double) f1 >= 0.1D) {
					boolean flag1 = entityplayer.capabilities.isCreativeMode
							|| (itemstack.getItem() instanceof ItemArrow
									&& ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));
					if (!world.isRemote) {
						ItemArrow itemarrow = (ItemArrow) (itemstack.getItem() instanceof ItemArrow
								? itemstack.getItem()
								: Items.ARROW);
						EntityArrow entityarrow = itemarrow.createArrow(world, itemstack, entityplayer);
						entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F,
								f1 * 3.0F, 1.0F);
						if (f1 == 1.0F) {
							entityarrow.setIsCritical(true);
						}

						int j1 = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

						if (j1 > 0) {
							Random random = new Random();
							int x = random.nextInt(15);

							entityarrow.setDamage(entityarrow.getDamage() + x + (double) j1 * 0.5D + 0.5D);
						}

						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

						if (k > 0) {
							entityarrow.setKnockbackStrength(k);
						}

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
							entityarrow.setFire(500);
						}

						stack.damageItem(1, entityplayer);

						if (flag1 || entityplayer.capabilities.isCreativeMode
								&& (itemstack.getItem() == Items.SPECTRAL_ARROW
										|| itemstack.getItem() == Items.TIPPED_ARROW)) {
							entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
						}
						if (!world.isRemote) {
							world.spawnEntity(entityarrow);
						}
					}
				}
			}
		}
	}

	public ItemStack findAmmo(EntityPlayer player) {
		if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		} else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isArrow(itemstack)) {
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 34;
	}
	// ��������cdʱ��

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("PVPMusicBow"));
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		ItemStack stack = player.getHeldItem(hand);

		ActionResult<ItemStack> event = ForgeEventFactory.onArrowNock(stack, world, player, hand, true);
		world.playSound((EntityPlayer) player, player.posX, player.posY, player.posZ, SoundsMusicGame.suomi,
				SoundCategory.PLAYERS, 3.0F, 1.0F);

		// player.getCooldownTracker().setCooldown(this, 90);

		if (event != null) {
			return event;
		}

		player.setActiveHand(hand);

		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public int getItemEnchantability() {
		return 1;
	}

}
