package mod.rolland0.synergetics.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;


public class CommonProxy {
	private static final Map<String, NBTTagCompound> propMap = new HashMap<String, NBTTagCompound>();
	public void registerRenderers() {
		
	}
	
	public void registerCraftingHandlers() {
		
	}
	
	public void registerSounds() {
		
	}
	
	public static void storeEntityData(String name, NBTTagCompound compound) {
		propMap.put(name, compound);
	}
	
	public static NBTTagCompound getEntityData(String name) {
		return propMap.remove(name);
	}
	
//	public int getExp(String username) {
//		int exp = 0;
//		if(expMap.containsKey(username)) {
//			exp = expMap.get(username);
//		}
//		else {
//			expMap.put(username, 0);
//		}
//		return exp;
//	}
//	
//	public void putExp(String username, int totalExp) {
//		expMap.put(username, totalExp);
//	}

}
