import java.util.ArrayList;
import java.util.Random;

public class start {
    static HopcroftsAlgorithm hopcroftsAlgorithm = new HopcroftsAlgorithm();

    public static void main(String[] args){
        int min = 16;
        int max = 64;
        Random random = new Random();
        //generates a random number from 0-48 and then adds 16, this obtains a number from 16-64
        int stateNumber = random.nextInt((max - min) + 1) + min;
        int startState = random.nextInt(stateNumber);
        ArrayList<Node> DFA = new ArrayList<>();

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

        BFS search = new BFS(startState, DFA);

        System.out.println("created " + stateNumber + " states");
        System.out.println("Start state is " + startState);

        ArrayList<Node> minimalDFA = hopcroftsAlgorithm.hopcrofts(DFA);
    }
}
