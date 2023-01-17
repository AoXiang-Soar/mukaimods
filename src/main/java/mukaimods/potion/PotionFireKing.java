package mukaimods.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionFireKing extends Potion
{
    private static final ResourceLocation res = new ResourceLocation(mukaimods.Mukaimods.MODID + ":" + "textures/potion/fireking.png");

    public PotionFireKing()
    {
        super(true, 0x7F0000);
        this.setPotionName("potion.Fireking");	
    }
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        mc.getTextureManager().bindTexture(PotionFireKing.res);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 0, 0, 18, 18);
    }
}