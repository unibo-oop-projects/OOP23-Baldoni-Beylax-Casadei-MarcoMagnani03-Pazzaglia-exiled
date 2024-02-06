package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.ItemContainer;
import unibo.exiled.model.item.ItemNames;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.ElementalType;

import java.util.Map;

/**
 * Extension of the enemy representing a Boss of the game.
 */
public final class BossEnemy extends EnemyImpl {
    /**
     * Constructs the boss.
     *
     * @param name       The name of the boss.
     * @param moveSet    The move set of the boss.
     * @param attributes The attributes of the boss.
     * @param type       The elemental type of the boss.
     */
    public BossEnemy(final String name,
                     final MoveSet moveSet,
                     final Map<AttributeIdentifier, Attribute> attributes,
                     final ElementalType type) {
        super(name, moveSet, attributes, type, ItemContainer.getItemByName(ItemNames.CRYSTAL.getName()));
    }

    @Override
    public double getDroppedExperience() {
        return 100;
    }
}
