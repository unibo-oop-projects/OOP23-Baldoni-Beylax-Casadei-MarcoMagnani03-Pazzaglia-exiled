package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.attributes.AttributeFactory;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.move.MoveSetFactory;
import unibo.exiled.model.move.MoveSetFactoryImpl;
import unibo.exiled.model.utilities.ElementalType;

import java.util.Random;

/**
 * The implementation of the enemy factory.
 */
public final class EnemyFactoryImpl implements EnemyFactory {
    private static final Random RANDOM = new Random();
    private final MoveSetFactory moveSetFactory;
    private final AttributeFactory attributeFactory;
    private final double defaultExperience;

    /**
     * The constructor of the enemy factory.
     */
    public EnemyFactoryImpl() {
        this.defaultExperience = 10;
        this.moveSetFactory = new MoveSetFactoryImpl();
        this.attributeFactory = new AttributeFactoryImpl();
    }

    @Override
    public Enemy createGoblin() {
        return new EnemyImpl(
                "Goblin",
                moveSetFactory.defaultNormalMoveSet(1),
                attributeFactory.createGoblinAttributes(), ElementalType.NORMAL) {
            @Override
            public double getDroppedExperience() {
                return defaultExperience;
            }
        };
    }

    @Override
    public Enemy createBrutus() {
        return new EnemyImpl(
                "Brutus",
                moveSetFactory.defaultNormalMoveSet(1),
                attributeFactory.createBrutusAttributes(), ElementalType.FIRE) {
            @Override
            public double getDroppedExperience() {
                return defaultExperience * 2;
            }
        };
    }

    @Override
    public Enemy createRandom() {
        final int factoryMethodsCount = this.getClass().getMethods().length - 10;
        final int choice = RANDOM.nextInt(factoryMethodsCount);
        switch (choice) {
            case 0 -> {
                return this.createBrutus();
            }
            case 1 -> {
                return this.createGoblin();
            }
            default -> throw new IllegalStateException("No enemy-creation method found!");
        }
    }
}
