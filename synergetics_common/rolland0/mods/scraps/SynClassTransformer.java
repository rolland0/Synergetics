package rolland0.mods.scraps;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class SynClassTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String arg0, String arg1, byte[] arg2) {

//		if (arg0.equals("func_82242_a")) {
//			System.out.println("**************** Inside obfuscated ");
//			return printClass(arg0, arg2, true);
//		}
//
//		if (arg0.equals("net.minecraft.entity.player.EntityPlayer")) {
//			System.out.println("**************** Inside deobfuscated ");
//			return printClass(arg0, arg2, false);
//		}

		return arg2;
	}
	
//	public byte[] printClass(String name, String methodName, byte[] bytes, boolean obf) {
//		ASMReader.loadResource(res)
//		return bytes;	
//	}

	public byte[] patchClassASM(String name, byte[] bytes, boolean obfuscated) {
		String targetMethodName = "";

		// our target method
		if (obfuscated)
			targetMethodName = "func_82242_a";
		else
			targetMethodName = "addExperienceLevel";

		// set up ASM class manipulation stuff. Consult the ASM docs for details
		ClassNode classNode = new ClassNode();
		ClassReader cr = new ClassReader(bytes);
		ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS
				| ClassWriter.COMPUTE_FRAMES);
		// SynClassAdapter cv = new SynClassAdapter(cw);
		EnteringAdapter cv = null;// new EnteringAdapter(mv, acc, name, desc)
		cr.accept(classNode, 0);

		// Now we loop over all the methods declared inside the EntityPlayer
		// class until we get to the targetMethodName "addExperienceLevel"
		Iterator<MethodNode> methods = classNode.methods.iterator();
		while (methods.hasNext()) {
			MethodNode m = methods.next();
			int opp_index = -1;

			// Check if this is addExperienceLevel and it's method signature is
			// (I)V which means that it accepts an integer (I) and returns a
			// void (V)
			if ((m.name.equals(targetMethodName) && m.desc.equals("(I)V"))) {
				cv = new EnteringAdapter(m, m.access, m.name, m.desc);

				System.out.println("********** Inside target method!");

				// AbstractInsnNode currentNode = null;
				// AbstractInsnNode targetNode = null;
				//
				// @SuppressWarnings("unchecked")
				// Iterator<AbstractInsnNode> iter = m.instructions.iterator();
				//
				// int index = -1;
				//
				// //Loop over the instruction set and find the instruction FDIV
				// which
				// while(iter.hasNext()) {
				// index++;
				// currentNode = iter.next();
				// System.out.println("********* index : " + index +
				// " currentNode.getOpcode() = " + currentNode.getOpcode());
				//
				// //look for the first opcode in the method
				// if(currentNode.getOpcode() == ALOAD && targetNode == null) {
				// System.out.println("Found ALOAD");
				// targetNode = currentNode;
				// opp_index = index;
				// }
				// //System.out.println("Type: " + currentNode.getType() +
				// " Opcode: " + currentNode.getOpcode());
				//
				// }
				//
				// if (targetNode == null)
				// {
				// System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				// return bytes;
				// }
				//
				// if (opp_index == -1)
				// {
				// System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				// return bytes;
				// }

				// now we want to inject this?
				/*
				 * Label l0 = new Label(); mv.visitLabel(l0);
				 * mv.visitLineNumber(2141, l0); mv.visitVarInsn(ILOAD, 1);
				 * Label l1 = new Label(); mv.visitJumpInsn(IFGE, l1); Label l2
				 * = new Label(); mv.visitLabel(l2); mv.visitLineNumber(2142,
				 * l2); mv.visitInsn(ICONST_0); mv.visitVarInsn(ISTORE, 1);
				 * mv.visitLabel(l1);
				 */

				// Make a new instruction list
				// InsnList toInject = new InsnList();
				//
				// //add instructions
				// toInject.add(new VarInsnNode(ILOAD, 1)); //push par1 onto the
				// stack
				// toInject.add(new VarInsnNode(IFGE, opp_index+3)); //pop par1
				// off stack -- if greater than or equal to 0, skip to
				// instruction 3
				// toInject.add(new InsnNode(ICONST_0)); //push a 0 onto the
				// stack
				// toInject.add(new VarInsnNode(ISTORE, 1)); //pop 0 off of the
				// stack and store it in par1
				//
				// //inject the new instructions into the instructions list
				// m.instructions.insertBefore(m.instructions.get(opp_index),
				// toInject);

				System.out.println("Patching complete");
				break;

			}
		}
		cr.accept(cw, 0);
		return cw.toByteArray();
		//
		// classNode.accept(writer);
		// return writer.toByteArray();
	}

}
