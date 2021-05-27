// Imports from Java
import java.awt.*;
import java.awt.event.*;
// Class for the paddle
// Rectangle - so it will be treated as a rectangle
public class Pong_Paddle extends Rectangle{
	// Variables
	int id;
	int velocityY;
	int speed = 10;
	// Constructor for the paddle
	Pong_Paddle(int x, int y, int paddleWidth, int paddleHeight, int id){
		super(x,y,paddleWidth,paddleHeight);
		this.id=id;
	}
	// Method - 
	public void keyPressed(KeyEvent e) { // Only triggered when the key has been pressed
		switch(id) {
		case 1: // Player 1
			if(e.getKeyCode()==KeyEvent.VK_W) { // Press W key on the keyboard makes paddle go upward
				setDirectionY(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {	// Press S key on the keyboard makes paddle go downward
				setDirectionY(speed);
			}
			break;
		case 2: // Player 2
			if(e.getKeyCode()==KeyEvent.VK_UP) { // Press up key on the keyboard makes paddle go upward
				setDirectionY(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) { // Press down key on the keyboard makes paddle go downward
				setDirectionY(speed);
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) { // Only triggered when the key has been released
		switch(id) {
		case 1: // Player 1
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setDirectionY(0); // Set the speed to be 0
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setDirectionY(0); // Set the speed to be 0
			}
			break;
		case 2: // Player 2
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setDirectionY(0); // Set the speed to be 0
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setDirectionY(0); // Set the speed to be 0
			}
			break;
		}
	}
	public void setDirectionY(int directionY) {
		velocityY = directionY;
	}
	public void move() {
		y = y + velocityY;
	}
	// Method - Draw paddles on the left and right side of the frame
	public void draw(Graphics g) {
		if(id==1) // Player 1
			g.setColor(Color.blue); // Set the paddle colour to be blue for player 1
		else // Player 2
			g.setColor(Color.red);	// Set the paddle colour to be red for player 2
		g.fillRect(x, y, width, height); // 
	}
}