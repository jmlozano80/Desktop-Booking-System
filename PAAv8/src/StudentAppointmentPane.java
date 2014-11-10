import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.Format;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;


/**
 * 
 * @author Jose
 *
 */
public class StudentAppointmentPane
{
	//Global variables
		static Container studentAppViewPane;
		static JList<String> appoinmentList;
		static DefaultListModel listModel;
		static JButton btnBack,btnCancelAppoinment;
			
		public static void buildStudentViewAppointmentsPane()
		{	
			studentAppViewPane = MainGUI.frmMain.getContentPane(); // Get content pane
			studentAppViewPane.setLayout(null); // Apply null layout
			MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
			
			// Create controls
			 
			//ListModel
			listModel = new DefaultListModel();
			String temp=ConnectionMySql.displayAppoinment();
			
			listModel.addElement(temp);
				//JList
			appoinmentList = new JList(listModel);
				
			//appoinmentList.setAlignmentX(24);
			appoinmentList.setForeground(Color.blue);//display the appointments on blue
			appoinmentList.setFont(new Font("Fontname",Font.BOLD,18));
				//Buttons
			btnBack= new JButton("<--");
			btnCancelAppoinment = new JButton("Cancel Appoinment");
				
				//Set Btn Colours
			
					//Btn details
			btnBack.setForeground(Color.blue);
					//Btn Cancel Appointments
			btnCancelAppoinment.setForeground(Color.red);
			
			// Set border
			((JComponent) studentAppViewPane).setBorder(BorderFactory.createTitledBorder("Appointments"));
			
			// Add controls to pane
			studentAppViewPane.removeAll();//Remove everything from Login
			
			// Add JList		
			studentAppViewPane.add(appoinmentList);
			
			//add buttons to the Pane
			studentAppViewPane.add(btnBack);
			studentAppViewPane.add(btnCancelAppoinment);
			
			// Set bounds
			studentAppViewPane.setBounds(0, 0, 320, 335);
			
			//--Buttons bounds
			btnBack.setBounds(10, 320, 80, 25);
			btnCancelAppoinment.setBounds(80, 220, 160, 25);
			
			//--JList bounds
			appoinmentList.setBounds(20, 20, 280, 160);
			
			// Register action listeners
			
			//--Button btnBack Listener
			btnBack.addActionListener(new ActionListener()
		{
			 
	        public void actionPerformed(ActionEvent e)
	        {
	        	studentAppViewPane.removeAll();
	        	Calendar.buildCalendar();
	        }
	        
		});// end btnMyDetails Listener
						
			
			//--Button Cancel Appointment Listener
			btnCancelAppoinment.addActionListener(new ActionListener()
		{
			 
	        public void actionPerformed(ActionEvent e)
	        {
	        	String selectedAppointment=(String) appoinmentList.getSelectedValue();
	        	System.out.println("The length of the selectedAppointment is: "+selectedAppointment);
	        	if(selectedAppointment!=null)
	        	{
	        		System.out.println("The appointment to cancel: "+ selectedAppointment );
	        		String dateString=selectedAppointment.substring(0, 10);
	        		System.out.println("The date to cancel: "+ dateString );
	        		String timeString=selectedAppointment.substring(11,19 );
	        		System.out.println("The time to cancel: "+ timeString );
	        		
	        		String[] dateSplitted = dateString.split("-");
	    			
	    			int year = Integer.parseInt(dateSplitted[0]);
	    			int month = Integer.parseInt(dateSplitted[1])-1;
	    			int day = Integer.parseInt(dateSplitted[2]);
	    			System.out.println("AMIGOOOOOOOO " + day + "/" + month + " YEAR! " + year);
	    			
	    			GregorianCalendar cal = new GregorianCalendar(year, month, day);
	    			Date date= cal.getTime();
	    			
	    			System.out.println("The calendat time:"+date);
	    			System.out.println("The calendat time:"+ cal.getTime());
	    			Time time=java.sql.Time.valueOf(timeString); 
	    			
	        		ConnectionMySql.studentCancelAppointment(date, time);
	        		
	        		//get student id
	        			String studentID= ConnectionMySql.studentId;
	        		
	        		//get student email
	        		
	        		String studentEmail=ConnectionMySql.getStudentEmailByStudent();
	        		System.out.println("The student email= " +studentEmail);
	        		
	        		//get PAA email
	        		String paaEmail=ConnectionMySql.getPAAEmail();
	        		System.out.println("The paa email is " + paaEmail);
	        			        		
	        		//subject of the email
	    			String subject= "PAA Apponitmnet Cancellation";
	    			
	    			//body of the email
	    			String body="The appointment of "+dateString+" at " + timeString+ " was cancelled by the student "+ studentID+" . \n\n\n\n Kind Regards.";
	    			
	    			//sender is the admin
	    			String sender= "joselozano006";
	    			
	    			//recipients PAA and student
	    			String recipients=""+paaEmail+","+studentEmail+"";
	    			
	    			//Send the email
	    			new GmailSender();
	    			GmailSender.sendMail(subject, body, sender, recipients);
	    			
	    			
	        		
	        		
	        		
	        		
	        		
	        		//refresh the pane
	        		studentAppViewPane.removeAll();
	        		buildStudentViewAppointmentsPane();
	        		
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(null, "Please, select an appointment to cancel","Empty Selection",JOptionPane.ERROR_MESSAGE);
					
	        	}
	        	
	        	
	        }
	        
		});// end btnCancelAppoinment Listener		
			
			
						
			studentAppViewPane.setVisible(true);
			MainGUI.frmMain.setVisible(true);
			
		
		}//end method buildLoginPane
		
}//end class StudentAppointmentPane
