package unibo.exiled.model.game;

/**
 * The implementation of the game core.
 */
public final class GameModelImpl implements GameModel {
    private final MapModel mapModel;
    private final MenuModel menuModel;
    private final ItemsModel itemsModel;
    private final CharacterModel characterModel;

    /**
     * The constructor of the game core.
     */
    public GameModelImpl() {
        this.menuModel = new MenuModelImpl();
        this.mapModel = new MapModelImpl(this);
        this.itemsModel = new ItemsModelImpl(this);
        this.characterModel = new CharacterModelImpl(this);
    }

    @Override
    public MapModel getMapModel() {
        return this.mapModel;
    }

    @Override
    public MenuModel getMenuModel() {
        return this.menuModel;
    }

    @Override
    public ItemsModel getItemsModel() {
        return this.itemsModel;
    }

    @Override
    public CharacterModel getCharacterModel() {
        return this.characterModel;
    }
}
