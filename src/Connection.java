class Connection {
    private String symbol = "";
    private int index;

    Connection(String symbol, int index){
        this.index = index;
        this.symbol = symbol;
    }

    String getSymbol(){
        return symbol;
    }

    int getIndex(){
        return index;
    }
}
