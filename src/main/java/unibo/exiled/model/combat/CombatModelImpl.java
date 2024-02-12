package unibo.exiled.model.combat;

import java.util.Optional;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.utilities.Position;

/**
 * The implementation of the combat model.
 */
public final class CombatModelImpl implements CombatModel {
    private final GameModel gameModel;
    private transient Combat combat;

    /**
     * The constructor of CombatModelImpl.
     * 
     * @param gameModel the game model.
     */
    public CombatModelImpl(final GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void createCombat(final Position combatPosition) {
        this.combat = new CombatImpl(this.getPlayer(), this.getEnemy(combatPosition));
        this.combat.setCombatPosition(combatPosition);
    }

    @Override
    public Optional<Combat> getCurrentCombat() {
        return Optional.of(this.combat);
    }

    @Override
    public Optional<Player> getPlayer() {
        final Optional<Player> player = this.gameModel.getCharacterModel().getPlayer();
        if (player.isPresent()) {
            return player;
        } else {
            throw new IllegalAccessError("Couldn't access the player");
        }
    }

    @Override
    public Optional<Enemy> getEnemy(final Position combatPosition) {
        final Optional<GameCharacter> enemy = this.gameModel.getCharacterModel()
                .getCharacterFromPosition(combatPosition);
        if (enemy.isPresent()) {
            return Optional.of((Enemy) enemy.get());
        } else {
            throw new IllegalAccessError("Couldn't access the enemy in this position");
        }
    }
}
