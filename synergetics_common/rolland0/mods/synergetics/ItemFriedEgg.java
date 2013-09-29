package rolland0.mods.synergetics;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import rolland0.mods.synergetics.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFriedEgg extends ItemFood {

	public ItemFriedEgg() {
		super(Reference.ItemID_FriedEgg, 3, 0.6f, false);
		this.setUnlocalizedName(Reference.ItemName_FriedEgg);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(Reference.TextureLocation_FriedEgg);
	}

}
