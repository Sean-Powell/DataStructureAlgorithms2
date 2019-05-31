package NonBrokenHopefully;

import java.util.ArrayList;
import java.util.Random;

public class newMain {
    //todo surround bfs with try catch block with a debug statement in the catch block to try understand the error.
    public static void main(String[] args) {


        int max = 9, min = 8;

        Random random = new Random();
        int dfsaSize = random.nextInt((max - min) + 1) + min;
        int startState = random.nextInt(dfsaSize + 1);

        Hopcrofts hopcrofts = new Hopcrofts();
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        DFSA dfsa = new DFSA(dfsaSize, startState);

        dfsa.generateTransitions();
        System.out.println("-----");
        ArrayList<Integer> unreachableStates = bfs.Bfs(dfsa.getTransitionTable(), dfsa.getStartState());
        System.out.println("-----");
        dfsa = removeUnreachableStates(dfsa, unreachableStates);
        DFSA m = hopcrofts.hopcroft(dfsa, dfsa.getStartState());
        System.out.println("-----");
        ArrayList<Integer> mBfs = bfs.Bfs(m.getTransitionTable(), m.getStartState());

        System.out.println("-----");
        ArrayList<String> strings = generateTestStrings();
        testStrings(m, strings);
        System.out.println("-----");
        Tarjans t = new Tarjans();
        ArrayList<ArrayList<Integer>> sccs = t.tarjans(m);
        int smallestSCCS = Integer.MAX_VALUE;
        int largestSCCS = Integer.MIN_VALUE;


        System.out.println("The number of strongly connected components is " + sccs.size());
        for (ArrayList<Integer> list : sccs) {
            int size = list.size();
            if (size > largestSCCS) {
                largestSCCS = size;
            }

            if (size < smallestSCCS) {
                smallestSCCS = size;
            }
        }

        System.out.println("The size of smallest strongly connected component is " + smallestSCCS);
        System.out.println("The size of largest strongly connected component is " + largestSCCS);
    }


    private static DFSA removeUnreachableStates(DFSA dfsa, ArrayList<Integer> unreachableStates) {
        int[][] transitions = dfsa.getTransitionTable();
        int startState = dfsa.getStartState();

        int size = transitions.length - unreachableStates.size();
        int[][] newTransitionTable = new int[size][3];
        int removedCounter = 0;
        for (int i = 0; i < transitions.length; i++) {
            if (!unreachableStates.contains(i)) {
                newTransitionTable[i - removedCounter] = transitions[i];
            } else {
                removedCounter++;
            }
        }


        for (int a : unreachableStates) {
            for (int i = 0; i < newTransitionTable.length; i++) {
                if (newTransitionTable[i][0] > a) {
                    newTransitionTable[i][0] = newTransitionTable[i][0] - 1;
                }
                if (newTransitionTable[i][1] > a) {
                    newTransitionTable[i][1] = newTransitionTable[i][1] - 1;
                }
            }

            if (startState > a) {
                startState = startState - 1;
            }
        }

        dfsa.setTransitions(newTransitionTable);
        dfsa.setStartState(startState);

        return dfsa;
    }

    private static ArrayList<String> generateTestStrings() {
        ArrayList<String> testStrings = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < random.nextInt(129); j++) {
                int k = random.nextInt(101);
                if (k <= 50) {
                    sb.append("a");
                } else {
                    sb.append("b");
                }
            }
            testStrings.add(sb.toString());
        }

        return testStrings;
    }

    private static void testStrings(DFSA dfsa, ArrayList<String> testStrings){
        int[][] transitionTable = dfsa.getTransitionTable();
        int counter = 1;
        for(String string: testStrings){
            int currentState = dfsa.getStartState();
            String[] characters = string.split("");
            for(String c: characters){
                if (currentState != -1) {
                    if (c.equals("a")) {
                        currentState = transitionTable[currentState][0];
                    } else {
                        currentState = transitionTable[currentState][1];
                    }
                }
            }

            System.out.print(counter + ": " + string);
            if(transitionTable[currentState][2] == 1 && currentState != -1){
                System.out.println("::Accepted");
            }else{
                System.out.println("::Rejected");
            }
            counter++;
        }
    }
}

