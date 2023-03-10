package mukaimods.world;

import mukaimods.worldgen.WorldGeneratorMusicore;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGeneratorLoader {

    
    private static WorldGenerator WorldGeneratorMusicore = new WorldGeneratorMusicore();

    private BlockPos pos;

    public WorldGeneratorLoader()
    {
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event)
    {
        if (!event.getPos().equals(this.pos))
        {
            this.pos = event.getPos();
            WorldGeneratorMusicore.generate(event.getWorld(), event.getRand(), event.getPos());
        }
    }

}

