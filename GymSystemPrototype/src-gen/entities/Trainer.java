package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class Trainer extends FrontDesk  implements Serializable {
	
	/* all primary attributes */
	private String Name;
	private String Description;
	private String RegisterTime;
	
	/* all references */
	private List<Course> TrainertoCourse = new LinkedList<Course>(); 
	
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
	public List<Course> getTrainertoCourse() {
		return TrainertoCourse;
	}	
	
	public void addTrainertoCourse(Course course) {
		this.TrainertoCourse.add(course);
	}
	
	public void deleteTrainertoCourse(Course course) {
		this.TrainertoCourse.remove(course);
	}
	


}
