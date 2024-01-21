package unibo.exiled.model.character.player;

import unibo.exiled.model.character.Attributes;

public class PlayerAttribute implements Attributes {
    public enum AttributeType{
        HEALTH,
        ATTACK,
        DEFENSE
    } 

    private double health;
    
    private double attack;
    private int attackModifier;

    private double defense;
    private int defenseModifier;

    public PlayerAttribute(final double health){
        this.health = health;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public double getAttack() {
        return this.attack;
    }

    @Override
    public double getAttackModifier() {
        return this.attackModifier;
    }

    @Override
    public double getDefense() {
        return this.defense;
    }

    @Override
    public double getDefenseModifier() {
        return this.defenseModifier;
    }
    
    @Override
    public void increase(final AttributeType attr, final double value) {
        switch (attr) {
            case HEALTH:
                attack += value;
                break;
            case ATTACK:
                attack += value;
                break;
            case DEFENSE:
                defense += value;
                break;
            default:
                break;
        }
    }

    @Override
    public void decrease(final AttributeType attr, final double value) {
        switch (attr) {
            case HEALTH:
                health -= value;
                break;
            case ATTACK:
                attack -= value;
                break;
            case DEFENSE:
                defense -= value;
                break;
            default:
                break;
        }
    }

}
