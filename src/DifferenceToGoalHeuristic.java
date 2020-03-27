import java.util.Comparator;
import java.util.Vector;


public class DifferenceToGoalHeuristic implements Comparator<Vector<Integer>> {
    @Override
    public int compare(Vector<Integer> a, Vector<Integer> b) {

        var sumA = 0;
        var sumB = 0;

        for (int i=0; i<a.size(); i++) {

            var goal = Problem.getGoal(i);

            sumA += Math.abs( a.get(i) -  goal );
            sumB += Math.abs( b.get(i) - goal );
        }

        return sumB - sumA;
    }
}
