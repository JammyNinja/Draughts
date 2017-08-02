import javax.swing.*; //JPanel/containers/scrollpane
import java.awt.*; //Dimension/colour/graphics
public class draughtsGUI extends JPanel
						//implements MouseListener
{
/* TODO
*/
	draughts game;
	int boardWidth, boardHeight; 
	int sqSize, pieceSize, pieceGap;
	public draughtsGUI(draughts game){
		this.game = game;

		Frame f = new Frame();
		initialiseGUI(f);
	}
	public void initialiseGUI (Frame f){
		setPreferredSize(new Dimension(f.frameWidth, f.frameHeight) );
		boardWidth = f.frameWidth;
		boardHeight = f.frameHeight;
		sqSize = (boardWidth + boardHeight) / 2 / 8; //average /8 
		pieceGap = 10;
		pieceSize = sqSize - 2 * pieceGap; //oof hardcode
		f.initialise(this);
	}

	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		paintBoard(g);
		paintPieces(g);
	}

	public void paintBoard(Graphics g){
		g.setColor(Color.WHITE);
		for(int i = 0; i<=8; i++){
			for(int j=0; j<=8; j++){
				g.fillRect(i*sqSize,j*sqSize, sqSize,sqSize);
				if(g.getColor() == Color.BLACK) g.setColor(Color.WHITE);
				else g.setColor(Color.BLACK);
			}
		}
	}
	public void paintPieces(Graphics g){
		for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
				if(game.board[i][j] < 0){
					g.setColor(Color.YELLOW);
					g.fillOval(i*sqSize + pieceGap, j*sqSize + pieceGap, pieceSize, pieceSize);
				}
				else if(game.board[i][j] > 0){
					g.setColor(Color.RED);
					g.fillOval(i*sqSize + pieceGap, j*sqSize + pieceGap, pieceSize, pieceSize);
				}
			}
		}
	}
}


class Frame extends JFrame {
	static int frameWidth = 720; //windowWidth = 1000; player 1/100 of this, ball 1/50
	static int frameHeight = 720; //windowHeight = 600;  //player is 1/6 of this

	public Frame(){
		setTitle("Draughts");
		setSize(frameWidth,frameHeight);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initialise(draughtsGUI guiPanel)
	{
		setLayout(new GridLayout(1,1));
		add(guiPanel);
		pack();
		//guiPanel.northLimit = getInsets().top;
		setLocationRelativeTo(null);
		setVisible(true);
	}
}