package mukaimods.internet;

import io.netty.buffer.ByteBuf;
import mukaimods.tileentity.TileEntityMakeMusic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageMakeMusic implements IMessage {

	BlockPos pos = BlockPos.ORIGIN;
	int dimension;
	int songid;
	float musicsize;
	// int musictime;
	int loop;
	int lock;
	int timeswitch;
	int makeloop;

	public MessageMakeMusic(BlockPos pos, int dim, int songid, float musicsize, int loop, int lock, int timeswitch,
			int makeloop) {
		this.pos = pos;

		this.dimension = dim;
		this.songid = songid;
		this.musicsize = musicsize;

		this.loop = loop;
		this.lock = lock;
		this.timeswitch = timeswitch;
		this.makeloop = makeloop;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());

		buf.writeInt(this.dimension);
		buf.writeInt(this.songid);
		buf.writeFloat(this.musicsize);

		buf.writeInt(this.loop);
		buf.writeInt(this.lock);
		buf.writeInt(this.timeswitch);
		buf.writeInt(this.makeloop);
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

		this.loop = buf.readInt();
		this.lock = buf.readInt();
		this.timeswitch = buf.readInt();
		this.makeloop = buf.readInt();

	}

	public static class MessageSwitchInitiativeSkillHandler implements IMessageHandler<MessageMakeMusic, IMessage> {
		@Override
		public IMessage onMessage(MessageMakeMusic msg, MessageContext ctx) {
			TileEntityMakeMusic makemusic = (TileEntityMakeMusic) FMLCommonHandler.instance()
					.getMinecraftServerInstance().getWorld(msg.dimension).getTileEntity(msg.pos);
		//	World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(msg.dimension);
/*
			world.playSound((EntityPlayer) null, msg.pos.getX(), msg.pos.getY(), msg.pos.getZ(),
					ArcMusic.songs1[msg.songid], SoundCategory.RECORDS, msg.musicsize, 1);

		
			world.playSound(msg.pos.getX(), msg.pos.getY(), msg.pos.getZ(), ArcMusic.songs1[msg.songid],
					SoundCategory.RECORDS, msg.musicsize, 1, false);
*/
			int recorddamage;
			ItemStack record = makemusic.songrecord.getStackInSlot(0);
			recorddamage = record.getItemDamage();
			if (recorddamage < 100) {
				record.setItemDamage(recorddamage + 20);
				makemusic.settimeswitch(msg.timeswitch);
				makemusic.setmusicsize(msg.musicsize);
				makemusic.setloop(msg.loop);
				makemusic.setlock(msg.lock);
				makemusic.setmakeloop(msg.makeloop);
				makemusic.setplaysong(1);
			}

			if (recorddamage == 100) {
				record.setItemDamage(100);
			}

			return null;

		}

	}

	public MessageMakeMusic() {
	}

}
