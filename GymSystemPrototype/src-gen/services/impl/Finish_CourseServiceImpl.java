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

public class Finish_CourseServiceImpl implements Finish_CourseService, Serializable {
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public Finish_CourseServiceImpl() {
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
	public List<Course> listAllCourseByTrainer(String trainer_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
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
			List<Course> tempsc = new LinkedList<>();
			//no nested iterator --  iterator: select
			for (Course c : ((List<Course>)EntityManager.getAllInstancesOf("Course")))
			{
				if (c.getTrainerId().equals(trainer_id))
				{
					tempsc.add(c);
				} 
			}
			
			
			refresh();
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			refresh(); return tempsc;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [trainer_id] 
	}  
	
	 
	@SuppressWarnings("unchecked")
	public boolean confirmCourseFinish(String trainer_id, String course_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
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
		if (StandardOPs.oclIsundefined(cls) == false && cls.getTrainerId().equals(trainer_id)) 
		{ 
			/* Logic here */
			cls.setStatus(CourseStatus.COMPLETED);
			
			
			refresh();
			// post-condition checking
			if (!(cls.getStatus() == CourseStatus.COMPLETED
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
		//string parameters: [trainer_id, course_id] 
		//all relevant vars : cls
		//all relevant entities : Course
	}  
	
	static {opINVRelatedEntity.put("confirmCourseFinish", Arrays.asList("Course"));}
	 
	@SuppressWarnings("unchecked")
	public boolean requestBonus(String trainer_id, String course_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
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
		if (StandardOPs.oclIsundefined(cls) == false && StandardOPs.oclIsundefined(trainer) == false && cls.getTrainerId().equals(trainer_id)) 
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
		//string parameters: [trainer_id, course_id] 
	}  
	
	 
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
