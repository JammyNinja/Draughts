import javax.swing.*; //JPanel/containers/scrollpane
import java.awt.*; //Dimension/colour/graphics
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class draughtsGUI extends JPanel
						implements KeyListener, MouseListener
{
	draughts game;
	int boardWidth, boardHeight; 
	int sqSize, pieceSize, pieceGap;
	//move making variables
	boolean piecePicked = false;
	Point moveFrom;
	int moveFromX,moveFromY;

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

		//get wet for input
		addKeyListener(this);
		setFocusable(true);
		addMouseListener(this);


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
				//p2
				if(game.board[i][j] < 0){
					g.setColor(Color.YELLOW);
					g.fillOval(i*sqSize + pieceGap, j*sqSize + pieceGap, pieceSize, pieceSize);
				}
				//p1
				if(game.board[i][j] > 0){
					g.setColor(Color.RED);
					g.fillOval(i*sqSize + pieceGap, j*sqSize + pieceGap, pieceSize, pieceSize);
				}
			}
		}
	}

	// INPUT
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_SPACE:
				game.tryMove(3,2,2,1);
			break;
			case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}
	public void mouseClicked(MouseEvent m){
		game.print(""+m.getPoint());

		int x = m.getX() / sqSize;
		int y = m.getY() / sqSize;
		game.print("I reckon square: ["+ x +"]["+ y +"].");
//TRY a varaibale called square clicked
		if(!piecePicked) {
			moveFromX = x;
			moveFromY = y;
			piecePicked = true;
		}
		//we now have the destination
		else {
			int success = game.tryMove(moveFromX, moveFromY, x, y);
			if (success == 0) piecePicked = false;
		}
	}

	//UNUSED listener functions
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseExited(MouseEvent m){}
	public void mouseEntered(MouseEvent m){}
	public void mouseReleased(MouseEvent m){}
	public void mousePressed(MouseEvent m){}
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