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

public class Register_trainerServiceImpl implements Register_trainerService, Serializable {
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public Register_trainerServiceImpl() {
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
	public boolean input_trainer_info(String id, String name, int age, String phone, String description, String datetime) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* previous state in post-condition*/
 
		/* check precondition */
		if (true) 
		{ 
			/* Logic here */
			Trainer newTrainer = null;
			newTrainer = (Trainer) EntityManager.createObject("Trainer");
			newTrainer.setId(id);
			newTrainer.setName(name);
			newTrainer.setPhone(phone);
			newTrainer.setDescription(description);
			newTrainer.setPassword("default123");
			newTrainer.setRegisterTime(datetime);
			EntityManager.addObject("Trainer", newTrainer);
			
			
			refresh();
			// post-condition checking
			if (!(true && 
			newTrainer.getId() == id
			 && 
			newTrainer.getName() == name
			 && 
			newTrainer.getPhone() == phone
			 && 
			newTrainer.getDescription() == description
			 && 
			newTrainer.getPassword().equals("default123")
			 && 
			newTrainer.getRegisterTime().equals(datetime)
			 && 
			StandardOPs.includes(((List<Trainer>)EntityManager.getAllInstancesOf("Trainer")), newTrainer)
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
		//string parameters: [id, name, phone, description, datetime] 
		//all relevant vars : newTrainer
		//all relevant entities : Trainer
	}  
	
	static {opINVRelatedEntity.put("input_trainer_info", Arrays.asList("Trainer"));}
	 
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
