package mukaimods.entity;


import mukaimods.player.EntityGodczm;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class EntityRenderLoader {
	 
	 @SuppressWarnings({"unchecked","rawtypes"})
	  
	 public EntityRenderLoader() {
              RenderingRegistry.registerEntityRenderingHandler(EntityGodczm.class, new EntityRenderFactory(EntityGodczm.EntityGodczmRender.class)); 
	    }
}
	 
