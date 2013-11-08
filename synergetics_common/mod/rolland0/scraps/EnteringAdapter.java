package mod.rolland0.scraps;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;


public class EnteringAdapter extends AdviceAdapter {
	protected EnteringAdapter(MethodVisitor mv, int acc,
			String name, String desc) {
		super(ASM4, mv, acc, name, desc);
	}
	
	@Override
	protected void onMethodEnter() {
//		visitLabel(timeVarStart);
//		int timeVar = newLocal(Type.getType("J"));
//		visitLocalVariable("timeVar", "J", null, timeVarStart, timeVarEnd, timeVar);
		 Label l0 = new Label();
			super.visitLabel(l0);
			super.visitLineNumber(2141, l0);
			super.visitVarInsn(ILOAD, 1);
			Label l1 = new Label();
			super.visitJumpInsn(IFGE, l1);
			Label l2 = new Label();
			super.visitLabel(l2);
			super.visitLineNumber(2142, l2);
			super.visitInsn(ICONST_0);
			super.visitVarInsn(ISTORE, 1);
			super.visitLabel(l1);
	}
//	
//	@Override
//	public void visitMaxs(int stack, int locals) {
//		
//	}
	

}

//public class ExpClassAdapter extends ClassVisitor {
//	public ExpClassAdapter(ClassVisitor cv) {
//		super(ASM4, cv);
//	}
//	
//	@Override
//	public MethodVisitor visitMethod( 
//			 int acc, String name, String desc, 
//			 String signature, String[] exceptions) {
//		MethodVisitor mv = cv.visitMethod(acc, name, desc, signature, exceptions);
//		if()
//	}
//}
