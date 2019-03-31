import java.util.ArrayList;

class Path {
    private int currentLength;
    private ArrayList<Node> visited;

    Path(){
        currentLength = 0;
        visited = new ArrayList<>();
    }

    void incrementLength(){
        currentLength++;
    }

    void setVisited(Node node){
        visited.add(node);
    }

    int getCurrentLength() {
        return currentLength;
    }

    boolean checkVisited(Node node){
        return visited.contains(node);
    }
}
