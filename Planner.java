import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Planner extends JFrame {

	public JFrame frame3 = new JFrame();
	public JFrame notes = new JFrame();
	public JFrame frame1 = new JFrame();
	public JFrame evDisplay = new JFrame();
	public JButton BL1, BL2, BL3, BL4, BL5, BL6, BL7;
	

	// Runs the class
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Planner();
				} catch (Exception exp) {
				}
			}
		});
	}

	public Planner() throws SQLException {

		JFrame frame2 = new JFrame();

		/*
		 * Creates title screen for user to choose desired block and if they want to
		 * create for edit previous notes
		 */
		
		frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame1.setTitle("School Planner");
		frame1.setSize(600, 500);
		frame1.setVisible(true);

		frame1.getContentPane();
		SpringLayout springLayout = new SpringLayout();
		frame1.setLayout(springLayout);

		JButton b1 = new JButton("Create Reminder");
		JButton b2 = new JButton("Edit/ View Reminders");
		GridLayout newLayout = new GridLayout(2, 2);
		frame1.setLayout(newLayout);
		frame1.add(b1);
		frame1.add(b2);

		// Creates new page to create notes
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame1.dispose();
				frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame2.setTitle("New Reminder");
				frame2.setSize(600, 600);
				frame2.setVisible(true);
				frame2.getContentPane();
				GridLayout notesLayout = new GridLayout(6, 1);
				frame2.setLayout(new FlowLayout(FlowLayout.LEFT));
				frame2.setLayout(notesLayout);

			}
		});

		// Displays areas for user input
		JLabel l1 = new JLabel("Title");
		JLabel l2 = new JLabel("Desciption");
		JLabel l3 = new JLabel("Due Date (mm/dd/yyyy)");
		JLabel l4 = new JLabel("Priority");
		JLabel l5 = new JLabel("Class");
		String[] priority = { "!", "!!", "!!!" };
		String[] classes = { "BL1", "BL2", "BL3", "BL4", "BL5", "BL6", "BL7" };
		JTextField t1 = new JTextField(20);
		JTextField t2 = new JTextField(20);
		JTextField t3 = new JTextField(20);
		JComboBox<String> priorityLevel = new JComboBox(priority);
		JComboBox block = new JComboBox(classes);
		JButton saveNew = new JButton("Save Reminder");
		JButton c = new JButton("Cancel");

		l1.setDisplayedMnemonic(KeyEvent.VK_T);
		l1.setLabelFor(t1);
		l2.setDisplayedMnemonic(KeyEvent.VK_D);
		l2.setLabelFor(t2);
		l3.setDisplayedMnemonic(KeyEvent.VK_D);
		l3.setLabelFor(t3);
		l4.setDisplayedMnemonic(KeyEvent.VK_P);
		l4.setLabelFor(priorityLevel);
		l5.setDisplayedMnemonic(KeyEvent.VK_C);
		l5.setLabelFor(block);

		frame2.getContentPane().add(l1);
		frame2.getContentPane().add(t1);
		frame2.getContentPane().add(l2);
		frame2.getContentPane().add(t2);
		frame2.getContentPane().add(l3);
		frame2.getContentPane().add(t3);
		frame2.getContentPane().add(l4);
		frame2.getContentPane().add(priorityLevel);
		frame2.getContentPane().add(l5);
		frame2.getContentPane().add(block);
		frame2.getContentPane().add(c);
		frame2.getContentPane().add(saveNew);

		// Takes in user input and inserts it into the PlannerDatabase
		saveNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Bridge b = new Bridge();
				String s1 = t1.getText();
				String s2 = t2.getText();
				String s3 = t3.getText();
				String s4 = (String) priorityLevel.getSelectedItem();
				String s5 = (String) block.getSelectedItem();
				b.SQLstatementInsert("INSERT INTO planner (Title, Task, DueDate, Importance, BlockNumber) VALUES ( '"
						+ s1 + "' , '" + s2 + "' , '" + s3 + "' , '" + s4 + "' , '" + s5 + "')");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				priorityLevel.setSelectedIndex(0);
				block.setSelectedIndex(0);
				frame2.dispose();
			}
		});

		// Stops the reminder creation process and exits the user
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
				priorityLevel.setSelectedIndex(0);
				block.setSelectedIndex(0);
				frame2.dispose();
				frame1.setVisible(true);
			}
		});

		BL1 = new JButton("Block 1");
		BL2 = new JButton("Block 2");
		BL3 = new JButton("Block 3");
		BL4 = new JButton("Block 4");
		BL5 = new JButton("Block 5");
		BL6 = new JButton("Block 6");
		BL7 = new JButton("Block 7");

		JButton back = new JButton("Back");

		// Creates new page to choose desired class
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame1.dispose();
				frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame3.setTitle("Classes");
				frame3.setSize(600, 600);
				frame3.setVisible(true);
				frame3.getContentPane();
				GridLayout notesLayout = new GridLayout(8, 1);
				frame3.setLayout(notesLayout);
				frame3.add(BL1);
				frame3.add(BL2);
				frame3.add(BL3);
				frame3.add(BL4);
				frame3.add(BL5);
				frame3.add(BL6);
				frame3.add(BL7);
				frame3.add(back);

			}
		});

		// Takes user back to start screen
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame3.dispose();
				frame1.setVisible(true);
			}
		});

		try {
			populateBlock(BL1, "BL1");
			populateBlock(BL2, "BL2");
			populateBlock(BL3, "BL3");
			populateBlock(BL4, "BL4");
			populateBlock(BL5, "BL5");
			populateBlock(BL6, "BL6");
			populateBlock(BL7, "BL7");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Displays saves notes and allows for changes

	}

	// Creates buttons to choose desired notes
	public void populateBlock(JButton b, String a) throws SQLException {
		Bridge B = new Bridge();
		String name1 = B.SQLstatementSelect("SELECT Title, Task FROM planner WHERE BlockNumber = '" + a
				+ "' ORDER BY Title OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY", "Title");
		String name2 = B.SQLstatementSelect("SELECT Title, Task FROM planner WHERE BlockNumber = '" + a
				+ "' ORDER BY Title OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY", "Title");
		String name3 = B.SQLstatementSelect("SELECT Title, Task FROM planner WHERE BlockNumber = '" + a
				+ "' ORDER BY Title OFFSET 2 ROWS FETCH NEXT 1 ROWS ONLY", "Title");
		String name4 = B.SQLstatementSelect("SELECT Title, Task FROM planner WHERE BlockNumber = '" + a
				+ "' ORDER BY Title OFFSET 3 ROWS FETCH NEXT 1 ROWS ONLY", "Title");
		String name5 = B.SQLstatementSelect("SELECT Title, Task FROM planner WHERE BlockNumber = '" + a
				+ "' ORDER BY Title OFFSET 4 ROWS FETCH NEXT 1 ROWS ONLY", "Title");
		String name6 = B.SQLstatementSelect("SELECT Title, Task FROM planner WHERE BlockNumber = '" + a
				+ "' ORDER BY Title OFFSET 5 ROWS FETCH NEXT 1 ROWS ONLY", "Title");
		String name7 = B.SQLstatementSelect("SELECT Title, Task FROM planner WHERE BlockNumber = '" + a
				+ "' ORDER BY Title OFFSET 6 ROWS FETCH NEXT 1 ROWS ONLY", "Title");
		String name8 = B.SQLstatementSelect("SELECT Title, Task FROM planner WHERE BlockNumber = '" + a
				+ "' ORDER BY Title OFFSET 7 ROWS FETCH NEXT 1 ROWS ONLY", "Title");

		JButton p1 = new JButton(name1);
		JButton p2 = new JButton(name2);
		JButton p3 = new JButton(name3);
		JButton p4 = new JButton(name4);
		JButton p5 = new JButton(name5);
		JButton p6 = new JButton(name6);
		JButton p7 = new JButton(name7);
		JButton p8 = new JButton(name8);

		// Displays all reminders made for desired class
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame3.dispose();
				frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				notes.setTitle("List of Reminders");
				notes.setSize(600, 500);
				notes.setVisible(true);
				notes.getContentPane();
				GridLayout notesTitles = new GridLayout(8, 1);
				notes.setLayout(notesTitles);
				notes.add(p1);
				notes.add(p2);
				notes.add(p3);
				notes.add(p4);
				notes.add(p5);
				notes.add(p6);
				notes.add(p7);
				notes.add(p8);
			}
		});

		// When button one is pressed, display editing page
		p1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				notes.dispose();
				notes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				evDisplay.setTitle(name1);
				evDisplay.setSize(600, 600);
				evDisplay.setVisible(true);
				evDisplay.getContentPane();
				GridLayout newNotesLay = new GridLayout(7, 1);
				evDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
				evDisplay.setLayout(newNotesLay);
				try {
					reDisplay(name1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		// When button two is pressed, display editing page
		p2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				notes.dispose();
				notes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				evDisplay.setTitle("Reminder Edit/View Page");
				evDisplay.setSize(600, 600);
				evDisplay.setVisible(true);
				evDisplay.getContentPane();
				GridLayout newNotesLay = new GridLayout(7, 1);
				evDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
				evDisplay.setLayout(newNotesLay);
				try {
					reDisplay(name2);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		// When button three is pressed, display editing page
		p3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				notes.dispose();
				notes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				evDisplay.setTitle("Reminder Edit/View Page");
				evDisplay.setSize(600, 600);
				evDisplay.setVisible(true);
				evDisplay.getContentPane();
				GridLayout newNotesLay = new GridLayout(7, 1);
				evDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
				evDisplay.setLayout(newNotesLay);
				try {
					reDisplay(name3);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		// When button four is pressed, display editing page
		p4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				notes.dispose();
				notes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				evDisplay.setTitle("Reminder Edit/View Page");
				evDisplay.setSize(600, 600);
				evDisplay.setVisible(true);
				evDisplay.getContentPane();
				GridLayout newNotesLay = new GridLayout(7, 1);
				evDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
				evDisplay.setLayout(newNotesLay);
				try {
					reDisplay (name4);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		// When button five is pressed, display editing page
		p5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				notes.dispose();
				notes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				evDisplay.setTitle("Reminder Edit/View Page");
				evDisplay.setSize(600, 600);
				evDisplay.setVisible(true);
				evDisplay.getContentPane();
				GridLayout newNotesLay = new GridLayout(7, 1);
				evDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
				evDisplay.setLayout(newNotesLay);
				try {
					reDisplay (name5);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		// When button six is pressed, display editing page
		p6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				notes.dispose();
				notes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				evDisplay.setTitle("Reminder Edit/View Page");
				evDisplay.setSize(600, 600);
				evDisplay.setVisible(true);
				evDisplay.getContentPane();
				GridLayout newNotesLay = new GridLayout(7, 1);
				evDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
				evDisplay.setLayout(newNotesLay);
				try {
					reDisplay (name6);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		// When button seven is pressed, display editing page
		p7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				notes.dispose();
				notes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				evDisplay.setTitle("Reminder Edit/View Page");
				evDisplay.setSize(600, 600);
				evDisplay.setVisible(true);
				evDisplay.getContentPane();
				GridLayout newNotesLay = new GridLayout(7, 1);
				evDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
				evDisplay.setLayout(newNotesLay);
				try {
					reDisplay (name7);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		// When button eight is pressed, display editing page
		p8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				notes.dispose();
				notes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				evDisplay.setTitle("Reminder Edit/View Page");
				evDisplay.setSize(600, 600);
				evDisplay.setVisible(true);
				evDisplay.getContentPane();
				GridLayout newNotesLay = new GridLayout(7, 1);
				evDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
				evDisplay.setLayout(newNotesLay);
				try {
					reDisplay (name8);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		});
	}
	
	public void reDisplay (String a) throws SQLException {
		Bridge B = new Bridge();
		JLabel rl1 = new JLabel("Title");
		JLabel rl2 = new JLabel("Desciption");
		JLabel rl3 = new JLabel("Due Date (mm/dd/yyyy)");
		JLabel rl4 = new JLabel("Priority");
		JLabel rl5 = new JLabel("Class");
		String[] rpriority = { "!", "!!", "!!!" };
		String[] rclasses = { "BL1", "BL2", "BL3", "BL4", "BL5", "BL7" };
		JTextField rt1 = new JTextField(50);
		JTextField rt2 = new JTextField(300);
		JTextField rt3 = new JTextField(20);
		JComboBox rpriorityLevel = new JComboBox(rpriority);
		JComboBox rblock = new JComboBox(rclasses);
		JButton saveChg = new JButton("Save Changes");
		JButton c2 = new JButton("Cancel");
		JButton d = new JButton("Delete Reminder");
        rt1.setText(a); 
        rt2.setText(B.SQLstatementSelect("SELECT Task FROM planner WHERE Title = '" + a + "'", "Task"));
        rt3.setText(B.SQLstatementSelect("SELECT DueDate FROM planner WHERE  Title = '" + a + "'", "DueDate"));
        rpriorityLevel.setSelectedItem(B.SQLstatementSelect("SELECT Importance FROM planner WHERE  Title = '" + a + "'", "Importance"));
        rblock.setSelectedItem(B.SQLstatementSelect("SELECT BlockNumber FROM planner WHERE  Title = '" + a + "'", "BlockNumber"));
		rl1.setDisplayedMnemonic(KeyEvent.VK_T);
		rl1.setLabelFor(rt1);
		rl2.setDisplayedMnemonic(KeyEvent.VK_D);
		rl2.setLabelFor(rt2);
		rl3.setDisplayedMnemonic(KeyEvent.VK_D);
		rl3.setLabelFor(rt3);
		rl4.setDisplayedMnemonic(KeyEvent.VK_P);
		rl4.setLabelFor(rpriorityLevel);
		rl5.setDisplayedMnemonic(KeyEvent.VK_C);
		rl5.setLabelFor(rblock);

		evDisplay.getContentPane().add(rl1);
		evDisplay.getContentPane().add(rt1);
		evDisplay.getContentPane().add(rl2);
		evDisplay.getContentPane().add(rt2);
		evDisplay.getContentPane().add(rl3);
		evDisplay.getContentPane().add(rt3);
		evDisplay.getContentPane().add(rl4);
		evDisplay.getContentPane().add(rpriorityLevel);
		evDisplay.getContentPane().add(rl5);
		evDisplay.getContentPane().add(rblock);
		evDisplay.getContentPane().add(c2);
		evDisplay.getContentPane().add(saveChg);
		evDisplay.getContentPane().add(d);

		// Saves the changes made by the user and updates the planner table
		saveChg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Bridge b = new Bridge();
				String s1 = rt1.getText();
				String s2 = rt2.getText();
				String s3 = rt3.getText();
				String s4 = (String) rpriorityLevel.getSelectedItem();
				String s5 = (String) rblock.getSelectedItem();
				b.SQLstatementInsert("UPDATE planner SET Title = '" + s1 + "', Task = '" + s2 + "', DueDate = '" + s3 + "', Importance = '" + s4 
					            	+ "', BlockNumber = '" + s5 + "' WHERE Title = '" + a + "' ");	
				evDisplay.dispose();
			}
		});

		// Stops reminder editing or viewing process and exits user
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				evDisplay.dispose();
			}
		});
		
		//Deletes the saved reminder from the planner table
		d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Bridge b = new Bridge();
				b.SQLstatementUpdate("DELETE FROM planner WHERE Title = '" + a + "'");
				evDisplay.dispose();
			}
		}); 
	}
}
