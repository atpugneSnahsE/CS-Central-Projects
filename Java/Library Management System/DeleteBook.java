import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;
import java.util.*;

public class DeleteBook extends JInternalFrame implements ActionListener, FocusListener {

	 JPanel pBook = new JPanel ();
	 JLabel lbBookId, lbBookName, lbBookAuthor;
	 JTextField txtBookId, txtBookName, txtBookAuthor;
	 JButton btnDel, btnCancel;
	
	 Statement st;		
	 ResultSet rs;		
	private long id = 0,bisued;		


	public DeleteBook (Connection con) {


		super ("Delete Book", false, true, false, true);
		setSize (325, 250);



		lbBookId = new JLabel ("Book Id:");
		lbBookId.setForeground (Color.black);
		lbBookId.setBounds (15, 15, 100, 20);
		lbBookName = new JLabel ("Book Name:");
		lbBookName.setForeground (Color.black);
		lbBookName.setBounds (15, 45, 100, 20);
		lbBookAuthor = new JLabel ("Book Author:");
		lbBookAuthor.setForeground (Color.black);
		lbBookAuthor.setBounds (15, 75, 100, 20);
		
		txtBookId = new JTextField ();
		txtBookId.setHorizontalAlignment (JTextField.RIGHT);
		txtBookId.addFocusListener (this);
		txtBookId.setBounds (120, 15, 175, 25);
		txtBookName = new JTextField ();
		txtBookName.setEnabled (false);
		txtBookName.setBounds (120, 45, 175, 25);
		txtBookAuthor = new JTextField ();
		txtBookAuthor.setEnabled (false);
		txtBookAuthor.setBounds (120, 75, 175, 25);
		
		btnDel = new JButton ("Delete Book");
		btnDel.setBounds (25, 175, 125, 25);
		btnDel.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (165, 175, 125, 25);
		btnCancel.addActionListener (this);
		
		txtBookId.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		
		pBook.setLayout (null);
		pBook.add (lbBookId);
		pBook.add (lbBookName);
		pBook.add (lbBookAuthor);
		
		pBook.add (txtBookId);
		pBook.add (txtBookName);
		pBook.add (txtBookAuthor);
		
		pBook.add (btnDel);
		pBook.add (btnCancel);
		
		getContentPane().add (pBook, BorderLayout.CENTER);

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

		if (obj == btnDel) {		

			if (txtBookId.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Book's Id not Provided.");
				txtBookId.requestFocus ();
			}
			else if(bisued!=0)
				{
					txtClear();
					JOptionPane.showMessageDialog(this,"Book held by a member");
				}
			else
			{
				
				
			    	int reply = JOptionPane.showConfirmDialog (this, 
				"Are you really want to Delete\nthe " + txtBookName.getText () + " Record?",
				"LibrarySystem - Delete Book", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

				
				if (reply == JOptionPane.YES_OPTION) {		

					try {	
						String q = "DELETE FROM Books WHERE BId = " + id + "";
						txtClear ();				
						JOptionPane.showMessageDialog (this, "Book Deleted.");
						ResultSet rs = st.executeQuery (q);	
					}
					catch (SQLException sqlex) { }
				}
				
				else if (reply == JOptionPane.NO_OPTION) { }
			}

		}		

		if (obj == btnCancel) {		

			setVisible (false);
			dispose();

		}

	}

	

	public void focusGained (FocusEvent fe) { }

	public void focusLost (FocusEvent fe) {

		if (txtBookId.getText().equals ("")) {	
		}
		else {
			id = Integer.parseInt (txtBookId.getText ());	
			long bookNo;					
			boolean found = false;				

			try {	
				
				String q = "SELECT * FROM Books WHERE BId = " + id + "";
				ResultSet rs = st.executeQuery (q);	
				rs.next ();				
				bookNo = rs.getLong ("BId");		
				bisued=rs.getLong("Mid");
				
				if (bookNo == id) {
					found = true;
					txtBookId.setText ("" + id);
					txtBookName.setText ("" + rs.getString ("BName"));
					txtBookAuthor.setText ("" + rs.getString ("BAuthor"));
					
				}
				else {
					found = false;
				}
			}
			catch (SQLException sqlex) {
				if (found == false) {
					txtClear ();		
					JOptionPane.showMessageDialog (this, "Record not Found.");
				}
			}
		}

	}

	

	private void txtClear () {

		txtBookId.setText ("");
		txtBookName.setText ("");
		txtBookAuthor.setText ("");
		txtBookId.requestFocus();
	}

	
}
	
