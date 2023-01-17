package mukaimods.player;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import javax.annotation.Nullable;

import mukaimods.player.PTTCapabilityList.PTTCapabilityHandler.Entry;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PTTCapabilityList {
	public static class PTTCapabilityStorage implements Capability.IStorage<IPTTCapability> {
//		static int allsong = ArcMusic.allsong;

		@Override
		public NBTBase writeNBT(Capability<IPTTCapability> capability, IPTTCapability instance, EnumFacing side) {
			NBTTagList b30list = new NBTTagList();
			TreeSet<Entry> b30 = instance.getb30();
			int count = 0;
			for(Iterator<Entry> iter = b30.iterator(); iter.hasNext();) {
				NBTTagCompound b30compound = new NBTTagCompound();
				Entry entry = iter.next();
				b30compound.setInteger("songid" + count, entry.song.songId);
				b30compound.setInteger("difficulty" + count, entry.song.difficulty.id);
				b30compound.setDouble("ptt" + count, entry.ptt);
				b30compound.setLong("time" + count, entry.time);
				b30list.appendTag(b30compound);
				count++;
	        }

			NBTTagList r30list = new NBTTagList();
			TreeSet<Entry> r30 = instance.getr30();
			count = 0;
			for(Iterator<Entry> iter = r30.iterator(); iter.hasNext();) {
				NBTTagCompound r30compound = new NBTTagCompound();
				Entry entry = iter.next();
				r30compound.setInteger("songid" + count, entry.song.songId);
				r30compound.setInteger("difficulty" + count, entry.song.difficulty.id);
				r30compound.setDouble("ptt" + count, entry.ptt);
				r30compound.setLong("time" + count, entry.time);
				r30list.appendTag(r30compound);
				count++;
	        }

			NBTTagCompound PTT = new NBTTagCompound();
			PTT.setTag("B30", b30list);
			PTT.setTag("R30", r30list);
			return PTT;

		}

		@Override
		public void readNBT(Capability<IPTTCapability> capability, IPTTCapability instance, EnumFacing side,
				NBTBase nbt) {
			NBTTagCompound ptt = (NBTTagCompound) nbt;
			TreeSet<Entry> b30 = new TreeSet<>((x, y) -> Double.compare(x.ptt, y.ptt));
			TreeSet<Entry> r30 = new TreeSet<>((x, y) -> Double.compare(x.ptt, y.ptt));
			if (ptt.hasKey("B30")) {
				NBTTagList b30list = (NBTTagList) ptt.getTag("B30");
				for (int i = 0; i < b30list.tagCount(); ++i) {
					NBTTagCompound tag = b30list.getCompoundTagAt(i);
					b30.add(new Entry(tag.getInteger("songid" + i),
							Difficulty.getDifficulty(tag.getInteger("difficulty" + i)), tag.getDouble("ptt" + i),
							tag.getLong("time" + i)));
				}
			}
			if (ptt.hasKey("R30")) {
				NBTTagList r30list = (NBTTagList) ptt.getTag("R30");
				for (int i = 0; i < r30list.tagCount(); ++i) {
					NBTTagCompound tag = r30list.getCompoundTagAt(i);
					r30.add(new Entry(tag.getInteger("songid" + i),
							Difficulty.getDifficulty(tag.getInteger("difficulty" + i)), tag.getDouble("ptt" + i),
							tag.getLong("time" + i)));
				}
			}
			instance.setb30(b30);
			instance.setr30(r30);
		}
	}

	public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound> {
		private IPTTCapability ptts = new PTTCapabilityHandler();
		private IStorage<IPTTCapability> storage = CapabilityLoader.PTT.getStorage();

		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return CapabilityLoader.PTT.equals(capability);
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			if (CapabilityLoader.PTT.equals(capability)) {
				@SuppressWarnings("unchecked")
				T result = (T) ptts;
				return result;
			}
			return null;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			NBTTagCompound compound = new NBTTagCompound();
			compound.setTag("ptts", storage.writeNBT(CapabilityLoader.PTT, ptts, null));
			return compound;
		}

		@Override
		public void deserializeNBT(NBTTagCompound compound) {
			NBTTagCompound list = (NBTTagCompound) compound.getTag("ptts");
			storage.readNBT(CapabilityLoader.PTT, ptts, null, list);
		}
	}

	public static class PTTCapabilityHandler implements IPTTCapability {
		private double ptt;
		public static class SongKey {
			private int songId;
			private Difficulty difficulty;

			public SongKey(int songId, Difficulty difficulty) {
				super();
				this.songId = songId;
				this.difficulty = difficulty;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + difficulty.id;
				result = prime * result + songId;
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				SongKey other = (SongKey) obj;
				if (difficulty != other.difficulty)
					return false;
				if (songId != other.songId)
					return false;
				return true;
			}

		}

		public static class Entry {
			private SongKey song;
			private double ptt;
			private long time;

			public Entry(int songId, Difficulty difficulty, double ptt, long time) {
				super();
				this.song = new SongKey(songId, difficulty);
				this.ptt = ptt;
				this.time = time;
			}

		};

		TreeSet<Entry> b30 = new TreeSet<>((x, y) -> Double.compare(x.ptt, y.ptt));
		TreeSet<Entry> r30 = new TreeSet<>((x, y) -> Double.compare(x.time, y.time));

		public void update(int songId, Difficulty difficulty, double ptt, long time, boolean ex) {
			Entry e = new Entry(songId, difficulty, ptt, time);
			// b30
			Entry eq = b30.stream().filter(x -> x.song.equals(e.song)).findAny().orElse(null);
			if (eq != null) {
				if(eq.ptt <= ptt) {
					b30.remove(eq);
					b30.add(e);
				}
			} else {
				if (b30.size() < 30) {
					b30.add(e);
				} else {
					if (e.ptt > b30.first().ptt) {
						b30.pollFirst();
						b30.add(e);
					}
				}
			}
			// r10
			if (r30.size() < 30) {
				r30.add(e);
			} else {
				Entry toRemove;
				if (ex) { // 写出ptt最小的
					toRemove = r30.stream().sorted((x, y) -> Double.compare(x.ptt, y.ptt)).findFirst().get();
					if (ptt <= toRemove.ptt) {
						return;
					}
				} else { // 写出最早的
					toRemove = r30.first();
				}
				int distinct = (int) r30.stream().map(x -> x.song).distinct().count();
				int countOld = (int) r30.stream().filter(x -> x.song.equals(toRemove.song)).count();
				int countNew = (int) r30.stream().filter(x -> x.song.equals(e.song)).count();
				if (!e.song.equals(toRemove.song) && countNew > 0 && countOld <= 1 && distinct <= 10) { // 判断是否会让歌曲数减少
					Entry f;
					if (ex) { // 如果是，且ex，则在同一首歌里写出ptt最小的
						f = r30.stream().filter(x -> x.song.equals(e.song)).sorted((x, y) -> Double.compare(x.ptt, y.ptt))
								.findFirst().get();
						if (ptt <= f.ptt) {
							return;
						}
					} else { // 否则在同一首歌里写出最早的
						f = r30.stream().filter(x -> x.song.equals(e.song)).findFirst().get();
					}
					r30.remove(f);
				} else {
					r30.remove(toRemove);
				}
				r30.add(e);
			}
			updatePtt(null);
		}

		
		@Override
		// 参数仅用于由指令改变ptt
		public void updatePtt(@Nullable Double ptt) {
			if (ptt != null) {
				this.ptt = ptt;
				return;
			}
			double sum1 = b30.stream().mapToDouble(x -> x.ptt).sum();
			HashSet<SongKey> songs = new HashSet<>();
			for (Entry e : r30.descendingSet()) {
				if (!songs.contains(e.song)) {
					songs.add(e.song);
					sum1 += e.ptt;
					if (songs.size() == 10) {
						break;
					}
				}
			}
			this.ptt =  sum1 / 40.0;
		}
		
		@Override
		public double getptt() {
			DecimalFormat df = new DecimalFormat(".00");
			return new Double(df.format(this.ptt));
		}


		@Override
		public TreeSet<Entry> getb30() {
			return b30;
		}


		@Override
		public TreeSet<Entry> getr30() {
			return r30;
		}


		@Override
		public void setb30(TreeSet<Entry> b30) {
			this.b30 = b30;
		}


		@Override
		public void setr30(TreeSet<Entry> r30) {
			this.r30 = r30;
		}
	}
		/*static int allsong = ArcMusic.allsong;
		HashMap<Binary, Double> bestscores = new HashMap<>();
		ArrayList<Quaternion> recent = new ArrayList<>();
		private double ptt = 0;
		private double[] b30 = new double[30];
		private double[] r10 = new double[10];
		private double[] r30 = new double[30];
		private double[] copyr30 = new double[30];

		@Override
		public void updateb30(Difficulty difficuty, int songid, double oneb30) {
			Binary score = new Binary(songid, difficuty);
			if (!bestscores.containsKey(score)) // 若未存在打歌记录，则直接加入
				bestscores.put(score, oneb30);
			else if (bestscores.get(score) < oneb30) // 仅在推分时刷新记录
					bestscores.replace(score, oneb30);
			ArrayList<Double> list = new ArrayList<>();
			for(double d : bestscores.values())
				list.add(d);
			Collections.sort(list, (s1, s2) -> Double.compare(s2, s1));
			for(int i = 0; i < Math.min(30, list.size()); i++)
				b30[i] = list.get(i);
			updatePTT(null);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void updater10(Difficulty difficuty, int songid, double oner10, boolean isEX) {
			int time = Mukaimods.mcserver.getTickCounter();
			if (recent.isEmpty()) { // 若r30为空则直接写入
				recent.add(new Quaternion(songid, difficuty, oner10, time));
				updatePTT(null);
				return;
			}
			// 尝试写入r30
			ArrayList<Quaternion> copy = (ArrayList<Quaternion>) recent.clone(); 
			int latest = Integer.MAX_VALUE;
			int id = 0;
			for (int i = 0; i < copy.size(); i++) {
				Quaternion q = copy.get(i);
				if (q.time < latest) {
					latest = q.time;
					id = i;
				}
			}
			copy.set(id, new Quaternion(songid, difficuty, oner10, time));
			
			if (isEX) {
				
			}
			/*for (int i = 1; i < r30.length; ++i) {
				r30[i - 1] = r30[i];
			}
			r30[r30.length - 1] = oner10;
			copyr30 = r30.clone();
			Arrays.sort(copyr30);
			int i, j;
			double temp;
			int len = copyr30.length;
			for (i = 0; i < len; i++)
				for (j = len - 2; j >= i; j--)
					if (copyr30[j] < copyr30[j + 1]) {
						temp = copyr30[j];
						copyr30[j] = copyr30[j + 1];
						copyr30[j + 1] = temp;
					}
			for (int x = 0; x < r10.length; x++) {
				r10[x] = copyr30[x];
			}
			updatePTT(null);
		}

		

		@Override
		public double[] getb30() {
			return b30.clone();
		}

		@Override
		public double[] getr10() {
			return r10.clone();
		}

		@Override
		public double getptt() {
			DecimalFormat df = new DecimalFormat(".00");
			return new Double(df.format(this.ptt));
		}

		class Binary {
			int songid;
			Difficulty difficuty;

			Binary(int songid, Difficulty difficuty) {
				this.songid = songid;
				this.difficuty = difficuty;
			}
		}
		
		class Quaternion {
			int songid;
			Difficulty difficulty;
			double score;
			int time;

			Quaternion(int songid, Difficulty difficulty, double score, int time) {
				this.songid = songid;
				this.difficulty = difficulty;
				this.score = score;
				this.time = time;
			}
		}
	}*/

	public enum Difficulty {
		PST(1), PRS(2), FTR(3), BYD(4), LAST_ETERNITY(5), UNKNOWN(6);
		public int id;

		Difficulty(int id) {
			this.id = id;
		}

		public static Difficulty getDifficulty(int id) {
			for (Difficulty dif : Difficulty.values())
				if (dif.id == id)
					return dif;
			return UNKNOWN;
		}
	}
}