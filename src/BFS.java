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
        createPath(startIndex, null, DFA);

        for(int i = 0; i < shortestPathLength.size(); i++){
            System.out.println((i + 1) + ": depth = " + shortestPathLength.get(i));
        }

        System.out.println("Depth of tree is: " + Collections.max(shortestPathLength));
    }

    private void createPath(int currentIndex, Path path, ArrayList<Node> dfa){
        if(path == null){
            path = new Path(size);
        }

        if(!path.checkVisited(currentIndex)){
            path.setVisited(currentIndex);
        }else{
            return;
        }

        if(shortestPathLength.get(currentIndex) == -1 || shortestPathLength.get(currentIndex) >= path.getCurrentLength()){
            shortestPathLength.set(currentIndex, path.getCurrentLength());
        }

        path.incrementLength();
        Node currentNode = dfa.get(currentIndex);
        ArrayList<Connection> connections = currentNode.getConnections();
        for(Connection connection: connections){
            createPath(connection.getIndex(), path, dfa);
        }
    }
}
