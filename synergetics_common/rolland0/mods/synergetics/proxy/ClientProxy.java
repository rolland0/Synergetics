package rolland0.mods.synergetics.proxy;

import rolland0.mods.synergetics.SynSounds;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		
	}
	
	@Override
	public void registerCraftingHandlers() {
		
	}
	
	public void registerSounds() {
		MinecraftForge.EVENT_BUS.register(new SynSounds());
	}
}
