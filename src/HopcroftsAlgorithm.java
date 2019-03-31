import java.util.ArrayList;

class HopcroftsAlgorithm {
    /*
    P := {F, Q \ F}; Done
    W := {F};
    while (W is not empty) d:
        choose and remove a set A from W
        for each c in Σ do:
            let X be the set of states for which a transition on c leads to a state in A
            for each set Y in P for which X ∩ Y is nonempty and Y \ X is nonempty do:
                replace Y in P by the two sets X ∩ Y and Y \ X
                if Y is in W
                     replace Y in W by the same two sets
                else
                     if |X ∩ Y| <= |Y \ X|
                           add X ∩ Y to W
                     else
                           add Y \ X to W
            end;
        end;
    end;
    */

    ArrayList<Node> hopcrofts(ArrayList<Node> DFA) {
        ArrayList<ArrayList<Node>> P = new ArrayList<>();
        ArrayList<Node> F = new ArrayList<>();
        for (Node node : DFA) {
            if (!node.getRejection()) {
                F.add(node);
            }
        }

        ArrayList<Node> Q = setDivision(F, DFA);

        P.add(F);
        P.add(Q);

        ArrayList<ArrayList<Node>> W = new ArrayList<>();
        W.add(F);

        while (!W.isEmpty()) {
            ArrayList<Node> A = W.get(0);
            W.remove(A);

            ArrayList<Node> X = new ArrayList<>();

            for (Node node : DFA) {
                for (Connection connection : node.getConnections()) {
                    Node tempNode = connection.getNode();

                    if (A.contains(tempNode)) {
                        X.add(tempNode);
                    }
                }
            }

            for (int i = 0; i < P.size(); i++) {
                ArrayList<Node> Y = P.get(i);
                ArrayList<Node> intersection = setIntersection(X, Y);
                ArrayList<Node> division = setDivision(Y, X);

                if (!intersection.isEmpty() && !division.isEmpty()) {
                    P.remove(Y);
                    P.add(intersection);
                    P.add(division);

                    if (W.contains(Y)) {
                        W.remove(Y);
                        W.add(division);
                        W.add(intersection);
                    } else {
                        if (intersection.size() <= division.size()) {
                            W.add(intersection);
                        } else {
                            W.add(division);
                        }
                    }
                }
            }
        }

        return null;
    }

    //intersection function
    private ArrayList<Node> setIntersection(ArrayList<Node> setA, ArrayList<Node> setB) {
        ArrayList<Node> intersected = new ArrayList<>();
        ArrayList<Node> tempNodes = new ArrayList<>(setA);
        for (int i = 0; i < tempNodes.size(); i++) {
            Node aNode = tempNodes.get(i);
            boolean found = false;
            for (Node bNode : setB) {
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
    private ArrayList<Node> setDivision(ArrayList<Node> setA, ArrayList<Node> setB) {
        ArrayList<Node> division = new ArrayList<>();
        for (Node aNode : setA) {
            boolean found = false;
            for (Node bNode : setB) {
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
}
