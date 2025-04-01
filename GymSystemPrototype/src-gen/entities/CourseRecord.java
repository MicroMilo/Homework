package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class CourseRecord implements Serializable {
	
	/* all primary attributes */
	private String Id;
	private String UserId;
	private String CourseId;
	private RecordStatus Status;
	
	/* all references */
	
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
	public RecordStatus getStatus() {
		return Status;
	}	
	
	public void setStatus(RecordStatus status) {
		this.Status = status;
	}
	
	/* all functions for reference*/
	


}
