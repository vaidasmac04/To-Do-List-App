package options;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import content.DataPanel;

public class OptionsPanel {
	private JPanel mainPanel;
	private InputPanel inputPanel;
	private SortPanel sortPanel;
	private JButton delete;
	private JButton modify;
	private JButton expand;
	private JButton exit;
	
	public OptionsPanel(DataPanel dataPanel) {
		mainPanel = new JPanel(new GridLayout(8, 2, 20, 20));
		mainPanel.setPreferredSize(new Dimension(400, 500));
		
		inputPanel = new InputPanel(dataPanel);
		sortPanel = new SortPanel(dataPanel);
		delete = new DeleteButton(dataPanel);
		modify = new ModifyButton(dataPanel);
		expand = new ExpandButton(dataPanel);
		exit = new JButton("Exit");
		
	    addActionToExit();
		addComponents();
		
	}
	
	private void addComponents() {
		//input panel
		mainPanel.add(getLabel("Title", new Color(240, 240, 240)));
		mainPanel.add(inputPanel.getTitle());
		
		mainPanel.add(getLabel("Deadline", new Color(240, 240, 240)));
		mainPanel.add(inputPanel.getDeadline());
		
		mainPanel.add(getLabel("Priority", new Color(240, 240, 240)));
		mainPanel.add(inputPanel.getPriorities());
		
		mainPanel.add(getLabel("Description", new Color(240, 240, 240)));
		mainPanel.add(inputPanel.getDescription());
		
		mainPanel.add(inputPanel.getClearButton());
		mainPanel.add(inputPanel.getAddButton());
		//end
		
		//sort panel
		mainPanel.add(getLabel("Select sort type:", new Color(215, 215, 145)));
		JPanel sortOptionsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		sortOptionsPanel.add(sortPanel.getSortDateRadio());
		sortPanel.getSortDateRadio().setBackground(new Color(215, 215, 145));
		sortOptionsPanel.add(sortPanel.getSortPriorityRadio());
		sortPanel.getSortPriorityRadio().setBackground(new Color(215, 215, 145));
		sortOptionsPanel.setBackground(new Color(215, 215, 145));
		mainPanel.add(sortOptionsPanel);
		//end
		
		delete.setBackground(new Color(215, 215, 145));
		mainPanel.add(delete);
		
		modify.setBackground(new Color(215, 215, 145));
		mainPanel.add(modify);
		
		expand.setBackground(new Color(215, 215, 145));
		mainPanel.add(expand);
		
		exit.setBackground(new Color(215, 215, 145));
		mainPanel.add(exit);
	}
	
	private void addActionToExit() {
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame)SwingUtilities.getRoot(mainPanel);
			    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
			
		});
	}
	
	private JLabel getLabel(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(color);
		return label;
	}
	
	
	public JPanel getPanel() {
		return mainPanel;
	}
}
