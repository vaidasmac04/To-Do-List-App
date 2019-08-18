package content;

import javax.swing.JScrollPane;

import main.DataSaver;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

public class DataPanel {
	private JScrollPane scrollPanel;
	private JPanel panel;
	private List<UserData> userDataList;
	private DataTable dataTable;
	
	public DataPanel() {
		userDataList = new ArrayList<>();
		createScrollPane();
		loadData();
		createTable();
		addLoadedDataToTable();
		scrollPanel.getViewport().add(dataTable.getTable());
	}
	
	private void loadData() {
		userDataList = DataSaver.loadUserData();
	}
	
	private void createScrollPane() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);
		scrollPanel = new JScrollPane();
	}
	
	private void addLoadedDataToTable() {
		for(int i = 0; i < userDataList.size(); i++) {
			dataTable.addRow(userDataList.get(i));
		}
	}
	
	private void createTable() {
		dataTable = new DataTable(new String[] {"Title", "Priority", "Deadline", "Description"});
	}
	
	public void updateAdd(UserData newJob) {
		dataTable.addRow(newJob);
	}
	
	//---------------------------getters---------------------------
	public DataTable getDataTable() {
		return dataTable;
	}

	public JScrollPane getPanel() {
		return scrollPanel;
	}
}
