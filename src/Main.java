import java.util.*;

/**
 * @author geetikasharma
 *
 */
public class Main {
	private Map<String, Peg> gridPegs;
    private Map<String, Peg> goalPegs;
    private StateSpace stateInitial;
    private StateSpace stateFinal;
    
    public void init(int option){

        Node node;
        Strategy strategy;

        if(option == 1){
            createBfsInitialGameState();
            strategy = new BreadthFirst();
        }else if(option == 2){
            createInitialGameState();
        //	createBfsInitialGameState();
            strategy = new DepthFirst();
        }else if(option == 3){
            //createInitialGameState();
        	    createBfsInitialGameState();
            strategy = new Astar(new Heuristic1());
        }else if(option == 4){
            //createInitialGameState();
            createBfsInitialGameState();
            strategy = new Astar(new Heuristic2());
        }else if(option == 5){
            //createInitialGameState();
        		createBfsInitialGameState();
            strategy = new Astar(new HeuristicAverage());
        } else{
        		createInitialGameState();
            strategy = new DepthFirst();
        }
        createGoalState();
        stateInitial = new StateSpace(getGridPegs());
        stateFinal = new StateSpace(getGoalPegs());
        //stateInitial.print();
        //stateFinal.print();

        node = strategy.search(stateInitial, stateFinal);

        List<Node> paths = new ArrayList<>();

        if(node != null) {
            Node currentNode = node;
            while(currentNode != null) {
                paths.add(currentNode);
                currentNode = currentNode.getParent();
            }

            Collections.reverse(paths);
        }

        for (Node n : paths) {
            n.getState().print();
        }

        System.out.print("Number of moves needed: " + paths.size());
    }

    public void createGoalState() {

        goalPegs = new HashMap<String, Peg>();

        goalPegs.put("13", new Peg(1, 3));
    }

    public Map<String, Peg> getGoalPegs() {
        return goalPegs;
    }

    public void setGoalPegs(Map<String, Peg> goalPegs) {
        this.goalPegs = goalPegs;
    }

    public void createBfsInitialGameState(){

        gridPegs = new HashMap<String, Peg>();

       gridPegs.put("15", new Peg(1, 5));
       gridPegs.put("20", new Peg(2, 0));
       gridPegs.put("21", new Peg(2, 1));
       gridPegs.put("24", new Peg(2, 4));
       gridPegs.put("30", new Peg(3, 0));
       gridPegs.put("31", new Peg(3, 1));
       gridPegs.put("32", new Peg(3, 2));
       gridPegs.put("34", new Peg(3, 4));
       gridPegs.put("40", new Peg(4, 0));
       gridPegs.put("43", new Peg(4, 3));
       gridPegs.put("44", new Peg(4, 4));
       gridPegs.put("54", new Peg(5, 4));
       gridPegs.put("55", new Peg(5, 5));
       gridPegs.put("64", new Peg(6, 4));
    }

    public void createInitialGameState(){

        gridPegs = new HashMap<String, Peg>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if(isGridWithinBound(i, j) && isInitialPositionEmpty(i,j)){
                    String key = "" + i + j;
                    gridPegs.put(key, new Peg(i, j));
                }
            }
        }
    }

    public boolean isGridWithinBound(int i, int j) {

        if (((i == 0 || i == 1 || i == 5 || i == 6) && (j == 0 || j == 6)) || ((i == 0 || i == 6) && (j == 1 || j == 5))) {
            return false;

        } else {
            return true;
        }
    }

    public boolean isInitialPositionEmpty(int i, int j) {
        if (i == 2 && j == 3) {
            return false;
        } else {
            return true;
        }
    }

    public Map<String, Peg> getGridPegs() {
        return gridPegs;
    }

    public void setGridPegs(Map<String, Peg> gridPegs) {
        this.gridPegs = gridPegs;
    }

    public static void main(String[] args) {

        Main game = new Main();
        boolean running = true;
        int option = 0;

            System.out.println("Welcome to European Peg Solitaire! Enter option!");
            System.out.println("[1]: Breadth First Search");
            System.out.println("[2]: Depth First Search");
            System.out.println("[3]: A* Search: Heuristic 1");
            System.out.println("[4]: A* Search: Heuristic 2");
            System.out.println("[5]: A* Search: Average of the two");
            System.out.println("Press any other value to Exit!");
            Scanner in = new Scanner(System.in);
            System.out.print("Enter Option: ");
            option = in.nextInt();

            if (option >= 1 && option <= 5) game.init(option);
            else{
                System.exit(0);
            }
    }

}
