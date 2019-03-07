import java.util.ArrayList;

public class Path {
    private int currentLength;
    private ArrayList<Integer> visited;

    Path(int size){
        currentLength = 0;
        visited = new ArrayList<>();
        for(int i = 0; i < size; i++){
            visited.add(0);
        }
    }

    void incrementLength(){
        currentLength++;
    }

    void setVisited(int index){
        visited.set(index, 1);
    }

    public int getCurrentLength() {
        return currentLength;
    }

    boolean checkVisited(int index){
        return visited.get(index) == 1;
    }
}
