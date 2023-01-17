package mukaimods.player;
/*
package mk.Player;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventFireKingSword {
    protected Random rand;
	@SubscribeEvent
	public void FireKing(PlayerInteractEvent event) {
		EntityPlayer player = event.getEntityPlayer();	
		World world=player.world;
		//EntityLargeFireball entitylargefireball= new EntityLargeFireball(world,player,  player.rotationPitch, player.rotationYaw, 0.0F);
		EntityLargeFireball entitylargefireball= new EntityLargeFireball(world, 0, 0, 0, 0, 0, 0);
		entitylargefireball.setNoGravity(true);
		shoot(entitylargefireball, player, player.rotationPitch,  player.rotationYaw, 1);
          world.spawnEntity(entitylargefireball);

	}
	
    public static void shoot(EntityLargeFireball entitfireball,Entity shooter, float pitch, float yaw, float velocity)
    {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float f1 = -MathHelper.sin(pitch * 0.017453292F);
        float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        shoot(entitfireball,(double)f, (double)f1, (double)f2, velocity);
        entitfireball.motionX += shooter.motionX;
        entitfireball.motionZ += shooter.motionZ;

        if (!shooter.onGround)
        {
        	entitfireball.motionY += shooter.motionY;
        }
    }
    public static void shoot(EntityLargeFireball entitfireball,double x, double y, double z, float velocity)
    {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        entitfireball.motionX = x;
        entitfireball.motionY = y;
        entitfireball.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        entitfireball.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        entitfireball.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
        entitfireball.prevRotationYaw = entitfireball.rotationYaw;
        entitfireball.prevRotationPitch = entitfireball.rotationPitch;
      
    }
    
    
}
*/
