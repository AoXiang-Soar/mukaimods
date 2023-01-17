package mukaimods.internet;

import io.netty.buffer.ByteBuf;
import mukaimods.player.PTTCapabilityList.Difficulty;
import mukaimods.tileentity.TileEntityPlaysongs;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSetSongsLv implements IMessage {
	int lv;
	int d;
	BlockPos pos = BlockPos.ORIGIN;
	Difficulty difficulty;

	public MessageSetSongsLv(BlockPos pos, int d, int lv, Difficulty difficulty) {
		this.pos = pos;
		this.d = d;
		this.lv = lv;
		this.difficulty = difficulty;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());
		buf.writeInt(this.d);
		buf.writeInt(this.lv);
		buf.writeInt(this.difficulty.id);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		final int x = buf.readInt();
		final int y = buf.readInt();
		final int z = buf.readInt();
		this.pos = new BlockPos(x, y, z);
		this.d = buf.readInt();
		this.lv = buf.readInt();
		this.difficulty = Difficulty.getDifficulty(buf.readInt());
	}

	public static class MessageSwitchInitiativeSkillHandler implements IMessageHandler<MessageSetSongsLv, IMessage> {
		@Override
		public IMessage onMessage(MessageSetSongsLv msg, MessageContext ctx) {
			TileEntityPlaysongs playsongs = (TileEntityPlaysongs) FMLCommonHandler.instance()
					.getMinecraftServerInstance().getWorld(msg.d).getTileEntity(msg.pos);
			if (playsongs.getlock() == 0) {
				playsongs.setlv(msg.lv);
				playsongs.setDifficulty(msg.difficulty);
			}
			return null;

		}

	}

	public MessageSetSongsLv() {
	}

}
