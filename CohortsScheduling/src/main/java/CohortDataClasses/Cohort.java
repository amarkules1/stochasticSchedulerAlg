package CohortDataClasses;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.List;

@PlanningEntity
public class Cohort {
	
	private Course requirements[];

    @PlanningVariable(valueRangeProviderRefs = {"courseSection0"}, nullable = true)
    private Section enrolledClass0;

    @PlanningVariable(valueRangeProviderRefs = {"courseSection1"}, nullable = true)
    private Section enrolledClass1;

    @PlanningVariable(valueRangeProviderRefs = {"courseSection2"}, nullable = true)
    private Section enrolledClass2;

    @PlanningVariable(valueRangeProviderRefs = {"courseSection3"}, nullable = true)
    private Section enrolledClass3;

    @PlanningVariable(valueRangeProviderRefs = {"courseSection4"}, nullable = true)
    private Section enrolledClass4;

    @PlanningVariable(valueRangeProviderRefs = {"courseSection5"}, nullable = true)
    private Section enrolledClass5;

    private String name;

	public Cohort() {
		requirements = new Course[6];
	}

	@ValueRangeProvider(id = "courseSection0")
    public List<Section> posibleSections0(){
        if(requirements[0] == null)
            return null;
	    return requirements[0].getSections();
    }

    @ValueRangeProvider(id = "courseSection1")
    public List<Section> posibleSections1(){
        if(requirements[1] == null)
            return null;
	    return requirements[1].getSections();
    }

    @ValueRangeProvider(id = "courseSection2")
    public List<Section> posibleSections2(){
        if(requirements[2] == null)
            return null;
	    return requirements[2].getSections();
    }

    @ValueRangeProvider(id = "courseSection3")
    public List<Section> posibleSections3(){
        if(requirements[3] == null)
            return null;
	    return requirements[3].getSections();
    }

    @ValueRangeProvider(id = "courseSection4")
    public List<Section> posibleSections4(){
        if(requirements[4] == null)
            return null;
	    return requirements[4].getSections();
    }

    @ValueRangeProvider(id = "courseSection5")
    public List<Section> posibleSections5(){
	    if(requirements[5] == null)
	        return null;
        return requirements[5].getSections();
    }

	public Course[] getRequirements() {
		return requirements;
	}

	public void setRequirements(Course[] requirements) {
		for (int i = 0; i<requirements.length && i < 6; i++){
		    this.requirements[i] = requirements[i];
        }

        for (int i = requirements.length; i<6; i++){
            this.requirements[i] = null;
        }
	}


	public Section[] getEnrolledClasses() {
		return new Section[] {enrolledClass0, enrolledClass1, enrolledClass2, enrolledClass3, enrolledClass4, enrolledClass5};
	}

	public void setEnrolledClasses(List<Section> enrolledClasses) {
		if(enrolledClasses.size() > 0){
		    enrolledClass0 = enrolledClasses.get(0);
        }

        else {
            enrolledClass0 = null;
        }

        if(enrolledClasses.size() > 1){
            enrolledClass1 = enrolledClasses.get(1);
        }

        else {
            enrolledClass1 = null;
        }

        if(enrolledClasses.size() > 2){
            enrolledClass2 = enrolledClasses.get(2);
        }

        else {
            enrolledClass2 = null;
        }

        if(enrolledClasses.size() > 3){
            enrolledClass3 = enrolledClasses.get(3);
        }

        else {
            enrolledClass3 = null;
        }

        if(enrolledClasses.size() > 4){
            enrolledClass4 = enrolledClasses.get(4);
        }

        else {
            enrolledClass4 = null;
        }

        if(enrolledClasses.size() > 5){
            enrolledClass5 = enrolledClasses.get(5);
        }

        else {
            enrolledClass5 = null;
        }
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
