//import java.util.ArrayList;
//import java.util.ListIterator;
//
//
//class HopcroftsAlgorithm {
//    /*
//    P := {F, Q \ F}; Done
//    W := {F};
//    while (W is not empty) do:
//        choose and remove a set A from W
//        for each c in Σ do:
//            let X be the set of states for which a transition on c leads to a state in A
//            for each set Y in P for which X ∩ Y is nonempty and Y \ X is nonempty do:
//                replace Y in P by the two sets X ∩ Y and Y \ X
//                if Y is in W
//                     replace Y in W by the same two sets
//                else
//                     if |X ∩ Y| <= |Y \ X|
//                           add X ∩ Y to W
//                     else
//                           add Y \ X to W
//            end;
//        end;
//    end;
//    */
//
//    //todo when a node is removed, remove any references to it
//    private SetOperations<Node> setOperations = new SetOperations<>();
//
//    ArrayList<Node> hopcroft(ArrayList<Node> Q, Node startState) {
//        ArrayList<Node> F = new ArrayList<>();
//        for (Node node : Q) {
//            if (node.getRejection()) {
//                F.add(node);
//            }
//        }
//
//        ArrayList<ArrayList<Node>> P = new ArrayList<>();
////            P := {F, Q \ F}; Done
//
//        P.add(F);
//        P.add(setOperations.division(Q, F));
////            W := {F};
//        ArrayList<ArrayList<Node>> W = new ArrayList<>();
//        W.add(F);
////            while (W is not empty) do:
//        while (!W.isEmpty()) {
//
////                choose and remove a set A from W
//            ArrayList<Node> A = W.get(0);
//            W.remove(0);
//            ArrayList<Node> X = obtainX(Q, A, "a");
////                for each c in Σ do:
////            let X be the set of states for which a transition on c leads to a state in A
//
//
//            setDifferenceCalculation(P, W, X);
//
//            X = obtainX(Q, A, "b");
//            setDifferenceCalculation(P, W, X);
////
////                    for each set Y in P for which X ∩ Y is nonempty and Y \ X is nonempty do:
////                        replace Y in P by the two sets X ∩ Y and Y \ X
////                        if Y is in W
////                            replace Y in W by the same two sets
////                        else
////                            if |X ∩ Y| <= |Y \ X|
////                                add X ∩ Y to W
////                            else
////                                add Y \ X to W
//        }
//
//        ArrayList<Node> MinimalDFSA = new ArrayList<>();
//        Node minimalStartNode;
//
//        for(ArrayList<Node> partition: P){
//            Node partititionNode = new Node();
//            for(Node node: partition){
//                if(node.getRejection()){
//                    partititionNode.setRejection(false);
//                }
//            }
//
//            if(partition.contains(startState)){
//                minimalStartNode = partititionNode;
//            }
//
//            MinimalDFSA.add(partititionNode);
//        }
//
//        for(int i = 0; i < P.size(); i++){
//            ArrayList<Node> currentPartition = P.get(i);
//            Node node = currentPartition.get(0);
//
//            Connection conA = node.getConnection("a");
//            Connection conB = node.getConnection("b");
//
//            Node connectedToA = conA.getNode();
//            Node connectedToB = conB.getNode();
//
//            for(int j = 0; j < P.size(); j++){
//                ArrayList<Node> currentP = P.get(j);
//                if(currentP.contains(connectedToA)){
//                    MinimalDFSA.get(i).addConnection(MinimalDFSA.get(j), "a");
//                }
//
//                if (currentP.contains(connectedToB)) {
//                    MinimalDFSA.get(i).addConnection(MinimalDFSA.get(j), "b");
//                }
//            }
//        }
//
//        return null;
//    }
////
////    private void setDifferenceCalculation(ArrayList<ArrayList<Node>> p, ArrayList<ArrayList<Node>> w, ArrayList<Node> x) {
////        for (ListIterator<ArrayList<Node>> iterator = p.listIterator(); iterator.hasNext(); ) {
////            ArrayList<Node> Y = iterator.next();
////            ArrayList<Node> intersection = setOperations.intersection(x, Y);
////            ArrayList<Node> division = setOperations.division(Y, x);
////
////            if (!intersection.isEmpty() && !division.isEmpty()) {
////                iterator.remove();
////                iterator.add(intersection);
////                iterator.add(division);
////                if (w.contains(Y)) {
////                    w.remove(Y);
////                    w.add(intersection);
////                    w.add(division);
////                } else {
////                    if (intersection.size() <= division.size()) {
////                        w.add(intersection);
////                    } else {
////                        w.add(division);
////                    }
////                }
////            }
////        }
////    }
//
//
//    private ArrayList<Node> obtainX(ArrayList<Node> Q, ArrayList<Node> A, String symbol) {
//        ArrayList<Node> X = new ArrayList<>();
//
//        for (Node node : Q) {
//            Connection con = node.getConnection(symbol);
//            Node connectionNode = con.getNode();
//            if (A.contains(connectionNode)) {
//                X.add(connectionNode);
//            }
//        }
//
//        return X;
//    }
//
////    ArrayList<Node> hopcrofts(ArrayList<Node> DFA) {
////        ArrayList<ArrayList<Node>> P = new ArrayList<>();
////        ArrayList<Node> F = new ArrayList<>();
////        for (Node node : DFA) {
////            if (!node.getRejection()) {
////                F.add(node);
////            }
////        }
////
////        ArrayList<Node> Q = setOperations.division(DFA, F);
////
////        P.add(F);
////        P.add(Q);
////
////        ArrayList<ArrayList<Node>> W = new ArrayList<>();
////        W.add(F);
////
////        while (!W.isEmpty()) {
////            ArrayList<Node> A = W.get(0);
////            W.remove(A);
////
////            ArrayList<Node> X = new ArrayList<>();
////
////            for (Node node : DFA) {
////                for (Connection connection : node.getConnections()) {
////                    Node tempNode = connection.getNode();
////
////                    if (A.contains(tempNode)) {
////                        X.add(tempNode);
////                    }
////                }
////            }
////
////            for (int i = 0; i < P.size(); i++) {
////                ArrayList<Node> Y = P.get(i);
////                ArrayList<Node> intersection = setOperations.intersection(X, Y);
////                ArrayList<Node> division = setOperations.division(Y, X);
////
////                if (!intersection.isEmpty() && !division.isEmpty()) {
////                    P.remove(Y);
////                    P.add(intersection);
////                    P.add(division);
////
////                    if (W.contains(Y)) {
////                        W.remove(Y);
////                        W.add(division);
////                        W.add(intersection);
////                    } else {
////                        if (intersection.size() <= division.size()) {
////                            W.add(intersection);
////                        } else {
////                            W.add(division);
////                        }
////                    }
////                }
////            }
////        }
////
////        //todo find new start state
////        ArrayList<Node> M = new ArrayList<>();
////        for(ArrayList<Node> list: P){
////            M.addAll(list);
////        }
////
////        return M;
//}
//
