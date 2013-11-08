package mod.rolland0.synergetics.core;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;

public class ClassPatch {	
	
	public static enum PatchType {
		InsertBeforeTarget,
		InsertAfterTarget,
		ReplaceTarget
	}
	
	public static enum PatchResult {
		Success,
		ClassNotFound,
		MethodNotFound,
		TargetNotFound,
		MultipleTargetsFound
	}
	
	private String _className;
public ClassPatch(String className, InsnList replaceTarget, InsnList replaceContent) {
		_className = className;
	}
	
	public boolean shouldPatch(String translatedClassName, ClassNode classNode) {
		return _className.equals(translatedClassName);
	}
	
	public PatchResult patch(ClassNode classNode) {
		return PatchResult.ClassNotFound;
	}
	
	
	 
}
 