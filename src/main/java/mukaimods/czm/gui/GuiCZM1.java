package mukaimods.czm.gui;

import java.util.Random;

import mukaimods.Mukaimods;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiCZM1 extends GuiContainer {
	Random random = new Random();
	int x = random.nextInt(37);

	private final String czm1 = Mukaimods.MODID + ":" + "textures/gui/container/czm" + x + ".png";

	private final ResourceLocation TEXTURE = new ResourceLocation(czm1);

	public GuiCZM1(ContainerCZM1 inventorySlotsIn) {
		super(inventorySlotsIn);
		this.xSize = 256;
		this.ySize = 100;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
	}
}