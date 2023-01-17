package mukaimods.gui;

import java.io.IOException;

import io.netty.buffer.Unpooled;
import mukaimods.Mukaimods;
import mukaimods.gui.container.ConitainerMakeMusic;
import mukaimods.internet.MessageMakeMusic;
import mukaimods.internet.NetworkManagers;
import mukaimods.item.ItemLoader;
import mukaimods.sounds.ArcMusic;
import mukaimods.sounds.ArcSongsLv;
import mukaimods.sounds.ArcSongsTime;
import mukaimods.tileentity.TileEntityMakeMusic;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class GuiMakeMusic extends GuiContainer {

	static int allsong = ArcMusic.allsong;
	private static final String TEXTURE_PATH = Mukaimods.MODID + ":" + "textures/gui/container/playrecord.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

	private static final String[] TEXTURE_song = new String[allsong + 5];
	static {

		for (int i = 0; i < allsong; i++) {
			TEXTURE_song[i] = Mukaimods.MODID + ":" + "textures/gui/container/" + i + ".png";
		}
	}
	public final static ResourceLocation[] TEXTUREsongs = new ResourceLocation[allsong + 5];

	static {

		for (int i = 0; i < allsong; i++) {
			TEXTUREsongs[i] = new ResourceLocation(TEXTURE_song[i]);
		}
	}

	private TileEntityMakeMusic tileEntity1;
	int ids;
	private String hour2;
	private String min2;
	private String sec2;
	double lv;
	int lvs;
	int size;
	int ftrprspsttime;
	static float songside = 1;
	int nowtime;
	int maketime;
	protected ConitainerMakeMusic inventory;
	private static String[] badrecord = new String[2];
	static {
		badrecord[0] = "";
		badrecord[1] = "Your record is broken!";
	}
	static int loop;
	int lock;
	int timeswitch;
	int makeloop;
	int modeswitch;
	boolean stopmusic;
	private static final int MusicSizeAdd = 0;
	private static final int MusicSizeLess = 1;
	private static final int MusicMode = 2;
	private static final int Start = 3;
	private static String[] mode = new String[2];
	{
		mode[0] = "One";
		mode[1] = "Loop";
	}

	public GuiMakeMusic(ConitainerMakeMusic inventorySlotsIn, TileEntity tileEntity) {
		super(inventorySlotsIn);
		this.xSize = 240;
		this.ySize = 200;
		this.inventory = inventorySlotsIn;
		this.tileEntity1 = (TileEntityMakeMusic) tileEntity;
		this.lvs = tileEntity1.getftrprspst();

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

		this.mc.getTextureManager().bindTexture(TEXTUREsongs[ids]);
		int offsetX11 = 18 + (this.width - this.xSize) / 2, offsetY11 = 30 + (this.height - this.ySize) / 2;
		this.drawScaledTexturedModelRect(offsetX11 - 10, offsetY11 + 2, 64, 64, 0, 0, 256, 256);
		// �޸������СΪ64,64������ͼƬ�ߴ�Ϊ256,256��

	}

	public void drawTexturedModalRect(float x, float y, float textureX, float textureY, float width, float height) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos((double) (x + 0), (double) (y + height), (double) this.zLevel)
				.tex((double) ((float) (textureX + 0) * 0.00390625F),
						(double) ((float) (textureY + height) * 0.00390625F))
				.endVertex();
		bufferbuilder.pos((double) (x + width), (double) (y + height), (double) this.zLevel)
				.tex((double) ((float) (textureX + width) * 0.00390625F),
						(double) ((float) (textureY + height) * 0.00390625F))
				.endVertex();
		bufferbuilder.pos((double) (x + width), (double) (y + 0), (double) this.zLevel)
				.tex((double) ((float) (textureX + width) * 0.00390625F),
						(double) ((float) (textureY + 0) * 0.00390625F))
				.endVertex();
		bufferbuilder.pos((double) (x + 0), (double) (y + 0), (double) this.zLevel)
				.tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F))
				.endVertex();
		tessellator.draw();

	}

	// czm�Ĵ���
	public void drawScaledTexturedModelRect(int x, int y, int sx, int sy, int tx, int ty, int tsx, int tsy) {
//		Minecraft mc = Minecraft.getMinecraft();
		double scaleX = (double) sx / (double) tsx;
		double scaleY = (double) sy / (double) tsy;
		GlStateManager.scale(scaleX, scaleY, 1.0);

		this.drawTexturedModalRect((float) (x / scaleX), (float) (y / scaleY), (float) tx, (float) ty, (float) tsx,
				(float) tsy);
		GlStateManager.scale(1 / scaleX, 1 / scaleY, 1.0);
	}
	// czm�Ĵ���

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		/*
		 * this.drawVerticalLine(30, 19, 36, 0xFF000000); this.drawHorizontalLine(8,
		 * 167, 43, 0xFF000000);
		 */
		this.ftrprspsttime = inventory.getftrprspsttime();
		this.size = inventory.getsizes();
		this.lock = inventory.getlocks();
		this.nowtime = inventory.getnowtimes();
		this.maketime = inventory.getmaketimes();

		ItemStackHandler record = tileEntity1.songrecord;
		for (int i = 1; i < allsong; i++) {
			if (record.getStackInSlot(0).getItem() == ItemLoader.song[i]) {
				ids = i;
				lv = ArcSongsLv.songlv[ids][lvs];

			}
			if (record.getStackInSlot(0).getItem() == Items.AIR) {
				ids = 0;
				lv = 0;
			}
			String title = I18n.format("container.makemusic");
			this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 4,
					0x404040);
			String Names = I18n.format("Name:");
			this.fontRenderer.drawString(Names, 80, 27, 0x404040);
			String Songname = I18n.format("songsname" + ids);
			this.fontRenderer.drawString(Songname, 80, 37, 0x404040);
			String Songtime = I18n.format("Songtime:");
			this.fontRenderer.drawString(Songtime, 80, 47, 0x404040);
			String Songtimes = I18n.format("          " + ArcSongsTime.songtime[ids]);
			this.fontRenderer.drawString(Songtimes, 80, 47, 0x404040);
			String Songlv = I18n.format("Songlv:");
			this.fontRenderer.drawString(Songlv, 80, 57, 0x404040);
			String Songlvs = I18n.format("     " + lv);
			this.fontRenderer.drawString(Songlvs, 80, 57, 0x404040);
			int now;
			now = (nowtime / 20);
			int hour3 = now / 3600;
			int min3 = (now - (hour3 * 3600)) / 60;
			int sec3 = now - (hour3 * 3600) - (min3 * 60);
			for (int i1 = 0; i1 <= 9; i1++) {
				if (hour3 <= i1) {
					hour2 = "0" + hour3;
				}

				if (min3 <= i1) {
					min2 = "0" + min3;
				}

				if (sec3 <= i1) {
					sec2 = "0" + sec3;
				}
				if (hour3 > i1) {
					hour2 = "" + hour3;
				}

				if (min3 > i1) {
					min2 = "" + min3;
				}

				if (sec3 > i1) {
					sec2 = "" + sec3;
				}
			}
			String Nowtime = I18n.format("NowTime:");
			this.fontRenderer.drawString(Nowtime, 80, 67, 0x404040);
			String Puremax = I18n.format("        " + hour2 + ":" + min2 + ":" + sec2);
			this.fontRenderer.drawString(Puremax, 80, 67, 0x404040);
			String Far = I18n.format("Making:");
			this.fontRenderer.drawString(Far, 80, 87, 0x404040);
			String make = I18n.format(" " + (ftrprspsttime - (maketime / 20)));
			this.fontRenderer.drawString(make, 80, 87, 0x404040);

			String Musicside = I18n.format("Side:");
			this.fontRenderer.drawString(Musicside, 80, 77, 0x404040);
			String Musicsides = I18n.format("     " + songside);
			this.fontRenderer.drawString(Musicsides, 80, 77, 0x404040);
			String Musicmode = I18n.format("Musicmode:" + mode[loop]);
			this.fontRenderer.drawString(Musicmode, 85, 15, 0x404040);
			String inventory = I18n.format("");
			this.fontRenderer.drawString(inventory, 180, 9, 0x404040);
			/*
			 * if (stopmusic == true) { for (int allplayer = 0; allplayer <
			 * Mukaimods.mcserver.getPlayerList().getMaxPlayers(); allplayer++) {
			 * 
			 * EntityPlayer player =
			 * Mukaimods.mcserver.getPlayerList().getPlayers().get(allplayer);
			 * 
			 * EntityPlayerMP playermp = (EntityPlayerMP) player; PacketBuffer packetbuffer
			 * = new PacketBuffer(Unpooled.buffer()); packetbuffer.writeString("player");
			 * packetbuffer.writeString("mukaimods:mukaimods:" + ids);
			 * playermp.connection.sendPacket(new SPacketCustomPayload("MC|StopSound",
			 * packetbuffer)); stopmusic = false; } }
			 */
		}

	}

	@Override
	public void initGui() {
		super.initGui();
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.buttonList.add(new GuiButton(MusicSizeAdd, offsetX + 127, offsetY + 77, 10, 8, "+"));
		this.buttonList.add(new GuiButton(MusicSizeLess, offsetX + 114, offsetY + 77, 10, 8, "-"));
		this.buttonList.add(new GuiButton(MusicMode, offsetX + 146, offsetY + 14, 23, 12, "Mode"));
		this.buttonList.add(new GuiButton(Start, offsetX + 40, offsetY + 12, 30, 14, "Start"));
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		try {
			World world = tileEntity1.getWorld();
			int dime = world.provider.getDimension();
			BlockPos block = tileEntity1.getPos();
			// World world = Minecraft.getMinecraft().player.world;
			switch (button.id) {
			case MusicSizeAdd:
				stopmusic = true;
				if (lock == 0) {
					songside = songside + 1;
					if (songside > 10) {
						songside = 10;
					}
				}
				break;
			case MusicSizeLess:
				if (lock == 0) {
					if (songside > 0) {
						songside = songside - 1;
					}
					if (songside < 0) {
						songside = 0;
					}
				}
				break;
			case MusicMode:
				if (lock == 0) {
					// if (loop < 2) {
					loop = loop + 1;

					if (loop > 1) {
						loop = 0;
					}
					// }
				}
				break;
			case Start:
				if (lock == 0) {
					timeswitch = 1;
					makeloop = 1;
					lock = 1;
					NetworkManagers.wrapper.sendToServer(
							new MessageMakeMusic(block, dime, ids, songside, loop, lock, timeswitch, makeloop));
				//	world.plays
					for (int allplayer = 0; allplayer < Mukaimods.mcserver.getPlayerList().getCurrentPlayerCount(); allplayer++) {

						EntityPlayer player1 = Mukaimods.mcserver.getPlayerList().getPlayers().get(allplayer);

						EntityPlayerMP playermp = (EntityPlayerMP) player1;
						PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
						packetbuffer.writeString("player");
						packetbuffer.writeString("mukaimods:mukaimods:" + ids);
						playermp.connection.sendPacket(new SPacketCustomSound("mukaimods:" + ids,
								 SoundCategory.RECORDS, block.getX(), block.getY(), block.getZ(), songside, 1));
						
						
					}

				}

				break;

			default:
				super.actionPerformed(button);
				return;
			}
		} catch (Exception e) {
			System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
	}

}
