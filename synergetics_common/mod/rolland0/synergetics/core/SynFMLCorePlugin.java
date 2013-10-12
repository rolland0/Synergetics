package mod.rolland0.synergetics.core;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@MCVersion(value = "1.6.4")
@TransformerExclusions(value={"mod.rolland0.synergetics.core"})
public class SynFMLCorePlugin implements IFMLLoadingPlugin {
	//public static File location;
	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { "mod.rolland0.synergetics.core.ClassPatcher" };
	}

	@Override
	public String getModContainerClass() {
		return "mod.rolland0.synergetics.core.SynDummyContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		//location = (File) data.get("coremodLocation");
	}
}
