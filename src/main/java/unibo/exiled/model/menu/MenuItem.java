package unibo.exiled.model.menu;

public class MenuItem {
    private String itemText;
    private Command itemCommand;

    public MenuItem(String itemText, Command itemCommand) {
        this.itemText = itemText;
        this.itemCommand = itemCommand;
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
