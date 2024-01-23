package unibo.exiled.model.character.attributes;

import java.util.Map;

public interface AttributeFactory {
    Map<AttributeIdentifier,Attribute> createBasicAttributes();
    Map<AttributeIdentifier,Attribute> createGoblinAttributes();
}
