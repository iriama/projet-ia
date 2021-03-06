import java.util.Vector;

public class DFS {

    boolean found;
    Vector<VisitedNode> visited;

    public DFS() {
        visited = new Vector<VisitedNode>();
        found = false;
    }

    public void execute(Node<Vector<Integer>> node, int level) {

        // s'arreter à la premiere solution
        if (found) return;

        if (Problem.isGoalReached(node.getValue())) {

            found = true;

            if (Problem.silent) return; // no printing

            System.out.println("<<<<< GOAL REACHED ! >>>>>");

            var current = node;

            while (current != null) {
                System.out.println("---- step");
                Problem.printBuckets(current.getValue());

                current = current.getParent();
            }

            return;
        }

        var hash = Problem.getHash(node.getValue());
        var exists = false;
        for (int i = 0; i<visited.size(); i++) {
            var val = visited.get(i);

            if (val.getHash() == hash) {

                if (level < val.getLevel()) {
                    visited.set(i, new VisitedNode(hash, level));
                } else {
                    // state explored at a lower level : halt
                    return;
                }

                exists = true;
                break;
            }
        }

        if (!exists) {
            visited.add(new VisitedNode(hash, level));
        }

        // visited.add(Problem.getHash(node));

        var children = Generator.generateChildren(node.getValue());

        for (var child: children) {
            execute(new Node<Vector<Integer>>(child, node), level+1);
        }
    }


}
