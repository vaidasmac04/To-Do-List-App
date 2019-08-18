package options;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import content.DataPanel;

public class ExpandButton extends JButton implements ActionListener{

	private DataPanel dataPanel;
	
	public ExpandButton(DataPanel dataPanel) {
		super("Expand");
		this.dataPanel = dataPanel;
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			expandElement();
		}
		catch(NoSuchElementException noSuchElementException) {
			JOptionPane.showMessageDialog(null,  noSuchElementException.getMessage(), "Error",
					 JOptionPane.ERROR_MESSAGE);
		}
		catch(IllegalArgumentException illegalArgumentException) {
			JOptionPane.showMessageDialog(null,  illegalArgumentException.getMessage(), "Error",
					 JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void expandElement() {
		JTable table = dataPanel.getDataTable().getTable();
		
		if(table.getSelectedRowCount() == 0) {
			throw new NoSuchElementException("Please select row to be expanded");
		}
		
		if(table.getSelectedRowCount() != 1) {
			throw new IllegalArgumentException("Please select only ONE row");
		}
		
		int confirm = JOptionPane.showConfirmDialog(null, "Do you want to expand selected row?", "Message",
				 JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(confirm == JOptionPane.YES_OPTION) {
			DefaultTableModel model = dataPanel.getDataTable().getModel();
			int tempSelectedRow = table.getSelectedRow();
			int selectedRow = table.convertRowIndexToModel(tempSelectedRow);
			
			String[] data = new String[4];
			
			for(int i = 0; i < model.getColumnCount(); i++) {
				data[i] = (String)model.getDataVector().elementAt(selectedRow).elementAt(i);
			}
			
			createExpandPanel(data);
		}
	}
	
	private void createExpandPanel(String[] data) {
		JFrame frame = new JFrame("Expand element");
		JPanel expandPanel = new JPanel(new GridLayout(4, 1, 15, 15));
		expandPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JLabel title = getField();
		JLabel deadline = getField();
		JLabel priority = getField();
		
		JTextArea description = new JTextArea();
		description.setEditable(false);
		description.setLineWrap(true);
		JScrollPane descriptionScroll = new JScrollPane(description);
		
		expandPanel.add(title);
		expandPanel.add(deadline);
		expandPanel.add(priority);
		expandPanel.add(descriptionScroll);
		
		title.setText((String)data[0]);
		priority.setText((String)data[1]);
		deadline.setText((String)data[2]);
		description.setText((String)data[3]);
		
		frame.add(expandPanel);
		frame.setSize(600, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private JLabel getField() {
		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return label;
	}
}
