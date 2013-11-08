package mod.rolland0.synergetics;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class SynCraftingHandler implements ICraftingHandler {

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
//		for(int i = 0; i < craftMatrix.getSizeInventory(); i++) {
//			ItemStack stack = craftMatrix.getStackInSlot(i);
//			if(stack != null) {
//				if(stack.getItem() == Synergetics.bucketWoodWater) {
//					if(!player.inventory.addItemStackToInventory(new ItemStack(Synergetics.bucketWoodEmpty, stack.stackSize)))
//						if(!player.worldObj.isRemote)
//							player.dropItem(Synergetics.bucketWoodEmpty.itemID, stack.stackSize);
//				}
//			}
//		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// TODO Auto-generated method stub

	}

}
