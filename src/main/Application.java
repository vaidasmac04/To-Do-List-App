package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import content.DataPanel;
import content.UserData;
import options.OptionsPanel;

public class Application {
	private JFrame frame;
	private DataPanel dataPanel;
	private OptionsPanel optionsPanel;
	
	public Application() {
		createJFrame();
		addPanels();
		modifyPanels();
		closeProgram();
		
		frame.setVisible(true);
		frame.pack();
	}
	
	private void createJFrame() {
		frame = new JFrame("To do list app");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addPanels() {
		dataPanel = new DataPanel();
		optionsPanel = new OptionsPanel(dataPanel);
		
		frame.add(dataPanel.getPanel(), BorderLayout.CENTER);
		frame.add(optionsPanel.getPanel(), BorderLayout.WEST);
	}
	
	private void modifyPanels() {
		dataPanel.getPanel().setBorder(new EmptyBorder(10, 10, 10, 10));
		dataPanel.getPanel().setBackground(Color.GRAY);
		
		optionsPanel.getPanel().setBorder(new EmptyBorder(10, 10, 10, 10));
		optionsPanel.getPanel().setBackground(Color.GRAY);
		
	}
	
	private void closeProgram() {
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				DataSaver.saveUserData(getDataFromTable());
				super.windowClosing(e);
			}
		});
	}
	
	private List<UserData> getDataFromTable() {
		List<UserData> userData = new ArrayList<UserData>();
		DefaultTableModel model = dataPanel.getDataTable().getModel();
		
		for(int i = 0; i < model.getRowCount(); i++) {
			String[] data = new String[4];
			for(int j = 0; j < model.getColumnCount(); j++) {
				data[j] = (String)model.getValueAt(i, j);
			}
			UserData userDataElement = new UserData();
			userDataElement.setTitle(data[0]);
			userDataElement.setPriority(data[1]);
			userDataElement.setDeadlineIgnoringPast(data[2]);
			userDataElement.setDescription(data[3]);
			userData.add(userDataElement);
		}
		
		return userData;
	}
}
