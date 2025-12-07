package game;

public class Player {
    GlobSettings globSet = new GlobSettings();
    Location location;
    Inventory inventory;

    public Player(Location location) {
        this.location = location;
    }

    public void move(String direction, int steps) {
        int x = location.getX();
        int y = location.getY();
        String dir = direction.toUpperCase();

        int gameSpace = globSet.SPACE_SIZE;

        /// Move based on the direction
        if (dir.equals("N")) {
            y -= steps;
        } else if (dir.equals("S")) {
            y += steps;
        } else if (dir.equals("W")) {
            x -= steps;
        } else if (dir.equals("E")) {
            x += steps;
        } else {
            System.out.println("Invalid direction!");
            return;
        }

        /// Make sure the new location is within the range of map, namely[0,99]
        x = Math.max(0, x);
        x = Math.min(gameSpace-1, x);

        y = Math.max(0, y);
        y = Math.min(gameSpace-1, y);

        location.setX(x);
        location.setY(y);
        System.out.println("Current location: "+location);
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
