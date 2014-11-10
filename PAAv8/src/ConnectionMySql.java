import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * 
 * @author Jose
 *
 */
public class ConnectionMySql
{ 
	
	//Global Variables
	static Connection connect;
	static PreparedStatement statement;
	static ResultSet resultSet;
	static boolean studentExist,isLogged,isLoggedPAA;
	private static boolean studentExits;
	static String course,workingDays, studentId;
	/**
	 * 
	 */
/*	public static void connection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 
	 */
	public static void connectionToMySql()
	{	//connection();
		//Class.forName("com.mysql.jdbc.Driver");
		String host="jdbc:mysql://localhost:3306/pat";
		String userName="root";
		String password="hackney32";
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			
			connect=DriverManager.getConnection(host, userName, password);
			System.out.println("Connected and works");
		} 
			catch (SQLException e) 
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end method connectionToMySql
	
	/**
	 * 
	 */
	public static void disconnectToMysql()
	{
		try {
			statement.close();
			connect.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end method disconnectToMysql()

	
	
	
	
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public static boolean studentIDExist(String userId) throws SQLException
	{	//String id= userId;
		connectionToMySql();
		try
		{
			statement=connect.prepareStatement("select * From student where student_id=?");
			statement.setString(1,userId);
			resultSet=statement.executeQuery();
			
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultSet.next())
		{
			studentExits =true;
			disconnectToMysql();
			return studentExits; 
		}
		else
		{
			studentExits =false;
			disconnectToMysql();
			return studentExits;
		}
		
		
	}//end method studentIDExist
	
	
	/**
	 * 
	 * @param studentId
	 * @param fName
	 * @param lName
	 * @param course
	 * @param email
	 * @param password
	 */
	public static void  insertStudent(String studentId, String fName, String lName, String course, String email, String password )
	{
		
		connectionToMySql();
		
		try {
			statement=connect.prepareStatement("INSERT INTO  student (student_id, student_fname,student_lname,student_email,student_course,student_password) VALUES (?,?,?,?,?,?)");
			statement.setString(1,studentId);
			statement.setString(2,fName);
			statement.setString(3,lName);
			statement.setString(4,email);
			statement.setString(5,course);
			statement.setString(6,password);
			statement.executeUpdate();
			System.out.println("INSERTED INTO DATABASE");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}//end method  insertStudent
	
	/**
	 * 
	 * @param userId
	 * @param password
	 * @return isLogged
	 * @throws SQLException
	 */
	public static boolean login(String userId, String password) throws SQLException
	{	
		connectionToMySql();
		try
		{
			statement=connect.prepareStatement("select * From student where student_id=? AND student_password=?");
			statement.setString(1,userId);
			statement.setString(2,password);
			resultSet=statement.executeQuery();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultSet.next())
		{
			course = resultSet.getString("student_course");
			System.out.println("The COURSE = "+ course );
			isLogged =true;
			disconnectToMysql();
			studentId = userId;
			return isLogged; 
		}
		else
		{
			isLogged =false;
			disconnectToMysql();
			return isLogged;
		}		
		
	}//end method login
	
	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static boolean loginPAA(String userId, String password) throws SQLException
	{	
		connectionToMySql();
		try
		{
			statement=connect.prepareStatement("select * From paa where paa_id=? AND paa_password=?");
			statement.setString(1,userId);
			statement.setString(2,password);
			resultSet=statement.executeQuery();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultSet.next())
		{
			course = resultSet.getString("course");
			System.out.println("The COURSE = "+ course );
			isLoggedPAA =true;
			disconnectToMysql();
			System.out.println("isLogged== "+isLoggedPAA);
			return isLoggedPAA; 
		}
		else
		{
			isLoggedPAA =false;
			disconnectToMysql();
			System.out.println("isLogged== "+isLoggedPAA);
			return isLoggedPAA;
		}		
		
	}//end method loginPAA
	
	
	
	
	
	/**
	 * 
	 */
	public static void myDetails()
	{	String userId=LoginPane.studentIDTxt.getText();
		System.out.println("userid - " + userId);
		try
		{	connectionToMySql();
			//statement=connect.prepareStatement("SELECT * FROM student WHERE student_id=?");
		statement=connect.prepareStatement("SELECT * FROM student,paa WHERE student.student_course=paa.course AND student.student_id=?");
			statement.setString(1,userId);
			System.out.println("test");
			resultSet = statement.executeQuery();
			System.out.println("test2 - " + resultSet.toString());
			while(resultSet.next())
			{
			/*	System.out.println("re = " + resultSet.toString());
			 resultSet.getString("student_id");
			 
			 System.out.println("lname = " + resultSet.getString("student_lname"));*/
				studentId	=   (resultSet.getString("student_id"));
				MyDetailsPane.studentID = studentId;
				MyDetailsPane.studentFName = resultSet.getString("student_fname");
				MyDetailsPane.studentLName  = (resultSet.getString("student_lname"));
				MyDetailsPane.studentCourse = resultSet.getString("student_course");
				course= resultSet.getString("student_course");
				MyDetailsPane.studentEmail = resultSet.getString("student_email");
				MyDetailsPane.studentPAA= resultSet.getString("paa.paa_fname");
				MyDetailsPane.studentPAALName= resultSet.getString("paa.paa_lname");
			}
			////studentID,studentFName,studentLName,studentEmail,studentCourse,studentPAA;studentID,studentFName,studentLName,studentEmail,studentCourse,studentPAA;
			//student_fname,student_lname,student_email,student_course
			
			//result.setSet
		}
		catch (SQLException e)
		{
			System.out.println("SQL Exception - " + e.getMessage());
		}
		
		disconnectToMysql();
		
	}//end method myDetails
	
	
	/**
	 * 
	 */
	public static void myPAADetails()
	{	String userId=LoginPane.studentIDTxt.getText();
		System.out.println("userid - " + userId);
		try
		{	connectionToMySql();
			statement=connect.prepareStatement("SELECT * FROM paa WHERE paa_id=?");
			//statement=connect.prepareStatement("SELECT * FROM student,paa WHERE student.student_course=paa.course AND student.student_id=?");
			statement.setString(1,userId);
			System.out.println("test");
			resultSet = statement.executeQuery();
			System.out.println("test2 - " + resultSet.toString());
			while(resultSet.next())
			{
			/*	System.out.println("re = " + resultSet.toString());
			 resultSet.getString("student_id");
			 paaID,paaFName,paaLName,paaEmail,paaCourse;
			 System.out.println("lname = " + resultSet.getString("student_lname"));*/
				PAADetails.paaID	=   (resultSet.getString("paa_id"));
				PAADetails.paaFName = resultSet.getString("paa_fname");
				PAADetails.paaLName  = (resultSet.getString("paa_lname"));
				PAADetails.paaEmail = resultSet.getString("paa_email");
				PAADetails.paaCourse = resultSet.getString("course");
				
			}
			////studentID,studentFName,studentLName,studentEmail,studentCourse,studentPAA;studentID,studentFName,studentLName,studentEmail,studentCourse,studentPAA;
			//student_fname,student_lname,student_email,student_course
			
			//result.setSet
		}
		catch (SQLException e)
		{
			System.out.println("SQL Exception - " + e.getMessage());
		}
		
		disconnectToMysql();
		
	}//end method myPAADetail
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 */
	public static String paaWorkingDays()
	{
		
		try 
		{	
			String userId=LoginPane.studentIDTxt.getText();
			connectionToMySql();
			System.out.println("The student_id is : "+ userId);	
			statement=connect.prepareStatement("SELECT paa.paa_days FROM student,paa WHERE student.student_course=paa.course AND student.student_id=?");
			System.out.println("The course is : "+ course);
			statement.setString(1,userId);
			//System.out.println("test");
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				System.out.println("RESULT HAS NEXT ");
				workingDays=(resultSet.getString("paa_days"));
				System.out.println("Paa working days :" +workingDays);
				
			}
			System.out.println("The working days of the PAA: "+workingDays);
				//System.out.println("No Course is selected : "+ course);	
				disconnectToMysql();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workingDays;
		
		
		
	}//end method paaWorkingDays
	
/**
 * 	Method to check timeslot availability
 */
	
	
	
	public static boolean bookAppoinment(Date date, String times)
	{
		boolean appointment = false;
		System.out.println("Course - " + course + " Date - " + date + " Time - " + times);
		try 
		{	
			String userId=LoginPane.studentIDTxt.getText();
			connectionToMySql();
			System.out.println("The student_id is : "+ userId);	
			//statement=connect.prepareStatement("SELECT paa.paa_days FROM student,paa WHERE student.student_course=paa.course AND student.student_id=?");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/YYYY");
			dateFormat.format(date);
			statement=connect.prepareStatement("SELECT *  FROM appoinments where course_id=? AND date=? and time_slot=? AND status IS NULL");
			
			
			statement.setString(1,course);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			statement.setDate(2, sqlDate);
	
			/*statement.setString(2,date);*/
			statement.setString(3,times);
		
			
			//System.out.println("test");
			resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				appointment = true;
				System.out.println("RESULT HAS NEXT ");
				String 	timeSlot=(resultSet.getString("time_slot"));
				System.out.println("Time slot :" +timeSlot);
				//JInfo panel the appoinmtn is taken
				
			} else {
				// INSERT!
				
				PreparedStatement statement2=connect.prepareStatement("INSERT INTO appoinments (course_id,student_id, date, time_slot) VALUES (?,?,?,?)");
				
				System.out.println("THe student Id :" +studentId);
				statement2.setString(1, course);
				statement2.setString(2, studentId);
				java.sql.Date appointmentSqlDate = new java.sql.Date(date.getTime());
				statement2.setDate(3, appointmentSqlDate);
				statement2.setString(4, times);
				statement2.executeUpdate();
				
				//INSERT INTO `pat`.`appoinments` (`course_id`, `student_id`, `date`, `time_slot`) VALUES ('Computer  Science', 'a', '2014-03-28', '14:00:00');

			}
			//System.out.println("The working days of the PAA: "+workingDays);
				//System.out.println("No Course is selected : "+ course);	
				disconnectToMysql();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appointment;
		
		
		
	}//end method paaWorkingDays
	
	
	public static ArrayList<String> getBookedTimes(Date date)
	{
		ArrayList<String> timesList = new ArrayList<String>();
		try 
		{	
			
			connectionToMySql();
			
			//statement=connect.prepareStatement("SELECT paa.paa_days FROM student,paa WHERE student.student_course=paa.course AND student.student_id=?");
			
			/*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/YYYY");
			dateFormat.format(date);*/
			statement=connect.prepareStatement("SELECT *  FROM appoinments where course_id=? AND date=? AND status  like'Cancelled by PAA' OR status IS NULL");
			
			
			statement.setString(1,course);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			statement.setDate(2, sqlDate);
	
		
			//System.out.println("time" + time);
			
			//System.out.println("test");
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				timesList.add(resultSet.getString("time_slot"));
				//timesMap.put("time_slot", resultSet.getString("time_slot"));
				
			}
			//System.out.println("The working days of the PAA: "+workingDays);
				//System.out.println("No Course is selected : "+ course);	
				disconnectToMysql();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timesList;
		
		
	}
	
	/**
	 * Method to display the appoinment of a student
	 */
	
	
	public static String displayAppoinment()
	{
		String appointment=null;
		
		try 
		{	
			
			connectionToMySql();
			statement=connect.prepareStatement("SELECT *  FROM appoinments where student_id=? AND status IS NULL AND (SELECT ADDTIME(date,time_slot))>now()");
			
			statement.setString(1,studentId);
			System.out.println("The studentID= "+studentId);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				appointment= (resultSet.getString("date"))+"/"+(resultSet.getString("time_slot"));
				//timesMap.put("time_slot", resultSet.getString("time_slot"));
				
			}
			//System.out.println("The working days of the PAA: "+workingDays);
				//System.out.println("No Course is selected : "+ course);	
				disconnectToMysql();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("Appoinment: "+appointment );
		return appointment;
		
	}//end method displayAppoinment
	


	/**
	 * Method to display all appointments of a PAA
	 */
	
	
	public static ArrayList<String> displayAppoinmentsForPAA()
	{
		ArrayList<String> appointments = new ArrayList<String>();
		
		try 
		{	
			
			connectionToMySql();
			//statement=connect.prepareStatement("SELECT *  FROM appoinments where course_id=? AND (SELECT ADDTIME(date,time_slot))>now()");
			
			statement=connect.prepareStatement("SELECT a.student_id, s.student_fname,s.student_lname,a.date,a.time_slot  FROM appoinments a, student s where a.student_id=s.student_id  AND course_id=? AND (SELECT ADDTIME(a.date,a.time_slot))>now() AND status IS  NULL ORDER BY a.date,a.time_slot");
			
			//SELECT a.student_id, s.student_fname,s.student_lname,a.date,a.time_slot  FROM pat.appoinments a, pat.student s where a.student_id=s.student_id  AND course_id='Computer Science' AND (SELECT ADDTIME(a.date,a.time_slot))>now();
			
			statement.setString(1,course);
			System.out.println("The course= "+course);
			System.out.println(" before resultSet: ");
			resultSet = statement.executeQuery();
			System.out.println("resultSet: "+resultSet );
			while(resultSet.next())
			{
				appointments.add(( resultSet.getString("date"))+"/"+(resultSet.getString("time_slot"))+"/"+(resultSet.getString("student_id"))+"/"+(resultSet.getString("student_fname"))+"/"+(resultSet.getString("student_lname")));
				//timesMap.put("time_slot", resultSet.getString("time_slot"));
				
			}
			//System.out.println("The working days of the PAA: "+workingDays);
				//System.out.println("No Course is selected : "+ course);	
				disconnectToMysql();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("Appoinments: "+appointments );
		return appointments;
		
	}//end method displayAppoinment


/**
 * This method checks if the student have an active appointment
 * 
 * 
 */

	public static boolean studentHasAppointment()
	{
		boolean hasAppointment=false;
		
		try 
		{	
			
			connectionToMySql();
			//statement=connect.prepareStatement("SELECT *  FROM appoinments where course_id=? AND (SELECT ADDTIME(date,time_slot))>now()");
			
			statement=connect.prepareStatement("SELECT *  FROM appoinments  where student_id=? AND status IS NULL   AND (SELECT ADDTIME(date,time_slot))>now()");
			
			//SELECT a.student_id, s.student_fname,s.student_lname,a.date,a.time_slot  FROM pat.appoinments a, pat.student s where a.student_id=s.student_id  AND course_id='Computer Science' AND (SELECT ADDTIME(a.date,a.time_slot))>now();
			System.out.println("The student id= "+studentId);
			statement.setString(1,studentId);
			//System.out.println("The course= "+course);
			//System.out.println(" before resultSet: ");
			resultSet = statement.executeQuery();
			System.out.println("resultSet: "+resultSet );
			if(resultSet.next())
			{
				hasAppointment=true;
			}
			//System.out.println("The working days of the PAA: "+workingDays);
				//System.out.println("No Course is selected : "+ course);	
				disconnectToMysql();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println(" Has an appoinment??: "+hasAppointment );
		return hasAppointment;
		
	}//end method
/**
 * PAA cancel appointment
 */

	public static void paaCancelAppointment(Date date, Time time)
	{
		System.out.println("Date= "+date);
		System.out.println("Time= "+time);
		System.out.println("Course= " +course);
		
		try 
		{	
			
			connectionToMySql();
			
			statement=connect.prepareStatement("UPDATE appoinments SET status='Cancelled by PAA' WHERE course_id=? AND date=? AND time_slot=? AND status IS NULL");
			
			System.out.println("The course id= "+course);
			statement.setString(1,course);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			statement.setDate(2, sqlDate);
			statement.setTime(3, time);
			int rows =statement.executeUpdate();
			// If the executeUpdate was success
			//int rows = statement.executeUpdate("UPDATE ...");
			System.out.println(rows + " rows updated");
			if(rows>0)
				{System.out.println(rows + " rows updated");
				disconnectToMysql();
			}
			else
			{
				System.out.println(" NO updated Refresh");
				JOptionPane.showMessageDialog(null, "Please, refesh the GUI. \n The appointment was already deleted ","Empty Selection",JOptionPane.ERROR_MESSAGE);
				disconnectToMysql();
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end method paaCancelAppointment
	

	/**
	 * Student cancel appointment
	 */

		public static void studentCancelAppointment(Date date, Time time)
		{
			System.out.println("Date= "+date);
			System.out.println("Time= "+time);
			System.out.println("Course= " +course);
			
			try 
			{	
				
				connectionToMySql();
				
				statement=connect.prepareStatement("UPDATE appoinments   SET status='Cancelled by Student' WHERE student_id=? AND date=? AND time_slot=? AND status IS NULL");
				
				System.out.println("The student id= "+studentId);
				statement.setString(1,studentId);
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				statement.setDate(2, sqlDate);
				statement.setTime(3, time);
				int rows =statement.executeUpdate();
				if(rows>0)
				{
					System.out.println(rows + " rows updated");
					disconnectToMysql();
				}
				else
				{
					System.out.println(" NO updated Refresh");
					JOptionPane.showMessageDialog(null, "Please, refesh the GUI. \n The appointment was already deleted ","Empty Selection",JOptionPane.ERROR_MESSAGE);
					disconnectToMysql();
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end method paaCancelAppointment
	
	
	/**
	 * get student email
	 */
		
		public static String getStudentEmail(String userName)
		{
			String studentEmail = null;
			try 
			{	
				
				connectionToMySql();
				System.out.println("The username= "+userName);
				statement=connect.prepareStatement("SELECT * FROM student WHERE student_id=? ");
				statement.setString(1,userName);
				resultSet=statement.executeQuery();
				if(resultSet.next())
				{
					studentEmail=resultSet.getString("student_email");
					System.out.println("The studentEmail= "+studentEmail);
					disconnectToMysql();
				}
				else
				{
					System.out.println("No working");
					disconnectToMysql();
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return studentEmail;
			
			
			
			
			
		}//end method get student email
		
		/**
		 * get paa email
		 */
		
		public static String getPAAEmail()
		{
			String paaEmail=null;
			try
			{
				connectionToMySql();
				statement=connect.prepareStatement("SELECT * FROM paa where course=?");
				statement.setString(1,course);
				resultSet=statement.executeQuery();
				if(resultSet.next())
				{
					paaEmail=resultSet.getString("paa_email");
					System.out.println("The studentEmail= "+paaEmail);
					disconnectToMysql();
				}
				else
				{
					System.out.println("No working");
					disconnectToMysql();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return paaEmail;
		}//end method

		
		/**
		 * get student email by student request
		 */
			
			public static String getStudentEmailByStudent()
			{
				String studentEmail = null;
				try 
				{	
					
					connectionToMySql();
					System.out.println("The course= "+ course);
					statement=connect.prepareStatement("SELECT * FROM student WHERE student_id=? ");
					statement.setString(1,studentId);
					resultSet=statement.executeQuery();
					if(resultSet.next())
					{
						studentEmail=resultSet.getString("student_email");
						System.out.println("The studentEmail= "+studentEmail);
						disconnectToMysql();
					}
					else
					{
						System.out.println("No working");
						disconnectToMysql();
					}
				}
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return studentEmail;
				
				
				
				
				
			}//end method get student email
			
			
			
	/**
	 * method to pouplate the course drop down menu of the registration pane
	 */
			
			
			public static ArrayList<String> populateDropDownCourseRegistration()
			{
				ArrayList<String> courses=new ArrayList();
				
				try
				{
					connectionToMySql();
					statement=connect.prepareStatement("SELECT * FROM paa ");
					resultSet=statement.executeQuery();
					while(resultSet.next())
					{
						courses.add(resultSet.getString("course"));
					}
					disconnectToMysql();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
					
				return courses;
				
			}//end method populateDropDownMenu
		
}//end class ConnectionMySql
