package CohortDataClasses;

import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;

import java.io.Serializable;
import java.util.List;
public class Course implements Serializable {
    @ValueRangeProvider(id = "courseSections")
	List<Section> sections;
	String name;
	List<Cohort> cohortsNeedClass;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Cohort> getCohortsNeedClass() {
		return cohortsNeedClass;
	}

	public void setCohortsNeedClass(List<Cohort> cohortsNeedClass) {
		this.cohortsNeedClass = cohortsNeedClass;
	}

}
