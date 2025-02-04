import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class TreeSearch {
    //private ArrayList<Cube> frontier;
    private PriorityQueue<Cube> frontier;
    private HashSet<Cube> closedSet;
    public int counter = 0;

    TreeSearch() {
        //this.frontier = new ArrayList<>();
        this.closedSet = new HashSet<>();
        this.frontier = new PriorityQueue<>();
    }

    Cube BestFSClosedSet(Cube initialState, int k) {
        if (initialState.isFinal(k))
            return initialState;
        // step 1: put initial state in the frontier.
        this.frontier.add(initialState);
        // step 2: check for empty frontier.
        while (this.frontier.size() > 0) {
            counter++;
            // step 3: get the first node out of the frontier.
            Cube currentState = this.frontier.remove();//poll()
            // step 4: if final state, return.
            if (currentState.isFinal(k))
                return currentState;

            // step 5: if the node is not in the closed set, put the children at the
            // frontier.
            // else go to step 2.
            if (!this.closedSet.contains(currentState)) {
                this.closedSet.add(currentState);
                this.frontier.addAll(currentState.getChildren(counter));
                //System.out.println(this.frontier.size());
                // step 6: sort the frontier based on the heuristic score to get best as first
                //Collections.sort(this.frontier); // sort the frontier to get best as first// priority queu
            }
            
        }
        return null;
    }
}

