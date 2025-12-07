package game;

public class Player {
    GlobSettings globSet = new GlobSettings();
    Location location;
    Inventory inventory;

    public Player() {  }
    public Player(Location location) {
        this.location = location;
    }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);}

    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }

    public void move(String direction, int steps) {
        int x = this.location.getX();
        int y = this.location.getY();
        String dir = direction.toUpperCase();

        int gameSpace = globSet.SPACE_SIZE;

        /// Move based on the direction
        if (dir.equals("N")) {
            //y -= steps;
            y+= steps;
        } else if (dir.equals("S")) {
            //y += steps;
            y -= steps;
        } else if (dir.equals("W")) {
            x -= steps;
        } else if (dir.equals("E")) {
           x += steps;
        } else {
            System.out.println("Invalid direction!");
            return;
        }

        // Make sure the new location is within the range of map, namely[0,99]

        y = (y>gameSpace ? gameSpace : y);
        y = (y<0 ? 0 : y);
        x = (x>gameSpace ? gameSpace : x);
        x = (x<0 ? 0 : x);
       /* or this code
        x = Math.max(0, x);
        x = Math.min(gameSpace, x);
        y = Math.max(0, y);
        y = Math.min(gameSpace, y);
        */
        this.location.setX(x);
        this.location.setY(y);
    } // end move

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
