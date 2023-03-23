import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board defaultBoard = new Board();
    private Board customBoard = new Board(10);
    private Player player1 = new Player("p1");
    private Player player2 = new Player("p2");

    @BeforeEach
    void setUp(){
        defaultBoard = new Board();
    }
    @Test
    void testDefaultSize() {
        assertEquals(15, defaultBoard.size());
    }
    @Test
    void testCustomSize(){
        assertEquals(10,customBoard.size());
    }

    @Test
    void testIsFull() {
        assertFalse(defaultBoard.isFull());
    }

    @Test
    void testPlaceStone() {
        defaultBoard.placeStone(0,0, player2);
        assertTrue(defaultBoard.isOccupied(0,0));
    }

    @Test
    void testIsEmpty() {
        assertTrue(defaultBoard.isEmpty(0,0));
    }

    @Test
    void testIsOccupied() {
        assertFalse(defaultBoard.isOccupied(0,0));
    }

    @Test
    void testIsOccupiedBy() {
        defaultBoard.placeStone(0,0, player2);
        defaultBoard.placeStone(1,1, player1);

        assertTrue(defaultBoard.isOccupiedBy(1,1,player1));
        assertFalse(defaultBoard.isOccupiedBy(1,1,player2));

        assertFalse(defaultBoard.isOccupiedBy(0,0,player1));
        assertTrue(defaultBoard.isOccupiedBy(0,0,player2));
    }

    @Test
    void testPlayerAt() {
        defaultBoard.placeStone(0,0, player1);
        assertEquals(player1, defaultBoard.playerAt(0,0));
        assertNotEquals(player2, defaultBoard.playerAt(0,0));
    }

    @Test
    void testIsWonByP1() {
        for(int i = 0; i < 5; i++) {
            defaultBoard.placeStone(i, i, player1);
        }
        assertTrue(defaultBoard.isWonBy(player1));
        assertFalse(defaultBoard.isWonBy(player2));
    }
    @Test
    void testIsWonByP2() {
        for(int i = 14; i > 9; i--) {
            defaultBoard.placeStone(i, i, player2);
        }
        assertFalse(defaultBoard.isWonBy(player1));
        assertTrue(defaultBoard.isWonBy(player2));
    }

    @Test
    void testDiagonalWinningRow() {
        for(int i = 0; i < 5; i++) {
            defaultBoard.placeStone(i, i, player1);
        }
        defaultBoard.isWonBy(player1);
        int i = 4;
        int j = 0;
        while(i > -1){
            Board.Place coordinate = defaultBoard.winningRow.get(i);
            assertEquals(j, coordinate.x);
            assertEquals(j, coordinate.y);
            i--;
            j++;
        }
    }
    @Test
    void testInverseDiagonalWinningRow() {
        int r = 0;
        int c = 4;
        while(r < 5) {
            defaultBoard.placeStone(r, c, player1);
            r++;
            c--;
        }
        defaultBoard.isWonBy(player1);
        int i = 4;
        int j = 0;
        while(i > -1){
            Board.Place coordinate = defaultBoard.winningRow.get(j);
            assertEquals(i, coordinate.x);
            assertEquals(j, coordinate.y);
            i--;
            j++;
        }
    }
    @Test
    void testHorizontalWinningRow() {
        for(int i = 0; i < 5; i++){
            defaultBoard.placeStone(0, i, player1);
        }
        defaultBoard.isWonBy(player1);
        int i = 4;
        int j = 0;
        while(i > -1){
            Board.Place coordinate = defaultBoard.winningRow.get(i);
            assertEquals(0, coordinate.x);
            assertEquals(j, coordinate.y);
            i--;
            j++;
        }
    }
    @Test
    void testVerticalWinningRow() {
        for(int i = 0; i < 5; i++){
            defaultBoard.placeStone(i, 0, player1);
        }
        defaultBoard.isWonBy(player1);
        int i = 4;
        int j = 0;
        while(i > -1){
            Board.Place coordinate = defaultBoard.winningRow.get(i);
            assertEquals(j, coordinate.x);
            assertEquals(0, coordinate.y);
            i--;
            j++;
        }
    }
}