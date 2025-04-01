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

public class Cancel_book_CourseServiceImpl implements Cancel_book_CourseService, Serializable {
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public Cancel_book_CourseServiceImpl() {
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
	public List<CourseRecord> listAllCourseBooked(String member_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
			List<CourseRecord> tempsre = new LinkedList<>();
			//no nested iterator --  iterator: select
			for (CourseRecord re : ((List<CourseRecord>)EntityManager.getAllInstancesOf("CourseRecord")))
			{
				if (re.getUserId().equals(member_id))
				{
					tempsre.add(re);
				} 
			}
			
			
			refresh();
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			refresh(); return tempsre;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [member_id] 
	}  
	
	 
	@SuppressWarnings("unchecked")
	public boolean confirmCancelBook(String course_id, String member_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
		//Get course
		Course course = null;
		//no nested iterator --  iterator: any previous:any
		for (Course c : (List<Course>)EntityManager.getAllInstancesOf("Course"))
		{
			if (c.getId().equals(course_id))
			{
				course = c;
				break;
			}
				
			
		}
		//Get record
		CourseRecord record = null;
		//no nested iterator --  iterator: any previous:any
		for (CourseRecord re : (List<CourseRecord>)EntityManager.getAllInstancesOf("CourseRecord"))
		{
			if (re.getUserId().equals(member_id) && re.getCourseId().equals(course_id))
			{
				record = re;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
 
		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(course) == false && StandardOPs.oclIsundefined(record) == false) 
		{ 
			/* Logic here */
			record.setStatus(RecordStatus.CANCEL);
			
			
			refresh();
			// post-condition checking
			if (!(record.getStatus() == RecordStatus.CANCEL
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
		//string parameters: [course_id, member_id] 
		//all relevant vars : record
		//all relevant entities : CourseRecord
	}  
	
	static {opINVRelatedEntity.put("confirmCancelBook", Arrays.asList("CourseRecord"));}
	 
	@SuppressWarnings("unchecked")
	public boolean requestRefund(String course_id, String member_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
		//Get payment
		CoursePayment payment = null;
		//no nested iterator --  iterator: any previous:any
		for (CoursePayment p : (List<CoursePayment>)EntityManager.getAllInstancesOf("CoursePayment"))
		{
			if (p.getUserId().equals(member_id) && p.getCourseId().equals(course_id))
			{
				payment = p;
				break;
			}
				
			
		}
		/* previous state in post-condition*/
 
		/* check precondition */
		if (StandardOPs.oclIsundefined(user) == false && StandardOPs.oclIsundefined(cls) == false && StandardOPs.oclIsundefined(payment) == false && payment.getPayStatus() == PayStatus.PAIED) 
		{ 
			/* Logic here */
			payment.setPayStatus(PayStatus.REFUND);
			
			
			refresh();
			// post-condition checking
			if (!(payment.getPayStatus() == PayStatus.REFUND
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
		//string parameters: [course_id, member_id] 
		//all relevant vars : payment
		//all relevant entities : CoursePayment
	}  
	
	static {opINVRelatedEntity.put("requestRefund", Arrays.asList("CoursePayment"));}
	 
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
