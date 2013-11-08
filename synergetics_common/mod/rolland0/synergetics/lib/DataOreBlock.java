package mod.rolland0.synergetics.lib;

import java.util.Map;

public class DataOreBlock {
	public DataOreBlock(int id, String internalName, Map<Integer, DataOre> data) {
		this.id = id;
		this.internalName = internalName;
		this.data = data;
	}
	public int id;
	public String internalName;
	public Map<Integer, DataOre> data;
}
