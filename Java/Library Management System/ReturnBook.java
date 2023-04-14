import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Date;
import java.util.Calendar;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class ReturnBook extends JInternalFrame implements ActionListener, FocusListener {

	private JPanel pBook = new JPanel ();
	private JLabel lbBookId, lbBookName,lbIssued;
	private JTextField txtBookId, txtBookName, txtIssued;
	private String urdate;
	private JButton btnReturn, btnCancel;
	private int id1,im,iy,vd,vm,vy,due;
	private Statement st;		
	private ResultSet rs;		
	private long id = 0;		
	private int mid,bc;
	
	public ReturnBook (Connection con) {

		
		super ("Return Book", false, true, false, true);
		setSize (325, 250);



		lbBookId = new JLabel ("Book Id:");
		lbBookId.setForeground (Color.black);
		lbBookId.setBounds (15, 15, 100, 20);
		lbBookName = new JLabel ("Book Name:");
		lbBookName.setForeground (Color.black);
		lbBookName.setBounds (15, 45, 100, 20);
		lbIssued = new JLabel ("Book Issued To:");
		lbIssued.setForeground (Color.black);
		lbIssued.setBounds (15, 75, 100, 20);
		
		txtBookId = new JTextField ();
		txtBookId.setHorizontalAlignment (JTextField.RIGHT);
		txtBookId.addFocusListener (this);
		txtBookId.setBounds (120, 15, 175, 25);
		txtBookName = new JTextField ();
		txtBookName.setEnabled (false);
		txtBookName.setBounds (120, 45, 175, 25);
		txtIssued = new JTextField ();
		txtIssued.setEnabled (false);
		txtIssued.setBounds (120, 75, 175, 25);
		
		btnReturn = new JButton ("Return Book");
		btnReturn.setBounds (25, 175, 125, 25);
		btnReturn.addActionListener (this);
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
		pBook.add (lbIssued);
		pBook.add (txtBookId);
		pBook.add (txtBookName);
		pBook.add (txtIssued);
		pBook.add (btnReturn);
		pBook.add (btnCancel);

		getContentPane().add (pBook, BorderLayout.CENTER);

		try {
			st = con.createStatement ();	
		}
		catch (SQLException sqlex) {			
 			JOptionPane.showMessageDialog (null, "A Problem Occurs While Loading the Form.");
 			dispose ();				
	 	}
		GregorianCalendar gcal=new GregorianCalendar();
	        id1= gcal.get(Calendar.DATE);
	        im=(int)gcal.get(Calendar.MONTH)+1;
	        iy=gcal.get(Calendar.YEAR);
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
	        urdate=xx+"/"+yy+"/"+iy;
		
		setVisible (true);
	}
	
	public void actionPerformed (ActionEvent ae) 
	{

		Object obj = ae.getSource();

		if (obj == btnReturn) {		

			if (txtBookId.getText().equals ("")) 
			{
				JOptionPane.showMessageDialog (this, "Book's Id not Provided.");
				txtBookId.requestFocus ();
			}
			else {
				try {
					int rd,rm,ry,urd,urm,ury,x;
					long v,v1,fine;
					Dates d1,d2;
					bc--;
					id = Integer.parseInt (txtBookId.getText ());
					ResultSet rs = st.executeQuery ("select * from Books WHERE BId ="+id+"");	//Executing the Query.
					rs.next();
					String ard=rs.getString("BReturn");
					System.out.println("came here 1");
					rs.close();
					
                    
                    String sr=urdate;
                    
                    StringTokenizer st2 = new StringTokenizer(sr,"/");
                    urd=Integer.parseInt(st2.nextToken());
                    urm=Integer.parseInt(st2.nextToken());
                    ury=Integer.parseInt(st2.nextToken());
                   
                    d2= new Dates(urm,urd,ury);
                   
                    StringTokenizer st1 = new StringTokenizer(ard,"/");
					
                    rd=Integer.parseInt(st1.nextToken());
                    rm=Integer.parseInt(st1.nextToken());
                    ry=Integer.parseInt(st1.nextToken());
                    
                    d1=new Dates(rm,rd,ry);
                    
                    v = d1.toLong();
                    v1 = d2.toLong();
                    
                    fine=v1-v;
                    if(fine<=0)
                       	fine=0;
                    else
                    {
                    	int reply = JOptionPane.showConfirmDialog (this, 
                				"Will you pay the Fine of Rs."+fine+"now","FinePay", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                		if (reply == JOptionPane.YES_OPTION)
                		{}
                		else if (reply == JOptionPane.NO_OPTION) 
                		{
                			due+=fine;
                		}
                    }
                    x=st.executeUpdate("Update Books Set Mid ="+0+" WHERE Bid ="+id+"");
                    x=st.executeUpdate("Update Members Set Bcnt ="+bc+", Mbdues="+due+" WHERE id ="+mid+"");
                    JOptionPane.showMessageDialog (this, "Book Returned");
                    txtClear();
				}
				catch (SQLException sqlex) {
					JOptionPane.showMessageDialog (this, "Problem");
				}
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
				ResultSet rs = st.executeQuery ("Select * from Books where BId="+id+"");	//Executing the Query.
				rs.next ();				
				bookNo = rs.getLong ("BId");		
				if (bookNo == id) {			
					found = true;
					txtBookId.setText ("" + id);
					txtBookName.setText ("" + rs.getString ("BName"));
					mid=rs.getInt("Mid");
					if(mid==0)
					{
						JOptionPane.showMessageDialog(this,"Not an Issued Book");
						dispose();
					}
					else
					{
						ResultSet rs1=st.executeQuery("Select * from Members where id="+mid+"");
						rs1.next();
						txtIssued.setText ("" + rs1.getString (3));
						bc=rs1.getInt("Bcnt");
						due=rs1.getInt(6);
					}
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
		txtIssued.setText ("");
		txtBookId.requestFocus ();

	}

	
	
	
	
	
	
	
}
