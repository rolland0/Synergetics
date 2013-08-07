package rolland0.mods.synergetics.lib;

import java.util.Random;

public class SynRef {
	
	public static Random rand = new Random(System.currentTimeMillis());
	
	public static final String MOD_ID = "Synergetics";
	public static final String MOD_NAME = "Synergetics";
	public static final String VERSION = "0.0.1";
	
	public static final String Mod = "synergetics:";
	
	public static final int COAL_ID = 3600;
	public static final int COAL_HARVEST_LEVEL = 0;
	public static final int COAL_MAX_HITS = 9;
	public static final int COAL_MIN_CHUNKS_PER_MINE = 0;
	public static final int COAL_MAX_CHUNKS_PER_MINE = 2;
	
	public static final int DIAMOND_ID = COAL_ID + 1;
	public static final int DIAMOND_HARVEST_LEVEL = 2;
	public static final int DIAMOND_MAX_HITS = 2;
	
	public static final int REDSTONE_ID = DIAMOND_ID + 1;
	public static final int REDSTONE_HARVEST_LEVEL = 2;
	public static final int REDSTONE_MAX_HITS = 4;
	
	public static final int LAPIS_ID = REDSTONE_ID + 1;
	public static final int LAPIS_HARVEST_LEVEL = 1;
	public static final int LAPIS_MAX_HITS = 6;
	
	public static final int IRON_ID = LAPIS_ID + 1;
	public static final int IRON_HARVEST_LEVEL = 1;
	public static final int IRON_MAX_HITS = 4;
	
	public static final int GOLD_ID = IRON_ID + 1;
	public static final int GOLD_HARVEST_LEVEL = 2;
	public static final int GOLD_MAX_HITS = 3;
	
	public static final float ORE_HARDNESS = 3.0F;

	
	
	//Item IDs
	public static int ItemID_BucketWoodEmpty = 1000;
	public static int ItemID_BucketWoodWater = ItemID_BucketWoodEmpty + 1;
	public static int ItemID_Mortor = ItemID_BucketWoodWater + 1;
	public static int ItemID_FriedEgg = ItemID_Mortor + 1;
	
	//Item Names
	public static final String ItemName_BucketWoodEmpty = "bucketWoodEmpty";
	public static final String ItemName_BucketWoodWater = "bucketWoodWater";
	public static final String ItemName_Mortor = "mortor";
	public static final String ItemName_FriedEgg = "friedEgg";

	//Item Texture Locations
	public static final String TextureLocation_BucketWoodEmpty = Mod + ItemName_BucketWoodEmpty;
	public static final String TextureLocation_BucketWoodWater = Mod + ItemName_BucketWoodWater;
	public static final String TextureLocation_Mortor = Mod + ItemName_Mortor;
	public static final String TextureLocation_FriedEgg = Mod + ItemName_FriedEgg;

	
	
}
