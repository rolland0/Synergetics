package rolland0.mods.synergetics.core;

import java.io.File;
import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@MCVersion(value = "1.6.4")
@TransformerExclusions(value={
		"rolland0.mods.synergetics.asm",
		"rolland0.mods.synergetics.core",
		"cpw.mods.fml.common"})
public class SynFMLLoadingPlugin implements IFMLLoadingPlugin {
	public static File location;
	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { ClassPatcher.class.getName() };
	}

	@Override
	public String getModContainerClass() {
		return SynDummyContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		location = (File) data.get("coremodLocation");
	}
}
