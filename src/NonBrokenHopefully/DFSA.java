package NonBrokenHopefully;

import java.util.Random;

class DFSA {
    private int[][] transitions;
    private int startState;

    DFSA(int size, int startState){
        this.startState = startState;
        transitions = new int[size][3];
    }

    void debugState(int[][] debug_transitions, int startState) {
        transitions = debug_transitions;
        this.startState =  startState;
    }

    int[][] getTransitionTable(){
        return transitions;
    }

    void generateTransitions(){
        Random random = new Random();
        for(int i = 0; i < transitions.length; i++){
            transitions[i][0] = random.nextInt(transitions.length);
            transitions[i][1] = random.nextInt(transitions.length);
            int acceptingChance = random.nextInt(101);
            if(acceptingChance <= 50){
                transitions[i][2] = 0;
            }else{
                transitions[i][2] = 1;
            }
        }

        for(int i = 0; i < transitions.length; i++){
            System.out.println(i + " - a: " + transitions[i][0] + ", b: " + transitions[i][1]);
        }
    }

    void setTransitions(int[][] transitions){
        this.transitions = transitions;
    }

    void setStartState(int startState){
        this.startState = startState;
    }


    int getStartState(){
        return startState;
    }


    int getTransition(int stateNumber, String transitionString){
        if(transitionString.equals("a")){
            return transitions[stateNumber][0];
        }else{
            return transitions[stateNumber][1];
        }
    }

    boolean getAccepting(int stateNumber){
        return transitions[stateNumber][2] == 1;
    }
}
