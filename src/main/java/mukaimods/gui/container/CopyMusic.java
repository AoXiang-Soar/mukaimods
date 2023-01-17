package mukaimods.gui.container;

import net.minecraft.init.Items;
import mukaimods.item.ItemLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CopyMusic extends Container {

	public ItemStackHandler record = new ItemStackHandler(1);
	protected Slot songs;
	// private EntityPlayer player;
	private World world;
	private BlockPos blockpos;

	public CopyMusic(World world, EntityPlayer player, BlockPos blockpos) {

		super();
		this.world = world;
		// this.player=player;
		this.blockpos = blockpos;

		this.addSlotToContainer(this.songs = new SlotItemHandler(record, 0, 23, 115)

		{

			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack != null && stack.getItem() == ItemLoader.itemVoidRecord && super.isItemValid(stack);
			}

			@Override
			public int getItemStackLimit(ItemStack stack) {
				return 1;
			}

		});

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				int k = j + 1;
				this.addSlotToContainer(new Slot(player.inventory, k + i * 9 + 8, 8 + j * 18, 141 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 199));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	/*
	 * @Override public ItemStack transferStackInSlot(EntityPlayer playerIn, int
	 * index) { Slot slot = inventorySlots.get(index); Item item =
	 * record.getStackInSlot(0).getItem(); Item[] playeritem = new Item[36];
	 * 
	 * for(int i=1;i<=36;i++) { playeritem[i] =
	 * player.inventory.getStackInSlot(i).getItem(); }
	 * 
	 * if (slot == null || !slot.getHasStack()) { return null; } ItemStack newStack
	 * = slot.getStack(), oldStack = newStack.copy();
	 * 
	 * boolean isMerged = false;
	 * 
	 * 
	 * for(int i=1;i<=36;i++) { if( playeritem[i]==ItemLoader.songs) {
	 * 
	 * if (index == 0 ) {
	 * 
	 * isMerged = mergeItemStack(newStack, 1, 45, true); } else if (index >= 11 &&
	 * index < 45) {
	 * 
	 * isMerged = !songs.getHasStack() && newStack.getMaxStackSize() <= 16 &&
	 * mergeItemStack(newStack, 0, 1, false); }
	 * 
	 * else if (index >= 1 && index < 10) { isMerged = !songs.getHasStack() &&
	 * newStack.getMaxStackSize() <= 16 && mergeItemStack(newStack, 0, 1, false);
	 * 
	 * }
	 * 
	 * }else { return null; } }
	 * 
	 * 
	 * if (!isMerged) { return null; }
	 * 
	 * if (newStack.getMaxStackSize() == 0) { slot.putStack(null); } else {
	 * slot.onSlotChanged(); }
	 * 
	 * 
	 * return oldStack; }
	 * 
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		final int all = 1;
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
		return this.songs;
	}

	public void onContainerClosed(EntityPlayer playerIn) {

		super.onContainerClosed(playerIn);

		world.playEvent(1010, blockpos, 0);
		world.playRecord(blockpos, (SoundEvent) null);
		if (playerIn.isServerWorld()) {
			ItemStack stack = this.songs.getStack();
			if (stack != null) {
				playerIn.dropItem(stack, false);

				this.songs.putStack(new ItemStack(Items.AIR, 1));
			}

		}

	}

}