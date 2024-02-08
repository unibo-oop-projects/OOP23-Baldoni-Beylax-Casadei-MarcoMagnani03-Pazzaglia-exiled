package unibo.exiled.model.game;

/**
 * The implementation of the game core.
 */
public final class GameModelImpl implements GameModel {
    private final MapModelImpl mapModel;
    private final CharacterModel characterModel;
    private final ItemsModel itemsModel;

    /**
     * The constructor of the game core.
     */
    public GameModelImpl() {
        this.mapModel = new MapModelImpl();
        this.characterModel = new CharacterModelImpl(this);
        this.itemsModel = new ItemsModelImpl(this);
    }

    @Override
    public MapModel getMapModel() {
        return this.mapModel;
    }

    @Override
    public CharacterModel getCharacterModel() {
        return this.characterModel;
    }

    @Override
    public ItemsModel getItemsModel() {
        return this.itemsModel;
    }
}
