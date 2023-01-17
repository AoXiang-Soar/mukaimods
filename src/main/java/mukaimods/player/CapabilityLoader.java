package mukaimods.player;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityLoader {

	@CapabilityInject(IPTTCapability.class)
	public static Capability<IPTTCapability> PTT;

	@SuppressWarnings("deprecation")
	public CapabilityLoader() {
		CapabilityManager.INSTANCE.register(IPTTCapability.class, new PTTCapabilityList.PTTCapabilityStorage(),
				PTTCapabilityList.PTTCapabilityHandler.class);
		MinecraftForge.EVENT_BUS.register(CapabilityLoader.class);
	}

}