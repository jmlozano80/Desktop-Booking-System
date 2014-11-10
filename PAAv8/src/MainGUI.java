// Import
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * This Class builds the main frame 
 * @author Jose 
 *
 */

public class MainGUI 
{

	//Variables 
	static JFrame frmMain;
	static JMenuBar menuBar;
	static JMenu helpMenu;
	static JMenuItem aboutItem,userManualItem;
	
	 
	 /**
	 * Main method
	 * @param argrs
	 */
	public static void main (String []argrs)
	{
		buildGUI();
		
	}//end main method
	
	/**
	 * This method builds the main frame of the GUI 
	 */
	public static void buildGUI()
	{
		// Prepare frame
			frmMain = new JFrame("PAT Appointment System"); // Create frame
			frmMain.setSize(330,420);
			makeMenuBar(frmMain);
			WellcomePane.buildWellcomePane();
			
	}//end method buildGUI
	
	/**
	 * This method builds the menuBar
	 * 
	 * @param frame
	 */
	   private static void makeMenuBar(JFrame frame)
	   {
	       //create MenuBar   
	       menuBar = new JMenuBar();
	       frame.setJMenuBar(menuBar);
	          
	       //Add MenuBar
	       menuBar.add(Box.createHorizontalGlue());

	          
	       //create  menu Help
	       helpMenu = new JMenu("Help");
	       menuBar.add(helpMenu);
	          
	       	// Create menuItems for menu Help
	       userManualItem = new JMenuItem("User Manual");  
	       helpMenu.add(userManualItem);
	          
	       aboutItem = new JMenuItem("About PAT v1.0");  
	       helpMenu.add(aboutItem);
	       
	       //---Action Listeners---
	       
		    // ActionListener for backBtn
	       userManualItem.addActionListener(new ActionListener()
	       {
	    	   public void actionPerformed(ActionEvent e)
	    	   {
	    		   //Execute when button is pressed
	    	       System.out.println("You clicked the Item of the menu bar User Manual");
	    	                
	    		            
	    	    }
	       });//end method Action Listener for Item of the menu bar User Manual
	       
		    // ActionListener for aboutItem of the menu bar User Manual
	       aboutItem.addActionListener(new ActionListener()
	       {
	    	   public void actionPerformed(ActionEvent e)
	    	   {
	    		   //Execute when button is pressed
	    	       System.out.println("You clicked the Item of the menu bar User Manual");
	    	                
	    		            
	    	    }
	       });//end method Action Listener for Item of the menu bar User Manual
	       
	       //end of the menu items corresponding to the  menu (Help)
	          
	   }// end of makeMenuBar() method

} // end Class MainGUI
