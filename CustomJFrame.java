import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * This class creates a custom JFrame to create a dietary survey GUI 
 * using GridBag layout. Also creates an action listener to write the data 
 * if submit is pressed, and set the values to default if clear was pressed.
 * 
 */
public class CustomJFrame extends JFrame {

	// All labels for JFrame
	private JLabel headingLabel = new JLabel("Personal Info");
	private JLabel firstNameLabel = new JLabel("First Name:");
	private JLabel lastNameLabel = new JLabel("Last Name:");
	private JLabel phoneNumberLabel = new JLabel("Phone Number:");
	private JLabel emailLabel = new JLabel("Email:");
	private JLabel dietaryLabel = new JLabel("Dietary Questions");
	private JLabel genderLabel = new JLabel("Sex:");
	private JLabel waterLabel = new JLabel("How many cups of water on average do you drink a day?");
	private JLabel mealsLabel = new JLabel("How many meals on average do you eat a day?");
	private JLabel checkboxLabel = new JLabel("Do any of these meals contain:");
	private JLabel walkLabel = new JLabel("How many miles on average do you walk in a day?");
	private JLabel weightLabel = new JLabel("How much do you weigh?");
	
	// All text fields for JFrame
	private JTextField firstNameTextField = new JTextField(10);
	private JTextField lastNameTextField = new JTextField(10);
	private JTextField phoneNumberTextField = new JTextField(10);
	private JTextField emailTextField = new JTextField(10);
	
	// Radio Buttons with button group for JFrame
	private JRadioButton maleRadioButton = new JRadioButton();
	private JRadioButton femaleRadioButton = new JRadioButton();
	private JRadioButton preferRadioButton = new JRadioButton();
	private ButtonGroup radioButtonGroup = new ButtonGroup();
	
	// JSpinner for JFrame
	private SpinnerNumberModel spinnerModel = 
			new SpinnerNumberModel(15, 0, 50, 1);
	private JSpinner waterIntakeSpinner = new JSpinner(spinnerModel);
	
	// JSlider for JFrame
	private JSlider mealSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 10, 3);
	
	// Check box for JFrame
	private JCheckBox wheatCheckBox = new JCheckBox();
	private JCheckBox sugarCheckBox = new JCheckBox();
	private JCheckBox dairyCheckBox = new JCheckBox();
	
	// JComboBox with an array of options to choose from
	private String[] walkOptions = {"Less than 1 mile", "More than 1 mile but less than 2 miles", "More than 2 miles but less than 3 miles", "More than 3 miles"};
	private JComboBox<String> walkComboBox = new JComboBox<String>(walkOptions);
	
	// Text field formatted with a number format only
	private NumberFormat numberFormatter = NumberFormat.getNumberInstance();
	private JFormattedTextField weightFormattedTextField = new JFormattedTextField(numberFormatter);
	
	// Submit and Clear buttons
	private JButton clearButton = new JButton("clear");
	private JButton submitButton = new JButton("submit");
	
	// Creating a new FileHandler object for output
	FileHandler fileHandler = new FileHandler();
	
	/**
	 * This inner class is used for clearing the data with clear
	 * or adding the values to the String survey data with submit
	 *
	 * Handles when the clear and submit buttons are pressed
	 */
	class InnerActionListener implements ActionListener
	{
		/**
		 * This method will add all of the values set on the GUI to the comma separated string 
		 * survey data which will be entered to the file survey_results using the method
		 * write results located in the file handler class.
		 * 
		 * @param ActionEvent e - Action being performed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			JButton button = (JButton) e.getSource();
			String name = button.getName();
			
			// If the submit button is clicked
			if(name.equals("submit"))
			{
				String surveyData = ""; // Will hold the comma separated string of all the values
				
				// Getting the current date and time
				Date date = new Date();
				SimpleDateFormat time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				String currentTime = time.format(date);
				
				// Adding the text field information and the current time to the string surveyData
				surveyData += currentTime;
				surveyData += "," + (String) firstNameTextField.getText();
				surveyData += "," + (String) lastNameTextField.getText();
				surveyData += "," + (String) phoneNumberTextField.getText();
				surveyData += "," + (String) emailTextField.getText();
				
				// Determining whether the male, female, or prefer radio button is selected and adding the value to survey data
				if(maleRadioButton.isSelected())
				{
					surveyData += "," + (String) maleRadioButton.getName();
				} // end if
				
				else if(femaleRadioButton.isSelected())
				{
					surveyData += "," + (String) femaleRadioButton.getName();
				} // end else if
				
				else if(preferRadioButton.isSelected())
				{
					surveyData += "," + (String) preferRadioButton.getName();
				} // end else if
				
				else
				{
					surveyData += "," + "null";
				} // end else
				
				// Adding the waterIntakeSpinner number to survey data 
				int value = (Integer) waterIntakeSpinner.getValue();
 				
				surveyData += "," + Integer.toString(value);
				
				// Adding the meal slider value to survey data
				surveyData += "," + mealSlider.getValue();
				
				// Determining if the checkboxes are checked and if they are, true is added to survey data, if not, false is added
				if(wheatCheckBox.isSelected())
				{
					surveyData += "," + "TRUE";
				} // end if
				
				else {
					surveyData += "," + "FALSE";
				} // end else
				
				if(sugarCheckBox.isSelected())
				{
					surveyData += "," + "TRUE";
				} // end if
				
				else {
					surveyData += "," + "FALSE";
				} // end else
				
				if(dairyCheckBox.isSelected())
				{
					surveyData += "," + "TRUE";
				} // end if
				
				else {
					surveyData += "," + "FALSE";
				} // end else
				
				// Adding the values for the combo box and the formatted text field
				surveyData += "," + (String) walkComboBox.getSelectedItem();
				
				surveyData += "," + weightFormattedTextField.getText();
				
				//Writing the comma separated string survey data to the file survey_results
				fileHandler.writeResults(surveyData);
				
				// Once the values are captured, the GUI is reset to its default values
				clearForm();
			} // end outer if
			
			else
			{
				// If clear button is pressed, reset the GUI to it's default value
				clearForm();
			} // end else
		} // end actionPerformed
		
		/**
		 * This method will reset the values in the GUI
		 * 
		 */
		private void clearForm()
		{
			
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			phoneNumberTextField.setText("");
			emailTextField.setText("");
			radioButtonGroup.clearSelection();
			wheatCheckBox.setSelected(false);
			dairyCheckBox.setSelected(false);
			sugarCheckBox.setSelected(false);
			walkComboBox.setSelectedIndex(0);
			weightFormattedTextField.setText("");
			waterIntakeSpinner.setValue(Integer.valueOf(15));
			mealSlider.setValue(3);	
		} // end clearForm
	} // end InnerActionListener
	
	/**
	 * This constructor will construct the Custom JFrame for the dietary survey
	 * by using grid bag layout and an inner action listener.
	 * 
	 */
	public CustomJFrame()
	{
		// Setting title for gui
		setTitle("Dietary Survey");
		
		// Setting the radio button names
		maleRadioButton.setName("Male");
		femaleRadioButton.setName("Female");
		preferRadioButton.setName("Other");
		
		// Setting the names for the submit or clear button
		submitButton.setName("submit");
		clearButton.setName("clear");
		
		// Creating a new action listener object and adding to submit and clear buttons
		InnerActionListener listener = new InnerActionListener();
		submitButton.addActionListener(listener);
		clearButton.addActionListener(listener);
		
		// Setting layout to gridbag layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints layoutManager = new GridBagConstraints();
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 0;
		layoutManager.insets = new Insets(1, 10, 10, 10);
		
		// Setting layout and adding the heading label
		layoutManager.gridx = 0;
		layoutManager.gridy = 0;
		this.add(headingLabel, layoutManager);
		
		// Setting layout and adding the first name label
		layoutManager.gridx = 0;
		layoutManager.gridy = 1;
		this.add(firstNameLabel, layoutManager);
		
		// Setting layout and adding the first name text field
		layoutManager.gridx = 1;
		layoutManager.gridy = 1;
		this.add(firstNameTextField, layoutManager);
		
		// Setting layout and adding the last name label
		layoutManager.gridx = 0;
		layoutManager.gridy = 2;
		this.add(lastNameLabel, layoutManager);
		
		// Setting layout and adding the last name text field
		layoutManager.gridx = 1;
		layoutManager.gridy = 2;
		this.add(lastNameTextField, layoutManager);
		
		// Setting layout and adding phone number label
		layoutManager.gridx = 0;
		layoutManager.gridy = 3;
		this.add(phoneNumberLabel, layoutManager);
		
		// Setting layout and adding the phone number text field
		layoutManager.gridx = 1;
		layoutManager.gridy = 3;
		this.add(phoneNumberTextField, layoutManager);
		
		// Setting layout and adding the email label
		layoutManager.gridx = 0;
		layoutManager.gridy = 4;
		this.add(emailLabel, layoutManager);
		
		// Setting layout and adding the email text field
		layoutManager.gridx = 1;
		layoutManager.gridy = 4;
		this.add(emailTextField, layoutManager);
		
		// Setting layout and adding the gender label
		layoutManager.gridx = 0;
		layoutManager.gridy = 5;
		this.add(genderLabel, layoutManager);
		
		// Setting the text for the radio buttons
		maleRadioButton.setText("Male                  ");
		femaleRadioButton.setText("Female              ");
		preferRadioButton.setText("Prefer not to say");
		
		// Setting layout and adding the male radio button
		layoutManager.gridx = 1;
		layoutManager.gridy = 5;
		this.add(maleRadioButton, layoutManager);
		
		// Setting layout and adding the female radio button
		layoutManager.gridx = 1;
		layoutManager.gridy = 6;
		this.add(femaleRadioButton, layoutManager);
		
		// Setting layout and adding the no preference radio button
		layoutManager.gridx = 1;
		layoutManager.gridy = 7;
		this.add(preferRadioButton, layoutManager);
		
		// Adding the three radio buttons to a button group
		radioButtonGroup.add(maleRadioButton);
		radioButtonGroup.add(femaleRadioButton);
		radioButtonGroup.add(preferRadioButton);
		
		// Setting layout and adding the dietary label
		layoutManager.gridx = 0;
		layoutManager.gridy = 8;
		this.add(dietaryLabel, layoutManager);
		
		// Setting layout and adding the water intake label
		layoutManager.gridx = 0;
		layoutManager.gridy = 9;
		this.add(waterLabel, layoutManager);
		
		//Setting layout and adding the water intake JSpinner
		layoutManager.gridx = 0;
		layoutManager.gridy = 10;
		this.add(waterIntakeSpinner, layoutManager);
		
		// Setting layout and adding the meals label
		layoutManager.gridx = 0;
		layoutManager.gridy = 11;
		this.add(mealsLabel, layoutManager);
		
		// Setting the design for the JSlider mealSlider
		mealSlider.setMinorTickSpacing(1);
		mealSlider.setMajorTickSpacing(1);
		mealSlider.setPaintTicks(true);
		mealSlider.setPaintLabels(true);
		
		// Setting layout and adding the meal JSlider
		layoutManager.gridx = 0;
		layoutManager.gridy = 12;
		this.add(mealSlider, layoutManager);
		
		// Setting layout and adding the label for the checkboxes
		layoutManager.gridx = 0;
		layoutManager.gridy = 13;
		this.add(checkboxLabel, layoutManager);
		
		// Setting the text displayed by the check boxes
		dairyCheckBox.setText("Dairy");
		wheatCheckBox.setText("Wheat");
		sugarCheckBox.setText("Sugar");
		
		// Setting layout and adding the label for the dairy check box
		layoutManager.gridx = 0;
		layoutManager.gridy = 14;
		this.add(dairyCheckBox, layoutManager);
		
		// Setting layout and adding the label for the wheat check box
		layoutManager.gridx = 0;
		layoutManager.gridy = 15;
		this.add(wheatCheckBox, layoutManager);
		
		// Setting layout and adding the label for the sugar check box
		layoutManager.gridx = 0;
		layoutManager.gridy = 16;
		this.add(sugarCheckBox, layoutManager);
		
		// Setting layout and adding the label for the walking miles label
		layoutManager.gridx = 0;
		layoutManager.gridy = 17;
		this.add(walkLabel, layoutManager);
		
		// Setting layout and adding the JComboBox walkComboBox
		layoutManager.gridx = 0;
		layoutManager.gridy = 18;
		this.add(walkComboBox, layoutManager);
		
		// Setting layout and adding the weight label
		layoutManager.gridx = 0;
		layoutManager.gridy = 19;
		this.add(weightLabel, layoutManager);
		
		// Setting the columns for the formatted text field
		weightFormattedTextField.setColumns(10);
		
		// Setting layout and adding the weight JFormattedTextField
		layoutManager.gridx = 0;
		layoutManager.gridy = 20;
		this.add(weightFormattedTextField, layoutManager);
		
		// Adding the submit and clear button to a JPanel
		JPanel panel = new JPanel();
		panel.add(clearButton);
		panel.add(submitButton);
		
		// Setting layout and adding the JPanel
		layoutManager.gridx = 0;
		layoutManager.gridy = 21;
		this.add(panel, layoutManager);	
	} // end constructor
} // end class
