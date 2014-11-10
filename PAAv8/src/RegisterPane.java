import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Jose
 *
 */
public class RegisterPane
{
	static Container registerContainer;
	JLabel lblStudentID,lblStudentFName,lblStudentLName,lblStudentLEmail,lblStudentLCourse,lblPassword,lblRePassword,lblErrorMessage;
	JTextField studentIDTxt, studentFNameTxt, studentLNameTxt, studentEmailTxt;
	JPasswordField passwordTxt,rePasswordTxt;
	JComboBox coursesComboBox;
	JButton btnRegistration, backBtn;
	//courses drop down menu auto-populate
	ArrayList<String> coursesArrayList=ConnectionMySql.populateDropDownCourseRegistration();
	String[] courses = coursesArrayList.toArray(new String[coursesArrayList.size()]);
	boolean completed,samePassword,userExist;
	String errorMessage;
	String studentId,studenFName,studentLName,studentEmail,studentCourse,studentPassword;
	
	final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	
	public void buildRegisterPane()
	{
		registerContainer = MainGUI.frmMain.getContentPane(); // Get content pane
		registerContainer.setLayout(null); // Apply null layout
		MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
			System.out.println("registerContainer");	
			
			// Create controls
				//--Labels--
			 lblStudentID = new JLabel("Student ID:");
			 lblStudentFName = new JLabel("First Name:");
			 lblStudentLName = new JLabel("Last Name:");
			 lblStudentLEmail = new JLabel("email:");
			 lblStudentLCourse = new JLabel("Course:");
			 lblPassword = new JLabel("Password");
			 lblRePassword = new JLabel("Re-Password");
			 lblErrorMessage = new JLabel();
			
				//TextFields
			
			 studentIDTxt = new JTextField();
			 studentFNameTxt = new JTextField();
			 studentLNameTxt = new JTextField();
			 studentEmailTxt = new JTextField();
			 coursesComboBox = new JComboBox(courses);
			 passwordTxt= new JPasswordField();
			 rePasswordTxt= new JPasswordField();
			 
			 	//Buttons
			 btnRegistration = new JButton("Registration");	
			 backBtn = new JButton("<--");
			
			// Set border
			registerContainer.removeAll();
			((JComponent) registerContainer).setBorder(BorderFactory.createTitledBorder("Registration"));
			
			// Add controls to pane
			registerContainer.removeAll();
			
				//add labels
			registerContainer.add(lblStudentID);
			registerContainer.add(lblStudentFName);
			registerContainer.add(lblStudentLName);
			registerContainer.add(lblStudentLEmail);
			registerContainer.add(lblStudentLCourse);
			registerContainer.add(lblPassword);
			registerContainer.add(lblRePassword);
			registerContainer.add(lblErrorMessage);
			
			
				//add textFields
			registerContainer.add(studentIDTxt);
			registerContainer.add(studentFNameTxt);
			registerContainer.add(studentLNameTxt);
			registerContainer.add(studentEmailTxt);
			registerContainer.add(coursesComboBox);
			registerContainer.add(passwordTxt);
			registerContainer.add(rePasswordTxt);
			
				//add btns
			registerContainer.add(btnRegistration);
			registerContainer.add(backBtn);

			
			// Set bounds
			registerContainer.setBounds(0, 0, 320, 335);
				
				//labels bounds			
			lblStudentID.setBounds(10,25,80,25);
			lblStudentFName.setBounds(10,55,80,25);
			lblStudentLName.setBounds(10,85,80,25);
			lblStudentLEmail.setBounds(10,115,80,25);
			lblStudentLCourse.setBounds(10,145,80,25);
			lblPassword.setBounds(10,175,80,25);
			lblRePassword.setBounds(10,210,80,25);
			lblErrorMessage.setBounds(10,235,320,25);	
				
				//textFileds bounds
			studentIDTxt.setBounds(90, 25,200 ,25);
			studentFNameTxt.setBounds(90, 55 ,200,25);
			studentLNameTxt.setBounds(90,85,200 ,25);
			studentEmailTxt.setBounds(90, 115, 200, 25);
			coursesComboBox.setBounds(90, 145, 200, 25);
			passwordTxt.setBounds(90, 175, 200, 25);
			rePasswordTxt.setBounds(90, 210, 200, 25);
			
				//buttons bounds
			btnRegistration.setBounds(110, 270, 125, 25);
			backBtn.setBounds(10, 320, 60, 25);
			
			
			
			// Register action listeners for Buttons
			
			//ActionListener for Registration btn
			btnRegistration.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {		
	            	String password=new String(passwordTxt.getPassword());
	            	
	            	
	                //Checks if the form is completed by calling the method areAllTextFieldDone() if false show error message
	               if( areAllTextFieldDone()==false)
	               {
	            	   	errorMessage="<html><font color=red><p align:center> Please complete every field</html>";
	       	    		lblErrorMessage.setText(errorMessage);
	               }
	               //UserName greater than 10
	               else if(studentIDTxt.getText().length()>10||studentIDTxt.getText().length()<3)
	               {
	            	   errorMessage="<html><font color=red><p align:center> StudentID between 3 and 10 characteres</html>";
	       	    		lblErrorMessage.setText(errorMessage);
	               }
	               //First name >45
	               else if(studentFNameTxt.getText().length()>45)
	               {
	            	   errorMessage="<html><font color=red><p align:center> First name must be less than 46 characteres</html>";
	       	    		lblErrorMessage.setText(errorMessage);
	               }
	               //Last Name>45
	               else if(studentLNameTxt.getText().length()>45)
	               {
	            	   errorMessage="<html><font color=red><p align:center> Last name must be less than 46 characteres</html>";
	       	    		lblErrorMessage.setText(errorMessage);
	               }
	               //password<45
	               else if(password.length()>15||password.length()<5)
	               {
	            	   errorMessage="<html><font color=red><p align:center> Password between 5 and 15 characteres</html>";
	       	    		lblErrorMessage.setText(errorMessage);
	               }
	               //email validation email1.matches(EMAIL_REGEX);
	               else if(studentEmailTxt.getText().matches(EMAIL_REGEX)==false)
	               {
	            	   errorMessage="<html><font color=red><p align:center> Email not valid</html>";
	       	    		lblErrorMessage.setText(errorMessage);
	               }
	               //email>45
	               else if(studentEmailTxt.getText().length()>45)
	               {
	            	   errorMessage="<html><font color=red><p align:center> Email cannot be more than 45 characteres</html>";
	       	    		lblErrorMessage.setText(errorMessage);
	               }
	               //Checks if the password mismatch by calling the method arePasswordTheSame() if false show error message
	               else if (arePasswordTheSame()==false)
	               {
	            	   	errorMessage="<html><font color=red><p align:center> Password mismatched</html>";
	       	    		lblErrorMessage.setText(errorMessage);
	               }
	               else
					try
	               		{	//checks if the studentId is already registered
						if(ConnectionMySql.studentIDExist(studentIDTxt.getText()))
						   {
							 	errorMessage="<html><font color=red><p align:center> Student id Registered</html>";
			       	    		lblErrorMessage.setText(errorMessage);
						   }
						   else
						   {	registerIntoDb();
						   		registerContainer.removeAll();
						   		LoginPane.buildLoginPane();
						   		errorMessage="";
								lblErrorMessage.setText(errorMessage);
								LoginPane.noRegisteredLbl.setText("<html> <font color=blue>You have been registered <br> Please login to arrange appoinment</html>");
						   }
					} 
	               catch (SQLException e1) 
	               {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               
	            }
	        });//end method Action Listener for the btn Register
			
			// ActionListener for backBtn
			backBtn.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	                System.out.println("You clicked the back button REgisterPane");
	                registerContainer.removeAll();
	                WellcomePane.buildWellcomePane();
		            
	            }
	        });//end method Action Listener for the backBtn
			
			
	        coursesComboBox.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Do something when you select a value

	            }
	        });
			
		
			registerContainer.setVisible(true);
		
			
}
	/**
	 * This method checks if the form is complete
	 * 
	 * @return complete
	 */
	public boolean areAllTextFieldDone()
	{
		
		 System.out.println(studentIDTxt.getText());
		 if(studentIDTxt.getText().isEmpty() || studentFNameTxt.getText().isEmpty() || studentLNameTxt.getText().isEmpty() ||
				studentEmailTxt.getText().isEmpty() || passwordTxt.getText().isEmpty() || rePasswordTxt.getText().isEmpty() )
		{
			System.out.println(studentIDTxt.getText());
			completed=false;
			return completed;
		}
		else			
		{
			completed=true;
			return completed;
		}
    	
		
	}//end method areAllTextFieldDone()
	
	
	/**
	 * This method check if the password are the same
	 * 
	 * @return samePassword
	 */
	public boolean arePasswordTheSame()
	{	
		
		String password=new String(passwordTxt.getPassword());
		String rePassword=new String(rePasswordTxt.getPassword());
				
		//samePassword
		if (password.equals(rePassword))
		{
			samePassword=true;
			return samePassword;
		}
		else
		{
			samePassword=false;
			return samePassword;
		}
	}//end method arePasswordTheSame()
	
	
	/**
	 * This method is a helper to insert students into the database
	 */
	public void registerIntoDb()
	{
	
		studentId= studentIDTxt.getText();
		studenFName = studentFNameTxt.getText();
		studentLName= studentLNameTxt.getText();
		studentCourse=  coursesComboBox.getSelectedItem().toString();
		studentEmail = studentEmailTxt.getText();
		studentPassword = new String(passwordTxt.getPassword());
		
		
		System.out.println("The student email is :"+studentEmail);
		//Call to the method insertStudent of the class ConnectionMySql passing the parameters (id, fName, lName, course, email, password)
		ConnectionMySql.insertStudent(studentId, studenFName, studentLName, studentCourse, studentEmail, studentPassword);		
		
	}// end method registerIntoDb() 


	/**
	 * email validation
	 */
	
}//end class RegisterPane
