package comp1110.lectures.G01;

/* Implementation of (3x3) TicTacToe.
 */
public class TicTacToe extends TwoPlayerGame {

    class Move implements TwoPlayerGame.Move {
	State state;
	int turn;
	int row;
	int col;
	
	Move(State s, int r, int c) {
	    this.state = s;
	    this.turn = s.turn;
	    this.row = r;
	    this.col = c;
	}
	
	public void apply() {
	    if (turn == 0)
		state.board[row][col] = 'X';
	    else
		state.board[row][col] = 'O';
	    state.turn = (state.turn + 1) % 2;
	}
	
	public void undo() {
	    state.board[row][col] = ' ';
	    state.turn = (state.turn + 1) % 2;
	}

	public String toString() {
	    return (turn == 0 ? "X" : "O") + " at (" + Integer.toString(row) + "," + Integer.toString(col) + ")";
	}
    }

    class State implements TwoPlayerGame.State {
	char[][] board;
	int turn;
	
	State() {
	    board = new char[][] { {' ', ' ', ' ' },
				   {' ', ' ', ' ' },
				   {' ', ' ', ' ' } };
	    turn = 0;
	}

	private int nMoves() {
	    int n = 0;
	    for (int row = 0; row < 3; row++)
		for (int col = 0; col < 3; col++)
		    if (board[row][col] == ' ')
			n += 1;
	    return n;
	}

	private Move[] computeMoves() {
	    TicTacToe.Move[] applicable = new TicTacToe.Move[nMoves()];
	    int i = 0;
	    for (int row = 0; row < 3; row++)
		for (int col = 0; col < 3; col++)
		    if (board[row][col] == ' ') {
			applicable[i] = new TicTacToe.Move(this, row, col);
			i += 1;
		    }
	    return applicable;
	}

	char threeInARow() {
	    for (int row = 0; row < 3; row++)
		if (board[row][0] == board[row][1] &&
		    board[row][0] == board[row][2] &&	
		    board[row][0] != ' ')
		    return board[row][0];
	    for (int col = 0; col < 3; col++)
		if (board[0][col] == board[1][col] &&
		    board[0][col] == board[2][col] &&	
		    board[0][col] != ' ')
		    return board[0][col];
	    if (board[0][0] == board[1][1] &&
		board[0][0] == board[2][2] &&	
		board[0][0] != ' ')
		return board[0][0];
	    if (board[0][2] == board[1][1] &&
		board[0][2] == board[2][0] &&	
		board[0][2] != ' ')
		return board[0][2];
	    return ' ';
	}
	
	public int current_player() {
	    return turn;
	}
	
	public boolean isTerminal() {
	    if (nMoves() == 0) return true;
	    char c = threeInARow();
	    if (c != ' ') return true;
	    return false;
	}
	
	public int score() {
	    char c = threeInARow();
	    if (c == 'X') return 1;
	    if (c == 'O') return -1;
	    return 0;
	}
	
	public Move[] moves() {
	    return computeMoves();
	}

	public String toString() {
	    return "turn: " + turn
		+ "\n|" + board[0][0] + board[0][1] + board[0][2] + "|\n"
		+ "|" + board[1][0] + board[1][1] + board[1][2] + "|\n"
		+ "|" + board[2][0] + board[2][1] + board[2][2] + "|";
	}
    }

    public State initialState() {
	return new State();
    }

    public static void main(String[] argv) {
	TicTacToe game = new TicTacToe();
	int max_score = game.solve();
	System.out.println("best outcome for player 0: " + max_score);
	System.out.println("sample play:");
	State s = game.initialState();
	game.samplePath(s, max_score, true);
    }
}
