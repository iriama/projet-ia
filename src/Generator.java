import java.util.Vector;

public class Generator {

   public static Vector<Vector<Integer>> generateChildren(Vector<Integer> origin) {

       var output = new Vector<Vector<Integer>>();

       // Vider
       for (int i = 0; i<origin.size(); i++) {

           var current_val= origin.get(i);

           // empty bucket
           if (current_val == 0) continue;

           // System.out.println( "Vider(" + i + ")" );

           var copy = Problem.copyValues(origin);

           copy.set(i, 0);

           output.add(copy);

           // Problem.printBuckets(copy);
       }

       // Remplir
       for (int i = 0; i<origin.size(); i++) {

           var current_val = origin.get(i);
           var current_capacity = Problem.getCapacity(i);

           if (current_val >= current_capacity) continue;

           // System.out.println( "Remplir(" + i + ")" );

           var copy = Problem.copyValues(origin);

           copy.set(i, current_capacity);

           output.add(copy);

           // Problem.printBuckets(copy);
       }

       // Transverser
       for (int i = 0; i<origin.size(); i++) {

           var current_val = origin.get(i);

           // current is empty
           if (current_val == 0) continue;

           for (int k = 0; k<origin.size(); k++) {

               if (i == k) continue;

               var other_val = origin.get(k);
               var max_capacity = Problem.getCapacity(k) - other_val;

               // Other is full
               if (max_capacity == 0) continue;

               // System.out.println( "Transverser(" + i + ", " + k +  ")" );

               var transversed = Math.min(current_val, max_capacity);

               var copy = Problem.copyValues(origin);

               copy.set(i, current_val - transversed);
               copy.set(k, other_val + transversed);

               output.add(copy);

               // Problem.printBuckets(copy);
           }

       }

       return output;

   }

}
