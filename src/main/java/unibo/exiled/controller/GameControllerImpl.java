package unibo.exiled.controller;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of the game main controller.
 */
public class GameControllerImpl implements GameController {

    private final GameModel model;

    /**
     * The constructor of the game main controller.
     *
     * @param model The game model to manage the game.
     */
    public GameControllerImpl(final GameModel model) {
        this.model = model;
    }

    @Override
    public List<String> getImagePathOfCharacter(final String folderPath, final String name) {
        return List.of(
                folderPath,
                name + "_up",
                name + "_down",
                name + "_left",
                name + "_right"
        );
    }

    @Override
    public double getPlayerHealth() {
        return model.getPlayerAttributeOf(AttributeIdentifier.HEALTH);
    }

    @Override
    public double getPlayerLevel() {
        return model.getPlayerLevel();
    }

    @Override
    public int getMapSize() {
        return model.getMapSize();
    }

    @Override
    public String getPlayerClassName() {
        return model.getPlayerClass().getName();
    }

    @Override
    public void movePlayer(final Direction direction) {
        this.model.movePlayer(direction);
    }

    @Override
    public void moveEnemies() {
        this.model.moveEnemies();
    }

    @Override
    public boolean isOver() {
        return getPlayerHealth() <= 0;
    }

    @Override
    public Position getPlayerPosition() {
        return model.getPlayerPosition();
    }

    @Override
    public boolean isEnemyInCell(final Position position) {
        final Optional<GameCharacter> gottenCharacter = model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get() instanceof Enemy;
        } else {
            return false;
        }
    }

    @Override
    public String getNameOfCharacterInPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getName();
        } else {
            return "";
        }
    }

    @Override
    public CellType getCellType(final Position position) {
        return model.getCellTypeOf(position);
    }
}
