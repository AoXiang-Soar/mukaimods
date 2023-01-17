package mukaimods;

import mukaimods.common.Common;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * @author Mu_kai
 */
@Mod(modid = Mukaimods.MODID, name = Mukaimods.NAME, version = Mukaimods.VERSION, acceptedMinecraftVersions = "1.12.2")
public class Mukaimods {
	public static final String MODID = "mukaimods";
	public static final String NAME = "mukai";
	public static final String VERSION = "0.4.4";

	@Instance(Mukaimods.MODID)
	public static Mukaimods instance;

	@SidedProxy(clientSide = "mukaimods.client.Client", serverSide = "mukaimods.common.Common")
	public static Common proxy;
	public static MinecraftServer mcserver=null;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void OnStart(FMLServerStartingEvent e) {
		mcserver = e.getServer();
		proxy.onStart(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(MODID, name);
	}

}
