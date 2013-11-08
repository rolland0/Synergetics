package mod.rolland0.synergetics.blocks;

import java.util.List;

import mod.rolland0.synergetics.ItemSynBlock;
import mod.rolland0.synergetics.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSynOre extends Block {
	@SideOnly(Side.CLIENT)
	private Icon[] _icons;

	public BlockSynOre() {
		super(Reference.BlockID_Ore, Material.rock);
		this.setUnlocalizedName(Reference.BlockName_Ore);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerBlock(this, ItemSynBlock.class, Reference.MOD_ID + Reference.BlockName_Ore);
		for (int i = 0; i < Reference.LocalizedBlockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(this, 1, i), Reference.LocalizedBlockNames[i]);
		}
		
		//set hardness
		this.setHardness(3.0f);
		
		//set harvest levels
		MinecraftForge.setBlockHarvestLevel(this, Reference.META_VALUE_TIN, "knife", 1);
		MinecraftForge.setBlockHarvestLevel(this, Reference.META_VALUE_TIN, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(this, Reference.META_VALUE_COPPER, "pickaxe", 1);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		_icons = new Icon[Reference.BlockTextureLocation_ore.length];
		for (int i = 0; i < _icons.length; i++) {
			_icons[i] = iconRegister.registerIcon(Reference.BlockTextureLocation_ore[i]);
		}
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
    	if(par2 < _icons.length && par2 >= 0)
    		return _icons[par2];
    	System.out.println("Invalid metadata for [" + this.getLocalizedName() + "] value: " + par2);
    	return _icons[0];
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < Reference.LocalizedBlockNames.length; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1;
	}
	
//	@Override
//	public boolean renderAsNormalBlock() {
//		return false;
//	}
//	
//	@Override
//	public int getRenderType() {
//		return Reference.RenderType;
//	}

}
