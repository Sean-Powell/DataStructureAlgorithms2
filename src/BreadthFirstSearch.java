
import java.util.ArrayList;

class BreadthFirstSearch {
    private int[] shortestPath;

    ArrayList<Integer> Bfs(int[][] transitionTable, int startIndex) {
        shortestPath = new int[transitionTable.length];
        for (int i = 0; i < shortestPath.length; i++) {
            shortestPath[i] = -1;
        }

        createPaths(startIndex, null, transitionTable);

        ArrayList<Integer> unreachable = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < shortestPath.length; j++) {
            int currentPathLength = shortestPath[j];
            if (currentPathLength > max) {
                max = currentPathLength;
            }

            if(currentPathLength == -1){
                unreachable.add(j);
            }
        }

        System.out.println("Depth of tree is " + max);
        System.out.println("The dfsa has " + transitionTable.length + " states");

        return unreachable;
    }

    private void createPaths(int index, Path path, int[][] transitionTable) {
        if (path == null) {
            path = new Path();
        }

        if (!path.checkVisited(index)) {
            path.addVisited(index);
        } else {
            return;
        }

        if (shortestPath[index] == -1 || shortestPath[index] > path.getCurrentLength()) {
            shortestPath[index] = path.getCurrentLength();
        }

        path.incrementLength();
        if (transitionTable[index][0] != -1) {
            createPaths(transitionTable[index][0], path, transitionTable);
        }
        if (transitionTable[index][1] != -1) {
            createPaths(transitionTable[index][1], path, transitionTable);
        }
    }
}
