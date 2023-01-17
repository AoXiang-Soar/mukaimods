package mukaimods.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ArcMusic {

	public final static int allsong = 231;
	public final static int all = 231;
	public final static SoundEvent[] songs1 = new SoundEvent[allsong];
	public final static SoundEvent[] songsp = new SoundEvent[allsong];
	
	 static  {
		
		for(Integer i = 0;i <= allsong-1;i++){
			songs1[i] = new SoundEvent(new ResourceLocation("mukaimods", i.toString()));
			songsp[i] = new SoundEvent(new ResourceLocation("mukaimods", i+"p"));
			
		}
	   
	}
	
}
