package mukaimods.gui;

import mukaimods.Mukaimods;
import mukaimods.gui.container.ContainerNumberGame;
import mukaimods.gui.container.ContainerPlayMusic;
import mukaimods.sounds.ArcSongsTime;
import mukaimods.tileentity.TileEntityNumberGame;
import mukaimods.tileentity.TileEntityPlaysongs;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiNumberGame extends GuiContainer {
	protected ContainerNumberGame inventory;
	public TileEntityNumberGame tileEntity1;
	private static final String TEXTURE_PATH = Mukaimods.MODID + ":" + "textures/gui/container/number.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
	public GuiNumberGame(ContainerNumberGame inventorySlotsIn, TileEntity tileEntity) {
		super(inventorySlotsIn);
		this.xSize = 240;
		this.ySize = 256;
		this.inventory = inventorySlotsIn;	
		this.tileEntity1 = (TileEntityNumberGame) tileEntity;
		
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
		
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String Songname = I18n.format("PlayerA:");
		this.fontRenderer.drawString(Songname, 80, 37, 0x404040);
		String Songnames = I18n.format("PlayerB:");
		this.fontRenderer.drawString(Songnames, 80, 47, 0x404040);
		String Songtime = I18n.format("NumberA:");
		this.fontRenderer.drawString(Songtime, 80, 57, 0x404040);
		String Songtime1 = I18n.format("          ");
		this.fontRenderer.drawString(Songtime1, 80, 57, 0x404040);
		String Songlv = I18n.format("NumberB:");
		this.fontRenderer.drawString(Songlv, 80, 67, 0x404040);
		String Songlv1 = I18n.format("          ");
		this.fontRenderer.drawString(Songlv1, 80, 67, 0x404040);
	}
	
}
