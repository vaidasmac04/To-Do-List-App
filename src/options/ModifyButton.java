package options;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import content.DataPanel;

public class ModifyButton extends JButton implements ActionListener{

	private DataPanel dataPanel;
	
	public ModifyButton(DataPanel dataPanel) {
		super("Edit");
		this.dataPanel = dataPanel;
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			modifyRow();
		}
		catch(NoSuchElementException noSuchElementException) {
			JOptionPane.showMessageDialog(null,  noSuchElementException.getMessage(), "Error",
					 JOptionPane.ERROR_MESSAGE);
		}
		catch(NumberFormatException numberFormatException) {
			JOptionPane.showMessageDialog(null,  "Incorrect date format", "Error",
					 JOptionPane.ERROR_MESSAGE);
		}
		catch(IllegalArgumentException illegalArgumentException) {
			JOptionPane.showMessageDialog(null,  illegalArgumentException.getMessage(), "Error",
					 JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void modifyRow() {
		JTable table = dataPanel.getDataTable().getTable();
		
		if(table.getSelectedRowCount() == 0) {
			throw new NoSuchElementException("Please select row to be modified");
		}
		
		if(table.getSelectedRowCount() != 1) {
			throw new IllegalArgumentException("Please select only ONE row");
		}
		
		int confirm = JOptionPane.showConfirmDialog(null, "Do you want to modify selected row?", "Message",
				 JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(confirm == JOptionPane.YES_OPTION) {
			DefaultTableModel model = dataPanel.getDataTable().getModel();
			int tempSelectedRow = table.getSelectedRow();
			int selectedRow = table.convertRowIndexToModel(tempSelectedRow);
			
			Object[] data = new Object[4];
			
			for(int i = 0; i < model.getColumnCount(); i++) {
				if(i == 1) {
					String priority = (String)model.getDataVector().elementAt(selectedRow).elementAt(i);
					if(priority.equals("low")) {
						data[i] = 0;
					}
					else if(priority.equals("medium")) {
						data[i] = 1;
					}
					else if(priority.equals("high")) {
						data[i] = 2;
					}
					
				}
				else {
					data[i] = (String)model.getDataVector().elementAt(selectedRow).elementAt(i);
				}
			}
			
			createModifyPanel(data);
		}
	}
	
	private void createModifyPanel(Object[] data) {
		JFrame frame = new JFrame("Modify element");
		JPanel modifyPanel = new JPanel(new GridLayout(5, 2, 20, 20));
		InputPanel inputPanel = new InputPanel(dataPanel);
		
		modifyPanel.add(getLabel("Title:"));
		modifyPanel.add(inputPanel.getTitle());
		
		modifyPanel.add(getLabel("Priority:"));
		modifyPanel.add(inputPanel.getPriorities());
		
		modifyPanel.add(getLabel("Deadline:"));
		modifyPanel.add(inputPanel.getDeadline());
		
		
		modifyPanel.add(getLabel("Description:"));
		modifyPanel.add(inputPanel.getDescription());
		
		modifyPanel.add(inputPanel.getClearButton());
		modifyPanel.add(inputPanel.getAddButton());
		inputPanel.getAddButton().setText("Save");
	
		inputPanel.getTitle().setText((String)data[0]);
		inputPanel.getPriorities().setSelectedIndex((Integer)data[1]);
		inputPanel.getDeadline().setText((String)data[2]);
		inputPanel.setDescriptionText((String)data[3]);
		
		frame.add(modifyPanel);
		frame.setSize(400, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private JLabel getLabel(String text) {
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(JLabel.RIGHT);
		return label;
	}
}

