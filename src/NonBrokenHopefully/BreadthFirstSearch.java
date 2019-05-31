package NonBrokenHopefully;

import java.util.ArrayList;

class BreadthFirstSearch {
    private int[] shortestPath;
    ArrayList<Integer> Bfs(int[][] dfsa, int startIndex){
        shortestPath = new int[dfsa.length];
        for(int i = 0; i < shortestPath.length; i++){
            shortestPath[i] = -1;
        }

        System.out.println("Path length is " + dfsa.length);
        createPaths(startIndex, null, dfsa);

        int unreachableStatesCount = 0;

        ArrayList<Integer> toRemove = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < shortestPath.length; i++){
            int currentPathLength = shortestPath[i];
            if(currentPathLength > max){
                max = currentPathLength;
            }

            if(currentPathLength == -1){
                unreachableStatesCount++;
                toRemove.add(i);
            }
        }

        System.out.println("Depth of tree is " + max);
        System.out.println("There is " + unreachableStatesCount + " unreachable states");

        return toRemove;
    }

    private void createPaths(int index, Path path, int[][] dfsa){
        if(path == null){
            path = new Path();
        }

        if(!path.checkVisited(index)){

            path.addVisited(index);
        }else{
            return;
        }

        if(shortestPath[index] == -1 || shortestPath[index] > path.getCurrentLength()){
            shortestPath[index] = path.getCurrentLength();
        }

        path.incrementLength();
        if(dfsa[index][0] != -1){
            createPaths(dfsa[index][0], path, dfsa);
        }
        if(dfsa[index][1] != -1){
            createPaths(dfsa[index][1], path, dfsa);
        }


    }
}
