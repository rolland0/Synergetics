package mod.rolland0.synergetics.lib;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;

public class Reference {
	
	private static Map<String, ItemRefInfo> Data = new HashMap<String, ItemRefInfo>();
	
	//Item Names
	public static final String ItemName_BucketWoodEmpty = "bucketWoodEmpty";
	public static final String ItemName_BucketWoodWater = "bucketWoodWater";
	public static final String ItemName_Mortor = "mortor";
	public static final String ItemName_FriedEgg = "friedEgg";
	public static final String ItemName_Heart = "heart";
	public static final String ItemName_Hatchet = "hatchet";
	public static final String ItemName_Knife = "knife";
	public static final String ItemName_Plank = "plank";
	
	static {
		addItem(ItemName_BucketWoodEmpty, "Empty Wooden Bucket");
		addItem(ItemName_BucketWoodWater, "Wooden Water Bucket");
		addItem(ItemName_Mortor, "Mortor");
		addItem(ItemName_FriedEgg, "Fried Egg");
		addItem(ItemName_Heart, "Heart");
		addItem(ItemName_Hatchet, "Hatchet");
		addItem(ItemName_Knife, "Knife");
		addItem(ItemName_Plank, "Plank");
	}
	
	public static Random rand = new Random(System.currentTimeMillis());
	public static boolean rollDie(int chance) {
		return rand.nextInt(100) < chance;
	}
	
	private static int currentItemID = 15000;
	private static int currentBlockID = 1337;
	
	public static int nextItemID () {
		return currentItemID++;
	}
	
	public static int nextBlockID() {
		return currentBlockID++;
	}
	
	public static void initConfig(File file) {
		Configuration config = new Configuration(file);
		config.load();
		for (String name : Data.keySet()) {
			ItemRefInfo data = Data.get(name);
			data.setItemID(config.getItem(name, data.getItemID()).getInt());		
		}
		config.save();
	}
	
	public static void addItem(String name, String displayName) {
		ItemRefInfo data = new ItemRefInfo(nextItemID(), Mod_Path + name, displayName);
		Data.put(name, data);
	}
	
	public static ItemRefInfo getData(String name) {
		if(Data.containsKey(name)) {
			return Data.get(name);
		}
		else
			return null;
	}
	
	public static final String MOD_ID = "synergetics";
	public static final String MOD_NAME = "Synergetics";
	public static final String VERSION = "0.1.0";
	public static final String Mod_Path = "synergetics:";	
	
	//Block Metadata
	public static final int META_VALUE_TIN = 0;
	public static final int META_VALUE_COPPER = 1;
	
	public static int[] StickDropBlocks = {Block.tallGrass.blockID, Block.deadBush.blockID};
	
	//Block IDs
	public static int BlockID_Ore = nextBlockID();
	
	//Misc values
	public static int HeartDropChance = 10;
	public static int HeartHealAmount = 4;
	public static int StickDropChance = 15;
	public static int FlintDropChance = 50;
	public static int ZombieAttackDelay = 250;
	
	//Block Names
	public static final String BlockName_Ore = "synOre";
	
	//Localized Block Names
	public static final String[] LocalizedBlockNames = {"Tin Ore", "Copper Ore"};
	
	//Block Texture Locations
	public static final String[] BlockTextureLocation_ore = {Mod_Path + "oreTin", Mod_Path + "oreCopper"};
	
	//Sound Effect Locations
	public static final String SoundHeal = Mod_Path + "heal";
	public static final String SoundHealFile = SoundHeal + ".ogg";

	//Proxy Names
	public static final String CLIENT_PROXY_CLASS = "mod.rolland0.synergetics.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "mod.rolland0.synergetics.proxy.CommonProxy";

	public static final int RenderType = 100;

	public static final int MAX_MOB_LIFE = 20;

	public static double MOB_SPAWN_DISTANCE = 80.0;
	
	public static OreSpawnInfo TinSpawnInfo;
	public static OreSpawnInfo GravelSpawnInfo;
	
	public static void initSpawnInfo() {
		TinSpawnInfo = new OreSpawnInfo(BlockID_Ore, META_VALUE_TIN, 50, 70, 3, 2, 5, 
				new int[]{Block.stone.blockID, Block.grass.blockID, Block.dirt.blockID});
		GravelSpawnInfo = new OreSpawnInfo(Block.gravel.blockID, 0, 45, 80, 3, 2, 5,
				new int[]{Block.stone.blockID, Block.grass.blockID, Block.dirt.blockID});
	}
}
