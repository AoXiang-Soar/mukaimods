package mukaimods.item;

import java.util.List;
import java.util.Random;

import io.netty.buffer.Unpooled;
import mukaimods.createtab.CreativeTabsLoader;
import mukaimods.player.EntityGodczm;
import mukaimods.sounds.ArcMusic;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemYitai extends Item {

	public ItemYitai() {
		super();
		this.setUnlocalizedName("yitai");
		this.setCreativeTab(CreativeTabsLoader.tabItems);
	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("ItemMusicyitai"));
		tooltips.add(I18n.format("ItemMusicyitai1"));
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack itemstack = player.getHeldItem(handIn);
		Entity EntityGodczm = new EntityGodczm(worldIn);
		Random gl = new Random();
		int girl = gl.nextInt(10);
		if (!worldIn.isRemote) {
			for (int song = 0; song < ArcMusic.allsong; song++) {
				EntityPlayerMP playermp = (EntityPlayerMP) player;
				PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
				packetbuffer.writeString("player");
				packetbuffer.writeString("mukaimods:mukaimods:" + song);
				playermp.connection.sendPacket(new SPacketCustomPayload("MC|StopSound", packetbuffer));

			
			
				if (girl == 2) {
					EntityGodczm.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, 0);
					worldIn.spawnEntity(EntityGodczm);
				}
			}
			itemstack.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}