package mukaimods.internet;

import io.netty.buffer.ByteBuf;
import mukaimods.item.ItemLoader;
import mukaimods.player.PTTCapabilityList.Difficulty;
import mukaimods.tileentity.TileEntityPlaysongs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.ItemStackHandler;

public class MessageMakeSongs implements IMessage {

	int finish;
	int d;
	BlockPos pos = BlockPos.ORIGIN;
	int makemark;
	int marktype;
	int songid;
	double lvs;
	Difficulty difficulty;
	private static String assess[] = new String[7];
	{
		assess[0] = "EX+";
		assess[1] = "EX";
		assess[2] = "AA";
		assess[3] = "A";
		assess[4] = "B";
		assess[5] = "C";
		assess[6] = "D";
	}

	private static String SongNames = "SongNames";
	private static String Lvs = "Lvs";
	private static String Marks = "Marks";
	private static ItemStack[] mark = new ItemStack[5];
	{
		mark[0] = new ItemStack(ItemLoader.itemPMMax);
		mark[1] = new ItemStack(ItemLoader.itemPM);
		mark[2] = new ItemStack(ItemLoader.itemFullRecall);
		mark[3] = new ItemStack(ItemLoader.itemTrackComplete);
		mark[4] = new ItemStack(ItemLoader.itemTrackLost);
	}

	public MessageMakeSongs(BlockPos pos, int d, int finish, int makemark, int marktpye, int songid, double lvs, Difficulty difficulty) {
		this.pos = pos;
		this.d = d;
		this.finish = finish;
		this.makemark = makemark;
		this.marktype = marktpye;
		this.songid = songid;
		this.lvs = lvs;
		this.difficulty = difficulty;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());
		buf.writeInt(this.d);
		buf.writeInt(this.finish);
		buf.writeInt(this.makemark);
		buf.writeInt(this.marktype);
		buf.writeInt(this.songid);
		buf.writeDouble(this.lvs);
		buf.writeInt(this.difficulty.id);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		final int x = buf.readInt();
		final int y = buf.readInt();
		final int z = buf.readInt();
		this.pos = new BlockPos(x, y, z);
		this.d = buf.readInt();
		this.finish = buf.readInt();
		this.makemark = buf.readInt();
		this.marktype = buf.readInt();
		this.songid = buf.readInt();
		this.lvs = buf.readDouble();
		this.difficulty = Difficulty.getDifficulty(buf.readInt());
	}

	public static class MessageSwitchInitiativeSkillHandler implements IMessageHandler<MessageMakeSongs, IMessage> {
		@Override
		public IMessage onMessage(MessageMakeSongs msg, MessageContext ctx) {
			TileEntityPlaysongs playsongs = (TileEntityPlaysongs) FMLCommonHandler.instance()
					.getMinecraftServerInstance().getWorld(msg.d).getTileEntity(msg.pos);
			ItemStackHandler result = playsongs.result;

			ItemStack markitem = mark[msg.marktype];
			/*
			 * NBTTagCompound nbt =stack.getTagCompound();��ȡ���ϵ���Ʒ��nbt��ǩ
			 * 
			 * if (nbt == null) { nbt = new NBTTagCompound();���û�б�ǩ���½�һ�� }
			 * nbt.setInteger("����"��114514);д��
			 */

			NBTTagCompound tag = markitem.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			tag.setInteger(SongNames, msg.songid);
			tag.setDouble(Lvs, msg.lvs);
			tag.setInteger(Marks, msg.makemark);
			tag.setString("Difficulty", msg.difficulty.name());
			if (msg.makemark >= 9900000) {
				tag.setString("Assess", assess[0]);
			} else if (msg.makemark < 9900000 && msg.makemark >= 9800000) {
				tag.setString("Assess", assess[1]);
			} else if (msg.makemark < 9800000 && msg.makemark >= 9500000) {
				tag.setString("Assess", assess[2]);
			} else if (msg.makemark < 9500000 && msg.makemark >= 9200000) {
				tag.setString("Assess", assess[3]);
			} else if (msg.makemark < 9200000 && msg.makemark >= 8900000) {
				tag.setString("Assess", assess[4]);
			} else if (msg.makemark < 8900000 && msg.makemark >= 8600000) {
				tag.setString("Assess", assess[5]);
			} else if (msg.makemark < 8600000) {
				tag.setString("Assess", assess[6]);
			}

			if (msg.finish == 1) {
				playsongs.settimeswitch(0);
				playsongs.setlock(0);

				markitem.setTagCompound(tag);
				result.setStackInSlot(0, markitem);

			}
			return null;

		}

	}

	public MessageMakeSongs() {
	}

}
