import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Purpose: this class will create the headings for the csv file
 * 			within the constructor and write the results from the survey
 * 			to the file within writeResults() method
 */
public class FileHandler {

	// File name
	private String surveyFile = "survey_results.csv";
	
	// Wrapping file writer with printwriter
	private FileWriter fileOutput;// = new FileWriter(surveyFile);
	private PrintWriter printWriter;
	
	/**
	 * Writes the heading to the csv file
	 */
	public FileHandler()
	{
		try {
			// Writing titles to file
			fileOutput = new FileWriter(surveyFile,false);
			printWriter = new PrintWriter(fileOutput);
			printWriter.println("DateTime,FirstName,LastName,PhoneNum,Email,Sex,Water,Meals,"
								+ "Wheat,Sugar,Dairy,Miles,Weight");
			
			// Closing both file writers
			printWriter.close();
			fileOutput.close();
		} // end try
		
		// Catching any exceptions with output
		catch(IOException e)
		{
			System.out.println("File titles cannot be written");
		} // end catch
	} // end FileHandler constructor
	
	
	/**
	 * Takes the data from the survey and writes it to the csv file
	 * 
	 * @param surveyData - the line of data that is comma separated to be written
	 */
	public void writeResults(String surveyData)
	{
		try {
			// Writing the data
			fileOutput = new FileWriter(surveyFile, true);
			printWriter = new PrintWriter(fileOutput);
			printWriter.println(surveyData);
			
			printWriter.close();
			fileOutput.close();
		} // end try
		
		// Catching any output exceptions
		catch(IOException e)
		{
			System.out.println("Having trouble writing the data to the file");
		} // end catch
	}
	
}


