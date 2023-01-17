package mukaimods.item;

import java.util.List;

import mukaimods.player.CapabilityLoader;
import mukaimods.player.EventLoader;
import mukaimods.player.IPTTCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemPTT extends Item {

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("PTTShow"));
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		IPTTCapability ptts = playerIn.getCapability(CapabilityLoader.PTT, null);
		if (!worldIn.isRemote) {
			playerIn.sendMessage(new TextComponentTranslation("ptt:" + ptts.getptt()));
			EventLoader.pttHelper(playerIn);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}