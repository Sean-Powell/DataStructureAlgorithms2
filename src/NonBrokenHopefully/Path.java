package NonBrokenHopefully;

import java.util.ArrayList;

public class Path {
    private int currentLength;
    private ArrayList<Integer>  visited;

    Path(){
        currentLength = 0;
        visited = new ArrayList<>();
    }

    void incrementLength(){
        currentLength++;
    }

    void addVisited(int index){
        visited.add(index);
    }

    public int getCurrentLength() {
        return currentLength;
    }

    boolean checkVisited(int index){
        return visited.contains(index);
    }
}
