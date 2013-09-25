package rolland0.mods.synergetics;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ExpProp implements IExtendedEntityProperties {
	public static final String identifier = "Rolland0ExpProp";
	public static final int expWatcher = 20;
	
	private final EntityPlayer player;
	
	public ExpProp(EntityPlayer player) {
		this.player = player;
		this.player.getDataWatcher().addObject(expWatcher, 0);
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(identifier, new ExpProp(player));
	}
	
	public static final ExpProp get(EntityPlayer player) {
		return (ExpProp)player.getExtendedProperties(identifier);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("exp", 
				this.player.getDataWatcher().getWatchableObjectInt(expWatcher));
		compound.setTag(identifier, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound)compound.getTag(identifier);
		this.player.getDataWatcher().updateObject(expWatcher, properties.getInteger("exp"));
		//this.exp = properties.getInteger("exp");
		System.out.println("[EXP PROPS] Exp from NBT: " + this.player.getDataWatcher().getWatchableObjectInt(expWatcher));
	}

	@Override
	public void init(Entity entity, World world) {
	}
	
	public void updateExp() {
		this.player.getDataWatcher().updateObject(expWatcher, this.player.experienceTotal);
	}
	
	public void persistExp() {
		player.experience = 0.0f;
		player.experienceLevel = 0;
		player.experienceTotal = 0;
		player.addExperience(this.player.getDataWatcher().getWatchableObjectInt(expWatcher));
	}
}
