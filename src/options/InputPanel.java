package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import content.DataPanel;
import content.UserData;
import testers.InvalidInputException;

public class InputPanel{
	
	private JTextField title;
	private JTextField deadline;
	private JTextArea description;
	private JScrollPane scroll;
	private JComboBox<String> priorities;
	
	private JButton addButton;
	private JButton clearButton;
	
	private DataPanel dataPanel;
	private UserData userData;
	
	public InputPanel(DataPanel dataPanel) {
		this.dataPanel = dataPanel;
		create();
	}
	
	private void create() {
		title = new JTextField();
		deadline = new JTextField();
		description = new JTextArea();
		description.setLineWrap(true);
		scroll = new JScrollPane(description);
		scroll.setAutoscrolls(true);
		createPrioritiesBox();
		createButtons();
	}

	private void createPrioritiesBox() {
		priorities = new JComboBox<String>();
		priorities.addItem("low");
		priorities.addItem("medium");
		priorities.addItem("high");
		priorities.addItem("critical");
	}
	
	private void createButtons() {
		createAddButton();
		createClearButton();
	}
	
	private void createAddButton() {
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(addButton.getText().equals("Add")) {//for adding new element
						getInput();
						dataPanel.updateAdd(getUserData());
						clearFields();
					}
					else if(addButton.getText().equals("Save")) {//for editing element
						getInput();
						int tempSelectedRow = dataPanel.getDataTable().getTable().getSelectedRow();
						int selectedRow = dataPanel.getDataTable().getTable().convertRowIndexToModel(tempSelectedRow);
						dataPanel.getDataTable().getModel().setValueAt(userData.getTitle(), selectedRow, 0);
						dataPanel.getDataTable().getModel().setValueAt(userData.getPriority(), selectedRow, 1);
						dataPanel.getDataTable().getModel().setValueAt(userData.getDeadline(), selectedRow, 2);
						dataPanel.getDataTable().getModel().setValueAt(userData.getDescription(), selectedRow, 3);
					    dataPanel.getDataTable().getSorter().sort();
					    
					    //close window after saving changes
						JFrame frame = (JFrame)SwingUtilities.getRoot(addButton);
						frame.dispose();
					}
				}
				catch(InvalidInputException invalidInputException) {
					JOptionPane.showMessageDialog(null, invalidInputException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(NumberFormatException numberFormatException) {
					JOptionPane.showMessageDialog(null, "Incorrect date format", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void createClearButton() {
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
	}
	
	private void clearFields() {
		title.setText("");
		deadline.setText("");
	    description.setText("");
	    priorities.setSelectedIndex(0);
	}

	public void getInput(){
		userData = new UserData();
		
		userData.setTitle(title.getText());
		userData.setDeadline(deadline.getText());
			
		userData.setPriority((String)priorities.getSelectedItem());
		
		if(description.getText().length() == 0) {
			int answer = JOptionPane.showConfirmDialog(null, "Do you want to add new task without description?", 
					"Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(answer == JOptionPane.OK_OPTION) {
				userData.setDescription("");
			}
			else {
				throw new InvalidInputException("Please fill description field");
			}
		}
		
		userData.setDescription(description.getText());
	}

	//---------------------------getters---------------------------
	public JTextField getTitle() {
		return title;
	}

	public JTextField getDeadline() {
		return deadline;
	}

	public JScrollPane getDescription() {
		return scroll;
	}
	
	public JComboBox<String> getPriorities() {
		return priorities;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public UserData getUserData() {
		return userData;
	}
	
	//---------------------------setters---------------------------
	public void setDescriptionText(String text) {
		description.setText(text);
	}
}

