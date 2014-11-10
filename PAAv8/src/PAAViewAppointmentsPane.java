import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

//import Calendar.tblCalendarRenderer;


/**
 * 
 * @author Jose
 *
 */
public class PAAViewAppointmentsPane
{
	//Global variables
	static Container PAALViewPane;
	static JList<String> appoinmentList;
	static DefaultListModel listModel;
	static JButton btnMyDetails,btnCancelAppoinment, btnRefresh;
		
	public static void buildPAALViewAppointmentsPane()
	{	
		PAALViewPane = MainGUI.frmMain.getContentPane(); // Get content pane
		PAALViewPane.setLayout(null); // Apply null layout
		MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
		
		// Create controls
			
		//ListModel
		listModel = new DefaultListModel();
		//ConnectionMySql.displayAppoinmentsForPAA();
		ArrayList<String> appointments=ConnectionMySql.displayAppoinmentsForPAA();
		
		if(appointments.size()>0)
		{
			for(int i=0;i<appointments.size();i++)
			{
				listModel.addElement(appointments.get(i));
			}
			
		}
		
		
		
		
			//JList
		appoinmentList = new JList(listModel);
			
		//set the font size
		
		appoinmentList.setForeground(Color.blue);//display the appointments on blue
		appoinmentList.setFont(new Font("Fontname",Font.BOLD,18));
			//Buttons
		btnMyDetails= new JButton("My Info");
		btnCancelAppoinment = new JButton("Cancel Appoinment");
		btnRefresh	= new JButton("Refresh");
			//Set Btn Colours
		
				//Btn details
		btnMyDetails.setForeground(Color.blue);
				
				//Btn Cancel Appointments
		btnCancelAppoinment.setForeground(Color.red);
				
				//Btn refresh
		btnRefresh.setForeground(Color.green.darker());
		
		// Set border
		((JComponent) PAALViewPane).setBorder(BorderFactory.createTitledBorder("Appointments"));
		
		// Add controls to pane
		PAALViewPane.removeAll();//Remove everything from Login
		
		// Add JList		
		PAALViewPane.add(appoinmentList);
		
		//add buttons to the Pane
		PAALViewPane.add(btnMyDetails);
		PAALViewPane.add(btnCancelAppoinment);
		PAALViewPane.add(btnRefresh);
		// Set bounds
		PAALViewPane.setBounds(0, 0, 320, 335);
		
		//--Buttons bounds
		btnMyDetails.setBounds(220, 320, 80, 25);
		btnCancelAppoinment.setBounds(10, 320, 160, 25);
		btnRefresh.setBounds(110, 230, 80, 25);
		
		//--JList bounds
		appoinmentList.setBounds(20, 20, 280, 200);
		
		// Register action listeners
		
		//--Button My Info Listener
		btnMyDetails.addActionListener(new ActionListener()
	{
		 
        public void actionPerformed(ActionEvent e)
        {
        	PAALViewPane.removeAll();
        	//loginPane.remove(loginPane);
        	//loginPane.setVisible(false);
        	ConnectionMySql.myPAADetails();
        	PAADetails.buildMyDetailsPane();
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
    			
    			/*//send email to confirm cancellation
    			String subject= "PAA appoinment Cancelation";
    			String body="The appoinment of "+cal.getTime()+" was cancelled by the PAA";
    				//PAA adimn
    			String sender="joselozano0066";
    				//send to
    			//recipients
    			String recipients="jmlozano80@gmail.com,paa007londonmet@gmail.com";
    			//String recipients="";
    			new CopyOfGmailSender();
    			CopyOfGmailSender.sendMail(subject, body, sender, recipients);*/
    	//From here preparation to send an email		
    			//get student email
    			String [] nameSplited= selectedAppointment.split("/");
    			String dateEmail=nameSplited[0];
    			String timeEmail=nameSplited[1];
    			String userName=nameSplited[2];
    			System.out.println("userName= "+userName);
    			String studentEmail=ConnectionMySql.getStudentEmail(userName);
    			System.out.println("The student email is:" +studentEmail);
    			
    			//get paa email
    			String paaEmail=ConnectionMySql.getPAAEmail();
    			System.out.println("The PAA email is ="+paaEmail);
    			
    			//subject of the email
    			String subject= "PAA Apponitmnet Cancellation";
    			
    			//body of the email
    			String body="The appointment of "+dateEmail+" at " + timeEmail+" with the student "+userName+ " was cancelled by the PAA. \n\n\n\n Kind Regards.";
    			
    			//sender is the admin
    			String sender= "joselozano006";
    			
    			//recipients PAA and student
    			String recipients=""+paaEmail+","+studentEmail+"";
    			
    			//Send the email
    			new GmailSender();
    			GmailSender.sendMail(subject, body, sender, recipients);
    			
    			
    			
    			
    			
    			
    			
    			//refresh the Pane
        		ConnectionMySql.paaCancelAppointment(date, time);
        		PAALViewPane.removeAll();
            	buildPAALViewAppointmentsPane();
            	
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(null, "Please, select an appointment to cancel","Empty Selection",JOptionPane.ERROR_MESSAGE);
				
        	}
        }
        
	});// end btnCancelAppoinment Listener		
		
		//TODO --Button refresh  Listener
		btnRefresh.addActionListener(new ActionListener()
	{
		 
        public void actionPerformed(ActionEvent e)
        {
        	PAALViewPane.removeAll();
        	buildPAALViewAppointmentsPane();
        }
        
	});// end btnMyDetails Listener
		
					
		PAALViewPane.setVisible(true);
		MainGUI.frmMain.setVisible(true);
		
	
	}//end method buildLoginPane
	
	/**
	 * IT IS NOT IN USE
	 * 
	 * This method translate the month int to month string
	 * @param theMonth
	 * @return
	 */
	public static int getMonthAsInt(String theMonth) {
		int monthAsInt = -1;
		if (theMonth.equals("January")) {
			monthAsInt = 0;
		} else if (theMonth.equals("February")) {
			monthAsInt = 1;
		} else if (theMonth.equals("March")) {
			monthAsInt = 2;
		} else if (theMonth.equals("April")) {
			monthAsInt = 3;
		} else if (theMonth.equals("May")) {
			monthAsInt = 4;
		} else if (theMonth.equals("June")) {
			monthAsInt = 5;
		} else if (theMonth.equals("July")) {
			monthAsInt = 6;
		} else if (theMonth.equals("August")) {
			monthAsInt = 7;
		} else if (theMonth.equals("September")) {
			monthAsInt = 8;
		} else if (theMonth.equals("October")) {
			monthAsInt = 9;
		} else if (theMonth.equals("November")) {
			monthAsInt = 10;
		} else if (theMonth.equals("December")) {
			monthAsInt = 11;
		}
		return monthAsInt;
	} // End of getMonthAsInt method
	
	
	
}//end class PAALoggedPane
