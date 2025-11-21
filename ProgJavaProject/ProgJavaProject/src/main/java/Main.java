//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        HashMap<Purpose, Integer> map = new HashMap<>();
        IO.println(map.isEmpty());
        map.put(Purpose.AVOID, 1);
        map.replace(Purpose.AVOID, map.get(Purpose.AVOID) + 5);
        map.put(Purpose.AVOID, map.get(Purpose.AVOID) - 3);
        IO.println(map.get(Purpose.AVOID));
        map.replace(Purpose.AVOID,10);
        IO.println(map.get(Purpose.AVOID));
        IO.println(map.get(Purpose.DECREASE));
        }
    }
    //commit for testing in gitHub
}
