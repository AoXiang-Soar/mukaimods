package mukaimods.tileentity;

import mukaimods.Mukaimods;
import mukaimods.block.BlockLoader;
import mukaimods.internet.MessageMakeMusic;
import mukaimods.internet.NetworkManagers;
import mukaimods.item.ItemLoader;
import mukaimods.sounds.ArcMusic;
import mukaimods.sounds.ArcSongsLv;
import mukaimods.sounds.ArcSongsTime;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

import net.minecraftforge.common.capabilities.Capability;

import net.minecraftforge.items.ItemStackHandler;
import io.netty.buffer.Unpooled;

public class TileEntityMakeMusic extends TileEntity implements ITickable {
	public ItemStackHandler musicinventory = new ItemStackHandler(49);
	public ItemStackHandler songrecord = new ItemStackHandler(1);
	int allsongs = ArcMusic.allsong;
	int songid;
	double songlv;
	String songalltime;
	int songalltimesec;
	int ftrprspst;
	int nowtime;
	int maketime;
	float musicsize;

	// int play = 0;
	int playsong;
	int recorddamage;
	int ftrprspsttime;
	int d;
	static int loop;
	int lock = 0;
	int timeswitch;
	int makeloop;

	int stopmusic;
	int stopsongid;

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
			compound.setTag("musicinventory", this.musicinventory.serializeNBT());
			compound.setTag("songrecord", this.songrecord.serializeNBT());

		}
		return compound;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		this.musicinventory.deserializeNBT(compound.getCompoundTag("musicinventory"));
		this.songrecord.deserializeNBT(compound.getCompoundTag("songrecord"));

	}

	@Override
	public void onLoad() {
		World world = this.world;
		if (world.getBlockState(this.getPos()).getBlock() == BlockLoader.blockBYDNoteGetter) {
			ftrprspst = 0;
			ftrprspsttime = 5;
		}
		if (world.getBlockState(this.getPos()).getBlock() == BlockLoader.blockFTRNoteGetter) {
			ftrprspst = 1;
			ftrprspsttime = 10;
		}
		if (world.getBlockState(this.getPos()).getBlock() == BlockLoader.blockPRSNoteGetter) {
			ftrprspst = 2;
			ftrprspsttime = 15;
		}
		if (world.getBlockState(this.getPos()).getBlock() == BlockLoader.blockPSTNoteGetter) {
			ftrprspst = 3;
			ftrprspsttime = 20;
		}

	}

	public int getftrprspst() {
		return this.ftrprspst;
	}

	public void setstopmusic(int stopmusic) {
		this.stopmusic = stopmusic;
	}

	public void setstopsongid(int stopsongid) {
		this.stopsongid = stopsongid;
	}

	public void setlock(int start3) {
		this.lock = start3;
	}
	
	public void setloop(int start4) {
		this.loop = start4;
	}
	
	public void setmakeloop(int start2) {
		this.makeloop = start2;
	}

	public int getlock() {
		return this.lock;
	}

	public int gettimeswitch() {
		return this.timeswitch;
	}

	public int getmakeloop() {
		return this.makeloop;
	}

	public void setmusicsize(float musicsize2) {
		this.musicsize = musicsize2;

	}

	public int getsongid() {
		return this.songid;
	}

	public float getmusicsize() {
		return this.musicsize;
	}

	public float getftrprspsttime() {
		return this.ftrprspsttime;
	}

	public int getnowtime() {
		return this.nowtime;
	}

	public int getmaketime() {
		return this.maketime;
	}

	public void settimeswitch(int start) {
		this.timeswitch = start;
	}

	public void setplaysong(int play) {
		this.playsong = play;
	}

	/*
	 * public static EntityPlayerMP getPlayer(MinecraftServer server, ICommandSender
	 * sender, String target) throws PlayerNotFoundException, CommandException {
	 * return getPlayer(server, EntitySelector.matchOnePlayer(sender, target),
	 * target); }
	 * 
	 * public void execute(MinecraftServer server, ICommandSender sender, String[]
	 * args) throws CommandException { if (args.length >= 1 && args.length <= 3) {
	 * int i = 0; EntityPlayerMP entityplayermp = getPlayer(server, sender,
	 * args[i++]); String s = ""; String s1 = "";
	 * 
	 * if (args.length >= 2) { String s2 = args[i++]; SoundCategory soundcategory =
	 * SoundCategory.getByName(s2);
	 * 
	 * if (soundcategory == null) { throw new
	 * CommandException("commands.stopsound.unknownSoundSource", new Object[] { s2
	 * }); }
	 * 
	 * s = soundcategory.getName(); }
	 * 
	 * if (args.length == 3) { s1 = args[i++]; }
	 * 
	 * PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
	 * packetbuffer.writeString(s); packetbuffer.writeString(s1);
	 * entityplayermp.connection.sendPacket(new SPacketCustomPayload("MC|StopSound",
	 * packetbuffer));
	 * 
	 * }
	 * 
	 * }
	 */
	@Override
	public void update() {

		BlockPos pos = this.getPos();
		/*
		 * if (playsong == 1) {
		 * 
		 * world.playSound((EntityPlayer) null, pos.getX(), pos.getY(), pos.getZ(),
		 * ArcMusic.songs1[songid], SoundCategory.RECORDS, musicsize, 1); playsong = 0;
		 * }
		 */
		if (!this.world.isRemote) {

			World world = this.world;
			d = world.provider.getDimension();

			for (int i = 1; i < allsongs; i++) {
				if (songrecord.getStackInSlot(0).getItem() == ItemLoader.song[i]) {
					songid = i;
					songlv = ArcSongsLv.songlv[songid][ftrprspst];
				}
			}

		

			if (songrecord.getStackInSlot(0).isEmpty()) {
				// int songid =tileEntity1.getsongid();

				// NetworkManagers.wrapper.sendToAll(new MessageStopMusic(pos,
				// world.provider.getDimension(), songid));
				// NetworkManagers.wrapper.sendToAll(new MessageStopMusic(pos,
				// world.provider.getDimension(), songid,
				for(EntityPlayerMP playermp : Mukaimods.mcserver.getPlayerList().getPlayers()) {
					PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
					packetbuffer.writeString("player");
					packetbuffer.writeString("mukaimods:mukaimods:" + songid);
					playermp.connection.sendPacket(new SPacketCustomPayload("MC|StopSound", packetbuffer));
					
				}

				// ��⳪Ƭ�Ƿ�ȡ����ȡ����ȡ������

				songlv = 0f;
				timeswitch = 0;
				lock = 0;
				songid = 0;
			}

			songalltime = ArcSongsTime.songtime[songid];
			String[] time = songalltime.split(":");
			int min1 = Integer.parseInt(time[0]);
			int sec = Integer.parseInt(time[1]);
			songalltimesec = min1 * 60 + sec;

			ItemStack item = songrecord.getStackInSlot(0);
			recorddamage = item.getItemDamage();

			if (timeswitch == 0) {
				nowtime = 0;
				maketime = 0;
			}
			if (timeswitch == 1) {
				lock = 1;
				if (nowtime < songalltimesec * 20) {
					nowtime = nowtime + 1;
					if (makeloop == 1) {

						if (maketime < ftrprspsttime * 20) {
							maketime = maketime + 1;
						} else if (maketime >= ftrprspsttime * 20) {
							// ���︴�ƴ���

							ItemStackHandler additem = this.musicinventory;
							Random random = new Random();
							int s = 1;
							int x = random.nextInt(4);
							ItemStack[] musics = new ItemStack[4];
							musics[0] = new ItemStack(ItemLoader.itemPureMax);
							musics[1] = new ItemStack(ItemLoader.itemPure);
							musics[2] = new ItemStack(ItemLoader.itemFar);
							musics[3] = new ItemStack(ItemLoader.itemLost);
//							double ptt = 12.56;
//							double puremax = 0.5 + ptt * 0.03 + 11.2 * 0.01;
//							double pure = 1 - puremax;
//							double far = 1 - puremax - pure;
//							double lost = 1 - puremax - pure - far;

							if (x == 0) {
								for (int i = 1; i < additem.getSlots(); i++) {
									if (additem.getStackInSlot(i).getItem() == ItemLoader.itemPureMax) {
										if (additem.getStackInSlot(i).getCount() + s >= 64) {
										}
										if (additem.getStackInSlot(i).getCount() + s <= 64) {
											additem.setStackInSlot(i, new ItemStack(additem.getStackInSlot(i).getItem(),
													(additem.getStackInSlot(i).getCount() + s)));
											s = 0;
										}
									} else if (additem.getStackInSlot(i).getItem() == Items.AIR) {

										additem.setStackInSlot(i, new ItemStack(ItemLoader.itemPureMax, s));
										s = 0;
									}
								}
							}
							if (x == 1) {
								for (int i = 1; i < additem.getSlots(); i++) {
									if (additem.getStackInSlot(i).getItem() == ItemLoader.itemPure) {
										if (additem.getStackInSlot(i).getCount() + s >= 64) {
										}
										if (additem.getStackInSlot(i).getCount() + s <= 64) {
											additem.setStackInSlot(i, new ItemStack(additem.getStackInSlot(i).getItem(),
													(additem.getStackInSlot(i).getCount() + s)));
											s = 0;
										}
									} else if (additem.getStackInSlot(i).getItem() == Items.AIR) {

										additem.setStackInSlot(i, new ItemStack(ItemLoader.itemPure, s));
										s = 0;
									}
								}
							}
							if (x == 2) {
								for (int i = 1; i < additem.getSlots(); i++) {
									if (additem.getStackInSlot(i).getItem() == ItemLoader.itemFar) {
										if (additem.getStackInSlot(i).getCount() + s >= 64) {

										}
										if (additem.getStackInSlot(i).getCount() + s <= 64) {
											additem.setStackInSlot(i, new ItemStack(additem.getStackInSlot(i).getItem(),
													(additem.getStackInSlot(i).getCount() + s)));
											s = 0;
										}
									} else if (additem.getStackInSlot(i).getItem() == Items.AIR) {

										additem.setStackInSlot(i, new ItemStack(ItemLoader.itemFar, s));
										s = 0;
									}
								}
							}
							if (x == 3) {
								for (int i = 1; i < additem.getSlots(); i++) {
									if (additem.getStackInSlot(i).getItem() == ItemLoader.itemLost) {
										if (additem.getStackInSlot(i).getCount() + s >= 64) {
										}
										if (additem.getStackInSlot(i).getCount() + s <= 64) {
											additem.setStackInSlot(i, new ItemStack(additem.getStackInSlot(i).getItem(),
													(additem.getStackInSlot(i).getCount() + s)));
											s = 0;
										}
									} else if (additem.getStackInSlot(i).getItem() == Items.AIR) {

										additem.setStackInSlot(i, new ItemStack(ItemLoader.itemLost, s));
										s = 0;
									}
								}
							}

							if (s == 1) {
								world.getBlockState(this.getPos()).getBlock();
								Block.spawnAsEntity(world, pos, musics[x]);
							}

							maketime = 0;
						}
					}
				} else if (nowtime >= songalltimesec * 20) {

					if (loop == 0) {
						timeswitch = 0;
						makeloop = 0;
						nowtime = 0;
						maketime = 0;
						lock = 0;
						// play = 1;
						loop = 0;
					} else if (loop == 1) {

						if (recorddamage >= 20) {

							loop = 1;
							lock = 1;
							timeswitch = 1;
							makeloop = 1;

							NetworkManagers.wrapper.sendToServer(
									new MessageMakeMusic(pos, d, songid, musicsize, loop, lock, timeswitch, makeloop));
							for (int allplayer = 0; allplayer < Mukaimods.mcserver.getPlayerList().getCurrentPlayerCount(); allplayer++) {
								EntityPlayer player = Mukaimods.mcserver.getPlayerList().getPlayers().get(allplayer);
								EntityPlayerMP playermp = (EntityPlayerMP) player;
								playermp.connection.sendPacket(new SPacketCustomSound("mukaimods:mukaimods:" + songid,
										 SoundCategory.RECORDS, pos.getX(), pos.getY(), pos.getZ(), musicsize, 1));
							}
								
						/*
							world.playSound((EntityPlayer) null, pos.getX(), pos.getY(), pos.getZ(),
									ArcMusic.songs1[songid], SoundCategory.PLAYERS, musicsize, 1.0F);
									*/
							nowtime = 0;
							// world.sendPacketToServer(new SPacketCustomSound("mukaimods:mukaimods:" +
							// songid,
							// SoundCategory.RECORDS, pos.getX(), pos.getY(), pos.getZ(), musicsize, 1));

							// world.sendPacketTo(new SPacketCustomSound("mukaimods:mukaimods:" + songid,
							// SoundCategory.RECORDS, pos.getX(), pos.getY(), pos.getZ(), 1, 1));
							// world.playSound((EntityPlayer) null, block.getX(), block.getY(),

						} else if (recorddamage < 20) {
							loop = 0;

							timeswitch = 0;
							makeloop = 0;
							nowtime = 0;
							maketime = 0;
							lock = 0;
							songrecord.setStackInSlot(0, new ItemStack(ItemLoader.itemVoidRecord));
						}
					}

				}

			}
		}
		/*
		 * if (play == 1) {
		 * 
		 * world.playSound(pos.getX(), pos.getY(), pos.getZ(), ArcMusic.songs1[songid],
		 * SoundCategory.RECORDS, musicsize, 1, false); world.playSound((EntityPlayer)
		 * null, pos.getX(), pos.getY(), pos.getZ(), ArcMusic.songs1[songid],
		 * SoundCategory.RECORDS, musicsize, 1); }
		 */
	}

}
