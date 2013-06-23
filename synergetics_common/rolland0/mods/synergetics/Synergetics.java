package rolland0.mods.synergetics;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import rolland0.mods.synergetics.lib.SynRef;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = SynRef.MOD_ID, name = SynRef.MOD_NAME, version = SynRef.VERSION)
//@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Synergetics {
	
	public final static Block SynIronOre = new SynOreBlock(SynRef.COAL_ID, SynRef.COAL_MAX_HITS, "Coal Ore",SynRef.COAL_HARVEST_LEVEL, "synOreCoal");
	public final static Block SynDiamondOre = new SynOreBlock(SynRef.DIAMOND_ID, SynRef.DIAMOND_MAX_HITS, "Diamond Ore", SynRef.DIAMOND_HARVEST_LEVEL, "synOreDiamond"); 
	public final static Block SynRedstoneOre = new SynOreBlock(SynRef.REDSTONE_ID, SynRef.REDSTONE_MAX_HITS, "Redstone Ore", SynRef.REDSTONE_HARVEST_LEVEL, "synOreRedstone");
	public final static Block SynLapisOre = new SynOreBlock(SynRef.LAPIS_ID, SynRef.LAPIS_MAX_HITS, "Lapis Ore", SynRef.LAPIS_HARVEST_LEVEL, "synOreLapis");
	public final static Block SynCoalOre = new SynOreBlock(SynRef.IRON_ID, SynRef.IRON_MAX_HITS, "Iron Ore", SynRef.IRON_HARVEST_LEVEL, "synOreIron");
	public final static Block SynGoldOre = new SynOreBlock(SynRef.GOLD_ID,SynRef.GOLD_MAX_HITS, "Gold Ore",  SynRef.GOLD_HARVEST_LEVEL, "synOreGold");
	
	@Instance(SynRef.MOD_ID)
	public static Synergetics instance;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.ORE_GEN_BUS.register(new SynOreReplacer());
	}

	@Init
	public void load(FMLInitializationEvent event) {
		registerBlock(SynIronOre);
		registerBlock(SynGoldOre);
		registerBlock(SynDiamondOre);
		registerBlock(SynCoalOre);
		registerBlock(SynRedstoneOre);
		registerBlock(SynLapisOre);
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
	}
	
	private void registerBlock(Block oreBlock) {
		if(oreBlock instanceof SynOreBlock) {
			SynOreBlock ore = (SynOreBlock)oreBlock;
			LanguageRegistry.addName(ore, ore.getDisplayName());
			MinecraftForge.setBlockHarvestLevel(ore, "pickaxe", ore.getHarvestLevel());
			GameRegistry.registerBlock(ore, ore.getName());
		}
	}
}
