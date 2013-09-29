#A bunch of vanilla tweaks

list newAddExperienceLevel
ALOAD 0
ILOAD 1
INVOKESTATIC rolland0/mods/synergetics/util/NewMethods.addExperienceLevel(Lnet/minecraft/entity/player/EntityPlayer;I)V
RETURN

list newXpBarCap
ALOAD 0
INVOKESTATIC rolland0/mods/synergetics/util/NewMethods.xpBarCap(Lnet/minecraft/entity/player/EntityPlayer;)I
IRETURN