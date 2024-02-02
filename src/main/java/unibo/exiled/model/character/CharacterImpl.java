package unibo.exiled.model.character;

import unibo.exiled.model.character.attributes.*;
import unibo.exiled.model.utilities.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class CharacterImpl implements Character {
    private final String path;
    private final String upImageName;
    private final String downImageName;
    private final String leftImageName;
    private final String rightImageName;

    private final Map<AttributeIdentifier, Attribute> attributes;
    private Position position;

    protected CharacterImpl(final Map<AttributeIdentifier, Attribute> attributes, final List<String> paths) {
        this.path = paths.get(0);
        this.upImageName = paths.get(1);
        this.downImageName = paths.get(2);
        this.leftImageName = paths.get(3);
        this.rightImageName = paths.get(4);
        this.attributes = attributes;
    }

    protected void setAttribute(final AttributeIdentifier id, final Attribute attribute) {
        this.attributes.put(id, attribute);
    }

    public void move(final Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public Map<AttributeIdentifier, Attribute> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    private void increaseAttributes(final AttributeIdentifier id, final double modifier, final double value) {
        final Attribute attributeToModify = this.attributes.get(id);
        if (attributeToModify.isModifier() && attributeToModify.isValue()) {
            final CombinedAttributeImpl conv = (CombinedAttributeImpl) attributeToModify;
            this.attributes.replace(id, new CombinedAttributeImpl(conv.value() + value, conv.modifier() + modifier));
        } else if (attributeToModify.isModifier()) {
            final MultiplierAttributeImpl conv = (MultiplierAttributeImpl) attributeToModify;
            this.attributes.replace(id, new MultiplierAttributeImpl(conv.modifier() + modifier));
        } else {
            final AdditiveAttributeImpl conv = (AdditiveAttributeImpl) attributeToModify;
            this.attributes.replace(id, new AdditiveAttributeImpl(conv.value() + value));
        }
    }

    protected void increaseAttributeModifier(final AttributeIdentifier id, final double modifier) {
        this.increaseAttributes(id, modifier, 0);
    }

    protected void increaseAttributeValue(final AttributeIdentifier id, final double value) {
        this.increaseAttributes(id, 0, value);
    }

    public double getHealth() {
        return ((CombinedAttribute) attributes.get(AttributeIdentifier.HEALTH)).getEvaluated();
    }

    public String getImagePath() {
        return this.path;
    }

    public String getImageUpPath() {
        return this.upImageName;
    }

    public String getImageDownPath() {
        return this.downImageName;
    }

    public String getImageLeftPath() {
        return this.leftImageName;
    }

    public String getImageRightPath() {
        return this.rightImageName;
    }
}
