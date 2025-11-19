public class Item {

    private String name;
    private Purpose purpose;
    private Location location;

    public Item(String name, Purpose purpose) {
        this.name = name;
        this.purpose = purpose;
        this.location = new Location();
    }

    public Purpose getPurpose() {
        return this.purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
