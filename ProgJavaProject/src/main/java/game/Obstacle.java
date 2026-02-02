package game;

/*
Author Murat Kezlev
Class Obstacle
Class attributes
  Location
  Size

Class Methods
    Constructor @Param location @Param size
    getLocation
    setLocation
    getSize
    setSize
    increaseSize
    decreaseSize
    placeObstacle
    hitObstacle
 */

public class Obstacle {

    private Location location;
    private int size;

    public Obstacle () {
        this.location = new Location();
        this.size = 0;
    }

    // getter and setter for location attribute
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    // getter and setter for size attribute
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * change obstacle size by +increment
     * @param increment size to be increased
     */
    public void increaseSize(int increment) {
        size +=increment;
    }

    /**
     * change obstacle size by -decrement
     * @param decrement size to be decreased
     */
    public void decreaseSize(int decrement) {
        size -=decrement;
    }

    /**
     * Place obstacle in game space
     * @return True if obstacle can be place in the game space
        False if obstacle cannot be place in the game space in
        case size of obstacle is equal or bigger than gamespace
     */
    public boolean placeObstacle(){
        if (GlobSettings.SPACE_SIZE-size <= 0) return false;
        this.location.newLocation(GlobSettings.SPACE_SIZE-size);
        return true;
    }

    /**
     * check if the given location is in the area covered by the obstacle
     * @param location
     * @return True if given location is in the area of obstacle covering
     */
    public boolean hitObstacle(Location location) {

        // check if locX is in range of obstacle x range
        if  ( this.location.getX() <= location.getX() && location.getX() <= this.location.getX() + this.size )
          return true;
        // check if locY is in range of obstacle x range
        if (this.location.getY() <= location.getY() && location.getY() <= this.location.getY() + this.size )
            return true;
        return false;
    } // end method hitObstacle


} // end Class Obstacle
