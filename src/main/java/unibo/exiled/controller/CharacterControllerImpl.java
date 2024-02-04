package unibo.exiled.controller;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Implementation of the CharacterController interface.
 */
public final class CharacterControllerImpl implements CharacterController {

    private final GameModel model;

    /**
     * Constructor for the CharacterControllerImpl.
     *
     * @param model The game model to manage the game.
     */
    public CharacterControllerImpl(final GameModel model) {
        this.model = model;
    }

    @Override
    public List<String> getImagePathOfCharacter(final String folderPath, final String name) {
        final String loweredName = name.toLowerCase(Locale.ROOT);
        return List.of(
                folderPath,
                loweredName + "_up",
                loweredName + "_down",
                loweredName + "_left",
                loweredName + "_right");
    }

    @Override
    public double getPlayerHealth() {
        return this.model.getPlayerAttributeOf(AttributeIdentifier.HEALTH);
    }

    @Override
    public int getPlayerLevel() {
        return this.model.getPlayerLevel();
    }

    @Override
    public String getPlayerClassName() {
        return this.model.getPlayerClass().getName();
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
    public Position getPlayerPosition() {
        return this.model.getPlayerPosition();
    }

    @Override
    public List<String> getMagicMoveNames() {
        return this.model.getMagicMoves().stream().map(move -> move.name()).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<String> getPlayerMoveSet() {
        return this.model.getPlayerMoveSet().getMagicMoves().stream().map(move -> move.name())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void attack(final boolean cond) {
        // TODO: Implement attack logic
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void assignPlayerClass(final ElementalType playerClass) {
        model.assignPlayerClass(playerClass);
    }

    @Override
    public boolean getIfCharacterInPositionIsMoving(final Position position) {
        return model.getCharacterFromPosition(position).get().spriteIsMoving();
    }
}
