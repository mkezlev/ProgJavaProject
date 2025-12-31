package game;

import java.util.List;
import java.util.Random;

/**
 * Manages game logic, player actions, obstacles, and item collection tasks.
 */
public class Game {

    Player player;
    Item[] items;
    Obstacle obstacle;
    int gameSpace = GlobSettings.SPACE_SIZE; // size of area 10x10, 100x10

    // Constructor
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

    /**
     * Displays game rules, objectives, and available items.
     */
    public void displayGameObjective(){
        System.out.println("Game Objective is to navigate to the coordinates the item is placed and collect the item");
        System.out.println("There is a square obstacle on the game board which user must not hit");
        System.out.println("The square obstacle size increase after each successful task");
        System.out.println("The game ends when the player hits the square obstacle or the obstacle covers the whole game board");
        System.out.printf("The player enter the direction and steps (0-%d) to move on the game board of size %dx%d\n", GlobSettings.SPACE_SIZE,GlobSettings.SPACE_SIZE,GlobSettings.SPACE_SIZE);
        System.out.println("The player can use power of the collect item to hide the obstacle or decrease the size of obstacle");
        System.out.println("The user will collect one of the items:");
        for (int i = 0; i <items.length; i++) {
            System.out.printf("Item name %s with power %s\n",items[i].getName(),items[i].getPurpose().toString());
        }
        System.out.println("Do not hit the obstacle, you can use the power of the items you collect");

    }

    /**
     * place obstacle in game space
     * @return TRUE if obstacle can be placed in the game space
     * if not, FALSE
     */
    public boolean setObstacleLocation(){
        return obstacle.placeObstacle();
    }

    /**
     *  place player in the game space
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
     * @return A randomly selected {@link Item}.
     */
    public Item setItemForUserToCollect() {
        Random random = new Random();
        if (items == null || items.length == 0) {
            return null;
        }

        // random.nextInt(5) will return one number in [0,4]
        int index = random.nextInt(items.length);
        Item itemPicked = items[index];

        return itemPicked;
    }

    /**
    * Place selected item in the game space
    * in a coordinate different from player and outside obstacle
     */
    public void setItemLocation(Item item){
        Location loc = new Location();
        loc.newLocation(gameSpace);
        Comparable cLoc = (Comparable)loc;
        Comparable playerLoc = (Comparable)player.location;

        // place item in a coordinate different from player
        // and outside obstacle
        while(cLoc.compareTo(playerLoc) == 0 || obstacle.hitObstacle(loc)){
            loc.newLocation(gameSpace);
            cLoc = (Comparable)loc;
        }
        item.placeItem(loc);
    } // end setItemLocation


    /**
     *  display player the task to collect and X-Y coordinates of the item,
     *  Player X-Y location
     *  Area that player must avoid for not hitting the obstacle
     *  @param item
     */
    public void displayTask(Item item){
        int obsX = obstacle.getLocation().getX();
        int obsY = obstacle.getLocation().getY();
        int obsSize = obstacle.getSize();

        System.out.printf("Your task is to collect %s at location x=%d , y=%d\n", item.getName(), item.getLocation().getX() , item.getLocation().getY());
        System.out.printf("You are at location at location x=%d , y=%d\n", player.getLocation().getX(), player.getLocation().getY());
        System.out.printf("There is a whole from x: %d to %d  and y: %d to %d\n", obsX,(obsX+obsSize) , obsY, (obsY+obsSize));

    } // end displayTask

    /**
    * Get direction to move
    * @return direction
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

    /**
     * Get steps to move
     * @return steps
     */
    private int getSteps(){
        UserInput input = new UserInput();
        int steps = input.constrainedInputInteger("Enter number of steps between 0 and "+GlobSettings.SPACE_SIZE,
                "Enter steps in the range 0-"+GlobSettings.SPACE_SIZE,0,GlobSettings.SPACE_SIZE);
        return steps;
    }

    /**
    * Ask user which item player wants to use from the player inventory
    * and remove that item from user inventory
    * @return The selected {@link Item}
    */
    public Item getItemFromPlayerInventory(){
        List<Item> currentInventory = player.getInventory().getAllItemsAsList();

        if (currentInventory.isEmpty()) {
            System.out.println("Your inventory is empty!");
            return null;
        }

        System.out.println("Inventory:");
        for (int i = 0; i < currentInventory.size(); i++) {
            System.out.printf("[" + (i + 1) + "] %s with power %s \n", currentInventory.get(i).getName(), currentInventory.get(i).getPurpose().toString());
        }

        UserInput input = new UserInput();
        int choice = input.constrainedInputInteger(
                "Enter the number of the item (1-" + currentInventory.size() + ")to use item, or enter 0 to skip: ",
                "Invalid selection",
                0, currentInventory.size()+1);

        if (choice == 0) {
            System.out.println("Skip using item.");
            return null;
        }
        else if (choice > 0 && choice <= currentInventory.size()) {
            Item selectedItem = currentInventory.get(choice - 1);
            player.getInventory().removeFromInventory(selectedItem);
            return selectedItem;
        }
        return null;
    }


    /**
    * Set task
    * @return The new target {@link Item} or null if no task can be set.
     */
    public Item setTask() {
        // set obstacle location if obstacle can be placed in the game space
        if (setObstacleLocation()) {
            Item itemToCollect;
            // pick random item to pick by player
            itemToCollect = setItemForUserToCollect();
            // set player location
            setPlayerLocation();
            // set item location
            setItemLocation(itemToCollect);
            return itemToCollect;
        } else return null;

    } // end set task

    /**
     * Start the main game loop, handling user turns, item usage,
     * movement, and collision detection.
     */
    public void playGame() {
        boolean canWalkOverObstacle = false;
        boolean onItemLocation = false;
        boolean fallInObstacle  = false;
        String direction="";
        int steps = 0;
        Comparable itemLoc;
        Item itemToUse;
        Item itemToCollect = setTask();
        displayGameObjective();
        while (itemToCollect !=null && !fallInObstacle) {
            // Reset the status
            onItemLocation = false;
            canWalkOverObstacle = false;

            // display to player the new task
            displayTask(itemToCollect);

            //  *****************for test adding code below
            // player.pickItem(itemToCollect); // test line

            // if bag is not empty ask user if user wants to use power
            itemToUse = getItemFromPlayerInventory();

            if (itemToUse !=null) {
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
                if (!canWalkOverObstacle)
                    fallInObstacle = obstacle.hitObstacle(player.getLocation());
                // check the final place
                if (itemLoc.compareTo((Comparable)player.getLocation()) == 0)
                    onItemLocation = true;
                displayTask(itemToCollect);
            }
            // end get player command while loop

            if (!fallInObstacle) {
                player.pickItem(itemToCollect);
                obstacle.increaseSize(GlobSettings.OBSTACLE_SIZE_CHANGE);
                itemToCollect = setTask();
            }
            else
                System.out.println("Player fall in obstacle End of Game!");
        }
        // end while loop

        if (!fallInObstacle) System.out.println("End of Game Obstacle covers all game space!");

    } // end play game

} // end class Game


