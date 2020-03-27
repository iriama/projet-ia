import java.util.Comparator;
import java.util.HashSet;
import java.util.Vector;

public class BestFirst {

    HashSet<Integer> visited;
    private Vector<Node<Vector<Integer>>> queue;
    private Comparator<Vector<Integer>> heurestic;

    BestFirst(Comparator<Vector<Integer>> heurestic) {
        visited = new HashSet<Integer>();
        queue = new Vector<Node<Vector<Integer>>>();
        this.heurestic = heurestic;
    }

    public void execute(Node<Vector<Integer>> start) {

        queue.add(start); // add start node to the queue
        visited.add(Problem.getHash(start.getValue())); // mark start node as visited

        while (queue.size() > 0) {

            var head = queue.get(0);

            // ---- If goal reached
            if (Problem.isGoalReached(head.getValue())) {
                System.out.println("<<<<< GOAL REACHED ! >>>>>");

                var current = head;
                var unordered = new Vector<Vector<Integer>>();

                while (current != null) {
                    unordered.add(current.getValue());
                    current = current.getParent();
                }

                // Print
                for (int i = unordered.size() - 1; i >= 0; i--) {
                    System.out.println("---- step");
                    Problem.printBuckets(unordered.get(i));
                }

                return;
            }
            // ----

            var children = Generator.generateChildren(head.getValue());
            queue.removeElementAt(0); // defiler

            // Heuristique
            children.sort(heurestic);

            for (var child : children) {
                var hash = Problem.getHash(child);
                if (visited.contains(hash)) // if already visited : ignore
                    continue;

                queue.add(new Node<Vector<Integer>>(child, head)); // enfiler
                visited.add(hash); // mark as visited
            }

        }
    }

}
