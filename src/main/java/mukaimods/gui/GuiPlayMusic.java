package mukaimods.gui;

import java.io.IOException;

import io.netty.buffer.Unpooled;
import mukaimods.Mukaimods;
import mukaimods.gui.container.ContainerPlayMusic;
import mukaimods.internet.MessageFinishSongs;
import mukaimods.internet.MessageSetSongsLv;
import mukaimods.internet.NetworkManagers;
import mukaimods.item.ItemLoader;
import mukaimods.player.PTTCapabilityList.Difficulty;
import mukaimods.sounds.ArcMusic;
import mukaimods.sounds.ArcSongsLv;
import mukaimods.sounds.ArcSongsTime;
import mukaimods.tileentity.TileEntityPlaysongs;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.items.ItemStackHandler;

public class GuiPlayMusic extends GuiContainer {
	static int allsong = ArcMusic.allsong;
	private TileEntityPlaysongs tileEntity1;
	private GuiTextField nameField;

	private int ids = 0;
	private double lv;
	private int lvs;
	private static int makesec;
	private String songtime;
	private static String s = "0";
	private int note;
	private int musicbutton;

	private int[] notes = new int[4];
	private int note4;
	private int timeswitch;

	private int willtime;
	private int dotime;
	private int lvss;
	int lock;
	int j;
	private int marktype;
	private int makemark;
	private String hour2;
	private String min2;
	private String sec2;
	private String nomake[] = new String[2];
	{
		nomake[0]="";
		nomake[1]="Not Enough Material!";
	}
	private static final int PST = 0;
	private static final int PRS = 1;
	private static final int FTR = 2;
	private static final int Make = 3;
	private static final int Change = 4;
	private static final String TEXTURE_PLAY = Mukaimods.MODID + ":" + "textures/gui/container/playmusic.png";

	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PLAY);
	private static final String[] TEXTURE_Music = new String[5];
	static {
		TEXTURE_Music[0] = Mukaimods.MODID + ":" + "textures/item/pure.png";
		TEXTURE_Music[1] = Mukaimods.MODID + ":" + "textures/item/far.png";
		TEXTURE_Music[2] = Mukaimods.MODID + ":" + "textures/item/grass.png";
		TEXTURE_Music[3] = Mukaimods.MODID + ":" + "textures/item/puremax.png";
		TEXTURE_Music[4] = Mukaimods.MODID + ":" + "textures/item/music_emerald.png";
	}
	public final static ResourceLocation[] TEXTUREmusic = new ResourceLocation[5];
	static {

		for (int i = 0; i <= 4; i++) {
			TEXTUREmusic[i] = new ResourceLocation(TEXTURE_Music[i]);
		}
	}
	private static final String[] TEXTURE_song = new String[allsong];
	static {

		for (int i = 0; i <allsong; i++) {
			TEXTURE_song[i] = Mukaimods.MODID + ":" + "textures/gui/container/" + i + ".png";
		}
	}
	public final static ResourceLocation[] TEXTUREsongs = new ResourceLocation[allsong];

	static {

		for (int i = 0; i <allsong; i++) {
			TEXTUREsongs[i] = new ResourceLocation(TEXTURE_song[i]);
		}
	}

	protected ContainerPlayMusic inventory;


	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

		this.mc.getTextureManager().bindTexture(TEXTUREsongs[ids]);
		int offsetX11 = 18 + (this.width - this.xSize) / 2, offsetY11 = 30 + (this.height - this.ySize) / 2;
		//this.drawTexturedModalRect(offsetX11 - 8, offsetY11 + 10, 0, 0, 64, 64);
		this.drawScaledTexturedModelRect(offsetX11-10, offsetY11+10, 64, 64, 0, 0,256,256);
		//修改曲绘大小为64,64，曲绘图片尺寸为256,256，
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
	//czm的代码
	public  void drawScaledTexturedModelRect(int x, int y, int sx, int sy, int tx, int ty, int tsx, int tsy) {
//		Minecraft mc = Minecraft.getMinecraft();
		double scaleX = (double) sx / (double) tsx;
		double scaleY = (double) sy / (double) tsy;
		GlStateManager.scale(scaleX, scaleY, 1.0);
		
		this.drawTexturedModalRect((float) (x / scaleX), (float) (y / scaleY),
				(float) tx, (float) ty, (float) tsx, (float) tsy);
		GlStateManager.scale(1 / scaleX, 1 / scaleY, 1.0);
	}
	//czm的代码
	public GuiPlayMusic(ContainerPlayMusic inventorySlotsIn, TileEntity tileEntity) {
		super(inventorySlotsIn);
		this.xSize = 240;
		this.ySize = 256;
		this.inventory = inventorySlotsIn;
		
		this.tileEntity1 = (TileEntityPlaysongs) tileEntity;
		

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// ItemStack stack = this.music.getStack();
		lvs = this.inventory.getlvs();
		lock = this.inventory.getlock();
//		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		ItemStackHandler record = tileEntity1.songrecord;
		for (int i = 1; i <allsong; i++) {
			if (record.getStackInSlot(0).getItem() == ItemLoader.song[i]) {
				ids = i;
				lv = ArcSongsLv.songlv[ids][lvs];
			}
			if (record.getStackInSlot(0).isEmpty()) {
				ids = 0;
				lv = 0;
			}
		}

		songtime = ArcSongsTime.songtime[ids];
		String[] time = songtime.split(":");
		double min1 = Integer.parseInt(time[0]);
		double sec = Integer.parseInt(time[1]);
		//DecimalFormat number = new DecimalFormat("");
//		double maketime1 = min1 * 60 + sec;

		double notes2 = (min1 * 60 + sec) * ArcSongsLv.songlv[ids][lvs] * 0.1;
		double note11 = Math.ceil(notes2);
		note = (int) note11;

		String Songname = I18n.format("songsname"+ids);
		this.fontRenderer.drawString(Songname, 80, 47, 0x404040);
		String Songnames = I18n.format("SongsName:");
		this.fontRenderer.drawString(Songnames, 80, 37, 0x404040);
		String Songtime = I18n.format("Songtime:");
		this.fontRenderer.drawString(Songtime, 80, 57, 0x404040);
		String Songtime1 = I18n.format("          " + ArcSongsTime.songtime[ids]);
		this.fontRenderer.drawString(Songtime1, 80, 57, 0x404040);
		String Songlv = I18n.format("Songlv:");
		this.fontRenderer.drawString(Songlv, 80, 67, 0x404040);
		String Songlv1 = I18n.format("          " + lv);
		this.fontRenderer.drawString(Songlv1, 80, 67, 0x404040);
//		int sectime = (int) maketime1;

		makesec = (note * 30) + (notes[0] * 60) + (notes[1] * 30) - (notes[2] * 30) + (notes[3] * 150) - (note4 * 30);

		if (note == 0) {
			j = 0;
		}
		if (note != 0) {
			j = 10000000 / note;
		}

		makemark = ((j * notes[0]) * 1) + ((j * notes[1]) / 2) + ((j * notes[2]) * 0) + ((j * notes[3]) + notes[3]);
		if (notes[0] + notes[1] + notes[2] + notes[3] == 0) {
			makemark = 0;
		} else if (notes[3] == note) {
			makemark = 10000000 + (notes[3] * 1);
			marktype = 0;
		} else if (notes[0] == note) {
			makemark = 10000000;
			marktype = 1;
		} else if (notes[1] + notes[2] == 0) {
			makemark = 10000000 + (notes[3] * 1);
			marktype = 1;
		} else if (notes[2] == 0) {
			marktype = 2;
		} else if (makemark < 8600000) {
			marktype = 4;
		} else {
			marktype = 3;
		}

		int hour1 = makesec / 3600;
		int min = (makesec - (hour1 * 3600)) / 60;
		int sec1 = makesec - (hour1 * 3600) - (min * 60);
		hour2 = "" + hour1;
		min2 = "" + min;
		sec2 = "" + sec1;
		for (int i = 0; i <= 9; i++) {
			if (hour1 <= i) {
				hour2 = "0" + hour1;
			}

			if (min <= i) {
				min2 = "0" + min;
			}

			if (sec1 <= i) {
				sec2 = "0" + sec1;
			}
		}
		String musicmaketime = hour2 + ":" + min2 + ":" + sec2;

		// int downtime=makesec-(tileEntity1.getdoing()/20);
//		int downtime = tileEntity1.getdoing();
		dotime = this.inventory.getdoingtime();

		willtime = makesec - (dotime / 20);
		int hour3 = willtime / 3600;
		int min3 = (willtime - (hour3 * 3600)) / 60;
		int sec3 = willtime - (hour3 * 3600) - (min3 * 60);
		for (int i = 0; i <= 9; i++) {
			if (hour3 <= i) {
				hour2 = "0" + hour3;
			}

			if (min3 <= i) {
				min2 = "0" + min3;
			}

			if (sec3 <= i) {
				sec2 = "0" + sec3;
			}
			if (hour3 > i) {
				hour2 = ""+ hour3;
			}

			if (min3 >i) {
				min2 =  ""+min3;
			}

			if (sec3 > i) {
				sec2 = "" + sec3;
			}
		}
		
		int no =this.inventory.getnomake();
		String musicnumber = I18n.format("Musicnum:");
		this.fontRenderer.drawString(musicnumber, 193, 23, 0x404040);		
		this.fontRenderer.drawString(musicnumber, 193, 38, 0x404040);		
		this.fontRenderer.drawString(musicnumber, 193, 50, 0x404040);		
		this.fontRenderer.drawString(musicnumber, 193, 62, 0x404040);		
		this.fontRenderer.drawString(musicnumber, 193, 74, 0x404040);
		String NoMakes = I18n.format(  "" + nomake[no]);
		this.fontRenderer.drawString(NoMakes, 90, 141, 0xCC0000);
		String Maketime = I18n.format( "Maketime:");
		this.fontRenderer.drawString(Maketime, 80, 87, 0x404040);
		String Maketime1 = I18n.format( "          "+musicmaketime );
		this.fontRenderer.drawString(Maketime1, 80, 87, 0x404040);
		String Makemin = I18n.format(  "Nowtime:");
		this.fontRenderer.drawString(Makemin, 80, 97, 0x404040);
		String Makemin1 = I18n.format(  "          "+hour2+":"+min2+":"+sec2);
		this.fontRenderer.drawString(Makemin1, 80, 97, 0x404040);
		String Result = I18n.format("Result:" );
		this.fontRenderer.drawString(Result, 80, 107, 0x404040);
		String Result1 = I18n.format("      " + makemark);
		this.fontRenderer.drawString(Result1, 80, 107, 0x404040);
		String notenumber = I18n.format("Note:" );
		this.fontRenderer.drawString(notenumber, 80, 77, 0x404040);
		String notenumber1 = I18n.format("          " + note);
		this.fontRenderer.drawString(notenumber1, 80, 77, 0x404040);
		String[] musictype = new String[5];
		musictype[0] = "Pure";
		musictype[1] = "Far";
		musictype[2] = "Lost";
		musictype[3] = "Pure(Max)";
		musictype[4] = "Musicore";
		String now = I18n.format("Now:" + musictype[musicbutton]);
		this.fontRenderer.drawString(now, 80, 116, 0x404040);
		if (dotime == 0) {
			timeswitch = 0;
		}

		ItemStackHandler musics = tileEntity1.pureinventory;
		// Item far1= fars.getStackInSlot(0).getItem();
		// ItemStack stack = this.music.getStack();
		// Item musicitem =stack.getItem();
		int musicores[] = new int[musics.getSlots()];
		for (int i = 1; i < musics.getSlots(); i++) {
			if (musics.getStackInSlot(i).getItem() == ItemLoader.itemGemMusic) {
				musicores[i] = musics.getStackInSlot(i).getCount();
			}
		}
		int musicoreall = 0;
		for (int i1 = 0; i1 < musicores.length; i1++) {
			musicoreall += musicores[i1];
		}

		int pures[] = new int[musics.getSlots()];
		for (int i = 1; i < musics.getSlots(); i++) {
			if (musics.getStackInSlot(i).getItem() == ItemLoader.itemPure) {
				pures[i] = musics.getStackInSlot(i).getCount();
			}
		}
		int pureall = 0;
		for (int i1 = 0; i1 < pures.length; i1++) {
			pureall += pures[i1];
		}

		int puremaxs[] = new int[musics.getSlots()];
		for (int i = 1; i < musics.getSlots(); i++) {
			if (musics.getStackInSlot(i).getItem() == ItemLoader.itemPureMax) {
				puremaxs[i] = musics.getStackInSlot(i).getCount();
			}
		}
		int puremaxall = 0;
		for (int i1 = 0; i1 < puremaxs.length; i1++) {
			puremaxall += puremaxs[i1];
		}

		int fars[] = new int[musics.getSlots()];
		for (int i = 1; i < musics.getSlots(); i++) {
			if (musics.getStackInSlot(i).getItem() == ItemLoader.itemFar) {
				fars[i] = musics.getStackInSlot(i).getCount();
			}
		}
		int farall = 0;
		for (int i1 = 0; i1 < fars.length; i1++) {
			farall += fars[i1];
		}

		int losts[] = new int[musics.getSlots()];
		for (int i = 1; i < musics.getSlots(); i++) {
			if (musics.getStackInSlot(i).getItem() == ItemLoader.itemLost) {
				losts[i] = musics.getStackInSlot(i).getCount();
			}
		}
		int lostall = 0;
		for (int i1 = 0; i1 < losts.length; i1++) {
			lostall += losts[i1];
		}
		
		this.drawHorizontalLine(60, 79, 33, 0xFF000000);
		this.drawHorizontalLine(101, 119, 33, 0xFF000000);
		this.drawHorizontalLine(140, 158, 33, 0xFF000000);
		this.drawHorizontalLine(135, 164, 138, 0xFF000000);
		this.drawHorizontalLine(90, 123, 166, 0xFF000000);
		String Musicorenumber = I18n.format("     " + musicoreall);
		this.fontRenderer.drawString(Musicorenumber, 193, 23, 0x404040);
		String PureMaxnumber = I18n.format("     " + puremaxall);
		this.fontRenderer.drawString(PureMaxnumber, 193, 38, 0x404040);
		String Purenumber = I18n.format("     " + pureall);
		this.fontRenderer.drawString(Purenumber, 193, 50, 0x404040);
		String Farnumber = I18n.format("     " + farall);
		this.fontRenderer.drawString(Farnumber, 193, 62, 0x404040);
		String Lostnumber = I18n.format("     " + lostall);
		this.fontRenderer.drawString(Lostnumber, 193, 74, 0x404040);
		
		


		String title = I18n.format("mukaismod.playmusic");
		this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);

		ItemStack music = new ItemStack(ItemLoader.itemGemMusic);
		this.itemRender.renderItemAndEffectIntoGUI(music, 175, 20);
		this.itemRender.renderItemAndEffectIntoGUI(music, 7, 107);
		ItemStack puremax = new ItemStack(ItemLoader.itemPureMax);
		this.itemRender.renderItemAndEffectIntoGUI(puremax, 175, 35);
		this.itemRender.renderItemAndEffectIntoGUI(puremax, 7, 121);
		ItemStack pure = new ItemStack(ItemLoader.itemPure);
		this.itemRender.renderItemAndEffectIntoGUI(pure, 175, 47);
		this.itemRender.renderItemAndEffectIntoGUI(pure, 7, 133);
		ItemStack far = new ItemStack(ItemLoader.itemFar);
		this.itemRender.renderItemAndEffectIntoGUI(far, 175, 59);
		this.itemRender.renderItemAndEffectIntoGUI(far, 7, 145);
		ItemStack lost = new ItemStack(ItemLoader.itemLost);
		this.itemRender.renderItemAndEffectIntoGUI(lost, 175, 71);
		this.itemRender.renderItemAndEffectIntoGUI(lost, 7, 157);
		ItemStack[] allmusic = new ItemStack[5];

		allmusic[0] = pure;
		allmusic[1] = far;
		allmusic[2] = lost;
		allmusic[3] = puremax;
		allmusic[4] = music;

		this.itemRender.renderItemAndEffectIntoGUI(allmusic[musicbutton], 80, 125);

		String Musicoretime = I18n.format("=" + note4);
		this.fontRenderer.drawString(Musicoretime, 24, 110, 0x404040);
		String PureMaxtime = I18n.format("=" + notes[3]);
		this.fontRenderer.drawString(PureMaxtime, 24, 124, 0x404040);
		String Puretime = I18n.format("=" + notes[0]);
		this.fontRenderer.drawString(Puretime, 24, 136, 0x404040);
		String Fartime = I18n.format("=" + notes[1]);
		this.fontRenderer.drawString(Fartime, 24, 148, 0x404040);
		String Losttime = I18n.format("=" + notes[2]);
		this.fontRenderer.drawString(Losttime, 24, 160, 0x404040);
		
		String Musicoretime1 = I18n.format("    /-30s");
		this.fontRenderer.drawString(Musicoretime1, 27, 110, 0x404040);
		String PureMaxtime1 = I18n.format("    /+150s");
		this.fontRenderer.drawString(PureMaxtime1, 27, 124, 0x404040);
		String Puretime1 = I18n.format("    /+60s");
		this.fontRenderer.drawString(Puretime1, 27, 136, 0x404040);
		String Fartime1 = I18n.format("    /+30s");
		this.fontRenderer.drawString(Fartime1, 27, 148, 0x404040);
		String Losttime1 = I18n.format("    /-30s");
		this.fontRenderer.drawString(Losttime1, 27, 160, 0x404040);
		
		String things = I18n.format("musicinventory");
		this.fontRenderer.drawString(things, 180, 10, 0x404040);
	}

	public void GuiTextField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width,
			int par6Height) {
		
		this.fontRenderer = fontrendererObj;
		
		this.width = par5Width;
		this.height = par6Height;
	}

	@Override
	public void initGui() {
		super.initGui();
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.nameField = new GuiTextField(0, this.fontRenderer, i + 109, j + 129, 30, 12);
		this.nameField.setTextColor(-1);
		this.nameField.setDisabledTextColour(0x404040);
		this.nameField.setEnableBackgroundDrawing(false);
		this.nameField.setMaxStringLength(3);

		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.buttonList.add(new GuiButton(PST, offsetX + 140, offsetY + 20, 20, 14, "PST"));
		this.buttonList.add(new GuiButton(PRS, offsetX + 100, offsetY + 20, 20, 14, "PRS"));
		this.buttonList.add(new GuiButton(FTR, offsetX + 60, offsetY + 20, 20, 14, "FTR"));
		this.buttonList.add(new GuiButton(Make, offsetX + 90, offsetY + 151, 35, 15, I18n.format("Make")));
		this.buttonList.add(new GuiButton(Change, offsetX + 135, offsetY + 127, 30, 11, "Change")

		);
	}
		
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (this.nameField.textboxKeyTyped(typedChar, keyCode)) {
			this.putnotenumber();
		} else {
			super.keyTyped(typedChar, keyCode);
		}
	}

	private void putnotenumber() {
		s = this.nameField.getText();

		// if (!s.isEmpty()) {
		try {
			if (isNumeric(s)) {

				int allnotes = Integer.parseInt(s);
				if (lock == 0) {
					if (musicbutton == 0) {
						notes[0] = allnotes;
						if (s.isEmpty()) {
							notes[0] = 0;
						}
						if (notes[0] > note - (notes[1] + notes[2] + notes[3])) {
							notes[0] = note - (notes[1] + notes[2] + notes[3]);
						}
						if (notes[0] < 0) {
							notes[0] = 0;
						}

					}
					if (musicbutton == 1) {
						notes[1] = allnotes;
						if (s.isEmpty()) {
							notes[1] = 0;
						}
						if (notes[1] > note - (notes[0] + notes[2] + notes[3])) {
							notes[1] = note - (notes[0] + notes[2] + notes[3]);
						}
						if (notes[1] < 0) {
							notes[1] = 0;
						}

					}
					if (musicbutton == 2) {
						notes[2] = allnotes;
						if (notes[2] > note - (notes[1] + notes[0] + notes[3])) {
							notes[2] = note - (notes[1] + notes[0] + notes[3]);
						}
						if (notes[2] < 0) {
							notes[2] = 0;
						}
						if (s.isEmpty()) {
							notes[2] = 0;
						}
					}
					if (musicbutton == 3) {
						notes[3] = allnotes;
						if (notes[3] > note - (notes[1] + notes[2] + notes[0])) {
							notes[3] = note - (notes[1] + notes[2] + notes[0]);
						}
						if (notes[3] < 0) {
							notes[3] = 0;
						}
						if (s.isEmpty()) {
							notes[3] = 0;
						}
					}
					if (musicbutton == 4) {
						note4 = allnotes;
						int yinkuang = (notes[0] * 2) + (notes[1] * 1) - (notes[2] * 1) + (notes[3] * 5) + note;
						if (note4 > yinkuang) {
							note4 = yinkuang;
						}
					}

				}
				/*
				 * for (int i = 0; i < note; i++) { if (farnote != i) { farnote = i; } if
				 * (farnote == i) { farnote += i; } } } /*if (!isNumeric(s)) { farnote =
				 * Integer.parseInt(s); for (int i = 0; i < note; i++) { if (farnote != i) {
				 * farnote = 0; } if (farnote == i) { farnote += i; } }
				 */
			}
		} catch (Exception e) {
	
		}

		// }

		this.mc.player.connection.sendPacket(
				new CPacketCustomPayload("MC|ItemName", (new PacketBuffer(Unpooled.buffer())).writeString(s)));
	}

	public static boolean isNumeric(String s1) {
		for (int i = s1.length(); --i >= 0;) {
			int chr = s1.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;

	}

	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.nameField.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public int getmaketimesec() {
		return makesec;
	}

	public int gettimeswitch() {
		return timeswitch;
	}

	public void settimeswitch(int newValue) {
		this.timeswitch = newValue;
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		this.nameField.drawTextBox();
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		World d = tileEntity1.getWorld();
		int dime = d.provider.getDimension();
		BlockPos block = tileEntity1.getPos();
		Difficulty difficulty = Difficulty.FTR;
		switch (button.id) {
		case FTR:
			lvss = 0;
			note4 = 0;
			difficulty = Difficulty.FTR;
			NetworkManagers.wrapper.sendToServer(new MessageSetSongsLv(block, dime, lvss, difficulty));
			break;
		case PRS:
			lvss = 1;
			note4 = 0;
			difficulty = Difficulty.PRS;
			NetworkManagers.wrapper.sendToServer(new MessageSetSongsLv(block, dime, lvss, difficulty));
			break;
		case PST:
			lvss = 2;
			note4 = 0;
			difficulty = Difficulty.PST;
			NetworkManagers.wrapper.sendToServer(new MessageSetSongsLv(block, dime, lvss, difficulty));
			break;
		case Change:

			try {

				if (s.isEmpty()) {
					if (lock == 0) {
						if (musicbutton != 4) {
							notes[musicbutton] = 0;
						}
						if (musicbutton == 4) {
							note4 = 0;
						}
					}
				}
				if (musicbutton < 5) {
					musicbutton = musicbutton + 1;
				}
				if (musicbutton > 4) {
					musicbutton = 0;
				}

			} catch (Exception e) {
				if (musicbutton < 5) {
					musicbutton = musicbutton + 1;
				}
				if (musicbutton > 4) {
					musicbutton = 0;
				}
			}

			break;
		case Make:
			if (notes[0] + notes[1] + notes[2] + notes[3] == note) {
				if (willtime == makesec) {
					if (timeswitch == 0) {
						timeswitch = 1;
						double lvssss = lv;
						NetworkManagers.wrapper.sendToServer(new MessageFinishSongs(block, dime, timeswitch, makesec,
								makemark, marktype, ids, lvssss, notes[0], notes[1], notes[2], notes[3], note4, difficulty));
					}
					if (timeswitch == 1) {
						return;
					}

				}

			}
			// WorldServer world =
			// FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(a);
			break;
		default:
			super.actionPerformed(button);
			return;
		}

	}

}
