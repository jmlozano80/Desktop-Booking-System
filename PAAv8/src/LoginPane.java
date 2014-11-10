import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
//import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.JPasswordField;
//import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class builds the Login Panel and call the necessary methods from the class ConnectionMySql
 * @author Jose
 *
 */
public class LoginPane
{
	//Global variables
	static Container loginPane;
	static JLabel lblStundentID,lblPassword,noRegisteredLbl;
	static JButton btnLogin,backBtn;
	static JTextField studentIDTxt;
	static JPasswordField passwordTxt;
	static String id,password;
	

	
	/**
	 * This method builds and give functionality to the buttons by invoking method from the class ConnectionMySql
	 */
	public static void buildLoginPane()
	{
		loginPane = MainGUI.frmMain.getContentPane(); // Get content pane
		loginPane.setLayout(null); // Apply null layout
		MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
		System.out.println("LoginPane");	
			
			// Create controls
		
				//Labels
			 lblStundentID = new JLabel("User id:");
			 lblPassword = new JLabel("Password:");
			 noRegisteredLbl = new JLabel("<html>Not, registerd yet? <br> Please go to the previous view</html>");
				//TextFileds
			 passwordTxt= new JPasswordField();
			 studentIDTxt = new JTextField();
				//JButtons
			 btnLogin = new JButton("Login");			
			 backBtn = new JButton("<--");
						
			// Set border
			((JComponent) loginPane).setBorder(BorderFactory.createTitledBorder("Login"));
			
			// Add controls to pane
			loginPane.removeAll();//Remove everything from WellcomePane
			
				//add Labels to the Pane
				loginPane.add(lblStundentID);
				loginPane.add(lblPassword);
				loginPane.add(noRegisteredLbl);
				
				//add TextFields to the Pane
				loginPane.add(studentIDTxt);
				loginPane.add(passwordTxt);
			
				//add buttons to the Pane
				loginPane.add(btnLogin);
				loginPane.add(backBtn);
			
			// Set bounds
			loginPane.setBounds(0, 0, 320, 335);
			
				//--Label bounds
				lblStundentID.setBounds(10,50,50,50);
				lblPassword.setBounds(10,100,80,50);
				noRegisteredLbl.setBounds(10, 250, 320, 50);
			
				//--TextField bounds
				studentIDTxt.setBounds(90, 65,200 , 25);
				passwordTxt.setBounds(90,115,200 , 25);
			
				//--Buttons bounds
				btnLogin.setBounds(110, 200, 100, 25);
				backBtn.setBounds(10, 310, 60, 25);
			
			// Register action listeners
				
				//--Button Login Listener
			btnLogin.addActionListener(new ActionListener()
			{
				 
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	                System.out.println("You clicked the button login");
	                try
	                {
						login();
						//loginPAA();
					}
	                catch (SQLException e1) 
	                {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                if (ConnectionMySql.isLogged==true)
	                {
	                	System.out.println("Logged");
	                	loginPane.removeAll();                	
	                	Calendar.buildCalendar();
	                }
	               /* if(ConnectionMySql.isLoggedPAA==true)
	                {
	                	System.out.println("PAA Logged");
	                	loginPane.removeAll();  
	                }*/
	                else
	                {
	                	try 
	                	{
							loginPAA();
						}
	                	catch (SQLException e1) 
	                	{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                	 if(ConnectionMySql.isLoggedPAA==true)
	 	                {	
	 	                	System.out.println("PAA Logged");
	 	                	loginPane.removeAll(); 
	 	                	PAAViewAppointmentsPane.buildPAALViewAppointmentsPane();
	 	                }
	                	 else
	                	 {
	                		 noRegisteredLbl.setText("<html><font color=red>Credentials not correct<br>If the problem continues contact admin </html>");
	                	 }
	                 }	
	            }
	        });// end btnLogin Listener
			
				
				//--Button Login Listener
			backBtn.addActionListener(new ActionListener()
			{
				 
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	                System.out.println("You clicked the back button LoginPane");
	                loginPane.removeAll();
	                WellcomePane.buildWellcomePane();		           
	            }
	        });// end backBtn Listener

			loginPane.setVisible(true);

			
	}//end method buildLoginPane
	
	/**
	 * IMPORTANT PROBABLY I CAN DELETE THE 2 METHODS BELOWS BY GETTING FROM THE CLASS ConnectionMySql THE PARAMETERS
	 */
	/**
	 * @throws SQLException 
	 * 
	 */
	public static void login() throws SQLException
	{		
		 id= studentIDTxt.getText();
		 password = new String(passwordTxt.getPassword());
		 //System.out.println("pass= " + passwordTxt.getPassword());
		System.out.println("The id: " + id + "The password: "+ password);
		//Call to the method insertStudent of the class ConnectionMySql passing the parameters (id, fName, lName, course, email, password)
		ConnectionMySql.login(id, password);		
		
		
	}//end method login
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public static void loginPAA() throws SQLException
	{		
		 id= studentIDTxt.getText();
		 password = new String(passwordTxt.getPassword());
		 System.out.println("The id: " + id + "The password: "+ password);
		//Call to the method insertStudent of the class ConnectionMySql passing the parameters (id, fName, lName, course, email, password)
		ConnectionMySql.loginPAA(id, password);		
		
		
	}//end method login
	

}//end class LoginPane
