package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import content.DataPanel;

public class DeleteButton extends JButton implements ActionListener{

	private DataPanel dataPanel;
	
	public DeleteButton(DataPanel dataPanel) {
		super("Delete");
		this.dataPanel = dataPanel;
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			deleteRow();
		}
		catch(NoSuchElementException noSuchElementException) {
			JOptionPane.showMessageDialog(null,  noSuchElementException.getMessage(), "Error",
					 JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void deleteRow() {
		JTable table = dataPanel.getDataTable().getTable();
		
		if(table.getSelectedRowCount() == 0) {
			throw new NoSuchElementException("Please select rows to be deleted");
		}
		
		String message;
		if(table.getSelectedRowCount() == 1) {
			message = "Do you want to delete selected row permanently?";
		}
		else {
			message = "Do you want to delete selected rows permanently?";
		}
		
		int confirm = JOptionPane.showConfirmDialog(null, message, "Message",
				 JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(confirm == JOptionPane.YES_OPTION) {
			DefaultTableModel model = dataPanel.getDataTable().getModel();
			
			int selectedRowsNumber = table.getSelectedRowCount();
			
			for(int i = 0; i < selectedRowsNumber; i++) {
				int index = table.convertRowIndexToModel(table.getSelectedRow());
				model.removeRow(index);
			}
		}
	}
}
