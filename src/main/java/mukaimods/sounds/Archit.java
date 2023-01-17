package mukaimods.sounds;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class Archit {


	public static final SoundEvent Archit = new SoundEvent(new ResourceLocation("mukaimods", "mukaimods:archit"));

	
	@SubscribeEvent
	public static void onSoundEvenrRegistration(RegistryEvent.Register<SoundEvent> event) {
	    event.getRegistry().register(Archit.setRegistryName(new ResourceLocation("mukaimods", "mukaimods:archit")));
	   
	}
}
