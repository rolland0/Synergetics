package rolland0.mods.synergetics;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class Echo {

//	public static final String REP = "Rolland0ExpProp";
//	public static final String REX = "Rolland0Experience";

	@ForgeSubscribe
	public void onEcho(EntityConstructing event) {
//		if (event.entity instanceof EntityPlayer) {
//			// EntityPlayerMP player = (EntityPlayerMP)event.entity;
//			NBTTagCompound nbt;
//			if (!event.entity.getEntityData().hasKey(REP)) {
//				System.out.println("Cannot find key");
//				nbt = new NBTTagCompound();
//				event.entity.getEntityData().setCompoundTag(REP, nbt);
//			} else {
//				System.out.println("Found Key");
//				nbt = event.entity.getEntityData().getCompoundTag(REP);
//			}
//			((EntityPlayer) event.entity).addExperience(nbt.getInteger(REX));
//		}

		if (event.entity instanceof EntityPlayerMP && ExpProp.get((EntityPlayer)event.entity) == null) {
			ExpProp.register((EntityPlayer)event.entity);
			//event.entity.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
			//EntityPlayerMP e = (EntityPlayerMP) event.entity;
			
			//e.registerExtendedProperties(ExpProp.identifier, new ExpProp());
			//System.out.println("Entity constructing: " + event.entity);
			// e.experience = 0.0f;
			// e.experienceLevel = 1;
			// e.experienceTotal = 300;
		}

	}

//	@ForgeSubscribe
//	public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
//		if (event.entity instanceof EntityPlayerMP) {
//			EntityPlayerMP e = (EntityPlayerMP) event.entity;
//			// IExtendedEntityProperties prop =
//			// e.getExtendedProperties("expProp");
//
//			System.out.println("Entity joining: " + event.entity);
//			e.experience = 0.0f;
//			e.experienceLevel = 1;
//			e.experienceTotal = 300;
//		}
//	}
}
