package mukaimods.internet;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import mukaimods.Mukaimods;
import mukaimods.player.CapabilityLoader;
import mukaimods.player.IPTTCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.ItemStackHandler;

public class MessagePlayerPTT implements IMessage {
	UUID uuid;
	int songid;
	double lvs;
	int mark;
	ItemStackHandler guiinventory;

	public MessagePlayerPTT(UUID uuid, int songid, double lv, int mark) {
		this.uuid = uuid;
		this.songid = songid;
		this.lvs = lv;
		this.mark = mark;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.uuid.getMostSignificantBits());
		buf.writeLong(this.uuid.getLeastSignificantBits());
		buf.writeInt(this.songid);
		buf.writeDouble(this.lvs);
		buf.writeInt(this.mark);

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		final long most = buf.readLong();
		final long least = buf.readLong();
		this.uuid = new UUID(most, least);
		this.songid = buf.readInt();
		this.lvs = buf.readDouble();
		this.mark = buf.readInt();

	}

	public static class MessageSwitchInitiativeSkillHandler implements IMessageHandler<MessagePlayerPTT, IMessage> {
		@Override
		public IMessage onMessage(MessagePlayerPTT msg, MessageContext ctx) {
			UUID uuid = msg.uuid;

			EntityPlayer player = Mukaimods.mcserver.getPlayerList().getPlayerByUUID(uuid);

			if (player == null) {
				return null;
			}

			IPTTCapability ptts = player.getCapability(CapabilityLoader.PTT, null);
			player.sendMessage(new TextComponentTranslation("Now PTT:" + ptts.getptt()));

			return null;

		}

	}

	public MessagePlayerPTT() {
		
	}
}
