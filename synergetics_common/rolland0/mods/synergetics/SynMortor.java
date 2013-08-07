package rolland0.mods.synergetics;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rolland0.mods.synergetics.lib.SynRef;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SynMortor extends Item {

	public SynMortor() {
		super(SynRef.ItemID_Mortor);
		this.setUnlocalizedName(SynRef.ItemName_Mortor);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister
				.registerIcon(SynRef.TextureLocation_Mortor);
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
