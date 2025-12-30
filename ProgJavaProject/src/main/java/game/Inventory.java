package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class Inventory
 * Holds the items player posses
 */
public class Inventory {

    private HashMap<Purpose, List<Item>> bag;

    // Constructor
    public Inventory() {
        bag = new HashMap<>();
        for (Purpose purpose : Purpose.values()) {
            bag.put(purpose, new ArrayList<Item>());
        }
    }

    /**
     * Adds an item to the inventory and categorizes it based on its purpose.
     * @param item The {@link Item} to be added to the player's inventory.
     */
    public void addToInventory(Item item) {
        if (item == null) {
            return;
        }
        bag.get(item.getPurpose()).add(item);
        System.out.println("Added to inventory: " + item.getName());
    }

    /**
     * Removes an item from the inventory if it exists within its category.
     * @param item The {@link Item} to be removed from the bag.
     */
    public void removeFromInventory(Item item) {
        if (item == null) {
            return;
        }
        List<Item> items = bag.get(item.getPurpose());
        if (items != null && items.remove(item)) {
            System.out.println("Used: " + item.getName());
        } else {
            System.out.println("No " + item.getName() + " item left!");
        }
    }

    /**
     * Displays a list of all items currently in the inventory
     */
    public void listInventory() {
        System.out.println("Inventory:");
        for (Purpose purpose : bag.keySet()) {
            List<Item> items = bag.get(purpose);
            if (!items.isEmpty()) {
                System.out.println(purpose + ": " + items.size() + " items"); // list quantity of the item
                for (Item item : items) {
                    System.out.println(" - " + item.getName()); //list name of the item
                }
            }
        }
    }
}
