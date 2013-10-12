package mod.rolland0.synergetics.core;

import net.minecraft.entity.player.EntityPlayer;

public class NewMethods {
	private static int baseReqExp = 200;
	private static int addedExpPerLevel = 10;
	
//	public static Map<Integer, Integer> xpMap = 
//			new HashMap<Integer, Integer>();
//	int requiredExp = 0;
//	if(xpMap.containsKey(player.experienceLevel)) {
//		requiredExp = xpMap.get(key)
//	}
//	
//	xpMap.get(player.experienceLevel);
//	if(requiredExp == 0)
//		
//	return requiredExp;
	
	public static int xpBarCap(EntityPlayer player) {
		return baseReqExp + player.experienceLevel * addedExpPerLevel;
	}
	
	public static void addExperienceLevel(EntityPlayer player, int levels) {
        if(levels > 0) {
			player.experienceLevel += levels;
	        float f = player.experienceLevel > 30 ? 1.0F : (float)player.experienceLevel / 30.0F;
	        player.worldObj.playSoundAtEntity(player, "random.levelup", f * 0.75F, 1.0F);
        }
	}
	
	
}
