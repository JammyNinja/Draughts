public class draughts {
	int[][] board = new int[8][8];
	public static void main(String args[]){
		System.out.println("welcome to draughts game");
		draughts game = new draughts();
		draughtsGUI gui = new draughtsGUI(game);
	}
	public draughts(){
		setupBoard();
	}
	private void setupBoard(){
		for(int col = 0; col<8; col++){
			for(int row=0; row<8; row++){
				board[col][row] = 0;

				//Player 2 = top 3 rows
				if(row < 3){
					//top and bottom rows p2 
					if(row%2 == 0){
						if(col%2 == 1) board[col][row] = -1;
					} 
					//middle row
					else {
						if(col%2 == 0) board[col][row] = -1;
					}
				}

				//Player 1 = bottom 3 rows
				if(row >= 5){
					//top and bottom rows p2 
					if(row%2 == 0){
						if(col%2 == 1) board[col][row] = 1;
					} 
					//middle row
					else {
						if(col%2 == 0) board[col][row] = 1;
					}
				}
			}
		}
	}
}