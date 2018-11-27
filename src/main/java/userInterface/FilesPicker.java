package userInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class FilesPicker extends JDialog{
	private JLabel labelUserLog;
	private JLabel labelDeviceLog;
	private JLabel labelHttpLog;
	private JLabel labelLogonLog;
	
	private JTextField textFieldUserLog;
	private JTextField textFieldDeviceLog;
	private JTextField textFieldHttpLog;
	private JTextField textFieldLogonLog;
	
	private JButton buttonUserLog;
	private JButton buttonDeviceLog;
	private JButton buttonHttpLog;
	private JButton buttonLogonLog;
	
	private JButton buttonOk;
	private JButton buttonCancel;
	
	private String filePathUserLog, filePathDeviceLog, filePathHttpLog, filePathLogonLog;
	
	
//	private FileChooserUI fileChooserUI;
	private JFileChooser fileChooser;
	
	public FilesPicker() {
		
		this.filePathUserLog = filePathUserLog;
		this.filePathDeviceLog = filePathDeviceLog;
		this.filePathHttpLog = filePathHttpLog;
		this.filePathLogonLog = filePathLogonLog;
		
		setModal(true);
		
		createView();
		
		setTitle("File Picker");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(500, 180);
		
		
	}
	
	private void createView() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		fileChooser = new JFileChooser();
		
		//User log section
		labelUserLog = new JLabel("User log file: ");
		labelUserLog.setPreferredSize(new Dimension(87, 20));
		panel.add(labelUserLog);
		
		textFieldUserLog = new JTextField("ABSOLUTE PATH");
		textFieldUserLog.setPreferredSize(
				new Dimension(300, 20));
		panel.add(textFieldUserLog);
		
		buttonUserLog = new JButton("Choose...");
		buttonUserLog.addActionListener( new FileChooserUI(textFieldUserLog) );
		panel.add(buttonUserLog);
		
		//Device log section
		labelDeviceLog = new JLabel("Device log file: ");
		labelDeviceLog.setPreferredSize(new Dimension(87, 20));
		panel.add(labelDeviceLog);	
		
		textFieldDeviceLog = new JTextField("ABSOLUTE PATH");
		textFieldDeviceLog.setPreferredSize(new Dimension(300, 20));
		panel.add(textFieldDeviceLog);
		
		buttonDeviceLog = new JButton("Choose...");
		buttonDeviceLog.addActionListener( new FileChooserUI(textFieldDeviceLog) );
		panel.add(buttonDeviceLog);
		
		//Http log section
		labelHttpLog = new JLabel("Http log file: ");
		labelHttpLog.setPreferredSize(new Dimension(87, 20));
		panel.add(labelHttpLog);	
		
		textFieldHttpLog = new JTextField("ABSOLUTE PATH");
		textFieldHttpLog.setPreferredSize(new Dimension(300, 20));
		panel.add(textFieldHttpLog);
		
		buttonHttpLog = new JButton("Choose...");
		buttonHttpLog.addActionListener( new FileChooserUI(textFieldHttpLog) );
		panel.add(buttonHttpLog);
		
		
		//Logon log section
		labelLogonLog = new JLabel("Logon log file: ");
		labelLogonLog.setPreferredSize(new Dimension(87, 20));
		panel.add(labelLogonLog);	
		
		textFieldLogonLog = new JTextField("ABSOLUTE PATH");
		textFieldLogonLog.setPreferredSize(new Dimension(300, 20));
		panel.add(textFieldLogonLog);
		
		buttonLogonLog = new JButton("Choose...");
		buttonLogonLog.addActionListener( new FileChooserUI(textFieldLogonLog) );
		panel.add(buttonLogonLog);
		
		//Confirm or cancel row
		buttonOk = new JButton("OK");
		buttonOk.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						filePathUserLog = textFieldUserLog.getText();
						filePathDeviceLog = textFieldDeviceLog.getText();
						filePathHttpLog = textFieldHttpLog.getText();
						filePathLogonLog = textFieldLogonLog.getText();
					}
				}
		);
		panel.add(buttonOk);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						try {
							dispose();
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}
				}
		);
		panel.add(buttonCancel);
	}
	
	public static void executeUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FilesPicker().setVisible(true);
			}
		});
	}	
	
	private class FileChooserUI implements ActionListener{

		JTextField textFieldLogPath;
		
		public FileChooserUI(JTextField textFieldLogPath) {
			this.textFieldLogPath = textFieldLogPath;
			
		}
		
		public void actionPerformed(ActionEvent arg0) {
			fileChooser.setCurrentDirectory( new File( System.getProperty("user.dir") ) );
			if( fileChooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
				textFieldLogPath.setText( fileChooser.getSelectedFile().getAbsolutePath() );
			}
		}
	}
}
