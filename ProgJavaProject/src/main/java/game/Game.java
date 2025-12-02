package game;

public class Game {
    GlobSettings globSet = new GlobSettings();
    Player player;
    Item[] items;
    Obstacle obstacle;
    int gameSpace = globSet.SPACE_SIZE; // size of area 10x10, 100x10

    Item item = new Item("Test1", Purpose.AVOID);



    public Game() {

    }

    public void setPlayerLocation(){
        player.location.newLocation(gameSpace);
    }

    // if oostacle can be placed in the game space return TRUE
    // if not return FALSE
    public boolean setObstacleLocation(){
        return obstacle.placeObstacle(player.location);
    }

    // pick an item from the items randomly
    public Item pickItemToCollect() {
        return null;
    }

    public void setItemLocation(Item item){
        Location loc = new Location();
        loc.newLocation(gameSpace);
        Comparable cLoc = (Comparable)loc;
        Comparable playerLoc = (Comparable)player.location;

        // place item in a coordinate different than player
        // and outside obstacle
        while(cLoc.compareTo(playerLoc) == 0 || obstacle.hitObstacle(loc)){
            loc.newLocation(gameSpace);
            cLoc = (Comparable)loc;
        }
        item.placeItem(loc);
    } // end setItemLocation

    // display player the task
    public void displayTask(){

    } // end displayTask

    public boolean getDirection(){
        return false;
    } // end getDirection

    public boolean setTask() {
        // set player random location
        setPlayerLocation();

        // set obstacle location
        // while obstacle can be placed in the game space continue
        if (setObstacleLocation()) {
            Item itemToCollect;
            // pick random item to pick by player
            item = pickItemToCollect();
            // set item location
            setItemLocation(item);
            return true;
        } else return false;

    } // end set task

    public void playGame() {

        while (setTask()) {
            // display to player
            //      - player location,
            //      - obstacle location
            //      - item and item location
            displayTask();

            // get player command until player hits obstacle
            // or reach item
            while (getDirection()) {

            }

            // ask player action on item
            // user power or collect
        }

    } // end play game

} // end class Game


