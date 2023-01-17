package mukaimods.player;

import java.util.Arrays;
import java.util.Random;

import mukaimods.Mukaimods;
import mukaimods.enchantment.EnchantmentLoader;
import mukaimods.item.ItemLoader;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventLoader {
	public EventLoader() {
		MinecraftForge.EVENT_BUS.register(this);
		// MinecraftForge.EVENT_BUS.register(new EventFireKingSword());
	}

	int Killnumber = 0;
	public static final EventBus EVENT_BUS = new EventBus();

	@SubscribeEvent
	public void hello(PlayerEvent.PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		String s1 = "\n";

		if (player.getName().equals("zer0M1nd_HbCZM")) {
			player.sendMessage(new TextComponentTranslation("omg.czm", s1));
		}

	}

	@SubscribeEvent
	public static void reSyncDataOnPlayerDeath(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
		Capability<IPTTCapability> capability = CapabilityLoader.PTT;
		IStorage<IPTTCapability> storage = capability.getStorage();
		if (event.getOriginal().hasCapability(capability, null)
				&& event.getEntityPlayer().hasCapability(capability, null)) {
			NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
			storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
		}
	}

	// 更换ptt方框
	@SubscribeEvent
	public void PickUpPtt(PlayerEvent event) {
//		if (!(event.getEntityLiving() instanceof EntityPlayer))
//			return;

		EntityPlayer player = event.player;
		if (player != null) {
			pttHelper(player);
		}
	}

	@SubscribeEvent
	public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			ICapabilitySerializable<NBTTagCompound> provider = new PTTCapabilityList.ProviderPlayer();
			event.addCapability(new ResourceLocation(Mukaimods.MODID + ":" + "player_ptt"), provider);
		}
	}

	// 意义不明的事件，看不懂这逻辑
	/*
	 * @SubscribeEvent public void tqlsdlwsl(LivingDeathEvent event) { if
	 * (event.getSource().getDamageType().equals(new DamageSource("tqlsdlwsl"))) {
	 * if (event.getEntityLiving() != null) { if (event.getEntityLiving() instanceof
	 * EntityPlayer) { EntityPlayer player = (EntityPlayer) event.getEntityLiving();
	 * player.attackEntityFrom((new DamageSource("tqlsdlwsl")), player.getHealth());
	 * } } } }
	 */

	@SubscribeEvent
	public void hitDropMusic(LivingHurtEvent event) {
		Entity hit = event.getEntity();
		if (hit instanceof EntityCreature) {
			if (event.getSource().getTrueSource() instanceof EntityPlayer) {

				EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
				ItemStack item = player.getHeldItemMainhand();

				if (player.getHeldItemMainhand().isItemEnchanted()) {
					int lv = EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.musichit, item);
					Random random = new Random();
					int x = random.nextInt(4);
					int y = random.nextInt(10);
					ItemStack[] musics = new ItemStack[4];
					musics[0] = new ItemStack(ItemLoader.itemPureMax);
					musics[1] = new ItemStack(ItemLoader.itemPure);
					musics[2] = new ItemStack(ItemLoader.itemFar);
					musics[3] = new ItemStack(ItemLoader.itemLost);
					if (lv == 1) {
						if (y == 1) {
							hit.entityDropItem(musics[x], 1);
						}
					}
					if (lv == 2) {
						if (y <= 2) {
							hit.entityDropItem(musics[x], 1);
						}
					}
					if (lv == 4) {
						if (y <= 3) {
							hit.entityDropItem(musics[x], 1);
						}
					}
					if (lv == 4) {
						if (y <= 4) {
							hit.entityDropItem(musics[x], 1);
						}
					}
					if (lv == 5) {
						if (y <= 5) {
							hit.entityDropItem(musics[x], 1);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void killDropMusic(LivingExperienceDropEvent event) {
		EntityPlayer player = event.getAttackingPlayer();
		Entity die = event.getEntity();
		if (player != null) {
			if (die instanceof EntityCreature) {

				ItemStack item = player.getHeldItemMainhand();

				if (player.getHeldItemMainhand().isItemEnchanted()) {
					int lv = EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.musickill, item);
					if (lv > 0) {
						Random random = new Random();
						int x = random.nextInt(4);
						int y = random.nextInt(4);

						ItemStack[] musics = new ItemStack[4];
						musics[0] = new ItemStack(ItemLoader.itemPureMax);
						musics[1] = new ItemStack(ItemLoader.itemPure);
						musics[2] = new ItemStack(ItemLoader.itemFar);
						musics[3] = new ItemStack(ItemLoader.itemLost);
						ItemStack[] musics2 = new ItemStack[4];
						musics2[0] = new ItemStack(ItemLoader.itemPureMax, 2);
						musics2[1] = new ItemStack(ItemLoader.itemPure, 2);
						musics2[2] = new ItemStack(ItemLoader.itemFar, 2);
						musics2[3] = new ItemStack(ItemLoader.itemLost, 2);
						if (lv == 1) {

							die.entityDropItem(musics[x], 1);
						}
						if (lv == 2) {

							if (y == 1) {
								die.entityDropItem(musics2[x], 1);
							} else {
								die.entityDropItem(musics[y], 1);
							}
						}
						if (lv == 3) {
							if (y != 1) {
								die.entityDropItem(musics2[y], 1);
							} else {
								die.entityDropItem(musics[x], 1);
							}
						}
					}
				}
			}
		}
	}
	
	public static void pttHelper(EntityPlayer player) {
		Capability<IPTTCapability> capability = CapabilityLoader.PTT;
		InventoryPlayer playeritems = player.inventory;
		if (player.isServerWorld()) {
			if (player.hasCapability(capability, null)) {
				IPTTCapability ptts = player.getCapability(CapabilityLoader.PTT, null);
				double nowptt = ptts.getptt();
				// System.out.println(nowptt);
				if (0 <= nowptt && nowptt < 3.5) {
					for (int i = 0; i < 36; i++) {
						if (Arrays
								.asList(ItemLoader.itemPTTGreen, ItemLoader.itemPTTGay,
										ItemLoader.itemPTTFlowerlyGay, ItemLoader.itemPTTRed,
										ItemLoader.itemPTTStar, ItemLoader.itemPTTStar2, ItemLoader.itemPTTStar3)
								.contains(playeritems.getStackInSlot(i).getItem())) {
							playeritems.setInventorySlotContents(i, new ItemStack(ItemLoader.itemPTTBlue, 1));
						}
					}
				} else if (3.5 <= nowptt && nowptt < 7) {
					for (int i = 0; i < 36; i++) {
						if (Arrays
								.asList(ItemLoader.itemPTTBlue, ItemLoader.itemPTTGay,
										ItemLoader.itemPTTFlowerlyGay, ItemLoader.itemPTTRed,
										ItemLoader.itemPTTStar, ItemLoader.itemPTTStar2, ItemLoader.itemPTTStar3)
								.contains(playeritems.getStackInSlot(i).getItem())) {
							playeritems.setInventorySlotContents(i, new ItemStack(ItemLoader.itemPTTGreen, 1));
						}
					}
				} else if (7 <= nowptt && nowptt < 10) {
					for (int i = 0; i < 36; i++) {
						if (Arrays
								.asList(ItemLoader.itemPTTBlue, ItemLoader.itemPTTGreen,
										ItemLoader.itemPTTFlowerlyGay, ItemLoader.itemPTTRed,
										ItemLoader.itemPTTStar, ItemLoader.itemPTTStar2, ItemLoader.itemPTTStar3)
								.contains(playeritems.getStackInSlot(i).getItem())) {
							playeritems.setInventorySlotContents(i, new ItemStack(ItemLoader.itemPTTGay, 1));
						}
					}
				} else if (10 <= nowptt && nowptt < 11) {
					for (int i = 0; i < 36; i++) {
						if (Arrays
								.asList(ItemLoader.itemPTTBlue, ItemLoader.itemPTTGreen, ItemLoader.itemPTTGay,
										ItemLoader.itemPTTRed, ItemLoader.itemPTTStar, ItemLoader.itemPTTStar2,
										ItemLoader.itemPTTStar3)
								.contains(playeritems.getStackInSlot(i).getItem())) {
							playeritems.setInventorySlotContents(i, new ItemStack(ItemLoader.itemPTTFlowerlyGay, 1));
						}
					}
				} else if (11 <= nowptt && nowptt < 12) {
					for (int i = 0; i < 36; i++) {
						if (Arrays
								.asList(ItemLoader.itemPTTBlue, ItemLoader.itemPTTGreen, ItemLoader.itemPTTGay,
										ItemLoader.itemPTTFlowerlyGay, ItemLoader.itemPTTStar,
										ItemLoader.itemPTTStar2, ItemLoader.itemPTTStar3)
								.contains(playeritems.getStackInSlot(i).getItem())) {
							playeritems.setInventorySlotContents(i, new ItemStack(ItemLoader.itemPTTRed, 1));
						}
					}
				} else if (12 <= nowptt && nowptt < 12.5) {
					for (int i = 0; i < 36; i++) {
						if (Arrays
								.asList(ItemLoader.itemPTTBlue, ItemLoader.itemPTTGreen, ItemLoader.itemPTTGay,
										ItemLoader.itemPTTFlowerlyGay, ItemLoader.itemPTTRed,
										ItemLoader.itemPTTStar2, ItemLoader.itemPTTStar3)
								.contains(playeritems.getStackInSlot(i).getItem())) {
							playeritems.setInventorySlotContents(i, new ItemStack(ItemLoader.itemPTTStar, 1));
						}
					}
				} else if (12.5 <= nowptt && nowptt < 13) {
					for (int i = 0; i < 36; i++) {
						if (Arrays
								.asList(ItemLoader.itemPTTBlue, ItemLoader.itemPTTGreen, ItemLoader.itemPTTGay,
										ItemLoader.itemPTTFlowerlyGay, ItemLoader.itemPTTRed,
										ItemLoader.itemPTTStar, ItemLoader.itemPTTStar3)
								.contains(playeritems.getStackInSlot(i).getItem())) {
							playeritems.setInventorySlotContents(i, new ItemStack(ItemLoader.itemPTTStar2, 1));
						}
					}
				} else if (13 <= nowptt) {
					for (int i = 0; i < 36; i++) {
						if (Arrays
								.asList(ItemLoader.itemPTTBlue, ItemLoader.itemPTTGreen, ItemLoader.itemPTTGay,
										ItemLoader.itemPTTFlowerlyGay, ItemLoader.itemPTTRed,
										ItemLoader.itemPTTStar, ItemLoader.itemPTTStar2)
								.contains(playeritems.getStackInSlot(i).getItem())) {
							playeritems.setInventorySlotContents(i, new ItemStack(ItemLoader.itemPTTStar3, 1));
						}
					}
				}
			}
		}
	}

}
