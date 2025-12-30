package game;

import java.util.Random;
// class Location
// holds X,Y values for location
public class Location {
    private int x;
    private int y;

    public Location() {
    }


    // getter and setter for x attribute
    public int getX(){ return this.x; }
    public void setX(int x){this.x = x; }

    // getter and setter for y attribute
    public void setY(int y){ this.y = y;    }
    public int getY(){ return this.y; }


    /*
       set new location x,y values
       @Param Size
     */

    public Location newLocation(int size){
        this.x = getRandomNumber(size);
        this.y = getRandomNumber(size);
        return this;
    }


    /*
       set new location x,y values
       location method overload to ensure new location is different than given location
       @Param size
       @Param loc
     */
    public Location newLocation(int size, Location loc){
        this.x = getRandomNumber(size);
        int locx=loc.getX();
        int locy=loc.getY();
        while (this.x == locx){
            this.x = getRandomNumber(size);
        }
        this.y = getRandomNumber(size);
        while (this.y == locy){
            this.y = getRandomNumber(size);
        }
        return this;
    }

    // generate random integer number in range 0-space
    private int getRandomNumber(int space) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(space+1);
        return randomNumber;
    }

} // end Class Location
