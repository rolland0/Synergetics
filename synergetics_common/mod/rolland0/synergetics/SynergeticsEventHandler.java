package mod.rolland0.synergetics;

import java.util.ArrayList;

import mod.rolland0.synergetics.items.EntityHeart;
import mod.rolland0.synergetics.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.oredict.OreDictionary;

public class SynergeticsEventHandler {
	public static EntityPlayer player = null;
	public static final int MOUSE_LEFT_CLICK = 0;
	public static final int MOUSE_RIGHT_CLICK = 1;
	public static final int MOUSE_MIDDLE_CLICK = 2;

//	@ForgeSubscribe
//	public void onPlayerInteractEvent(PlayerInteractEvent event) {
//		if (!event.entityPlayer.worldObj.isRemote) {
//			if (event.action == Action.LEFT_CLICK_BLOCK
//					&& event.entityPlayer.swingProgressInt == -1) {
//				System.out.println("Click");
//			} else {
//				System.out.println("Bad swing");
//				event.entityPlayer.isSwingInProgress = false;
//			}
//
//		}
//	}
//	
//	@ForgeSubscribe
//	public void onEntitySpawn(EntityConstructing event) {
//		if(event.entity instanceof EntityMob) {
//			EntityMob mob = (EntityMob)event.entity;
//		}
//	}
//	
	
	@ForgeSubscribe
	public void onUpdate(LivingUpdateEvent event) {
		if(event.entity instanceof EntityMob
				&& event.entity.ticksExisted > Reference.MAX_MOB_LIFE) {
			//System.out.println(event.entity.ticksExisted);
			event.entity.setDead();
			//System.out.println("Dead");
		}
	}
	
	@ForgeSubscribe
	public void onSpawnCheck(CheckSpawn event) {
		if (event.entity instanceof EntityMob) {
			EntityMob mob = (EntityMob) event.entity;
			if (mob.worldObj.getClosestPlayerToEntity(mob, Reference.MOB_SPAWN_DISTANCE) == null) {
				event.setResult(Result.DENY);
			} else if (!mob.worldObj.canBlockSeeTheSky((int) event.x, (int) event.y, (int) event.z)) {
				event.setResult(Result.ALLOW);
			}
		}
		// System.out.println(event.entity.worldObj.getBlockId((int)event.x,
		// (int)event.y, (int)event.z));
		// System.out.println(event.entity.getEntityName() + ": " + event.x +
		// "," + event.y + "," + event.z);
	}

	@ForgeSubscribe
	public void onMouseClick(MouseEvent event) {
//		logMousePress(event);
		if(player == null) {
			player = Minecraft.getMinecraft().thePlayer;
		}
		if(event.button == MOUSE_LEFT_CLICK && event.buttonstate) {
			if(player.swingProgressInt != 0) {
				event.setCanceled(true);
			}
		}
	}

private void logMousePress(MouseEvent event) {
	if(event.button != -1) {
		StringBuilder builder = new StringBuilder();
		builder.append("Button: ");
		builder.append(event.button);
		builder.append(" ButtonState: ");
		builder.append(event.buttonstate);
		builder.append(" Nanoseconds: ");
		builder.append(event.nanoseconds);
	    builder.append(" X: ");
	    builder.append(event.x);
	    builder.append(" Y: ");
	    builder.append(event.y);
	    builder.append(" Dx: ");
	    builder.append(event.dx);
	    builder.append(" Dy: ");
	    builder.append(event.dy);
	    builder.append(" Dwheel: ");
	    builder.append(event.dwheel);

		System.out.println(builder);
	}
}

	@ForgeSubscribe
	public void onEntityAttack(LivingAttackEvent event) {
		if (event.entityLiving.getEntityName().equals("Zombie")
				|| event.entityLiving.getEntityName().equals("rolland0")) {
			if (event.source != null && event.source.getEntity() instanceof EntityLiving) {
//				((EntityLiving) event.source.getEntity()).addPotionEffect(new PotionEffect(
//						Potion.digSlowdown.id, 20, 0, false));
				if (event.source.getEntity() instanceof EntityMob) {
					EntityMob mob = (EntityMob) event.source.getEntity();
					mob.attackTime = 100;
				}
			}
			// // player is attacked
			// if (event.entity instanceof EntityPlayer
			// && event.source.getEntity() instanceof EntityMob) {
			// EntityMob mob = (EntityMob) event.source.getEntity();
			// mob.attackTime = Reference.ZombieAttackDelay;
			// }

			// player attacks something
			else if (event.source.getEntity() instanceof EntityPlayer
					&& !event.source.getEntity().worldObj.isRemote) {
				EntityPlayer player = (EntityPlayer) event.source.getEntity();
				if (player.swingProgressInt != -1) {
					event.setCanceled(true);
					player.isSwingInProgress = false;
					return;
				}
			}
		}
	}

	@ForgeSubscribe
	public void onBlockHarvest(HarvestDropsEvent event) {
		// general calls
		if (event.block.blockID == Block.gravel.blockID && !event.isSilkTouching) {
			dropFlint(event);
		}
		
		persistWood(event);

		// requires player
		if (event.harvester instanceof EntityPlayer) {
			
			dropSticks(event);
		}

		String eventLog = "Block: "+ event.block.blockID + " " + event.block.getLocalizedName() + " Drops: ";
		for (ItemStack stack : event.drops) {
			eventLog += "(" + stack.getDisplayName() + "x" + stack.stackSize + ") ";
		}
		if(event.harvester instanceof EntityPlayer)
			System.out.println(eventLog);

	}
	
	private void dropSticks(HarvestDropsEvent event) {
		if (event.world.isRemote) 
			return;
		for (int i = 0; i < Reference.StickDropBlocks.length; i++) {
			if (event.block.blockID == Reference.StickDropBlocks[i]
					&& Reference.rollDie(Reference.StickDropChance)) {
				event.drops.add(new ItemStack(Item.stick));
				break;
			}
		}
	}

	private void dropFlint(HarvestDropsEvent event) {
		if (event.world.isRemote)
			return;
		event.drops.clear();
		event.drops.add(new ItemStack(Item.flint));
		if (Reference.rollDie(Reference.FlintDropChance))
			event.drops.add(new ItemStack(Item.flint));
	}
	
	private boolean isWood(Block block, int blockMetadata) {
		ArrayList<ItemStack> logs = OreDictionary.getOres("logWood");
		for (ItemStack itemStack : logs) {
			if (block.blockID == itemStack.getItem().itemID
					&& blockMetadata == itemStack.getItem().getMetadata(blockMetadata)) {
				return true;
			}
		}
		return false;
	}

	private void persistWood(HarvestDropsEvent event) {
		if (isWood(event.block, event.blockMetadata)) {
			event.drops.clear();
			if (event.harvester != null) {
				ItemStack item = event.harvester.getCurrentEquippedItem();
				if (item != null
						&& ForgeHooks.isToolEffective(item, event.block, event.blockMetadata)) {
					event.drops.add(new ItemStack(Synergetics.itemPlank));
					event.drops.add(new ItemStack(Synergetics.itemPlank));
					// event.world.setBlock(event.x, event.y, event.z,
					// event.block.blockID,
					// event.blockMetadata, 2);
				}
			}

				
		}
	}

	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event) {
		// if player killed entity, spawn a heart
		if (event.source.getEntity() instanceof EntityPlayer
				&& shouldSpawnHeart((EntityPlayer) event.source.getEntity())) {
			event.entity.worldObj.spawnEntityInWorld(new EntityHeart(event.entity.worldObj,
					event.entity));
		}
	}

	private boolean shouldSpawnHeart(EntityPlayer player) {
		return player.getHealth() < player.getMaxHealth()
				&& Reference.rollDie(Reference.HeartDropChance);
	}

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		event.world.getGameRules().setOrCreateGameRule("keepInventory", "true");
	}
}
