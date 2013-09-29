package rolland0.mods.synergetics;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import rolland0.mods.synergetics.lib.Reference;
import rolland0.mods.synergetics.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Synergetics {

	public static Item bucketWoodEmpty;
	public static Item bucketWoodWater;
	public static Item itemMortor;
	public static Item itemFriedEgg;
	public static Item itemHeart;

	@Instance(Reference.MOD_ID)
	public static Synergetics instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	//Event Handler for the mod
	private SynergeticsEventHandler eventHandler = new SynergeticsEventHandler();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// init config
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		Reference.ItemID_BucketWoodEmpty = config.getItem(
				Reference.ItemName_BucketWoodEmpty,
				Reference.ItemID_BucketWoodEmpty).getInt();
		Reference.ItemID_BucketWoodWater = config.getItem(
				Reference.ItemName_BucketWoodWater,
				Reference.ItemID_BucketWoodWater).getInt();
		Reference.ItemID_Mortor = config.getItem(Reference.ItemName_Mortor,
				Reference.ItemID_Mortor).getInt();
		Reference.ItemID_FriedEgg = config.getItem(Reference.ItemName_FriedEgg,
				Reference.ItemID_FriedEgg).getInt();
		Reference.ItemID_Heart = config.getItem(Reference.ItemName_Heart,
				Reference.ItemID_Heart).getInt();
		config.save();

		// create items
		bucketWoodEmpty = new ItemBucketWood(Reference.ItemID_BucketWoodEmpty,
				0, Reference.ItemName_BucketWoodEmpty);
		bucketWoodWater = new ItemBucketWood(Reference.ItemID_BucketWoodWater,
				Block.waterMoving.blockID, Reference.ItemName_BucketWoodWater);
		itemMortor = new SynMortor();
		itemFriedEgg = new ItemFriedEgg();
		itemHeart = new ItemHeart();

		// register items
		GameRegistry.registerItem(bucketWoodEmpty,
				Reference.ItemName_BucketWoodEmpty);
		GameRegistry.registerItem(bucketWoodWater,
				Reference.ItemName_BucketWoodWater);
		GameRegistry.registerItem(itemMortor, Reference.ItemName_Mortor);
		GameRegistry.registerItem(itemFriedEgg, Reference.ItemName_FriedEgg);
		GameRegistry.registerItem(itemHeart, Reference.ItemName_Heart);

		// register item names
		LanguageRegistry.addName(bucketWoodEmpty, "Wooden Bucket");
		LanguageRegistry.addName(bucketWoodWater, "Wooden Bucket of Water");
		LanguageRegistry.addName(itemMortor, "Mortor");
		LanguageRegistry.addName(itemFriedEgg, "Fried Egg");
		LanguageRegistry.addName(itemHeart, "Heart");

		proxy.registerRenderers();
		proxy.registerSounds();
		
		MinecraftForge.EVENT_BUS.register(eventHandler);

		// MinecraftForge.EVENT_BUS.register(new DebugSound());
		// MinecraftForge.EVENT_BUS.register(new LavaInWoodBucketBlocker());
		// MinecraftForge.ORE_GEN_BUS.register(new SynOreReplacer());
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		addCrafting();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	public void initConfig() {

	}

	public void addCrafting() {
		// create common itemstacks
		ItemStack clayStack = new ItemStack(Item.clay);
		ItemStack clayBlockStack = new ItemStack(Block.blockClay);
		ItemStack dirtStack = new ItemStack(Block.dirt);
		ItemStack bucketWoodWaterStack = new ItemStack(bucketWoodWater);
		ItemStack bucketWoodEmptyStack = new ItemStack(bucketWoodEmpty);
		ItemStack mortorStack = new ItemStack(itemMortor, 4);

		// register recipes
		GameRegistry.registerCraftingHandler(new SynCraftingHandler());

		// bucket
		GameRegistry.addRecipe(new ShapedOreRecipe(bucketWoodEmptyStack, "x x",
				"yxy", 'x', "plankWood", 'y', "stickWood"));
		
		// mortor
		GameRegistry.addShapelessRecipe(mortorStack, clayStack, clayStack,
				clayStack, clayStack, dirtStack, dirtStack,
				bucketWoodWaterStack);
		GameRegistry.addShapelessRecipe(mortorStack, clayBlockStack, dirtStack,
				dirtStack, bucketWoodWaterStack);
		GameRegistry.addShapelessRecipe(new ItemStack(itemMortor, 8),
				clayBlockStack, clayBlockStack, bucketWoodWaterStack,
				bucketWoodWaterStack, dirtStack, dirtStack, dirtStack,
				dirtStack);

		// fried egg
		GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(itemFriedEgg),
				0.1f);

		// glowstone torch recipes
		GameRegistry.addRecipe(new ItemStack(Block.torchWood), new Object[] {
				"X", "#", 'X', Item.glowstone, '#', Item.stick });
		GameRegistry.addRecipe(new ItemStack(Item.glowstone, 4), new Object[] {
				"X", 'X', Block.glowStone });

	}

	// private void registerBlock(Block oreBlock) {
	// if(oreBlock instanceof SynOreBlock) {
	// SynOreBlock ore = (SynOreBlock)oreBlock;
	// LanguageRegistry.addName(ore, ore.getDisplayName());
	// MinecraftForge.setBlockHarvestLevel(ore, "pickaxe",
	// ore.getHarvestLevel());
	// GameRegistry.registerBlock(ore, ore.getName());
	// }
	// }
}
