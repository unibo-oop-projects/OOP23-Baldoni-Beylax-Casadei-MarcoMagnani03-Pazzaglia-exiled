package unibo.exiled.controller;

import unibo.exiled.controller.menu.InGameMenuController;
import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.model.GameModel;
import unibo.exiled.model.GameModelImpl;
import unibo.exiled.model.character.Character;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameControllerImpl implements GameController {
    private final GameModel model;
    private final PlayerController pc;
    private final InventoryController ic;
    private final MenuController inGameMenuController;
    private final MapController mpc;
    private final List<EnemyController> ec;

    public GameControllerImpl() {
        this.model = new GameModelImpl();
        this.pc = new PlayerController(model.getPlayer());
        this.ic = new InventoryController(pc.player().getInventory());
        this.inGameMenuController = new InGameMenuController();
        this.mpc = new MapController(model.getMap());
        this.ec = new ArrayList<>();

        for (int i = 0; i < model.getEnemies().size(); i++) {
            var enemy = model.getEnemies().values().stream().toList().get(i);
            ec.add(new EnemyController(enemy));
        }
    }

    @Override
    public void movePlayer(final Direction dir){
        final Position oldPosition = this.getPlayerController().player().getPosition();
        final Position newPosition = new Position(
                    oldPosition.x() + dir.getPosition().x(),
                    oldPosition.y() + dir.getPosition().y()
                );
        if(this.getMapController().isInBoundaries(newPosition)){
            this.getPlayerController().move(newPosition);
        }
    }

    @Override
    public void moveEnemies() {
        Random rnd = new Random();
        int indexDirection;
        List<Direction> directions = List.of(Direction.values());
        Map<Position, Enemy> enemies = this.model.getEnemies();
        Position oldPosition, newPosition;

        for (int i = 0; i < enemies.size(); i++) {
            var enemy = enemies.entrySet().stream().toList().get(i);
            indexDirection = rnd.nextInt(4);
            oldPosition = enemy.getKey();
            newPosition = new Position(
                oldPosition.x() + directions.get(indexDirection).getPosition().x(),
                oldPosition.y() + directions.get(indexDirection).getPosition().y()
            );
            if(this.getMapController().isInBoundaries(newPosition)){
                this.getEnemyController().get(i).move(newPosition);
            }
        }
    }

    @Override
    public List<String> getImagePathOfCharacter(final Character character){
        return List.of(
                character.getImagePath(),
                character.getImageUpPath(),
                character.getImageDownPath(),
                character.getImageLeftPath(),
                character.getImageRightPath()
        );
    }

    @Override
    public boolean isEnemyInCell(final Position pos){ return this.model.getEnemies().containsKey(pos); }

    @Override
    public boolean isOver(){ return isEnemyInCell(this.getPlayerController().getPlayerPosition()); }

    @Override
    public Character getCharacterInPosition(Position pos) { return this.model.getEnemies().get(pos); }

    @Override
    public InventoryController getInventoryController() { return this.ic; }
        
    @Override
    public PlayerController getPlayerController() { return this.pc; }

    @Override
    public MenuController getInGameMenuController() { return this.inGameMenuController; }

    @Override
    public MapController getMapController(){ return this.mpc; }

    @Override
    public List<EnemyController> getEnemyController() { return this.ec; }

}
