package game;

import java.util.Random;

public class Game {

    Player player;
    Item[] items;
    Obstacle obstacle;
    int gameSpace = GlobSettings.SPACE_SIZE; // size of area 10x10, 100x10


    public Game() {
        this.player = new Player(new Location());
        this.obstacle = new Obstacle();

        // Construct items to be collect and use
        this.items = new Item[] {
                new Item("Ghost Blanket", Purpose.HIDE),
                new Item("Hammer", Purpose.DECREASE),
                new Item("Rocket Boots", Purpose.HIDE),
                new Item("Ancient Coin", Purpose.NO_AFFECT),
                new Item("Shrink Ray", Purpose.DECREASE)
        };
    }

    public void displatGameObjective(){
        System.out.println("Game Objective is to navigate to the coordinates the item is placed and collect the item");
        System.out.println("There is a square obstacle on the game board which user must not hit");
        System.out.println("The square obstacle size increase after each successful task");
        System.out.println("The game ends when the player hits the square obstacle or the obstacle covers the whole game board");
        System.out.println("User can use power of the collect item to hide the obstacle or decrease sise of obstacle");
        System.out.println("The user will collect one of the items:");
        for (int i = 0; i <= items.length; i++) {
            System.out.printf("Item name %s with power %s",items[i].getName(),items[i].getPurpose().toString());
        }

    }

    /*
    place obstacle in game space
    if obstacle can be placed in the game space return TRUE
    if not return FALSE
     */
    public boolean setObstacleLocation(){
        return obstacle.placeObstacle();
    }

    /*
     *   place player in the game space
     *  in a coordinate outside obstacle
     */
    public void setPlayerLocation(){
        Location loc = new Location();
        loc.newLocation(gameSpace);
        // place player outside the area covered by obstacle
        while (obstacle.hitObstacle(loc)) loc.newLocation(gameSpace);
        player.setLocation(loc);
    }


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

    /*
    * Place selected item in the game space
    * in a coordinate different than player and outside obstacle
     */
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
     * display player the task
     *  item to collect and X-Y coordinates of the item
     *  Player X-Y location
     *  Area player must  avoid for not hitting the obstacle
     */
    public void displayTask(Item item){
        int obsX = obstacle.getLocation().getX();
        int obsY = obstacle.getLocation().getY();
        int obsSize = obstacle.getSize();

        System.out.printf("Your new task is to collect %s at location x=%d , y=%d\n", item.getName(), item.getLocation().getX() , item.getLocation().getY());
        System.out.printf("You are at location at location x=%d , y=%d\n", player.getLocation().getX(), player.getLocation().getY());
        System.out.printf("There is a whole from x: %d to %d  and y: %d to %d\n", obsX,(obsX+obsSize) , obsY, (obsY+obsSize));
        System.out.println("Do not hit the obstacle, you can use the power of the items you collect");

    } // end displayTask

    /*
    * get direction to move
    * return direction
    */
    public String getDirection(){
        UserInput input = new UserInput();
        String direction ="";
        boolean correctDirection = false;
        while(!correctDirection) {
            direction = input.lengthConstrainedInputString("Direction to move (N)orth,(S)outh,(W)est,(E)ast : Enter capital first letter",
                    "Enter one charter only", 1);
            String dir = direction.toUpperCase();
            if (dir.equals("N") || dir.equals("S") || dir.equals("W") || dir.equals("E")) {
                correctDirection = true;
            } else {
                System.out.println("Invalid direction!");
            }
        }
        return direction;
    } // end getDirection

    /*
     * get steps to move
     * return steps
     */
    private int getSteps(){
        UserInput input = new UserInput();
        int steps = input.constrainedInputInteger("Enter number of steps between 0 and "+GlobSettings.SPACE_SIZE,
                "Enter steps in the range 0-"+GlobSettings.SPACE_SIZE,0,GlobSettings.SPACE_SIZE);
        return steps;
    }

    /*
    * ask user which item player wants to use from the player inventory
    * remove the item from user inventory
    * return the item
    */
    private Item getItemFromPlayerInventory(){
        Item item;
        player.getInventory().listInventory();
        // develop the code to select the item
        item = null;
        player.useItem(item);
        return item;
    }


    /*
    * set task
    * if task can be set return item to collect
    * if task cannot be set return null
     */
    public Item setTask() {
        // set obstacle location if obstacle can be placed in the game space
        if (setObstacleLocation()) {
            Item itemToCollect;
            // pick random item to pick by player
            itemToCollect = getItemForUserToCollect();
            // set player location
            setPlayerLocation();
            // set item location
            setItemLocation(itemToCollect);
            return itemToCollect;
        } else return null;

    } // end set task

    public void playGame() {
        boolean canWalkOverObstacle = false;
        boolean onItemLocation = false;
        boolean fallInObstacle  = false;
        String direction="";
        int steps = 0;
        Comparable itemLoc;
        Item itemToCollect = setTask();
        Item itemToUse;
        while (itemToCollect !=null && !fallInObstacle) {
            // display to player the new task
            displayTask(itemToCollect);

            // if bag is not empty ask user if user wants to use power
            if (player.getInventory()!=null) {
                itemToUse = getItemFromPlayerInventory();
                if (itemToUse.getPurpose() == Purpose.HIDE)
                    canWalkOverObstacle = true;
                else if (itemToUse.getPurpose() == Purpose.DECREASE)
                       obstacle.decreaseSize(GlobSettings.OBSTACLE_SIZE_CHANGE);
            }

            itemLoc = (Comparable) itemToCollect.getLocation();
            // get player command until player hits obstacle
            // or reach item
            while (!onItemLocation && !fallInObstacle) {
                // get the direction & steps
                direction = getDirection();
                steps = getSteps();
                player.move(direction, steps);
                // if player has power to walk over obstacle do not check hit obstacle
                if (!canWalkOverObstacle) fallInObstacle = obstacle.hitObstacle(player.getLocation());
                // check the final place
                if (itemLoc.compareTo((Comparable)player.getLocation()) == 0) onItemLocation = true;
            } // end get player command while loop
            if (!fallInObstacle) {
                player.pickItem(itemToCollect);
                obstacle.increaseSize(GlobSettings.OBSTACLE_SIZE_CHANGE);
                itemToCollect = setTask();
            } else System.out.println("Player fall in obstacle End of Game!");
        } // end while loop
        if (!fallInObstacle) System.out.println("End of Game Obstacle covers all game space!");

    } // end play game

} // end class Game


