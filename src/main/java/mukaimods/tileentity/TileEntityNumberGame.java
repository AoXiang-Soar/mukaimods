package mukaimods.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityNumberGame extends TileEntity implements ITickable {
	public ItemStackHandler playerA = new ItemStackHandler(30);
	public ItemStackHandler playerB = new ItemStackHandler(30);
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {		
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {		
		return super.getCapability(capability, facing);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);
		if (!this.world.isRemote) {
			compound.setTag("playerA", this.playerA.serializeNBT());
			compound.setTag("playerB", this.playerB.serializeNBT());

		}
		return compound;

	}
	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		this.playerA.deserializeNBT(compound.getCompoundTag("playerA"));
		this.playerB.deserializeNBT(compound.getCompoundTag("playerB"));

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
