package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.item.ItemContainer;
import unibo.exiled.model.item.ItemNames;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.ElementalType;

/**
 * Extension of the enemy representing a Boss of the game.
 */
public final class BossEnemy extends EnemyImpl {
    /**
     * Constructs the boss.
     *
     * @param name    The name of the boss.
     * @param moveSet The move set of the boss.
     * @param type    The elemental type of the boss.
     */
    public BossEnemy(final String name,
                     final MoveSet moveSet,
                     final ElementalType type) {
        super(name,
                moveSet,
                new AttributeFactoryImpl().createBossAttributes(),
                type, ItemContainer.getItemByName(ItemNames.CRYSTAL.getName()));
    }

    @Override
    public int getDroppedExperience() {
        return 100;
    }
}
