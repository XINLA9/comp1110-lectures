package comp1110.lectures.G01;

import java.util.random.*;

/* Abstract implementation of a simple Two-player (zero-sum) game.
 *
 * Concrete game implementations (TicTacToe and FifteenStones) implement
 * State and Move in nested classes.
 */
public abstract class TwoPlayerGame {

    public interface Move {
	void apply();
	void undo();
    }

    public interface State {
	int current_player();
	boolean isTerminal();
	int score();
	Move[] moves();
    }

    public abstract State initialState();

    int expansions = 0;

    /** minimax search with alpha-beta pruning.
     * playMax is the max player's (i.e., "our") function,
     * playMin is the min player's (i.e., opponent's) funciton.
     */ 
    public int playMax(State s, int alpha, int beta) {
    	if (s.isTerminal())
    	    return s.score();
    	expansions += 1;
    	int max_score = -1; // lowest possible score
    	for (Move m : s.moves()) {
    	    m.apply();
    	    // note: here we assume the game turn switches to the other
    	    // player after every move; to be more general, we should
    	    // query s.current_player() after applying the move, and
    	    // call playMax if it is 0, playMin if it is 1.
    	    int move_score = playMin(s, alpha, beta);
    	    if (move_score >= beta) {
    		m.undo();
    		return move_score;
    	    }
    	    if (move_score > max_score)
    		max_score = move_score;
    	    if (move_score > alpha)
    		alpha = move_score;
    	    m.undo();
    	}
    	return max_score;
    }

    public int playMin(State s, int alpha, int beta) {
    	if (s.isTerminal())
    	    return s.score();
    	expansions += 1;
    	int min_score = 1; // highest possible score
    	for (Move m : s.moves()) {
    	    m.apply();
    	    int move_score = playMax(s, alpha, beta);
    	    if (move_score <= alpha) {
    		m.undo();
    		return move_score;
    	    }
    	    if (move_score < min_score)
    		min_score = move_score;
    	    if (move_score < beta)
    		beta = move_score;
    	    m.undo();
    	}
    	return min_score;
    }

    /* minimax search without pruning.
     */
    // public int playMax(State s) {
    // 	if (s.isTerminal())
    // 	    return s.score();
    // 	expansions += 1;
    // 	int max_score = -1; // lowest possible score
    // 	for (Move m : s.moves()) {
    // 	    m.apply();
    // 	    int move_score = playMin(s);
    // 	    if (move_score > max_score)
    // 		max_score = move_score;
    // 	    m.undo();
    // 	}
    // 	return max_score;
    // }

    // public int playMin(State s) {
    // 	if (s.isTerminal())
    // 	    return s.score();
    // 	expansions += 1;
    // 	int min_score = 1; // highest possible score
    // 	for (Move m : s.moves()) {
    // 	    m.apply();
    // 	    int move_score = playMax(s);
    // 	    if (move_score < min_score)
    // 		min_score = move_score;
    // 	    m.undo();
    // 	}
    // 	return min_score;
    // }

    static RandomGenerator rng = RandomGenerator.of("Xoroshiro128PlusPlus");

    /* Print a random path from the game tree that ends with given score.
     */
    public void samplePath(State s, int score, boolean is_max) {
	if (s.isTerminal()) {
	    System.out.println("game ended in state " + s + " with score " + score);
	    return;
	}
	String state_string = s.toString();
	Move[] moves = s.moves();
	int offset = rng.nextInt(moves.length);
	for (int n = 0; n < moves.length; n++) {
	    int i = (offset + n) % moves.length;
	    String move_string = moves[i].toString();
	    moves[i].apply();
	    //int move_score = (is_max ? playMin(s) : playMax(s)); // if not alpha-beta
	    int move_score = (is_max ? playMin(s, -1, 1) : playMax(s, -1, 1));
	    if (move_score == score) {
		System.out.println("state " + state_string + ", move " + move_string);
		samplePath(s, score, !is_max);
		return;
	    }
	    moves[i].undo();
	}
    }

    public int solve() {
	State s = initialState();
	expansions = 0;
	// int max_score = playMax(s); // if not using alpha-beta
	int max_score = playMax(s, -1, 1);
	System.out.println(expansions + " state expansions");
	return max_score;
    }
}
