package rolland0.mods.synergetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import rolland0.mods.synergetics.lib.SynReference;

public class SynOreBlock extends Block {
	private int blockId;
	private byte metaValue;
	private String displayName;
	private int harvestLevel;
	private String uniqueName;
	
	public SynOreBlock(int id, byte maxHits, String displayName, int harvestLevel, String uniqueName) {
		super(id, Material.rock);
		this.setHardness(SynReference.OreHardness);
		this.setUnlocalizedName(uniqueName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundStoneFootstep);
		blockId = id;
		metaValue = maxHits;
		this.displayName = displayName;
		this.harvestLevel = harvestLevel;
		this.uniqueName = uniqueName;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(SynReference.MOD_ID + ":" + uniqueName);
	}

	public int getId() {
		return blockId;
	}
	
	public short getMeta() {
		return metaValue;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getHarvestLevel() {
		return harvestLevel;
	}

	public String getName() {
		return uniqueName;
	}
	
	
}
