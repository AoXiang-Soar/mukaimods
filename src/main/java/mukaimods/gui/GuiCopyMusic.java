package mukaimods.gui;

import java.io.IOException;

import mukaimods.Mukaimods;
import mukaimods.gui.container.CopyMusic;
import mukaimods.internet.MessagePackage;
import mukaimods.internet.NetworkManagers;
import mukaimods.sounds.ArcMusic;
import mukaimods.sounds.ArcSongsTime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiCopyMusic extends GuiContainer {
	static int allsong = ArcMusic.allsong-1;
	private Slot songs;
	public static int name1 = 1;
	int name2 = 2;
	int name3 = 3;
	int name4 = 4;
	int name5 = 5;
	int name6 = 6;
	int bpm = 7;
	int time = 8;
	int art = 9;
	int lv = 10;
	int arc = 11;
	private static final String TEXTURE_PATH = Mukaimods.MODID + ":" + "textures/gui/container/copymusic.png";

	private static final String[] TEXTURE_song = new String[allsong+10];
	static {

		for (int i = 1; i <= allsong+2; i++) {
			TEXTURE_song[i] = Mukaimods.MODID + ":" + "textures/gui/container/" + i + ".png";
		}
	}
	public final static ResourceLocation[] TEXTUREsongs = new ResourceLocation[allsong+10];

	static {

		for (int i = 1; i <= allsong+2; i++) {
			TEXTUREsongs[i] = new ResourceLocation(TEXTURE_song[i]);
		}
	}

	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int UP10 = 10;
	private static final int DOWN10 = 11;
	private static final int Music = 5;
	private static final int Make = 6;
	BlockPos blockpos;
	

	
	//private void drawTexturedModalRect(float xCoord, float yCoord, float tx, float ty, float tsx, float tsy) {
		// TODO Auto-generated method stub
		
	//}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
		//this.drawScaledTexturedModelRect(offsetX, offsetY, this.xSize, this.ySize, 0, 0, 256, 256);
		this.mc.getTextureManager().bindTexture(TEXTUREsongs[name1]);
		int offsetX11 = 18 + (this.width - this.xSize) / 2, offsetY11 = 30 + (this.height - this.ySize) / 2;

		this.drawScaledTexturedModelRect(offsetX11-2, offsetY11-2, 64, 64, 0, 0,256,256);
		//�޸������СΪ64,64������ͼƬ�ߴ�Ϊ256,256��
	}

	public GuiCopyMusic(CopyMusic inventorySlotsIn, BlockPos blockpos) {
		super(inventorySlotsIn);
		this.xSize = 256;
		this.ySize = 223;
		this.blockpos = blockpos;
		this.songs = inventorySlotsIn.getIronSlot();
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
	//czm�Ĵ���
	public  void drawScaledTexturedModelRect(int x, int y, int sx, int sy, int tx, int ty, int tsx, int tsy) {
		double scaleX = (double) sx / (double) tsx;
		double scaleY = (double) sy / (double) tsy;
		GlStateManager.scale(scaleX, scaleY, 1.0);
		
		this.drawTexturedModalRect((float) (x / scaleX), (float) (y / scaleY),
				(float) tx, (float) ty, (float) tsx, (float) tsy);
		GlStateManager.scale(1 / scaleX, 1 / scaleY, 1.0);
	}
	//czm�Ĵ���
	
	@Override
	public void initGui() {
		super.initGui();
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.buttonList.add(new GuiButton(UP, offsetX + 211, offsetY + 6, 17, 10,  I18n.format("-")) {
			public void drawButton(Minecraft mc, int mouseX, int mouseY) {
				if (this.visible) {
					GlStateManager.color(1.0F, 1.0F, 1.0F);

					mc.getTextureManager().bindTexture(TEXTURE);
					int x = mouseX - this.x, y = mouseY - this.y;

					if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
						this.drawTexturedModalRect(this.x, this.y, 1, 146, this.width, this.height);
					} else {
						this.drawTexturedModalRect(this.x, this.y, 1, 134, this.width, this.height);
					}
				}
			}
		});

		this.buttonList.add(new GuiButton(DOWN, offsetX + 211, offsetY + 207, 17, 10,  I18n.format("+")) {
			public void drawButton(Minecraft mc, int mouseX, int mouseY) {
				if (this.visible) {
					GlStateManager.color(1.0F, 1.0F, 1.0F);

					mc.getTextureManager().bindTexture(TEXTURE);
					int x = mouseX - this.x, y = mouseY - this.y;

					if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
						this.drawTexturedModalRect(this.x, this.y, 20, 146, this.width, this.height);
					} else {
						this.drawTexturedModalRect(this.x, this.y, 20, 134, this.width, this.height);
					}
				}
			}
		});
		this.buttonList.add(new GuiButton(UP10, offsetX + 190, offsetY + 6, 17, 10,  I18n.format("-10")) {
			public void drawButton(Minecraft mc, int mouseX, int mouseY) {
				if (this.visible) {
					GlStateManager.color(1.0F, 1.0F, 1.0F);

					mc.getTextureManager().bindTexture(TEXTURE);
					int x = mouseX - this.x, y = mouseY - this.y;

					if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
						this.drawTexturedModalRect(this.x, this.y, 1, 146, this.width, this.height);
					} else {
						this.drawTexturedModalRect(this.x, this.y, 1, 134, this.width, this.height);
					}
				}
			}
		});
		this.buttonList.add(new GuiButton(DOWN10, offsetX + 190, offsetY + 207, 17, 10,  I18n.format("+10")) {
			public void drawButton(Minecraft mc, int mouseX, int mouseY) {
				if (this.visible) {
					GlStateManager.color(1.0F, 1.0F, 1.0F);

					mc.getTextureManager().bindTexture(TEXTURE);
					int x = mouseX - this.x, y = mouseY - this.y;

					if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
						this.drawTexturedModalRect(this.x, this.y, 20, 146, this.width, this.height);
					} else {
						this.drawTexturedModalRect(this.x, this.y, 20, 134, this.width, this.height);
					}
				}
			}
		});

		this.buttonList.add(new GuiButton(Music, offsetX + 90, offsetY + 115, 42, 15,  I18n.format("Play")) {
			public void drawButton(Minecraft mc, int mouseX, int mouseY) {
				if (this.visible) {
					GlStateManager.color(1.0F, 1.0F, 1.0F);

					mc.getTextureManager().bindTexture(TEXTURE);
					int x = mouseX - this.x, y = mouseY - this.y;

					if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
						this.drawTexturedModalRect(100, 100, 1, 1, this.width, this.height);
					} else {
						this.drawTexturedModalRect(200, 200, 148, 100, 100, 100);
					}
				}
			}
		});
		this.buttonList.add(new GuiButton(Make, offsetX + 140, offsetY + 115, 25, 15,  I18n.format("Makes")) {
			public void drawButton(Minecraft mc, int mouseX, int mouseY) {
				if (this.visible) {
					GlStateManager.color(1.0F, 1.0F, 1.0F);

					mc.getTextureManager().bindTexture(TEXTURE);
					int x = mouseX - this.x, y = mouseY - this.y;

					if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
						this.drawTexturedModalRect(100, 100, 1, 1, this.width, this.height);
					} else {
						this.drawTexturedModalRect(200, 200, 148, 100, 100, 100);
					}
				}
			}
		});

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

		String title = I18n.format("container.mukaimods.copymusic");
		this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);

		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

		String songsname1 = I18n.format("songsname" + name1);
		this.fontRenderer.drawString(songsname1, 190, 25, 0x404040);
		String songsname2 = I18n.format("songsname" + name2);
		this.fontRenderer.drawString(songsname2, 190, 60, 0x404040);

		String songsname3 = I18n.format("songsname" + name3);
		this.fontRenderer.drawString(songsname3, 190, 95, 0x404040);
		String songsname4 = I18n.format("songsname" + name4);
		this.fontRenderer.drawString(songsname4, 190, 131, 0x404040);
		String songsname5 = I18n.format("songsname" + name5);
		this.fontRenderer.drawString(songsname5, 190, 168, 0x404040);
		String songsname6 = I18n.format("songsname" + name1);
		this.fontRenderer.drawString(songsname6, 100, 30, 0x404040);
		String bpm = I18n.format("" + name1);
		this.fontRenderer.drawString(bpm, 125, 45, 0x404040);
		this.fontRenderer.drawString(bpm, 215, 34, 0x404040);
		String id2 = I18n.format("" + name2);
		this.fontRenderer.drawString(id2, 215, 69, 0x404040);
		String id3 = I18n.format("" + name3);
		this.fontRenderer.drawString(id3, 215, 104, 0x404040);
		String id4 = I18n.format("" + name4);
		this.fontRenderer.drawString(id4, 215, 140, 0x404040);
		String id5 = I18n.format("" + name5);
		this.fontRenderer.drawString(id5, 215, 178, 0x404040);
		String time = I18n.format("" + ArcSongsTime.songtime[name1]);
		this.fontRenderer.drawString(time, 120, 60, 0x404040);
		String art = I18n.format("art" + name1);
		this.fontRenderer.drawString(art, 120, 75, 0x404040);
		String lv = I18n.format("Arcaea");
		this.fontRenderer.drawString(lv, 120, 90, 0x404040);
		String bpmname = I18n.format("SongID:");
		this.fontRenderer.drawString(bpmname, 100, 45, 0x404040);
		this.fontRenderer.drawString(bpmname, 190, 35, 0x404040);
		this.fontRenderer.drawString(bpmname, 190, 70, 0x404040);
		this.fontRenderer.drawString(bpmname, 190, 105, 0x404040);
		this.fontRenderer.drawString(bpmname, 190, 141, 0x404040);
		this.fontRenderer.drawString(bpmname, 190, 178, 0x404040);

		String timename = I18n.format("Time:");
		this.fontRenderer.drawString(timename, 100, 60, 0x404040);
		String artname = I18n.format("Art:");
		this.fontRenderer.drawString(artname, 100, 75, 0x404040);
		String lvname = I18n.format("From:");
		this.fontRenderer.drawString(lvname, 100, 90, 0x404040);
		String arc = I18n.format("Arcaea1");
		this.fontRenderer.drawString(arc, 190, 45, 0x404040);
		this.fontRenderer.drawString(arc, 190, 80, 0x404040);
		this.fontRenderer.drawString(arc, 190, 115, 0x404040);
		this.fontRenderer.drawString(arc, 190, 151, 0x404040);
		this.fontRenderer.drawString(arc, 190, 188, 0x404040);

	}

	protected void actionPerformed(GuiButton button) throws IOException {

		World world = Minecraft.getMinecraft().player.world;
		switch (button.id) {
		case DOWN:
			world.playEvent(1010, blockpos, 0);
			world.playRecord(blockpos, (SoundEvent) null);

			name1 = (name1 % allsong) + 1;
			name2 = (name1 % allsong) + 1;

			name3 = (name2 % allsong) + 1;

			name4 = (name3 % allsong) + 1;
			name5 = (name4 % allsong) + 1;
			break;
		case UP:
			world.playEvent(1010, blockpos, 0);
			world.playRecord(blockpos, (SoundEvent) null);
			name1 = name1 - 1;
			if (name1 == 0) {
				name1 = allsong;
			}
			name2 = (name1 % allsong) + 1;
			name3 = (name2 % allsong) + 1;
			name4 = (name3 % allsong) + 1;
			name5 = (name4 % allsong) + 1;

			break;
		case DOWN10:
			world.playEvent(1010, blockpos, 0);
			world.playRecord(blockpos, (SoundEvent) null);
			if (name1 >= allsong-10) {
				name1 = 0;
			}
			name1 = (name1 % allsong) + 10;
			name2 = (name1 % allsong) + 1;

			name3 = (name2 % allsong) + 1;

			name4 = (name3 % allsong) + 1;
			name5 = (name4 % allsong) + 1;
			break;
		case UP10:
			world.playEvent(1010, blockpos, 0);
			world.playRecord(blockpos, (SoundEvent) null);
			if (name1 <= 10) {
				name1 = allsong;
			}
			name1 = name1 - 10;
			name2 = (name1 % allsong) + 1;
			name3 = (name2 % allsong) + 1;
			name4 = (name3 % allsong) + 1;
			name5 = (name4 % allsong) + 1;
			break;
		case Make:
			NetworkManagers.wrapper.sendToServer(
					new MessagePackage(EntityPlayer.getUUID(Minecraft.getMinecraft().player.getGameProfile()), name1));

			break;
		case Music:

			world.playRecord(blockpos, ArcMusic.songsp[(name1)]);

			break;

		default:
			super.actionPerformed(button);
			return;
		}

	}

}
