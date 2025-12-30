package game;

import java.util.Random;

public class Game {
    GlobSettings globSet = new GlobSettings();
    Player player;
    Item[] items;
    Obstacle obstacle;
    int gameSpace = globSet.SPACE_SIZE; // size of area 10x10, 100x10

    public Game() {
        this.player = new Player(new Location());
        this.obstacle = new Obstacle();

        // Concrete items to be collected and used
        this.items = new Item[] {
                new Item("Ghost Blanket", Purpose.AVOID),
                new Item("Hammer", Purpose.DECREASE),
                new Item("Rocket Boots", Purpose.AVOID),
                new Item("Ancient Coin", Purpose.NO_AFFECT),
                new Item("Shrink Ray", Purpose.DECREASE)
        };
    }

    public void setPlayerLocation(){
        player.location.newLocation(gameSpace);
    }

    // if obstacle can be placed in the game space return TRUE
    // if not return FALSE
    public boolean setObstacleLocation(){
        return obstacle.placeObstacle(player.location);
    }


    /*
    ********************!!!!!!!! pick an item from the itemList randomly
     */

    /**
     * Randomly picks one item from the item pool as the current game objective.
     * @return A randomly selected {@link Item}, or {@code null} if the pool is empty.
     */
    public Item getItemForUserToCollect() {
        Random random = new Random();
        if (items == null || items.length == 0) {
            return null;
        }

        // random.nextInt(5) will return one number in [0,4]
        int index = random.nextInt(items.length);
        Item itemPicked = items[index];

        return itemPicked;
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


    /*
    ******************!!!!! display player the task

     */

    public void displayTask(){
       // develop the code
    } // end displayTask

    /*
    ******************!!!!!
    */
    public boolean getDirection(){
        // develop the code
        return false;
    } // end getDirection

    public boolean setTask() {
        // set player random location
        setPlayerLocation();

        // set obstacle location
        // while obstacle can be placed in the game space
        if (setObstacleLocation()) {
            Item itemToCollect;
            // pick random item to pick by player
            itemToCollect = getItemForUserToCollect();
            // set item location
            setItemLocation(itemToCollect);
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

            // if bag is not empty ask user if user wants to use power

            // get player command until player hits obstacle
            // or reach item
            while (getDirection()) {
                // get the direction
                // check the final place
                // if it is in obstacle end game
                // if same place as item leave loop
            }


            // ask player action on item
            // user power or collect
        }

    } // end play game

} // end class Game


