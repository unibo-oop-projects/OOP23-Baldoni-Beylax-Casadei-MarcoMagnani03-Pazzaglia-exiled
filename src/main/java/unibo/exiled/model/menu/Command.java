package unibo.exiled.model.menu;

public enum Command {
    NEW_GAME("new_game"),
    CLOSE_MENU("close_menu"),
    QUIT("quit");

    private String commandString;

    private Command(String s) {
        this.commandString = s;
    }

    public String getCommandString() {
        return this.commandString;
    }
}
