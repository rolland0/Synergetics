package mod.rolland0.synergetics.lib;

public class ItemRefInfo {
	public ItemRefInfo(int itemID, String internalName, String displayName) {
		this.internalName = internalName;
		this.displayName = displayName;
		this.itemID = itemID;
	}
	public String getInternalName() {
		return internalName;
	}
	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	private String internalName;
	private String displayName;
	private int itemID;
}
