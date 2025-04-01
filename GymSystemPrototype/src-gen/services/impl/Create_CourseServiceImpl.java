package services.impl;

import services.*;
import entities.*;
import java.util.List;
import java.util.LinkedList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.Arrays;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import org.apache.commons.lang3.SerializationUtils;
import java.util.Iterator;

public class Create_CourseServiceImpl implements Create_CourseService, Serializable {
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public Create_CourseServiceImpl() {
		services = new ThirdPartyServicesImpl();
	}

	
	//Shared variable from system services
	
	/* Shared variable from system services and get()/set() methods */
			
	/* all get and set functions for temp property*/
				
	
	
	/* Generate inject for sharing temp variables between use cases in system service */
	public void refresh() {
		GymSystemSystem gymsystemsystem_service = (GymSystemSystem) ServiceManager.getAllInstancesOf("GymSystemSystem").get(0);
	}
	
	/* Generate buiness logic according to functional requirement */
	@SuppressWarnings("unchecked")
	public boolean requestCreateCourse(String trainer_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get trainer
		Trainer trainer = null;
		//no nested iterator --  iterator: any previous:any
		for (Trainer t : (List<Trainer>)EntityManager.getAllInstancesOf("Trainer"))
		{
			if (t.getId().equals(trainer_id))
			{
				trainer = t;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
 
		/* check precondition */
		if (StandardOPs.oclIsundefined(trainer) == false) 
		{ 
			/* Logic here */
			
			
			refresh();
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [trainer_id] 
	}  
	
	 
	@SuppressWarnings("unchecked")
	public boolean createRoom(String room_id, String location, int capacity) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* previous state in post-condition*/
 
		/* check precondition */
		if (true) 
		{ 
			/* Logic here */
			CourseRoom newRoom = null;
			newRoom = (CourseRoom) EntityManager.createObject("CourseRoom");
			newRoom.setId(room_id);
			newRoom.setLocation(location);
			newRoom.setCapacity(capacity);
			EntityManager.addObject("CourseRoom", newRoom);
			
			
			refresh();
			// post-condition checking
			if (!(true && 
			newRoom.getId() == room_id
			 && 
			newRoom.getLocation() == location
			 && 
			newRoom.getCapacity() == capacity
			 && 
			StandardOPs.includes(((List<CourseRoom>)EntityManager.getAllInstancesOf("CourseRoom")), newRoom)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [room_id, location] 
		//all relevant vars : newRoom
		//all relevant entities : CourseRoom
	}  
	
	static {opINVRelatedEntity.put("createRoom", Arrays.asList("CourseRoom"));}
	 
	@SuppressWarnings("unchecked")
	public boolean confirmCourseInfo(String room_id, String course_id, String register_time, String register_end_time, String begin_time, String end_time, String course_name, String description, String trainer_id, float cost) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get trainer
		Trainer trainer = null;
		//no nested iterator --  iterator: any previous:any
		for (Trainer t : (List<Trainer>)EntityManager.getAllInstancesOf("Trainer"))
		{
			if (t.getId().equals(trainer_id))
			{
				trainer = t;
				break;
			}
				
			
		}
		//Get room
		CourseRoom room = null;
		//no nested iterator --  iterator: any previous:any
		for (CourseRoom t : (List<CourseRoom>)EntityManager.getAllInstancesOf("CourseRoom"))
		{
			if (t.getId().equals(room_id))
			{
				room = t;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
 
		/* check precondition */
		if (StandardOPs.oclIsundefined(trainer) == false && StandardOPs.oclIsundefined(room) == false) 
		{ 
			/* Logic here */
			Course newCourse = null;
			newCourse = (Course) EntityManager.createObject("Course");
			newCourse.setRegisterStartTime(register_time);
			newCourse.setRegisterEndTime(register_end_time);
			newCourse.setEventStartTime(begin_time);
			newCourse.setEventEndTime(end_time);
			newCourse.setId(course_id);
			newCourse.setName(course_name);
			newCourse.setCost(cost);
			newCourse.setRoomId(room_id);
			newCourse.setDescription(description);
			newCourse.setTrainerId(trainer_id);
			newCourse.setStatus(CourseStatus.UNSTARTED);
			EntityManager.addObject("Course", newCourse);
			
			
			refresh();
			// post-condition checking
			if (!(true && 
			newCourse.getRegisterStartTime() == register_time
			 && 
			newCourse.getRegisterEndTime() == register_end_time
			 && 
			newCourse.getEventStartTime() == begin_time
			 && 
			newCourse.getEventEndTime() == end_time
			 && 
			newCourse.getId() == course_id
			 && 
			newCourse.getName() == course_name
			 && 
			newCourse.getCost() == cost
			 && 
			newCourse.getRoomId() == room_id
			 && 
			newCourse.getDescription() == description
			 && 
			newCourse.getTrainerId() == trainer_id
			 && 
			newCourse.getStatus() == CourseStatus.UNSTARTED
			 && 
			StandardOPs.includes(((List<Course>)EntityManager.getAllInstancesOf("Course")), newCourse)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [room_id, course_id, register_time, register_end_time, begin_time, end_time, course_name, description, trainer_id] 
		//all relevant vars : newCourse
		//all relevant entities : Course
	}  
	
	static {opINVRelatedEntity.put("confirmCourseInfo", Arrays.asList("Course"));}
	 
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
