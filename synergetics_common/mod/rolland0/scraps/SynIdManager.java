package mod.rolland0.scraps;

public class SynIdManager {
	private static int lastItemId = 5000;
	public static int nextItemId() {
		lastItemId++;
		return lastItemId;
	}
	
	private static int lastBlockId = 500;
	public static int nextBlockId() {
		lastBlockId++;
		return lastBlockId;
	}
	
	private static int lastOreMetaValue = -1;
	public static int nextOreMetaValue() {
		lastOreMetaValue++;
		return lastOreMetaValue;
	}
	
	private static int oreID = 500;
	public static int getOreID() {
		return oreID;
	}
	private static int oreItemID = 250;
	public static int getOreItemId() {
		return oreItemID;
	}
}
