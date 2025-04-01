package services.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import services.*;
	
public class ServiceManager {
	
	private static Map<String, List> AllServiceInstance = new HashMap<String, List>();
	
	private static List<GymSystemSystem> GymSystemSystemInstances = new LinkedList<GymSystemSystem>();
	private static List<ThirdPartyServices> ThirdPartyServicesInstances = new LinkedList<ThirdPartyServices>();
	private static List<Cancel_book_CourseService> Cancel_book_CourseServiceInstances = new LinkedList<Cancel_book_CourseService>();
	private static List<Book_CourseService> Book_CourseServiceInstances = new LinkedList<Book_CourseService>();
	private static List<LoginService> LoginServiceInstances = new LinkedList<LoginService>();
	private static List<Register_memberService> Register_memberServiceInstances = new LinkedList<Register_memberService>();
	private static List<Register_trainerService> Register_trainerServiceInstances = new LinkedList<Register_trainerService>();
	private static List<Create_CourseService> Create_CourseServiceInstances = new LinkedList<Create_CourseService>();
	private static List<Update_scheduleService> Update_scheduleServiceInstances = new LinkedList<Update_scheduleService>();
	private static List<Finish_CourseService> Finish_CourseServiceInstances = new LinkedList<Finish_CourseService>();
	
	static {
		AllServiceInstance.put("GymSystemSystem", GymSystemSystemInstances);
		AllServiceInstance.put("ThirdPartyServices", ThirdPartyServicesInstances);
		AllServiceInstance.put("Cancel_book_CourseService", Cancel_book_CourseServiceInstances);
		AllServiceInstance.put("Book_CourseService", Book_CourseServiceInstances);
		AllServiceInstance.put("LoginService", LoginServiceInstances);
		AllServiceInstance.put("Register_memberService", Register_memberServiceInstances);
		AllServiceInstance.put("Register_trainerService", Register_trainerServiceInstances);
		AllServiceInstance.put("Create_CourseService", Create_CourseServiceInstances);
		AllServiceInstance.put("Update_scheduleService", Update_scheduleServiceInstances);
		AllServiceInstance.put("Finish_CourseService", Finish_CourseServiceInstances);
	} 
	
	public static List getAllInstancesOf(String ClassName) {
			 return AllServiceInstance.get(ClassName);
	}	
	
	public static GymSystemSystem createGymSystemSystem() {
		GymSystemSystem s = new GymSystemSystemImpl();
		GymSystemSystemInstances.add(s);
		return s;
	}
	public static ThirdPartyServices createThirdPartyServices() {
		ThirdPartyServices s = new ThirdPartyServicesImpl();
		ThirdPartyServicesInstances.add(s);
		return s;
	}
	public static Cancel_book_CourseService createCancel_book_CourseService() {
		Cancel_book_CourseService s = new Cancel_book_CourseServiceImpl();
		Cancel_book_CourseServiceInstances.add(s);
		return s;
	}
	public static Book_CourseService createBook_CourseService() {
		Book_CourseService s = new Book_CourseServiceImpl();
		Book_CourseServiceInstances.add(s);
		return s;
	}
	public static LoginService createLoginService() {
		LoginService s = new LoginServiceImpl();
		LoginServiceInstances.add(s);
		return s;
	}
	public static Register_memberService createRegister_memberService() {
		Register_memberService s = new Register_memberServiceImpl();
		Register_memberServiceInstances.add(s);
		return s;
	}
	public static Register_trainerService createRegister_trainerService() {
		Register_trainerService s = new Register_trainerServiceImpl();
		Register_trainerServiceInstances.add(s);
		return s;
	}
	public static Create_CourseService createCreate_CourseService() {
		Create_CourseService s = new Create_CourseServiceImpl();
		Create_CourseServiceInstances.add(s);
		return s;
	}
	public static Update_scheduleService createUpdate_scheduleService() {
		Update_scheduleService s = new Update_scheduleServiceImpl();
		Update_scheduleServiceInstances.add(s);
		return s;
	}
	public static Finish_CourseService createFinish_CourseService() {
		Finish_CourseService s = new Finish_CourseServiceImpl();
		Finish_CourseServiceInstances.add(s);
		return s;
	}
}	
