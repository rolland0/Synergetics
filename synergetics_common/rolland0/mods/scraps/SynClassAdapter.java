package rolland0.mods.scraps;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class SynClassAdapter extends ClassVisitor {

	public SynClassAdapter(ClassVisitor cv) {
		super(Opcodes.ASM4, cv);
	}
}
