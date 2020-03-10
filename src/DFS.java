import java.util.Vector;

public class DFS {

    class VisitedNode {
        private int hash;
        private int level;

        public VisitedNode(int hash, int level) {
            this.hash = hash;
            this.level = level;
        }

        public int getHash() {
            return hash;
        }

        public int getLevel() {
            return level;
        }

    }


    Vector<VisitedNode> visited;

    public DFS() {
        visited = new Vector<VisitedNode>();
    }

    public void execute(Node<Vector<Integer>> node, int level) {

        if (Problem.isGoalReached(node.getValue())) {
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

            if (val.hash == hash) {

                if (level < val.level) {
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
