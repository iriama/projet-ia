import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Problem {

    public static Vector<Integer> values = new Vector<Integer>();
    private static Vector<Integer> capacities = new Vector<Integer>();
    private static Vector<Integer> goals = new Vector<Integer>();
    public static boolean silent = false;
    public static boolean silentDFS = false;

    public static void parse(String path) throws IOException {

        var reader = new BufferedReader(
                new FileReader(path)
        );

        String lineCount = reader.readLine();
        String lineCapacity = reader.readLine();
        String lineGoal = reader.readLine();

        var bucketCount = Integer.parseInt(lineCount);
        var splitCapacity = lineCapacity.split(" ");
        var splitGoal = lineGoal.split(" ");

        assert(bucketCount == splitCapacity.length &&  splitCapacity.length == splitGoal.length) :
                "Format incorrect";

        for (int i = 0; i < splitCapacity.length; i++) {

            var capacity = Integer.parseInt(splitCapacity[i]);
            var goal = Integer.parseInt(splitGoal[i]);

            assert (goal <= capacity) :
                    "L'objectif de remplissage ne peut pas être supérieur à la capacité du seau";

            values.add(0);
            capacities.add(capacity);
            goals.add(goal);
        }
    }

    public static Vector<Integer> copyValues(Vector<Integer> origin) {

        var copy = new Vector<Integer>(origin.size());

        for (int val: origin) {
            copy.add(
                    val
            );
        }

        return copy;
    }

    public static Vector<Integer> copyValues() {
        return copyValues(values);
    }

    public static void printBuckets(Vector<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            System.out.println("[Bucket n°" + i + "] current = " + values.get(i) + " ; max = " + capacities.get(i) + " ; goal = " + goals.get(i));
        }
    }

    public static void printBuckets() {
        printBuckets(values);
    }


    public static int getCapacity(int index) {
        return capacities.get(index);
    }

    public static int getGoal(int index) {
        return goals.get(index);
    }

    public static Vector<Integer> getGoalState() {
        var ret = new Vector<Integer>();

        for (int i=0; i<values.size(); i++)
            ret.add(getGoal(i));

        return ret;
    }

    public static boolean isGoalReached(Vector<Integer> values) {

        for (int i=0; i<values.size(); i++) {
            var val = values.get(i);
            var goal = getGoal(i);

            if (val != goal) return false;
        }

        return true;
    }

    public static int getHash() {
        return getHash(values);
    }

    public static int getHash(Vector<Integer> values) {
        var s = "";

        for (var val: values) {
            s += values.toString();
        }

        return s.hashCode();
    }

}
