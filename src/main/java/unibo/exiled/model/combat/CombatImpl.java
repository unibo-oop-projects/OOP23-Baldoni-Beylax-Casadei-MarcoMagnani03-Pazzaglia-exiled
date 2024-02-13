package unibo.exiled.model.combat;

import java.util.Optional;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.utilities.Position;

/**
 * The implementation of the combat.
 */
public final class CombatImpl implements Combat {
    private final Player player;
    private final Enemy enemy;
    private transient Position combatPosition;
    private transient CombatStatus combatStatus;

    /**
     * The constructor of CombatImpl.
     * 
     * @param player the player.
     * @param enemy  the enemy.
     */
    public CombatImpl(final Player player, final Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public Position getCombatPosition() {
        return this.combatPosition;
    }

    @Override
    public void setCombatPosition(final Position position) {
        this.combatPosition = position;
    }

    @Override
    public CombatStatus getCombatStatus() {
        return this.combatStatus;
    }

    @Override
    public void setCombatStatus(final CombatStatus status) {
        this.combatStatus = status;
    }

    @Override
    public Optional<Player> getPlayer() {
        return Optional.of(this.player);
    }

    @Override
    public Optional<Enemy> getEnemy() {
        return Optional.of(this.enemy);
    }
}
