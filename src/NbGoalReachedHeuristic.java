import java.util.Comparator;
import java.util.Vector;

public class NbGoalReachedHeuristic implements Comparator<Vector<Integer>> {
    @Override
    public int compare(Vector<Integer> a, Vector<Integer> b) {

        var nbReachedA = 0;
        var nbReachedB = 0;

        for (int i=0; i<a.size(); i++) {

            var goal = Problem.getGoal(i);
            var valA = a.get(0);
            var valB = b.get(0);

            if ( valA == goal ) nbReachedA++;
            if ( valB == goal ) nbReachedB++;
        }

        return nbReachedA - nbReachedB;
    }
}
