package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import dataManipulators.UserTrees;
import logReaders.UserReader;

public class MainMenu extends JFrame{

	private JLabel labelEmpty;
	private JLabel emptySpace(int width, int height) { 
		JLabel empty = new JLabel("");
		empty.setPreferredSize(new Dimension(width, height));
		return empty; 
	}
	
	private JButton buttonPickFiles;
	
	private JButton buttonLoadUsers;
	
	private JLabel labelSelectTime;
	private JButton buttonLoadActivities;
	private JButton buttonSelectTimeBegin;
	private JButton buttonSelectTimeEnd;
	
	private JButton buttonVisualizeUsers;
	
	private FilesPicker filesPicker;
	
	private UserTrees userTrees;
	
	private static int WINDOW_SIZE_WIDTH = 500;
	private static int WINDOW_SIZE_HEIGHT = 400;
	
	public MainMenu() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createView();
		
		setTitle("File Picker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT);
		
	}
	
	private void createView() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		

		panel.add(emptySpace(WINDOW_SIZE_WIDTH - 110,15));
		filesPicker = new FilesPicker();
		buttonPickFiles = new JButton("Pick Files");
		buttonPickFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filesPicker.setVisible(true);
			}
		});
		panel.add(buttonPickFiles);
		
		panel.add(emptySpace(WINDOW_SIZE_WIDTH - 110, 15));
		buttonLoadUsers = new JButton("Load files");
		buttonLoadUsers.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if( filesPicker.getUserLogPath()==null || filesPicker.getDeviceLogPath()==null || 
						filesPicker.getHttpLogPath() == null || filesPicker.getLogonLogPath()==null )
				{
					JOptionPane.showMessageDialog(null, "Files path have not been set");
				}else {
					userTrees = new UserTrees();
					UserReader userreader = new UserReader(filesPicker.getUserLogPath());
					userreader.loadUsersFromCsv(userTrees);
					JOptionPane.showMessageDialog(null, "Done!");
				}
			}
		});
		panel.add(buttonLoadUsers);
		
		labelSelectTime = new JLabel("Select time period:");
		labelSelectTime.setPreferredSize(new Dimension(WINDOW_SIZE_WIDTH - 270,15));
		buttonSelectTimeBegin = new JButton("Begin");
		buttonSelectTimeEnd = new JButton("End");
		buttonLoadActivities = new JButton("Load Activities");
		panel.add(labelSelectTime);
		panel.add(buttonSelectTimeBegin);
		panel.add(buttonSelectTimeEnd);
		panel.add(buttonLoadActivities);
		
		panel.add(emptySpace(WINDOW_SIZE_WIDTH - 140, 15));
		buttonVisualizeUsers = new JButton("Visualize Users");
		panel.add(buttonVisualizeUsers);
		
	}
	
	public static void executeUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainMenu().setVisible(true);
			}
		});
	}
	
}

/*UI without extending JFrame*/
/*public static void main(String[] args) {
	JFrame frame = new JFrame();
	
	JPanel panel = new JPanel();
	panel.setBackground(Color.GRAY);
	frame.getContentPane().add(panel);
	
	JButton button = new JButton("This is a button");
	panel.add(button);
	
	JTextField textField = new JTextField("Ahahaha");
	textField.setPreferredSize(new Dimension(200, 15));
	panel.add(textField);
	
	frame.setSize(new Dimension(500, 400));
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	frame.setTitle("A simple JFrame");
	frame.setResizable(false);
	frame.setVisible(true);
}*/

/*
 * someElement.addActionListener( a class which implements ActionListener )
 * ActionListener class can be implemented inline with new ActionListener(){ *code* }
 */

/*
 * After instantiating a panel element, to add it to the JFrame, just call the methods getContentPane().add()
 * then, gui elements can be added to this panel by JPanel.add();
 * */



