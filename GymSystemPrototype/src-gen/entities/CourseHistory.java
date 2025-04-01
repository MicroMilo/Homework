package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class CourseHistory implements Serializable {
	
	/* all primary attributes */
	private String Id;
	private String UserId;
	private String CourseId;
	private String RegistrationTime;
	
	/* all references */
	private Course CourseHistorytoCourse; 
	
	/* all get and set functions */
	public String getId() {
		return Id;
	}	
	
	public void setId(String id) {
		this.Id = id;
	}
	public String getUserId() {
		return UserId;
	}	
	
	public void setUserId(String userid) {
		this.UserId = userid;
	}
	public String getCourseId() {
		return CourseId;
	}	
	
	public void setCourseId(String courseid) {
		this.CourseId = courseid;
	}
	public String getRegistrationTime() {
		return RegistrationTime;
	}	
	
	public void setRegistrationTime(String registrationtime) {
		this.RegistrationTime = registrationtime;
	}
	
	/* all functions for reference*/
	public Course getCourseHistorytoCourse() {
		return CourseHistorytoCourse;
	}	
	
	public void setCourseHistorytoCourse(Course course) {
		this.CourseHistorytoCourse = course;
	}			
	


}
