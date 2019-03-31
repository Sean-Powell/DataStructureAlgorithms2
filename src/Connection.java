class Connection {
    private String symbol;
    private Node connectedTo;

    Connection(String symbol, Node connectedTo){
        this.connectedTo = connectedTo;
        this.symbol = symbol;
    }

    String getSymbol(){
        return symbol;
    }

    Node getNode(){
        return connectedTo;
    }
}
