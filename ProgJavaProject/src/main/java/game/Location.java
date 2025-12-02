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

    public Location newLocation(int size){
        this.x = getRandomNumber(size);
        this.y = getRandomNumber(size);
        return this;

    }

    private int getRandomNumber(int space) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(space);
        return randomNumber;
    }

}
