package unibo.exiled;

import unibo.exiled.view.NewGameView;

public final class GameLauncher {
    
    private GameLauncher(){}

    public static void main(final String[] args) {
        new NewGameView();
    }
}