package rolland0.mods.synergetics;

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
