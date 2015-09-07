package examples.NxN_puzzle;
import Search.*;

/**
 * Created by ruben on 06.09.15.
 */
public class Runner {
    public static void main(String[] args){
        DFS<State> dfs = new DFS<State>();
        State start = State.getValidState(3);
        System.out.println("DFS: ");
        System.out.println("Length of solution: " + dfs.search(start, State.getGoalState(3)).size());
        System.out.println("Memory used: " + dfs.memoryUsed()/(8*1024) + " MB");
        System.out.println("Time used: " + dfs.timeUsed() + " ms");

        dfs = null;
        DepthLimitedDFS<State> dlDFS = new DepthLimitedDFS<State>();
        System.out.println("\nDepth limited DFS: ");
        System.out.println("Length of solution: " + dlDFS.search(start, State.getGoalState(3),40).size());
        System.out.println("Memory used: " + dlDFS.memoryUsed()/(8*1024) + " MB");
        System.out.println("Time used: " + dlDFS.timeUsed() + " ms");
    }
}
