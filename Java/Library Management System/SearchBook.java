import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class SearchBook extends JInternalFrame implements ActionListener {

	JPanel pBook = new JPanel ();
	JLabel lbSearch;
	JRadioButton rb1,rb2,rb3,rb4;
	JTextField txtSearch;
	JButton btnFind, btnCancel;
	int flag=0;
	Statement st;
	String bname,bauthor,bcat,search;
	int bref,bmid,bid,rows=0;
	JTable table;
	JScrollPane jsp;
	Object data1[][];
	Container c;
	public SearchBook (Connection con) {


		super ("Search Books", false, true, false, true);
		setSize (510, 300);



		lbSearch = new JLabel ("Search Field");
		lbSearch.setForeground (Color.black);
		lbSearch.setBounds (15, 15, 100, 20);
		
		txtSearch = new JTextField ();
		txtSearch.setBounds (120, 15, 175, 25);
		
		
		btnFind = new JButton ("Find Book");
		btnFind.setBounds (25, 175, 125, 25);
		btnFind.addActionListener (this);
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (165, 175, 125, 25);
		btnCancel.addActionListener (this);
		
		rb1=new JRadioButton("By Title");
		rb1.addActionListener(this);
		rb1.setBounds (15, 45, 100, 20);
		rb2=new JRadioButton("By Author");
		rb2.addActionListener(this);
		rb2.setBounds (15, 75, 100, 20);
		rb3=new JRadioButton("By Category");
		rb3.addActionListener(this);
		rb3.setBounds (15, 105, 100, 20);
		rb4=new JRadioButton("By id");
		rb4.addActionListener(this);
		rb4.setBounds(15,135,100,20);
		
		pBook.setLayout (null);
		pBook.add(lbSearch);
		pBook.add(txtSearch);
		pBook.add(btnFind);
		pBook.add(btnCancel);
		ButtonGroup bg=new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		bg.add(rb4);
		pBook.add(rb1);
		pBook.add(rb2);
		pBook.add(rb3);
		pBook.add(rb4);
		rb1.setSelected(true);
		getContentPane().add (pBook, BorderLayout.CENTER);
		c=getContentPane();

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
				
				String bname1,bauthor1,bcat1;
				int num;
				boolean found = false;				

				try {	
					String q,bavl,bisr;
					num=st.executeUpdate("Delete * from BSearch");
					ResultSet rs = st.executeQuery ("SELECT * FROM Books ");	//Executing the Query.
					
					
					search=txtSearch.getText();
					search=search.toLowerCase();
					
					while(rs.next())
					{
						
						bname=rs.getString(2);
						bauthor=rs.getString("BAuthor");
						bcat=rs.getString("BCat");
						bref=rs.getInt("BRef");
						if(bref==1) bisr="Yes";
						else bisr="No";
						bmid=rs.getInt("Mid");
						if(bmid==0) bavl="Available";
						else bavl="Issued:"+ bmid;
						bid=rs.getInt("BId");
						
						if(flag==0)
						{
							bname1=bname.toLowerCase();
							if(bname1.equals(search)||(bname1.indexOf(search)!=-1))
							{
								System.out.println("Came Here2");
							    num=st.executeUpdate("insert into BSearch values("+bid+", '"+bname+"' , '"+bcat+"' , '"+bauthor+"' , '"+bavl+"', '"+bisr+"')");
								rows++;
							    found=true;
							}
						}
						else if(flag==1)
						{
							bauthor1=bauthor.toLowerCase();
							if(bauthor1.equals(search)||(bauthor1.indexOf(search)!=-1))
							{
								num=st.executeUpdate("insert into BSearch values("+bid+", '"+bname+"' , '"+bcat+"' , '"+bauthor+"' , '"+bavl+"', '"+bisr+"')");
								rows++;
								found=true;
								
							}
						}
						else if(flag==2)
						{
							bcat1=bcat.toLowerCase();
							if(bcat1.equals(search)||(bcat1.indexOf(search)!=-1))
							{
								num=st.executeUpdate("insert into BSearch values("+bid+", '"+bname+"' , '"+bcat+"' , '"+bauthor+"' , '"+bavl+"', '"+bisr+"')");
								rows++;
								found=true;
								
							}
						}
						else if(flag==3)
						{
							if(bid==Integer.parseInt(txtSearch.getText()))
							{
								rows++;
								num=st.executeUpdate("insert into BSearch values("+bid+", '"+bname+"' , '"+bcat+"' , '"+bauthor+"' , '"+bavl+"', '"+bisr+"')");
								found=true;
								
							}
						}
						
					}
					
					
				}
				catch(SQLException sqlex) {
					if (found == false) {
						JOptionPane.showMessageDialog (this, "Record not Found.");
					}
				}
				try{
				data1=new Object[rows][6];
				
				Object[] Colheads={"Book Id","Book Name","Category","Author","Availability","Reference"};
				ResultSet rs=st.executeQuery("Select * from BSearch");
			
				for(int i1=0;i1<rows;i1++)
				{
						rs.next();
						for(int j1=0;j1<6;j1++)
						{
							data1[i1][j1]=rs.getString(j1+1);
						}
				}
				table=new JTable(data1,Colheads);
				int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				System.out.println("hai we came here");
				jsp=new JScrollPane(table,v,h);
				TableDisp td=new TableDisp(table);
			
				
				}
				catch(Exception sqlex) {
					if (found == false) {
						JOptionPane.showMessageDialog (this, "Some prob Found.");
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
		if(obj==rb3)
		{
			flag=2;
		}
		if(obj==rb4)
		{
			flag=3;
		}

	}
		
}