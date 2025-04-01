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

public class Register_memberServiceImpl implements Register_memberService, Serializable {
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public Register_memberServiceImpl() {
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
	public boolean input_member_info(String id, String name, int age, String phone, String description, String datetime) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* previous state in post-condition*/
 
		/* check precondition */
		if (true) 
		{ 
			/* Logic here */
			Member mem = null;
			mem = (Member) EntityManager.createObject("Member");
			mem.setId(id);
			mem.setName(name);
			mem.setPhone(phone);
			mem.setDescription(description);
			mem.setPassword("default123");
			mem.setRegisterTime(datetime);
			EntityManager.addObject("Member", mem);
			
			
			refresh();
			// post-condition checking
			if (!(true && 
			mem.getId() == id
			 && 
			mem.getName() == name
			 && 
			mem.getPhone() == phone
			 && 
			mem.getDescription() == description
			 && 
			mem.getPassword().equals("default123")
			 && 
			mem.getRegisterTime().equals(datetime)
			 && 
			StandardOPs.includes(((List<Member>)EntityManager.getAllInstancesOf("Member")), mem)
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
		//all relevant vars : mem
		//all relevant entities : Member
	}  
	
	static {opINVRelatedEntity.put("input_member_info", Arrays.asList("Member"));}
	 
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
