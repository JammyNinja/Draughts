public class draughts {
/* TODO
	turns
	kings
	moving rules
	consider board class
	wrap head around row/cols i/j - maybe sooner than later?!
*/
	static draughts game;
	static draughtsGUI gui;
	int[][] board = new int[8][8];
	int turn; //1 or 2

	public static void main(String args[]){
		System.out.println("welcome to draughts game");
		game = new draughts();
		gui = new draughtsGUI(game);
	}
	public draughts(){
		setupBoard();
		startGame();
	}

	//fill board array, -1 for p2, 1 for p1, kings -2,+2
	private void setupBoard(){
		for(int col = 0; col<8; col++){
			for(int row=0; row<8; row++){
				board[col][row] = 0;

				//Player 2, top 3 rows
				if(row < 3){
					if(row%2 == 0){ //p2 top and bottom rows inset
						if(col%2 == 1) board[col][row] = -1;
					} 
					else { //p2 middle row starts from edge
						if(col%2 == 0) board[col][row] = -1;
					}
				}

				//Player 1, bottom 3 rows
				if(row >= 5){
					if(row%2 == 0){ //p1 top and bottom rows inset
						if(col%2 == 1) board[col][row] = 1;
					} 
					else { //p1 middle row starts from edge
						if(col%2 == 0) board[col][row] = 1;
					}
				}
			}
		}
	}

	public void startGame(){
		turn = 1;
	}

	public int tryMove(int x1,int y1, int x2, int y2){
		//return 0 if success, or maybe vary depending on why not
		if(board[x2][y2] != 0) {
			print("the square " + x2+ " , "+y2+" is already occupied!");
			return 1; //must move into an empty square
		}
		else move(x1,y1,x2,y2);
		return 0;
	}


	//execute the move
	void move(int x1, int y1, int x2, int y2){
		//check whose turn it is - does it correspond with piece

		int piece = board[x1][y1];
		//remove piece from where it was
		board[x1][y1] = 0;
		//put it where it's going
		board[x2][y2] = piece;

		//flip turn
		if(turn == 1) turn = 2;
		else turn = 1;
		//remove killed pieces
		gui.repaint();
	}

	void print(String text){
		System.out.println(text);
	}
}










