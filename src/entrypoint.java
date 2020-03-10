import java.io.IOException;
import java.util.Vector;

public class entrypoint {

    public static void main(String[] args) throws IOException {

        Problem.parse("C:\\Users\\SOHAIB\\OneDrive\\Desktop\\STUDY\\S6\\projet-ia\\problems\\test.txt");

        System.out.println("---- Problem");
        Problem.printBuckets();

        System.out.println("---- DFS");

        var DFS = new DFS();
        DFS.execute(new Node<Vector<Integer>>(Problem.values, null), 0);


    }


}
