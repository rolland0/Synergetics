package rolland0.mods.synergetics;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;
import rolland0.mods.synergetics.lib.SynRef;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = SynRef.MOD_ID, name = SynRef.MOD_NAME, version = SynRef.VERSION)
//@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Synergetics {
	
//	public final static Block SynIronOre = new SynOreBlock(SynRef.COAL_ID, SynRef.COAL_MAX_HITS, "Coal Ore",SynRef.COAL_HARVEST_LEVEL, "synOreCoal");
//	public final static Block SynDiamondOre = new SynOreBlock(SynRef.DIAMOND_ID, SynRef.DIAMOND_MAX_HITS, "Diamond Ore", SynRef.DIAMOND_HARVEST_LEVEL, "synOreDiamond"); 
//	public final static Block SynRedstoneOre = new SynOreBlock(SynRef.REDSTONE_ID, SynRef.REDSTONE_MAX_HITS, "Redstone Ore", SynRef.REDSTONE_HARVEST_LEVEL, "synOreRedstone");
//	public final static Block SynLapisOre = new SynOreBlock(SynRef.LAPIS_ID, SynRef.LAPIS_MAX_HITS, "Lapis Ore", SynRef.LAPIS_HARVEST_LEVEL, "synOreLapis");
//	public final static Block SynCoalOre = new SynOreBlock(SynRef.IRON_ID, SynRef.IRON_MAX_HITS, "Iron Ore", SynRef.IRON_HARVEST_LEVEL, "synOreIron");
//	public final static Block SynGoldOre = new SynOreBlock(SynRef.GOLD_ID,SynRef.GOLD_MAX_HITS, "Gold Ore",  SynRef.GOLD_HARVEST_LEVEL, "synOreGold");
	
	public static Item bucketWoodEmpty;
	public static Item bucketWoodWater;
	public static Item synMortor;
	public static Item itemFriedEgg;
	
	@Instance(SynRef.MOD_ID)
	public static Synergetics instance;
	
	@SidedProxy(clientSide="rolland0.mods.synergetics.ClientProxy", serverSide="rolland0.mods.synergetics.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//init config
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		SynRef.ItemID_BucketWoodEmpty = config.getItem(SynRef.ItemName_BucketWoodEmpty, SynRef.ItemID_BucketWoodEmpty).getInt();
		SynRef.ItemID_BucketWoodWater = config.getItem(SynRef.ItemName_BucketWoodWater, SynRef.ItemID_BucketWoodWater).getInt();
		SynRef.ItemID_Mortor = config.getItem(SynRef.ItemName_Mortor, SynRef.ItemID_Mortor).getInt();
		SynRef.ItemID_FriedEgg = config.getItem(SynRef.ItemName_FriedEgg, SynRef.ItemID_FriedEgg).getInt();
		
		config.save();
		
		//create items
		bucketWoodEmpty = new ItemBucketWood(SynRef.ItemID_BucketWoodEmpty, 0, SynRef.ItemName_BucketWoodEmpty);
		bucketWoodWater = new ItemBucketWood(SynRef.ItemID_BucketWoodWater, Block.waterMoving.blockID, SynRef.ItemName_BucketWoodWater);
		synMortor = new SynMortor();
		itemFriedEgg = new ItemFriedEgg();
		
		
		//register items
		GameRegistry.registerItem(bucketWoodEmpty, SynRef.ItemName_BucketWoodEmpty);
		GameRegistry.registerItem(bucketWoodWater, SynRef.ItemName_BucketWoodWater);
		GameRegistry.registerItem(synMortor, SynRef.ItemName_Mortor);
		GameRegistry.registerItem(itemFriedEgg, SynRef.ItemName_FriedEgg);
		
		//register item names
		LanguageRegistry.addName(bucketWoodEmpty, "Wooden Bucket");
		LanguageRegistry.addName(bucketWoodWater, "Wooden Bucket of Water");
		LanguageRegistry.addName(synMortor, "Mortor");
		LanguageRegistry.addName(itemFriedEgg, "Fried Egg");
		
		proxy.registerRenderers();
		
		//MinecraftForge.EVENT_BUS.register(new LavaInWoodBucketBlocker());
		//MinecraftForge.EVENT_BUS.register(new NoBowls());
		//MinecraftForge.ORE_GEN_BUS.register(new SynOreReplacer());
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		//create common itemstacks
		ItemStack clayStack = new ItemStack(Item.clay);
		ItemStack clayBlockStack = new ItemStack(Block.blockClay);
		ItemStack dirtStack = new ItemStack(Block.dirt);
		ItemStack bucketWoodWaterStack = new ItemStack(bucketWoodWater);
		ItemStack bucketWoodEmptyStack = new ItemStack(bucketWoodEmpty);
		ItemStack mortorStack = new ItemStack(synMortor, 4);
		
		//register recipes
		GameRegistry.registerCraftingHandler(new SynCraftingHandler());
		GameRegistry.addRecipe(new ShapedOreRecipe(bucketWoodEmptyStack, 
				"x x", "yxy",
				'x', "plankWood", 'y', "stickWood"));
		GameRegistry.addShapelessRecipe(mortorStack, clayStack, clayStack, clayStack, clayStack,
										dirtStack, dirtStack, bucketWoodWaterStack);
		GameRegistry.addShapelessRecipe(mortorStack, clayBlockStack, dirtStack, dirtStack, bucketWoodWaterStack);
		GameRegistry.addShapelessRecipe(new ItemStack(synMortor, 8), 
				clayBlockStack, clayBlockStack, bucketWoodWaterStack, bucketWoodWaterStack,
				dirtStack, dirtStack,  dirtStack, dirtStack);
		
		GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(itemFriedEgg), 0.1f);
		
		
		
//		registerBlock(SynIronOre);
//		registerBlock(SynGoldOre);
//		registerBlock(SynDiamondOre);
//		registerBlock(SynCoalOre);
//		registerBlock(SynRedstoneOre);
//		registerBlock(SynLapisOre);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
	
	public void initConfig() {
		
	}
	
//	private void registerBlock(Block oreBlock) {
//		if(oreBlock instanceof SynOreBlock) {
//			SynOreBlock ore = (SynOreBlock)oreBlock;
//			LanguageRegistry.addName(ore, ore.getDisplayName());
//			MinecraftForge.setBlockHarvestLevel(ore, "pickaxe", ore.getHarvestLevel());
//			GameRegistry.registerBlock(ore, ore.getName());
//		}
//	}
}
