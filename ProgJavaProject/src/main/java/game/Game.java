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
     * Author Murat Kezlev
     * Displays game rules, objectives, and available items.
     */
    private void displayGameObjective(){
        System.out.println("Game Goal: Reach the item's coordinates to collect it.");
        System.out.println("Avoid: Do NOT hit the square Hole(Obstacle).");
        System.out.println("The obstacle size grows after each success.");
        System.out.println("The game ends when the player hits the square obstacle or the obstacle covers the hole game board");
        System.out.printf("Enter direction and steps (0-%d) to move on the game board of size %dx%d\n", GlobSettings.SPACE_SIZE,GlobSettings.SPACE_SIZE,GlobSettings.SPACE_SIZE);
        System.out.println("The user will collect items for 3 purposes:DECREASE,HIDE and No AFFECT, and use them to hide or decrease the size of obstacle");
    }

    /**
     * Author Murat Kezlev
     * place obstacle in game space
     * @return TRUE if obstacle can be placed in the game space
     * if not, FALSE
     */
    private boolean setObstacleLocation(){
        return obstacle.placeObstacle();
    }

    /**
     * Author Murat Kezlev
     *  place player in the game space
     *  in a coordinate outside obstacle
     */
    private void setPlayerLocation(){
        Location loc = new Location();
        loc.newLocation(gameSpace);
        // place player outside the area covered by obstacle
        while (obstacle.hitObstacle(loc)) loc.newLocation(gameSpace);
        player.setLocation(loc);
    }


    /**
     * Author Kangqi Tuo
     * Randomly picks one item from the item pool as the current game objective.
     * @return A randomly selected {@link Item}.
     */
    private Item setItemForUserToCollect() {
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
    private void setItemLocation(Item item){
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
    private void displayTask(Item item){
        int obsX = obstacle.getLocation().getX();
        int obsY = obstacle.getLocation().getY();
        int obsSize = obstacle.getSize();
        System.out.println("---------------------------------------------------------");
        System.out.printf("Task: collect %s at location x=%d , y=%d\n", item.getName(), item.getLocation().getX() , item.getLocation().getY());
        System.out.printf("You are at location x=%d , y=%d\n", player.getLocation().getX(), player.getLocation().getY());
        System.out.printf("There is a hole from x:[%d to %d], y:[%d to %d]\n", obsX,(obsX+obsSize) , obsY, (obsY+obsSize));
    } // end displayTask

    /**
    * Get direction to move
    * @return direction
    */
    private String getDirection(){
        UserInput input = new UserInput();
        String direction ="";
        boolean correctDirection = false;
        while(!correctDirection) {
            direction = input.lengthConstrainedInputString("Direction to move (N)orth,(S)outh,(W)est,(E)ast : Enter first letter",
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
    private Item getItemFromPlayerInventory(){
        List<Item> currentInventory = player.getInventory().getAllItemsAsList();

        if (currentInventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
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
    private Item setTask() {
        obstacle.increaseSize(GlobSettings.OBSTACLE_SIZE_CHANGE);
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
        boolean fallInObstacle = false;
        Item itemToUse;
        int steps;
        String direction;

        Item itemToCollect = setTask();
        displayGameObjective();

        while (itemToCollect != null && !fallInObstacle) {
            onItemLocation = false;
            canWalkOverObstacle = false;

            // get move action from the player and let use items in the player inventory
            // Runs all the time until item is collected or player hits an obstacle
            while (!onItemLocation && !fallInObstacle) {

                do{
                    displayTask(itemToCollect);
                    itemToUse = getItemFromPlayerInventory();
                    if (itemToUse == null) {
                        break; //Skip or finished using items, end it will jump to Movement.
                    }

                    if (itemToUse.getPurpose() == Purpose.HIDE) {
                        canWalkOverObstacle = true;
                        System.out.println(">>> Player now has HIDE power and can walk through obstacle!");
                    } else if (itemToUse.getPurpose() == Purpose.DECREASE) {
                        obstacle.decreaseSize(GlobSettings.OBSTACLE_SIZE_CHANGE);
                        int x1 = obstacle.getLocation().getX();
                        int y1 = obstacle.getLocation().getY();
                        int size = obstacle.getSize();
                        System.out.printf(">>> Player used DECREASE power. Obstacle range is now：x[%d to %d], y[%d to %d]\n",
                                x1, (x1 + size), y1, (y1 + size));
                    }
                }while (itemToUse != null);


                // Movement
                direction = getDirection();
                steps = getSteps();
                player.move(direction, steps);


                if (!canWalkOverObstacle) {
                    fallInObstacle = obstacle.hitObstacle(player.getLocation());
                }


                if (itemToCollect.getLocation().compareTo(player.getLocation()) == 0) {
                    onItemLocation = true;
                }

            } // End while (!onItemLocation && !fallInObstacle)

            if (!fallInObstacle) {
                player.pickItem(itemToCollect);
                itemToCollect = setTask();
            } else {
                System.out.println("Player fall in obstacle. End of Game!");
            }
        }

        if (!fallInObstacle) {
            System.out.println("End of Game Obstacle covers all game space!");
        }
    } // end play game

/*
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


            itemLoc = (Comparable) itemToCollect.getLocation();
            // get player command until player hits obstacle
            // or reach item
            while (!onItemLocation && !fallInObstacle) {
              do {
                if (itemToUse.getPurpose() == Purpose.HIDE) {
                    canWalkOverObstacle = true;
                    System.out.println("Player now has HIDE power and can walk thru obstacle!");
                }
                else if (itemToUse.getPurpose() == Purpose.DECREASE) {
                    obstacle.decreaseSize(GlobSettings.OBSTACLE_SIZE_CHANGE);
                    System.out.println("Player used DECREASE power. Obstacle size is now:"+obstacle.getSize());
                }

                 } while (itemToUse !=null);
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

            }
            // end get player command while loop

            if (!fallInObstacle) {
                player.pickItem(itemToCollect);
                itemToCollect = setTask();
            }
            else
                System.out.println("Player fall in obstacle End of Game!");
        }
        // end while loop

        if (!fallInObstacle) System.out.println("End of Game Obstacle covers all game space!");
    } // end play game

*/

    public void testGame() {
        boolean canWalkOverObstacle = false;
        boolean onItemLocation = false;
        boolean fallInObstacle  = false;
        String direction="";
        int steps = 0;
        Comparable itemLoc;
        Item itemToUse;
        Item itemToCollect = setTask();
        displayGameObjective();
        int count =0;
        while (itemToCollect !=null && !fallInObstacle) {
            // Reset the status
            onItemLocation = false;
            canWalkOverObstacle = false;

            System.out.println("The task iterate :" + ++count);
            System.out.println("OBS Size at start of task:"+obstacle.getSize());
            // display to player the new task
            displayTask(itemToCollect);


            // if bag is not empty ask user if user wants to use power
            itemToUse = getItemFromPlayerInventory();


            if (itemToUse !=null) {
                if (itemToUse.getPurpose() == Purpose.HIDE){
                    canWalkOverObstacle = true;
                    System.out.println("Player now has HIDE power");
                }
                else if (itemToUse.getPurpose() == Purpose.DECREASE)
                {
                    obstacle.decreaseSize(GlobSettings.OBSTACLE_SIZE_CHANGE);
                    System.out.println("OBS Size after decrease:"+obstacle.getSize());
                }
            }

            itemLoc = (Comparable) itemToCollect.getLocation();
            // get player command until player hits obstacle
            // or reach item
            while (!onItemLocation && !fallInObstacle) {
                // get the direction & steps

                // **********


                System.out.println("Enter Steps to test one of the following");
                System.out.println("1 player on item collect loc");
                System.out.println("2 player on obstacle");
            //    System.out.println("3 player on obstacle with hide");
            //    System.out.println("4 on item to collect with with decrease");
                steps = getSteps();
                  if (steps == 1 ) player.setLocation(itemToCollect.getLocation());
                  if (steps == 2)  {
                      player.setLocation(obstacle.getLocation());

                  }
                  if (steps == 3) {
                      player.setLocation(obstacle.getLocation());
                  }
                  if (steps == 4) {
                      player.setLocation(itemToCollect.getLocation());
                      obstacle.decreaseSize(GlobSettings.OBSTACLE_SIZE_CHANGE);
                      System.out.println("OBS Size after decrease:"+obstacle.getSize());
                  }

                // ************

                // if player has power to walk over obstacle do not check hit obstacle
                if (!canWalkOverObstacle)
                    fallInObstacle = obstacle.hitObstacle(player.getLocation());
                // check the final place
                if (itemLoc.compareTo((Comparable)player.getLocation()) == 0)
                    onItemLocation = true;
                //displayTask(itemToCollect);
            }
            // end get player command while loop

            if (!fallInObstacle) {
                player.pickItem(itemToCollect);
                itemToCollect = setTask();
            }
            else
                System.out.println("Player fall in obstacle End of Game!");
        }
        // end while loop

        if (!fallInObstacle) System.out.println("End of Game Obstacle covers all game space!");

    } // end test game

} // end class Game


