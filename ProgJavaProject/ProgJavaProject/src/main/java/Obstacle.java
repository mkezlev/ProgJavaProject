/*
Class Obstacle
Class attributes
  Location
  Size

Class Methods
    Constructor @Param location @Param size
 */

public class Obstacle {
    Location location;
    int size;


    /*
    Constructor
    @Param location : where the initial location is
    @Param size     : initial Size.
     */

    public Obstacle (Location location,int size) {
        this.location = location;
        this.size = size;
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
    @Param location
    #Return True if given location is in teh area of obstacle covering
     */
    public boolean hitObstacle(Location location) {

        // check if locX is in range of obstacle x range
        if  ( this.location.getX() <= location.getX() && location.getX() <= this.location.getX() + this.size )
          return true;
        if (this.location.getY() <= location.getY() && location.getY() <= this.location.getY() + this.size )
            return true;
        return false;
    }

}
