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
    private final Map<AttributeIdentifier, Attribute> attributes;
    private final String name;
    private Position position;

    public EnemyImpl(final List<String> paths, final String name, final MoveSet moveSet, final Map<AttributeIdentifier,Attribute> attrubutes){
        super(paths);
        this.moveSet = moveSet;
        this.name = name;
        this.attributes = attrubutes;
    }

    @Override
    public MoveSet getMoveSet() {
        return this.moveSet;
    }

    @Override
    public void move(final Position newPosition) {
        this.position = newPosition;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Map<AttributeIdentifier, Attribute> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public abstract double getDroppedExperience();
}
