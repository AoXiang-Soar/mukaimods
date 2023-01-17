package mukaimods.item.equipments;

import java.util.List;
import java.util.Random;

import mukaimods.sounds.ArcMusic;
import mukaimods.sounds.ArcSongsTime;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemMusicApple extends ItemFood {
	int a;

	public ItemMusicApple() {
		super(4, 10F, false);
		this.setAlwaysEdible();
	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {

		tooltips.add(I18n.format("PVPMusicApple"));

	}

	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {

		Random lvs = new Random();
		int lv = lvs.nextInt(5);
		String songtime = ArcSongsTime.songtime[a];
		String[] time = songtime.split(":");
		int min1 = Integer.parseInt(time[0]);
		int sec = Integer.parseInt(time[1]);
		int bufftime = (min1 * 60 + sec) * 20;
		if (!worldIn.isRemote) {
			Random arc = new Random();
			a = arc.nextInt(230);
			player.sendMessage(new TextComponentTranslation("info_play_song"));
			player.sendMessage(new TextComponentTranslation("songsname" + a));
			player.playSound(ArcMusic.songs1[a], 10.0F, 1.0F);
			worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, ArcMusic.songs1[a],
					SoundCategory.PLAYERS, 10.0F, 1.0F);
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, bufftime, lvs.nextInt(5)));
			player.addPotionEffect(new PotionEffect(MobEffects.HASTE, bufftime, lvs.nextInt(5)));
			player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, bufftime, lvs.nextInt(5)));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, bufftime, lvs.nextInt(5)));
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, bufftime, lvs.nextInt(5)));
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, bufftime, lv));
			//player.getCooldownTracker().setCooldown(this, bufftime);
			player.addExperience(2000);
		}
		EntityPlayerMP playermp = (EntityPlayerMP) player;
		playermp.connection.sendPacket(new SPacketCustomSound("mukaimods:mukaimods:" + a, SoundCategory.PLAYERS,
				player.posX, player.posY, player.posZ, 10.0F, 1.0F));

		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, bufftime, lvs.nextInt(5)));
		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, bufftime, lvs.nextInt(5)));
		player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, bufftime, lvs.nextInt(5)));
		player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, bufftime, lvs.nextInt(5)));
		player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, bufftime, lvs.nextInt(5)));
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, bufftime, lv));
		player.getCooldownTracker().setCooldown(this, bufftime);
		player.addExperience(2000);
		worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, ArcMusic.songs1[a],
				SoundCategory.PLAYERS, 10.0F, 1.0F);
		worldIn.playSound((EntityPlayer) player, player.posX, player.posY, player.posZ, ArcMusic.songs1[a],
				SoundCategory.PLAYERS, 10.0F, 1.0F);
		

		player.playSound(ArcMusic.songs1[a], 10.0F, 1.0F);
		super.onFoodEaten(stack, worldIn, player);
	}

}
