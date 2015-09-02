import java.util.List;

/**
 * Created by ruben on 02.09.15.
 */
public interface INode {

    /**
     * Every node needs to implement the hashCode method,
     * which returns the hashcode of the node.
     * Make sure, that nodes which you consider equal get the same Hash.
     * @return The Hash of the node
     */
    @Override
    int hashCode();

    /**
     * Every node needs to implement the equals method,
     * which returns true if the two nodes are equal and false otherwise
     * @param o the object to compare your node to
     * @return true if equal, false otherwise
     */
    @Override
    boolean equals(Object o);

    /**
     * A method to return the memory used by one node,
     * gets used to calculate the memory used by the search algorithms
     *
     * @return
     */
    default int memoryUsed() {
        return 0;
    }

    /**
     * A method that returns every possible move from this node
     * @return A list with every possible move
     */
    List<INode> possibleMoves();
}
