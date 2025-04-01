package entities;

import services.impl.StandardOPs;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.Serializable;
import java.lang.reflect.Method;

public class Course implements Serializable {
	
	/* all primary attributes */
	private String Id;
	private String Name;
	private String TrainerId;
	private String Description;
	private String RegisterStartTime;
	private String RegisterEndTime;
	private String EventStartTime;
	private String EventEndTime;
	private float Cost;
	private String RoomId;
	private int Capacity;
	private CourseStatus Status;
	
	/* all references */
	private CourseRoom CoursetoCourseRoom; 
	private List<CourseNotification> CoursetoCourseNotification = new LinkedList<CourseNotification>(); 
	private List<CoursePayment> CoursetoCoursePayment = new LinkedList<CoursePayment>(); 
	private List<CourseRecord> CoursetoCourseRecord = new LinkedList<CourseRecord>(); 
	
	/* all get and set functions */
	public String getId() {
		return Id;
	}	
	
	public void setId(String id) {
		this.Id = id;
	}
	public String getName() {
		return Name;
	}	
	
	public void setName(String name) {
		this.Name = name;
	}
	public String getTrainerId() {
		return TrainerId;
	}	
	
	public void setTrainerId(String trainerid) {
		this.TrainerId = trainerid;
	}
	public String getDescription() {
		return Description;
	}	
	
	public void setDescription(String description) {
		this.Description = description;
	}
	public String getRegisterStartTime() {
		return RegisterStartTime;
	}	
	
	public void setRegisterStartTime(String registerstarttime) {
		this.RegisterStartTime = registerstarttime;
	}
	public String getRegisterEndTime() {
		return RegisterEndTime;
	}	
	
	public void setRegisterEndTime(String registerendtime) {
		this.RegisterEndTime = registerendtime;
	}
	public String getEventStartTime() {
		return EventStartTime;
	}	
	
	public void setEventStartTime(String eventstarttime) {
		this.EventStartTime = eventstarttime;
	}
	public String getEventEndTime() {
		return EventEndTime;
	}	
	
	public void setEventEndTime(String eventendtime) {
		this.EventEndTime = eventendtime;
	}
	public float getCost() {
		return Cost;
	}	
	
	public void setCost(float cost) {
		this.Cost = cost;
	}
	public String getRoomId() {
		return RoomId;
	}	
	
	public void setRoomId(String roomid) {
		this.RoomId = roomid;
	}
	public int getCapacity() {
		return Capacity;
	}	
	
	public void setCapacity(int capacity) {
		this.Capacity = capacity;
	}
	public CourseStatus getStatus() {
		return Status;
	}	
	
	public void setStatus(CourseStatus status) {
		this.Status = status;
	}
	
	/* all functions for reference*/
	public CourseRoom getCoursetoCourseRoom() {
		return CoursetoCourseRoom;
	}	
	
	public void setCoursetoCourseRoom(CourseRoom courseroom) {
		this.CoursetoCourseRoom = courseroom;
	}			
	public List<CourseNotification> getCoursetoCourseNotification() {
		return CoursetoCourseNotification;
	}	
	
	public void addCoursetoCourseNotification(CourseNotification coursenotification) {
		this.CoursetoCourseNotification.add(coursenotification);
	}
	
	public void deleteCoursetoCourseNotification(CourseNotification coursenotification) {
		this.CoursetoCourseNotification.remove(coursenotification);
	}
	public List<CoursePayment> getCoursetoCoursePayment() {
		return CoursetoCoursePayment;
	}	
	
	public void addCoursetoCoursePayment(CoursePayment coursepayment) {
		this.CoursetoCoursePayment.add(coursepayment);
	}
	
	public void deleteCoursetoCoursePayment(CoursePayment coursepayment) {
		this.CoursetoCoursePayment.remove(coursepayment);
	}
	public List<CourseRecord> getCoursetoCourseRecord() {
		return CoursetoCourseRecord;
	}	
	
	public void addCoursetoCourseRecord(CourseRecord courserecord) {
		this.CoursetoCourseRecord.add(courserecord);
	}
	
	public void deleteCoursetoCourseRecord(CourseRecord courserecord) {
		this.CoursetoCourseRecord.remove(courserecord);
	}
	


}
