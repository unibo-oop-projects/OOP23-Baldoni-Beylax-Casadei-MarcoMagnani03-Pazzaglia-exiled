package unibo.exiled.model.character.attributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AttributesImpl implements Attributes{

    private final Map<AttributeType,Stat> attributes;

    public AttributesImpl(){
        this.attributes = new HashMap<>();
    }

    @Override
    public Map<AttributeType, Stat> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override
    public Stat getAttributeOfType(AttributeType type) {
        return this.attributes.get(type);
    }

    @Override
    public void addAttribute(AttributeType type, Stat stat) {
        this.attributes.putIfAbsent(type,stat);
    }

    @Override
    public void removeAttribute(AttributeType type) {
        this.attributes.remove(type);
    }
}
