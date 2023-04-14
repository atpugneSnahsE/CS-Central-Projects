import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Logon extends JFrame implements ActionListener
 {

	

	 int fl=1;
	 JPanel pLog = new JPanel();
	 JLabel lbUser, lbPass;
	 JTextField txtUser;
	 JPasswordField txtPass;
	 JButton btnOk, btnCancel;
	
	 Connection con;		
	public String user;		




	public Logon ()
	 {

		super ("Library Management System.");


		setSize (275, 300);					


		addWindowListener (new WindowAdapter ()
		 {		
			public void windowClosing (WindowEvent we) {
				setVisible (false);			
				dispose();            		
				System.exit (0);        	
			}
		}
		);







		pLog.setLayout (null);

		


		
		


		lbUser = new JLabel ("Username:");
		lbUser.setForeground (Color.black);
		lbUser.setBounds (20, 15, 75, 25);
                lbPass = new JLabel ("Password:");
		lbPass.setForeground (Color.BLACK);
                lbPass.setBounds (20, 50, 75, 25);



		txtUser = new JTextField ();
		txtUser.setBounds (100, 15, 150, 25);
		txtPass = new JPasswordField ();
		txtPass.setBounds (100, 50, 150, 25);

		//Setting the Form's Buttons.

		btnOk = new JButton ("OK");
		btnOk.setBounds (20, 90, 100, 25);
		btnOk.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (150, 90, 100, 25);
		btnCancel.addActionListener (this);

		
		pLog.add (lbUser);
		pLog.add (lbPass);
		pLog.add (txtUser);
		pLog.add (txtPass);
		pLog.add (btnOk);
		pLog.add (btnCancel);



		getContentPane().add (pLog);

		//Opening the Database.

		try
		 {
			Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
			String loc = "jdbc:odbc:temp1";
			con = DriverManager.getConnection (loc);
		}
		catch (ClassNotFoundException cnf)  {
			JOptionPane.showMessageDialog (null, "Driver not Loaded...");
			System.exit (0);
		}
		catch (SQLException sqlex) {
 			JOptionPane.showMessageDialog (null, "Unable to Connect to Database...");
 			System.exit (0);
	 	}

		//Showing The Logon Form.

		setVisible (true);

	}

	public void actionPerformed (ActionEvent ae)
	 {

		Object obj = ae.getSource();

		if (obj == btnOk)
		 {		

			String password = new String (txtPass.getPassword());

			if (txtUser.getText().equals ("")) 
			{
				JOptionPane.showMessageDialog (this, "Provide Username to Logon.");
				txtUser.requestFocus();
			}
			else if (password.equals ("")) 
			{
				txtPass.requestFocus();
				JOptionPane.showMessageDialog (null,"Provide Password to Logon.");
			}
			else 
			{
				String pass;			
				
				boolean verify = false;		
				if(fl==1)
				{
						if(txtUser.getText().equals("Admin")&&password.equals("admin"))
						{
							verify=true;
							
							new LibrarySystem(1,1,con);
							setVisible(false);
							dispose();
						}
				}
				else
				{
					String tablename=null;
					if(fl==2) tablename="Clerks";
					else if(fl==3)tablename="Members";
					
					try {	//SELECT Query to Retrieved the Record.
 					String query = "SELECT * FROM " + tablename + " WHERE id = " + Integer.parseInt(txtUser.getText());

 					Statement st = con.createStatement ();		
		 			ResultSet rs = st.executeQuery (query);		
					rs.next();					
 					user = rs.getString ("id");		
 					pass = rs.getString ("Password");	

 					if (txtUser.getText().equals (user) && password.equals (pass)) 
					{
						
						verify = true;
						new LibrarySystem (fl,Integer.parseInt(txtUser.getText()), con);

						
						setVisible (false);		
						dispose();            		
					}
					else 
					{
						verify = false;

						txtUser.setText ("");
						txtPass.setText ("");
						txtUser.requestFocus ();
					}
				}
				catch (Exception sqlex)
				 {
					if (verify == false)
					 {
					
						txtUser.setText ("");
						txtPass.setText ("");
						txtUser.requestFocus ();
					}
				}
			}

		}
		}
		else if (obj == btnCancel) 
		{	

			setVisible (false);
			dispose();
			System.exit (0);

		}
		

	}
	public static void main(String args[])
	{
			Logon start=new Logon();
	}
	
}
class FrmSplash extends JWindow implements Runnable
{
	
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();	

	public void run()
	{
				
		setSize(275,300);
	
		setVisible(true);
	}
}