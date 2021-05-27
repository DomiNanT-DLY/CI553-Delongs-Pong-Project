// Imports from Java
import java.awt.*;
// Class for the score
public class Pong_Score extends Rectangle{
	// Variables
	// static - it will only share one variable if there is multiple panels class
	static int gameWidth;
	static int gameHeight;
	int player1; // It holds the current score of the player 1
	int player2; // It holds the current score of the player 2
	// Constructor for the score
	Pong_Score(int gameWidth, int gameHeight){
		Pong_Score.gameWidth = gameWidth; 
		Pong_Score.gameHeight = gameHeight;
	}
	public void draw(Graphics g) {
		g.setColor(Color.white); // Set the colour of the score to be white
		g.setFont(new Font("Ubuntu",Font.PLAIN,45)); // Set the font of the score to be Ubuntu
		g.drawLine(gameWidth/2, 0, gameWidth/2, gameHeight); // Draw a line in the middle
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (gameWidth/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (gameWidth/2)+35, 50);
	}
}
