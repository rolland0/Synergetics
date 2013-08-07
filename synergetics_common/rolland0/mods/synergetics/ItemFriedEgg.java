package rolland0.mods.synergetics;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import rolland0.mods.synergetics.lib.SynRef;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFriedEgg extends ItemFood {

	public ItemFriedEgg() {
		super(SynRef.ItemID_FriedEgg, 3, 0.6f, false);
		this.setUnlocalizedName(SynRef.ItemName_FriedEgg);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(SynRef.TextureLocation_FriedEgg);
	}

}
