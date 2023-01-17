package mukaimods.item;

import java.util.List;

import mukaimods.createtab.CreativeTabsLoader;
import mukaimods.sounds.Archit;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemFragment extends Item {
	public ItemFragment() {
		super();
		this.setUnlocalizedName("suipian");
		this.setCreativeTab(CreativeTabsLoader.tabItems);
	}

	@Override
	public void addInformation(ItemStack stack, World clientWorld, List<String> tooltips, ITooltipFlag flag) {
		tooltips.add(I18n.format("ItemMusicsuipian"));
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.attackEntityFrom(new DamageSource("tqlsdlwsl"), 14);

		worldIn.playSound((EntityPlayer) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, Archit.Archit,
				SoundCategory.PLAYERS, 3.0F, 1.0F);

		itemstack.shrink(1);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

	}
}
