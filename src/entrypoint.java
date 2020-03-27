import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

public class entrypoint {

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("usage : java -jar resolver.jar problem.buck [--silent] [--silent-dfs]");
            return;
        }

        var path = args[0];

        for (var arg : args) {
            if (arg.equals("--silent")) Problem.silent = true;
        }

        //Problem.parse("C:\\Users\\PC\\IdeaProjects\\projet-ia\\problems\\test.txt");
        Problem.parse(path);

        System.out.println("---- Problem");
        Problem.printBuckets();

        var startTimeDFS = System.nanoTime() / 1000000;
        try {
            if (!Problem.silent) System.out.println("\n> DFS");
            var DFS = new DFS();
            DFS.execute(new Node<Vector<Integer>>(Problem.values, null), 0);
        } catch (StackOverflowError e) {
            if (!Problem.silent) System.out.println("!!! DFS : StackOverflow");
            startTimeDFS = -1;
        }
        var endTimeDFS = System.nanoTime() / 1000000;

        var startTimeBFS = System.nanoTime() / 1000000;
        try {
            if (!Problem.silent) System.out.println("\n> BFS");
            var BFS = new BFS();
            BFS.execute(new Node<Vector<Integer>>(Problem.values, null));
        } catch (StackOverflowError e) {
            if (!Problem.silent) System.out.println("!!! BFS : StackOverflow");
            startTimeBFS = -1;
        }
        var endTimeBFS = System.nanoTime() / 1000000;

        var startTimeBestFirstDifferenceToGoal = System.nanoTime() / 1000000;
        try {
            if (!Problem.silent) System.out.println("\n> BestFirst : DifferenceToGoal");
            var BFS = new BestFirst(new DifferenceToGoalHeuristic());
            BFS.execute(new Node<Vector<Integer>>(Problem.values, null));
        } catch (StackOverflowError e) {
            if (!Problem.silent) System.out.println("!!! BestFirst - DifferenceToGoal : StackOverflow");
            startTimeBestFirstDifferenceToGoal = -1;
        }
        var endTimeBestFirstDifferenceToGoal = System.nanoTime() / 1000000;

        var startTimeBestFirstNbGoalReached = System.nanoTime() / 1000000;
        try {
            if (!Problem.silent) System.out.println("\n> BestFirst : NbGoalReached");
            var BFS = new BestFirst(new NbGoalReachedHeuristic());
            BFS.execute(new Node<Vector<Integer>>(Problem.values, null));
        } catch (StackOverflowError e) {
            if (!Problem.silent) System.out.println("!!! BestFirst - NbGoalReached : StackOverflow");
            startTimeBestFirstNbGoalReached = -1;
        }
        var endTimeBestFirstNbGoalReached = System.nanoTime() / 1000000;


        System.out.println("\n> Execution time" + (!Problem.silent ? " (I/O included)" : "") + " :");
        System.out.println( " . DFS : " + (startTimeDFS > 0 ? (endTimeDFS - startTimeDFS) + " ms" : "error"));
        System.out.println( " . BFS : " + (startTimeBFS > 0 ? (endTimeBFS - startTimeBFS) + " ms" : "error"));
        System.out.println( " . BestFirst - DifferenceToGoal : " + (startTimeBestFirstDifferenceToGoal > 0 ? (endTimeBestFirstDifferenceToGoal - startTimeBestFirstDifferenceToGoal) + " ms" : "error"));
        System.out.println( " . BestFirst - NbGoalReached : " + (startTimeBestFirstNbGoalReached > 0 ? (endTimeBestFirstNbGoalReached - startTimeBestFirstNbGoalReached) + " ms" : "error"));

    }


}
