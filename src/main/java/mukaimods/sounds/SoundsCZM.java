package mukaimods.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoundsCZM {
public static final SoundEvent CZM = new SoundEvent(new ResourceLocation("mukaimods", "mukaimods:czm"));

	
	@SubscribeEvent
	public static void onSoundEvenrRegistration(RegistryEvent.Register<SoundEvent> event) {
	    event.getRegistry().register(CZM.setRegistryName(new ResourceLocation("mukaimods", "mukaimods:czm")));
	   
	}
}