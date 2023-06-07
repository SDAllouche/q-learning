package ma.enset.sequential;

public class QLearning {

    private final double ALPHA=0.1;
    private final double GAMMA=0.9;
    private final double EPS=0.4;
    private final int MAZE_SIZE=3;
    private final int EPOCH =200;
    private final int ACTION_SIZE=4;
    private int [][] maze=new int[MAZE_SIZE][MAZE_SIZE];
    private double [][] qTable = new double[MAZE_SIZE*MAZE_SIZE][ACTION_SIZE];

    private int[][]actions;

    public QLearning() {
        actions=[];
    }
}
