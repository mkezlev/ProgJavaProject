package game;

public class Player {
    Location location;
    Inventory inventory;

    public Player(Location location) {
        this.location = location;
    }

    public void move(String direction, int steps) {
        int x = location.getX();
        int y = location.getY();
        String dir = direction.toUpperCase();

        if (dir.equals("NORTH")) {
            y -= steps;
        } else if (dir.equals("SOUTH")) {
            y += steps;
        } else if (dir.equals("WEST")) {
            x -= steps;
        } else if (dir.equals("EAST")) {
            x += steps;
        } else {
            System.out.println("Invalid direction!");
            return;
        }

        location.setX(x);
        location.setY(y);
    }

    public void pickItem(Item item) {
        if (inventory == null) {
            inventory = new Inventory();
        }
        inventory.addToInvetory(item);
        System.out.println("Picked item: " + item.getName());
    }

    public void dropItem(Item item) {
        if (inventory != null) {
            inventory.removeFromInvetory(item);
            System.out.println("Dropped item: " + item.getName());
        }
        else  {
            System.out.println("Inventory is empty!");
        }
    }
}
