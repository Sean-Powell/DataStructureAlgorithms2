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
            int aConnectionIndex = random.nextInt(stateNumber);
            int bConnectionIndex = random.nextInt(stateNumber);
            int rejection = random.nextInt(2);
            Node state = new Node();

            if(rejection == 0){
                state.setReject(false);
            }else{
                state.setReject(true);
            }

            state.addConnection(aConnectionIndex, "a");
            state.addConnection(bConnectionIndex, "b");
            DFA.add(state);
        }

        BFS search = new BFS(startState, DFA);

        System.out.println("created " + stateNumber + " states");
        System.out.println("Start state is " + startState);

        ArrayList<Node> minimalDFA = hopcroftsAlgorithm.hopcrofts(DFA);
    }
}
