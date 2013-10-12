package mod.rolland0.synergetics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.rolland0.synergetics.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHeart extends Item {

	public ItemHeart() {
		super(Reference.ItemID_Heart);
		setUnlocalizedName(Reference.ItemName_Heart);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(Reference.TextureLocation_Heart);
	}
	
	@Override
    public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
	
    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
    	return new EntityHeart(world, location);
    }

}
