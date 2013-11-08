package mod.rolland0.synergetics.lib;

public class OreSpawnInfo {
	public OreSpawnInfo(int targetBlockId, int targetMetavalue, int minY, int maxY, int baseVeinSize, 
			int bonusVeinSize, int spawnChances, int[] blocksToReplace) {
		this.targetBlockId = targetBlockId;
		this.targetMetavalue = targetMetavalue;
		this.minY = minY;
		this.maxY = maxY;
		this.blocksToReplace = blocksToReplace;
		this.baseVeinSize = baseVeinSize;
		this.bonusVeinSize = bonusVeinSize;
		this.spawnChances = spawnChances;
	}
	public int getTargetBlockId() {
		return targetBlockId;
	}
	public int getTargetMetavalue() {
		return targetMetavalue;
	}
	public int getMinY() {
		return minY;
	}
	public int getMaxY() {
		return maxY;
	}
	public int[] getBlocksToReplace() {
		return blocksToReplace;
	}
	public int getVeinSize() {
		return Reference.rand.nextInt(bonusVeinSize) + baseVeinSize;
	}
	public int getSpawnChances() {
		return spawnChances;
	}
	
	private int targetBlockId;
	private int targetMetavalue;
	private int minY;
	private int maxY;
	private int[] blocksToReplace;
	private int baseVeinSize;
	private int bonusVeinSize;
	private int spawnChances;
	
}
