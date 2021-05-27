// Imports from Java
import java.awt.*;
import javax.swing.*;
// Class for the frame
// JFrame - so the frame will be treated as JFrame
public class Pong_Frame extends JFrame{
	Pong_Panel panel;
	// Attributes for the frames
	// Constructor for the frame
	Pong_Frame(){
		panel = new Pong_Panel();
		this.add(panel); // Add the panel to this frame
		this.setTitle("Delong's Pong"); // Add title for the frame
		this.setBackground(Color.DARK_GRAY); // Set the background colour
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when exit
		this.pack(); // This let window to be sized to fit the preferred size and layouts of its subcomponents
		this.setVisible(true); // So the frame will be visible for the player
		this.setResizable(false); // So the user doesn't accidentally changed the size of the frame
		this.setLocationRelativeTo(null);// So the frame will be set to the centre of the screen
	}
}