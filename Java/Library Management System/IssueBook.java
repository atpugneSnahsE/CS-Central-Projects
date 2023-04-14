import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.sql.*;
import java.util.*;

public class IssueBook extends JInternalFrame implements ActionListener, FocusListener {

	private JPanel pBook = new JPanel ();
	private JLabel lbBookId, lbBookName, lbBookAuthor, lbBookCategory, lbMemberId, lbMemberName,lbDate1,lbDate2;
	private JTextField txtBookId, txtBookName, txtBookAuthor, txtBookCategory, txtMemberId, txtMemberName,txtDate1,txtDate2;
	private JButton btnOk, btnCancel;

	private Statement st;			
	private long id = 0;			
	private int memberId = 0;		
	private int id1,im,iy,vd,vm,vy;
	private String idate,vdate;
	public IssueBook (Connection con) {

		
		super ("Issue Book", false, true, false, true);
		setSize (325, 340);



		lbBookId = new JLabel ("Book Id:");
		lbBookId.setForeground (Color.black);
		lbBookId.setBounds (15, 15, 100, 20);
		lbBookName = new JLabel ("Book Name:");
		lbBookName.setForeground (Color.black);
		lbBookName.setBounds (15, 45, 100, 20);
		lbBookAuthor = new JLabel ("Book Author:");
		lbBookAuthor.setForeground (Color.black);
		lbBookAuthor.setBounds (15, 75, 100, 20);
		lbBookCategory = new JLabel ("Book Category:");
		lbBookCategory.setForeground (Color.black);
		lbBookCategory.setBounds (15, 105, 100, 20);
		lbMemberId = new JLabel ("Member Id:");
		lbMemberId.setForeground (Color.black);
		lbMemberId.setBounds (15, 135, 100, 20);
		lbMemberName = new JLabel ("Member Name:");
		lbMemberName.setForeground (Color.black);
		lbMemberName.setBounds (15, 165, 100, 20);
		lbDate1 = new JLabel ("Issue Date:");
		lbDate1.setForeground (Color.black);
		lbDate1.setBounds (15, 195, 100, 20);
		lbDate2 = new JLabel ("Return Date:");
		lbDate2.setForeground (Color.black);
		lbDate2.setBounds (15, 225, 100, 20);
		
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
		txtBookCategory = new JTextField ();
		txtBookCategory.setEnabled (false);
		txtBookCategory.setBounds (120, 105, 175, 25);
		txtMemberId = new JTextField ();
		txtMemberId.setHorizontalAlignment (JTextField.RIGHT);
		txtMemberId.addFocusListener (this);
		txtMemberId.setBounds (120, 135, 175, 25);
		txtMemberName = new JTextField ();
		txtMemberName.setEnabled (false);
		txtMemberName.setBounds (120, 165, 175, 25);
		txtDate1 = new JTextField ();
		txtDate1.setEnabled (false);
		txtDate1.setBounds (120, 195, 175, 25);
		txtDate1.setEditable(false);
		txtDate2 = new JTextField ();
		txtDate2.setEnabled (false);
		txtDate2.setBounds (120, 225, 175, 25);
		txtDate2.setEditable(false);
		
		btnOk = new JButton ("OK");
		btnOk.setBounds (50, 260, 100, 25);
		btnOk.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (170, 260, 100, 25);
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
		
		pBook.setLayout (null);
		pBook.add (lbBookId);
		pBook.add (lbBookName);
		pBook.add (lbBookAuthor);
		pBook.add (lbBookCategory);
		pBook.add (lbMemberId);
		pBook.add (lbMemberName);
		pBook.add (txtBookId);
		pBook.add (txtBookName);
		pBook.add (txtBookAuthor);
		pBook.add (txtBookCategory);
		pBook.add (txtMemberId);
		pBook.add (txtMemberName);
		pBook.add (btnOk);
		pBook.add (btnCancel);
		pBook.add (txtDate1);
		pBook.add (txtDate2);
		pBook.add (lbDate1);
		pBook.add (lbDate2);

		

		getContentPane().add (pBook, BorderLayout.CENTER);
		
		try {
			st = con.createStatement ();	
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

			if (txtBookId.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Book's Id not Provided.");
				txtBookId.requestFocus ();
			}
			else if (txtMemberId.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Member's Id not Provided.");
				txtMemberId.requestFocus ();
			}
			else {
				try {
					int i1= Integer.parseInt(txtMemberId.getText());
					ResultSet rs = st.executeQuery("Select * from Members where id="+ i1 +"");
					rs.next();
					int bc=rs.getInt("Bcnt");
					bc++;
					int bid1=Integer.parseInt(txtBookId.getText());
					
					int result = st.executeUpdate ("Update Members SET Bcnt="+ bc +" WHERE id="+ i1 +"");	
					if (result == 1) {			
						txtClear ();			
						
					}
					else {	
						txtClear ();			
						JOptionPane.showMessageDialog (this, "Problem while Saving the Record.");
					}
					System.out.println("came 1");
					result = st.executeUpdate("Update Books SET Mid= "+ i1 + ", BIssue = '"+ idate +"', BReturn = '"+vdate+"' where Bid="+bid1+"");
					System.out.println("came 2");
					if (result == 1) {			
						txtClear ();			
						JOptionPane.showMessageDialog (this, "Record has been Saved.");
					}
					else {					
						txtClear ();			
						JOptionPane.showMessageDialog (this, "Problem while Saving the Record.");
					}
				}
				catch (SQLException sqlex) {JOptionPane.showMessageDialog (this, "Problem"); }
			}

		}

		if (obj == btnCancel) {		

			setVisible (false);
			dispose();

		}

	}
	
	public void focusGained (FocusEvent fe) { }

	public void focusLost (FocusEvent fe) {

		Object obj = fe.getSource ();

		if (obj == txtBookId) {

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
					int mid=rs.getInt("Mid");
					int bref=rs.getInt("BRef");
					if(bref==1)
					{
						txtClear();
						JOptionPane.showMessageDialog (this, "Ref Book Can't Be Issued.");
					}
					if(mid!=0)
					{
						txtClear();
						JOptionPane.showMessageDialog(this,"Book Already Issued");
					}
					if (bookNo == id) {			
						found = true;
						txtBookId.setText ("" + id);
						txtBookName.setText ("" + rs.getString ("BName"));
						txtBookAuthor.setText ("" + rs.getString ("BAuthor"));
						txtBookCategory.setText ("" + rs.getString ("BCat"));
					}
					else {
						found = false;
					}
				}
				catch (SQLException sqlex) {
					if (found == false) {
						txtBookId.requestFocus ();
						txtBookId.setText ("");
						txtBookName.setText ("");
						txtBookAuthor.setText ("");
						txtBookCategory.setText ("");
						JOptionPane.showMessageDialog (this, "Record not Found.");
					}
				}
			}

		}
		else if (obj == txtMemberId) {

			if (txtMemberId.getText().equals ("")) {	
			}
			else {
				memberId = Integer.parseInt (txtMemberId.getText ());	
				int memberNo,memberDays,memberBooks,memberCat,heldBooks;						
				boolean find = false;					

				try {	
					String q = "SELECT * FROM Members WHERE id = " + memberId + "";
					ResultSet rs = st.executeQuery (q);	
					rs.next ();				
					memberNo = rs.getInt ("id");
												
					if (memberNo == memberId) {			
						
						find = true;
						memberCat=rs.getInt("MCat");
						heldBooks=rs.getInt("Bcnt");
						txtMemberName.setText ("" + rs.getString ("MName"));
						rs.close();
						ResultSet rs1= st.executeQuery("Select * from Mecat where MCat = " + memberCat + "" );
						rs1.next();
						memberBooks=rs1.getInt("Blmt");
						memberDays=rs1.getInt("Dlmt");
						if(heldBooks==memberBooks)
						{
							txtClear();
							JOptionPane.showMessageDialog (this, "Book Limit Reached");
							dispose();
						}
						
						
						 GregorianCalendar gcal=new GregorianCalendar();
					        id1= gcal.get(Calendar.DATE);
					        im=(int)gcal.get(Calendar.MONTH)+1;
					        iy=gcal.get(Calendar.YEAR);
					        vd=id1+memberDays;
					        vm=im;
					        vy=iy;
					        String xx,yy,zz;
					        if(id1<10) {
					            xx="0"+id1;
					        } else {
					            xx = ""+id1;
					        }
					        if(im<10) {
					            yy="0"+im;
					        } else {
					            yy = ""+im;
					        }
					        idate=xx+"/"+yy+"/"+iy;
					        
					         while(vd>31) {
					            if(im==1||im==3||im==5||im==7||im==8||im==10||im==12){
					                if(vd>31){
					                    im=im+1;
					                    vd=vd-31;
					                    if(im>12){
					                        im=im-12;
					                        iy=iy+1;
					                    }}}
					            
					            
					            if(im==4||im==6||im==9||im==11){
					                if(vd>30){
					                    im=im+1;
					                    vd=vd-30;
					                    if(im>12){
					                        im=im-12;
					                        iy=iy+1;}
					                }}
					            
					            
					            if(im==2){
					                if(vd>28){
					                    im=im+1;
					                    vd=vd-28;
					                    if(im>12){
					                        im=im-12;
					                        iy=iy+1;
					                    }}}
					        }
					       vdate = vd+"/"+im+"/"+iy;
						
						
						txtMemberId.setText ("" + memberId);
						
						txtDate1.setText(idate);
						txtDate2.setText(vdate);
					}
					else {
						find = false;
					}
				}
				catch (SQLException sqlex) {
					if (find == false) {
						txtClear ();		
						JOptionPane.showMessageDialog (this, "Record not Found.");
					}
				}
			}
		}

	}

	

	private void txtClear () {

		txtBookId.setText ("");
		txtBookName.setText ("");
		txtBookAuthor.setText ("");
		txtBookCategory.setText ("");
		txtMemberId.setText ("");
		txtMemberName.setText ("");
		txtBookId.requestFocus ();

	}
}	


