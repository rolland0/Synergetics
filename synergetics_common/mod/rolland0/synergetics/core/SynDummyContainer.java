package mod.rolland0.synergetics.core;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class SynDummyContainer extends DummyModContainer {
	public SynDummyContainer() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "SynergeticsCore";
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
	
	@Subscribe
	public void modConstruction(FMLConstructionEvent event) {
		
	}
	
	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Subscribe
	public void postInit(FMLPostInitializationEvent event) {

	}
}
