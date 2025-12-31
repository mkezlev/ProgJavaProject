package game;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        Location loc = new Location();
//        UserInput userInput = new UserInput();
//        Player player = new Player(loc);
//        System.out.println("player x: " + player.getLocation().getX()+" Player y: " + player.getLocation().getY());
//        player.move("N",102);
//        System.out.println("player x: " + player.getLocation().getX()+" Player y: " + player.getLocation().getY());
//        player.move("S",102);
//        System.out.println("player x: " + player.getLocation().getX()+" Player y: " + player.getLocation().getY());
//        player.move("E",102);
//        System.out.println("player x: " + player.getLocation().getX()+" Player y: " + player.getLocation().getY());
//        player.move("W",102);
//        System.out.println("player x: " + player.getLocation().getX()+" Player y: " + player.getLocation().getY());


//        Location loc = new Location();
//        Location loc2 = new Location();
//        loc.newLocation(100);
//
//
//        Obstacle obs = new Obstacle();
//        while (obs.placeObstacle(loc))
//        {
//            loc.newLocation(100);
//            IO.println("location " + loc.getX() + " " + loc.getY());
//            obs.obsLocation();
//            obs.increaseSize(5);
//


/*


    IO.println("Welcome to input Test");
    IO.println(userInput.lengthConstrainedInputString("Enter Direction : E,W,N,S", "Enter one char only",1));
    IO.println(userInput.constrainedInputInteger("Enter Steps","Numbers only",0,100));

 */
        /*
        *** Test for location and obstacle

        Location loc = new Location();
        Location loc2 = new Location();
        loc.newLocation(100);


        Obstacle obs = new Obstacle();
        while (obs.placeObstacle(loc))
        {
            loc.newLocation(100);
            IO.println("location " + loc.getX() + " " + loc.getY());
            obs.obsLocation();
            obs.increaseSize(5);
        }
 */




        /*
        HashMap<Purpose, Integer> map = new HashMap<>();
        IO.println(map.isEmpty());
        map.put(Purpose.AVOID, 1);
        map.replace(Purpose.AVOID, map.get(Purpose.AVOID) + 5);
        map.put(Purpose.AVOID, map.get(Purpose.AVOID) - 3);
        IO.println(map.get(Purpose.AVOID));
        map.replace(Purpose.AVOID,10);
        IO.println(map.get(Purpose.AVOID));
        IO.println(map.get(Purpose.DECREASE));
        */

    }
}