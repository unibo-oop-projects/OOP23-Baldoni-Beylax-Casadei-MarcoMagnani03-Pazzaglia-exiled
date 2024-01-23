package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.attributes.AttributeFactory;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.move.*;


public class EnemyFactoryImpl implements EnemyFactory {
    private final MoveSetFactory moveSetFactory;
    private final AttributeFactory attributeFactory;
    public EnemyFactoryImpl(){
        this.moveSetFactory = new MoveSetFactoryImpl();
        this.attributeFactory = new AttributeFactoryImpl();
    }
    @Override
    public Enemy createGoblin() {
        return new EnemyImpl("Gobleno", moveSetFactory.defaultBasicMoveSet(),attributeFactory.createGoblinAttributes()) {
            @Override
            public double getDroppedExperience() {
                return 10;
            }
        };
    }
}