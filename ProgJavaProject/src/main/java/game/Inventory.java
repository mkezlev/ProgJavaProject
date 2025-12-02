package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    
    private HashMap <Purpose, List<Item>> bag;


    public Inventory() {
        bag = new HashMap<>();
        for (Purpose purpose : Purpose.values()) {
            bag.put(purpose, new ArrayList<Item>());
        }
    }

    public void addToInvetory(Item item) {
        // increase purpose count
        bag.get(item.getPurpose()).add(item);
    }

    public void removeFromInvetory(Item item) {
        List<Item> items = bag.get(item.getPurpose());
        if (items != null && !items.isEmpty()) {
            Item removed = items.remove(0);
            System.out.println("Used: " + removed.getName());
        } else {
            System.out.println("No " + item + " item left!");
        }
    }

    public void listInvetory() {
        System.out.println("Inventory:");
        for (Purpose purpose : bag.keySet()) {
            List<Item> items = bag.get(purpose);
            System.out.println(purpose + ": " + items.size() + " items"); // list quantity of the item
            for (Item item : items) {
                System.out.println(" - " + item.getName()); //list name of the item
            }
        }
    }
}
