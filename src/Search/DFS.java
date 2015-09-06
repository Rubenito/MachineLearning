package Search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import State.IState;

/**
 * Created by ruben on 02.09.15.
 */
public class DFS<T extends IState> implements Search<T> {

    /**
     * Needed data structures
     */
    LinkedList<T> result = new LinkedList<>();
    Stack<T> border = new Stack<>();
    HashMap<T,T> parent = new HashMap<>();
    int nodeSize = 0;
    int movesMaxSize = 0;

    /**
     * Search.DFS with complete saving of every visited node
     * which makes it faster, but less memory efficient
     * @param source starting point of the search
     * @param sink node to find
     * @return List which holds a valid sequence from source to sink
     */
    @Override
    public List<T> search(T source, T sink) {

        //Get node size for memory used
        nodeSize = source.memoryUsed();
        // init Search.DFS
        border.push(source);
        parent.put(source,null);

        //Iterative Search.DFS
        while (!border.isEmpty()){

            //Get next element from border
            T current = border.pop();
            //Check for possible moves & update movesMaxSize for memory used
            HashMap<T,Double> moves = (HashMap<T,Double>) current.possibleMoves();
            if(moves.size() > movesMaxSize){
                movesMaxSize = moves.size();
            }

            //For every possible move check
            for(T move : moves.keySet()){

                //If sink stop search and return result list
                if(move.equals(sink)) {
                    result.add(move);
                    while(current != null){
                        result.addFirst(current);
                        current = parent.get(current);
                    }
                    return result;
                }

                //Else if not yet visited add move to border
                else if (!parent.containsKey(move)) {
                    parent.put(move, current);
                    border.push(move);
                }
            }
        }

        //If all states are visited and sink isn't found return null to signal failure
        return null;
    }
}
