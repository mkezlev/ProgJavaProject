package game;

import java.util.Random;
/*
Author Murat Kezlev
class Location
holds X,Y values for location
Attribues
x
y

Methods
getX / setX
getY / setY
newLocation
compareTo
getRandomNumber

 */

public class Location implements Comparable{
    private int x;
    private int y;

    public Location() {
    }

    public int compareTo (Object o){
        Location loc = (Location)o;
        if (this.x == loc.x && this.y == loc.y){ return 0;}
        else return -1;
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


    /**
     * Generate a random integer number in range 0-space
     * @param space
     * @return a random integer number in range 0-space
     */
    private int getRandomNumber(int space) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(space+1);
        return randomNumber;
    }

} // end Class Location
