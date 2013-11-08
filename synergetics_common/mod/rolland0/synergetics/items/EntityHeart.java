package mod.rolland0.synergetics.items;

import mod.rolland0.synergetics.Synergetics;
import mod.rolland0.synergetics.lib.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityHeart extends EntityItem {

	public EntityHeart(World world, Entity location) {
		super(world, location.posX, location.posY, location.posZ, new ItemStack(Synergetics.itemHeart));
		lifespan = 600; //in seconds?
		this.delayBeforeCanPickup = 60; //in ticks
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player){
		if(!worldObj.isRemote && player.shouldHeal()) {
			this.playSound(Reference.SoundHeal, 0.6f, 1.0f);
			player.heal(Reference.HeartHealAmount);
			setDead();
		}
	}
}
