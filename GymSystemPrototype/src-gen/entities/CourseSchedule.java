package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class CourseSchedule implements Serializable {
	
	/* all primary attributes */
	private String Id;
	private String StartTime;
	private String EndTime;
	
	/* all references */
	private Course CourseScheduletoCourse; 
	
	/* all get and set functions */
	public String getId() {
		return Id;
	}	
	
	public void setId(String id) {
		this.Id = id;
	}
	public String getStartTime() {
		return StartTime;
	}	
	
	public void setStartTime(String starttime) {
		this.StartTime = starttime;
	}
	public String getEndTime() {
		return EndTime;
	}	
	
	public void setEndTime(String endtime) {
		this.EndTime = endtime;
	}
	
	/* all functions for reference*/
	public Course getCourseScheduletoCourse() {
		return CourseScheduletoCourse;
	}	
	
	public void setCourseScheduletoCourse(Course course) {
		this.CourseScheduletoCourse = course;
	}			
	


}
