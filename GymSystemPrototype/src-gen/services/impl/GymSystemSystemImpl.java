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

public class GymSystemSystemImpl implements GymSystemSystem, Serializable {
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public GymSystemSystemImpl() {
		services = new ThirdPartyServicesImpl();
	}

	public void refresh() {
		Cancel_book_CourseService cancel_book_courseservice_service = (Cancel_book_CourseService) ServiceManager
				.getAllInstancesOf("Cancel_book_CourseService").get(0);
		Book_CourseService book_courseservice_service = (Book_CourseService) ServiceManager
				.getAllInstancesOf("Book_CourseService").get(0);
		LoginService loginservice_service = (LoginService) ServiceManager
				.getAllInstancesOf("LoginService").get(0);
		Register_memberService register_memberservice_service = (Register_memberService) ServiceManager
				.getAllInstancesOf("Register_memberService").get(0);
		Register_trainerService register_trainerservice_service = (Register_trainerService) ServiceManager
				.getAllInstancesOf("Register_trainerService").get(0);
		Create_CourseService create_courseservice_service = (Create_CourseService) ServiceManager
				.getAllInstancesOf("Create_CourseService").get(0);
		Update_scheduleService update_scheduleservice_service = (Update_scheduleService) ServiceManager
				.getAllInstancesOf("Update_scheduleService").get(0);
		Finish_CourseService finish_courseservice_service = (Finish_CourseService) ServiceManager
				.getAllInstancesOf("Finish_CourseService").get(0);
	}			
	
	/* Generate buiness logic according to functional requirement */
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
