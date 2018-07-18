
public class HeuristicAverage extends Heuristic {
	private Heuristic1 heuristic1;
    private Heuristic2 Heuristic2;

    public HeuristicAverage() {
        heuristic1 = new Heuristic1();
        Heuristic2 = new Heuristic2();
    }

    @Override
    public int evaluate(StateSpace initialState, StateSpace goalState) {
        estimate = (heuristic1.evaluate(initialState, goalState)
                + Heuristic2.evaluate(initialState, goalState)) / 2;

        return estimate;
    }

}
