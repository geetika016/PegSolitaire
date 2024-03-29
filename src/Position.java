public class Position {
	private int i;
    private int j;
    private int i1;
    private int j1;

    public Position(int i, int j, int i1, int j1) {
        this.i = i;
        this.j = j;
        this.i1 = i1;
        this.j1 = j1;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public int getJ1() {
        return j1;
    }

    public void setJ1(int j1) {
        this.j1 = j1;
    }

    public String toString() {
        return i + "," + j + " -> " + i1 + "," + j1;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Position position = (Position) obj;

        return position.i == i && position.j == j && position.i1 == i1 && position.j1 == j1;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}
