package unibo.exiled.model.character;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.AttributeImpl;
import unibo.exiled.model.utilities.Position;

import java.util.*;

public abstract class CharacterImpl implements  Character{
    private final String path;
    private final String upImageName;
    private final String downImageName;
    private final String leftImageName;
    private final String rightImageName;

    private final Map<AttributeIdentifier, Attribute> attributes;
    private Position position;

    protected CharacterImpl(final Map<AttributeIdentifier,Attribute> attributes ,final List<String> paths){
        this.path = paths.get(0);
        this.upImageName = paths.get(1);
        this.downImageName = paths.get(2);
        this.leftImageName = paths.get(3);
        this.rightImageName = paths.get(4);
        this.attributes = attributes;
    }

    protected void setAttribute(final AttributeIdentifier id, final Attribute attribute){
        this.attributes.put(id,attribute);
    }

    public void move(final Position position){
        this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }

    @Override
    public Map<AttributeIdentifier, Attribute> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    protected void increaseAttributeModifierBy(final AttributeIdentifier id, final double value){
        this.attributes.replace(id,
                new AttributeImpl(Optional.of(this.attributes.get(id).getModifier().get() + value),
                        Optional.empty()));
    }

    protected void increaseAttributeValue(final AttributeIdentifier id, final double value){
        this.attributes.replace(id,
                new AttributeImpl(Optional.empty(),
                        Optional.of(this.attributes.get(id).getValue().get() + value)));
    }

    public String getImagePath(){return this.path;}
    public String getImageUpPath(){return this.upImageName;}
    public String getImageDownPath(){return this.downImageName;}
    public String getImageLeftPath(){return this.leftImageName;}
    public String getImageRightPath(){return this.rightImageName;}
}
