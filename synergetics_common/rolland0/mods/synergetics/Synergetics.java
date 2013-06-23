package rolland0.mods.synergetics;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import rolland0.mods.synergetics.lib.SynReference;
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

@Mod(modid = SynReference.MOD_ID, name = SynReference.MOD_NAME, version = SynReference.VERSION)
//@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Synergetics {
	
	public final static Block SynIronOre = new SynOreBlock(SynReference.COAL_ID, (byte)1, "Coal Ore", 1, "synOreCoal");
	public final static Block SynDiamondOre = new SynOreBlock(SynReference.DIAMOND_ID, (byte)1, "Diamond Ore", 1, "synOreDiamond"); 
	public final static Block SynRedstoneOre = new SynOreBlock(SynReference.REDSTONE_ID, (byte)1, "Redstone Ore", 1, "synOreRedstone");
	public final static Block SynLapisOre = new SynOreBlock(SynReference.LAPIS_ID, (byte)1, "Lapis Ore", 1, "synOreLapis");
	public final static Block SynCoalOre = new SynOreBlock(SynReference.IRON_ID, (byte)1, "Iron Ore", 1, "synOreIron");
	public final static Block SynGoldOre = new SynOreBlock(SynReference.GOLD_ID, (byte)1, "Gold Ore", 1, "synOreGold");
	
	@Instance(SynReference.MOD_ID)
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
			LanguageRegistry.addName(oreBlock, ore.getDisplayName());
			MinecraftForge.setBlockHarvestLevel(oreBlock, "pickaxe",  ore.getHarvestLevel());
			GameRegistry.registerBlock(oreBlock, ore.getName());
		}
	}
}
