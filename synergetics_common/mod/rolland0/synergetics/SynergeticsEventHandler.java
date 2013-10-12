package mod.rolland0.synergetics;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import mod.rolland0.synergetics.lib.Reference;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.oredict.OreDictionary;

public class SynergeticsEventHandler {

	// @ForgeSubscribe
	// public void onWorldSave(Save event) {
	// if(event.world.isRemote) {
	// String[] usernames = MinecraftServer.getServer().getAllUsernames();
	// for (String username : usernames) {
	// EntityPlayer player = event.world.getPlayerEntityByName(username);
	// SynergeticsExtendedProperties.saveProxyData(player);
	// }
	// }
	// }

	// @ForgeSubscribe
	// public void onPlayerJoin(EntityJoinWorldEvent event) {
	// if(event.entity instanceof EntityPlayerMP) {
	// EntityPlayer player = (EntityPlayer) event.entity;
	// SynergeticsExtendedProperties.loadProxyData(player);
	// SynergeticsExtendedProperties props =
	// SynergeticsExtendedProperties.get(player);
	// if(props != null) {
	// props.persistExp();
	// }
	// else
	// System.out.println("No props");
	//
	// }
	// else if(event.entity instanceof EntityPlayer) {
	//
	// //player.addExperience(CommonProxy.getEntityData(CommonProxy.getSaveKey(player)));
	// }
	//
	// }

	// @ForgeSubscribe
	// public void onEntityConstructing(EntityConstructing event) {
	// if (event.entity instanceof EntityPlayer &&
	// SynergeticsExtendedProperties.get((EntityPlayer)event.entity) == null) {
	// System.out.println("Registering props");
	// SynergeticsExtendedProperties.register((EntityPlayer)event.entity);
	// }
	// }

	@ForgeSubscribe
	public void onEntityAttack(LivingAttackEvent event) {
		// player is attacked
		if (event.entity instanceof EntityPlayer
				&& event.source.getEntity() instanceof EntityMob) {
			EntityMob mob = (EntityMob) event.source.getEntity();
			mob.attackTime = Reference.ZombieAttackDelay;
		}

		// player attacks something
		else if (event.source.getEntity() instanceof EntityPlayer) {
			// System.out.println("Entity: " + event.entity.getEntityName() +
			// " Source: " + event.source.getDamageType() + " Ammount: " +
			// event.ammount);
		}
		// if(event.entity instanceof EntityMob) {
		// System.out.println(event.entity.);
		// EntityMob mob = (EntityMob)event.entity;
		// mob.
		// }
	}

	//
	// @ForgeSubscribe
	// public void onEntitySpawn(EntityJoinWorldEvent event) {
	// if(event.entity instanceof EntityLivingBase) {
	// EntityLivingBase entity = (EntityLivingBase)event.entity;
	// entity.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(SharedMonsterAttributes.knockbackResistance.getDefaultValue());
	// }
	// // if(event.entity instanceof EntityArrow) {
	// // EntityArrow arrow = (EntityArrow)event.entity;
	// // arrow.knockbackStrength = 0;
	// // }
	// }

	@ForgeSubscribe
	public void onBlockHarvest(HarvestDropsEvent event) {
		boolean isWood = false;
		ArrayList<ItemStack> logs = OreDictionary.getOres("logWood");
		for (ItemStack itemStack : logs) {
			if (event.block.blockID == itemStack.getItem().itemID
					&& event.blockMetadata == itemStack.getItem().getMetadata(
							event.blockMetadata)) {
				isWood = true;
			}
		}

		if (event.harvester instanceof EntityPlayer) {
			ItemStack item = event.harvester.getCurrentEquippedItem();
			{
				if(item != null && item.itemID == Item.pickaxeGold.itemID)
					System.out.println(item.getItemDamage());
				if (isWood) {
					boolean shouldDrop = false;

					if (item != null
							&& ForgeHooks.isToolEffective(item, event.block,
									event.blockMetadata))
						shouldDrop = true;

					if (!shouldDrop) {
						event.drops.clear();
						event.world.setBlock(event.x, event.y, event.z,
								event.block.blockID, event.blockMetadata, 2);
					}
				}
			}

		}

		String eventLog = "Block: " + event.block.getLocalizedName()
				+ " Drops: ";
		for (ItemStack stack : event.drops) {
			eventLog += "(" + stack.getDisplayName() + "x" + stack.stackSize
					+ ") ";
		}
		System.out.println(eventLog);

	}

	// @ForgeSubscribe
	// public void onEntityHurt(LivingHurtEvent event) {
	// double multiplier = 0.5;
	// if((event.source.getSourceOfDamage() instanceof EntityArrow)) {
	// multiplier = 0.2;
	// }
	// event.entity.motionX *= multiplier;
	// event.entity.motionY *= multiplier;
	// event.entity.motionZ *= multiplier;
	// //event.entity.addVelocity(0, 0, 0);
	//
	// // System.out.println(event.entity.getEntityName());
	// // System.out.println("        entity:       " +
	// event.source.getEntity());
	// // System.out.println("        damage type:  " +
	// event.source.getDamageType());
	// // System.out.println("        damage source: " +
	// event.source.getSourceOfDamage());
	// //
	// }

	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event) {
		// if player killed entity, spawn a heart
		if (event.source.getEntity() instanceof EntityPlayer
				&& shouldSpawnHeart((EntityPlayer) event.source.getEntity())) {
			event.entity.worldObj.spawnEntityInWorld(new EntityHeart(
					event.entity.worldObj, event.entity));
		}

		// //if the dead entity is a player, persist its extended properties
		// if(event.entity instanceof EntityPlayer) {
		// EntityPlayer player = (EntityPlayer) event.entity;
		//
		// // NBTTagCompound playerData = new NBTTagCompound();
		// // ((SynergeticsExtendedProperties)
		// (event.entity.getExtendedProperties(SynergeticsExtendedProperties.identifier))).saveNBTData(playerData);
		// // CommonProxy.storeEntityData(player.username, playerData);
		// // SynergeticsExtendedProperties.saveProxyData(player);
		// // Synergetics.proxy.putExp(player.username, player.experienceTotal);
		// SynergeticsExtendedProperties props =
		// SynergeticsExtendedProperties.get(player);
		// if(props != null) {
		// System.out.println("Death - updating exp");
		// props.updateExp();
		// }
		// else
		// System.out.println("Death - No props found");
		// SynergeticsExtendedProperties.saveProxyData(player);
		// player.experience = 0;
		// player.experienceLevel = 0;
		// player.experienceTotal = 0;
		// }
	}

	private boolean shouldSpawnHeart(EntityPlayer player) {
		return player.getHealth() < player.getMaxHealth()
				&& Reference.rand.nextInt(100) < Reference.HeartDropChance;
	}

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		event.world.getGameRules().setOrCreateGameRule("keepInventory", "true");
	}

}
