package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.GameCharacterImpl;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.move.MoveSet;

import java.util.Map;

/**
 * Abstract implementation of the Enemy interface.
 */
public abstract class EnemyImpl extends GameCharacterImpl implements Enemy {
    private final MoveSet moveSet;
    // private final double level; TODO: Aggiungere livello per migliorare la logica
    // del drop di esperienza

    /**
     * Constructs an enemy with a name, move set, and attributes.
     *
     * @param name       The name of the enemy.
     * @param moveSet    The move set of the enemy.
     * @param attributes The attributes of the enemy.
     */
    public EnemyImpl(final String name,
            final MoveSet moveSet,
            final Map<AttributeIdentifier, Attribute> attributes
    /* final double level */) {
        super(name, attributes);
        this.moveSet = moveSet;
        // this.level = level; TODO
    }

    /**
     * Gets the move set of the enemy.
     *
     * @return The move set of the enemy.
     */
    @Override
    public MoveSet getMoveSet() {
        return this.moveSet;
    }

    /**
     * Gets the dropped experience when the enemy is defeated.
     *
     * @return The dropped experience.
     */
    @Override
    public abstract double getDroppedExperience();
}
