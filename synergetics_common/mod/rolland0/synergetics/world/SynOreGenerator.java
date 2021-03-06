package mod.rolland0.synergetics.world;

import java.util.Random;

import mod.rolland0.synergetics.Synergetics;
import mod.rolland0.synergetics.lib.OreSpawnInfo;
import mod.rolland0.synergetics.lib.Reference;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class SynOreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
		case -1: generateNether(world, random, chunkX*16, chunkZ*16);
		case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16);
        case 1: generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
	}
	
	private void generateEnd(World world, Random random, int x, int z) {
		
	}

	private void generateSurface(World world, Random random, int x, int z) {
		addOreSpawn(world, random, x, z, Reference.TinSpawnInfo);
		addOreSpawn(world, random, x, z, Reference.GravelSpawnInfo);
	}

	private void generateNether(World world, Random random, int x, int z) {
		
	}

	private void addOreSpawn(World world, Random random, int blockX, int blockZ, OreSpawnInfo info) {
		int diffBtwnMinMaxY = info.getMaxY() - info.getMinY();
		for (int x = 0; x < info.getSpawnChances(); x++) {
			int posX = blockX + random.nextInt(16);
			int posY = info.getMinY() + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZ + random.nextInt(16);
			int[] targetIds = info.getBlocksToReplace();
			for (int i = 0; i < targetIds.length; i++) {
				(new WorldGenMinable(Synergetics.oreBlock.blockID, info.getTargetMetavalue(), info.getVeinSize(), targetIds[i]))
						.generate(world, random, posX, posY, posZ);
			}
		}
	}
}
