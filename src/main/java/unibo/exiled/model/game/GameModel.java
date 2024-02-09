package unibo.exiled.model.game;

/**
 * The model of the game, its core.
 */
public interface GameModel {
    /**
     * Gets the MapModel.
     *
     * @return The MapModel.
     */
    MapModel getMapModel();

    /**
     * Gets the character model.
     *
     * @return The CharacterModel.
     */
    CharacterModel getCharacterModel();

    /**
     * Gets the Items Model.
     *
     * @return The Items Model.
     */
    ItemsModel getItemsModel();

    /**
     * Gets the Menu Model.
     *
     * @return A MenuModel.
     */
    MenuModel getMenuModel();
}
