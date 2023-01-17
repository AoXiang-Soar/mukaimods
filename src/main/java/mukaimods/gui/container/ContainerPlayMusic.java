package mukaimods.gui.container;

import net.minecraft.inventory.IContainerListener;
import net.minecraft.tileentity.TileEntity;

//import java.util.ArrayList;

import mukaimods.item.ItemLoader;
import mukaimods.item.ItemMKRecord;
import mukaimods.tileentity.TileEntityPlaysongs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
//import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPlayMusic extends Container implements IGuiHandler {

	protected Slot music;
	protected Slot songsrecord;
	protected Slot mark;
	private int doingtime;
	int lv;
	private int lock;
	private int nomake;

	private TileEntityPlaysongs tileEntity1;

	public ContainerPlayMusic(World world, EntityPlayer player, TileEntity tileEntity) {
		super();

		this.tileEntity1 = (TileEntityPlaysongs) tileEntity;
		this.addSlotToContainer(this.songsrecord = new SlotItemHandler(tileEntity1.songrecord, 0, 16, 20) {
			@Override
			public boolean isItemValid(ItemStack stack) {
//				int i = 230;
//				ArrayList<Item> records = new ArrayList<>();
//				for (int j = 1; j <= i; j++)
//					records.add(ItemLoader.song[i]);
				return stack != null && stack.getItem() instanceof ItemMKRecord && super.isItemValid(stack);

			}

			@Override
			public int getItemStackLimit(ItemStack stack) {
				return 1;
			}

		});
		this.addSlotToContainer(this.mark = new SlotItemHandler(tileEntity1.result, 0, 145, 152) {

			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack != null && stack.getItem() == Items.AIR && super.isItemValid(stack);
			}

			@Override
			public int getItemStackLimit(ItemStack stack) {
				return 1;
			}

		});

		for (int i = 1; i < 10; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(this.music = new SlotItemHandler(tileEntity1.pureinventory, j * 9 + i,
						175 + j * 18, 70 + i * 18)

				{

					@Override
					public boolean isItemValid(ItemStack stack) {
						return stack != null && stack.getItem() == ItemLoader.itemFar
								|| stack.getItem() == ItemLoader.itemLost || stack.getItem() == ItemLoader.itemPureMax
								|| stack.getItem() == ItemLoader.itemPure
								|| stack.getItem() == ItemLoader.itemGemMusic && super.isItemValid(stack);
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
				this.addSlotToContainer(new Slot(player.inventory, k + i * 9 + 8, 8 + j * 18, 174 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 232));
		}

	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		final int all = 29;
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

	public Slot getIronSlot() {
		return this.music;

	}

	public Slot getIronSlot2() {
		return this.songsrecord;

	}

	public Slot getIronSlot3() {
		return this.mark;

	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		int doingtime = tileEntity1.getdoing();
		int lv = tileEntity1.getlvs();
		int lock = tileEntity1.getlock();
		int nomake = tileEntity1.getnomake();
		for (IContainerListener listener : this.listeners) {
			listener.sendWindowProperty(this, 0, doingtime);
			listener.sendWindowProperty(this, 1, lv);
			listener.sendWindowProperty(this, 2, lock);
			listener.sendWindowProperty(this, 3, nomake);
		}
		this.doingtime = doingtime;
		this.lv = lv;
		this.lock = lock;
		this.nomake = nomake;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);
		switch (id) {
		case 0:
			this.doingtime = data;
			break;
		case 1:
			this.lv = data;
			break;
		case 2:
			this.lock = data;
			break;
		case 3:
			this.nomake = data;
			break;
		default:
			break;
		}
	}

	public int getdoingtime() {
		return this.doingtime;
	}

	public int getlvs() {
		return this.lv;
	}

	public int getlock() {
		return this.lock;
	}

	public int getnomake() {
		return this.nomake;
	}

	public int gettiledoingTime() {
		return this.tileEntity1.getdoing();
	}

	public int getsonglvs() {
		return this.tileEntity1.getlvs();
	}

	public int getnote0() {
		return this.tileEntity1.getnote0();
	}

	public int getnote1() {
		return this.tileEntity1.getnote1();
	}

	public int getnote2() {
		return this.tileEntity1.getnote2();
	}

	public int getnote3() {
		return this.tileEntity1.getnote3();
	}

	public int getnote4() {
		return this.tileEntity1.getnote4();
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

}
