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

public class Book_CourseServiceImpl implements Book_CourseService, Serializable {
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public Book_CourseServiceImpl() {
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
	public List<Course> listAllCourseAvailable(String member_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get user
		Member user = null;
		//no nested iterator --  iterator: any previous:any
		for (Member m : (List<Member>)EntityManager.getAllInstancesOf("Member"))
		{
			if (m.getId().equals(member_id))
			{
				user = m;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
 
		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false) 
		{ 
			/* Logic here */
			
			
			refresh();
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			refresh(); return ((List<Course>)EntityManager.getAllInstancesOf("Course"));
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [member_id] 
	}  
	
	 
	@SuppressWarnings("unchecked")
	public boolean chooseOneBooking(String member_id, String course_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get user
		Member user = null;
		//no nested iterator --  iterator: any previous:any
		for (Member m : (List<Member>)EntityManager.getAllInstancesOf("Member"))
		{
			if (m.getId().equals(member_id))
			{
				user = m;
				break;
			}
				
			
		}
		//Get cls
		Course cls = null;
		//no nested iterator --  iterator: any previous:any
		for (Course c : (List<Course>)EntityManager.getAllInstancesOf("Course"))
		{
			if (c.getId().equals(course_id))
			{
				cls = c;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
 
		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(cls) == false) 
		{ 
			/* Logic here */
			CourseRecord record = null;
			record = (CourseRecord) EntityManager.createObject("CourseRecord");
			record.setUserId(member_id);
			record.setCourseId(course_id);
			record.setStatus(RecordStatus.NORMAL);
			EntityManager.addObject("CourseRecord", record);
			
			
			refresh();
			// post-condition checking
			if (!(true && 
			record.getUserId() == member_id
			 && 
			record.getCourseId() == course_id
			 && 
			record.getStatus() == RecordStatus.NORMAL
			 && 
			StandardOPs.includes(((List<CourseRecord>)EntityManager.getAllInstancesOf("CourseRecord")), record)
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
		//string parameters: [member_id, course_id] 
		//all relevant vars : record
		//all relevant entities : CourseRecord
	}  
	
	static {opINVRelatedEntity.put("chooseOneBooking", Arrays.asList("CourseRecord"));}
	 
	@SuppressWarnings("unchecked")
	public boolean payFee(String member_id, String course_id, String datetime) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get user
		Member user = null;
		//no nested iterator --  iterator: any previous:any
		for (Member m : (List<Member>)EntityManager.getAllInstancesOf("Member"))
		{
			if (m.getId().equals(member_id))
			{
				user = m;
				break;
			}
				
			
		}
		//Get cls
		Course cls = null;
		//no nested iterator --  iterator: any previous:any
		for (Course c : (List<Course>)EntityManager.getAllInstancesOf("Course"))
		{
			if (c.getId().equals(course_id))
			{
				cls = c;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
 
		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(cls) == false) 
		{ 
			/* Logic here */
			CoursePayment payment = null;
			payment = (CoursePayment) EntityManager.createObject("CoursePayment");
			payment.setUserId(member_id);
			payment.setCourseId(course_id);
			payment.setPrice(cls.getCost());
			payment.setPayTime(datetime);
			payment.setPayStatus(PayStatus.PAIED);
			EntityManager.addObject("CoursePayment", payment);
			
			
			refresh();
			// post-condition checking
			if (!(true && 
			payment.getUserId() == member_id
			 && 
			payment.getCourseId() == course_id
			 && 
			payment.getPrice() == cls.getCost()
			 && 
			payment.getPayTime().equals(datetime)
			 && 
			payment.getPayStatus() == PayStatus.PAIED
			 && 
			StandardOPs.includes(((List<CoursePayment>)EntityManager.getAllInstancesOf("CoursePayment")), payment)
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
		//string parameters: [member_id, course_id, datetime] 
		//all relevant vars : payment
		//all relevant entities : CoursePayment
	}  
	
	static {opINVRelatedEntity.put("payFee", Arrays.asList("CoursePayment"));}
	 
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
