import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.List;

/**
 * 
 */

/**
 * @author geetikasharma
 *
 */
public class ProductionSystem {
    private Move move;

    public ProductionSystem() {
        move = new Move();
    }

    public Set<Node> expand(Node node, Map<String, Node> visitedStates) {
        Set<Node> nodes = new LinkedHashSet<>();

        if (node != null) {

            StateSpace state = node.getState();
            Peg[][] stateGrid = state.getGrid();

            for (int i = 0; i < state.getGrid().length; i++) {
                for (int j = 0; j < state.getGrid().length; j++) {
                    if (stateGrid[i][j] != null) {
                        nodes.addAll(getFilteredNodes(node, state, i, j, visitedStates));
                    }
                }
            }
        }
        return nodes;
    }

    public Set<Node> getFilteredNodes(Node parentNode, StateSpace state, int i, int j, Map<String, Node> visitedStates) {
        Set<Node> moves = new LinkedHashSet<>();
        Set<Node> allPossibleMoves = move.getAllMoves(state, i, j);

        for (Node node : allPossibleMoves) {
            if (visitedStates.get(node.getState().toString()) == null) {

                Node.increaseNodeCount();
                node.setParent(parentNode);
                node.setParentIndex(parentNode.getIndex());
                node.setIndex(Node.getNodeCount());
                moves.add(node);

            }
        }
        return moves;
    }
}
