package mukaimods.internet;

import mukaimods.Mukaimods;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManagers {
	public static SimpleNetworkWrapper wrapper;
	public static NetworkManagers instance;
	private final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel("mukaismod");

	public NetworkManagers() {
		instance = this;
		wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Mukaimods.MODID);
		wrapper.registerMessage(MessagePackage.MessageSwitchInitiativeSkillHandler.class, MessagePackage.class, 0, Side.SERVER);
		wrapper.registerMessage(MessagePackage.MessageSwitchInitiativeSkillHandler.class, MessagePackage.class, 0, Side.CLIENT);
		wrapper.registerMessage(MessageFinishSongs.MessageSwitchInitiativeSkillHandler.class, MessageFinishSongs.class, 1, Side.CLIENT);
		wrapper.registerMessage(MessageFinishSongs.MessageSwitchInitiativeSkillHandler.class, MessageFinishSongs.class, 1, Side.SERVER);
		wrapper.registerMessage(MessageMakeSongs.MessageSwitchInitiativeSkillHandler.class, MessageMakeSongs.class, 2, Side.SERVER);
		wrapper.registerMessage(MessageSetSongsLv.MessageSwitchInitiativeSkillHandler.class, MessageSetSongsLv.class, 3, Side.SERVER);
		wrapper.registerMessage(MessagePlayerPTT.MessageSwitchInitiativeSkillHandler.class, MessagePlayerPTT.class, 4, Side.SERVER);
		wrapper.registerMessage(MessageMakeMusic.MessageSwitchInitiativeSkillHandler.class, MessageMakeMusic.class, 5, Side.SERVER);
		wrapper.registerMessage(MessageMakeMusic.MessageSwitchInitiativeSkillHandler.class, MessageMakeMusic.class, 5, Side.CLIENT);
		// wrapper.registerMessage(MessageStopMusic.MessageSwitchInitiativeSkillHandler.class, MessageStopMusic.class, 6, Side.CLIENT);
		wrapper.registerMessage(MessagePlayMusic.MessageSwitchInitiativeSkillHandler.class, MessagePlayMusic.class, 7, Side.CLIENT);
		wrapper.registerMessage(MessagePlayMusic.MessageSwitchInitiativeSkillHandler.class, MessagePlayMusic.class, 7, Side.SERVER);
		// wrapper.registerMessage(MessageFireKing.MessageSwitchInitiativeSkillHandler.class, MessageFireKing.class, 6, Side.CLIENT);
		// wrapper.registerMessage(MessageFireKing.MessageSwitchInitiativeSkillHandler.class, MessageFireKing.class, 6, Side.SERVER);
	}

	public void sendMessageToDim(IMessage msg, int dim) {
		channel.sendToDimension(msg, dim);
	}

	public void sendMessageAroundPos(IMessage msg, int dim, BlockPos pos) {

		channel.sendToAllAround(msg, new NetworkRegistry.TargetPoint(dim, pos.getX(), pos.getY(), pos.getZ(), 2.0D));
	}

	public void sendMessageToPlayer(IMessage msg, EntityPlayerMP player) {
		channel.sendTo(msg, player);
	}

	public void sendMessageToAll(IMessage msg) {
		channel.sendToAll(msg);
	}

	public void sendMessageToServer(IMessage msg) {
		channel.sendToServer(msg);
	}

	public void sendPacket(SPacketCustomPayload sPacketCustomPayload) {

	}

}
