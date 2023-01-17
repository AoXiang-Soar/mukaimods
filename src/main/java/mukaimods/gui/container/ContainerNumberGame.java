package mukaimods.gui.container;

//import mukaimods.tileentity.TileEntityNumberGame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerNumberGame extends Container {
	protected Slot PlayerNumberA;
	protected Slot PlayerNumberB;
//	private TileEntityNumberGame tileEntity1;
	// private PlayMusicInventory pureinventory;
//	private ItemStackHandler output;

	public ContainerNumberGame(World world, EntityPlayer player, TileEntity tileEntity) {
		super();

//		this.tileEntity1 = (TileEntityNumberGame) tileEntity;
		/*
		for (int i = 10; i < 20; ++i) {
			this.addSlotToContainer(
					this.PlayerNumberA = new SlotItemHandler(tileEntity1.playerA, i, 13 + i * 32, 20));
		}
		

		for (int j = 10; j < 20; ++j) {
			this.addSlotToContainer(
					this.PlayerNumberB = new SlotItemHandler(tileEntity1.playerB, j * 9, 175 + j * 18, 2));
		}
		*/

	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {

		return true;
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

}
