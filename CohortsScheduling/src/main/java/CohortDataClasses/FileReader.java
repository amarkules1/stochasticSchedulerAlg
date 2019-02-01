package CohortDataClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
		
		String[] field = new String[14];
		
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
		setTimes(field, section);
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
				setTimes(field, section);			
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
				setTimes(field, section);
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
	public static  void setTimes(String[] field, Section section) {
		String[] tempTime = new String[2];
		String[]tempTime2 = new String[2];
		int hour;
		int minute;
		
		tempTime = field[9].split(" - ");  
		tempTime2 = tempTime[0].split(":");
		hour = Integer.parseInt(tempTime2[0]);
		minute = Integer.parseInt(tempTime2[1]);		
		//start time
		section.setStartTime(LocalTime.of(hour, minute));
		//end time
		tempTime2 = tempTime[1].split(":");
		hour = Integer.parseInt(tempTime2[0]);
		minute = Integer.parseInt(tempTime2[1]); 
		section.setEndTime(LocalTime.of(hour, minute));
	}
}
