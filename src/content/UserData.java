package content;

import testers.DateInputHandler;
import testers.InvalidInputException;

public class UserData {
	private String title;
	private String description;
	private String deadline;
	private String priority;
	
	//---------------------------getters---------------------------
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getDeadline() {
		return deadline;
	}
	

	public String getPriority() {
		return priority;
	}

	//---------------------------setters---------------------------
	public void setTitle(String title) {
		title = title.trim();
		if(title.length() == 0) {
			throw new InvalidInputException("The title field cannot be empty.");
		}
		this.title = title;
	}

	public void setDescription(String description) {
		description = description.trim();
		this.description = description;
	}
	
	//for data loading from data.txt file
	public void setDeadlineIgnoringPast(String deadline) {
		DateInputHandler.checkFormat(deadline);
		int[] yearMonthDay = DateInputHandler.getYearMonthDay(deadline);
		DateInputHandler.checkYearMonthDay(yearMonthDay[0], yearMonthDay[1], yearMonthDay[2]);
		this.deadline = deadline;
	}

	public void setDeadline(String deadline) {
		deadline = deadline.trim();
		if(deadline.length() == 0) {
			throw new InvalidInputException("The deadline field cannot be empty.");
		}
		DateInputHandler.check(deadline);
		this.deadline = deadline;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}
