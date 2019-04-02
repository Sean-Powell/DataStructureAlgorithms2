import java.util.ArrayList;

public class SetOperations<T> {
    //intersection function
    ArrayList<T> intersection(ArrayList<T> setA, ArrayList<T> setB) {
        ArrayList<T> intersected = new ArrayList<>();
        ArrayList<T> tempNodes = new ArrayList<>(setA);
        for (int i = 0; i < tempNodes.size(); i++) {
            T aNode = tempNodes.get(i);
            boolean found = false;
            for (T bNode : setB) {
                if (aNode == bNode) {
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

    //set division function
    //setA \ setB
    ArrayList<T> division(ArrayList<T> setA, ArrayList<T> setB) {
        ArrayList<T> division = new ArrayList<>();
        for (T aNode : setA) {
            boolean found = false;
            for (T bNode : setB) {
                if (bNode == aNode) {
                    found = true;
                }
            }

            if (!found) {
                division.add(aNode);
            }
        }

        return division;
    }

    private boolean Subset(ArrayList<T> setA, ArrayList<T> setB){
        return setB.containsAll(setA);
    }
}
