package logic;

import AI.Algorithm;
import AI.Evaluation;
import AI.Node;

public class GameLogic {
	
	public Board board;
	public Player p1, p2;
	
	public GameLogic() {
		
		board = new Board();
        p1 = new Player('P', board);
        p2 = new Player('A', board);
        
	}
}
