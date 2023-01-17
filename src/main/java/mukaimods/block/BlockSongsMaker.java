package mukaimods.block;

import java.util.List;

import mukaimods.Mukaimods;
import mukaimods.gui.container.GuiElementLoader;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSongsMaker extends Block {
	public BlockSongsMaker() {
		super(Material.GROUND);
		this.setSoundType(SoundType.WOOD);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos pos1 = player.getPosition();
		int id = GuiElementLoader.Copy_Music;
		player.openGui(Mukaimods.instance, id, world, pos1.getX(), pos1.getY(), pos1.getZ());
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {

		tooltips.add(I18n.format("BlockMakeSongs"));

	}
}
