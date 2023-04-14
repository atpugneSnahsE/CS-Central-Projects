import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class SearchMember extends JInternalFrame implements ActionListener 
{

	 JPanel pBook = new JPanel ();
	 JLabel lbSearch;
	 JRadioButton rb1,rb2;
	 JTextField txtSearch;
	 JButton btnFind, btnCancel;
	 int flag=0,rows=0;
	 Statement st;
	 String mname,mcat,search;
	 JTable table;
	 Object data1[][];
	 Container c;
	 int mid,bcnt;
	public SearchMember (Connection con)
	 {


		super ("Search Members", false, true, false, true);
		setSize (325, 250);



		lbSearch = new JLabel ("Search Field");
		lbSearch.setForeground (Color.black);
		lbSearch.setBounds (15, 15, 100, 20);
		
		txtSearch = new JTextField ();
		txtSearch.setBounds (120, 15, 175, 25);
		
		
		btnFind = new JButton ("Find Member");
		btnFind.setBounds (25, 175, 125, 25);
		btnFind.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (165, 175, 125, 25);
		btnCancel.addActionListener (this);
		
		rb1=new JRadioButton("By Id");
		rb1.addActionListener(this);
		rb1.setBounds (15, 45, 100, 20);
		rb2=new JRadioButton("By Name");
		rb2.addActionListener(this);
		rb2.setBounds (15, 75, 100, 20);
		
		
		pBook.setLayout (null);
		pBook.add(lbSearch);
		pBook.add(txtSearch);
		pBook.add(btnFind);
		pBook.add(btnCancel);
		ButtonGroup bg=new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		
		pBook.add(rb1);
		pBook.add(rb2);
		
		rb1.setSelected(true);
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

		if (obj == btnFind) {		

			if (txtSearch.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Search Field not Provided.");
				txtSearch.requestFocus ();
			}
			else 
			{
				
				String mname1;
				int num,id,catid,bcnt1;
				boolean found = false;				
				ResultSet rs,rs1,rs3;
				try {	
					String bavl,text,tts;
					num=st.executeUpdate("Delete * from MSearch");
					if(flag==0)
					{
						id=Integer.parseInt(txtSearch.getText());
						rs=st.executeQuery("Select * from Members where id="+id+"");
						rs.next();
						bavl=rs.getString("Mname");
						catid=rs.getInt(7);
						bcnt=rs.getInt(5);
					
						rs1=st.executeQuery("Select * from MeCat where Mcat="+catid+"");
						rs1.next();
						mcat=rs1.getString("CName");
						bcnt1=rs1.getInt("Blmt");
						rs3=st.executeQuery("Select * from Books where Mid="+id+"");
						text="Name: "+bavl+"\n Category: "+mcat+"\n Books Held: "+bcnt+"\n Book Limit: "+bcnt1+"\n";
						text+="Books Held:\n";
						while(rs3.next())
						{
							tts=rs3.getString(2);
							text+=tts+"\n";
						}
						JOptionPane.showMessageDialog(this,text);
						txtSearch.setText("");
						txtSearch.requestFocus();
					}
					else
					{
					
					search=txtSearch.getText();
					search=search.toLowerCase();
					rs=st.executeQuery("Select * from Members");
					while(rs.next())
					{
						
						mname=rs.getString(3);
						mid=rs.getInt(1);
						bcnt=rs.getInt(5);
						catid=rs.getInt(7);
						if(flag==1)
						{
							mname1=mname.toLowerCase();
							if(mname1.equals(search)||(mname1.indexOf(search)!=-1))
							{
								rs1=st.executeQuery("Select * from MeCat where Mcat="+catid+"");
								rs1.next();
								mcat=rs1.getString("CName");
								bcnt1=rs1.getInt("Blmt");
								num=st.executeUpdate("insert into MSearch values("+mid+", '"+mname+"' ,"+bcnt+", '"+mcat+"',"+bcnt1+")");
								rows++;
								found=true;
							}
						}
												
						}
					}
				}
				catch(SQLException sqlex) {
					if (found == false) {
						JOptionPane.showMessageDialog (this, "Record not Found.");
					}
				}if(flag==1){
				try{
					data1=new Object[rows][5];
					
					Object[] Colheads={"Member Id","Name","Books Held","Category","Book Limit"};
					ResultSet rs2=st.executeQuery("Select * from MSearch");
				
					for(int i1=0;i1<rows;i1++)
					{
							rs2.next();
							for(int j1=0;j1<5;j1++)
							{
								data1[i1][j1]=rs2.getString(j1+1);
							}
					}
					table=new JTable(data1,Colheads);
					TableDisp td=new TableDisp(table);
					txtSearch.setText("");
					txtSearch.requestFocus();
				
					
					}
					catch(Exception sqlex) {
						if (found == false) {
							JOptionPane.showMessageDialog (this, "Some prob Found.");
						}
					}
				}
				
			}

		}		

		if (obj == btnCancel) {		

			setVisible (false);
			dispose();

		}
		
		if(obj==rb1)
		{
			flag=0;
		}
		if(obj==rb2)
		{
			flag=1;
		}
	
	}
		
}
	
