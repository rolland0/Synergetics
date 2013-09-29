package rolland0.mods.synergetics;

import rolland0.mods.synergetics.lib.Reference;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SynSounds {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		System.out.println("Registering sound: " + Reference.SoundHealFile);
		event.manager.addSound(Reference.SoundHealFile);
	}
}
