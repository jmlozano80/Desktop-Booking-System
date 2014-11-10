import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Jose
 *
 */
public class PAADetails
{
	//Global variables
	static Container myDetailsPane;
	static JLabel lblYourDetails, lblPAAID,lblPAAFName,lblPAALName,lblPAAEmail,lblPAACourse;
	static String paaID,paaFName,paaLName,paaEmail,paaCourse;
	static JButton backBtn;

public static void buildMyDetailsPane()
{
	myDetailsPane = MainGUI.frmMain.getContentPane(); // Get content pane
	myDetailsPane.setLayout(null); // Apply null layout
	MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
	System.out.println("myDetailsPane");	

	
		//Labels
	//lblYourDetails = new JLabel("<html><font size=5><font color=blue>Your Details"+studentFName+"</html>");
	lblYourDetails = new JLabel("<html><font size=6><font color=blue>Your Details</html>");
	lblPAAID = new JLabel("<html><font size=4><a><font color=blue>PAA ID :"+ "    "+"</a><a><font color=black>"+paaID+"</html>");
	lblPAAFName = new JLabel("<html><font size=4><a><font color=blue>PAA Name :"+ "    "+"</a><a><font color=black>"+paaFName+"</html>");
	lblPAALName = new JLabel("<html><font size=4><a><font color=blue>PAA Last Name :"+ "    "+"</a><a><font color=black>"+paaLName+"</html>");
	lblPAAEmail = new JLabel("<html><font size=4><a><font color=blue>PAA Email:"+ "    "+"</a><a><font color=black>"+paaEmail+"</html>");
	lblPAACourse = new JLabel("<html><font size=4><a><font color=blue>PAA Course :"+ "    "+"</a><a><font color=black>"+paaCourse+"</html>");
	//lblStudentPAA = new JLabel("<html><font size=4><a><font color=blue>Student PAA :"+ "    "+"</a><a><font color=black>"+studentPAA+"</html>");
	
		//JButtons		
	backBtn = new JButton("<--");
	
	// Set border
	((JComponent) myDetailsPane).setBorder(BorderFactory.createTitledBorder("My Details"));
				
		// Add controls to pane
	//myDetailsPane.removeAll();//Remove everything from previous pane
				
		//add Labels to the Pane
	myDetailsPane.add(lblYourDetails);
	myDetailsPane.add(lblPAAID);
	myDetailsPane.add(lblPAAFName);
	myDetailsPane.add(lblPAALName);
	myDetailsPane.add(lblPAAEmail);
	myDetailsPane.add(lblPAACourse);
	
	
		//add buttons to the Pane
	myDetailsPane.add(backBtn);
	
	// Set bounds
	myDetailsPane.setBounds(0, 0, 320, 335);
	
		//--Label bounds
	lblYourDetails.setBounds(95,10,320,50);
	lblPAAID.setBounds(10,60,320,50);
	lblPAAFName.setBounds(10, 90, 320, 50);
	lblPAALName.setBounds(10, 120, 320, 50);
	lblPAAEmail.setBounds(10, 150, 320, 50);
	lblPAACourse.setBounds(10, 180, 320, 50);
	
		//--Buttons bounds
	backBtn.setBounds(10, 310, 60, 25);
	
	// Register action listeners
	
	//--Button <-- Listener
	backBtn.addActionListener(new ActionListener()
	{
		 
        public void actionPerformed(ActionEvent e)
        {
            //Execute when button is pressed
            System.out.println("You clicked the back button myDetailsPane");
            myDetailsPane.removeAll();
            PAAViewAppointmentsPane.buildPAALViewAppointmentsPane();		           
        }
    });// end backBtn Listener
	
	//Set the Pane visible 
	myDetailsPane.setVisible(true);
	MainGUI.frmMain.setVisible(true);
	System.out.println("myDetailsPane" + myDetailsPane.isEnabled());
}//end method buildMyDetailsPane

}//end class PAADetails
