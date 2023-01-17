package mukaimods.item;

import java.math.BigDecimal;
import java.util.List;

import mukaimods.player.CapabilityLoader;
import mukaimods.player.IPTTCapability;
import mukaimods.player.PTTCapabilityList.Difficulty;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemScore extends Item {
	SoundEvent sound;
	private String SongNames = "SongNames";
	private String Lvs = "Lvs";
	private String Marks = "Marks";
	private String difficulty = "Difficulty";
	private NBTTagCompound itemNBTData;

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {
		itemNBTData = stack.getTagCompound();
		if (itemNBTData == null) {
			itemNBTData = new NBTTagCompound();
		}
		if (itemNBTData.hasKey(SongNames)) {
			tooltips.add("SongNames: " + I18n.format("songsname" + itemNBTData.getInteger(SongNames)));
		}
		if (itemNBTData.hasKey(difficulty)) {
			tooltips.add("Difficulty: " + itemNBTData.getString("Difficulty"));
		}
		if (itemNBTData.hasKey(Lvs)) {
			tooltips.add("Lvs: " + itemNBTData.getInteger(Lvs));
		}
		if (itemNBTData.hasKey(Marks)) {
			tooltips.add("Marks: " + itemNBTData.getInteger(Marks));
		}
		if (itemNBTData.hasKey("Assess")) {
			tooltips.add("Assess: " + itemNBTData.getString("Assess"));
		}
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack itemstack = playerIn.getHeldItem(handIn);
		NBTTagCompound itemNBTData = itemstack.getTagCompound();
		if (itemNBTData == null) {
			itemNBTData = new NBTTagCompound();
		}
		int songid = 0;
		if (itemNBTData.hasKey(SongNames)) {
			songid = itemNBTData.getInteger(SongNames);
		}
		if (itemNBTData.hasKey(Lvs) && itemNBTData.hasKey(difficulty)) {
			IPTTCapability ptts = playerIn.getCapability(CapabilityLoader.PTT, null);
			double oldptt = ptts.getptt();

			int score = itemNBTData.getInteger(Marks);
			double oneptt = getOncePTT(itemNBTData.getDouble("Lvs"), score);
			
			Difficulty dif;
			switch(itemNBTData.getString(difficulty)) {
			case "BYD":
				dif = Difficulty.BYD;
				break;
			case "FTR":
				dif = Difficulty.FTR;
				break;
			case "PRS":
				dif = Difficulty.PRS;
				break;
			case "PST":
				dif = Difficulty.PST;
				break;
			case "LAST_ETERNITY":
				dif = Difficulty.LAST_ETERNITY;
				break;
			default:
				dif = Difficulty.UNKNOWN;
				break;
			}

			ptts.update(songid, dif, oneptt, worldIn.getTotalWorldTime(), itemNBTData.getInteger(Marks) >= 9800000);

			double newptt = ptts.getptt();

			String n = String.valueOf(newptt);
			BigDecimal newplayerptt = new BigDecimal(n);
			newplayerptt = newplayerptt.setScale(2, BigDecimal.ROUND_HALF_UP);

			double change = 0;
			if (!worldIn.isRemote) {
				if (newptt > oldptt) {
					change = newptt - oldptt;
					String c = String.valueOf(change);
					BigDecimal playerchange = new BigDecimal(c);
					playerchange = playerchange.setScale(2, BigDecimal.ROUND_HALF_UP);
					playerIn.sendMessage(
							new TextComponentTranslation("ptt:" + newplayerptt + "(+" + playerchange + ")"));
				}
				if (newptt == oldptt) {
					playerIn.sendMessage(new TextComponentTranslation("ptt:" + newplayerptt));
				}
				if (newptt < oldptt) {
					change = oldptt - newptt;
					String c = String.valueOf(change);
					BigDecimal playerchange = new BigDecimal(c);
					playerchange = playerchange.setScale(2, BigDecimal.ROUND_HALF_UP);
					change = oldptt - newptt;
					playerIn.sendMessage(
							new TextComponentTranslation("ptt:" + newplayerptt + "(-" + playerchange + ")"));
				}

			}

		}
		worldIn.playSound((EntityPlayer) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, sound,//Sounds_Musicgame.PMmax,
				SoundCategory.PLAYERS, 3.0F, 1.0F);
		playerIn.getCooldownTracker().setCooldown(this, 3 * 20);
		itemstack.shrink(1);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
	
	// 数学的魅力！
	private double getOncePTT(double modifier, int score) {
		if (score >= 10000000)
			return modifier + 2;
		else if (score >= 9800000)
			return modifier + score / 200000 - 48;
		else
			return Math.max(0, modifier + (score - 8900000) / 300000);
	}
	
	void setMusic(SoundEvent sound) {
		this.sound = sound;
	}
}