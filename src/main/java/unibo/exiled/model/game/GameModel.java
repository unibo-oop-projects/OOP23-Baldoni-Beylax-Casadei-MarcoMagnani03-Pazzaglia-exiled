package unibo.exiled.model.game;

/**
 * The model of the game, its core.
 */
public interface GameModel {

    MapModel getMapModel();

    CharacterModel getCharacterModel();

    ItemsModel getItemsModel();

    MenuModel getMenuModel();
}
