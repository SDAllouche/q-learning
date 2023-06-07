package ma.enset.sequential;

import java.util.Random;

public class QLearning {

    private final double ALPHA=0.1;
    private final double GAMMA=0.9;
    private final double EPS=0.4;
    private final int MAZE_SIZE=3;
    private final int EPOCH =200;
    private final int ACTION_SIZE=4;
    private int [][] maze=new int[MAZE_SIZE][MAZE_SIZE];
    private double [][] qTable = new double[MAZE_SIZE*MAZE_SIZE][ACTION_SIZE];
    private int stateI;
    private int stateJ;

    private int[][]actions;

    public QLearning() {
        actions=new int[][]{
                {0,-1},
                {0,1},
                {1,0},
                {-1,0}
        };

        maze=new int[][]{
                {0,0,1},
                {0,-1,0},
                {0,0,0}
        };
    }

    public void resetState(){
        stateI = 2;
        stateJ = 0;
    }

    public void run(){
        int it = 0;
        int currentState;
        int nextState;
        int act,act1;
        while (it<EPOCH){
            resetState();
            while (!finished()){
                currentState = stateI*MAZE_SIZE + stateJ;
                act = chooseAction(0.4);
                nextState = executeAction(act);
                //System.out.println(stateI + " " + stateJ + " " + maze[stateI][stateJ]);
                act1=chooseAction(0);
                qTable[currentState][act]=qTable[currentState][act]+ALPHA *(maze[stateI][stateJ]+GAMMA*qTable[nextState][act1]-qTable[currentState][act]);
            }
            it++;
        }
        show();
    }

    public int chooseAction(double eps){
        Random random = new Random();
        double bestQ = 0;
        int act = 0;
        if(random.nextDouble() < eps){
            act = random.nextInt(ACTION_SIZE);
        }else{
            int st = stateI*MAZE_SIZE + stateJ;
            for (int i = 0; i < ACTION_SIZE; i++) {
                if(qTable[st][i] > bestQ){
                    bestQ = qTable[st][i];
                    act = i;
                }
            }
        }
        return act;
    }

    public int executeAction(int act){
        stateI = Math.max(0, Math.min(actions[act][0]+stateI, 2));
        stateJ = Math.max(0, Math.min(actions[act][1]+stateJ, 2));
        return stateI*MAZE_SIZE + stateJ;
    }

    private boolean finished(){
        return maze[stateI][stateJ]==1;
    }

    private void show(){
        for (double []row:qTable){
            System.out.println("[");
            for (double value:row){
                System.out.println(value+",");
            }
            System.out.println("]");
        }
    }

}
