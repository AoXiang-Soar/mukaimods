package mukaimods.item;

import java.util.List;

import mukaimods.sounds.SoundsMusicGame;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemFireKingSword extends ItemSword {

	// private String Kill = "Kill";

	// private NBTTagCompound itemNBTData;

	public static final Item.ToolMaterial Fire = EnumHelper.addToolMaterial("Fire", 0, -1, 0.0F, 36.0F, 50);

	public ItemFireKingSword() {
		super(Fire);

	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		worldIn.playSound((EntityPlayer) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ,
				SoundsMusicGame.firekingstart, SoundCategory.PLAYERS, 10.0F, 1.0F);
	 	int duration = 20*30; 
    	int amplifier = 0;
    	playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, duration, amplifier));
     
      	playerIn.getCooldownTracker().setCooldown(this, 3*20);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (!entity.world.isRemote && entity instanceof EntityPlayer) {
			EntityPlayer victim = (EntityPlayer) entity;
			if (victim.capabilities.isCreativeMode && !victim.isDead && victim.getHealth() > 0) {
				victim.getCombatTracker().trackDamage(new DamageSource("tqlsdlwsl"), victim.getHealth(),
						victim.getHealth());
				victim.setHealth(0);
				victim.onDeath(new EntityDamageSource("tqlsdlwsl", player));
				// player.addStat(new LudicrousAchievement("creative_kill", -6, -6, new
				// ItemStack(Items.skull, 1, 3), null).setSpecial(), 1);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase attacker) {
		if (attacker.world.isRemote) {

			attacker.world.playSound((EntityPlayer) attacker, attacker.posX, attacker.posY, attacker.posZ,
					SoundsMusicGame.firekingkill, SoundCategory.PLAYERS, 10.0F, 1.0F);
			if (attacker.world.isRemote)
				return true;
			if (victim instanceof EntityPlayer) {

				victim.attackEntityFrom(new DamageSource("tqlsdlwsl").setFireDamage().setDamageBypassesArmor(), 4.0F);

				victim.getCombatTracker().trackDamage(new DamageSource("tqlsdlwsl"), victim.getHealth(),
						victim.getHealth());
				victim.setHealth(0);

			}
		}
		return true;
	}

	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		stack.addEnchantment(Enchantments.FIRE_ASPECT, 10);

	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {

		tooltips.add(I18n.format("TheFireKingSwod"));
		tooltips.add(I18n.format("TheFireKingSwodUse1"));
		tooltips.add(I18n.format("TheFireKingSwodUse2"));

		// tooltips.add(I18n.format("Each attack will further release power!"));
		/*
		 * itemNBTData = stack.getTagCompound(); if (itemNBTData == null) { itemNBTData
		 * = new NBTTagCompound(); }
		 * 
		 * 
		 * if (itemNBTData.hasKey(Kill)) { tooltips.add(I18n.format("( " + (8 -
		 * itemNBTData.getInteger(Kill)) + " time left! )")); }else {
		 * tooltips.add(I18n.format("( 8 time left! )")); }
		 */
	}
}