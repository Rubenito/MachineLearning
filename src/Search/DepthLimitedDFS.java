package Search;

import State.IState;

import java.util.*;

/**
 * Created by ruben on 06.09.15.
 */
public class DepthLimitedDFS<T extends IState> implements ISearch<T> {

    /**
     * Needed data structures
     */
    HashSet<T> visited = new HashSet<>();
    HashMap<T,T> parent = new HashMap<>();
    int nodeSize = 0;
    int movesMaxSize = 0;
    int visitedMaxSize = 0;
    long timeUsed = 0;
    /**
     * ISearch.DFS with complete saving of every visited node
     * which makes it faster, but less memory efficient
     * @param source starting point of the search
     * @param sink node to find
     * @return List which holds a valid sequence from source to sink
     */
    @Override
    public List<T> search(T source, T sink, int limit) {
        long startingTime = System.currentTimeMillis();
        nodeSize = source.memoryUsed();
        LinkedList<T> result = null;
        if(rekursiveSearch(source,sink,limit)){
            T current = sink;
            result = new LinkedList<>();
            parent.put(source,null);
            while(current != null){
                result.addFirst(current);
                current = parent.get(current);
            }
        }
        timeUsed = (System.currentTimeMillis() - startingTime);
        return result;
    }

    public boolean rekursiveSearch(T source, T sink, int limit){

        boolean result;

        //See if limit is reached
        if(limit==0){
            return false;
        }

        //See if element is found
        if(source.equals(sink)){
            return true;
        }

        //If not call depth-limited-search recursively
        visited.add(source);
        if(visited.size() > visitedMaxSize){
            visitedMaxSize = visited.size();
        }
        HashMap<T,Double> moves = (HashMap<T,Double>)source.possibleMoves();
        if(moves.size() > movesMaxSize){
            movesMaxSize = moves.size();
        }
        for(T move : moves.keySet()){
            if(!visited.contains(move)) {
                result = rekursiveSearch(move, sink, limit - 1);
                //If one rekursive call returns non null, we found the sink
                if (result) {
                    parent.put(move,source);
                    return true;
                }
            }
        }
        visited.remove(source);
        return false;
    }

    public long memoryUsed(){
        return visitedMaxSize*nodeSize + movesMaxSize*(nodeSize + 64) + parent.size()*nodeSize;
    }

    public long timeUsed(){
        return timeUsed;
    }
}
