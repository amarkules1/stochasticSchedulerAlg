package CohortDataClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

	
	public FileReader() {
		 
	}
	
	public List<Course> readClassFile(String fileName, List<Course> courseList) throws FileNotFoundException{
		File file = new File(fileName);
		Scanner scan = new Scanner(file); 
		String line;
		String[] field = new String[14];
		Course course = new Course();
		List<Section> sections = new ArrayList<Section>();
		Section section = new Section();
		
		scan.nextLine();						//skip title line
		
		line = scan.nextLine();
		field = line.split(",");
		
		course.setName(field[4]); 
		
		
		section.setSeats(Integer.parseInt(field[13]));
		section.setName(field[1]);
		section.setCrn(field[2]);
		
		sections.add(section);
				
		while(scan.hasNext()) {
			line = scan.nextLine();
			field = line.split(","); 
			
			if(course.getName().compareTo(field[0])==0) {
				section.setName(field[1]); 
				section.setSeats(Integer.parseInt(field[6]));
				sections.add(section);
				
			}
			else {
				course.setSections(sections);
				courseList.add(course);
				course.setName((field[0]));
				section.setName(field[1]); 
				section.setSeats(Integer.parseInt(field[6]));
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
		
		scan.nextLine();		//skip title line
		
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
