import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

public class entrypoint {

    public static void main(String[] args) throws IOException {

        var path = args[ args.length - 1 ];

        //Problem.parse("C:\\Users\\PC\\IdeaProjects\\projet-ia\\problems\\test.txt");
        Problem.parse(path);

        System.out.println("---- Problem");
        Problem.printBuckets();

        var startTimeDFS = System.nanoTime() / 1000000;
        try {
            System.out.println("---- DFS");
            var DFS = new DFS();
            DFS.execute(new Node<Vector<Integer>>(Problem.values, null), 0);
        } catch (StackOverflowError e) {
            System.out.println("!!! DFS : StackOverflow");
            startTimeDFS = -1;
        }
        var endTimeDFS = System.nanoTime() / 1000000;

        var startTimeBFS = System.nanoTime() / 1000000;
        try {
            System.out.println("---- BFS");
            var BFS = new BFS();
            BFS.execute(new Node<Vector<Integer>>(Problem.values, null));
        } catch (StackOverflowError e) {
            System.out.println("!!! BFS : StackOverflow");
            startTimeBFS = -1;
        }
        var endTimeBFS = System.nanoTime() / 1000000;

        var startTimeBestFirstDifferenceToGoal = System.nanoTime() / 1000000;
        try {
            System.out.println("---- BestFirst : DifferenceToGoal");
            var BFS = new BestFirst(new DifferenceToGoalHeuristic());
            BFS.execute(new Node<Vector<Integer>>(Problem.values, null));
        } catch (StackOverflowError e) {
            System.out.println("!!! BestFirst - DifferenceToGoal : StackOverflow");
            startTimeBestFirstDifferenceToGoal = -1;
        }
        var endTimeBestFirstDifferenceToGoal = System.nanoTime() / 1000000;

        var startTimeBestFirstNbGoalReached = System.nanoTime() / 1000000;
        try {
            System.out.println("---- BestFirst : NbGoalReached");
            var BFS = new BestFirst(new NbGoalReachedHeuristic());
            BFS.execute(new Node<Vector<Integer>>(Problem.values, null));
        } catch (StackOverflowError e) {
            System.out.println("!!! BestFirst - NbGoalReached : StackOverflow");
            startTimeBestFirstNbGoalReached = -1;
        }
        var endTimeBestFirstNbGoalReached = System.nanoTime() / 1000000;


        System.out.println("> Execution time (I/O included) :");
        System.out.println( " . DFS : " + (startTimeDFS > 0 ? (endTimeDFS - startTimeDFS) + " ms" : "error"));
        System.out.println( " . BFS : " + (startTimeBFS > 0 ? (endTimeBFS - startTimeBFS) + " ms" : "error"));
        System.out.println( " . BestFirst - DifferenceToGoal : " + (startTimeBestFirstDifferenceToGoal > 0 ? (endTimeBestFirstDifferenceToGoal - startTimeBestFirstDifferenceToGoal) + " ms" : "error"));
        System.out.println( " . BestFirst - NbGoalReached : " + (startTimeBestFirstNbGoalReached > 0 ? (endTimeBestFirstNbGoalReached - startTimeBestFirstNbGoalReached) + " ms" : "error"));

    }


}
