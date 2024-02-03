package unibo.exiled.model.game;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyFactoryImpl;
import unibo.exiled.model.character.enemy.EnemyCollectionImpl;
import unibo.exiled.model.character.enemy.EnemyFactory;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.item.HealingItem;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemType;
import unibo.exiled.model.item.PowerUpItem;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.Moves;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;
import unibo.exiled.model.character.attributes.AdditiveAttribute;
import unibo.exiled.model.character.attributes.MultiplierAttribute;
import unibo.exiled.model.character.attributes.CombinedAttribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.Attribute;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The implementation of the game core.
 */
public final class GameModelImpl implements GameModel {
    private static final Random RANDOM = new Random();
    private static final int RANGE_PLAYER_ENEMY = 2;
    private final EnemyCollection enemyCollection;
    private Player player;
    private GameMap map;

    /**
     * The constructor of the game core.
     */
    public GameModelImpl() {
        //Constants loading
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        final int moveNumber = Integer.parseInt(Constants.getConstantOf("NUM_PLAYER_MOVES"));
        final double playerExperienceCap = Double.parseDouble(Constants.getConstantOf("PLAYER_EXPERIENCE_CAP"));
        final double defaultExperience = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE"));
        final int playerLevelIncrease = Integer.parseInt(Constants.getConstantOf("PLAYER_LEVEL_INCREASE"));
        final int enemyNumber = Integer.parseInt(Constants.getConstantOf("NUM_ENEMIES"));
        final int mapSize = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));
        final int movesLearningInterval = Integer.parseInt(Constants.getConstantOf("MOVES_LEARNING_INTERVAL"));

        this.mapInitialization(mapSize);
        this.playerInitialization(playerExperienceCap,
                defaultExperience,
                playerLevelIncrease,
                moveNumber,
                movesLearningInterval);
        this.enemyCollection = new EnemyCollectionImpl();
        this.enemyInitialization(enemyNumber);
    }

    private void enemyInitialization(final int number) {
        final Random random = new Random();
        Position newEnemyPosition;
        final EnemyFactory factory = new EnemyFactoryImpl();
        for (int i = 0; i < number; i++) {
            do {
                newEnemyPosition = new Position(random.nextInt(map.getSize()), random.nextInt(map.getSize()));
            } while (!isCellEmpty(newEnemyPosition));
            final Enemy newEnemy = factory.createRandom();
            newEnemy.move(newEnemyPosition);
            this.enemyCollection.addEnemy(newEnemy);
        }
    }

    private void mapInitialization(final int size) {
        this.map = new GameMapImpl(size);
    }

    private void playerInitialization(final double playerExperienceCap,
                                      final double defaultExperience,
                                      final int levelIncrease,
                                      final int moveNumber,
                                      final int movesLearningInterval) {
        this.player = new PlayerImpl(playerExperienceCap,
                defaultExperience, levelIncrease, moveNumber, movesLearningInterval);
        this.player.move(new Position(map.getSize() / 2, map.getSize() / 2));
    }

    private boolean isCellEmpty(final Position position) {
        if (!map.isInBoundaries(position)) {
            return false;
        }
        return !player.getPosition().equals(position) && !enemyCollection.getEnemyFromPosition(position).isPresent();
    }

    @Override
    public void movePlayer(final Direction dir) {
        final Position currentPlayerPosition = this.player.getPosition();
        if (map.isInBoundaries(Positions.sum(currentPlayerPosition, dir.getPosition()))) {
            this.player.move(Positions.sum(currentPlayerPosition, dir.getPosition()));
        }
    }

    // Check if in the position there is already another enemy
    private boolean checkOtherEnemies(final Position newPosition) {
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
            final Position currentEnemyPosition = enemy.getPosition();
            Position newPosition = currentEnemyPosition;
            if (!isEnemyNearThePlayer(enemy)) {
                do {
                    rndDirection = Direction.values()[RANDOM.nextInt(4)];
                } while (!this.map.isInBoundaries(Positions.sum(currentEnemyPosition, rndDirection.getPosition()))
                        && checkOtherEnemies(Positions.sum(currentEnemyPosition, rndDirection.getPosition())));
                newPosition = Positions.sum(currentEnemyPosition, rndDirection.getPosition());
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
                }
            }
            enemy.move(newPosition);
        }
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
    public double getPlayerLevel() {
        return player.getLevel();
    }

    @Override
    public ElementalType getPlayerClass() {
        return player.getPlayerClass();
    }

    @Override
    public int getMapSize() {
        return map.getSize();
    }

    @Override
    public Position getPlayerPosition() {
        return player.getPosition();
    }

    @Override
    public Optional<GameCharacter> getCharacterFromPosition(final Position pos) {
        if (player.getPosition().equals(pos)) {
            return Optional.of(player);
        } else if (enemyCollection.getEnemyFromPosition(pos).isPresent()) {
            return Optional.of(enemyCollection.getEnemyFromPosition(pos).get());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public CellType getCellTypeOf(final Position position) {
        return this.map.getCellType(position);
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

    private Item getItem(String itemName){
        return player.getInventory().getItems()
            .entrySet()
            .stream()
            .filter(entry -> entry.getKey().getName().equals(itemName))
            .findFirst().get().getKey();
    }

    @Override
    public String getItemDescription(String itemName) {
        return getItem(itemName).getDescription();
    }

    @Override
    public double getItemValor(String itemName) {
        Item selectedItem = getItem(itemName);
        if(selectedItem instanceof UsableItem){
            return ((UsableItem)selectedItem).getAmount();
        }
        return 0;
    }

    @Override
    public ItemType getItemType(String itemName) {
        return getItem(itemName).getType();
    }

    @Override
    public String getItemBoostedAttributeName(String itemName) {
        Item selectedItem = getItem(itemName);

        if(selectedItem instanceof PowerUpItem){
            return ((PowerUpItem)selectedItem).getBoostedAttribute().getName();
        }
        else if(selectedItem instanceof HealingItem){
            return AttributeIdentifier.HEALTH.getName();
        }
        return "";
    }

    @Override
    public int getItemDuration(String itemName) {
        Item selectedItem = getItem(itemName);
        if(selectedItem instanceof PowerUpItem){
            return ((PowerUpItem)selectedItem).getDuration();
        }
        return 0;
    }

    @Override
    public boolean useItem(String item) {
        Item selectedItem = getItem(item);
        if(selectedItem instanceof UsableItem){
            UsableItem convertedItem = (UsableItem)selectedItem;
            player.useItem(convertedItem);
            return true;
        }
        return false;
    }
}
