package mod.rolland0.synergetics.items;

import mod.rolland0.synergetics.lib.ItemRefInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFriedEgg extends ItemFood {
	
	private ItemRefInfo info;

	public ItemFriedEgg(ItemRefInfo info) {
		super(info.getItemID(), 3, 0.6f, false);
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

}
