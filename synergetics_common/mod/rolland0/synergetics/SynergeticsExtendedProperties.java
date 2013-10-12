package mod.rolland0.synergetics;

import mod.rolland0.synergetics.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SynergeticsExtendedProperties implements IExtendedEntityProperties {
	public static final String identifier = "SynergeticsExtendedProperties";
	public static final int expWatcher = 20;
	
	private final EntityPlayer player;
	
	public SynergeticsExtendedProperties(EntityPlayer player) {
		this.player = player;
		this.player.getDataWatcher().addObject(expWatcher, 0);
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(identifier, new SynergeticsExtendedProperties(player));
	}
	
	public static final SynergeticsExtendedProperties get(EntityPlayer player) {
		return (SynergeticsExtendedProperties)player.getExtendedProperties(identifier);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("exp", this.player.getDataWatcher().getWatchableObjectInt(expWatcher));
		compound.setTag(identifier, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound)compound.getTag(identifier);
		System.out.println("exp going into datawatcher: " + properties.getInteger("exp"));
		this.player.getDataWatcher().updateObject(expWatcher, properties.getInteger("exp"));
		//this.exp = properties.getInteger("exp");
		//System.out.println("[EXP PROPS] Exp from NBT: " + this.player.getDataWatcher().getWatchableObjectInt(expWatcher));
	}
	
	private static String getSaveKey(EntityPlayer player) {
		return player.username + ":" + identifier;
	}
	
	public static void saveProxyData(EntityPlayer player) {
		SynergeticsExtendedProperties playerData = SynergeticsExtendedProperties.get(player);
		NBTTagCompound savedData = new NBTTagCompound();
		playerData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}
	
	public static void loadProxyData(EntityPlayer player) {
		SynergeticsExtendedProperties playerData = SynergeticsExtendedProperties.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
		
		if(savedData != null) {
			playerData.loadNBTData(savedData);
		}
		else
			System.out.println("savedData is NULL");
	}

	@Override
	public void init(Entity entity, World world) {
	}
	
	public void updateExp() {
		this.player.getDataWatcher().updateObject(expWatcher, this.player.experienceTotal);
		System.out.println("update exp - saved value: " + this.player.getDataWatcher().getWatchableObjectInt(expWatcher));
	}
	
	public void persistExp() {
		if(player.experienceTotal == 0) {
			System.out.println("Adding exp to the player: " + this.player.getDataWatcher().getWatchableObjectInt(expWatcher));
			player.addExperience(this.player.getDataWatcher().getWatchableObjectInt(expWatcher));
		}
	}
}
