package mukaimods.tileentity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;
import mukaimods.internet.MessageMakeSongs;
import mukaimods.internet.NetworkManagers;
import mukaimods.player.PTTCapabilityList.Difficulty;

public class TileEntityPlaysongs extends TileEntity implements ITickable {

	private int timeswitch;
	private int makesec;
	public int doing;
	public int finish;
	public int lvs = 0;
	public int lock = 0;
	public int allnote;
	public int musicbutton;
	private int[] notes = new int[4];
	int note4;
	Double lvssss;
	int nomake;
	private int makemark;
	private int marktype;
	private int songid;
	private Difficulty difficulty = Difficulty.FTR;
	// private GuiContainer guicontainer;
	// private GuiPlayMusic playmusic = (GuiPlayMusic) guicontainer;
	public ItemStackHandler pureinventory = new ItemStackHandler(29);
	public ItemStackHandler songrecord = new ItemStackHandler(1);
	public ItemStackHandler result = new ItemStackHandler(1);

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
			compound.setTag("pureinventory", this.pureinventory.serializeNBT());
			compound.setTag("songrecord", this.songrecord.serializeNBT());
			compound.setTag("result", this.result.serializeNBT());
		}
		return compound;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		this.pureinventory.deserializeNBT(compound.getCompoundTag("pureinventory"));
		this.songrecord.deserializeNBT(compound.getCompoundTag("songrecord"));
		this.result.deserializeNBT(compound.getCompoundTag("result"));

	}

	protected final void syncToTrackingClients() {
		if (!this.world.isRemote) {
			SPacketUpdateTileEntity packet = this.getUpdatePacket();
			PlayerChunkMapEntry trackingEntry = ((WorldServer) this.world).getPlayerChunkMap()
					.getEntry(this.pos.getX() >> 4, this.pos.getZ() >> 4);
			if (trackingEntry != null) {
				for (EntityPlayerMP player : trackingEntry.getWatchingPlayers()) {
					player.connection.sendPacket(packet);
				}
			}
		}
	}

	@Override
	public void update() {

		if (!this.world.isRemote) {
			if (timeswitch == 1) {
				if (doing < makesec * 20) {
					doing = doing + 1;
					lock = 1;
					this.markDirty();
				}
				if (doing == makesec * 20) {
					finish = 1;
					this.markDirty();
					World world = this.getWorld();
					int d = world.provider.getDimension();
					BlockPos pos = this.getPos();
					lock = 0;
					NetworkManagers.wrapper
							.sendToServer(new MessageMakeSongs(pos, d, finish, makemark, marktype, songid, lvssss, difficulty));

					doing = 0;
				}
			}
		}

	}

	public int getdoing() {
		return doing;
	}

	public void setlv(int lvss) {
		this.lvs = lvss;
	}

	public int getlvs() {
		return lvs;
	}

	public void setlvssss(Double lvssss2) {
		this.lvssss = lvssss2;
	}

	public int getlock() {
		return lock;
	}

	public void setlock(int locks) {
		this.lock = locks;
	}

	public void settimeswitch(int newValue) {
		this.timeswitch = newValue;
	}

	public void setmusicbutton(int newValue) {
		this.musicbutton = newValue;
	}

	public int getmusicbutton() {
		return musicbutton;
	}

	public void setallnote(int newValue) {
		this.allnote = newValue;
	}

	public int getallnote() {
		return allnote;
	}

	public void setsongid(int songid) {
		this.songid = songid;
	}

	public void setmakesec(int time) {
		this.makesec = time;
	}

	public void setmakemark(int newValue) {
		this.makemark = newValue;
	}

	public void setmarktype(int newValue) {
		this.marktype = newValue;
	}

	public void setnote(int newValue, int value) {
		this.notes[newValue] = value;
	}

	public void setnote0(int newValue) {
		this.notes[0] = newValue;
	}

	public void setnote1(int newValue) {
		this.notes[1] = newValue;
	}

	public void setnote2(int newValue) {
		this.notes[2] = newValue;
	}

	public void setnote3(int newValue) {
		this.notes[3] = newValue;
	}

	public void setnote4(int newValue) {
		this.note4 = newValue;
	}

	public int getnote0() {
		return notes[0];
	}

	public int getnote1() {
		return notes[1];
	}

	public int getnote2() {
		return notes[2];
	}

	public int getnote3() {
		return notes[3];
	}

	public int getnote4() {
		return note4;
	}

	public void setnotmake(int no) {
		this.nomake = no;
	}

	public int getnomake() {
		return nomake;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	// ItemStack itemStack = pureinventory.extractItem(0, 1, true);
	// GuiPlayMusic maketimesec =new GuiPlayMusic(null, null);
	// IBlockState state = this.world.getBlockState(pos);

	// if(playmusic.gettimeswitch()==1) {

	// if(maketimesec-(doing/20)==0) {
	// doing=maketimesec*20;
	// }

	// }

}
