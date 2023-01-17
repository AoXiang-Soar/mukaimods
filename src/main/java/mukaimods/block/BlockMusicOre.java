package mukaimods.block;

import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockMusicOre extends Block {
	public BlockMusicOre() {
		super(Material.GROUND);
		this.setSoundType(SoundType.STONE);
	}

	@Nonnull
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return mukaimods.item.ItemLoader.itemGemMusic;
	}

	@Override
	public int quantityDropped(Random random) {
		return 2;
	}

	@SubscribeEvent
	public static void onOreGen(OreGenEvent.Post event) {
		// WIP
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (fortune > 0) {
			int bonusFactor = Math.max(random.nextInt(fortune + 2) - 1, 0);
			return this.quantityDropped(random) * (bonusFactor + 2);
		} else {
			return this.quantityDropped(random);
		}
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess blockAccess, BlockPos pos, int fortune) {
		return 1;
	}
}
