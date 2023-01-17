package mukaimods.internet;

import io.netty.buffer.ByteBuf;
import mukaimods.sounds.ArcMusic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayMusic implements IMessage {

	BlockPos pos = BlockPos.ORIGIN;
	int dimension;
	int songid;
	float musicsize;

	public MessagePlayMusic(BlockPos pos, int dim, int songid, float musicsize) {
		this.pos = pos;
		this.dimension = dim;
		this.songid = songid;
		this.musicsize = musicsize;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());

		buf.writeInt(this.dimension);
		buf.writeInt(this.songid);
		buf.writeFloat(this.musicsize);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		final int x = buf.readInt();
		final int y = buf.readInt();
		final int z = buf.readInt();
		this.pos = new BlockPos(x, y, z);

		dimension = buf.readInt();
		this.songid = buf.readInt();
		this.musicsize = buf.readFloat();
	}

	public static class MessageSwitchInitiativeSkillHandler implements IMessageHandler<MessagePlayMusic, IMessage> {
		@Override
		public IMessage onMessage(MessagePlayMusic msg, MessageContext ctx) {
			World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(msg.dimension);

			world.playSound((EntityPlayer) null, msg.pos.getX(), msg.pos.getY(), msg.pos.getZ(),
					ArcMusic.songs1[msg.songid], SoundCategory.RECORDS, msg.musicsize, 1);
			return null;
		}
	}
	
	public MessagePlayMusic() {
		
	}
}