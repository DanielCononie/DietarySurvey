/**
 * Author: Daniel Cononie
 * Course: COP 3503
 * Project #: 4
 * Project name: Dietary survey GUI
 * Due Date: 12-10-2022
 * 
 * Purpose: This program will create a graphical user interface to retrieve
 * 			dietary information from the user and then store the information in the file 
 * 			survey_results.csv
 */
import javax.swing.JFrame;

public class Project4 {

	public static void main(String[] args) {
		
		// Creating a new CustomJFrame
		CustomJFrame frame = new CustomJFrame();
		
		// Setting the GUI to close when exit is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		
		frame.setVisible(true);
	}

}
