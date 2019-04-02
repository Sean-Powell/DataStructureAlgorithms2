import java.util.ArrayList;
import java.util.Collections;

class BFS {
    //create path object at start nodex
    //recursively traverse path
    //when reaching a new node check the path length against the global lowest count for that node
        //if lower replace else ignore
    //go until you visit the same node twice, then return
    //at end should have path length
    //find max -> depth of the tree

    ArrayList<Integer> shortestPathLength = new ArrayList<>();
    private int size;

    BFS(int startIndex, ArrayList<Node> DFA){
        for(int i = 0; i < DFA.size(); i++){ //populates the global path with -1 to indicate never visited
            shortestPathLength.add(-1);
        }

        size = DFA.size();
        System.out.println("Path length is " + DFA.size());
        createPath(DFA.get(startIndex), null, DFA);

        int debug_unreachable_count = 0;
        for (Integer aShortestPathLength : shortestPathLength) {
            if (aShortestPathLength == -1) {
                debug_unreachable_count++;
            }
        }

        System.out.println("Depth of tree is: " + Collections.max(shortestPathLength));
        System.out.println("There is " + debug_unreachable_count + " unreachable states ");
        System.out.println("-------------");
    }

    private void createPath(Node node, Path path, ArrayList<Node> dfa){
        if(path == null){
            path = new Path();
        }

        if(!path.checkVisited(node)){
            path.setVisited(node);
        }else{
            return;
        }

        int currentIndex = dfa.indexOf(node);

        if(currentIndex == -1){
            System.out.println("Node could not be found in the DFA");
            return;
        }

        if(shortestPathLength.get(currentIndex) == -1 || shortestPathLength.get(currentIndex) > path.getCurrentLength()){
            shortestPathLength.set(currentIndex, path.getCurrentLength());
        }

        path.incrementLength();
        ArrayList<Connection> connections = node.getConnections();
        for(Connection connection: connections){
            createPath(connection.getNode(), path, dfa);
        }
    }
}
