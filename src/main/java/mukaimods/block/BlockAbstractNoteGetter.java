package mukaimods.block;

import java.util.List;

import io.netty.buffer.Unpooled;
import mukaimods.Mukaimods;
import mukaimods.gui.container.GuiElementLoader;
import mukaimods.tileentity.TileEntityMakeMusic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

// 音律收集装置，具体类写成了内部类
public abstract class BlockAbstractNoteGetter extends BlockContainer {
	// 不同难度的描述不同，写成参数给具体类改
	protected String format;
	protected int seconds;
	public int musicswitch;
	public float musicsize;

	public BlockAbstractNoteGetter() {
		super(Material.GROUND);
		this.setSoundType(SoundType.WOOD);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMakeMusic();
	}

	public int getRenderType() {
		return 3;
	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format(format));
		tooltips.add(I18n.format("TickTimeMake"+seconds));
		tooltips.add(I18n.format("BlockMakeMusicUse1"));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (worldIn.isRemote) {
			return true;
		}
		playerIn.openGui(Mukaimods.instance, GuiElementLoader.Make_Music, worldIn, pos.getX(), pos.getY(), pos.getZ());

		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		TileEntity tileentity = worldIn.getTileEntity(pos);

		TileEntityMakeMusic tileEntity1 = (TileEntityMakeMusic) tileentity;
		ItemStackHandler pure = tileEntity1.musicinventory;
		ItemStackHandler song = tileEntity1.songrecord;
		int songid = tileEntity1.getsongid();
		ItemStack block = new ItemStack(this);
		Block.spawnAsEntity(worldIn, pos, block);
		Block.spawnAsEntity(worldIn, pos, song.getStackInSlot(0));

		for (int i = 0; i <= 30; i++) {
			if (pure.getStackInSlot(i) != null) {
				Block.spawnAsEntity(worldIn, pos, pure.getStackInSlot(i));
			}
		}
		for (EntityPlayerMP playermp : Mukaimods.mcserver.getPlayerList().getPlayers()) {
			PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
			packetbuffer.writeString("record");
			packetbuffer.writeString("mukaimods:" + songid);
			playermp.connection.sendPacket(new SPacketCustomPayload("MC|StopSound", packetbuffer));

		}
		super.breakBlock(worldIn, pos, state);

	}
	public static class BlockBYDNoter extends BlockAbstractNoteGetter {
		public BlockBYDNoter() {
			super();
			this.format = "BlockMakeMusicTypeBYD";
			this.seconds = 5;
		}
	}
	public static class BlockFTRNoter extends BlockAbstractNoteGetter {
		public BlockFTRNoter() {
			super();
			this.format = "BlockMakeMusicTypeFTR";
			this.seconds = 10;
		}
	}
	public static class BlockPRSNoter extends BlockAbstractNoteGetter {
		public BlockPRSNoter() {
			super();
			this.format = "BlockMakeMusicTypePRS";
			this.seconds = 15;
		}
	}
	public static class BlockPSTNoter extends BlockAbstractNoteGetter {
		public BlockPSTNoter() {
			super();
			this.format = "BlockMakeMusicTypePST";
			this.seconds = 20;
		}
	}

}
