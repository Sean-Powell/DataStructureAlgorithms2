package NonBrokenHopefully;

import java.util.ArrayList;
import java.util.ListIterator;

public class Hopcrofts {
    DFSA hopcroft(DFSA dfsa, int startState){
        ArrayList<Integer> F = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();
        ArrayList<ArrayList<Integer>> P = new ArrayList<>();
        ArrayList<ArrayList<Integer>> W = new ArrayList<>();

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

            ArrayList<Integer> x = obtainX(transitionTable, A, "a");
            //calc set difference
            setDifferenceCalculation(P, W, x);
            x = obtainX(transitionTable, A, "b");
            setDifferenceCalculation(P, W, x);
        }
        return null;
    }

    private ArrayList<Integer> obtainX(int[][] transitionTable, ArrayList<Integer> A, String symbol){
        ArrayList<Integer> X = new ArrayList<>();

        int selector = 0;
        if(symbol.equals("b")){
            selector = 1;
        }

        for (int[] ints : transitionTable) {
            X.add(ints[selector]);
        }

        return X;
    }

    private void setDifferenceCalculation(ArrayList<ArrayList<Integer>> p, ArrayList<ArrayList<Integer>> w, ArrayList<Integer> x){
        for (ListIterator<ArrayList<Integer>> iterator = p.listIterator(); iterator.hasNext(); ) {
            ArrayList<Integer> Y = iterator.next();
            ArrayList<Integer> intersection = intersection(x, Y);
            ArrayList<Integer> division = division(Y, x);

            if (!intersection.isEmpty() && !division.isEmpty()) {
                iterator.remove();
                iterator.add(intersection);
                iterator.add(division);
                if (w.contains(Y)) {
                    w.remove(Y);
                    w.add(intersection);
                    w.add(division);
                } else {
                    if (intersection.size() <= division.size()) {
                        w.add(intersection);
                    } else {
                        w.add(division);
                    }
                }
            }
        }
    }

    private ArrayList<Integer> intersection(ArrayList<Integer> x, ArrayList<Integer> y){
        ArrayList<Integer> intersected = new ArrayList<>();
        ArrayList<Integer> tempNodes = new ArrayList<>(x);
        for (int i = 0; i < tempNodes.size(); i++) {
            Integer aNode = tempNodes.get(i);
            boolean found = false;
            for (Integer bNode : y) {
                if (aNode.equals(bNode)) {
                    found = true;
                }
            }

            if (found) {
                tempNodes.remove(aNode);
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
}
