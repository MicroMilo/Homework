package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class CoursePayment implements Serializable {
	
	/* all primary attributes */
	private String Id;
	private String UserId;
	private String CourseId;
	private float Price;
	private String PayTime;
	private PayStatus PayStatus;
	
	/* all references */
	private List<Member> CoursePaymenttoMember = new LinkedList<Member>(); 
	
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
	public float getPrice() {
		return Price;
	}	
	
	public void setPrice(float price) {
		this.Price = price;
	}
	public String getPayTime() {
		return PayTime;
	}	
	
	public void setPayTime(String paytime) {
		this.PayTime = paytime;
	}
	public PayStatus getPayStatus() {
		return PayStatus;
	}	
	
	public void setPayStatus(PayStatus paystatus) {
		this.PayStatus = paystatus;
	}
	
	/* all functions for reference*/
	public List<Member> getCoursePaymenttoMember() {
		return CoursePaymenttoMember;
	}	
	
	public void addCoursePaymenttoMember(Member member) {
		this.CoursePaymenttoMember.add(member);
	}
	
	public void deleteCoursePaymenttoMember(Member member) {
		this.CoursePaymenttoMember.remove(member);
	}
	


}
