package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import content.DataPanel;

public class SortPanel {
	private DataPanel dataPanel;
	
	private JRadioButton sortDateRadio;
	private JRadioButton sortPriorityRadio;
	private ButtonGroup dateGroup;
	
	private JButton sort;
	
	public SortPanel(DataPanel dataPanel) {
		this.dataPanel = dataPanel;
		create();
	    addEventToSorting();
	}
	
	private void create() {
		dateGroup = new ButtonGroup();
		
		sortDateRadio = new JRadioButton("Sort by date");
		sortDateRadio.setSelected(true);
		dateGroup.add(sortDateRadio);
		
		sortPriorityRadio = new JRadioButton("Sort by priority");
		dateGroup.add(sortPriorityRadio);
	}

	private void addEventToSorting() {
		
		sortDateRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dataPanel.getDataTable().setDateSorting();
			}
		});
		
		sortPriorityRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dataPanel.getDataTable().setPrioritySorting();
			}
		});
	}

	//---------------------------getters---------------------------
	public JRadioButton getSortDateRadio() {
		return sortDateRadio;
	}

	public JRadioButton getSortPriorityRadio() {
		return sortPriorityRadio;
	}

	public JButton getSort() {
		return sort;
	}
}
