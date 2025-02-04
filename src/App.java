import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class App {

    public static void main(String[] args)
    
    {
        System.out.println("HOW MANY SIDES DO U WANT ME TO SOLVE?");
        Scanner in = new Scanner(System.in);
 
        int k = Integer.parseInt(in.nextLine());
        int counter = 0;
        Cube initialState = new Cube();  // our initial (3x3)x6 state.
        // String[] moves = {"R'", "B'", "D", "F", "F", "U", "B","M", "U"};// dn to linei
        // String[] moves = {"R'", "B", "D", "F", "R'", "B","U"  };// R', M , S
        // String[] moves = {"R",  "F", "S","B'", "M","L'","R", "L", "S", "E"};
        //String[] moves = {"R", "S", "S", "U", "D", "E'", "U", "U", "B",  "R", "M", "L"}; // M , L , L'
        //String[] moves = {"U", "R'", "D", "F", "F", "B'","R", "L'", "M", "S",};
        //String[] moves = {"U", "U", "R", "D", "S", "F", "M", "M", "L", "L", "R", "E", "R'"}; // dn to linei
        //String[] moves = {"R'", "B", "D", "F", "R'", "B","U" }; // dn to linei
        // String[] moves = {"U", "U'", "D", "D'", "F", "F'", "B", "B'", "R", "R'", "L", "L'"};
        //String[] moves = {"U", "R", "L", "F", "B", "D", "U'", "R'", "L'", "F'", "B'", "D'"};
        //String[] moves = {"M", "S", "E", "M'", "S'", "E'"};
        // for (String move : moves){//test
        //     initialState.Move(move);//test
        //     initialState.PrintSides();
        // }
        for (String move : initialState.Randomaise(7))
            System.out.print(move);
        System.out.println();
        //initialState.PrintSides();
        initialState.score();
        TreeSearch searcher = new TreeSearch();
        long start = System.currentTimeMillis();
        
        Cube terminalState = searcher.BestFSClosedSet(initialState, k);
        long end = System.currentTimeMillis();
        if(terminalState == null) System.out.println("Could not find a solution.");
        else
        {
			// print the path from beggining to start.
            Cube temp = terminalState; // begin from the end.
            terminalState.PrintSides();
            ArrayList<Cube> path = new ArrayList<>();
			path.add(terminalState);
            while(temp.getFather() != null) // if father is null, then we are at the root.
            {
                path.add(temp.getFather());
                temp = temp.getFather();
            }
			// reverse the path and print.
            Collections.reverse(path);

            // terminalState.PrintSides();
            for(Cube item: path)
            {
                counter++;
                // System.out.println(counter);
                String move = item.getPrevMove();
                
                if (move != null)
                    System.out.print(move + " ");
                // item.PrintSides();
            }

            System.out.println();
            System.out.println("Search time:" + (double)(end - start) / 1000 + " sec.");  // total time of searching in seconds.
        }
     }
}
