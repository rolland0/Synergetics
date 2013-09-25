package rolland0.mods.synergetics;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Load;

public class OnWorldLoad {
	@ForgeSubscribe
	public void onWorldLoad(Load event) {
		event.world.getGameRules().setOrCreateGameRule("keepInventory", "true");
	}
}
