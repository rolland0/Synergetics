package rolland0.mods.synergetics.core;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;

import cpw.mods.fml.common.FMLLog;

public class ClassPatcher implements IClassTransformer {
	private byte[] classBytes = null;

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
    	if (bytes == null) { return null; }
    	FMLLog.getLogger().info("name: " + name + "\ntransformedName: " + transformedName);

        ClassNode classNode = new ClassNode() {
        	@Override
        	public MethodVisitor visitMethod(int access, String name,
        			String desc, String signature, String[] exceptions) {
/*        		System.out.println("access: " + access + "\nname: " + name + "\ndesc" + desc
        				+ "\nsignature" + signature);
        		System.out.println();*/
        		return super.visitMethod(access, name, desc, signature, exceptions);
        	}
        };
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);


        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }
    
    public byte[] patchClass(String className, String methodName, byte[] bytes) {
    	
    	return bytes;
    }

}
