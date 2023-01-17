package mukaimods.sounds;

import mukaimods.Mukaimods;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsMusicGame {
	public static final SoundEvent gongfu = new SoundEvent(new ResourceLocation("mukaimods", "gongfu"));
	public static final SoundEvent pa = new SoundEvent(new ResourceLocation("mukaimods", "pa"));
	public static final SoundEvent suomi = new SoundEvent(new ResourceLocation("mukaimods", "suomi"));
	public static final SoundEvent gkd = new SoundEvent(new ResourceLocation("mukaimods", "gkd"));
	public static final SoundEvent ice = new SoundEvent(new ResourceLocation("mukaimods", "ice"));
	public static final SoundEvent good = new SoundEvent(new ResourceLocation("mukaimods", "good"));
	public static final SoundEvent firekingstart = new SoundEvent(new ResourceLocation("mukaimods", "firekingstart"));
	public static final SoundEvent firekingkill = new SoundEvent(new ResourceLocation("mukaimods", "firekingkill"));

	public static final SoundEvent PM = new SoundEvent(new ResourceLocation("mukaimods", "pm"));
	public static final SoundEvent PMmax = new SoundEvent(new ResourceLocation("mukaimods", "pmmax"));
	public static final SoundEvent Complete = new SoundEvent(new ResourceLocation("mukaimods", "trackclear"));
	public static final SoundEvent Full = new SoundEvent(new ResourceLocation("mukaimods", "trackfull"));
	public static final SoundEvent Lost = new SoundEvent(new ResourceLocation("mukaimods", "tracklost"));

	public SoundsMusicGame() {
		register(gongfu, new ResourceLocation(Mukaimods.MODID, "gongfu"));
		register(pa, new ResourceLocation(Mukaimods.MODID, "pa"));
		register(suomi, new ResourceLocation(Mukaimods.MODID, "suomi"));
		register(gkd, new ResourceLocation(Mukaimods.MODID, "gkd"));
		register(ice, new ResourceLocation(Mukaimods.MODID, "ice"));
		register(good, new ResourceLocation(Mukaimods.MODID, "good"));
		register(firekingstart, new ResourceLocation(Mukaimods.MODID, "firekingstart"));
		register(firekingkill, new ResourceLocation(Mukaimods.MODID, "firekingkill"));

		register(PM, new ResourceLocation(Mukaimods.MODID, "pm"));
		register(PMmax, new ResourceLocation(Mukaimods.MODID, "pmmax"));
		register(Complete, new ResourceLocation(Mukaimods.MODID, "trackclear"));
		register(Full, new ResourceLocation(Mukaimods.MODID, "trackfull"));
		register(Lost, new ResourceLocation(Mukaimods.MODID, "tracklost"));
		
		for (Integer i = 0; i < ArcMusic.songs1.length; i++) {
			register(ArcMusic.songs1[i], new ResourceLocation(Mukaimods.MODID, i.toString()));
		}
		for (Integer i = 0; i < ArcMusic.songsp.length; i++) {
			register(ArcMusic.songsp[i], new ResourceLocation(Mukaimods.MODID, i+"p"));
		}
	}

	private static void register(SoundEvent sound, ResourceLocation resourceLocation) {
		ForgeRegistries.SOUND_EVENTS.register(sound.setRegistryName(resourceLocation));
	}
}
