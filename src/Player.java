/**
 * A player in an Omok game. It holds the name of the player and
 * can be used to identify a specific player throughout the game. 
 * The Player class helps to keep track of the moves made by each 
 * player during the game.
 */
public class Player {

    /** Name of this player. */
    private final String name;
    private final int[] lastMove = {-1,-1};

    /** Create a new player with the given name. */
    public Player(String name) {
        this.name = name;
    }

    /** Return the name of this player. */
    public String name() {
        return name;
    }
    public void setLastMove(int x, int y){
        lastMove[0] = x;
        lastMove[1] = y;
    }
    public int[] getLastMove(){
        return lastMove;
    }
}
