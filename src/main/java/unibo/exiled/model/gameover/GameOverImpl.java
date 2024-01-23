package unibo.exiled.model.gameover;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;

public class GameOverImpl implements GameOver {

    private Player player;

    public GameOverImpl(Player player){
        this.player = player;
    }

    @Override
    public void restart() {
        player = new PlayerImpl();
    }

    @Override
    public void closeGame() {

    }
    
}
