package game;

/*
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
    printLocation
    hitObstacle
 */

public class Obstacle {

    Location location;
    int size = GlobSettings.OBS_INITIAL_SIZE;




    public Obstacle () {
        this.location = new Location();
        this.size = size;
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

    /** method increase size
     @Param increment
     change obstacle size by +increment
     */
    public void increaseSize(int increment) {
        size +=increment;
    }

    /* method decrease size
     @Param decrement
     change obstacle size by -decrement
     */

    public void decreaseSize(int decrement) {
        size -=decrement;
    }

    /*
       Place obstacle in game space
       #Return True if obstacle can be place in the game space
       #Retrun False if obstacle cannot be place in the game space
               in case size of obstacle is equal or bigger than gamespace
     */
    public boolean placeObstacle(){
        if (GlobSettings.SPACE_SIZE-size <= 0) return false;
        this.location.newLocation(GlobSettings.SPACE_SIZE-size);
        return true;
    }

    // print obstacle location
    public void printLocation(){
        System.out.println(location.getX() + " " + location.getY());
    }

    /*
    this method check if the give location is in the area covered by the obstacle
    @Param location
    #Return True if given location is in the area of obstacle covering
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
