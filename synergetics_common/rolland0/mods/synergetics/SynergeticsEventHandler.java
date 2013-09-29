package rolland0.mods.synergetics;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class SynergeticsEventHandler {
	
//	@ForgeSubscribe
//	public void onWorldSave(Save event) {
//		if(event.world.isRemote) {
//			String[] usernames = MinecraftServer.getServer().getAllUsernames();
//			for (String username : usernames) {
//				EntityPlayer player = event.world.getPlayerEntityByName(username);
//				SynergeticsExtendedProperties.saveProxyData(player);
//			}
//		}
//	}
	
	@ForgeSubscribe
	public void onPlayerJoin(EntityJoinWorldEvent event) {
		if(event.entity instanceof EntityPlayerMP) {
			EntityPlayer player = (EntityPlayer) event.entity;
			SynergeticsExtendedProperties.loadProxyData(player);
			SynergeticsExtendedProperties props = SynergeticsExtendedProperties.get(player);
			if(props != null) {
				props.persistExp();
			}
			else
				System.out.println("No props");
				
		}
		else if(event.entity instanceof EntityPlayer) {

			//player.addExperience(CommonProxy.getEntityData(CommonProxy.getSaveKey(player)));
		}
		
	}
	
	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && SynergeticsExtendedProperties.get((EntityPlayer)event.entity) == null) {
			System.out.println("Registering props");
			SynergeticsExtendedProperties.register((EntityPlayer)event.entity);
		}
	}
	
	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event) {
		//if player killed entity, spawn a heart
		if(event.source.getEntity() instanceof EntityPlayer) {
			event.entity.worldObj.spawnEntityInWorld(new EntityHeart(event.entity.worldObj, event.entity));
		}
		
		//if the dead entity is a player, persist its extended properties
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			
//			NBTTagCompound playerData = new NBTTagCompound();
//			((SynergeticsExtendedProperties) (event.entity.getExtendedProperties(SynergeticsExtendedProperties.identifier))).saveNBTData(playerData);
//			CommonProxy.storeEntityData(player.username, playerData);
//			SynergeticsExtendedProperties.saveProxyData(player);
//			Synergetics.proxy.putExp(player.username, player.experienceTotal);
			SynergeticsExtendedProperties props = SynergeticsExtendedProperties.get(player);
			if(props != null) {
				System.out.println("Death - updating exp");
				props.updateExp();
			}
			else
				System.out.println("Death - No props found");
			SynergeticsExtendedProperties.saveProxyData(player);
			player.experience = 0;
			player.experienceLevel = 0;
			player.experienceTotal = 0;
		}
	}
	
//	@ForgeSubscribe
//	public void onWorldLoad(Load event) {
//		event.world.getGameRules().setOrCreateGameRule("keepInventory", "true");
//	}

}
