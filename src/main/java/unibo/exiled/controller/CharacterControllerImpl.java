package unibo.exiled.controller;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.player.PlayerClass;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
        return this.model.getPlayerClass().elementalType().getName();
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
        return this.model.getMagicMoves().stream().map(MagicMove::name).toList();
    }

    @Override
    public List<String> getPlayerMoveSet() {
        return this.model.getPlayerMoveSet().getMagicMoves().stream().map(MagicMove::name).toList();
    }

    @Override
    public void attack(final boolean cond) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public void assignPlayerClass(final PlayerClass playerClass) {
        this.model.assignPlayerClass(playerClass);
    }

    @Override
    public boolean getIfCharacterInPositionIsMoving(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().spriteIsMoving();
        } else {
            throw new IllegalArgumentException("The position doesn't contain a character.");
        }
    }
}
