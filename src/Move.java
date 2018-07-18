import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author geetikasharma
 *
 */
public class Move {
    public static boolean isRight(StateSpace state, int i, int j) {  //20
        Peg peg = state.getGrid()[i][j];

        List<Integer> pegAfterBoundaries = StateSpace.boundaries.get(j+1); //1-5
        List<Integer> freeSpaceBoundaries = StateSpace.boundaries.get(j+2); //0-7

        boolean isPegAfter = (j + 1) <= state.getGrid().length-1 && i >= pegAfterBoundaries.get(0) && i < pegAfterBoundaries.get(1) && state.getGrid() [i][j+1] != null;
        boolean isFreeSpace = (j + 2) <= state.getGrid().length-1 && i >= freeSpaceBoundaries.get(0) && i < freeSpaceBoundaries.get(1) && state.getGrid()[i][j+2] == null;

        return peg != null && isPegAfter && isFreeSpace;
    }

    public static boolean isLeft(StateSpace state, int i, int j) {
        Peg peg = state.getGrid()[i][j];

        List<Integer> pegAfterBoundaries = StateSpace.boundaries.get(j-1);
        List<Integer> freeSpaceBoundaries = StateSpace.boundaries.get(j-2);

        boolean isPegAfter = (j - 1) >= 0 && i >= pegAfterBoundaries.get(0) && i < pegAfterBoundaries.get(1) && state.getGrid() [i][j-1] != null;
        boolean isFreeSpace = (j - 2) >= 0 && i >= freeSpaceBoundaries.get(0) && i < freeSpaceBoundaries.get(1) && state.getGrid()[i][j-2] == null;


        return peg != null && isPegAfter && isFreeSpace;
    }

    public static boolean isUp(StateSpace state, int i, int j) {
        Peg peg = state.getGrid()[i][j];

        List<Integer> pegAfterBoundaries = StateSpace.boundaries.get(i-1);
        List<Integer> freeSpaceBoundaries = StateSpace.boundaries.get(i-2);

        boolean isPegAfter = (i - 1) >= 0 && j >= pegAfterBoundaries.get(0) && j < pegAfterBoundaries.get(1) && state.getGrid() [i-1][j] != null;
        boolean isFreeSpace = (i - 2) >= 0 && j >= freeSpaceBoundaries.get(0) && j < freeSpaceBoundaries.get(1) && state.getGrid()[i-2][j] == null;

        return peg != null && isPegAfter && isFreeSpace;
    }

    public static boolean isDown(StateSpace state, int i, int j) {  //41
        Peg peg = state.getGrid()[i][j];

        List<Integer> pegAfterBoundaries = StateSpace.boundaries.get(i+1);
        List<Integer> freeSpaceBoundaries = StateSpace.boundaries.get(i+2);


        boolean isPegAfter = (i + 1) <= state.getGrid().length-1 && j >= pegAfterBoundaries.get(0) && j < pegAfterBoundaries.get(1) && state.getGrid()[i+1][j] != null;
        boolean isFreeSpace = (i + 2) <= state.getGrid().length-1 && j >= freeSpaceBoundaries.get(0) && j < freeSpaceBoundaries.get(1) && state.getGrid()[i+2][j] == null;

        return peg != null && isPegAfter && isFreeSpace;
    }

    public Set<Node> moveRight(StateSpace state, int i, int j) {
        Peg peg = state.getGrid()[i][j];
        if (peg != null) {
            Set<Node> nodes = new LinkedHashSet<>();
            if (isRight(state, i, j)) {

                Peg[][] grid = state.getCloneGrid();

                    grid[i][j + 1] = null;
                    grid[i][j + 2] = grid[i][j];
                    grid[i][j] = null;

                StateSpace s = new StateSpace(grid);
                Node n = new Node(s);
                n.setMovePosition(new Position(i, j, i, j + 2));
                nodes.add(n);
            }
            return nodes;
        }
        return null;
    }

    public Set<Node> moveLeft(StateSpace state, int i, int j) {
        Peg peg = state.getGrid()[i][j];
        if (peg != null) {
            Set<Node> nodes = new LinkedHashSet<>();
            if (isLeft(state, i, j)) {

                Peg[][] grid = state.getCloneGrid();

                    grid[i][j - 1] = null;
                    grid[i][j - 2] = grid[i][j];
                    grid[i][j] = null;

                StateSpace s = new StateSpace(grid);
                Node n = new Node(s);
                n.setMovePosition(new Position(i, j, i, j - 2));
                nodes.add(n);
            }
            return nodes;
        }
        return null;
    }

    public Set<Node> moveUp(StateSpace state, int i, int j) {
        Peg peg = state.getGrid()[i][j];
        if (peg != null) {
            Set<Node> nodes = new LinkedHashSet<>();
            if (isUp(state, i, j)) {

                Peg[][] grid = state.getCloneGrid();

                    grid[i-1][j] = null;
                    grid[i-2][j] = grid[i][j];
                    grid[i][j] = null;

                StateSpace s = new StateSpace(grid);
                Node n = new Node(s);
                n.setMovePosition(new Position(i, j, i-2, j));
                nodes.add(n);
            }
            return nodes;
        }
        return null;
    }

    public Set<Node> moveDown(StateSpace state, int i, int j) {
        Peg peg = state.getGrid()[i][j];
        if (peg != null) {
            Set<Node> nodes = new LinkedHashSet<>();
            if (isDown(state, i, j)) {

                Peg[][] grid = state.getCloneGrid();

                    grid[i+1][j] = null;
                    grid[i+2][j] = grid[i][j];
                    grid[i][j] = null;

                StateSpace s = new StateSpace(grid);
                Node n = new Node(s);
                n.setMovePosition(new Position(i, j, i+2, j));
                nodes.add(n);
            }
            return nodes;
        }
        return null;
    }

    public Set<Node> getAllMoves(StateSpace state, int i, int j) {
        Set<Node> nodes = new LinkedHashSet<>();

        nodes.addAll(moveRight(state, i, j));
        nodes.addAll(moveLeft(state, i, j));
        nodes.addAll(moveUp(state, i, j));
        nodes.addAll(moveDown(state, i, j));

        nodes.removeAll(Collections.singleton(null));  //removing all null nodes that are returned from above moves
        return nodes;
    }
}
