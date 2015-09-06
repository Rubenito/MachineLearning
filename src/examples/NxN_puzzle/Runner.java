package examples.NxN_puzzle;
import Search.*;

/**
 * Created by ruben on 06.09.15.
 */
public class Runner {
    public static void main(String[] args){
        Runtime rt = Runtime.getRuntime();
        System.out.println("Memory used before search: " + ((rt.totalMemory()-rt.freeMemory())/(8*1024)) + " MB");
        DFS<State> dfs = new DFS<State>();
        System.out.println(dfs.search(State.getValidState(3), State.getGoalState(3)));
        System.out.println("Memory used during search: " + dfs.memoryUsed() + " MB");
        dfs = null;
        System.gc();
    }
}
