import java.util.ArrayList;

public class Node {
    private boolean reject;
    private ArrayList<Connection> connections;

    Node(){
        reject = false;
        connections = new ArrayList<>();
    }

    void setReject(boolean rejectionValue){
        reject = rejectionValue;
    }

    void addConnection(Node connectedTo, String symbol){
        connections.add(new Connection(symbol, connectedTo));
    }

    boolean getRejection(){
        return reject;
    }

    ArrayList<Connection> getConnections(){
        return connections;
    }
}