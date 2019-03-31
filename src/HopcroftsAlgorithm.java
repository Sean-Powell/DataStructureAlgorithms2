import jdk.nashorn.internal.ir.WhileNode;

import java.util.ArrayList;

public class HopcroftsAlgorithm {
    /*
    P := {F, Q \ F}; Done
    W := {F};
    while (W is not empty) do
        choose and remove a set A from W
        for each c in Σ do
              let X be the set of states for which a transition on c leads to a state in A
            for each set Y in P for which X ∩ Y is nonempty and Y \ X is nonempty do
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

    https://www.reddit.com/r/learnprogramming/comments/8jfom9/hopcrofts_dfa_minimization_algorithm_using_java/
     */
    ArrayList<Node> hopcrofts(ArrayList<Node> DFA){
        ArrayList<ArrayList<Node>> P = new ArrayList<>();
        ArrayList<Node> F = new ArrayList<>();
        for(Node node: DFA){
            if(!node.getRejection()){
                F.add(node);
            }
        }

        ArrayList<Node> Q = setDivision(F, DFA);

        P.add(F);
        P.add(Q);

        while(!F.isEmpty()){
            Node node = F.get(0);
            F.remove(node);


        }
        return null;
    }

    //intersection function
    private ArrayList<Node> setInterscetion(ArrayList<Node> setA, ArrayList<Node> setB){
        ArrayList<Node> intersected = new ArrayList<>();
        for(Node aNode: setA){
            boolean found = false;
            for(Node bNode: setB){
                if(aNode == bNode){
                    found = true;
                }
            }

            if(found){
                setA.remove(aNode);
            }
        }

        return intersected;
    }

    //set division function

    private ArrayList<Node> setDivision(ArrayList<Node> setA, ArrayList<Node> setB){
        ArrayList<Node> division = new ArrayList<>();
        for(Node bNode: setB){
            boolean found = false;
            for(Node aNode: setA){
                if(bNode == aNode){
                    found = true;
                }
            }

            if(!found){
                division.add(bNode);
            }
        }

        return division;
    }
}
