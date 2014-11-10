import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *This class builds the e Student  for Details 
 * @author Jose
 *
 */

public class MyDetailsPane
{
	//Global variables
		static Container myDetailsPane;
		static JLabel lblYourDetails, lblStundentID,lblStudentFName,lblStudentLName,lblStudentEmail,lblStudentCourse,lblStudentPAA;
		static String studentID,studentFName,studentLName,studentEmail,studentCourse,studentPAA,studentPAALName;
		static JButton backBtn;
	
	public static void buildMyDetailsPane()
	{
		myDetailsPane = MainGUI.frmMain.getContentPane(); // Get content pane
		myDetailsPane.setLayout(null); // Apply null layout
		MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
		System.out.println("myDetailsPane");	
	 //test with variables
		
		//studentLName="Lozano";
		/*studentID="jml";
		studentFName="jose";
		studentLName="Lozano";
		studentEmail= "jmlozano80@gamil.com";
		studentCourse="Computing";
		studentPAA="David Hawells";*/
		// Create controls
		//studentID=LoginPane.studentIDTxt.getText()
			//Labels
		//lblYourDetails = new JLabel("<html><font size=5><font color=blue>Your Details"+studentFName+"</html>");
		lblYourDetails = new JLabel("<html><font size=6><font color=blue>Your Details</html>");
		lblStundentID = new JLabel("<html><font size=4><a><font color=blue>Student ID :"+ "    "+"</a><a><font color=black>"+studentID+"</html>");
		lblStudentFName = new JLabel("<html><font size=4><a><font color=blue>Student Name :"+ "    "+"</a><a><font color=black>"+studentFName+"</html>");
		lblStudentLName = new JLabel("<html><font size=4><a><font color=blue>Student Last Name :"+ "    "+"</a><a><font color=black>"+studentLName+"</html>");
		lblStudentEmail = new JLabel("<html><font size=4><a><font color=blue>Student Email:"+ "    "+"</a><a><font color=black>"+studentEmail+"</html>");
		lblStudentCourse = new JLabel("<html><font size=4><a><font color=blue>Student Course :"+ "    "+"</a><a><font color=black>"+studentCourse+"</html>");
		lblStudentPAA = new JLabel("<html><font size=4><a><font color=blue>Student PAA :"+ "    "+"</a><a><font color=black>"+studentPAA+" "+studentPAALName+"</html>");
		
			//JButtons		
		backBtn = new JButton("<--");
		
		// Set border
		((JComponent) myDetailsPane).setBorder(BorderFactory.createTitledBorder("My Details"));
					
			// Add controls to pane
		//myDetailsPane.removeAll();//Remove everything from previous pane
					
			//add Labels to the Pane
		myDetailsPane.add(lblYourDetails);
		myDetailsPane.add(lblStundentID);
		myDetailsPane.add(lblStudentFName);
		myDetailsPane.add(lblStudentLName);
		myDetailsPane.add(lblStudentEmail);
		myDetailsPane.add(lblStudentCourse);
		myDetailsPane.add(lblStudentPAA);
		
			//add buttons to the Pane
		myDetailsPane.add(backBtn);
		
		// Set bounds
		myDetailsPane.setBounds(0, 0, 320, 335);
		
			//--Label bounds
		lblYourDetails.setBounds(95,10,320,50);
		lblStundentID.setBounds(10,60,320,50);
		lblStudentFName.setBounds(10, 90, 320, 50);
		lblStudentLName.setBounds(10, 120, 320, 50);
		lblStudentEmail.setBounds(10, 150, 320, 50);
		lblStudentCourse.setBounds(10, 180, 320, 50);
		lblStudentPAA.setBounds(10, 210, 320, 50);
		
			//--Buttons bounds
		backBtn.setBounds(10, 310, 60, 25);
		
		// Register action listeners
		
		//--Button Login Listener
		backBtn.addActionListener(new ActionListener()
		{
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the back button myDetailsPane");
                myDetailsPane.removeAll();
               Calendar.buildCalendar();		           
            }
        });// end backBtn Listener
		
		//Set the Pane visible 
		myDetailsPane.setVisible(true);
		MainGUI.frmMain.setVisible(true);
		System.out.println("myDetailsPane" + myDetailsPane.isEnabled());
	}//end method buildMyDetailsPane



}// end class MyDetailsPane 
