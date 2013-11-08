package mod.rolland0.synergetics.core;

import java.util.ArrayList;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
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
					if(mn.instructions.get(j).getOpcode() == Opcodes.LDC) {
						LdcInsnNode targetNode = (LdcInsnNode)mn.instructions.get(j);
						if(targetNode.cst.equals("GOLD"))
							targetIndex = j;
					}
				}
				if(targetIndex != -1) {
					targetIndex += 2; //ICONST_0  --> miningLevel
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new InsnNode(Opcodes.ICONST_2));
					targetIndex += 1; //BIPUSH 32 --> durability
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.SIPUSH, 750));
					targetIndex += 1; //LDC 12.0  --> efficiencyOnProperMaterial
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new LdcInsnNode(7.0f));
					targetIndex += 1; //FCONST_0  --> damageVsEntity
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new LdcInsnNode(2.5f));
					targetIndex += 1; //BIPUSH 22 --> enchantability
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.BIPUSH, 18));
				}
			}
			
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(writer);
			return writer.toByteArray();
		}
		
		else if(transformedName.equals("net.minecraft.item.EnumArmorMaterial")) {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(bytes);
			classReader.accept(classNode, 0);
			int targetIndex = -1;
			for(int i = 0; i < classNode.methods.size() && targetIndex == -1; i++) {
				MethodNode mn = classNode.methods.get(i);
				for(int j = 0; j < mn.instructions.size() && targetIndex == -1; j++) {
					if(mn.instructions.get(j).getOpcode() == Opcodes.LDC) {
						LdcInsnNode targetNode = (LdcInsnNode)mn.instructions.get(j);
						if(targetNode.cst.equals("GOLD"))
							targetIndex = j;
					}
				}
				if(targetIndex != -1) {
					targetIndex += 2; //BIPUSH 7
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.BIPUSH, 22));
					targetIndex += 5; //ICONST_2
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.BIPUSH, 3));
					targetIndex += 4; //ICONST_5
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.BIPUSH, 7));
					targetIndex += 4; //ICONST_3
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.BIPUSH, 5));
					targetIndex += 4; //ICONST_1
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.BIPUSH, 2));
					targetIndex += 2; //BIPUSH 25
					replaceInstruction(mn.instructions, mn.instructions.get(targetIndex), new IntInsnNode(Opcodes.BIPUSH, 15));
				}
				
			}
			
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(writer);
			return writer.toByteArray();
		}
		
		return bytes;
	}
	
	private void replaceInstruction(InsnList instructions, AbstractInsnNode loc, AbstractInsnNode insn) {
		instructions.insertBefore(loc, insn);
		instructions.remove(loc);
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
