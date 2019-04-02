import java.util.ArrayList;

class Node {
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

    Connection getConnection(String symbol){
        for(Connection connection: connections){
            if(connection.getSymbol().equals(symbol)){
                return connection;
            }
        }

        return null;
    }
    boolean getRejection(){
        return reject;
    }

    ArrayList<Connection> getConnections(){
        return connections;
    }
}