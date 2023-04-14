import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class LibrarySystem extends JFrame implements ActionListener
{
	

	 private JDesktopPane desktop = new JDesktopPane ();

	

	 JMenuBar bar;
	 JMenu mnuFile, mnuEdit; 
	 JMenuItem newBook,newMember, printBook,  printIssueBook;	
	 JMenuItem issueBook, returnBook, delBook, findBook;

	



	private	JToolBar toolBar;



	private	JButton btnNewBook,  btnIssue, btnReturn, btnPrintIssue, btnDelBook,btnFindBook;



	private JPanel statusBar = new JPanel ();
	
	

	
	Connection con;		
	Statement st;		

	String userName;	
	
	public LibrarySystem (int type,int user, Connection conn)
	{
		super ("Library Management System.");



		setIconImage (getToolkit().getImage ("Images/Warehouse.png"));	
		setSize (700, 550);						



		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth()) / 2,
			(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);



		addWindowListener (new WindowAdapter () {		
			public void windowClosing (WindowEvent we) {	
				//quitApp ();				
			}
		}
		);
		bar = new JMenuBar ();		
		setJMenuBar (bar);		
		


		mnuFile = new JMenu ("File");
		mnuFile.setMnemonic ((int)'E');
		mnuEdit = new JMenu ("Edit");
		mnuEdit.setMnemonic ((int)'E');
		
		




		newBook = new JMenuItem ("Add New Book");
		newBook.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		newBook.setMnemonic ((int)'N');
		newBook.addActionListener (this);
		
		newMember = new JMenuItem ("Add New Member");
		newMember.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK));
		newMember.setMnemonic ((int)'M');
		newMember.addActionListener (this);
		
		


		issueBook = new JMenuItem ("Issue Book");
		issueBook.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK));
		issueBook.setMnemonic ((int)'I');
		issueBook.addActionListener (this);
		
		returnBook = new JMenuItem ("Return Book");
		returnBook.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		returnBook.setMnemonic ((int)'R');	
		returnBook.addActionListener (this);

		delBook = new JMenuItem ("Delete Book");
		delBook.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
		delBook.setMnemonic ((int)'D');
		delBook.addActionListener (this);
		
		findBook = new JMenuItem ("Search Book");
		findBook.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK));
		findBook.setMnemonic ((int)'F');
		findBook.addActionListener (this);






	mnuFile.add (newBook);
	mnuFile.add (newMember);	
	

	mnuEdit.add (issueBook);
	mnuEdit.add (returnBook);
	mnuEdit.addSeparator ();
	mnuEdit.add (delBook);
	
	mnuEdit.addSeparator ();
	mnuEdit.add (findBook);
	
	




	bar.add (mnuFile);
	bar.add (mnuEdit);
	
	


	
	

		
	

	
	
	btnNewBook = new JButton (new ImageIcon ("Images/NotePad.gif"));

	btnNewBook.setToolTipText ("Add New Book");

	

	btnIssue = new JButton (new ImageIcon ("Images/Film.gif"));
	btnIssue.setToolTipText ("Issue Book");
	

	btnReturn = new JButton (new ImageIcon ("Images/Backup.gif"));
	btnReturn.setToolTipText ("Return Book");

	
	
	btnDelBook = new JButton (new ImageIcon ("Images/Recycle.gif"));
	btnDelBook.setToolTipText ("Delete Book");
	
	btnFindBook = new JButton (new ImageIcon ("Images/Mirror.gif"));
	btnFindBook.setToolTipText ("Search Book");
	btnFindBook.addActionListener (this);
	
	


	toolBar = new JToolBar ();
	toolBar.add (btnNewBook);

	toolBar.addSeparator ();
	toolBar.add (btnIssue);
	toolBar.add (btnReturn);
	toolBar.addSeparator ();
	
	toolBar.add (btnDelBook);
	
	toolBar.addSeparator ();
	toolBar.add (btnFindBook);
	
	
	if(type==1)
		userName="Admin";
	else if(type==2)
	{
	}
	else if(type==3)
	{
		
	}
	
	
	

	

	//Setting the Contents of Programs.

	getContentPane().add (toolBar, BorderLayout.NORTH);
	getContentPane().add (desktop, BorderLayout.CENTER);
	getContentPane().add (statusBar, BorderLayout.SOUTH);

	//Getting the Database.

	con = conn;
	
	setVisible (true);
	
	}	
	
	public void actionPerformed (ActionEvent ae) {
		
		Object obj = ae.getSource();

		if (obj == newBook ) {

			
			boolean b = openChildWindow ("Add New Book");
			if (b == false) {
				AddBook adBook = new AddBook (con);
				desktop.add (adBook);			
				adBook.show ();		
			}

		}
		else if (obj == newMember )
		 {

			boolean b = openChildWindow ("Add New Member");
			if (b == false) {
				AddMember adMember = new AddMember (con);
				desktop.add (adMember);
				adMember.show ();
				
			} 

		}
		
		
		else if (obj == issueBook )
		 {
			
			boolean b = openChildWindow ("Issue Book");
			if (b == false) {
				IssueBook isBook = new IssueBook (con);
				desktop.add (isBook);
				isBook.show ();
			} 

		}
		else if (obj == returnBook) 
		{

			boolean b = openChildWindow ("Return Book");
			if (b == false) {
				ReturnBook rtBook = new ReturnBook (con);
				desktop.add (rtBook);
				rtBook.show ();
			}
			

		}
		else if (obj == delBook) 
		{

			boolean b = openChildWindow ("Delete Book");
			if (b == false)
			 {
				DeleteBook dlBook = new DeleteBook (con);
				desktop.add (dlBook);
				dlBook.show ();
			} 

		}
		else if (obj == findBook )
		 {

			boolean b = openChildWindow ("Search Books");
			if (b == false) {
				SearchBook srBook = new SearchBook (con);
				desktop.add (srBook);
				srBook.show ();
			} 

		}
	}
	
	private boolean openChildWindow (String title) 
	{

		JInternalFrame[] childs = desktop.getAllFrames ();		
		for (int i = 0; i < childs.length; i++) {
			if (childs[i].getTitle().equalsIgnoreCase (title)) 
			{	
				childs[i].show ();				
				return true;
			}
		}
		return false;

	}
		
}