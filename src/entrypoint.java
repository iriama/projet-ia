import java.io.IOException;

public class entrypoint {

    public static void main(String[] args) throws IOException {

        Problem.parse("G:\\projet-ia\\problems\\test.txt");

        Problem.values.set(0, 5);
        Problem.values.set(1, 4);
        Problem.values.set(2, 1);

        Problem.printBuckets();

        System.out.println(Problem.getHash());

        System.out.println("---- DFS");

        var DFS = new DFS();
        DFS.execute(Problem.values);

    }


}
