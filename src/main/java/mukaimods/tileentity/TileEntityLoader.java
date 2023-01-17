package mukaimods.tileentity;

import mukaimods.Mukaimods;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
	public TileEntityLoader()
    {
		// registerTileEntity(TileEntityMetalFurnace.class, "MetalFurnace");
		registerTileEntity(TileEntityPlaysongs.class, "Playsongs");
		registerTileEntity(TileEntityMakeMusic.class, "MakeMusic");
		registerTileEntity(TileEntityNumberGame.class, "NumberGame");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
    {
        
        
    	// GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation("mukaimods", "TileEntityMetalFurnace"));
    	GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(Mukaimods.MODID, id));
    	//GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(Mukaimods.MODID, id));
    }
}
