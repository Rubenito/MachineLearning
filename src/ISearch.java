import java.util.List;

/**
 * Created by ruben on 02.09.15.
 */
public interface ISearch<T extends INode>{
    /**
     * The key functunality of every search,
     * performs the algorithm and returns a List which contains
     * a correct path from source to sink (SSSP)
     * @param source The starting point of the search
     * @param sink The node to be found
     * @return a list which contains
     * a valid order of states to pass through to get from
     * source to sink
     */
    List<T> search(T source, T sink);

    /**
     * A function which returns the amount of memory
     * used in dependency to the size of a single node
     * @return The total amount of memory used during the search
     */
    int memoryUsed();
}
