package unibo.exiled.controller;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

public record PlayerController(Player player) implements CharacterController {

    public Position getPlayerPosition() {
        return this.player.getPosition();
    }

    @Override
    public void move(final Position position) {
        this.player.move(position);
    }

    public void setPlayerClass(final ElementalType playerClass){
        this.player.setPlayerClass(playerClass);
    }

    public ElementalType getPlayerClass(){
        return this.player.getPlayerClass();
    }

    public Player getPlayer(){
        return this.player;
    }
}
