package NonBrokenHopefully;

import java.util.ArrayList;
import java.util.ListIterator;

class Hopcrofts {
    private ArrayList<Integer> F = new ArrayList<>();
    private ArrayList<Integer> R = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> P = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> W = new ArrayList<>();

    DFSA hopcroft(DFSA dfsa, int startState){
        int[][] transitionTable = dfsa.getTransitionTable();

        for(int i = 0; i < transitionTable.length; i++){
            if(dfsa.getAccepting(i)){
                F.add(i);
            }else{
                R.add(i);
            }
        }

        P.add(F);
        P.add(R);

        W.add(F);

        while(!W.isEmpty()){
            ArrayList<Integer> A = W.get(0);
            W.remove(0);

            ArrayList<Integer> x = obtainX(transitionTable, A);
            //calc set difference
            setDifferenceCalculation(x);
//            x = obtainX(transitionTable, A, "b");
//            setDifferenceCalculation(P, W, x);
        }

        int[][] newTransitionTable = createTransitionTable(P, dfsa.getTransitionTable());
        int newStartState = findStartState(P, startState);
        DFSA m = new DFSA(newTransitionTable.length, newStartState);
        m.setTransitions(newTransitionTable);

        return m;
    }

    private ArrayList<Integer> obtainX(int[][] transitionTable, ArrayList<Integer> A){
        ArrayList<Integer> X = new ArrayList<>();

//        int selector = 0;
//        if(symbol.equals("b")){
//            selector = 1;
//        }

        for (int i = 0; i < transitionTable.length; i++){
            if(A.contains(transitionTable[i][0]) || A.contains(transitionTable[i][1])){
                X.add(i);
            }
        }

        return X;
    }

    private void setDifferenceCalculation(ArrayList<Integer> x){
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for (ListIterator<ArrayList<Integer>> iterator = P.listIterator(); iterator.hasNext(); ) {
            ArrayList<Integer> y = iterator.next();
            ArrayList<Integer> intersection = intersection(x, y);
            ArrayList<Integer> division = division(y, x);

            if (!intersection.isEmpty() && !division.isEmpty()) {
                iterator.remove();
                P.remove(y);
                temp.add(intersection);
                temp.add(division);

                if (W.contains(y)) {
                    W.remove(y);
                    W.add(intersection);
                    W.add(division);
                } else {
                    if (intersection.size() <= division.size()) {
                        W.add(intersection);
                    } else {
                        W.add(division);
                    }
                }
            }
        }

        P.addAll(temp);
    }


    private ArrayList<Integer> intersection(ArrayList<Integer> x, ArrayList<Integer> y){
        ArrayList<Integer> intersected = new ArrayList<>();
        ArrayList<Integer> tempNodes = new ArrayList<>(x);
        for (Integer aNode : tempNodes) {
            boolean found = false;
            for (Integer bNode : y) {
                if (aNode.equals(bNode)) {
                    found = true;
                }
            }

            if (found) {
                intersected.add(aNode);
            }
        }

        return intersected;
    }

    private ArrayList<Integer> division(ArrayList<Integer> x, ArrayList<Integer> y){
        ArrayList<Integer> division = new ArrayList<>();
        for (Integer aNode : x) {
            boolean found = false;
            for (Integer bNode : y) {
                if (bNode.equals(aNode)) {
                    found = true;
                }
            }

            if (!found) {
                division.add(aNode);
            }
        }

        return division;
    }

    //todo try fix this, then hopcroft works correctly
    private int[][] createTransitionTable(ArrayList<ArrayList<Integer>> P, int[][] originalTransitions){ //i don't think this works correctly
        int[][] transitionTable = new int[P.size()][3];

        for(int i = 0; i < P.size(); i++){
            ArrayList<Integer> list = P.get(i);
            int newATransition = -1;
            int newBTransition = -1;
            for(int j = 0; j < list.size(); j++){
                int aTransition = originalTransitions[j][0];
                int bTransition = originalTransitions[j][1];

                for(int k = 0; k < P.size(); k++){
                    ArrayList<Integer> list_b = P.get(k);
                    if(list_b.contains(aTransition)){
                        newATransition = k;
                    }

                    if(list_b.contains(bTransition)){
                        newBTransition = k;
                    }

                    transitionTable[k][0] = newATransition;
                    transitionTable[k][1] = newBTransition;

                    for(Integer integer: list_b){
                        if(originalTransitions[integer][2] == 1) {
                            transitionTable[k][2] = 1;
                        }else{
                            transitionTable[k][2] = 0;
                        }
                    }
                }
            }
        }

        return transitionTable;
    }

    private int findStartState(ArrayList<ArrayList<Integer>> P, int startState){
        for(int i = 0; i < P.size(); i++){
            ArrayList<Integer> list = P.get(i);
            if(list.contains(startState)){
                return i;
            }
        }

        System.err.println("Could not find start state in Hopcroft minimized DFSA");
        return 0;
    }
}
