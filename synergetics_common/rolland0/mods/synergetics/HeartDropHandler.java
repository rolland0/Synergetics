package rolland0.mods.synergetics;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class HeartDropHandler {
	@ForgeSubscribe
	public void onMobDeath(LivingDeathEvent event) {
		if(event.source.getEntity() instanceof EntityPlayer) {
			System.out.println("Killed by player");
			if(event.entity instanceof EntityLiving)
				System.out.println("Killed thing was animal/mob");
			System.out.println("Damage source: " + event.source.getSourceOfDamage());
			System.out.println("Entity name: " + event.entity.getEntityName());
			event.entity.worldObj.spawnEntityInWorld(new EntityHeart(event.entity.worldObj, event.entity));
			System.out.println("=========End Report");
		}
		
	}
}
