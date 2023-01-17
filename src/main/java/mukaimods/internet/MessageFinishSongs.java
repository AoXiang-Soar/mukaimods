package mukaimods.internet;

import java.util.Timer;
import java.util.TimerTask;

import io.netty.buffer.ByteBuf;
import mukaimods.item.ItemLoader;
import mukaimods.player.PTTCapabilityList.Difficulty;
import mukaimods.tileentity.TileEntityPlaysongs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.ItemStackHandler;

public class MessageFinishSongs implements IMessage {
	int timeswitch;
	int makesec;
	int dimension;
	BlockPos pos = BlockPos.ORIGIN;
	int makemark;
	int marktype;
	int songid;
	Double lvssss;
	int note0;
	int note1;
	int note2;
	int note3;
	int note4;
	Difficulty difficulty;

	public MessageFinishSongs(BlockPos pos, int dim, int timeswitch, int makesec, int makemark, int marktype,
			int songid, Double lvssss, int note0, int note1, int note2, int note3, int note4, Difficulty difficulty) {
		// this(te.getPos(), te.getWorld().provider.getDimension());
		this.pos = pos;
		this.dimension = dim;
		this.timeswitch = timeswitch;
		this.makesec = makesec;
		this.makemark = makemark;
		this.marktype = marktype;
		this.songid = songid;
		this.lvssss = lvssss;
		this.note0 = note0;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.difficulty = difficulty;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());
		buf.writeInt(this.dimension);
		buf.writeInt(this.timeswitch);
		buf.writeInt(this.makesec);
		buf.writeInt(this.makemark);
		buf.writeInt(this.marktype);
		buf.writeInt(this.songid);
		buf.writeDouble(this.lvssss);
		buf.writeInt(this.note0);
		buf.writeInt(this.note1);
		buf.writeInt(this.note2);
		buf.writeInt(this.note3);
		buf.writeInt(this.note4);
		buf.writeInt(this.difficulty.id);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		final int x = buf.readInt();
		final int y = buf.readInt();
		final int z = buf.readInt();
		this.pos = new BlockPos(x, y, z);
		dimension = buf.readInt();
		this.timeswitch = buf.readInt();
		this.makesec = buf.readInt();
		this.makemark = buf.readInt();
		this.marktype = buf.readInt();
		this.songid = buf.readInt();
		this.lvssss = buf.readDouble();
		this.note0 = buf.readInt();
		this.note1 = buf.readInt();
		this.note2 = buf.readInt();
		this.note3 = buf.readInt();
		this.note4 = buf.readInt();
		this.difficulty = Difficulty.getDifficulty(buf.readInt());
	}

	public static class MessageSwitchInitiativeSkillHandler implements IMessageHandler<MessageFinishSongs, IMessage> {
		@Override
		public IMessage onMessage(MessageFinishSongs msg, MessageContext ctx) {
			TileEntityPlaysongs playsongs = (TileEntityPlaysongs) FMLCommonHandler.instance()
					.getMinecraftServerInstance().getWorld(msg.dimension).getTileEntity(msg.pos);

			int notes0 = msg.note0;
			int notes1 = msg.note1;
			int notes2 = msg.note2;
			int notes3 = msg.note3;
			int notes4 = msg.note4;
			int pureall = 0;
			int farall = 0;
			int lostall = 0;
			int puremaxall = 0;
			int musicall = 0;
			ItemStackHandler delitem = playsongs.pureinventory;
			int pures[] = new int[delitem.getSlots()];
			int fars[] = new int[delitem.getSlots()];
			int losts[] = new int[delitem.getSlots()];
			int puremaxs[] = new int[delitem.getSlots()];
			int musics[] = new int[delitem.getSlots()];
			for (int a = 1; a < delitem.getSlots(); a++) {
				if (delitem.getStackInSlot(a).getItem() == ItemLoader.itemPure) {
					pures[a] = delitem.getStackInSlot(a).getCount();
				}
				for (int i1 = 0; i1 < pures.length; i1++) {
					pureall += pures[i1];
				}
				if (delitem.getStackInSlot(a).getItem() == ItemLoader.itemFar) {
					fars[a] = delitem.getStackInSlot(a).getCount();
				}
				for (int i1 = 0; i1 < fars.length; i1++) {
					farall += fars[i1];
				}
				if (delitem.getStackInSlot(a).getItem() == ItemLoader.itemLost) {
					losts[a] = delitem.getStackInSlot(a).getCount();
				}
				for (int i1 = 0; i1 < losts.length; i1++) {
					lostall += losts[i1];
				}
				if (delitem.getStackInSlot(a).getItem() == ItemLoader.itemPureMax) {
					puremaxs[a] = delitem.getStackInSlot(a).getCount();
				}
				for (int i1 = 0; i1 < puremaxs.length; i1++) {
					puremaxall += puremaxs[i1];
				}
				if (delitem.getStackInSlot(a).getItem() == ItemLoader.itemGemMusic) {
					musics[a] = delitem.getStackInSlot(a).getCount();
				}
				for (int i1 = 0; i1 < musics.length; i1++) {
					musicall += musics[i1];
				}
			}
			if (pureall >= notes0 && farall >= notes1 && lostall >= notes2 && puremaxall >= notes3
					&& musicall >= notes4) {
				playsongs.setnotmake(0);
				playsongs.settimeswitch(msg.timeswitch);
				playsongs.setmakesec(msg.makesec);
				playsongs.setlock(msg.timeswitch);
				playsongs.setmakemark(msg.makemark);
				playsongs.setmarktype(msg.marktype);
				playsongs.setsongid(msg.songid);
				playsongs.setlvssss(msg.lvssss);
				for (int i = 1; i < delitem.getSlots(); i++) {
					if (delitem.getStackInSlot(i).getItem() == ItemLoader.itemPure) {
						if (notes0 >= delitem.getStackInSlot(i).getCount()) {
							notes0 = notes0 - delitem.getStackInSlot(i).getCount();
							delitem.setStackInSlot(i, new ItemStack(Items.AIR));
						} else if (delitem.getStackInSlot(i).getCount() > notes0) {
							delitem.setStackInSlot(i, new ItemStack(delitem.getStackInSlot(i).getItem(),
									(delitem.getStackInSlot(i).getCount() - notes0)));
							notes0 = 0;
						}
					}
					if (delitem.getStackInSlot(i).getItem() == ItemLoader.itemFar) {
						if (notes1 >= delitem.getStackInSlot(i).getCount()) {
							notes1 = notes1 - delitem.getStackInSlot(i).getCount();
							delitem.setStackInSlot(i, new ItemStack(Items.AIR));
						} else if (delitem.getStackInSlot(i).getCount() > notes1) {
							delitem.setStackInSlot(i, new ItemStack(delitem.getStackInSlot(i).getItem(),
									(delitem.getStackInSlot(i).getCount() - notes1)));
							notes1 = 0;
						}
					}
					if (delitem.getStackInSlot(i).getItem() == ItemLoader.itemLost) {

						if (notes2 >= delitem.getStackInSlot(i).getCount()) {
							notes2 = notes2 - delitem.getStackInSlot(i).getCount();
							delitem.setStackInSlot(i, new ItemStack(Items.AIR));
						} else if (delitem.getStackInSlot(i).getCount() > notes2) {
							delitem.setStackInSlot(i, new ItemStack(delitem.getStackInSlot(i).getItem(),
									(delitem.getStackInSlot(i).getCount() - notes2)));
							notes2 = 0;
						}
					}
					if (delitem.getStackInSlot(i).getItem() == ItemLoader.itemPureMax) {

						if (notes3 >= delitem.getStackInSlot(i).getCount()) {
							notes3 = notes3 - delitem.getStackInSlot(i).getCount();
							delitem.setStackInSlot(i, new ItemStack(Items.AIR));
						} else if (delitem.getStackInSlot(i).getCount() > notes3) {
							delitem.setStackInSlot(i, new ItemStack(delitem.getStackInSlot(i).getItem(),
									(delitem.getStackInSlot(i).getCount() - notes3)));
							notes3 = 0;
						}
					}
					if (delitem.getStackInSlot(i).getItem() == ItemLoader.itemGemMusic) {
						if (notes4 >= delitem.getStackInSlot(i).getCount()) {
							notes4 = notes4 - delitem.getStackInSlot(i).getCount();
							delitem.setStackInSlot(i, new ItemStack(Items.AIR));
						} else if (delitem.getStackInSlot(i).getCount() > notes4) {
							delitem.setStackInSlot(i, new ItemStack(delitem.getStackInSlot(i).getItem(),
									(delitem.getStackInSlot(i).getCount() - notes4)));
							notes4 = 0;
						}
					}

				}
			} else {
				playsongs.setnotmake(1);
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						playsongs.setnotmake(0);
					}
				}, 6000);
			}
			return null;

		}

	}

	public MessageFinishSongs() {
	}

}

/*
 * public MessageFinishSongs(BlockPos block, int dim) { this.pos = block;
 * this.dimension = dim; }
 */
