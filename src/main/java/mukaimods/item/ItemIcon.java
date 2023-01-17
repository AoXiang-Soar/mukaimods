package mukaimods.item;

import mukaimods.Mukaimods;
import mukaimods.createtab.CreativeTabsLoader;
import mukaimods.gui.container.GuiElementLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemIcon extends Item {
	 public ItemIcon()
	    {
	        super();
	        this.setUnlocalizedName("icon");
	        this.setCreativeTab(CreativeTabsLoader.tabItems);
	    }
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		 ItemStack itemstack = playerIn.getHeldItem(handIn);
         BlockPos pos = playerIn.getPosition();
         int id = GuiElementLoader.GUI_DEMO;
         playerIn.openGui(Mukaimods.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
     
     
 
         return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
 
    }
}

