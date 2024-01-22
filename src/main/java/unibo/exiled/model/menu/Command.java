package unibo.exiled.model.menu;

public enum Command {
    NEW_GAME("new_game"),
    QUIT("quit");

    private String commandString;

    private Command(String s) {
        this.commandString = s;
    }

    public String getCommandString() {
        return this.commandString;
    }
}
