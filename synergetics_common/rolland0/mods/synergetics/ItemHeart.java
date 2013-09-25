package rolland0.mods.synergetics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rolland0.mods.synergetics.lib.SynRef;

public class ItemHeart extends Item {

	public ItemHeart() {
		super(SynRef.ItemID_Heart);
		setUnlocalizedName(SynRef.ItemName_Heart);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(SynRef.TextureLocation_Heart);
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
