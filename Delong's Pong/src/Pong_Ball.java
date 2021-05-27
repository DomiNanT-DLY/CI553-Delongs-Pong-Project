// Imports from Java
import java.awt.*;
import java.util.*;
// Class for the ball
public class Pong_Ball extends Rectangle{
	// Variables
	Random random;
	int velocityX; // How fast it the ball travel from left to right
	int velocityY; // How fast it the ball travel from top to bottom
	int initialSpeed = 2;
	// Constructor for the ball
	Pong_Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random(); // Set a random direction for the ball
		int randomDirectionX = random.nextInt(2); // So it is either going to be 0 or 1
		if(randomDirectionX == 0) // if the random number are 0 then the ball will go left
			randomDirectionX--;
		setDirectionX(randomDirectionX*initialSpeed);
		
		int randomDirectionY = random.nextInt(2); // So it is either going to be 0 or 1
		if(randomDirectionY == 0) // if the random number are 0 then the ball will go right
			randomDirectionY--;
		setDirectionY(randomDirectionY*initialSpeed);
		
	}
	public void setDirectionX(int randomDirectionX) {
		velocityX = randomDirectionX; // Receive random X direction
	}
	public void setDirectionY(int randomDirectionY) {
		velocityY = randomDirectionY; // Receive random Y direction
	}
	public void move() {
		x += velocityX;
		y += velocityY;
	}
	public void draw(Graphics g) {
		g.setColor(Color.white); // Set the colour to be white for the ball
		g.fillOval(x, y, height, width); // Make the ball to be circle
	}
}