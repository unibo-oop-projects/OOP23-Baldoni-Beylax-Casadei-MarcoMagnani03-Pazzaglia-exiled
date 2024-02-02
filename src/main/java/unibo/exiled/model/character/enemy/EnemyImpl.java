package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.CharacterImpl;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class EnemyImpl extends CharacterImpl implements Enemy {
    private final MoveSet moveSet;
    private final String name;
    //private final double level; TODO: Aggiungere livello per migliorare la logica del drop di esperienza

    public EnemyImpl(final String name,
                     final MoveSet moveSet,
                     final Map<AttributeIdentifier,Attribute> attributes
                     /*final double level*/){
        super(name, attributes);
        this.moveSet = moveSet;
        this.name = name;
        //this.level = level; TODO
    }

    @Override
    public MoveSet getMoveSet() {
        return this.moveSet;
    }

    @Override
    public abstract double getDroppedExperience();
}
