package unibo.exiled.controller;

import unibo.exiled.model.game.GameModel;

import java.util.Objects;

/**
 * The implementation of the game main controller.
 */
public final class GameControllerImpl implements GameController {

    private final GameModel model;

    /**
     * The constructor of the game main controller.
     *
     * @param model The game model to manage the game.
     */
    public GameControllerImpl(final GameModel model) {
        this.model = Objects.requireNonNull(model);
    }

    @Override
    public MapController getMapController() {
        return new MapControllerImpl(this.model);
    }

    @Override
    public ItemsController getItemsController() {
        return new ItemsControllerImpl(this.model);
    }

    @Override
    public CharacterController getCharacterController() {
        return new CharacterControllerImpl(this.model);
    }

    @Override
    public boolean isOver() {
        return getCharacterController().getPlayerHealth() <= 0;
    }
}
