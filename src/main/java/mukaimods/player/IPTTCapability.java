package mukaimods.player;

import java.util.TreeSet;

import javax.annotation.Nullable;

import mukaimods.Mukaimods;
import mukaimods.player.PTTCapabilityList.Difficulty;
import mukaimods.player.PTTCapabilityList.PTTCapabilityHandler.Entry;
import net.minecraft.util.ResourceLocation;

public interface IPTTCapability {
	ResourceLocation ID = Mukaimods.prefix("player_ptt");

	public TreeSet<Entry> getb30();

	public TreeSet<Entry> getr30();

	public void setb30(TreeSet<Entry> b30);
	
	public void setr30(TreeSet<Entry> r30);

	public double getptt();

	public void update(int songId, Difficulty difficulty, double ptt, long time, boolean ex);
	
	public void updatePtt(@Nullable Double ptt);

}
