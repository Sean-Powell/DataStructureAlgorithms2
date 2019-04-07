import java.util.ArrayList;
import java.util.Random;

public class start {
    static HopcroftsAlgorithm hopcroftsAlgorithm = new HopcroftsAlgorithm();
    private static SetOperations<Integer> setOperations = new SetOperations<>();
    private static TestingStrings testingStrings = new TestingStrings();
    public static void main(String[] args){
        int min = 2;
        int max = 8;
        Random random = new Random();
        //generates a random number from 0-48 and then adds 16, this obtains a number from 16-64
        int stateNumber = random.nextInt((max - min) + 1) + min;
        int startStateIndex = random.nextInt(stateNumber);

        ArrayList<Node> DFA = new ArrayList<>();

        System.out.println("created " + stateNumber + " states");
        System.out.println("Start state is " + startStateIndex);
        System.out.println("-------------");
        for(int i = 0; i < stateNumber; i++){
            Node state = new Node();
            DFA.add(state);
        }

        for(int i = 0; i < stateNumber; i++){
            int aConnectionIndex = random.nextInt(stateNumber);
            int bConnectionIndex = random.nextInt(stateNumber);
            int rejection = random.nextInt(2);
            if(rejection == 0){
                DFA.get(i).setReject(false);
            }else{
                DFA.get(i).setReject(true);
            }

            DFA.get(i).addConnection(DFA.get(aConnectionIndex), "a");
            DFA.get(i).addConnection(DFA.get(bConnectionIndex), "b");

        }

        Node startState = DFA.get(startStateIndex);
        new BFS(startStateIndex, DFA);

        //TODO remove unreachable states
        ArrayList<Node> minimalDFA = hopcroftsAlgorithm.hopcroft(DFA);

        testingStrings.testStrings(testingStrings.createTestSets(), startState);

        //int newStartStateIndex = minimalDFA.indexOf(startState);
        //new BFS(newStartStateIndex, minimalDFA);
    }
}
