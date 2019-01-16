import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;

/**
 * Created by snadi on 2018-07-23.
 */
public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void testInit() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assert (board.getPlayerAtPos(i, j).equals(Board.Player.NONE));
            }
        }

        assert (board.getCurrentPlayer().equals(Board.Player.X));
        assert (board.getWinner() == null);
    }

    @Test
    public void testPlayMoveEmptyBoard() throws InvalidMoveException {

        assert (board.getPlayerAtPos(1, 1).equals(Board.Player.NONE));
        board.playMove(1, 1);

        assert (board.getPlayerAtPos(1, 1).equals(Board.Player.X));
        assert (board.getCurrentPlayer().equals(Board.Player.O));

        assert (board.getPlayerAtPos(0, 0).equals(Board.Player.NONE));
        board.playMove(0, 0);
        assert (board.getPlayerAtPos(0, 0).equals(Board.Player.O));
        assert (board.getCurrentPlayer().equals(Board.Player.X));
    }


    @Test(expected = InvalidMoveException.class)
    public void testPlayMoveInvalidRowPosition() throws InvalidMoveException {
        board.playMove(3, 0);
    }

    @Test(expected = InvalidMoveException.class)
    public void testPlayMoveInvalidColPosition() throws InvalidMoveException {
        board.playMove(0, 3);
    }

    @Test(expected = InvalidMoveException.class)
    public void testPlayMoveInvalidNegRowPosition() throws InvalidMoveException {
        board.playMove(-1, 2);
    }

    @Test(expected = InvalidMoveException.class)
    public void testPlayMoveInvalidNegColPosition() throws InvalidMoveException {
        board.playMove(2, -1);
    }

    @Test(expected = InvalidMoveException.class)
    public void testPlayMoveExistinSq() throws InvalidMoveException {
        board.playMove(2, 1);
        board.playMove(2, 1);
    }

    @Test
    public void testGetSymbol() {
        assert (board.getSymbol(Board.Player.NONE).equals(" "));
        assert (board.getSymbol(Board.Player.X).equals("X"));
        assert (board.getSymbol(Board.Player.O).equals("O"));
    }


    @Test
    public void testWinRightDiagonalX() throws InvalidMoveException {
        board.playMove(0,0); //player X
        board.playMove(0,1);
        board.playMove(1,1); //player X
        board.playMove(2,1);
        board.playMove(2,2); //player X

        assert(board.getWinner().equals(Board.Player.X));
    }

    @Test
    public void testWinRightDiagonalO() throws InvalidMoveException {

        board.playMove(0,1);
        board.playMove(1,1); //player 0
        board.playMove(2,1);
        board.playMove(2,2); //player 0
        board.playMove(1,0);
        board.playMove(0,0); //player 0

        assert(board.getWinner().equals(Board.Player.O));
    }

    @Test
     public void testWinLeftDiagonalO() throws InvalidMoveException {

        board.playMove(1,2);
        board.playMove(1,1); //player 0
        board.playMove(2,1);
        board.playMove(0,2); //player 0
        board.playMove(0,0);
        board.playMove(2,0); //player 0

        assert(board.getWinner().equals(Board.Player.O));
    }

    @Test
    public void testWinLeftDiagonalX() throws InvalidMoveException {

        board.playMove(1,1); //player X
        board.playMove(0,1);
        board.playMove(2,0); //player X
        board.playMove(1,2);
        board.playMove(0,2); //player X

        assert(board.getWinner().equals(Board.Player.X));
    }

    @Test
      public void testWinHorizonalX() throws InvalidMoveException {

        board.playMove(1,0); //player X
        board.playMove(2,1);
        board.playMove(1,1); //player X
        board.playMove(2,2);
        board.playMove(1,2); //player X

        assert(board.getWinner().equals(Board.Player.X));
    }

    @Test
    public void testWinHorizonalO() throws InvalidMoveException {

        board.playMove(0,1);
        board.playMove(2,1); //player 0
        board.playMove(1,1);
        board.playMove(2,2); //player 0
        board.playMove(1,0);
        board.playMove(2,0); //player 0

        assert(board.getWinner().equals(Board.Player.O));
    }

    @Test
    public void testWinVerticalX() throws InvalidMoveException {

        board.playMove(0,1); //player X
        board.playMove(0,2);
        board.playMove(1,1); //player X
        board.playMove(2,2);
        board.playMove(2,1); //player X

        assert(board.getWinner().equals(Board.Player.X));
    }

    @Test
    public void testVerticalO() throws InvalidMoveException {

        board.playMove(0,1);
        board.playMove(2,2); //player 0
        board.playMove(1,1);
        board.playMove(1,2); //player 0
        board.playMove(1,0);
        board.playMove(0,2); //player 0

        assert(board.getWinner().equals(Board.Player.O));
    }
    
    @Test
    public void testTieCorner() throws InvalidMoveException {

	board.playMove(0,1); //player X
	board.playMove(0,0);
	board.playMove(1,1); //player X 
	board.playMove(1,0);
	board.playMove(1,2); //player X 
	board.playMove(0,2);
	board.playMove(2,0); //player X 
	board.playMove(2,1);
	assert(board.isTie().equals(true));
    }
    
    @Test
    public void	testTieSideMiddle() throws InvalidMoveException {

	board.playMove(0,1); //player X                                                                       
        board.playMove(0,0);
        board.playMove(1,0); //player X 
        board.playMove(0,2);
        board.playMove(2,0); //player X 
        board.playMove(1,1);
        board.playMove(2,2); //player X 
        board.playMove(2,1);
	assert(board.isTie().equals(true));
    }

    @Test
    public void testTieMiddle() throws InvalidMoveException {

	board.playMove(0,1); //player X                                                                       
        board.playMove(0,0);
        board.playMove(0,2); //player X 
        board.playMove(1,2);
        board.playMove(1,0); //player X 
        board.playMove(2,0);
        board.playMove(2,2); //player X 
        board.playMove(2,1);
	assert(board.isTie().equals(true));
    }

    @Test
    public void testTieNotTie() throws InvalidMoveException {

	board.playMove(0,1);
        board.playMove(2,1); //player 0                                                                       
        board.playMove(1,1);
        board.playMove(2,2); //player 0                                                                      
        board.playMove(1,0);
        board.playMove(2,0); //player 0
	
	assert(board.isTie().equals(false));
    }
    
}
