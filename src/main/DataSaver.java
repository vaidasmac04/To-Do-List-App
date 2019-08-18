package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import content.UserData;
import testers.InvalidInputException;

public class DataSaver {
	
	public static List<UserData> loadUserData(){
		List<UserData> userDataList = new ArrayList<>();
		String[] dataNames = {"Title ", "Priority ", "Deadline ", "Description "};

		Scanner scanner;
		UserData userData = new UserData();
		try {
			scanner = new Scanner(new File("data.txt"));
			int i = 0;
			
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				
				if(data.contains(dataNames[0])) {
					data = data.replace(dataNames[0], "");
					userData.setTitle(data);
					i++;
				}

				else if(data.contains(dataNames[1])) {
					data = data.replace(dataNames[1], "");
					userData.setPriority(data);
					i++;
				}

				else if(data.contains(dataNames[2])) {
					data = data.replace(dataNames[2], "");
					userData.setDeadlineIgnoringPast(data);
					i++;
				}

				else if(data.contains(dataNames[3])) {
					data = data.replace(dataNames[3], "");
					userData.setDescription(data);
					i++;
				}
				
				if(i == 4) {
					userDataList.add(userData);
					userData = new UserData();
					i = 0;
				}
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			return new ArrayList<UserData>();
		} catch(InvalidInputException invalidInputException) { //should never happen
			JOptionPane.showMessageDialog(null, "Data loading error", "Error", JOptionPane.ERROR_MESSAGE);
		} catch(NumberFormatException numberFormatException) { //should never happen
			JOptionPane.showMessageDialog(null, "Data loading error", "Error", JOptionPane.ERROR_MESSAGE);
		}

		return userDataList;
	}
	
	public static void saveUserData(List<UserData> userDataList){
		String[] dataNames = {"Title ", "Priority ", "Deadline ", "Description "};
		
		try {
			FileWriter fileWriter = new FileWriter(new File("data.txt"));
			
			for(int i = 0; i < userDataList.size(); i++) {
				fileWriter.write(dataNames[0]+userDataList.get(i).getTitle()+'\n');
				fileWriter.write(dataNames[1]+userDataList.get(i).getPriority()+'\n');
				fileWriter.write(dataNames[2]+userDataList.get(i).getDeadline()+'\n');
				fileWriter.write(dataNames[3]+userDataList.get(i).getDescription()+'\n');
			}
			
			fileWriter.close();
		}catch(IOException ioException) {
			JOptionPane.showMessageDialog(null, "Data saving error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
