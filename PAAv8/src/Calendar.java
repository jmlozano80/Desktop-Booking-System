/*Contents of CalendarProgran.class */

//Import packages
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calendar
{
	static JLabel lblMonth, lblYear, lblInstruction;
	static JButton btnPrev, btnNext, btnTimeSlots, btnArrangeAppoinment, btnMyDetails, btnMyAppointments;
	static JTable tblCalendar, tblDaySlot;
	static JComboBox cmbYear;
	//static JFrame frmMain;
	static Container pane, paneDaySlot;
	static DefaultTableModel mtblCalendar, mtblDaySlot; // Table model
	static JScrollPane stblCalendar, stblDaySlot; // The scrollpane
	static JPanel pnlCalendar, pnlDaySlot;
	static int realYear, realMonth, realDay, currentYear, currentMonth,dayOfMonth;
	static String[] headers = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
	static String[] months = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November","December" };
	static JFrame frmMainDaySlot;
	static String dayOfWeek,selectedTimeSlot, selectedAvailability;

	/*
	 * Main method
	 */
	public static void buildCalendar()
	{
		// Look and feel
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		// Prepare frame
		
		//frmMain = new JFrame("PAT calendar"); // Create frame
		//frmMain.setSize(330, 375); // Set size to 400x400 pixels
		MainGUI.frmMain.setTitle(LoginPane.studentIDTxt.getText());
		pane = MainGUI.frmMain.getContentPane(); // Get content pane
		pane.setLayout(null); // Apply null layout
		MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
		
		pane = MainGUI.frmMain.getContentPane(); // Get content pane
		pane.setLayout(null); // Apply null layout
		MainGUI.frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X// is clicked

		// Create controls
		lblMonth = new JLabel("January");
		lblYear = new JLabel("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton("<<");
		btnNext = new JButton(">>");
		btnTimeSlots = new JButton("Day Slots");
		btnMyDetails = new JButton("My Details");
		btnMyAppointments = new JButton("My Appointments");
		mtblCalendar = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}
		};
		tblCalendar = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(tblCalendar);
		pnlCalendar = new JPanel(null);

		// Set border
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

		// Register action listeners
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());
		btnTimeSlots.addActionListener(new btnTimeSlots_Action());
		btnMyDetails.addActionListener(new btnMyDetails_Action());
		btnMyAppointments.addActionListener(new btnMyAppointments_Action());
		
		pane.removeAll();//remove all the components of the container
		// Add controls to pane
		pane.add(pnlCalendar);
		pnlCalendar.add(lblMonth);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(btnPrev);
		pnlCalendar.add(btnNext);
		pnlCalendar.add(stblCalendar);
		pnlCalendar.add(btnTimeSlots);
		pnlCalendar.add(btnMyDetails); //Btn to see the students details
		pnlCalendar.add(btnMyAppointments);
		

		// Set bounds
		pnlCalendar.setBounds(0, 0, 330, 420);
		lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25,	100, 25);
		lblYear.setBounds(10, 305, 90, 20);
		btnTimeSlots.setBounds(110, 305, 80, 20);
		cmbYear.setBounds(230, 305, 80, 20);
		btnPrev.setBounds(10, 25, 50, 25);
		btnNext.setBounds(260, 25, 50, 25);
		stblCalendar.setBounds(10, 50, 300, 250);
		//TODO
		btnMyDetails.setBounds(5, 340, 120, 25);
		btnMyAppointments.setBounds(200, 340, 120, 25);
		//set color for btns details and appointments
		btnMyDetails.setBackground(Color.blue);
		btnMyAppointments.setBackground(Color.blue);
		btnMyDetails.setForeground(Color.blue);
		btnMyAppointments.setForeground(Color.BLUE);
		// Make frame visible
		MainGUI.frmMain.setResizable(false);
		MainGUI.frmMain.setVisible(true);
		btnTimeSlots.setVisible(false);

		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		// Add headers
		
		for (int i = 0; i < 7; i++)
		{
			mtblCalendar.addColumn(headers[i]);
		}

		tblCalendar.getParent().setBackground(tblCalendar.getBackground()); // Set
																			// background

		// No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		tblCalendar.setRowHeight(38);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		// Populate table
		for (int i = realYear; i <= realYear + 1; i++) 
		{
			cmbYear.addItem(String.valueOf(i));
		}

		// Refresh calendar
		refreshCalendar(realMonth, realYear); 
		// Build Day Slot GUI
		TimeSlotsGUI.buildGUI();
		
	}//end main method

	
	/**
	 * This method refresh the calendar when the buttons "<<", ">>"are pressed or the year changes
	 * @param month
	 * @param year
	 */
	public static void refreshCalendar(int month, int year) 
	{
		// Variables
		int nod, som; // Number Of Days, Start Of Month

		// Allow/disallow buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		
		// Disable the buttons "<<", ">>" when the calendar  will be out of bounds
		// Too early
		if (month == realMonth && year <= realYear) 
		{
			btnPrev.setEnabled(false);
		}
		// Too late
		if (month == 11 && year >= realYear + 1)
		{
			btnNext.setEnabled(false);
		} 
		lblMonth.setText(months[month]); // Refresh the month label (at the top)
		lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25,180, 25); // Re-align label with calendar
		cmbYear.setSelectedItem(String.valueOf(year)); // Select the correct year in the combo box

		// Clear table
		for (int i = 0; i < 6; i++) 
		{
			for (int j = 0; j < 7; j++)
			{
				mtblCalendar.setValueAt(null, i, j);

			}
		}//end for loop

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		// if startOfMonth
		if (som == GregorianCalendar.SUNDAY)
		{
			som = 7;
		}
		else 
		{
			som--;
		} 

		// Draw calendar
		for (int i = 1; i <= nod; i++)
		{
			int row = new Integer((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);

		}//end for loop

		// Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0),new tblCalendarRenderer());
	
	}//end method refreshCalendar

	/**
	 * 
	 *This class draw the calendar
	 *
	 */

	static class tblCalendarRenderer extends DefaultTableCellRenderer
	{
		//variables
		static String selectedDay, selectedMonth;
		static int dayOfMonth;
		static String workingDays=ConnectionMySql.paaWorkingDays();
		//System.out.println("The working days are: "+workingDays );
		
		
		/**
		 * This method draw the calendar and helps to select a cell
		 */
		public Component getTableCellRendererComponent(JTable table,Object value, boolean selected, boolean focused, int row,int column)
		{
			
			super.getTableCellRendererComponent(table, value, selected,focused, row, column);
			// No Working days on red
			if (!workingDays.contains("Mon") && column == 0) {
				setBackground(new Color(255, 220, 220));
			}
			if (column == 5 || column == 6 || (!workingDays.contains("Mon") && column == 0)||(!workingDays.contains("Tue") && column == 1)
					||(!workingDays.contains("Wed") && column == 2)||(!workingDays.contains("Thu") && column == 3)||(!workingDays.contains("Fri") && column == 4)) 
			{ 
				setBackground(new Color(255, 220, 220));
			}
			//week
			else 
			{
				setBackground(new Color(255, 255, 255));
			}
			// Select a day"Improve this" Selected change the color
			if (selected == true && value != null)
			{
				setBackground(new Color(220, 220, 0));
				// value.toString()+"/"+lblMonth.getText()+" "+CalendarProgram.headers[column]);
				dayOfWeek=Calendar.headers[column];
				selectedMonth=lblMonth.getText();
				selectedDay = value.toString() + "/" + lblMonth.getText() + "/"	+ Calendar.headers[column];
				
				lblYear.setText(selectedDay);
				//Not show the button if the PAA is not working that day
				if  (column == 5 || column == 6 || (workingDays.contains("Mon") && column == 0)||(workingDays.contains("Tue") && column == 1)
					||(workingDays.contains("Wed") && column == 2)||(workingDays.contains("Thu") && column == 3)||(workingDays.contains("Fri") && column == 4)) 
				{ 
					btnTimeSlots.setVisible(true);
				}
				dayOfMonth = Integer.parseInt(value.toString());
				if  (column == 5 || column == 6 || (!workingDays.contains("Mon") && column == 0)||(!workingDays.contains("Tue") && column == 1)
						||(!workingDays.contains("Wed") && column == 2)||(!workingDays.contains("Thu") && column == 3)||(!workingDays.contains("Fri") && column == 4)) 
				{ 
					btnTimeSlots.setVisible(false);
				}

			}
			///Modify
			
			if (value != null) 
			{	
				// Today has a different colour
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear)
				{ 
					setBackground(new Color(220, 220, 255));
				}
			}
			setBorder(null);
			setForeground(Color.black);
			return this;
			
		}// end method getTableCellRendererComponent
		
	}//end class tblCalendarRenderer

	
	/**
	 * 
	 * This class provides functionality to the button "<<" 
	 *
	 */
	static class btnPrev_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	// Back one year
			if (currentMonth == 0)
			{ 
				currentMonth = 11;
				currentYear -= 1;
			}
			// Back one month
			else 
			{ 
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		
		}//end method actionPerformed
	
	}//end class btnPrev_Action

	
	/**
	 * 
	 * This class provides functionality to the button ">>" 
	 *
	 */
	static class btnNext_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	// Forward one year
			if (currentMonth == 11)
			{ 
				currentMonth = 0;
				currentYear += 1;
			}
			// Forward one month
			else 
			{ 
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		
		}//end method actionPerformed
	
	}//end class btnNext_Action

	/**
	 * 
	 * This class provides functionality to the combo box for the years
	 *
	 */
	static class cmbYear_Action implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (cmbYear.getSelectedItem() != null) 
			{
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
			
		}//end method actionPerformed
	
	}//end class cmbYear_Action

	
	/**
	 * 
	 * This class provides functionality to the button btnTimeSlots "Day Slots"
	 *
	 *
	 */
	static class btnTimeSlots_Action implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			// avoid travel to the past
			if (tblCalendarRenderer.dayOfMonth < realDay&& months[realMonth].equals(lblMonth.getText())	&& realYear == Integer.parseInt(cmbYear.getSelectedItem().toString()))
			{
				JOptionPane.showMessageDialog(null, "Sorry but you can not travel to the past",	"Travel to the past",	JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				
				System.out.println("This is THE LABEL" +lblYear.getText());
			System.out.println("The current Day " + realDay);
			System.out.println("The real Month " + months[realMonth]);
			System.out.println("The selected Month " + lblMonth.getText());
			System.out.println("The real year " + realYear);
			System.out.println("The selected year "	+ Integer.parseInt(cmbYear.getSelectedItem().toString()));
			TimeSlotsGUI.updateGUI();
			showGUI();
			}
		}//end method actionPerformed
	
	}//end class btnTimeSlots_Action

	/**
	 * 
	 * This class provides functionality to the button btnArrangeAppoinment "Arrange Appointment"
	 *
	 */
	static class btnArrangeAppoinment_Action implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{

			System.out.println("The selected Month " + lblMonth.getText());
			System.out.println(tblCalendarRenderer.dayOfMonth);
			// tblDaySlot.setRowSelectionAllowed(true);

			int[] selectedSlot = tblDaySlot.getSelectedRows();
			System.out.println(selectedSlot);
			System.out.println("Currently edited row "
					+ tblDaySlot.getEditingRow());
			System.out.println("Selected row " + tblDaySlot.getSelectedRow());
			System.out.println("The selected time is: "
					+ mtblDaySlot.getValueAt(tblDaySlot.getSelectedRow(), 0));
		//not sure about this
			selectedTimeSlot=(String) mtblDaySlot.getValueAt(tblDaySlot.getSelectedRow(), 0);
			selectedAvailability=(String) mtblDaySlot.getValueAt(tblDaySlot.getSelectedRow(), 1);
			System.out.println("selectedAvailability"+selectedAvailability);
			
			if(selectedAvailability.contains("        Unavailable"))
			{
				JOptionPane.showMessageDialog(null, "Sorry, you selected an unavailable time slot","Unavailable TimeSlot",JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				System.out.println("You selected " + dayOfWeek+" "+tblCalendarRenderer.dayOfMonth +" of " + tblCalendarRenderer.selectedMonth +" "+ 
									Integer.parseInt(cmbYear.getSelectedItem().toString())+ " at "+selectedTimeSlot);
			
				String year=cmbYear.getSelectedItem().toString();
			    String month= tblCalendarRenderer.selectedMonth;
			    int day=tblCalendarRenderer.dayOfMonth;
			    GregorianCalendar appointmentCalendar = new GregorianCalendar(Integer.parseInt(year), getMonthAsInt(month), day);
			    
			    //Split the time from the JList to get the Time to book the appoinment
			    String[] timeSlotSplitted = selectedTimeSlot.split("-");
			    String startTime = timeSlotSplitted[0]; // 10:00
			    String[] splitTime = startTime.split(":");
			 
			    // TODO Query whether this appointment is available
			    System.out.println("The appointmentCalendar.getTime() : " +appointmentCalendar.getTime());
			    appointmentCalendar.set(java.util.Calendar.HOUR_OF_DAY, Integer.parseInt(splitTime[0]));
			    appointmentCalendar.set(java.util.Calendar.MINUTE, Integer.parseInt(splitTime[1]));
			
			    java.util.Calendar currentCal = java.util.Calendar.getInstance();
			    boolean timeExpired = false;
			
			    // Check curent calendar is after appointment calendar (expired)
			    if (currentCal.after(appointmentCalendar))
			    {
			    	System.out.println("Current time is after appointment");
			    	JOptionPane.showMessageDialog(null, "The time for this appoinment expires","Impossible Appointment",JOptionPane.ERROR_MESSAGE);
			    	timeExpired = true;
			    } 
			
			    //TODO Here checks if the student have an active appointment
			
			    if(ConnectionMySql.studentHasAppointment()==true)
			    {
			    	closeGUI();
			    	JOptionPane.showMessageDialog(null, "Remember you have an active appointment","Active Appointment",JOptionPane.ERROR_MESSAGE);
				
			    }
			    else if (timeExpired) 
			    {
			    	System.out.println("SMILEY :) Mark");
			    } 
			    //never arrives here
			    else
			    {
			    	ConnectionMySql.bookAppoinment(appointmentCalendar.getTime(), startTime);
			    	TimeSlotsGUI.updateGUI();
			    	closeGUI();
			    	JOptionPane.showMessageDialog(null, "Your appoinment is on "+day+" of " +month+" "+year+" at "+startTime,"Appoinment Arrange",JOptionPane.INFORMATION_MESSAGE);
			
			    
			    }
			}
		}//end method actionPerformed
	
	
	}//end class btnArrangeAppoinment_Action


	/**
	 * This class provide functionality for the button myDetails
	 */
	
	static class btnMyDetails_Action implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			pane.removeAll();
        	//loginPane.remove(loginPane);
        	//loginPane.setVisible(false);
        	ConnectionMySql.myDetails();
        	MyDetailsPane.buildMyDetailsPane();
			
		}//end method actionPerformed
	
	}//end class btnMyDetails_Action
	
	
	/**
	 * This class provide functionality for the button my student appointments
	 */
	
	static class btnMyAppointments_Action implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			pane.removeAll();
			StudentAppointmentPane.buildStudentViewAppointmentsPane();
        	//Call to a method to populate the list of appoinments
	/*		String temp=ConnectionMySql.displayAppoinment();
			
			StudentAppointmentPane.listModel.addElement(temp);
			//((String) StudentAppointmentPane.appoinmentList).addElement("temp");
*/			
		}//end method actionPerformed
	
	}//end class btnMyDetails_Action
	
	
	/**
	 * This methos shows the time slot GUI for the selected day 
	 */
	public static void showGUI() 
	{
		frmMainDaySlot.setTitle("Time Slot " + tblCalendarRenderer.selectedDay );
		frmMainDaySlot.setVisible(true);
	
	}//end method showGUI
	//TODO
	/**
	 * This method close the gui
	 */
	public static void closeGUI() 
	{
		frmMainDaySlot.setTitle("Time Slot " + tblCalendarRenderer.selectedDay );
		frmMainDaySlot.setVisible(false);
		frmMainDaySlot.dispose();
	
	}//end method showGUI
	/**
	 * 
	 * This class creates the time slots GUI for the selected day
	 *
	 */
	static class TimeSlotsGUI 
	{
		

		public static void buildGUI() 
		{

			// Prepare frame
			frmMainDaySlot = new JFrame("Time Slot "+ tblCalendarRenderer.selectedDay); // Create frame
			frmMainDaySlot.setSize(330, 375); // Set size to 400x400 pixels
			paneDaySlot = frmMainDaySlot.getContentPane(); // Get content pane
			paneDaySlot.setLayout(null); // Apply null layout
			// frmMainDaySlot.setDefaultCloseOperation(frmMainDaySlot.EXIT_ON_CLOSE);
			// //Close when X is clicked
			frmMainDaySlot.addWindowListener(new WindowListener() {
			
				public void windowClosing(WindowEvent e) {
					frmMainDaySlot.setVisible(false);
				}
				public void windowClosed(WindowEvent e) {
				}
				public void windowOpened(WindowEvent e) {
				}
				public void windowIconified(WindowEvent e) {
				}
				public void windowDeiconified(WindowEvent e) {
				}
				public void windowActivated(WindowEvent e) {
				}
				public void windowDeactivated(WindowEvent e) {
				}
			});

			// Create controls

			lblInstruction = new JLabel("Select the time slot and press the button ");
			// lblYear = new JLabel ("Change year:");
			// cmbYear = new JComboBox();
			// btnPrev = new JButton ("<<");
			// btnNext = new JButton (">>");
			btnArrangeAppoinment = new JButton("Arrange Appoinment");
			mtblDaySlot = new DefaultTableModel() {
				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			};
			tblDaySlot = new JTable(mtblDaySlot);
			stblDaySlot = new JScrollPane(tblDaySlot);
			pnlDaySlot = new JPanel(null);

			// Set border
			pnlDaySlot.setBorder(BorderFactory.createTitledBorder("Day Slot"));

			// Register actiton listeners

			btnArrangeAppoinment.addActionListener(new btnArrangeAppoinment_Action());

			// Add controls to pane
			paneDaySlot.add(pnlDaySlot);
			pnlDaySlot.add(lblInstruction);
			pnlDaySlot.add(stblDaySlot);
			pnlDaySlot.add(btnArrangeAppoinment);

			// Set bounds
			pnlDaySlot.setBounds(0, 0, 320, 335);
			lblInstruction.setBounds(70, 25, 300, 25);
			btnArrangeAppoinment.setBounds(80, 305, 150, 20);
			stblDaySlot.setBounds(10, 50, 300, 250);

			// Make frame visible
			frmMainDaySlot.setResizable(false);
			frmMainDaySlot.setVisible(false);
			btnArrangeAppoinment.setVisible(true);

			// Add headers
			String[] headers = { "     Time ", "        Available" }; // table headers
																		
			String[] timeSlot = { "10:00-10:30 ", "10:30-11:00", "11:00-11:30",
					"11:30-12:00", "12:00-12:30", "12:30-13:00", "14:00-14:30",
					"14:00-14:30", "14:30-15:00", "15:00-15:30", "15:30-16:00" }; // first column																				// headers
			//populate header
			for (int i = 0; i < 2; i++)
			{
				mtblDaySlot.addColumn(headers[i]);

			}

			// No resize/reorder
			tblDaySlot.getTableHeader().setResizingAllowed(false);
			tblDaySlot.getTableHeader().setReorderingAllowed(false);

			// Single row selection
			tblDaySlot.setRowSelectionAllowed(true);
			tblDaySlot.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// Set row/column count
			tblDaySlot.setRowHeight(38);
			mtblDaySlot.setColumnCount(2);
			mtblDaySlot.setRowCount(11);
			
			
			
			/*System.out.println("dd/mm/YYYY" + day + " " + month + " " + year);*/
			
			// Populate rows with time slots and availability
			for (int i = 0; i < timeSlot.length; i++)
			{
				mtblDaySlot.setValueAt(timeSlot[i], i, 0);
				
			/*	V1
				
				//10:00 - 10:30 @ i = 0
				
				// Make a calendar object set with date and time
				
				// Query database to check if Time Slot contains DATE & TIME
				
				*/
				
				/* v2
					// iF ARRAYLIST CONTAINS TIME - THEN SET UNAVAILABLE
				*/
				mtblDaySlot.setValueAt("        Available", i, 1);
				
				/*selectedAvailability=(String) mtblDaySlot.getValueAt(tblDaySlot.getSelectedRow(), 0);
				
				if(selectedAvailability.contains("        Unavailable"))
				{
					btnArrangeAppoinment.setEnabled(false);
				}
				else{
					btnArrangeAppoinment.setEnabled(true);
				}*/

			}

		}//end method biuldGUI
		
		
		
		/**
		 * This method update the time slot gui
		 */
		public static void updateGUI() {
			
			
			// Get result set
			String[] dateSplitted = tblCalendarRenderer.selectedDay.split("/");
			
			int day = Integer.parseInt(dateSplitted[0]);
			int month = getMonthAsInt(dateSplitted[1]);
			int year = Integer.parseInt(cmbYear.getSelectedItem().toString());
			System.out.println("AMIGOOOOOOOO " + day + "/" + month + " YEAR! " + year);
			
			GregorianCalendar cal = new GregorianCalendar(year, month, day);
			
			/* SELECT *  FROM appoinments where date='2014-03-24' and course_id='Computer Science'; */
			
			ArrayList<String> tempBookedTimesList = ConnectionMySql.getBookedTimes(cal.getTime());
			System.out.println("tempBookedTimesList - " + tempBookedTimesList);
			ArrayList<String> bookedTimesList = new ArrayList<String>();
			for (int j = 0; j < tempBookedTimesList.size(); j++) {
				String[] tempSplitTime = tempBookedTimesList.get(j).split(":");
				String tempTime = tempSplitTime[0] + ":" + tempSplitTime[1];
				bookedTimesList.add(tempTime);
			}
			System.out.println("MIERDAAAAAAAA" + " " + bookedTimesList);
			
			String[] timeSlot = { "10:00-10:30 ", "10:30-11:00", "11:00-11:30",
					"11:30-12:00", "12:00-12:30", "12:30-13:00", "14:00-14:30",
					"14:00-14:30", "14:30-15:00", "15:00-15:30", "15:30-16:00" }; // first column	
			
			for (int i = 0; i < timeSlot.length; i++) {
				mtblDaySlot.setValueAt(timeSlot[i], i, 0);
				
			
				if (bookedTimesList.size() > 0) {
					String[] splitTimeSlot = timeSlot[i].split("-");
					if (bookedTimesList.contains(splitTimeSlot[0])) {
						mtblDaySlot.setValueAt("        Unavailable", i, 1);
					
					} else {
						mtblDaySlot.setValueAt("        Available", i, 1);
					}
				} else {
					mtblDaySlot.setValueAt("        Available", i, 1);
				}
				
			}
		}
		
	}//end class TimeSlotsGUI
	
	
	
	/**
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
}// end class Calendar