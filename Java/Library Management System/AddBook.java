import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddBook extends JInternalFrame implements ActionListener, FocusListener
 {

	 JPanel pBook = new JPanel ();

	 JLabel lbBookId, lbBookName, lbBookAuthor, lbBookRef, lbBookCategory;

	 JTextField txtBookId, txtBookName, txtBookAuthor;

	 JComboBox cboBookCategory;
	 JButton btnOk, btnCancel;
	 JRadioButton rby,rbn;
	 ButtonGroup bg;
	 String[] cn =new String[100];
	 Statement st;			
	 long id = 0;			
	 int i,j,ref=0;

	public AddBook (Connection con)
	 {

		
		super ("Add New Book", false ,true, true, true);
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
		lbBookRef = new JLabel ("Reference:");
		lbBookRef.setForeground (Color.black);
		lbBookRef.setBounds (15, 105, 100, 20);
		lbBookCategory = new JLabel ("Book Category:");
		lbBookCategory.setForeground (Color.black);
		lbBookCategory.setBounds (15, 135, 100, 20);
		
		txtBookId = new JTextField ();
		txtBookId.setHorizontalAlignment (JTextField.RIGHT);
		txtBookId.addFocusListener (this);
		txtBookId.setBounds (120, 15, 175, 25);
		txtBookName = new JTextField ();
		txtBookName.setBounds (120, 45, 175, 25);
		txtBookAuthor = new JTextField ();
		txtBookAuthor.setBounds (120, 75, 175, 25);
		
		rby=new JRadioButton("yes");
		rby.addActionListener(this);
		rby.setBounds(120,105,60,25);
		rbn=new JRadioButton("no");
		rbn.addActionListener(this);
		rbn.setBounds(180,105,60,25);
		bg = new ButtonGroup();
		bg.add(rby);
		bg.add(rbn);
		rbn.setSelected(true);
				
		cboBookCategory = new JComboBox();
		cboBookCategory.setBounds (120, 135, 175, 25);
		
		btnOk = new JButton ("OK");
		btnOk.setBounds (50, 175, 100, 25);
		btnOk.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (170, 175, 100, 25);
		btnCancel.addActionListener (this);
		
		txtBookId.addKeyListener (new KeyAdapter ()
		 {
			public void keyTyped (KeyEvent ke) 
			{
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) 
				{
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		
		txtBookAuthor.addKeyListener (new KeyAdapter ()
		 {
			public void keyTyped (KeyEvent ke) 
			{
				char c = ke.getKeyChar ();
				if (! ((Character.isLetter (c)) || (c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_SPACE))) {
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
		pBook.add (lbBookRef);
		pBook.add (lbBookCategory);
		pBook.add (txtBookId);
		pBook.add (txtBookName);
		pBook.add (txtBookAuthor);
		pBook.add (rby);
		pBook.add (rbn);
		
		pBook.add (cboBookCategory);
		pBook.add (btnOk);
		pBook.add (btnCancel);
		
		getContentPane().add (pBook, BorderLayout.CENTER);

		try {
			i=0;
			st = con.createStatement ();	
			ResultSet rs=st.executeQuery("Select * from BCat");
			while(rs.next())
			{
				cn[i]=rs.getString(1);
				i++;
			}
			for(j=0;j<i;j++)
			{
				cboBookCategory.addItem(cn[j]);
			}
			cboBookCategory.addActionListener(this);
			cboBookCategory.setSelectedItem(cn[0]);
			rs.close();
		}
		catch (SQLException sqlex)
		 {
 			JOptionPane.showMessageDialog (null, "A Problem Occurs While Loading Form.");
 			dispose ();				
	 	}

		setVisible (true);
		
	}
	
	public void actionPerformed (ActionEvent ae) 
	{

		Object obj = ae.getSource();

		if (obj == btnOk) 
		{



			if (txtBookId.getText().equals ("")) 
			{
				JOptionPane.showMessageDialog (this, "Book's Id not Provided.");
				txtBookId.requestFocus ();
			}
			else if (txtBookName.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Book's Name not Provided.");
				txtBookName.requestFocus ();
			}
			else if (txtBookAuthor.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Book's Author Name not Provided.");
				txtBookAuthor.requestFocus ();
			}
			else
			{
				try {	
					int x = 0;
	                                String s8 = x+"/"+x+"/"+x ;
					//INSERT Query to Add Book Record in Table.
					/* String q = "INSERT INTO Books " +
					"VALUES (" + id + ", '" + txtBookName.getText() + "', '" + txtBookAuthor.getText() + 
					"', " + ref + ", '" + cboBookCategory.getSelectedItem() + "' ,"+ 0 + ", '" + s8 + "', '" + s8 + ")"; */
					int result = st.executeUpdate ("Insert into Books values("+ id +",'" + txtBookName.getText() +"','" + txtBookAuthor.getText() +"', " + ref + ", '" + cboBookCategory.getSelectedItem().toString() +"', " + 0 + ", '"+ s8 +"' ,'"+ s8 + "')");	//Running Query.
					if (result == 1) {			//If Query Successful.
						JOptionPane.showMessageDialog (this, "Record has been Saved.");
						txtClear ();			//Clearing the TextFields.
					}
					else {					//If Query Failed.
						JOptionPane.showMessageDialog (this, "Problem while Saving the Record.");
					}
				}
				catch (SQLException sqlex) {
					JOptionPane.showMessageDialog (this, "Problem while Saving the Record Excep.");
				}
			}
		}
		
		if (obj == btnCancel) {		//If Cancel Button Pressed Unload the From.

			setVisible (false);
			dispose();

		}
		if(obj==rby)
		{
			ref=1;
		}
		else if(obj==rbn)
		{
			ref=0;
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
				if (bookNo == id) {			
					found = true;
					txtClear ();			
					JOptionPane.showMessageDialog (this, id + " is already assigned.");
				}
				else {
					found = false;
				}
			}
			catch (SQLException sqlex) { 
				
			}
		}

	}
	private void txtClear () {

		txtBookId.setText ("");
		txtBookName.setText ("");
		txtBookAuthor.setText ("");
		cboBookCategory.setSelectedIndex(0);
		txtBookId.requestFocus ();

	}

	
}