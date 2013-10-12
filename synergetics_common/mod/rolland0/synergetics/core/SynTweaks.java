package mod.rolland0.synergetics.core;
//package rolland0.mods.synergetics.asm;
//
//import java.util.Map;
//
//import net.minecraft.launchwrapper.IClassTransformer;
//
//import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.tree.MethodNode;
//
//import com.google.common.collect.HashMultimap;
//
//public class SynTweaks implements IClassTransformer, Opcodes
//{
//    private static HashMultimap<String, MethodAltercator> altercators = HashMultimap.create();
//    private static Map<String, ASMBlock> blocks = ASMReader.loadResource("/assets/synergetics/asm/tweaks.asm");
//    //public static ConfigTag tweaks;
//
//    public static void load()
//    {
//    	
//        alterMethod(new MethodAltercator(new ObfMapping("net/minecraft/entity/player/EntityPlayer", "addExperienceLevel", "(I)V"))
//        {
//            @Override
//            public void alter(MethodNode mv)
//            {
//            	mv.instructions = blocks.get("newAddExperienceLevel").insns;
//            }
//        });
//        
//        alterMethod(new MethodAltercator(new ObfMapping("net/minecraft/entity/player/EntityPlayer", "xpBarCap", "()I"))
//        {
//            @Override
//            public void alter(MethodNode mv)
//            {
//            	mv.instructions = blocks.get("newXpBarCap").insns;
//            }
//        });
//    }
//    
//    private static void alterMethod(MethodAltercator ma)
//    {
//        altercators.put(ma.method.javaClass(), ma);
//    }
//    
//    @Override
//    public byte[] transform(String name, String tname, byte[] bytes)
//    {
//        if (bytes == null) return null;
//        bytes = ASMHelper.alterMethods(name, bytes, altercators);
//        return bytes;
//    }
//}