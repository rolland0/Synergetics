package mod.rolland0.synergetics.lib;

import net.minecraft.item.ItemStack;

public class DataDrop {	
	
	public ItemStack drop;
	public int minNum;
	public int maxNum;
	
	public DataDrop(ItemStack drop, int minNum, int maxNum) {
		this.drop = drop;
		this.minNum = minNum;
		this.maxNum = maxNum;
	}
}
