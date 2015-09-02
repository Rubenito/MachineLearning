import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ruben on 02.09.15.
 */
public class DFS<T extends INode> implements ISearch<T>{

    /**
     * Needed data structures
     */
    List<T> result = new LinkedList<>();
    Stack<T> border = new Stack<>();
    HashMap<T,T> parent = new HashMap<>();

    /**
     * DFS with complete saving of every visited node
     * which makes it faster, but less memory efficient
     * @param source startpoint of the search
     * @param sink node to find
     * @return List which holds a valid sequence from source to sink
     */
    @Override
    public List<T> search(T source, T sink) {

        // init DFS
        border.push(source);
        parent.put(source,null);

        //DFS with
        while (!border.isEmpty()){
            T current = border.pop();
            List<T> moves = (List<T>) current.possibleMoves();
            for(T move : moves){
                if(move.equals(sink)) {
                    result.add(move);
                    while(current != null){
                        result.add(current);
                        current = parent.get(current);
                    }
                    return result;
                }else if (!parent.containsKey(move)) {
                    parent.put(move, current);
                    border.push(move);
                }
            }
        }
        return null;
    }

    @Override
    public int memoryUsed(){
        return parent.size()* ;
    }
}
