package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class FrontDesk implements Serializable {
	
	/* all primary attributes */
	private String Id;
	private String Phone;
	private String Password;
	
	/* all references */
	
	/* all get and set functions */
	public String getId() {
		return Id;
	}	
	
	public void setId(String id) {
		this.Id = id;
	}
	public String getPhone() {
		return Phone;
	}	
	
	public void setPhone(String phone) {
		this.Phone = phone;
	}
	public String getPassword() {
		return Password;
	}	
	
	public void setPassword(String password) {
		this.Password = password;
	}
	
	/* all functions for reference*/
	


}
