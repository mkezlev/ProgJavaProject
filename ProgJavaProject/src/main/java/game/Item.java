package game;

// Item Class
// Game items to hold item location and purpose
public class Item {
    GlobSettings globSet = new GlobSettings();
    private String name;
    private Purpose purpose;
    private Location location;

    public Item(String name, Purpose purpose) {
        this.name = name;
        this.purpose = purpose;
        this.location = new Location();
    }

    // getter and setter for purpose attribute
    public Purpose getPurpose() {
        return this.purpose;
    }
    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    // getter and setter for location attribute
    public Location getLocation() {
        return this.location;
    }
    public void placeItem(Location loc) { this.location = loc; }


    // getter for name attribute
    public String getName() {
        return this.name;
    }
}
