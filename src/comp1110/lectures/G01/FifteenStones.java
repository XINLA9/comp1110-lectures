package comp1110.lectures.G01;

/* Implementation of the "15 stones" game (apparently also known
 * as "Nim" - thanks for the reference!).
 */
public class FifteenStones extends TwoPlayerGame {

    class Move implements TwoPlayerGame.Move {
	State state;
	int col;
	int take;
	
	Move(State s, int c, int n) {
	    this.state = s;
	    this.col = c;
	    this.take = n;
	}
	
	public void apply() {
	    state.left[col] -= take;
	    state.turn = (state.turn + 1) % 2;
	}
	
	public void undo() {
	    state.left[col] += take;
	    state.turn = (state.turn + 1) % 2;
	}

	public String toString() {
	    return "take " + take + " from column " + col;
	}
    }

    class State implements TwoPlayerGame.State {
	int[] left;
	int turn;
	
	State() {
	    left = new int[] { 1, 2, 3, 4, 5 };
	    turn = 0;
	}

	private int nMoves() {
	    int n = 0;
	    for (int col = 0; col < 5; col++)
		if (left[col] >= 2)
		    n += 2;
		else if (left[col] >= 1)
		    n += 1;
	    return n;
	}

	private Move[] computeMoves() {
	    FifteenStones.Move[] applicable = new FifteenStones.Move[nMoves()];
	    int i = 0;
	    for (int col = 0; col < 5; col++)
		if (left[col] >= 2) {
		    applicable[i] = new Move(this, col, 2);
		    applicable[i + 1] = new Move(this, col, 1);
		    i += 2;
		}
		else if (left[col] >= 1) {
		    applicable[i] = new Move(this, col, 1);
		    i += 1;
		}
	    return applicable;
	}
	
	public int current_player() {
	    return turn;
	}
	
	public boolean isTerminal() {
	    return (nMoves() == 1);
	}
	
	public int score() {
	    if (nMoves() == 1) {
		if (turn == 0)
		    return -1;
		else
		    return 1;
	    }
	    return 0;
	}
	
	public Move[] moves() {
	    return computeMoves();
	}

	public String toString() {
	    return "[ " + left[0] + ", " + left[1] + ", " + left[2] + ", "
		+ left[3] + ", " + left[4] + " ], turn: " + turn;
	}
    }

    public State initialState() {
	return new State();
    }

    public static void main(String[] argv) {
	FifteenStones game = new FifteenStones();
	int max_score = game.solve();
	System.out.println("best outcome for player 0: " + max_score);
	System.out.println("sample play:");
	State s = game.initialState();
	game.samplePath(s, max_score, true);
    }
}
