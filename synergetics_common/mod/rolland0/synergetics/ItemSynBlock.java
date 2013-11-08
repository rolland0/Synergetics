package mod.rolland0.synergetics;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSynBlock extends ItemBlock {

	public ItemSynBlock(int par1) {
		super(par1);
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
	}
	
	@Override
	public int getMetadata(int par1) {
		return par1;
	}
	

}
