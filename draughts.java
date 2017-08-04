import java.lang.Math; //abs
public class draughts {
/* TODO
	moving rules
	stop it from overtaking self
	kings
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
	//way more validation needed
	public int tryMove(int x1,int y1, int x2, int y2, int piece){
		//things to validate for:
		//square empty
		//move forwards - unless king!
		//is it an overtake
		//not overtaking your own piece
		//are we forced to overtake elsewhere


		//consider moviing if conditions into separate functions - maybe just the worst ones
		int sqFrom 	= board[x1][y1];
		int sqTo	= board[x2][y2];
		//return 0 if success, or maybe vary depending on why not
		if(sqTo != 0) {
			print("The square ["+x2+"]["+y2+"] is already occupied!");
			return 1; //must move into an empty square
		}
		else if(Math.abs(piece) < 2 && ((turn == 2 && y2 <= y1) || (turn == 1 && y2 >= y1)) ){
			print("Must move forwards!");
			return 2;
		}
		//simple overtake
		else if (Math.abs(x2-x1) == 2 && Math.abs(y2-y1) == 2) {
			int victimX = (x1+x2)/2; 
			int victimY = (y1+y2)/2;
			int victimPiece = board[victimX][victimY];

			print("simple overtake attempt! piece: " + victimPiece);
			//only allow if not overtaking own team
			if((victimPiece > 0 && turn == 1) || (victimPiece< 0 && turn == 2)){
				print("Can't overtake your own piece!");
				return 3;
			}
		}
		move(x1,y1,x2,y2);
		return 0;
	}



	//execute the move
	void move(int x1, int y1, int x2, int y2){
		int piece = board[x1][y1];
		//remove piece from where it was
		board[x1][y1] = 0;
		//put it where it's going
		board[x2][y2] = piece;

		//if it was overtake then delete that piece too
		if (Math.abs(x2-x1) == 2 && Math.abs(y2-y1) == 2) {
			//average the coords to find overtaken sq
			//perhaps math could do that
			int deadX = (x1+x2)/2; 
			int deadY = (y1+y2)/2;
			board[deadX][deadY] = 0;
		}

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










