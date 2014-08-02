package eud.nyu.cs.pqs.connectfour;

import java.util.ArrayList;
import java.util.List;

public class ConnectFourModel {
	int rowSize, colSize, winSize;
	private int[][] grid;
	List<ConnectFourListener> listeners = new ArrayList<ConnectFourListener>();
	
	public void startGame() {
		rowSize = 7;
		colSize = 7;
		winSize = 4;
		grid = new int[rowSize][colSize];
		for(int i=0; i<rowSize; ++i) {
			for( int j=0; j<colSize; ++j) {
				grid[i][j] = -1;
			}
		}
		fireStartGameEvent();
	}
	
	
	private void fireStartGameEvent() {
		for(ConnectFourListener listener: listeners) {
			listener.startGame(rowSize, colSize);
		}
	}

	public boolean makeMove(int player, int x, int y) {
		//System.out.println("Got one move!" + grid[x][y]);
		if(grid[x][y] == -1) {
			fireMakeMoveEvent(player, x, y);
			grid[x][y] = player;
			int result = checkIfWon();
			if(result != -1) firePlayerWinEvent(player);
			
			return true;
		}
		return false;
	}

	private void firePlayerWinEvent(int player) {
		for(ConnectFourListener listener: listeners) {
			listener.playerWin(player);
		}		
	}


	private int checkIfWon() {
		for(int i=1; i<rowSize; ++i) {
			for(int j=0; j<colSize; ++j) {
				//right
				if(j+winSize<=colSize){
					int check;
					for(check=j; check<j+winSize; ++check) {
						if(grid[i][check] != grid[i][j]) break;
					}
					if(check == j+winSize) return grid[i][j];
				}
				//bottom right
				//bottom
				//bottom left
				//left
				//left top
				//top
				//top left
			}
		}
		return -1;
	}


	private void fireMakeMoveEvent(int player, int x, int y) {
		for(ConnectFourListener listener: listeners) {
			listener.makeMove(player, x, y);
		}
	}


	public boolean addConnectFourListener(ConnectFourListener listener) {
		return listeners.add(listener);
	}
	
	public boolean removeConnectFourListener(ConnectFourListener listener) {
		return listeners.remove(listener);
	}
}
