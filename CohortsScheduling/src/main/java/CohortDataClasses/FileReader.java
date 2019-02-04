package CohortDataClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

	
	public FileReader() {
		 
	}
	
	public static void readClassFile(String fileName, List<Course> courseList) throws FileNotFoundException, ParseException{
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
		course.setName(field[1]); 
		course.setCourseID(field[1]);
		course.setUnitSize(Integer.parseInt(field[13]));
		section.setCrn(Integer.parseInt(field[2]));
		section.setSectionId(field[3]);
		section.setName(field[1]);
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
			if(course.getName().compareTo(field[1])==0) {  
				section = new Section();
				section.setSectionId(field[3]);
				section.setCrn(Integer.parseInt(field[2]));
				section.setName(field[1]);
				section.setLink(field[5]); 
				section.setDaysOfWeek(field[8]); 
				try {
				setTimes(field, section);
				}catch(Exception e) {
					section.setStartTime(null);
					section.setEndTime(null);
				}
				section.setBuilding(field[10]);
				section.setRoom(field[11]);
				section.setInstructor(field[12]);
				section.setSeats(Integer.parseInt(field[13]));
				//add initialized section to sections list
				
				sections.add(section); 
				
			}
			//if not the same course, create new course and add finished course to course list
			else { 
				//finish and set course since we have arrived at a new course
				course.setSections(sections); 
				
				//add finished course to list
				courseList.add(course);
				course = new Course();
				section = new Section();
				sections = new ArrayList<Section>();
				course.setName((field[1]));
				course.setUnitSize(Integer.parseInt(field[13]));
				section.setSectionId(field[3]);
				section.setCrn(Integer.parseInt(field[2]));
				section.setName(field[1]);
				section.setLink(field[5]); 
				try {
					setTimes(field, section);
					}catch(Exception e) {
						section.setStartTime(null);
						section.setEndTime(null);
					}
				section.setDaysOfWeek(field[8]); 
				section.setBuilding(field[10]);
				section.setRoom(field[11]);
				section.setInstructor(field[12]);
				section.setSeats(Integer.parseInt(field[13]));
				sections.add(section);
				
					
			}
			
		}
		scan.close(); 

		
	}
	
	
	public static void readCohortFile(String fileName, List<Cohort> cohortList) throws FileNotFoundException{
		File file  = new File(fileName);
		Scanner scan = new Scanner(file);
		String line; 
		String[] field = new String[7];
		Cohort cohort = new Cohort();
		List<ClassRequirement> requirements = new ArrayList<ClassRequirement>();
		
		ClassRequirement requirement = new ClassRequirement();
		
		//skip title line
		scan.nextLine();		
		
		line = scan.nextLine();
		field = line.split(",");
		requirement.setCohortName(field[0]);
		requirement.setClassName(field[1]);
		requirement.setSeatsNeeded(Integer.parseInt(field[2]));
		cohort.setName(field[0]);  

		requirements.add(requirement);
		
		while(scan.hasNext()) {
			line = scan.nextLine();
			field = line.split(",");
			
			if(cohort.getName().compareTo(field[0])==0) { 
				requirement.setClassName(field[1]);
				requirement.setSeatsNeeded(Integer.parseInt(field[2]));
				requirements.add(requirement);
			}
			else {
				cohort.setRequirements(requirements);
				cohortList.add(cohort);		
				cohort = new Cohort();
				requirements = new ArrayList<ClassRequirement>();
				cohort.setName(field[0]);
				requirement.setClassName(field[1]);
				requirement.setSeatsNeeded(Integer.parseInt(field[2]));
				requirements.add(requirement);				
				
			}
		}
		scan.close();
		
	}
	
	//method to parse and initialize start and end times
	private static  void setTimes(String[] field, Section section) {
		String[] tempTime = new String[2];
		String[]tempTime2 = new String[2];
		int hour;
		int minute;
		
		tempTime = field[9].split(" - ");  
		tempTime2 = tempTime[0].split(":");
		hour = Integer.parseInt(tempTime2[0]);
		minute = Integer.parseInt(tempTime2[1]);		
		section.setStartTime(LocalTime.of(hour, minute));
		tempTime2 = tempTime[1].split(":");
		hour = Integer.parseInt(tempTime2[0]);
		minute = Integer.parseInt(tempTime2[1]); 
		section.setEndTime(LocalTime.of(hour, minute));
	}
	
	
}
