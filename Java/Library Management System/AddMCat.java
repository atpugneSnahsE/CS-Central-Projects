import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class AddMCat extends JInternalFrame implements ActionListener {

	 JPanel pNew = new JPanel();
	 JLabel lbUser,lbDate,lbBooks;
	 JTextField txtUser,txtDate,txtBooks;
	 JButton btnOk, btnCancel;

	private Statement st;			

	

	public AddMCat (Connection con) {

		
		super ("New Member Category", false, true, false, true);
		setSize (280, 200);

		

		lbUser = new JLabel ("Category:");
		lbUser.setForeground (Color.black);
		lbUser.setBounds (20, 20, 100, 25);
		lbDate = new JLabel ("Days Issued:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (20, 55, 100, 25);
		lbBooks= new JLabel ("No. of Books");
		lbBooks.setForeground (Color.black);
		lbBooks.setBounds (20, 90, 100, 25);
		
		

		txtUser = new JTextField ();
		txtUser.setBounds (100, 20, 150, 25);
		
		txtDate = new JTextField ();
		txtDate.setBounds (100, 55, 150, 25);
		txtDate.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		txtBooks = new JTextField();
		txtBooks.setBounds(100,90,150,25);
		txtBooks.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		
		

		btnOk = new JButton ("OK");
		btnOk.setBounds (20, 123, 100, 25);
		btnOk.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (150, 123, 100, 25);
		btnCancel.addActionListener (this);

		

		pNew.setLayout (null);

		

		pNew.add (lbUser);
		pNew.add (lbDate);
		pNew.add (lbBooks);
		
		pNew.add (txtUser);
		pNew.add (txtDate);
		pNew.add (txtBooks);
		
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
				JOptionPane.showMessageDialog (this, "Username not Provided.");
			}
			
			else {
				try {	
					String id= txtUser.getText();
					String q = "SELECT CName FROM MeCat ";
					ResultSet rs = st.executeQuery (q);	
					int fl=0;
					while(rs.next())
					{
						String memberNo = rs.getString ("CName");	
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
					int num=0;
					try{
					rs= st.executeQuery("Select * From MeCat");
					
					while(rs.next())
					{
						num++;
					}
					num++;
					rs.close();
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog (this, "Problem while Creating excep1.");
					}
					if(fl==0){
					
					int result = st.executeUpdate ("Insert into MeCat Values(" + num + ", '" + txtUser.getText() + "' ," + Integer.parseInt(txtBooks.getText()) + " , " + Integer.parseInt(txtDate.getText())+ " )" );	//Running Query.
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