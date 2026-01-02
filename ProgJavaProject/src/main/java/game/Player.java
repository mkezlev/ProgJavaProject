package game;
/*
Author Kangqi Tuo
 class Player

 Attributes
 location
 inventory

 Methods
 getLocation
 setLocation
 getInventory
 move
 setLocationAfterMove
 */


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

    public Inventory getInventory() { return inventory; }


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
        this.setLocationAfterMove(x, y);
    } // end move

    /**
     * Adds the item to the inventory.
     * @param item
     */
    public void pickItem(Item item) {
        inventory.addToInventory(item);
    }


    /*
 set location of player using x,y coordinates and check new location after move
 is always within the range of the game space
  */
    private void setLocationAfterMove(int x, int y) {
        int gameSpace = GlobSettings.SPACE_SIZE;
        // If the location > gameSpace, take gameSpace; Else if location<0, take 0; Else take X or Y
        this.location.setX(x>gameSpace?gameSpace:(x < 0 ? 0 : x));
        this.location.setY(y>gameSpace?gameSpace:(y < 0 ? 0 : y));
    }
}
