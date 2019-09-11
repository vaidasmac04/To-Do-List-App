package content;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class DataTable {
	private JTable table;
	private String[] columnNames;
	private DefaultTableModel model;
	private TableRowSorter<DefaultTableModel> sorter;
	private List<RowSorter.SortKey> sortKeysDate;
	private List<RowSorter.SortKey> sortKeysPriority;
	
	public DataTable(String[] columnNames) {
		this.columnNames = columnNames;
		table = new JTable();
		createTableModel();
		table.setModel(model);
		createSorter();
		createSortingKeys();
		setDateSorting(); //initially JTable data is sorted by date
	}
	
	private void createTableModel() {
		model = new DefaultTableModel() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(columnNames);
	}
	
	public void addRow(UserData userData) {
		model.addRow(new Object[] {userData.getTitle(), userData.getPriority(), userData.getDeadline(),
				userData.getDescription()});
	}
	
	
	private void createSortingKeys() {
		sortKeysDate = new ArrayList<>();
		sortKeysDate.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeysDate.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		
		sortKeysPriority = new ArrayList<>();
		sortKeysPriority.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		sortKeysPriority.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
	}
	
	private void createSorter() {
		sorter = new TableRowSorter<DefaultTableModel>(model) {
			@Override
			public boolean isSortable(int column) {
				return false;
			}
		};
	}
	
	public void setDateSorting() {
		sorter.setComparator(2, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				String[] temp1 = o1.split("-");
				String[] temp2 = o2.split("-");
				
				int year1 = Integer.parseInt(temp1[0]);
				int year2 = Integer.parseInt(temp2[0]);
				
				if(year1 > year2) {
					return 1;
				}
				else if(year1 < year2) {
					return -1;
				}
				
				else{
					int month1 = Integer.parseInt(temp1[1]);
					int month2 = Integer.parseInt(temp2[1]);
					
					if(month1 > month2) {
						return 1;
					}
					else if(month1 < month2) {
						return -1;
					}
					else{
						int day1 = Integer.parseInt(temp1[2]);
						int day2 = Integer.parseInt(temp2[2]);
						
						if(day1 > day2) {
							return 1;
						}
						else if(day1 < day2) {
							return -1;
						}
						else {
							return 0;
						}
					}
				}
			}
		});
		sorter.setSortKeys(sortKeysDate);
		table.setRowSorter(sorter);
	}
	
	public void setPrioritySorting() {
		sorter.setComparator(1, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				int priorityValue1 = findValue(o1);
				int priorityValue2 = findValue(o2);
				
				if(priorityValue1 > priorityValue2) {
					return -1;
				}
				else if(priorityValue1 < priorityValue2) {
					return 1;
				}
				else {
					return 0;
				}
			}
			
			private int findValue(String value) {
				final String[] priorities = {"critical", "high", "medium","low"};
				
				int i = 0;
				while(!value.equals(priorities[i])) {
					i++;
				}
				
				return i;
			}
				
		});
		sorter.setSortKeys(sortKeysPriority);
		table.setRowSorter(sorter);
	}
	
	//---------------------------getters---------------------------
	public JTable getTable() {
		return table;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}

	public TableRowSorter<DefaultTableModel> getSorter() {
		return sorter;
	}
}
