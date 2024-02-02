package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.attributes.AttributeFactory;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.move.MoveSetFactory;
import unibo.exiled.model.move.MoveSetFactoryImpl;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * The implementation of the enemy factory.
 */
public final class EnemyFactoryImpl implements EnemyFactory {
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
                attributeFactory.createGoblinAttributes()) {
            @Override
            public double getDroppedExperience() {
                return defaultExperience;
            }
        };
    }

    @Override
    public Enemy createBrutus() {
        final List<String> paths = List.of("enemy"
                + File.separator
                + "brutus", "brutus_up", "brutus_down", "brutus_left", "brutus_right");
        return new EnemyImpl(
                "Brutus",
                moveSetFactory.defaultNormalMoveSet(1),
                attributeFactory.createBrutusAttributes()) {
            @Override
            public double getDroppedExperience() {
                return defaultExperience * 2;
            }
        };
    }

    @Override
    public Enemy createRandom() {
        final int factoryMethodsCount = this.getClass().getMethods().length - 10;
        Random rand = new Random();
        final int choice = rand.nextInt(factoryMethodsCount);
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
