package game;

import java.util.Random;

public class Location {
    private int x;
    private int y;


    public Location() {
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }


    // set new location x,y values
    public Location newLocation(int size){
        this.x = getRandomNumber(size);
        this.y = getRandomNumber(size);
        return this;

    }

   // set new location x,y values  
   // location method overload to ensure new location is different than given location
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

    // generate random interger number in range 0-space
    private int getRandomNumber(int space) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(space+1);
        return randomNumber;
    }

}
