package CohortDataClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

	
	public FileReader() {
		 
	}
	
	public List<Course> readClassFile(String fileName, List<Course> courseList) throws FileNotFoundException, ParseException{
		File file = new File(fileName);
		Scanner scan = new Scanner(file); 
		String line;
		String[] tempTime = new String[2];
		String[] field = new String[14];
		String tempDay = "01/01/2019";
		Course course = new Course();
		List<Section> sections = new ArrayList<Section>();
		Section section = new Section(); 
		
		//skip title line
		scan.nextLine();	
		line = scan.nextLine();
		field = line.split(",");
		course.setName(field[4]); 
		section.setSectionId(field[1]);
		section.setCrn(Integer.parseInt(field[2]));
		section.setSection(Integer.parseInt(field[3])); 
		section.setName(field[4]);
		section.setLink(field[5]); 
		section.setDaysOfWeek(field[8]);
		tempTime = field[9].split(" - ");  
		
		//start time
		//end time
		section.setBuilding(field[10]);
		section.setRoom(field[11]);
		section.setInstructor(field[12]);
		section.setSeats(Integer.parseInt(field[13]));

		//add initialized section to sections list
		sections.add(section); 
				
		while(scan.hasNext()) {
			line = scan.nextLine();
			field = line.split(","); 
			
			//check to see if same course, if so add to sections list
			if(course.getName().compareTo(field[0])==0) { 
				section.setSectionId(field[3]);
				section.setCrn(Integer.parseInt(field[2]));
				section.setName(field[4]);
				section.setLink(field[5]); 
				section.setDaysOfWeek(field[8]); 
				tempTime = field[9].split(" - "); 
				//start time
				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm"); 
				section.setStartTime(format.parse(tempDay+ " " + tempTime[0]));
				//end time
				section.setEndTime(format.parse(tempDay+ " "+ tempTime[1]));
				section.setBuilding(field[10]);
				section.setRoom(field[11]);
				section.setInstructor(field[12]);
				section.setSeats(Integer.parseInt(field[13]));
				//add initialized section to sections list
				sections.add(section); 
				
			}
			//if not the same course, create new course and add finished course to course list
			else { 
				course.setSections(sections);
				courseList.add(course);
				course.setName((field[4]));
				section.setSectionId(field[3]);
				section.setCrn(Integer.parseInt(field[2]));
				section.setName(field[4]);
				section.setLink(field[5]); 
				tempTime = field[9].split(" - "); 
				//start time
				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm"); 
				section.setStartTime(format.parse(tempDay+ " " + tempTime[0]));
				//end time
				section.setEndTime(format.parse(tempDay+ " "+ tempTime[1]));
				section.setDaysOfWeek(field[8]); 
				section.setBuilding(field[10]);
				section.setRoom(field[11]);
				section.setInstructor(field[12]);
				section.setSeats(Integer.parseInt(field[13]));
				//make new sections list for new course object
				sections = new ArrayList<Section>(); 	
			}
			
		}
		scan.close();
		return courseList;
	}
	public List<Cohort> readCohortFile(String fileName, List<Cohort> cohortList) throws FileNotFoundException{
		File file  = new File(fileName);
		Scanner scan = new Scanner(file);
		String line; 
		String[] field = new String[7];
		Cohort cohort = new Cohort();
		Course[] requirements = new Course[6];
		Course requirement = new Course();
		int index=0;
		
		//skip title line
		scan.nextLine();		
		
		line = scan.nextLine();
		field = line.split(",");
		cohort.setName(field[0]);  
		requirement.setName(field[0]);
		requirements[0] = requirement;
		
		while(scan.hasNext()) {
			line = scan.nextLine();
			field = line.split(",");
			
			if(cohort.getName().compareTo(field[0])==0) { 
				index++; 
				requirement.setName(field[1]);
				requirements[index] = requirement;
			}
			else {
				index=0;
				cohort.setName(field[0]);
				requirement.setName(field[1]); 
				requirements[index] = requirement;				
				
			}
		}
		scan.close();
		return cohortList;
	}
}
