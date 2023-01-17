package mukaimods.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerDemo extends Container {
	private ItemStackHandler items = new ItemStackHandler(3);
	private ItemStackHandler itemss = new ItemStackHandler(1);

	public ContainerDemo(EntityPlayer player) {
		super();
		for (int i = 0; i < 3; ++i) {
			this.addSlotToContainer(new SlotItemHandler(items, i, 13 + i * 32, 20));
		}
		int i1 = 0;
		this.addSlotToContainer(new SlotItemHandler(itemss, i1, 145, 20));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 109));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {

		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		return null;
	}

}
