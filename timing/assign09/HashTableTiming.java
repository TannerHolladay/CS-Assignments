package assign09;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@CodeTimer.Settings(title = "",
        titleY = "",
        titleX = "",
        start = 100,
        loops = 1000
)
public class HashTableTiming extends CodeTimer {
    static HashMap<Integer, Integer> table = new HashMap<>();
    static ArrayList<StudentMediumHash> array;
    static Random rnd = new Random();

    public static void main(String[] args) {
        rnd = new Random();
        table = new HashMap<>();
        new HashTableTiming();
    }

    private String randString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String newstr = "";
        for (int i = 0; i < length; i++) {
            newstr += alphabet.charAt(rnd.nextInt(alphabet.length()));
        }
        return newstr;
    }

    /**
     * Code that is being timed
     *
     * @param steps Current steps
     */
    @Override
    public void timedCode(int steps) {
        table = new HashMap<>();
        for (int i = 0; i < steps; i++) {
            table.put(rnd.nextInt(steps), 10);
        }
    }
}
