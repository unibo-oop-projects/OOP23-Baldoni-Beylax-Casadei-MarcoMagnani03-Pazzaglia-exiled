package unibo.exiled;

import unibo.exiled.view.NewGameView;

/**
 * The GameLauncher class is responsible for launching the game.
 */
public final class GameLauncher {

    private GameLauncher() {
    }

    /**
     * The main method to start the game.
     *
     * @param args The command-line arguments.
     */
    public static void main(final String[] args) {
        new NewGameView();
    }
}
