package rolland0.mods.synergetics;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import rolland0.mods.synergetics.lib.SynRef;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = SynRef.MOD_ID, name = SynRef.MOD_NAME, version = SynRef.VERSION)
//@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Synergetics {
	
	public static Item bucketWoodEmpty;
	public static Item bucketWoodWater;
	public static Item itemMortor;
	public static Item itemFriedEgg;
	public static Item itemHeart;
	
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
		SynRef.ItemID_Heart = config.getItem(SynRef.ItemName_Heart, SynRef.ItemID_Heart).getInt();
		config.save();
		
		//create items
		bucketWoodEmpty = new ItemBucketWood(SynRef.ItemID_BucketWoodEmpty, 0, SynRef.ItemName_BucketWoodEmpty);
		bucketWoodWater = new ItemBucketWood(SynRef.ItemID_BucketWoodWater, Block.waterMoving.blockID, SynRef.ItemName_BucketWoodWater);
		itemMortor = new SynMortor();
		itemFriedEgg = new ItemFriedEgg();
		itemHeart = new ItemHeart();
		
		//register items
		GameRegistry.registerItem(bucketWoodEmpty, SynRef.ItemName_BucketWoodEmpty);
		GameRegistry.registerItem(bucketWoodWater, SynRef.ItemName_BucketWoodWater);
		GameRegistry.registerItem(itemMortor, SynRef.ItemName_Mortor);
		GameRegistry.registerItem(itemFriedEgg, SynRef.ItemName_FriedEgg);
		GameRegistry.registerItem(itemHeart, SynRef.ItemName_Heart);
		
		
		//register item names
		LanguageRegistry.addName(bucketWoodEmpty, "Wooden Bucket");
		LanguageRegistry.addName(bucketWoodWater, "Wooden Bucket of Water");
		LanguageRegistry.addName(itemMortor, "Mortor");
		LanguageRegistry.addName(itemFriedEgg, "Fried Egg");
		LanguageRegistry.addName(itemHeart, "Heart");
		
		proxy.registerRenderers();
		proxy.registerSounds();
		
		MinecraftForge.EVENT_BUS.register(new HeartDropHandler());
		MinecraftForge.EVENT_BUS.register(new OnWorldLoad());
		MinecraftForge.EVENT_BUS.register(new OnPlayerDeath());
		MinecraftForge.EVENT_BUS.register(new Echo());
		addCrafting();
		//MinecraftForge.EVENT_BUS.register(new DebugSound());
		
		//MinecraftForge.EVENT_BUS.register(new LavaInWoodBucketBlocker());
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
		ItemStack mortorStack = new ItemStack(itemMortor, 4);
		
		//register recipes
		GameRegistry.registerCraftingHandler(new SynCraftingHandler());
		GameRegistry.addRecipe(new ShapedOreRecipe(bucketWoodEmptyStack, 
				"x x", "yxy",
				'x', "plankWood", 'y', "stickWood"));
		GameRegistry.addShapelessRecipe(mortorStack, clayStack, clayStack, clayStack, clayStack,
										dirtStack, dirtStack, bucketWoodWaterStack);
		GameRegistry.addShapelessRecipe(mortorStack, clayBlockStack, dirtStack, dirtStack, bucketWoodWaterStack);
		GameRegistry.addShapelessRecipe(new ItemStack(itemMortor, 8), 
				clayBlockStack, clayBlockStack, bucketWoodWaterStack, bucketWoodWaterStack,
				dirtStack, dirtStack,  dirtStack, dirtStack);
		
		GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(itemFriedEgg), 0.1f);
		//proxy.registerSounds();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//proxy.registerSounds();
	}
	
	public void initConfig() {
		
	}
	
	public void addCrafting() {
		GameRegistry.addRecipe(new ItemStack(Block.torchWood), new Object[] {"X", "#", 'X', Item.glowstone, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(Item.glowstone, 4), new Object[] {"X", 'X', Block.glowStone});

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
