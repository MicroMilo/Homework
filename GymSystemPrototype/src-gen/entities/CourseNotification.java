package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class CourseNotification implements Serializable {
	
	/* all primary attributes */
	private String Id;
	private String UserId;
	private String Message;
	
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
	public String getMessage() {
		return Message;
	}	
	
	public void setMessage(String message) {
		this.Message = message;
	}
	
	/* all functions for reference*/
	


}
