package mukaimods.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoundsDaisuke {
public static final SoundEvent daisuke = new SoundEvent(new ResourceLocation("mukaimods", "mukaimods:daisuke"));

	
	@SubscribeEvent
	public static void onSoundEvenrRegistration(RegistryEvent.Register<SoundEvent> event) {
	    event.getRegistry().register(daisuke.setRegistryName(new ResourceLocation("mukaimods", "mukaimods:daisuke")));
	   
	}
}

