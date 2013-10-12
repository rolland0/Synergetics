//package mod.rolland0.scraps;
//
//import mod.rolland0.synergetics.lib.Reference;
//import net.minecraft.block.Block;
//import net.minecraft.block.material.Material;
//import net.minecraft.client.renderer.texture.IconRegister;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.world.World;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//public class SynOreBlock extends Block { 
//	private int maxHits;
//	private String displayName;
//	private int harvestLevel;
//	private String uniqueName;
//	
//	public SynOreBlock(int id, int maxHits, String displayName, int harvestLevel, String uniqueName) {
//		super(id, Material.rock);
//		this.setHardness(Reference.ORE_HARDNESS);
//		this.setUnlocalizedName(uniqueName);
//		this.setCreativeTab(CreativeTabs.tabBlock);
//		this.setStepSound(soundStoneFootstep);
//		this.maxHits = maxHits;
//		this.displayName = displayName;
//		this.harvestLevel = harvestLevel;
//		this.uniqueName = uniqueName;
//	}
//	
//	@SideOnly(Side.CLIENT)
//	@Override
//	public void registerIcons(IconRegister iconRegister) {
//		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + uniqueName);
//	}
//	
//	@Override
//	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
//		player.addExhaustion(0.025F);
//		System.out.println(uniqueName + " harvested from {" + x + "," + y + "," + z + "} with meta: " + meta);
//		//int fortuneModifier = EnchantmentHelper.getFortuneModifier(player);
//		if(meta > 1) {
//			world.setBlock(x, y, z, this.blockID, meta-1, 2);
//		}
//	}
//	
//	@Override
//	public void onBlockAdded(World world, int x, int y, int z) {
//		if(world.getBlockMetadata(x, y, z) == 0) {
//			int hits = Reference.rand.nextInt(this.maxHits)+1;
//			world.setBlock(x, y, z, this.blockID, hits, 2);
//			System.out.println("Block added: " + this.uniqueName + " with meta: " + hits);
//		}
//		
//	}
//
//	public int getId() {
//		return this.blockID;
//	}
//	
//	public int getMaxHits() {
//		return maxHits;
//	}
//
//	public String getDisplayName() {
//		return displayName;
//	}
//
//	public int getHarvestLevel() {
//		return harvestLevel;
//	}
//
//	public String getName() {
//		return uniqueName;
//	}
//	
//	
//}
