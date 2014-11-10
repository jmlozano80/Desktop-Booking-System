import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;


public class WellcomePane
{
	static Container wellcomePane;

	public WellcomePane()
	{
		
	}
	
	public static void buildWellcomePane()
	{
		
			// Prepare frame
					// frmMain = new JFrame("PAT Appointment System"); // Create frame
					//frmMain.setSize(330, 375); // Set size to 400x400 pixels
			wellcomePane = MainGUI.frmMain.getContentPane(); // Get content pane
			wellcomePane.setLayout(null); // Apply null layout
			MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
					System.out.println("EEE");	
					
					// Create controls
					JLabel lblLogin = new JLabel("Login");
					JLabel lblRegister = new JLabel("Register");
					JButton btnLogin = new JButton("Login");
					JButton btnRegister = new JButton("Register");
					
					// Set border
					//wellcomePane.removeAll();
					//JPanel pnlCalendar = new JPanel(null);
					//pnlCalendar.setBorder(BorderFactory.createTitledBorder("Wellcome"));
					
					//wellcomePane.removeAll();
					((JComponent) wellcomePane).setBorder(BorderFactory.createTitledBorder("Wellcome"));
					
					// Add controls to pane
					//wellcomePane.add(pnlCalendar);
					wellcomePane.add(lblLogin);
					wellcomePane.add(lblRegister);
					wellcomePane.add(btnLogin);
					wellcomePane.add(btnRegister);
					
					// Set bounds
					wellcomePane.setBounds(0, 0, 320, 335);
					lblLogin.setBounds(10,50,50,25);
					lblRegister.setBounds(10,100,50,25);
					btnLogin.setBounds(90, 50, 100, 25);
					btnRegister.setBounds(90, 100, 100, 25);
					
					
					// Register action listeners
					btnLogin.addActionListener(new ActionListener() {
						 
			            public void actionPerformed(ActionEvent e)
			            {
			                //Execute when button is pressed
			                System.out.println("You clicked the button");
			                wellcomePane.setVisible(false);
			              // LoginPane logina = new LoginPane();
			                //LoginPane lp= new LoginPane();
			                LoginPane.buildLoginPane();
			                //lp.loginPane.setVisible(true);
			                //lp.visible();
			            }
			        });
					
					btnRegister.addActionListener(new ActionListener() {
						 
			            public void actionPerformed(ActionEvent e)
			            {
			                //Execute when button is pressed
			                System.out.println("You clicked the button");
			                wellcomePane.setVisible(false);
			              // LoginPane logina = new LoginPane();
			                RegisterPane rP= new RegisterPane();
			                rP.buildRegisterPane();
			                //lp.loginPane.setVisible(true);
			                //lp.visible();
			                //ConnectionMySql mySql= new ConnectionMySql();
			                ConnectionMySql.connectionToMySql();
			            }
			        });


					//btnLogin.addActionListener(new btnLoginAction());
					
					//btnRegister.addActionListener(new btnRegisterAction());
							
					
					// Make frame visible
					MainGUI.frmMain.setResizable(false);
					MainGUI.frmMain.setVisible(true);
					// Register action listeners
					//btnLogin.addActionListener(new btnPrev_Action());
					//btnRegister.addActionListener(new btnNext_Action());
					
					//Add action listener to button
					
	}
}
