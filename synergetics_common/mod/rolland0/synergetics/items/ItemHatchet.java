package mod.rolland0.synergetics.items;

import mod.rolland0.synergetics.Synergetics;
import mod.rolland0.synergetics.lib.ItemRefInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemAxe;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHatchet extends ItemAxe {
	
	private ItemRefInfo info;

	public ItemHatchet(ItemRefInfo info) {
		super(info.getItemID(), Synergetics.makeshiftMaterial);
		this.info = info;
		setUnlocalizedName(info.getInternalName());
		GameRegistry.registerItem(this, info.getInternalName());
		LanguageRegistry.addName(this, info.getDisplayName());
		MinecraftForge.setToolClass(this, "axe", 0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(info.getInternalName());
	}

}
