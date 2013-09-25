package rolland0.mods.synergetics;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class OnPlayerDeath {
	@ForgeSubscribe
	public void onPlayerDeath(LivingDeathEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			for (int i = 0; i < player.inventory.getHotbarSize(); i++) {
				if (player.inventory.mainInventory[i] != null) {
					player.dropPlayerItemWithRandomChoice(
							player.inventory.mainInventory[i], true);
					player.inventory.mainInventory[i] = null;
				}
			}

			for (int i = 0; i < player.inventory.armorInventory.length; ++i) {
				if (player.inventory.armorInventory[i] != null) {
					player.dropPlayerItemWithRandomChoice(
							player.inventory.armorInventory[i], true);
					player.inventory.armorInventory[i] = null;
				}
			}

			if (player.experienceLevel > 0) {
				player.experienceLevel--;
			} else {
				player.experience = player.experienceTotal = 0;
			}
		}
	}

	// @ForgeSubscribe
	// public void onPlayerDeath(LivingDeathEvent event) {
	// if(event.entityLiving instanceof EntityPlayer) {
	// EntityPlayer player = (EntityPlayer)event.entityLiving;
	// System.out.println("exp: " + player.experience);
	// System.out.println("expTotal: " + player.experienceTotal);
	// System.out.println("expLevel: " + player.experienceLevel);
	// ExpProp prop = (ExpProp)player.getExtendedProperties(ExpProp.identifier);
	//
	// player.experience = 0.0f;
	// player.experienceLevel = 0;
	// player.experienceTotal = 0;
	// }
	// }

}
