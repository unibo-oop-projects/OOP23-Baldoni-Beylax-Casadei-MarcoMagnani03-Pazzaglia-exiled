package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeFactory;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.ItemContainer;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetFactory;
import unibo.exiled.model.move.MoveSetFactoryImpl;
import unibo.exiled.model.utilities.ElementalType;

import java.util.Map;
import java.util.Random;

/**
 * The implementation of the enemy factory.
 */
public final class EnemyFactoryImpl implements EnemyFactory {
    private static final Random RANDOM = new Random();
    private static final int DROPPED_EXPERIENCE_BASE = 10;
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
                                   final int droppedExperience) {
        return new EnemyImpl(name, moveSet, attributes, type, ItemContainer.getRandomItem()) {
            @Override
            public int getDroppedExperience() {
                return droppedExperience;
            }
        };
    }

    @Override
    public Enemy createGoblin() {
        return createFromValues(SelectableEnemies.GOBLIN.name,
                moveSetFactory.defaultGrassMoveSet(),
                attributeFactory.createGoblinAttributes(),
                ElementalType.GRASS,
                SelectableEnemies.GOBLIN.droppedExperience);
    }

    @Override
    public Enemy createBrutus() {
        return this.createFromValues(
                SelectableEnemies.BRUTUS.name,
                moveSetFactory.defaultFireMoveSet(),
                attributeFactory.createBrutusAttributes(),
                ElementalType.FIRE,
                SelectableEnemies.BRUTUS.droppedExperience
        );
    }

    @Override
    public Enemy createWaterBoss() {
        return new BossEnemy(
                "Umidamiano",
                moveSetFactory.defaultWaterMoveSet(),
                ElementalType.WATER);
    }

    @Override
    public Enemy createFireBoss() {
        return new BossEnemy(
                "Piercalore",
                moveSetFactory.defaultFireMoveSet(),
                ElementalType.FIRE
        );
    }

    @Override
    public Enemy createBoltBoss() {
        return new BossEnemy(
                "Carlaccesa",
                moveSetFactory.defaultBoltMoveSet(),
                ElementalType.BOLT
        );
    }

    @Override
    public Enemy createGrassBoss() {
        return new BossEnemy(
                "Lucionerba",
                moveSetFactory.defaultGrassMoveSet(),
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
            case WHIRLER -> {
                return this.createWhirler();
            }
            case WAVE_BREAKER -> {
                return this.createWaveBreaker();
            }
            case AQUA_SHADE -> {
                return this.createAquaShade();
            }
            default -> throw new IllegalStateException("Enemy generation failed.");
        }
    }

    @Override
    public Enemy createWhirler() {
        return createFromValues(SelectableEnemies.WHIRLER.name,
                moveSetFactory.whirlerMoveset(),
                attributeFactory.createWhirlerAttributes(),
                ElementalType.FIRE, SelectableEnemies.WHIRLER.droppedExperience);
    }

    @Override
    public Enemy createWaveBreaker() {
        return createFromValues(SelectableEnemies.WAVE_BREAKER.name,
                moveSetFactory.defaultWaterMoveSet(),
                attributeFactory.createWaveBreakerAttributes(),
                ElementalType.WATER, SelectableEnemies.WAVE_BREAKER.droppedExperience);
    }

    @Override
    public Enemy createAquaShade() {
        return createFromValues(SelectableEnemies.AQUA_SHADE.name,
                moveSetFactory.defaultWaterMoveSet(), attributeFactory.createAquaShadeAttributes(),
                ElementalType.WATER, SelectableEnemies.AQUA_SHADE.droppedExperience);
    }

    private enum SelectableEnemies {
        GOBLIN("Goblin", DROPPED_EXPERIENCE_BASE),
        BRUTUS("Brutus", DROPPED_EXPERIENCE_BASE * 2),
        WHIRLER("Whirler", DROPPED_EXPERIENCE_BASE * 2),
        AQUA_SHADE("Aquashade", DROPPED_EXPERIENCE_BASE * 2),
        WAVE_BREAKER("Wavebreaker", DROPPED_EXPERIENCE_BASE);

        private final int droppedExperience;
        private final String name;

        SelectableEnemies(final String name, final int droppedExperience) {
            this.droppedExperience = droppedExperience;
            this.name = name;
        }
    }
}
