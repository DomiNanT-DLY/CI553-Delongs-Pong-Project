// Imports from Java
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
// Class for the panel
// JFrame - so the frame will be treated as JFrame
// Runnable - so this can run on a thread for both paddle and ball
public class Pong_Panel extends JPanel implements Runnable{
	// Define the properties for the panel
	// static - it will only share one variable if there is multiple panels class
	// final - it prevents the variables been accidentally changed, it also helps game run a little bit faster
	static final int pongWidth = 1000;
	static final int pongHeight = (int)(pongWidth * (0.5555));
	static final int paddelWidth = 25;
	static final int paddelHeight = 100;
	static final int paddleBallDiameter = 20;
	static final Dimension paddleScreenSize = new Dimension(pongWidth,pongHeight);
	Thread panelThread;
	Image panelImage;
	Graphics panelGraphics;	
	Random panelRandomDirection;
	Pong_Paddle panelPaddle1;
	Pong_Paddle panelPaddle2;
	Pong_Ball panelBall;
	Pong_Score panelScore;
	// Constructor for the panel
	Pong_Panel(){
		newPaddles();
		newBall();
		panelScore = new Pong_Score(pongWidth,pongHeight);
		this.setFocusable(true); // So it going to read the keys that has been pressed
		this.addKeyListener(new actionListener()); // So it will going to respond to the key that has been pressed
		this.setPreferredSize(paddleScreenSize);
		panelThread = new Thread(this);
		panelThread.start();
	}
	// Method - Whenever this method has been called, it create a new ball on the screen
	public void newBall() {
		panelRandomDirection = new Random();
		panelBall = new Pong_Ball((pongWidth/2)-(paddleBallDiameter/2),panelRandomDirection.nextInt(pongHeight-paddleBallDiameter),paddleBallDiameter,paddleBallDiameter);
	}
	// Method - Whenever this method has been called, it updates the locations of the paddle
	public void newPaddles() {
		panelPaddle1 = new Pong_Paddle(0,(pongHeight/2)-(paddelHeight/2),paddelWidth,paddelHeight,1);
		panelPaddle2 = new Pong_Paddle(pongWidth-paddelWidth,(pongHeight/2)-(paddelHeight/2),paddelWidth,paddelHeight,2);
	}
	// Method - Create a image that has the dimensions of the width and the height of the game panel
	public void paint(Graphics g) {
		panelImage = createImage(getWidth(),getHeight());
		panelGraphics = panelImage.getGraphics();
		draw(panelGraphics);
		g.drawImage(panelImage,0,0,this);
	}
	// Method - Create paddles on both side of the screen
	public void draw(Graphics g) {
		panelPaddle1.draw(g); // To draw a paddle on the Left side of the screen
		panelPaddle2.draw(g); // To draw a paddle on the right side of the screen
		panelBall.draw(g); // To draw a ball on the panel
		panelScore.draw(g); // To draw the score number and white line on the panel
		// It helps with the animation
		Toolkit.getDefaultToolkit().sync(); 
	}
	// Method - Move things after each iteration
	public void move() {
		panelPaddle1.move(); // Move - Makes the paddle moving smooth and faster
		panelPaddle2.move(); // Move - Makes the paddle moving smooth and faster
		panelBall.move(); // Move - Makes the ball moving smooth and faster
	}
	// Method - Stops paddles from going out side of the window edges
	public void checkCollision() {
		// Bounce ball off from top and bottom of the window edges
		if(panelBall.y <=0) {
		   panelBall.setDirectionY(-panelBall.velocityY);
		}
		if(panelBall.y >= pongHeight-paddleBallDiameter) {
		   panelBall.setDirectionY(-panelBall.velocityY);
		}
		// Bounce ball off from the paddles
		if(panelBall.intersects(panelPaddle1)) {
		   panelBall.velocityX = Math.abs(panelBall.velocityX);
		   panelBall.velocityX++; // Increase the speed of the ball
			if(panelBall.velocityY>0)
			   panelBall.velocityY++; // Increase the speed of the ball
			else
			   panelBall.velocityY--;
			   panelBall.setDirectionX(panelBall.velocityX);
			   panelBall.setDirectionY(panelBall.velocityY);
		}
		if(panelBall.intersects(panelPaddle2)) {
		   panelBall.velocityX = Math.abs(panelBall.velocityX);
		   panelBall.velocityX++; // Increase the speed of the ball
			if(panelBall.velocityY>0)
			   panelBall.velocityY++; // Increase the speed of the ball
			else
			   panelBall.velocityY--;
			   panelBall.setDirectionX(-panelBall.velocityX);
			   panelBall.setDirectionY(panelBall.velocityY);
		}
		// When the ball touch the edge of the window, it stops
		if(panelPaddle1.y<=0)
		   panelPaddle1.y=0;
		if(panelPaddle1.y >= (pongHeight-paddelHeight))
		   panelPaddle1.y = pongHeight-paddelHeight;
		if(panelPaddle2.y<=0)
		   panelPaddle2.y=0;
		if(panelPaddle2.y >= (pongHeight-paddelHeight))
		   panelPaddle2.y = pongHeight-paddelHeight;
		// Give a player 1 point and creates new paddles & ball
		if(panelBall.x <=0) {
		   panelScore.player1++;
		   newPaddles();
		   newBall();
		   // To display Scores on the console
		   System.out.println("Player 1: "+panelScore.player2);
		}
		if(panelBall.x >= pongWidth-paddleBallDiameter) {
		   panelScore.player2++;
		   newPaddles();
		   newBall();
		   // To display Scores on the console
		   System.out.println("Player 2: "+panelScore.player1);
		}
	}
	// Method - loop for game to run in 60 frame per second
	public void run() {
		// Game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double nanoSeconds = 1000000000/amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/nanoSeconds;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	// Class - For the game to response to the mouse click
	public class actionListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			panelPaddle1.keyPressed(e);
			panelPaddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			panelPaddle1.keyReleased(e);
			panelPaddle2.keyReleased(e);
		}
	}
}