package unibo.exiled.model.Item;

public interface Inventory {
    public void addItem(Item item);
    public Integer getQuantity(Item item);
    public void removeItem(Item position);
    public boolean containsItem(UsableItem item);
}
