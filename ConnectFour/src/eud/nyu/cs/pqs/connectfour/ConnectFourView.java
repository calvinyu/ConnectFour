package eud.nyu.cs.pqs.connectfour;

import java.util.Scanner;

public class ConnectFourView implements ConnectFourListener{

	boolean isStart;
	ConnectFourModel model;
	int colSize, rowSize;
	int grid[][];
	
	@Override
	public void makeMove(int player, int x, int y) {
		grid[x][y] = player;
		printGrid();
	}

	private void printGrid() {
		System.out.println("--------");
		for(int i=rowSize-1; i>=1; --i) {
			System.out.printf("|");
			for( int j=0; j<colSize; ++j) {
				int mark = grid[i][j];
				if(mark == -1) System.out.printf(" ");
				else if(mark == 0) System.out.printf("X");
				else if(mark == 1) System.out.printf("O");
			}			
			System.out.printf("|");
			System.out.println();
		}
		System.out.println("--------");
	}
	
	public ConnectFourView(ConnectFourModel model) {
		this.model = model;
		model.addConnectFourListener(this);
	}

	@Override
	public void playerWin(int player) {
		isStart = false;
		System.out.println("Game Over!! Player " + (player+1) + " won!");
	}

	@Override
	public void startGame(int colSize, int rowSize) {
		this.colSize = colSize;
		this.rowSize = rowSize;
		grid = new int[this.rowSize][this.colSize];
		
		for(int i=0; i<this.rowSize; ++i) {
			for( int j=0; j<this.colSize; ++j) {
				grid[i][j] = -1;
				if(i==0) grid[i][j] = 1;
			}
		}
		
		run();
	}

	private void run() {
		isStart = true;
		
		while(isStart) {
			System.out.println("Player 1's turn");
			promptUserAndMove(0);
			if(!isStart) break;			
			System.out.println("Player 2's turn");
			promptUserAndMove(1);

		}
	}

	private void promptUserAndMove(int player) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please select a column to drop. From 1 to " + colSize);
		int col;
		int x,y;
		col = kb.nextInt()-1;
		y = col;
		x = grid[0][col]++;
		System.out.println(x + " " + y);
		model.makeMove(player, x, y);
		//kb.close();
	}
	
}
