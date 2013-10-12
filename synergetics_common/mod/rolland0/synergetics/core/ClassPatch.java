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
//	private String _methodName;
//	private String _obfMethodName;
//	private String _Desc;
	private InsnList _replaceTarget;
	private InsnList _replaceContent;
	private boolean _targetFound;
	private int _offset;
	
	public ClassPatch(String className, InsnList replaceTarget, InsnList replaceContent) {
		_className = className;
		//_methodName = methodName;
		_replaceTarget = replaceTarget;
		_replaceContent = replaceContent;
	}
	
	public boolean shouldPatch(String translatedClassName, ClassNode classNode) {
		return _className.equals(translatedClassName);
	}
	
	public PatchResult patch(ClassNode classNode) {
		return PatchResult.ClassNotFound;
	}
	
	private PatchResult insertBeforeTarget(ClassNode node) {
		return null;
	}
	
	private PatchResult insertAfterTarget(ClassNode node) {
		return null;
	}
	
	private PatchResult replaceTarget(ClassNode node) {
		return null;
	}
	
	
	 
}
 