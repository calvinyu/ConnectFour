package eud.nyu.cs.pqs.connectfour;

public interface ConnectFourListener {
	void makeMove(int player, int x, int y);
	void playerWin(int player);
	void startGame(int colSize, int rowSize);
}
