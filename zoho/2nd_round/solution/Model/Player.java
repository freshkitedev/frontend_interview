package Model;

//class representing a player
public class Player {
    private String name;
    private char symbol;

    //constructor for initializing a player with a name and symbol
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    //getter for player name and symbol
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}