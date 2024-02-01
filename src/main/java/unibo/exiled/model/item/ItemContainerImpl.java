package unibo.exiled.model.item;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.utilities.ItemType;

public class ItemContainerImpl implements ItemContainer{

    private static final ItemFactory itemFactory = new ItemFactoryImpl();
    
    private static final Set<Item> items = Set.of(
        itemFactory.createHealingItem(ItemNames.HEALTH_POTION.getName(), "Restores health points.", 20.0),
        itemFactory.createHealingItem(ItemNames.HERB.getName(), "A natural remedy for minor injuries.", 15.0),
        itemFactory.createHealingItem(ItemNames.BANDAGE.getName(), "A simple bandage to stop bleeding.", 10.0),
        itemFactory.createPowerUpItem(ItemNames.STRENGTH_BOOST.getName(), "Increases strength for a short duration.", 10.0, 3, AttributeIdentifier.ATTACK),
        itemFactory.createPowerUpItem(ItemNames.DEFENSE_SHIELD.getName(), "Boosts defense against attacks.", 15.0, 3, AttributeIdentifier.DEFENSE),
        itemFactory.createUnUsableItem(ItemNames.CRYSTAL.getName(),"The redemption crystal")
    );

    @Override
    public Optional<Item> getItemByName(String name) {
        return items.stream()
            .filter(item -> item.getName().equals(name))
            .findFirst();
    }

    @Override
    public Set<Item> getAllItems() {
        return Collections.unmodifiableSet(items);
    }

    @Override
    public Optional<Item> getRandomItemByType(ItemType type) {
        Random random = new Random();
        List<Item> itemByType = items.stream()
                .filter(items -> items.getType() == type)
                .toList();
        
        if(itemByType.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(itemByType.get(random.nextInt(itemByType.size())));
    }
    
}
