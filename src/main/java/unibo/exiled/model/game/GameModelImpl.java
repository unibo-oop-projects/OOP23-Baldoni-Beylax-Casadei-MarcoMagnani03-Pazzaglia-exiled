package unibo.exiled.model.game;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyFactory;
import unibo.exiled.model.character.enemy.EnemyFactoryImpl;
import unibo.exiled.model.character.enemy.EnemyCollectionImpl;
import unibo.exiled.model.character.enemy.BossEnemy;
import unibo.exiled.model.character.player.PlayerClass;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.item.HealingItem;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemType;
import unibo.exiled.model.item.PowerUpItem;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.Moves;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;
import unibo.exiled.model.character.attributes.AdditiveAttribute;
import unibo.exiled.model.character.attributes.MultiplierAttribute;
import unibo.exiled.model.character.attributes.CombinedAttribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.Attribute;

import java.util.stream.Collectors;
import java.util.Random;
import java.util.Optional;
import java.util.Map;
import java.util.Set;


/**
 * The implementation of the game core.
 */
public final class GameModelImpl implements GameModel {
    private static final Random RANDOM = new Random();
    private static final int RANGE_PLAYER_ENEMY = 2;
    private final EnemyCollection enemyCollection;
    private final MapModelImpl mapModel;
    private Player player;

    /**
     * The constructor of the game core.
     */
    public GameModelImpl() {
        //Constants loading
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        final int playerExperienceCap = Integer.parseInt(Constants.getConstantOf("PLAYER_EXPERIENCE_CAP"));
        final int defaultExperience = Integer.parseInt(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE"));
        final int playerLevelIncrease = Integer.parseInt(Constants.getConstantOf("PLAYER_LEVEL_INCREASE"));
        final int enemyNumber = Integer.parseInt(Constants.getConstantOf("NUM_ENEMIES"));
        final int mapSize = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));
        final int movesLearningInterval = Integer.parseInt(Constants.getConstantOf("MOVES_LEARNING_INTERVAL"));

        this.mapModel = new MapModelImpl(mapSize);
        this.playerInitialization(playerExperienceCap,
                defaultExperience,
                playerLevelIncrease,
                movesLearningInterval);
        this.enemyCollection = new EnemyCollectionImpl();
        this.enemyInitialization(enemyNumber);
    }

    private boolean isCornerOfMap(final Position position) {
        final int x = position.x();
        final int y = position.y();
        return x == 0 && y == 0
                || x == getMapSize() && y == getMapSize()
                || x == 0 && y == getMapSize()
                || y == 0 && x == getMapSize();
    }

    private void enemyInitialization(final int number) {
        Position newEnemyPosition;
        final EnemyFactory factory = new EnemyFactoryImpl();
        for (int i = 0; i < number; i++) {
            final Enemy newEnemy = factory.createRandom();
            do {
                newEnemyPosition = new Position(RANDOM.nextInt(mapModel.getSize()), RANDOM.nextInt(mapModel.getSize()));
            } while (!isCellEmpty(newEnemyPosition)
                    || isCornerOfMap(newEnemyPosition)
                    || !(getCellTypeOf(newEnemyPosition).getAssociatedType().equals(newEnemy.getType())));
            newEnemy.move(newEnemyPosition);
            this.enemyCollection.addEnemy(newEnemy);
        }
        //Boss creation
        final Enemy bossWater = factory.createWaterBoss();
        bossWater.move(this.mapModel.getCornerOfType(CellType.SWAMP));
        this.enemyCollection.addEnemy(bossWater);
        final Enemy bossFire = factory.createFireBoss();
        bossFire.move(this.mapModel.getCornerOfType(CellType.VOLCANO));
        this.enemyCollection.addEnemy(bossFire);
        final Enemy bossBolt = factory.createBoltBoss();
        bossBolt.move(this.mapModel.getCornerOfType(CellType.STORM));
        this.enemyCollection.addEnemy(bossBolt);
        final Enemy bossGrass = factory.createGrassBoss();
        bossGrass.move(this.mapModel.getCornerOfType(CellType.FOREST));
        this.enemyCollection.addEnemy(bossGrass);
    }

    private void playerInitialization(final int playerExperienceCap,
                                      final int defaultExperience,
                                      final int levelIncrease,
                                      final int movesLearningInterval) {
        this.player = new PlayerImpl(playerExperienceCap,
                defaultExperience, levelIncrease, movesLearningInterval);
        this.player.move(new Position(mapModel.getSize() / 2, mapModel.getSize() / 2));
    }

    private boolean isCellEmpty(final Position position) {
        if (!mapModel.isInBoundaries(position)) {
            return false;
        }
        return !player.getPosition().equals(position) && enemyCollection.getEnemyFromPosition(position).isEmpty();
    }

    @Override
    public void movePlayer(final Direction dir) {
        this.player.setLastDirection(dir);
        final Position currentPlayerPosition = this.player.getPosition();
        if (mapModel.isInBoundaries(Positions.sum(currentPlayerPosition, dir.getPosition()))) {
            this.player.move(Positions.sum(currentPlayerPosition, dir.getPosition()));
        }
    }

    /**
     * Checks if the position is occupied by another enemy.
     *
     * @param newPosition The new calculated position.
     * @return True if the position is free, false otherwise.
     */
    private boolean isEnemyInCell(final Position newPosition) {
        return this.enemyCollection.getEnemies().stream().noneMatch(enemy -> enemy.getPosition().equals(newPosition));
    }

    /**
     * Checks if the specified enemy is near the player.
     *
     * @param enemy the enemy to check.
     * @return if the enemy is near the player.
     */
    private boolean isEnemyNearThePlayer(final Enemy enemy) {
        final Position playerPosition = this.player.getPosition();
        final Position enemyPosition = enemy.getPosition();
        final int verticalDistance = Math.abs(playerPosition.y() - enemyPosition.y());
        final int horizontalDistance = Math.abs(playerPosition.x() - enemyPosition.x());
        return verticalDistance <= RANGE_PLAYER_ENEMY && horizontalDistance <= RANGE_PLAYER_ENEMY;
    }

    /**
     * Calculates the distance between two positions.
     *
     * @param pos1 the first position.
     * @param pos2 the second position.
     * @return the distance between the two positions.
     */
    private int calculateDistance(final Position pos1, final Position pos2) {
        final int deltaX = pos1.x() - pos2.x();
        final int deltaY = pos1.y() - pos2.y();
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Calculates the direction in which an enemy should move to chase the player.
     *
     * @param currentEnemyPosition the current position of the enemy.
     * @param playerPosition       the position of the player.
     * @return the direction in which the enemy should move to chase the player.
     */
    private Direction calculateChaseDirection(final Position currentEnemyPosition, final Position playerPosition) {
        final int deltaX = playerPosition.x() - currentEnemyPosition.x();
        final int deltaY = playerPosition.y() - currentEnemyPosition.y();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            return deltaX > 0 ? Direction.EAST : Direction.WEST;
        } else {
            return deltaY > 0 ? Direction.SOUTH : Direction.NORTH;
        }
    }

    @Override
    public void moveEnemies() {
        Direction rndDirection;

        for (final Enemy enemy : this.enemyCollection) {
            if (!(enemy instanceof BossEnemy)) {
                final Position currentEnemyPosition = enemy.getPosition();
                Position newPosition = currentEnemyPosition;
                if (!isEnemyNearThePlayer(enemy)) {
                    do {
                        rndDirection = Direction.values()[RANDOM.nextInt(4)];
                        newPosition = Positions.sum(currentEnemyPosition, rndDirection.getPosition());
                    } while (!this.mapModel.isInBoundaries(newPosition)
                            && isEnemyInCell(newPosition));
                    enemy.setLastDirection(rndDirection);
                } else {
                    /*
                     * If the enemy and the player are close by a certain range of cells,
                     * then the enemy will try to chase the player.
                     */
                    final Position playerPosition = this.player.getPosition();

                    final int distance = calculateDistance(currentEnemyPosition, playerPosition);

                    // This check is used to ensure that the player and the enemy meet when their
                    // distance is equal to 0.
                    if (distance != 0) {
                        final Direction chaseDirection = calculateChaseDirection(currentEnemyPosition, playerPosition);
                        newPosition = Positions.sum(currentEnemyPosition, chaseDirection.getPosition());
                        enemy.setLastDirection(chaseDirection);
                    }
                }
                if (isEnemyInCell(newPosition)
                        && getCellTypeOf(newPosition).getAssociatedType().equals(enemy.getType())) {
                    enemy.move(newPosition);
                }
            }

        }
    }

    @Override
    public Optional<Player> getPlayer() {
        return Optional.of(player);
    }

    @Override
    public double getPlayerAttributeOf(final AttributeIdentifier id) {
        final Attribute selectedAttribute = this.player.getAttributes().get(id);
        if (selectedAttribute.isModifier() && selectedAttribute.isValue()) {
            return ((CombinedAttribute) selectedAttribute).getEvaluated();
        } else if (selectedAttribute.isValue()) {
            return ((AdditiveAttribute) selectedAttribute).value();
        } else {
            return ((MultiplierAttribute) selectedAttribute).modifier();
        }
    }

    @Override
    public int getPlayerLevel() {
        return player.getLevel();
    }

    @Override
    public int getPlayerCurrentExperience() {
        return player.getExperience();
    }

    @Override
    public int getPlayerExperienceCap() {
        return player.getCapExperience();
    }

    @Override
    public PlayerClass getPlayerClass() {
        return player.getPlayerClass();
    }

    @Override
    public MoveSet getPlayerMoveSet() {
        return player.getMoveSet();
    }

    @Override
    public int getMapSize() {
        return mapModel.getSize();
    }

    @Override
    public Position getPlayerPosition() {
        return player.getPosition();
    }

    @Override
    public Optional<GameCharacter> getCharacterFromPosition(final Position pos) {
        if (enemyCollection.getEnemyFromPosition(pos).isPresent()) {
            return Optional.of(enemyCollection.getEnemyFromPosition(pos).get());
        } else if (player.getPosition().equals(pos)) {
            return Optional.of(player);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void removeEnemyFromPosition(final Position position) {
        if (enemyCollection.getEnemyFromPosition(position).isPresent()) {
            this.enemyCollection.removeEnemy(enemyCollection.getEnemyFromPosition(position).get());
        }
    }

    @Override
    public CellType getCellTypeOf(final Position position) {
        return this.mapModel.getCellType(position);
    }

    @Override
    public Set<MagicMove> getMagicMoves() {
        return Moves.getAllMagicMoves();
    }

    @Override
    public Map<String, Integer> getItems() {
        return player.getInventory().getItems()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue));
    }

    private Item getItem(final String itemName) {
        //TODO: Lacks isPresent control.
        return player.getInventory().getItems()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(itemName))
                .findFirst().get().getKey();
    }

    @Override
    public String getItemDescription(final String itemName) {
        return getItem(itemName).getDescription();
    }

    @Override
    public double getItemValor(final String itemName) {
        final Item selectedItem = getItem(itemName);
        if (selectedItem instanceof UsableItem) {
            return ((UsableItem) selectedItem).getAmount();
        }
        return 0;
    }

    @Override
    public ItemType getItemType(final String itemName) {
        return getItem(itemName).getType();
    }

    @Override
    public String getItemBoostedAttributeName(final String itemName) {
        final Item selectedItem = getItem(itemName);
        if (selectedItem instanceof PowerUpItem) {
            return ((PowerUpItem) selectedItem).getBoostedAttribute().getName();
        } else if (selectedItem instanceof HealingItem) {
            return AttributeIdentifier.HEALTH.getName();
        }
        return "";
    }

    @Override
    public int getItemDuration(final String itemName) {
        final Item selectedItem = getItem(itemName);
        if (selectedItem instanceof PowerUpItem) {
            return ((PowerUpItem) selectedItem).getDuration();
        }
        return 0;
    }

    @Override
    public boolean useItem(final String item) {
        final Item selectedItem = getItem(item);
        if (selectedItem instanceof UsableItem convertedItem) {
            player.useItem(convertedItem);
            return true;
        }
        return false;
    }

    @Override
    public void assignPlayerClass(final PlayerClass playerClass) {
        player.setPlayerClass(playerClass);
    }
}
