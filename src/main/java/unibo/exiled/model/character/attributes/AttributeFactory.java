package unibo.exiled.model.character.attributes;

import java.util.Map;

public interface AttributeFactory {
    Map<AttributeIdentifier,Attribute> createPlayerAttributes();
    Map<AttributeIdentifier,Attribute> createGoblinAttributes();
    Map<AttributeIdentifier,Attribute> createBrutusAttributes();
}
