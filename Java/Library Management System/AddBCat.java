import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddBCat extends JInternalFrame implements ActionListener {

	JPanel pNew = new JPanel();
	JLabel lbUser;
	JTextField txtUser;
	JButton btnOk, btnCancel;

	private Statement st;			

	

	public AddBCat (Connection con) {

		
		super ("New Book Category", false, true, false, true);
		setSize (280, 175);

		

		lbUser = new JLabel ("Category:");
		lbUser.setForeground (Color.black);
		lbUser.setBounds (20, 20, 100, 25);
	       
		

		txtUser = new JTextField ();
		txtUser.setBounds (100, 20, 150, 25);
		
		

		btnOk = new JButton ("OK");
		btnOk.setBounds (20, 100, 100, 25);
		btnOk.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (150, 100, 100, 25);
		btnCancel.addActionListener (this);



		pNew.setLayout (null);



		pNew.add (lbUser);
		
		pNew.add (txtUser);
		
		pNew.add (btnOk);
		pNew.add (btnCancel);

		

		getContentPane().add (pNew);

		try {
			st = con.createStatement ();	
		}
		catch (SQLException sqlex) {			
 			JOptionPane.showMessageDialog (null, "A Problem Occurs While Loading the Form.");
 			dispose ();				
	 	}

		setVisible (true);

	}

	public void actionPerformed (ActionEvent ae) {

		Object obj = ae.getSource();

		if (obj == btnOk) {		

			

			if (txtUser.getText().equals ("")) {
				txtUser.requestFocus();
				JOptionPane.showMessageDialog (this, "Category not Provided.");
			}
			
			else {
				try {	
					String id= txtUser.getText();
					String q = "SELECT * FROM BCat ";
					ResultSet rs = st.executeQuery (q);	
					int fl=0;
					while(rs.next())
					{
						String memberNo = rs.getString ("Cat");	
						if(id.equals(memberNo))
						{
							JOptionPane.showMessageDialog(this,"Already existing Category");
							txtUser.setText("");
							txtUser.requestFocus();
							fl=1;
							break;
							
						}
					}
					rs.close();
					if(fl==0){
					q = "INSERT INTO BCat " + 
						"VALUES ('" + txtUser.getText() + "')";

					int result = st.executeUpdate (q);	
					if (result == 1) {			
						JOptionPane.showMessageDialog (this, "New Category Created.");
						txtUser.setText ("");
						txtUser.requestFocus ();
					}
					else {					
						JOptionPane.showMessageDialog (this, "Problem while Creating. ");
						txtUser.setText ("");
						txtUser.requestFocus ();
					}
					}
				}
				catch (SQLException sqlex) {
					
					JOptionPane.showMessageDialog (this, "Problem while Creating excep.");
				}
			}

		}		

		if (obj == btnCancel) {		

			setVisible (false);
			dispose();

		}

	}


}	