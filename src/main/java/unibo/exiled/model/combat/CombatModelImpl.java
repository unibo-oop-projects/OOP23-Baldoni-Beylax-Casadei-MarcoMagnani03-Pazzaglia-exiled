package unibo.exiled.model.combat;

import java.util.Optional;
import java.util.Set;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.utilities.Position;

import javax.annotation.concurrent.Immutable;

/**
 * The implementation of the combat model.
 */
@Immutable
public final class CombatModelImpl implements CombatModel {
    private final GameModel model;
    private Combat combat;

    /**
     * The constructor of CombatModelImpl.
     * 
     * @param model the game model.
     */
    public CombatModelImpl(final GameModel model) {
        this.model = model;
    }

    @Override
    public void createCombat(final Position combatPosition) {
        this.combat.setCombatPosition(combatPosition);
        this.combat = new CombatImpl(this.getPlayer(), this.getEnemy());
    }

    @Override
    public Optional<Player> getPlayer() {
        final Optional<Player> player = this.model.getCharacterModel().getPlayer();
        if (player.isPresent()) {
            return player;
        } else {
            throw new IllegalAccessError("Couldn't access the player");
        }
    }

    @Override
    public Optional<Enemy> getEnemy() {
        final Optional<GameCharacter> enemy = this.model.getCharacterModel()
                .getCharacterFromPosition(this.combat.getCombatPosition());
        if (enemy.isPresent()) {
            return Optional.of((Enemy) enemy.get());
        } else {
            throw new IllegalAccessError("Couldn't access the enemy in this position");
        }
    }

    @Override
    public CombatStatus getCombatStatus() {
        return this.combat.getCombatStatus();
    }

    @Override
    public void setCombatStatus(final CombatStatus status) {
        this.combat.setCombatStatus(status);
    }

    @Override
    public String getEnemyName() {
        return this.combat.getEnemy().get().getName();
    }

    @Override
    public double getEnemyHealth() {
        return this.combat.getEnemy().get().getHealth();
    }

    @Override
    public double getEnemyHealthCap() {
        return this.combat.getEnemy().get().getHealthCap();
    }

    @Override
    public Set<MagicMove> getEnemyMoves() {
        return this.combat.getEnemy().get().getMoveSet().getMagicMoves();
    }

    @Override
    public Position getCombatPosition() {
        return this.combat.getCombatPosition();
    }
}