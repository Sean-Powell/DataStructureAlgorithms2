package NonBrokenHopefully;

import java.util.ArrayList;
import java.util.Stack;

class Tarjans {
    private int[][] tarjansLookupTable;
    private int[][] transitionTable;
    private int smallestUnusedIndex = 0;
    private Stack<Integer> stack = new Stack<>();
    private ArrayList<ArrayList<Integer>> StrongConnectedComponents;

    ArrayList<ArrayList<Integer>> tarjans(DFSA dfsa){
        StrongConnectedComponents = new ArrayList<>();
        transitionTable = dfsa.getTransitionTable();
        tarjansLookupTable = new int[transitionTable.length][3];

        for(int i = 0; i < tarjansLookupTable.length; i++){
            setIndex(i, -1);
            setLowLink(i, -1);
            setOnStack(i, false);
        }

        for(int i = 0; i < tarjansLookupTable.length; i++){
            if(getIndex(i) == -1){
                strongConnect(i);
            }
        }

        return StrongConnectedComponents;
    }

    private void strongConnect(int index){
        setIndex(index, smallestUnusedIndex); //index set
        setLowLink(index, smallestUnusedIndex); // lowlink set
        smallestUnusedIndex++;

        stack.push(index);
        setOnStack(index, true); //on stack is set to true


        for(int i = 0; i < 2; i++){
            int connectedVertex = transitionTable[index][i];
            if (connectedVertex != -1) {
                if (getIndex(connectedVertex) == -1) {
                    //vertex has not been visited before
                    strongConnect(connectedVertex);
                    setLowLink(index, Math.min(getLowLink(index), getLowLink(connectedVertex)));
                } else if (getOnStack(index) == 1) {
                    setLowLink(index, Math.min(getLowLink(index), getIndex(connectedVertex)));
                }
            }
        }


        if(getLowLink(index) == getIndex(index)){
            ArrayList<Integer> StrongConnectedComponent = new ArrayList<>();
            int w;
            do{
                w = stack.pop();
                setOnStack(w, false);
                StrongConnectedComponent.add(w);
            }while(w != index);
            StrongConnectedComponents.add(StrongConnectedComponent);
        }
    }

    private int getIndex(int index){
        return tarjansLookupTable[index][0];
    }

    private int getLowLink(int index){
        return tarjansLookupTable[index][1];
    }

    private int getOnStack(int index){
        return tarjansLookupTable[index][2];
    }

    private void setIndex(int index, int value){
        tarjansLookupTable[index][0] = value;
    }

    private void setLowLink(int index, int value){
        tarjansLookupTable[index][1] = value;
    }

    private void setOnStack(int index, boolean bool){
        if(bool){
            tarjansLookupTable[index][2] = 1;
        }else{
            tarjansLookupTable[index][2] = 0;
        }
    }
}
