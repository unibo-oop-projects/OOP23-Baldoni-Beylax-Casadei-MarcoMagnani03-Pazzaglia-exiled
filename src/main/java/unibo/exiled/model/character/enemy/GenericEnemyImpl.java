package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.utilities.ItemsContainer;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.utilities.ElementalType;

import java.util.Map;
import java.util.Optional;

/**
 * The generic enemy class implementation.
 */
public final class GenericEnemyImpl extends EnemyImpl {

    private final int droppedExperience;

    /**
     * Constructs an enemy with a name, move set, and attributes.
     *
     * @param name              The name of the enemy.
     * @param moveSet           The move set of the enemy.
     * @param attributes        The attributes of the enemy.
     * @param type              The Elemental Type of the enemy.
     * @param droppedExperience The dropped experience of the enemy.
     */
    public GenericEnemyImpl(final String name,
                            final MoveSet moveSet,
                            final Map<AttributeIdentifier, Attribute> attributes,
                            final ElementalType type,
                            final int droppedExperience) {
        super(name, moveSet, attributes, type);
        this.droppedExperience = droppedExperience;
    }

    @Override
    public int getDroppedExperience() {
        return this.droppedExperience;
    }

    @Override
    public Optional<Item> getHeldItem() {
        return ItemsContainer.getRandomItem();
    }
}
