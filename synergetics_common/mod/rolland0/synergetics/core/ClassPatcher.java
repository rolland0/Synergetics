package mod.rolland0.synergetics.core;

import java.util.ArrayList;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ClassPatcher implements IClassTransformer {
	public static ArrayList<ClassPatch> patches = new ArrayList<ClassPatch>();

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		
		if (transformedName.equals("net.minecraft.entity.player.EntityPlayer")) {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(bytes);
			classReader.accept(classNode, 0);
			for (MethodNode mn : classNode.methods) {
				if (mn.name.equals("addExperienceLevel")
						|| (mn.name.equals("a") && mn.desc.equals("(I)V"))) {
					mn.instructions.clear();
					mn.visitVarInsn(Opcodes.ALOAD, 0);
					mn.visitVarInsn(Opcodes.ILOAD, 1);
					if (mn.name.equals("addExperienceLevel"))
						mn.visitMethodInsn(Opcodes.INVOKESTATIC,
								Type.getInternalName(NewMethods.class),
								"addExperienceLevel",
								"(Lnet/minecraft/entity/player/EntityPlayer;I)V");
					else
						mn.visitMethodInsn(Opcodes.INVOKESTATIC,
								Type.getInternalName(NewMethods.class),
								"addExperienceLevel", "(Luf;I)V");
					mn.visitInsn(Opcodes.RETURN);
					mn.visitEnd();
				} 
				else if (mn.name.equals("xpBarCap")
						|| (mn.name.equals("bH") && mn.desc.equals("()I"))) {
					mn.instructions.clear();
					mn.visitVarInsn(Opcodes.ALOAD, 0);
					if(mn.name.equals("xpBarCap"))
						mn.visitMethodInsn(Opcodes.INVOKESTATIC,
								Type.getInternalName(NewMethods.class),
								"xpBarCap",
								"(Lnet/minecraft/entity/player/EntityPlayer;)I");
					else
						mn.visitMethodInsn(Opcodes.INVOKESTATIC,
								Type.getInternalName(NewMethods.class),
								"xpBarCap",
								"(Luf;)I");
					mn.visitInsn(Opcodes.IRETURN);
				}
			}

			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(writer);
			return writer.toByteArray();
		}
		
		else if(transformedName.equals("net.minecraft.item.EnumToolMaterial")) {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(bytes);
			classReader.accept(classNode, 0);
			int targetIndex = -1;
			for(int i = 0; i < classNode.methods.size() && targetIndex == -1; i++) {
				MethodNode mn = classNode.methods.get(i);
				for(int j = 0; j < mn.instructions.size() && targetIndex == -1; j++) {
					if(mn.instructions.get(j).getOpcode() == Opcodes.BIPUSH) {
						IntInsnNode intNode = (IntInsnNode)mn.instructions.get(j);
						if(intNode.operand == 32)
							targetIndex = j;
					}
				}
				if(targetIndex != -1) {
					mn.instructions.insertBefore(mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.SIPUSH, 750));
					mn.instructions.remove(mn.instructions.get(targetIndex+1));	
				}
			}
				
//				AbstractInsnNode node = mn.instructions.getFirst();
//				while(node.getNext() != null) {
//					if(node.getOpcode() == Opcodes.BIPUSH) {
//						IntInsnNode newNode = (IntInsnNode)node;
//						if(newNode.operand == 32) {
//							newNode.operand = 750;
//							System.out.println("************************************ VALUE CHANGED");
//							break;
//						}
//					}
//					node = node.getNext();
//				}
//			}
			
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(writer);
			return writer.toByteArray();
		}
		
		return bytes;
	}

	public String toString(ClassNode node) {
		return "================\nclass - name: " + node.name + "\naccess: "
				+ node.access + "\nsignature: " + node.signature
				+ "\n================";
	}

	public String toString(MethodNode node) {
		return "================\nmethod - name: " + node.name + "\naccess: "
				+ node.access + "\ndesc: " + node.desc + "\nsignature: "
				+ node.signature + "\n================";
	}

	public byte[] patchClass(String className, byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);
		
		
		
		return bytes;
	}

}
