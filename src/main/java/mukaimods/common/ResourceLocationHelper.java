package mukaimods.common;

import mukaimods.Mukaimods;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {
	public static ResourceLocation prefix(String path) {
		return new ResourceLocation(Mukaimods.MODID, path);
	}
}