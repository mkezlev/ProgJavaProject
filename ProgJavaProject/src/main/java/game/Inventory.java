package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * @param item The item to be added to the inventory.
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
     * @param item The item to be removed from the bag.
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
     * Extract all items of all purposes in the inventory and gather them into one single Arraylist
     * @return A list containing all items.
     */
    public List<Item> getAllItemsAsList() {
        List<Item> allItems = new ArrayList<>();

        for (Purpose purpose : Purpose.values()) {
            allItems.addAll(bag.get(purpose));
        }
        return allItems;
    }
}
