package mod.rolland0.synergetics.items;

import mod.rolland0.synergetics.lib.ItemRefInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMortor extends Item {
	private ItemRefInfo info;

	public ItemMortor(ItemRefInfo info) {
		super(info.getItemID());
		this.info = info;
		this.setUnlocalizedName(info.getInternalName());
		GameRegistry.registerItem(this, info.getInternalName());
		LanguageRegistry.addName(this, info.getDisplayName());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(info.getInternalName());
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			int targetId = world.getBlockId(x, y, z);
			int result = 0;
			boolean validBlock = false;
	
			if (targetId == Block.cobblestone.blockID) {
				result = Block.stone.blockID;
				validBlock = true;
			}
			
			if (validBlock) {
				world.setBlock(x, y, z, result);
				stack.stackSize -= 1;
			}
		}
		
		return false;
	}

}
