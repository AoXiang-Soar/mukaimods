package mukaimods.entity;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import java.lang.reflect.Field;
import java.util.Random;

@SuppressWarnings("deprecation")
public class EntityAutoFire extends EntityArrow {
	public static Field inGroundField;
	public static Field ticksInGroundField;
	static {
		try {
			inGroundField = ReflectionHelper.findField(EntityArrow.class, "inGround", "field_70254_i");
			ticksInGroundField = ReflectionHelper.findField(EntityArrow.class, "ticksInGround", "field_70252_j");
		} catch (Exception e) {

		}
	}

	public boolean impacted = false;
	public Random randy = new Random();

	public EntityAutoFire(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityAutoFire(World world, EntityLivingBase entity, EntityLivingBase entity2, float something,
			float otherthing) {
		super(world);
	}

	public EntityAutoFire(World world, EntityLivingBase entity, float something) {
		super(world, entity);

	}

	public EntityAutoFire(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		this.rotationPitch = 0;
		this.rotationYaw = 0;
		super.onUpdate();
		if (!this.impacted) {
			try {
				if (inGroundField.getBoolean(this)) {
					this.impacted = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (this.impacted) {
				if (!this.world.isRemote) {
					this.barrage();
				}
			}
		}

		if (getInGround(this) && getTicksInGround(this) >= 100) {
			this.setDead();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setBoolean("impacted", this.impacted);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		this.impacted = tag.getBoolean("impacted");
	}

	public static boolean getInGround(EntityArrow arrow) {
		try {
			return inGroundField.getBoolean(arrow);
		} catch (Exception e) {
			return false;
		}
	}

	public static int getTicksInGround(EntityArrow arrow) {
		try {
			return ticksInGroundField.getInt(arrow);
		} catch (Exception e) {
			return 0;
		}
	}

	public void barrage() {

	}

	@Override
	protected ItemStack getArrowStack() {
		// TODO Auto-generated method stub
		return null;
	}

}
