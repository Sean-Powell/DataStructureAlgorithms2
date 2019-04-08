package NonBrokenHopefully;

import java.util.ArrayList;
import java.util.Random;

public class newMain {
    public static void main(String[] args) {
        int max = 64, min = 16;
        Random random = new Random();
        int dfsaSize = random.nextInt((max - min) + 1) + min;
        int startState = random.nextInt(dfsaSize + 1);
        DFSA dfsa = new DFSA(dfsaSize, startState);
        dfsa.generateTransitions();

        BreadthFirstSearch bfs = new BreadthFirstSearch();
        ArrayList<Integer> unreachableStates = bfs.Bfs(dfsa.getTransitionTable(), startState);
        Hopcrofts hopcrofts = new Hopcrofts();
        hopcrofts.hopcroft(dfsa, dfsa.getStartState());
    }
}
