public class Obstacle {
    Location location;
    int size=5;


    public Obstacle (Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public void increaseSize(int increment) {
        size +=increment;
    }

    public void decreaseSize(int decrement) {
        size -=decrement;
    }

    /*
    this method check if the give location is in the area cover by the obstacle
     */
    public boolean hitObstacle(Location location) {
        return false;
    }

}
