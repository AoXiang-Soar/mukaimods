package mukaimods.gui;

import mukaimods.Mukaimods;
import mukaimods.gui.container.ContainerDemo;
import mukaimods.item.ItemLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiContainerDemo extends GuiContainer {
	private static final String TEXTURE_PATH = Mukaimods.MODID + ":" + "textures/gui/container/getmusic.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

	private static final int BUTTON_UP = 0;
	private static final int BUTTON_DOWN = 1;

	public GuiContainerDemo(ContainerDemo inventorySlotsIn) {
		super(inventorySlotsIn);
		this.xSize = 500;
		this.ySize = 500;
	}

	public void MYdrawTexturedModalRect(int xCoord, int yCoord, TextureAtlasSprite textureSprite, int widthIn,
			int heightIn) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos((double) (xCoord + 0), (double) (yCoord + heightIn), (double) this.zLevel)
				.tex((double) textureSprite.getMinU(), (double) textureSprite.getMaxV()).endVertex();
		bufferbuilder.pos((double) (xCoord + widthIn), (double) (yCoord + heightIn), (double) this.zLevel)
				.tex((double) textureSprite.getMaxU(), (double) textureSprite.getMaxV()).endVertex();
		bufferbuilder.pos((double) (xCoord + widthIn), (double) (yCoord + 0), (double) this.zLevel)
				.tex((double) textureSprite.getMaxU(), (double) textureSprite.getMinV()).endVertex();
		bufferbuilder.pos((double) (xCoord + 0), (double) (yCoord + 0), (double) this.zLevel)
				.tex((double) textureSprite.getMinU(), (double) textureSprite.getMinV()).endVertex();
		tessellator.draw();
	}

	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

		this.MusicdrawTexturedModalRect(offsetX, offsetY, 2, 2, this.xSize, this.ySize);
	}

	public void MusicdrawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos((double) (x + 0), (double) (y + height), (double) this.zLevel)
				.tex((double) ((float) (textureX + 0) * 0.002F), (double) ((float) (textureY + height) * 0.002F))
				.endVertex();
		bufferbuilder.pos((double) (x + width), (double) (y + height), (double) this.zLevel)
				.tex((double) ((float) (textureX + width) * 0.002F), (double) ((float) (textureY + height) * 0.002F))
				.endVertex();
		bufferbuilder.pos((double) (x + width), (double) (y + 0), (double) this.zLevel)
				.tex((double) ((float) (textureX + width) * 0.002F), (double) ((float) (textureY + 0) * 0.002F))
				.endVertex();
		bufferbuilder.pos((double) (x + 0), (double) (y + 0), (double) this.zLevel)
				.tex((double) ((float) (textureX + 0) * 0.002F), (double) ((float) (textureY + 0) * 0.002F))
				.endVertex();
		tessellator.draw();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.drawVerticalLine(30, 19, 36, 0xFF000000);
		this.drawHorizontalLine(8, 167, 43, 0xFF000000);

		String title = I18n.format("container.mukaimods.demo");
		this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);

		ItemStack item = new ItemStack(ItemLoader.itemIcon);
		this.itemRender.renderItemAndEffectIntoGUI(item, 120, 20);
	}

	@Override
	public void initGui() {
		super.initGui();
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.buttonList.add(new GuiButton(BUTTON_UP, offsetX + 105, offsetY + 17, 15, 10, "") {
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
		this.buttonList.add(new GuiButton(BUTTON_DOWN, offsetX + 105, offsetY + 29, 15, 10, "") {
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
	}

}