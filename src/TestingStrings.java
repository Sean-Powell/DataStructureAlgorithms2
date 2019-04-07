import java.util.ArrayList;
import java.util.Random;

class TestingStrings {
    //generate 100 random strings
    //length 0 - 128, empty string is valid
    //test if its rejected or accepted by the DFSA

    ArrayList<String> createTestSets(){
        ArrayList<String> testSets = new ArrayList<>();
        Random random = new Random();

        int maxStringLength = 128;
        for(int i = 0; i < 100; i++){
            int stringLength = random.nextInt(maxStringLength + 1);
            String newString = "";
            for(int j = 0; j < stringLength; j++){
                int x = random.nextInt(2);
                if(x == 1){
                    newString = newString.concat("a");
                }else{
                    newString = newString.concat("b");
                }
            }

            testSets.add(newString);
        }

        return testSets;
    }

    void testStrings(ArrayList<String> testStrings, Node startState){
        for(String string: testStrings){
            boolean result = testString(string, startState);
            String output = string + " :: ";
            if(result){
                output += "accepted";
            }else{
                output += "rejected";
            }

            System.out.println(output);
        }
    }

    private boolean testString(String test, Node startState){
        if(test.length() == 0){
            return startState.getRejection();
        }

        String[] splitString = test.split("(?!^)");
        Node currentNode = startState;
        for(String split: splitString){
            Connection connection = currentNode.getConnection(split);
            currentNode = connection.getNode();
            if(currentNode == null){
                return false;
            }
        }

        return currentNode.getRejection();
    }
}
