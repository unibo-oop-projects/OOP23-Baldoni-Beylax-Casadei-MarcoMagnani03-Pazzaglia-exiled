package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeFactory;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetFactory;
import unibo.exiled.model.move.MoveSetFactoryImpl;
import unibo.exiled.model.utilities.ElementalType;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * The implementation of the enemy factory.
 */
public final class EnemyFactoryImpl implements EnemyFactory {
    private static final Random RANDOM = new Random();
    private final MoveSetFactory moveSetFactory;
    private final AttributeFactory attributeFactory;

    /**
     * The constructor of the enemy factory.
     */
    public EnemyFactoryImpl() {
        this.moveSetFactory = new MoveSetFactoryImpl();
        this.attributeFactory = new AttributeFactoryImpl();
    }

    private Enemy createFromValues(final String name,
                                   final MoveSet moveSet,
                                   final Map<AttributeIdentifier, Attribute> attributes,
                                   final ElementalType type,
                                   final double droppedExperience) {
        return new EnemyImpl(name, moveSet, attributes, type, Optional.empty()) {
            @Override
            public double getDroppedExperience() {
                return droppedExperience;
            }
        };
    }

    @Override
    public Enemy createGoblin() {
        return createFromValues(SelectableEnemies.GOBLIN.name,
                moveSetFactory.defaultGrassMoveSet(1),
                attributeFactory.createGoblinAttributes(),
                ElementalType.GRASS,
                SelectableEnemies.GOBLIN.droppedExperience);
    }

    @Override
    public Enemy createBrutus() {
        return this.createFromValues(
                SelectableEnemies.BRUTUS.name,
                moveSetFactory.defaultFireMoveSet(1),
                attributeFactory.createBrutusAttributes(),
                ElementalType.FIRE,
                SelectableEnemies.BRUTUS.droppedExperience
        );
    }

    @Override
    public Enemy createWaterBoss() {
        return new BossEnemy(
                "Umidamiano",
                moveSetFactory.defaultWaterMoveSet(4),
                ElementalType.WATER);
    }

    @Override
    public Enemy createFireBoss() {
        return new BossEnemy(
                "Piercalore",
                moveSetFactory.defaultFireMoveSet(4),
                ElementalType.FIRE
        );
    }

    @Override
    public Enemy createBoltBoss() {
        return new BossEnemy(
                "Carlaccesa",
                moveSetFactory.defaultBoltMoveSet(4),
                ElementalType.BOLT
        );
    }

    @Override
    public Enemy createGrassBoss() {
        return new BossEnemy(
                "Lucionerba",
                moveSetFactory.defaultGrassMoveSet(4),
                ElementalType.GRASS
        );
    }

    @Override
    public Enemy createRandom() {
        final int rand = RANDOM.nextInt(SelectableEnemies.values().length);
        final SelectableEnemies selected = SelectableEnemies.values()[rand];
        switch (selected) {
            case BRUTUS -> {
                return this.createBrutus();
            }
            case GOBLIN -> {
                return this.createGoblin();
            }
            default -> {
                throw new IllegalStateException("Enemy generation failed.");
            }
        }
    }

    private enum SelectableEnemies {
        GOBLIN("Goblin", 10),
        BRUTUS("Brutus", 20);

        private final int droppedExperience;
        private final String name;

        SelectableEnemies(final String name, final int droppedExperience) {
            this.droppedExperience = droppedExperience;
            this.name = name;
        }
    }
}
