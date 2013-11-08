package mod.rolland0.synergetics.lib;

import java.util.List;

import net.minecraft.util.Icon;

public class DataOre {
	public DataOre(int metadataValue, String displayName, Icon icon, List<DataDrop> drops) {
		this.metadataValue = metadataValue;
		this.displayName = displayName;
		this.icon = icon;
		this.drops = drops;
	}
	public int metadataValue;
	public String displayName;
	public Icon icon;
	public List<DataDrop> drops;
}
