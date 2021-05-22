package classes;

public class Game{

	public static Game life;
	public static Board board;

	static int[][] m = new int[100][100];
	static int[][] m2 = new int[100][100];
	
	static int[] nbx = {-1, -1, -1,  0, 0,  1, 1, 1};
	static int[] nby = {-1,  0,  1, -1, 1, -1, 0, 1};
	
	static boolean gameOver = false;

	public static final int width = 96;
	public static final int height = 54;
	
	public static int generation = 0;
	
	public Game() {
		
		board = new Board();
	}

	public static void main(String[] args) {
		life = new Game();
		
		giveBoardConfiguration();
		
		while(!gameOver) {
			if(Board.reseted) {
				
				
				for(int i = 1; i <= height; i++) {
					for(int j = 1; j <= width; j++) {
						m[i][j] = m2[i][j] = 0;
					}
				}
				
				generation = 0;
				giveBoardConfiguration();
				Board.arr.clear();
				Board.reseted = false;
			}
			
			formNextGeneration();
			//printBoard();		
			
			++generation;
			
			if(Board.gameOver)
				gameOver = true;
			
			try {
				Thread.sleep(20);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			while(Board.paused) {
				board.painter.repaint();
			}
			
			board.painter.repaint();
		}
	}

	public static void formNextGeneration() {
		
		int neighbors;
		
		for(int i = 1; i <= height; ++i) {
			for(int j = 1; j <= width; ++j) {
				neighbors = countNeighbors(i, j);
				
				if(m[i][j] == 1)
					deadOrAlive(neighbors, true, i, j);
				else
					deadOrAlive(neighbors, false, i, j);
				
			}
		}
		
		for(int i = 1; i <= height; ++i) {
			for(int j = 1; j <= width; ++j) {
				m[i][j] = m2[i][j];
				
				if(m[i][j] == 1) {
					
					int[] a = new int[100];
					int cont = 0;
					int c = 0;
					
					for(Square square : Board.arr) {
						
						cont++;
						if(square.i == i && square.j == j) {
							a[++c] = cont;
							break;
						}
					}
					
					for(int it = 1; it <= c; it++)
						Board.arr.remove(a[i]);
					
					Square square = new Square((j - 1) * 20 + 1, (i - 1) * 20 + 1);
					
					square.setI(i);
					square.setJ(j);
					
					Board.arr.add(square);
					
				}
				else {
					
					int[] a = new int[100];
					int cont = 0;
					int c = 0;
					
					for(Square square : Board.arr) {
						
						cont ++;
						if(square.i == i && square.j == j) {
							a[++c] = cont; 
						}
					}
					
					for(int it = 1; it <= c; it++) {
						Board.arr.remove(a[i]);
					}
					
				}
			}
		}
		
	}
	
	public static void deadOrAlive(int neighbors, boolean status, int x, int y) {
		
		if(status) {
			if(neighbors < 2 || neighbors > 3)
				m2[x][y] = 0;
			else
				m2[x][y] = 1;
		}
		else {
			if(neighbors == 3)
				m2[x][y] = 1;
			else
				m2[x][y] = 0;
		}
	}
	
	public static int countNeighbors(int x, int y) {
		
		int counter = 0;
	
		for(int i = 0; i < 8; ++i) {
			if(m[x + nbx[i]][y + nby[i]] == 1 && inTheBoard(x + nbx[i], y + nby[i])) {
				++ counter;
			}
		}
		return counter;
	}
	
	public static boolean inTheBoard(int x, int y) {
		return x > 0 && x <= height && y > 0 && y <= width; 
	}
	
	public static void printBoard() {
		
		for(int i = 1; i <= height; ++i) {

			for(int j = 1; j <= width; ++j) {

				System.out.print(m[i][j]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
		System.out.print('\n');
	}
	
	public static void clearMatrix() {
		
		for(int i = 1; i <= width; i++) {
			
			for(int j = 1; j <= height; j++) {
				
				m[i][j] = m2[i][j] = 0;
				
			}
			
		}
		
	}
	
	public static void giveBoardConfiguration() {

		while(!Board.started && !Board.paused) {
			
			board.painter.repaint();
		}	
	}
}
