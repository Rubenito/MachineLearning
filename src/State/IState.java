package State;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

/**
 * Created by ruben on 02.09.15.
 */
public abstract class IState {

    /**
     * Every state needs to implement the hashCode method,
     * which returns the hashcode of the state.
     * Make sure, that states which you consider equal get the same Hash.
     * @return The Hash of the state
     */
    @Override
    public abstract int hashCode();

    /**
     * Every state needs to implement the equals method,
     * which returns true if the two states are equal and false otherwise
     * @param o the object to compare your state to
     * @return true if equal, false otherwise
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * Every state needs to implement the toString method to ensure it gets displayed nicely
     * @return A String which displays the state nicely
     */
    @Override
    public abstract String toString();

    /**
     * A method to return the memory used by one state,
     * gets used to calculate the memory used by the search algorithms
     *
     * @return
     */
    public int memoryUsed() {
        throw new NotImplementedException();
    }



    /**
     * A method that returns every possible move from this state
     * @return A list with every possible move
     */
    public abstract HashMap<IState, Double> possibleMoves();
}
