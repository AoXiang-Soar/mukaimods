package mukaimods.internet;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import mukaimods.Mukaimods;
import mukaimods.item.ItemLoader;
import mukaimods.potion.PotionLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageFireKing implements IMessage {
	UUID uuid;

	public MessageFireKing(UUID uuid) {
		this.uuid = uuid;

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.uuid.getMostSignificantBits());
		buf.writeLong(this.uuid.getLeastSignificantBits());

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		final long most = buf.readLong();
		final long least = buf.readLong();
		this.uuid = new UUID(most, least);
	}

	public static class MessageSwitchInitiativeSkillHandler implements IMessageHandler<MessageFireKing, IMessage> {
		@Override
		public IMessage onMessage(MessageFireKing msg, MessageContext ctx) {
			UUID uuid = msg.uuid;

			EntityPlayer player = Mukaimods.mcserver.getPlayerList().getPlayerByUUID(uuid);
			player.sendMessage(new TextComponentTranslation("Fire !"));
			ItemStack item = player.getHeldItemMainhand();
			if (player != null) {

				if (item.getItem() == ItemLoader.itemFireKingSword) {

					NBTTagList ench = item.getEnchantmentTagList();

					for (int a = 0; a < ench.tagCount(); a++) {
						ench.removeTag(a);
						player.sendMessage(new TextComponentTranslation("del" + a));
					}

					NBTTagCompound kill = item.getTagCompound();

					if (kill == null) {
						kill = new NBTTagCompound();
					}

					if (kill.hasKey("Kill")) {
						int Killnumber = kill.getInteger("Kill") + 1;
						if (Killnumber > 8) {
							Killnumber = 8;
						}
						if (Killnumber == 8) {
							item.shrink(1);
							player.addPotionEffect(new PotionEffect(PotionLoader.potionfireking, 99999999 * 60, 0));
						}
						kill.setInteger("Kill", Killnumber);
						item.addEnchantment(Enchantments.FIRE_ASPECT, Killnumber + 1);
					} else {
						kill.setInteger("Kill", 1);
					}

					item.setTagCompound(kill);
					// player.sendMessage(new TextComponentTranslation("nbt !"));
				}
			}

			return null;

		}

	}

	public MessageFireKing() {
	}

}
