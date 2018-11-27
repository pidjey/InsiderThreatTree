package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class MainMenu extends JFrame{

	private JButton buttonPickFiles;
	private JButton buttonLoadUsers;
	private String filePathUserLog;
	private String filePathDeviceLog;
	private String filePathHttpLog;
	private String filePathLogonLog;
	
	public MainMenu() {
		filePathUserLog = null;
		filePathDeviceLog = null;
		filePathHttpLog = null;
		filePathLogonLog = null;
		
		createView();
		
		setTitle("File Picker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(150, 180);
		
	}
	
	public void createView() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		buttonPickFiles = new JButton("Pick Files");
		buttonPickFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilesPicker.executeUI();
				
			}
		});
		panel.add(buttonPickFiles);
		
		buttonLoadUsers = new JButton("Load files");
		buttonLoadUsers.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(filePathUserLog == null ||
						filePathDeviceLog == null ||
						filePathHttpLog == null ||
						filePathLogonLog == null){
					JOptionPane.showMessageDialog(null, "Files path have not been set");
				}
				
			}
		});
		
		panel.add(buttonLoadUsers);
	}
	
	public static void executeUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainMenu().setVisible(true);
			}
		});
	}
	
	public void setFilePathUserLog(String filePathUserLog){
		this.filePathUserLog = filePathUserLog;
	}
	public void filePathDeviceLog(String filePathDeviceLog){
		this.filePathDeviceLog = filePathDeviceLog;
	}
	public void filePathHttpLog(String filePathHttpLog){
		this.filePathHttpLog = filePathHttpLog;
	}
	public void filePathLogonLog(String filePathLogonLog){
		this.filePathLogonLog = filePathLogonLog;
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



