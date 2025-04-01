package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class CourseRoom implements Serializable {
	
	/* all primary attributes */
	private String Id;
	private String Location;
	private int Capacity;
	
	/* all references */
	
	/* all get and set functions */
	public String getId() {
		return Id;
	}	
	
	public void setId(String id) {
		this.Id = id;
	}
	public String getLocation() {
		return Location;
	}	
	
	public void setLocation(String location) {
		this.Location = location;
	}
	public int getCapacity() {
		return Capacity;
	}	
	
	public void setCapacity(int capacity) {
		this.Capacity = capacity;
	}
	
	/* all functions for reference*/
	


}
