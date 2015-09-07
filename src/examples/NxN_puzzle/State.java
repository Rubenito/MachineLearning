package examples.NxN_puzzle;

import java.util.*;
import State.IState;
/**
 * Created by ruben on 06.09.15.
 */
public class State extends IState {

    int[][] state;
    boolean debug = false;

    public static State getValidState(int rows) {
        State validState;
        while (!((validState = new State(rows, false)).isSolvable())) {
        }
        validState.printState();
        return validState;
    }

    public static State getGoalState(int rows) {
        return new State(rows, true);
    }

    private State(int rows, boolean goal) {

        //Generate numbers from 0 to rows^2
        LinkedList<Integer> randomOrder = new LinkedList<>();
        for (int i = 0; i < rows * rows; i++) {
            randomOrder.add(i);
        }

        //Shuffle if not goal state
        if (!goal) {
            Collections.shuffle(randomOrder);
        }

        //Add numbers to internal representation of state
        state = new int[rows][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                state[i][j] = randomOrder.poll();
            }
        }
    }

    private State(int[][] toCopy) {
        state = new int[toCopy.length][toCopy[0].length];
        for(int i = 0; i < toCopy.length; i++){
            state[i] = Arrays.copyOf(toCopy[i],toCopy[i].length);
        }
    }

    private boolean isSolvable() {
        int numSwaps = 0;
        int zeroPos = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if(state[i][j] == 0){
                    zeroPos = i + 1;
                }else {
                    for (int k = i; k < state.length; k++) {
                        int hFirst = 0;
                        if (k == i) {
                            hFirst = j;
                        }
                        for (int h = hFirst; h < state.length; h++) {
                            if (state[k][h] != 0 && state[i][j] > state[k][h]) {
                                numSwaps++;
                            }
                        }
                    }
                }
            }
        }
        if(state.length % 2 == 1) {
            return (numSwaps % 2 == 0);
        }
        else{
            return ((numSwaps + zeroPos) % 2 != 0);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state1 = (State) o;

        return Arrays.deepEquals(state, state1.state);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(state);
    }

    @Override
    public String toString() {
        String printState = "[ ";
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                printState += Integer.toString(state[i][j]) + " ";
            }
        }
        printState += "]";
        return printState;
    }

    @Override
    public int memoryUsed() {
        //The size of an state is rows^2*size of an integer
        return state.length * state.length * 32;
    }

    @Override
    public HashMap<IState, Double> possibleMoves() {
        //Get position of empty tile
        int zeroRow = 0;
        int zeroColumn = 0;
        Outermost:for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if(state[i][j] == 0){
                    zeroRow = i;
                    zeroColumn = j;
                    break Outermost;
                }
            }
        }

        //Create list with possible moves
        HashMap<IState,Double> possibleMoves= new HashMap<>();

        //Check if tile to the left of empty is valid
        if(zeroColumn > 0){
            State left = new State(state);
            left.switchNumbers(zeroRow,zeroColumn,zeroRow,zeroColumn-1);
            possibleMoves.put(left,0.0);
        }
        //Check if tile to the right of empty is valid
        if(zeroColumn < state.length-1){
            State right = new State(state);
            right.switchNumbers(zeroRow, zeroColumn, zeroRow, zeroColumn+1);
            possibleMoves.put(right, 0.0);
        }
        //Check if tile on top of empty is valid
        if(zeroRow > 0){
            State top = new State(state);
            top.switchNumbers(zeroRow, zeroColumn,zeroRow-1,zeroColumn);
            possibleMoves.put(top, 0.0);
        }
        //Check if tile on bottom of empty is valid
        if(zeroRow < state.length-1){
            State bottom = new State(state);
            bottom.switchNumbers(zeroRow, zeroColumn,zeroRow+1,zeroColumn);
            possibleMoves.put(bottom, 0.0);
        }

        return possibleMoves;
    }

    private void switchNumbers(int FirstRows, int FirstColumns, int SecondRows, int SecondColumns){
        //System.out.println("FirstRows: " + FirstRows + "\tFirstColumns: " + FirstColumns + "\tSecondRows: " + SecondRows + "\tSecondColumns:" + SecondColumns);
        int tmp = state[FirstRows][FirstColumns];
        state[FirstRows][FirstColumns] = state[SecondRows][SecondColumns];
        state[SecondRows][SecondColumns] = tmp;
    }

    private void printState(){
        String printState = "[ ";
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                printState += Integer.toString(state[i][j]);
                printState += " ";
            }
        }
        printState += "]";
        System.out.println(printState);
    }
}
