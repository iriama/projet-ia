import java.util.Vector;

public class DFS {


    Vector<Integer> visited;

    public DFS() {
        visited = new Vector<Integer>();
    }

    public void execute(Vector<Integer> node) {

        System.out.println("-----");
        Problem.printBuckets(node);

        if (Problem.isGoalReached(node)) {
            System.out.println("GOAL REACHED !");
            return;
        }

        visited.add(Problem.getHash(node));

        var children = Generator.generateChildren(node);

        for (var child: children) {
            if (visited.contains(Problem.getHash(child))) continue;

            execute(child);
        }

    }


}
