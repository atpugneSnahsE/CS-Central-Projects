import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;

import java.sql.*;
import java.util.*;

public class AddMember extends JInternalFrame implements ActionListener, FocusListener {

	 JPanel pMember = new JPanel ();
	 JLabel lbMemberId, lbMemberName, lbMemberpwd, lbEntryDate,lbCategory;
	 JTextField txtMemberId, txtMemberName, txtMemberpwd,txtMemberdate;
	 JButton btnOk, btnCancel;
	 JComboBox cboMemCategory;
	 Statement st;			
	 long id = 0;	
	 String[] cn= new String[100];
	 int id1,im,iy,vd,vm,vy,i;
	
	public AddMember (Connection con) {


		super ("Add New Member", false, true, false, true);
		setSize (355, 250);



		lbMemberId = new JLabel ("Member Id:");
		lbMemberId.setForeground (Color.black);
		lbMemberId.setBounds (15, 15, 100, 20);
		lbMemberName = new JLabel ("Member Name:");
		lbMemberName.setForeground (Color.black);
		lbMemberName.setBounds (15, 45, 100, 20);
		lbMemberpwd = new JLabel ("Member Pwd:");
		lbMemberpwd.setForeground (Color.black);
		lbMemberpwd.setBounds (15, 75, 110, 20);
		lbEntryDate = new JLabel ("Entry Date:");
		lbEntryDate.setForeground (Color.black);
		lbEntryDate.setBounds (15, 105, 100, 20);
		lbCategory = new JLabel ("Category:");
		lbCategory.setForeground(Color.BLACK);
		lbCategory.setBounds(15,135,100,20);
		
		txtMemberId = new JTextField ();
		txtMemberId.setHorizontalAlignment (JTextField.RIGHT);
		txtMemberId.addFocusListener (this);
		txtMemberId.setBounds (125, 15, 205, 25);
		txtMemberName = new JTextField ();
		txtMemberName.setBounds (125, 45, 205, 25);
		txtMemberpwd = new JTextField ();
		txtMemberpwd.setBounds (125, 75, 205, 25);
		txtMemberdate=new JTextField();
		txtMemberdate.setBounds(125,105,205,25);
		txtMemberdate.setEditable(false);
		cboMemCategory= new JComboBox();
		cboMemCategory.setBounds(125,135,100,20);
		
		GregorianCalendar gcal=new GregorianCalendar();
        id= gcal.get(Calendar.DATE);
        im=(int)gcal.get(Calendar.MONTH)+1;
        iy=gcal.get(Calendar.YEAR);
                      
        String idate=id+"/"+im+"/"+iy;
    	txtMemberdate.setText(idate);
		
		
		btnOk = new JButton ("OK");
		btnOk.setBounds (30, 165, 125, 25);
		btnOk.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (190, 165, 125, 25);
		btnCancel.addActionListener (this);
		
		txtMemberId.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		
		txtMemberName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
           char c = ke.getKeyChar();
                if (! ((Character.isLetter(c)) || (c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_SPACE))) {
                    getToolkit().beep();
                    ke.consume();
                    
                }
            }
        }
        );
		
		pMember.setLayout (null);
		pMember.add (lbMemberId);
		pMember.add (lbMemberName);
		pMember.add (lbMemberpwd);
		pMember.add (lbEntryDate);
		pMember.add (txtMemberId);
		pMember.add (txtMemberName);
		pMember.add (txtMemberpwd);
		pMember.add(txtMemberdate);
		pMember.add (btnOk);
		pMember.add (btnCancel);
		pMember.add (lbCategory);
		pMember.add (cboMemCategory);
		
	
		getContentPane().add (pMember, BorderLayout.CENTER);
		int j;
		try {
			i=0;
			st = con.createStatement ();	
			ResultSet rs=st.executeQuery("Select * from MeCat");
			while(rs.next())
			{
				cn[i]=rs.getString(2);
				i++;
			}
			for(j=0;j<i;j++)
			{
				cboMemCategory.addItem(cn[j]);
			}
			cboMemCategory.addActionListener(this);
			cboMemCategory.setSelectedItem(cn[0]);
			rs.close();
		}
		catch (SQLException sqlex) {			
 			JOptionPane.showMessageDialog (null, "A Problem Occurs While Loading Form.");
 			dispose ();				
	 	}

		setVisible (true);

	}
		
	public void actionPerformed (ActionEvent ae) {

		Object obj = ae.getSource();

		if (obj == btnOk) {		

			

			if (txtMemberId.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Member's Id not Provided.");
				txtMemberId.requestFocus ();
			}
			else if (txtMemberName.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Member's Name not Provided.");
				txtMemberName.requestFocus ();
			}
			else if (txtMemberpwd.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Member's Password not Provided.");
				txtMemberpwd.requestFocus ();
			}
			else {
				try {	
					int mtype=cboMemCategory.getSelectedIndex()+1;
					String q = "INSERT INTO Members" + 
						" VALUES (" + id + ", '" + txtMemberpwd.getText() + "', '" + 
						txtMemberName.getText() + "', '" + txtMemberdate.getText() + "',"+ 0 + "," + 0 + "," + mtype + ")";

					int result = st.executeUpdate (q);	
					if (result == 1) {			
						JOptionPane.showMessageDialog (this, "Record has been Saved.");
						txtClear ();			
					}
					else {					
						JOptionPane.showMessageDialog (this, "Problem while Saving the Record.");
					}
				}
				catch (SQLException sqlex) {JOptionPane.showMessageDialog(this,"Error!!"); }
			
			}
		}
		if (obj == btnCancel) {		

			setVisible (false);
			dispose();

		}
	}
	public void focusGained (FocusEvent fe) { }

	public void focusLost (FocusEvent fe) {

		if (txtMemberId.getText().equals ("")) {	
		}
		else {
		
		id = Integer.parseInt (txtMemberId.getText ());	
		long memberNo;					
		boolean found = false;				

		try {	
			String q = "SELECT * FROM Members WHERE id = " + id + "";
			ResultSet rs = st.executeQuery (q);	
			rs.next ();				
			memberNo = rs.getLong ("id");	
			if (memberNo == id) {		
				found = true;
				txtClear ();
				JOptionPane.showMessageDialog (this, id + " is already assigned.");
			}
			else {
				found = false;
			}
		}
		catch (SQLException sqlex) { }
	}

	}
	
	private void txtClear () {

		txtMemberId.setText ("");
		txtMemberName.setText ("");
		txtMemberpwd.setText ("");
		txtMemberId.requestFocus ();

	}
	
}
	
	
	

	
	
	
	
	
