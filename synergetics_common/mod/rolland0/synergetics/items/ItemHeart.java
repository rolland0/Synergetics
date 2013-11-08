package mod.rolland0.synergetics.items;

import mod.rolland0.synergetics.lib.ItemRefInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHeart extends Item {

	private ItemRefInfo info;
	
	public ItemHeart(ItemRefInfo info) {
		super(info.getItemID());
		this.info = info;
		setUnlocalizedName(info.getInternalName());
		GameRegistry.registerItem(this, info.getInternalName());
		LanguageRegistry.addName(this, info.getDisplayName());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(info.getInternalName());
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
