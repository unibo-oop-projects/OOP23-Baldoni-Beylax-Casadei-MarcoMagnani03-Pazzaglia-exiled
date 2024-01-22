package unibo.exiled.model.menu;

public class MenuItem {
    private int itemWidth;
    private int itemHeight;
    private String itemText;
    private Command itemCommand;

    public MenuItem(int itemWidth, int itemHeight, String itemText, Command itemCommand) {
        this.itemWidth = itemWidth;
        this.itemHeight = itemHeight;
        this.itemText = itemText;
        this.itemCommand = itemCommand;
    }

    public int getItemWidth() {
        return this.itemWidth;
    }

    public int getItemHeight() {
        return this.itemHeight;
    }

    public String getItemText() {
        return this.itemText;
    }

    public Command getItemCommand() {
        return this.itemCommand;
    }

    public void setItemText(String text) {
        this.itemText = text;
    }
}
