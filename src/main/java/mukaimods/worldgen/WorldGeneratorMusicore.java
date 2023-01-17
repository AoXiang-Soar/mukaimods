package mukaimods.worldgen;

import java.util.Random;

import mukaimods.block.BlockLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class WorldGeneratorMusicore extends WorldGenerator {
	private final WorldGenerator musicoreGenerator = new WorldGenMinable(BlockLoader.blockMusicOre.getDefaultState(),
			16);

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			for (int i = 0; i < 4; ++i) {

				int posX = pos.getX() + rand.nextInt(16);
				int posY = 16 + rand.nextInt(16);
				int posZ = pos.getZ() + rand.nextInt(16);
				BlockPos blockpos = new BlockPos(posX, posY, posZ);
				musicoreGenerator.generate(world, rand, blockpos);

			}

		}
		return true;

	}

}