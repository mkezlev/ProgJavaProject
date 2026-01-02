package game;
// class Player


public class Player {

    Location location;
    Inventory inventory;

    public Player() {
        this.location = new Location();
        this.inventory = new Inventory();
    }

    public Player(Location location) {
        if (location != null) {
            this.location = location;
        } else {
            this.location = new Location();
        }
        this.inventory = new Inventory();
    }

    public Location getLocation() { return location; }

    public void setLocation(Location location) { this.location = location; }

    public void setLocation(int x, int y) {
        int gameSpace = GlobSettings.SPACE_SIZE;

        // If the location > gameSpace, take gameSpace; Else if location<0, take 0; Else take X or Y
        this.location.setX(x>gameSpace?gameSpace:(x < 0 ? 0 : x));
        this.location.setY(y>gameSpace?gameSpace:(y < 0 ? 0 : y));
    }

    public Inventory getInventory() { return inventory; }

    public void setInventory(Inventory inventory) { this.inventory = inventory; }

    /**
     * Updates the player's location.
     * Prevents the player from moving outside the game boundaries.
     * @param direction "N", "S", "E", "W"
     * @param steps Number of units to move.
     */
    public void move(String direction, int steps) {
        int x = this.location.getX();
        int y = this.location.getY();
        String dir = direction.toUpperCase();
        int gameSpace = GlobSettings.SPACE_SIZE;

        // Move logic
        if (dir.equals("N")) {
            y+= steps;
        }
        else if (dir.equals("S")) {
            y -= steps;
        }
        else if (dir.equals("W")) {
            x -= steps;
        }
        else if (dir.equals("E")) {
           x += steps;
        }
        else {
            System.out.println("Invalid direction!");
            return;
        }

        // Make sure the new location is within the range of map, namely[0,100]
        if(x<0) x=0;
        if(y<0) y=0;
        if(x>gameSpace) x=gameSpace;
        if(y>gameSpace) y=gameSpace;
        this.setLocation(x, y);
    } // end move

    /**
     * Adds the item to the inventory.
     * @param item
     */
    public void pickItem(Item item) {
        inventory.addToInventory(item);
    }

    /**
     * Removes an item from the inventory after it has been used.
     * @param item The item to be used.
     */
    public void useItem(Item item) {
        if (inventory != null&& item != null) {
            inventory.removeFromInventory(item);
        }
        else  {
            System.out.println("Inventory is empty or item is invalid.");
        }
    }
}
