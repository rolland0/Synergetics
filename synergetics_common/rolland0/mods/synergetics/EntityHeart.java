package rolland0.mods.synergetics;

import rolland0.mods.synergetics.lib.SynRef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityHeart extends EntityItem {

	public EntityHeart(World world, Entity location) {
		super(world, location.posX, location.posY, location.posZ, new ItemStack(Synergetics.itemHeart));
		lifespan = 600;
		this.delayBeforeCanPickup = 10;
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player){
		if(!worldObj.isRemote) {
			if(player.shouldHeal())
				this.playSound(SynRef.SoundHeal, 0.6f, 1.0f);
			else
				this.playSound("random.pop",  1.0f,  0.7f);
		}
		player.heal(2);
		setDead();
	}
	

}
