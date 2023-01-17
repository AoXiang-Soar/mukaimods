package mukaimods.block;

import java.util.List;

import mukaimods.Mukaimods;
import mukaimods.gui.container.GuiElementLoader;
import mukaimods.tileentity.TileEntityPlaysongs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

// 自动打歌机
public class BlockAutoSongPlayer extends BlockContainer {

	public BlockAutoSongPlayer() {
		super(Material.GROUND);
		this.setSoundType(SoundType.WOOD);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPlaysongs();
	}

	public int getRenderType() {
		return 3;
	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {

		tooltips.add(I18n.format("BlockPlayMusic"));
		tooltips.add(I18n.format("BlockPlayMusicUse1"));
		tooltips.add(I18n.format("BlockPlayMusicUse2"));

	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		}
		playerIn.openGui(Mukaimods.instance, GuiElementLoader.Play_Music, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		TileEntity tileentity = worldIn.getTileEntity(pos);
		TileEntityPlaysongs tileEntity1 = (TileEntityPlaysongs) tileentity;
		ItemStackHandler pure = tileEntity1.pureinventory;
		ItemStackHandler song = tileEntity1.songrecord;
		ItemStackHandler result = tileEntity1.result;
		ItemStack block = new ItemStack(BlockLoader.blockAutoSongPlayer);
		Block.spawnAsEntity(worldIn, pos, block);
		Block.spawnAsEntity(worldIn, pos, song.getStackInSlot(0));
		Block.spawnAsEntity(worldIn, pos, result.getStackInSlot(0));
		for (int i = 0; i < pure.getSlots(); i++) {
			if (pure.getStackInSlot(i) != null) {
				Block.spawnAsEntity(worldIn, pos, pure.getStackInSlot(i));
			}
		}

		super.breakBlock(worldIn, pos, state);

	}

}
