package mukaimods.internet;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import mukaimods.Mukaimods;
import mukaimods.gui.container.CopyMusic;
import mukaimods.item.ItemLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.ItemStackHandler;

public class MessagePackage implements IMessage {
	UUID uuid;
	int songid;
	ItemStackHandler guiinventory;
	public MessagePackage(UUID uuid, int songid) {
		this.uuid = uuid;
		this.songid = songid;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.uuid.getMostSignificantBits());
		buf.writeLong(this.uuid.getLeastSignificantBits());
		buf.writeInt(this.songid);	
		
		/*buf.writeInt(this.block.getX());
		buf.writeInt(this.block.getY());
		buf.writeInt(this.block.getZ());*/
		
	}
	@Override
	public void fromBytes(ByteBuf buf) {
		final long most = buf.readLong();
		final long least = buf.readLong();
		this.uuid = new UUID(most, least);
		this.songid = buf.readInt();
		/*
		final int x = buf.readInt();
		final int y = buf.readInt();
		final int z = buf.readInt();*/
		//this.block = new BlockPos(x, y, z);
		
		
	}
	public static class MessageSwitchInitiativeSkillHandler implements IMessageHandler<MessagePackage, IMessage> {
		@Override
		public IMessage onMessage(MessagePackage msg, MessageContext ctx) {
			// World world =
			// FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(msg.dim);
			
		    UUID uuid = msg.uuid;
			EntityPlayer player = Mukaimods.mcserver.getPlayerList().getPlayerByUUID(uuid);
		//	EntityPlayerMP playermp=(EntityPlayerMP) player;
		
					
				
			if (player == null) {
				return null;
			}
			Container cont = player.openContainer;
			if (!(cont instanceof CopyMusic)) {
				return null;
			}
			ItemStackHandler record1 = ((CopyMusic) cont).record;
			if (record1.getStackInSlot(0).getItem() == ItemLoader.itemVoidRecord) {
				record1.setStackInSlot(0, new ItemStack(ItemLoader.song[msg.songid]));
			}
			if (record1.getStackInSlot(0).getItem() != ItemLoader.itemVoidRecord) {
				return null;
			}
			
			
			
			
			
			/*
			 * TileEntityEnergyConvertor te = ((ContainerEnergyConvertor)cont).block; if(te
			 * == null) { return null; } te.rev = !te.rev;
			 */

			return null;

		}

	}

	public MessagePackage() {
	}
	

}
