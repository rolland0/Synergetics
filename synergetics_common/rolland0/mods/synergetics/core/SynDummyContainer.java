package rolland0.mods.synergetics.core;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class SynDummyContainer extends DummyModContainer {
	public SynDummyContainer() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "synergeticscore";
		meta.name = "SynergeticsCore";
		meta.version = "@VERSION@";
		meta.credits = "Roll Credits...";
		meta.authorList = Arrays.asList("rolland0");
		meta.description = "";
		meta.url = "";
		meta.updateUrl = "";
		meta.screenshots = new String[0];
		meta.logoFile = "";
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
}
