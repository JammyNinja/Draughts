public class draughts {
/* TODO
	maybe listen to keys for escaping?/reset game
	kings
	Mouselistener moving pieces
	moving rules

	wrap head around row/cols i/j - maybe sooner than later?!
*/
	static draughts game;
	static draughtsGUI gui;
	int[][] board = new int[8][8];

	public static void main(String args[]){
		System.out.println("welcome to draughts game");
		game = new draughts();
		gui = new draughtsGUI(game);
	}
	public draughts(){
		setupBoard();
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
}