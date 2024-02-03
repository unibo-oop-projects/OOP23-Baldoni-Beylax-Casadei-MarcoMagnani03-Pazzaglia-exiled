package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.GameCharacterImpl;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.move.MoveSet;
import java.util.Map;

public abstract class EnemyImpl extends GameCharacterImpl implements Enemy {
    private final MoveSet moveSet;
    //private final double level; TODO: Aggiungere livello per migliorare la logica del drop di esperienza

    public EnemyImpl(final String name,
                     final MoveSet moveSet,
                     final Map<AttributeIdentifier,Attribute> attributes
                     /*final double level*/){
        super(name, attributes);
        this.moveSet = moveSet;
        //this.level = level; TODO
    }

    @Override
    public MoveSet getMoveSet() {
        return this.moveSet;
    }

    @Override
    public abstract double getDroppedExperience();
}
