package mukaimods.gui.container;

import io.netty.buffer.Unpooled;
import mukaimods.Mukaimods;
import mukaimods.item.ItemMKRecord;
import mukaimods.sounds.ArcMusic;
import mukaimods.tileentity.TileEntityMakeMusic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

public class ConitainerMakeMusic extends Container implements IGuiHandler {
	private TileEntityMakeMusic tileEntity1;
	Slot songrecord;
	Slot savemusic;
	int allsongs=ArcMusic.allsong;
	public ConitainerMakeMusic(World world, EntityPlayer player, TileEntity tileEntity) {
		super();

		this.tileEntity1 = (TileEntityMakeMusic) tileEntity;
	
		this.addSlotToContainer(this.songrecord = new SlotItemHandler(tileEntity1.songrecord, 0, 16, 10) {

			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack!= null && stack.getItem() instanceof ItemMKRecord && super.isItemValid(stack);
			}

			@Override
			public void onSlotChanged() {
				ItemStack stack = this.getStack();
				int songid = tileEntity1.getsongid();
				try {
					if (stack.getItem() == Items.AIR) {
					//	NetworkManagers.wrapper.sendToAll(new MessageStopMusic(tileEntity1.getPos(),tileEntity1.getWorld().provider.getDimension(),songid));
						for(EntityPlayerMP playermp : Mukaimods.mcserver.getPlayerList().getPlayers()) {
							PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
							packetbuffer.writeString("record");
							packetbuffer.writeString("mukaimods:" + songid);
							playermp.connection.sendPacket(new SPacketCustomPayload("MC|StopSound", packetbuffer));
							
						}
					}
				} catch (Exception e) {

				}
				super.onSlotChanged();
			}

			@Override
			public int getItemStackLimit(ItemStack stack) {
				return 1;
			}

		});

		for (int i = 1; i < 10; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(this.savemusic = new SlotItemHandler(tileEntity1.musicinventory, j * 9 + i,
						175 + j * 18, 2 + i * 18)

				{

					@Override
					public boolean isItemValid(ItemStack stack) {
						return stack != null && stack.getItem() == Items.AIR && super.isItemValid(stack);
					}

					@Override
					public int getItemStackLimit(ItemStack stack) {
						return 64;
					}
				});
			}
		}
		
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				int k = j + 1;
				this.addSlotToContainer(new Slot(player.inventory, k + i * 9 + 8, 8 + j * 18, 106 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 164));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		final int all = 28;
		Slot slot = inventorySlots.get(index);
		if (slot == null || !slot.getHasStack()) {
			return ItemStack.EMPTY;

		}
		ItemStack item = slot.getStack(), fail = item.copy(), success = ItemStack.EMPTY;
		if (index < all) {
			if (!this.mergeItemStack(item, all, all + 36, true)) {
				return success;
			}
		} else {
			if (!mergeItemStack(item, 0, all, false)) {
				return success;
			}
		}
		if (item.isEmpty()) {
			slot.putStack(ItemStack.EMPTY);
		} else {
			slot.onSlotChanged();
		}
		if (fail.getCount() == item.getCount()) {
			return success;
		}
		slot.onTake(playerIn, item);
		return fail;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {

		return true;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	int nowtime;
	int maketime;
	int lock;
	int size;
	int ftrprspsttime;
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		int nowtime = tileEntity1.getnowtime();
		int maketime = tileEntity1.getmaketime();
		int lock = tileEntity1.getlock();
		int size =(int)tileEntity1.getmusicsize();
		int ftrprspsttime=(int)tileEntity1.getftrprspsttime();
		for (IContainerListener listener : this.listeners) {
			listener.sendWindowProperty(this, 10, nowtime);
			listener.sendWindowProperty(this, 11, maketime);
			listener.sendWindowProperty(this, 12, lock);
			listener.sendWindowProperty(this, 13, size);
			listener.sendWindowProperty(this, 15, ftrprspsttime);
		}
		this.nowtime = nowtime;
		this.maketime = maketime;
		this.lock = lock;
		this.size = size;
		this.ftrprspsttime=ftrprspsttime;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);
		switch (id) {
		case 10:
			this.nowtime = data;
			break;
		case 11:
			this.maketime = data;
			break;
		case 12:
			this.lock = data;
			break;
		case 13:
			this.size = data;
		case 15:
			this.ftrprspsttime = data;
			break;
		default:
			break;
		}
	}

	public void onContainerClosed(EntityPlayer playerIn) {

		super.onContainerClosed(playerIn);
		for (int i = 1; i < allsongs; i++) {

		}

	}

	public int getnowtimes() {
		return this.nowtime;
	}

	public int getmaketimes() {
		return this.maketime;
	}

	public int getlocks() {
		return this.lock;
	}
	public int getsizes() {
		return this.size;
	}
	public int getftrprspsttime() {
		return this.ftrprspsttime;
	}
}
