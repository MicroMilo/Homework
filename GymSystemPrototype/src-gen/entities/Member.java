package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class Member extends FrontDesk  implements Serializable {
	
	/* all primary attributes */
	private String Name;
	private String Description;
	private String RegisterTime;
	
	/* all references */
	private List<Course> MembertoCourse = new LinkedList<Course>(); 
	private List<CourseNotification> MembertoCourseNotification = new LinkedList<CourseNotification>(); 
	private List<CourseRecord> MembertoCourseRecord = new LinkedList<CourseRecord>(); 
	
	/* all get and set functions */
	public String getName() {
		return Name;
	}	
	
	public void setName(String name) {
		this.Name = name;
	}
	public String getDescription() {
		return Description;
	}	
	
	public void setDescription(String description) {
		this.Description = description;
	}
	public String getRegisterTime() {
		return RegisterTime;
	}	
	
	public void setRegisterTime(String registertime) {
		this.RegisterTime = registertime;
	}
	
	/* all functions for reference*/
	public List<Course> getMembertoCourse() {
		return MembertoCourse;
	}	
	
	public void addMembertoCourse(Course course) {
		this.MembertoCourse.add(course);
	}
	
	public void deleteMembertoCourse(Course course) {
		this.MembertoCourse.remove(course);
	}
	public List<CourseNotification> getMembertoCourseNotification() {
		return MembertoCourseNotification;
	}	
	
	public void addMembertoCourseNotification(CourseNotification coursenotification) {
		this.MembertoCourseNotification.add(coursenotification);
	}
	
	public void deleteMembertoCourseNotification(CourseNotification coursenotification) {
		this.MembertoCourseNotification.remove(coursenotification);
	}
	public List<CourseRecord> getMembertoCourseRecord() {
		return MembertoCourseRecord;
	}	
	
	public void addMembertoCourseRecord(CourseRecord courserecord) {
		this.MembertoCourseRecord.add(courserecord);
	}
	
	public void deleteMembertoCourseRecord(CourseRecord courserecord) {
		this.MembertoCourseRecord.remove(courserecord);
	}
	


}
