package unibo.exiled.model.item;

interface Inventory {
    public void addItem(final Item item);
    public Integer getQuantity(final Item item);
    public void removeItem(final Item position);
    public boolean containsItem(final UsableItem item);
}
