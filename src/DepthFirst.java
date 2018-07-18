import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author geetikasharma
 *
 */
public class DepthFirst implements Strategy{
    private Stack<Node> nodeList = new Stack<>();
    private Map<String, Node> visitedStates = new LinkedHashMap<>();
    private ProductionSystem prodSystem;


    public Node search(StateSpace stateInitial, StateSpace stateFinal) {
        // TODO Auto-generated method stub
        if (stateInitial != null) {

            prodSystem = new ProductionSystem();

            Node node = new Node(stateInitial);
            nodeList.push(node);

            return treeSearch(stateFinal);
        }
        return null;
    }

    private Node treeSearch(StateSpace stateFinal) {
        // TODO Auto-generated method stub
        while (!nodeList.isEmpty()) {
            Node node = nodeList.pop();

            if (visitedStates.get(node.getState().toString()) == null) {
                visitedStates.put(node.getState().toString(), node);


                if (node.getState().toString().equals(stateFinal.toString())) {
                    //node.getState().print();
                    System.out.println("Number of Nodes created: " + (node.getIndex()));
                    System.out.println("Reached Goal State!");
                    return node;
                }else {

                    for (Node n : prodSystem.expand(node, visitedStates)) nodeList.push(n);
                }

                }
            }
            return null;
    }
}
