package unibo.exiled.model.item;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import unibo.exiled.model.character.attributes.AttributeIdentifier;

/**
 * A container of every existent item in the game.
 */
public final class ItemContainer {

        private static final ItemFactory ITEM_FACTORY = new ItemFactoryImpl();
        private static final Random RANDOM = new Random();
        private static final Set<Item> ITEMS = Set.of(
                        ITEM_FACTORY.createHealingItem(ItemNames.HEALTH_POTION.getName(),
                                        "Restores health points.", 20.0),
                        ITEM_FACTORY.createHealingItem(ItemNames.HERB.getName(),
                                        "A natural remedy for minor injuries.", 15.0),
                        ITEM_FACTORY.createHealingItem(ItemNames.BANDAGE.getName(),
                                        "A simple bandage to stop bleeding.", 10.0),
                        ITEM_FACTORY.createPowerUpItem(ItemNames.STRENGTH_BOOST.getName(),
                                        "Increases strength for a short duration.",
                                        10.0, 3, AttributeIdentifier.ATTACK),
                        ITEM_FACTORY.createPowerUpItem(ItemNames.DEFENSE_SHIELD.getName(),
                                        "Boosts defense against attacks.",
                                        15.0, 3, AttributeIdentifier.DEFENSE),
                        ITEM_FACTORY.createUnUsableItem(ItemNames.CRYSTAL.getName(),
                                        "The redemption crystal"));

        private ItemContainer() {
        }

        /**
         * Retrieves an item by its name from the container.
         *
         * @param name The name of the item to retrieve.
         * @return An Optional containing the item if found, or an empty Optional if not
         *         found.
         */
        public static Optional<Item> getItemByName(final String name) {
                return ITEMS.stream()
                                .filter(item -> item.getName().equals(name))
                                .findFirst();
        }

        /**
         * Retrieves a list of all items in the container.
         *
         * @return A set containing all items in the container.
         */
        public static Set<Item> getAllItems() {
                return Collections.unmodifiableSet(ITEMS);
        }

        /**
         * Retrieves a random item of a specified type from the container.
         *
         * @param type The type of the item to retrieve.
         * @return An Optional containing a random item of the specified type if found,
         *         or an empty Optional if no such item is found.
         */
        public static Optional<Item> getRandomItemByType(final ItemType type) {
                final List<Item> itemByType = ITEMS.stream()
                                .filter(items -> items.getType().equals(type))
                                .toList();

                if (itemByType.isEmpty()) {
                        return Optional.empty();
                }

                return Optional.of(itemByType.get(RANDOM.nextInt(itemByType.size())));
        }

        /**
         * Retrieves a random item from the container.
         *
         * @return An Optional containing a random item of the specified type if found,
         *         or randomly also an empty Optional.
         */
        public static Optional<Item> getRandomItem() {
                final List<Item> itemByType = ITEMS.stream()
                                .filter(items -> items.getType().equals(ItemType.HEALTH)
                                                || items.getType().equals(ItemType.POWERUP))
                                .toList();

                if (RANDOM.nextBoolean()) {
                        return Optional.of(itemByType.get(RANDOM.nextInt(ITEMS.size() - 1)));
                } else {
                        return Optional.empty();
                }
        }

}
