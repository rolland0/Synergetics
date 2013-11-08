package mod.rolland0.synergetics;

import mod.rolland0.synergetics.blocks.BlockSynOre;
import mod.rolland0.synergetics.items.ItemFriedEgg;
import mod.rolland0.synergetics.items.ItemHatchet;
import mod.rolland0.synergetics.items.ItemHeart;
import mod.rolland0.synergetics.items.ItemKnife;
import mod.rolland0.synergetics.items.ItemMortor;
import mod.rolland0.synergetics.items.ItemPlank;
import mod.rolland0.synergetics.lib.Reference;
import mod.rolland0.synergetics.proxy.CommonProxy;
import mod.rolland0.synergetics.world.SynOreGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Synergetics {
	
	public static EnumToolMaterial makeshiftMaterial;
//	public static Item bucketWoodEmpty;
//	public static Item bucketWoodWater;
	public static Item itemMortor;
	public static Item itemFriedEgg;
	public static Item itemHeart;
	public static Item itemHatchet;
	public static Item itemKnife;
	public static Item itemPlank;
	public static Block oreBlock;

	@Instance(Reference.MOD_ID)
	public static Synergetics instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	// Event Handler for the mod
	private SynergeticsEventHandler eventHandler = new SynergeticsEventHandler();

	@SuppressWarnings("static-access")
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Register Items

		
		//Load Config Files
		Reference.initConfig(event.getSuggestedConfigurationFile());

		//Create Materials
		makeshiftMaterial = new EnumHelper().addToolMaterial("makeshift", 0, 50, 1.5f, 2.0f, 0);

		//Create Items
		createItems();
		
		//Register Ores
		registerOres();

//		// register items
//		GameRegistry.registerItem(bucketWoodEmpty, Reference.ItemName_BucketWoodEmpty);
//		GameRegistry.registerItem(bucketWoodWater, Reference.ItemName_BucketWoodWater);
//		
//		// register item names
//		LanguageRegistry.addName(bucketWoodEmpty, "Wooden Bucket");
//		LanguageRegistry.addName(bucketWoodWater, "Wooden Bucket of Water");

		//Apply misc vanilla tweaks
		empowerTools();

		proxy.registerRenderers();
		proxy.registerSounds();

		MinecraftForge.EVENT_BUS.register(eventHandler);
		
		
	}

	private void registerOres() {
		Reference.initSpawnInfo();
		GameRegistry.registerWorldGenerator(new SynOreGenerator());
	}

	private void createItems() {
//		bucketWoodEmpty = new ItemBucketWood(Reference.ItemID_BucketWoodEmpty, 0, Reference.ItemName_BucketWoodEmpty);
//		bucketWoodWater = new ItemBucketWood(Reference.ItemID_BucketWoodWater, 
//				Block.waterMoving.blockID, Reference.ItemName_BucketWoodWater);
		itemMortor = new ItemMortor(Reference.getData(Reference.ItemName_Mortor));
		itemFriedEgg = new ItemFriedEgg(Reference.getData(Reference.ItemName_FriedEgg));
		itemHeart = new ItemHeart(Reference.getData(Reference.ItemName_Heart));
		itemHatchet = new ItemHatchet(Reference.getData(Reference.ItemName_Hatchet));
		itemKnife =  new ItemKnife(Reference.getData(Reference.ItemName_Knife));
		itemPlank = new ItemPlank(Reference.getData(Reference.ItemName_Plank));
		oreBlock = new BlockSynOre();
	}

	private void empowerTools() {
		MinecraftForge.setToolClass(Item.pickaxeGold, "pickaxe", 2);
		MinecraftForge.setToolClass(Item.axeGold, "axe", 2);

		MinecraftForge.setToolClass(Item.pickaxeWood, "shovel", 0);
		MinecraftForge.setToolClass(Item.pickaxeStone, "shovel", 1);
		MinecraftForge.setToolClass(Item.pickaxeIron, "shovel", 2);
		MinecraftForge.setToolClass(Item.pickaxeGold, "shovel", 2);
		MinecraftForge.setToolClass(Item.pickaxeDiamond, "shovel", 3);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		addCrafting();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	public void addCrafting() {
		// create common itemstacks
//		ItemStack clayStack = new ItemStack(Item.clay);
//		ItemStack clayBlockStack = new ItemStack(Block.blockClay);
//		ItemStack dirtStack = new ItemStack(Block.dirt);
////		ItemStack bucketWoodWaterStack = new ItemStack(bucketWoodWater);
////		ItemStack bucketWoodEmptyStack = new ItemStack(bucketWoodEmpty);
//		ItemStack mortorStack = new ItemStack(itemMortor, 4);

		// register recipes
		//GameRegistry.registerCraftingHandler(new SynCraftingHandler());

//		// bucket
//		GameRegistry.addRecipe(new ShapedOreRecipe(bucketWoodEmptyStack, "x x", "yxy", 'x',
//				"plankWood", 'y', "stickWood"));

//		// mortor
//		GameRegistry.addShapelessRecipe(mortorStack, clayStack, clayStack, clayStack, clayStack,
//				dirtStack, dirtStack, bucketWoodWaterStack);
//		GameRegistry.addShapelessRecipe(mortorStack, clayBlockStack, dirtStack, dirtStack,
//				bucketWoodWaterStack);
//		GameRegistry.addShapelessRecipe(new ItemStack(itemMortor, 8), clayBlockStack,
//				clayBlockStack, bucketWoodWaterStack, bucketWoodWaterStack, dirtStack, dirtStack,
//				dirtStack, dirtStack);

		// fried egg
		GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(itemFriedEgg), 0.1f);

		// glowstone torch recipes
		GameRegistry.addRecipe(new ItemStack(Block.torchWood), 
				new Object[] { "X", "#", 'X', Item.glowstone, '#', Item.stick });
		GameRegistry.addRecipe(new ItemStack(Item.glowstone, 4), 
				new Object[] { "X", 'X', Block.glowStone });
		
		//makeshift hatchet
		GameRegistry.addRecipe(new ItemStack(itemHatchet), 
				new Object[] { "X#", " #", 'X', Item.flint, '#', Item.stick});
		
		//makeshift knife
		GameRegistry.addRecipe(new ItemStack(itemKnife),
				new Object[] {"X", "#", 'X', Item.flint, '#', Item.stick});
	}
}
