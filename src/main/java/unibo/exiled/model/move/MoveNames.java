package unibo.exiled.model.move;

public enum MoveNames {

    COLPACCIO("Colpaccio"),
    COLPONE("Colpone"),
    FIREBALL("Fireball"),
    LEAFBLADE("Leaf Blade"),
    LIGHTBULB("Light bulb"),
    WATERPISTOL("Water Pistol");

    private final String name;

    public String getName(){
        return this.name;
    }

    MoveNames(final String name){
        this.name = name;
    }
}
