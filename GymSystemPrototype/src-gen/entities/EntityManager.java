package entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.lang.reflect.Method;
import java.util.Map;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.File;

public class EntityManager {

	private static Map<String, List> AllInstance = new HashMap<String, List>();
	
	private static List<Course> CourseInstances = new LinkedList<Course>();
	private static List<Member> MemberInstances = new LinkedList<Member>();
	private static List<Trainer> TrainerInstances = new LinkedList<Trainer>();
	private static List<FrontDesk> FrontDeskInstances = new LinkedList<FrontDesk>();
	private static List<CourseRoom> CourseRoomInstances = new LinkedList<CourseRoom>();
	private static List<CourseNotification> CourseNotificationInstances = new LinkedList<CourseNotification>();
	private static List<CourseRecord> CourseRecordInstances = new LinkedList<CourseRecord>();
	private static List<CoursePayment> CoursePaymentInstances = new LinkedList<CoursePayment>();

	
	/* Put instances list into Map */
	static {
		AllInstance.put("Course", CourseInstances);
		AllInstance.put("Member", MemberInstances);
		AllInstance.put("Trainer", TrainerInstances);
		AllInstance.put("FrontDesk", FrontDeskInstances);
		AllInstance.put("CourseRoom", CourseRoomInstances);
		AllInstance.put("CourseNotification", CourseNotificationInstances);
		AllInstance.put("CourseRecord", CourseRecordInstances);
		AllInstance.put("CoursePayment", CoursePaymentInstances);
	} 
		
	/* Save State */
	public static void save(File file) {
		
		try {
			
			ObjectOutputStream stateSave = new ObjectOutputStream(new FileOutputStream(file));
			
			stateSave.writeObject(CourseInstances);
			stateSave.writeObject(MemberInstances);
			stateSave.writeObject(TrainerInstances);
			stateSave.writeObject(FrontDeskInstances);
			stateSave.writeObject(CourseRoomInstances);
			stateSave.writeObject(CourseNotificationInstances);
			stateSave.writeObject(CourseRecordInstances);
			stateSave.writeObject(CoursePaymentInstances);
			
			stateSave.close();
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* Load State */
	public static void load(File file) {
		
		try {
			
			ObjectInputStream stateLoad = new ObjectInputStream(new FileInputStream(file));
			
			try {
				
				CourseInstances =  (List<Course>) stateLoad.readObject();
				AllInstance.put("Course", CourseInstances);
				MemberInstances =  (List<Member>) stateLoad.readObject();
				AllInstance.put("Member", MemberInstances);
				TrainerInstances =  (List<Trainer>) stateLoad.readObject();
				AllInstance.put("Trainer", TrainerInstances);
				FrontDeskInstances =  (List<FrontDesk>) stateLoad.readObject();
				AllInstance.put("FrontDesk", FrontDeskInstances);
				CourseRoomInstances =  (List<CourseRoom>) stateLoad.readObject();
				AllInstance.put("CourseRoom", CourseRoomInstances);
				CourseNotificationInstances =  (List<CourseNotification>) stateLoad.readObject();
				AllInstance.put("CourseNotification", CourseNotificationInstances);
				CourseRecordInstances =  (List<CourseRecord>) stateLoad.readObject();
				AllInstance.put("CourseRecord", CourseRecordInstances);
				CoursePaymentInstances =  (List<CoursePayment>) stateLoad.readObject();
				AllInstance.put("CoursePayment", CoursePaymentInstances);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	/* create object */  
	public static Object createObject(String Classifer) {
		try
		{
			Class c = Class.forName("entities.EntityManager");
			Method createObjectMethod = c.getDeclaredMethod("create" + Classifer + "Object");
			return createObjectMethod.invoke(c);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/* add object */  
	public static Object addObject(String Classifer, Object ob) {
		try
		{
			Class c = Class.forName("entities.EntityManager");
			Method addObjectMethod = c.getDeclaredMethod("add" + Classifer + "Object", Class.forName("entities." + Classifer));
			return  (boolean) addObjectMethod.invoke(c, ob);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}	
	
	/* add objects */  
	public static Object addObjects(String Classifer, List obs) {
		try
		{
			Class c = Class.forName("entities.EntityManager");
			Method addObjectsMethod = c.getDeclaredMethod("add" + Classifer + "Objects", Class.forName("java.util.List"));
			return  (boolean) addObjectsMethod.invoke(c, obs);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/* Release object */
	public static boolean deleteObject(String Classifer, Object ob) {
		try
		{
			Class c = Class.forName("entities.EntityManager");
			Method deleteObjectMethod = c.getDeclaredMethod("delete" + Classifer + "Object", Class.forName("entities." + Classifer));
			return  (boolean) deleteObjectMethod.invoke(c, ob);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/* Release objects */
	public static boolean deleteObjects(String Classifer, List obs) {
		try
		{
			Class c = Class.forName("entities.EntityManager");
			Method deleteObjectMethod = c.getDeclaredMethod("delete" + Classifer + "Objects", Class.forName("java.util.List"));
			return  (boolean) deleteObjectMethod.invoke(c, obs);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}		 	
	
	 /* Get all objects belongs to same class */
	public static List getAllInstancesOf(String ClassName) {
			 return AllInstance.get(ClassName);
	}	

   /* Sub-create object */
	public static Course createCourseObject() {
		Course o = new Course();
		return o;
	}
	
	public static boolean addCourseObject(Course o) {
		return CourseInstances.add(o);
	}
	
	public static boolean addCourseObjects(List<Course> os) {
		return CourseInstances.addAll(os);
	}
	
	public static boolean deleteCourseObject(Course o) {
		return CourseInstances.remove(o);
	}
	
	public static boolean deleteCourseObjects(List<Course> os) {
		return CourseInstances.removeAll(os);
	}
	public static Member createMemberObject() {
		Member o = new Member();
		return o;
	}
	
	public static boolean addMemberObject(Member o) {
		return MemberInstances.add(o);
	}
	
	public static boolean addMemberObjects(List<Member> os) {
		return MemberInstances.addAll(os);
	}
	
	public static boolean deleteMemberObject(Member o) {
		return MemberInstances.remove(o);
	}
	
	public static boolean deleteMemberObjects(List<Member> os) {
		return MemberInstances.removeAll(os);
	}
	public static Trainer createTrainerObject() {
		Trainer o = new Trainer();
		return o;
	}
	
	public static boolean addTrainerObject(Trainer o) {
		return TrainerInstances.add(o);
	}
	
	public static boolean addTrainerObjects(List<Trainer> os) {
		return TrainerInstances.addAll(os);
	}
	
	public static boolean deleteTrainerObject(Trainer o) {
		return TrainerInstances.remove(o);
	}
	
	public static boolean deleteTrainerObjects(List<Trainer> os) {
		return TrainerInstances.removeAll(os);
	}
	public static FrontDesk createFrontDeskObject() {
		FrontDesk o = new FrontDesk();
		return o;
	}
	
	public static boolean addFrontDeskObject(FrontDesk o) {
		return FrontDeskInstances.add(o);
	}
	
	public static boolean addFrontDeskObjects(List<FrontDesk> os) {
		return FrontDeskInstances.addAll(os);
	}
	
	public static boolean deleteFrontDeskObject(FrontDesk o) {
		return FrontDeskInstances.remove(o);
	}
	
	public static boolean deleteFrontDeskObjects(List<FrontDesk> os) {
		return FrontDeskInstances.removeAll(os);
	}
	public static CourseRoom createCourseRoomObject() {
		CourseRoom o = new CourseRoom();
		return o;
	}
	
	public static boolean addCourseRoomObject(CourseRoom o) {
		return CourseRoomInstances.add(o);
	}
	
	public static boolean addCourseRoomObjects(List<CourseRoom> os) {
		return CourseRoomInstances.addAll(os);
	}
	
	public static boolean deleteCourseRoomObject(CourseRoom o) {
		return CourseRoomInstances.remove(o);
	}
	
	public static boolean deleteCourseRoomObjects(List<CourseRoom> os) {
		return CourseRoomInstances.removeAll(os);
	}
	public static CourseNotification createCourseNotificationObject() {
		CourseNotification o = new CourseNotification();
		return o;
	}
	
	public static boolean addCourseNotificationObject(CourseNotification o) {
		return CourseNotificationInstances.add(o);
	}
	
	public static boolean addCourseNotificationObjects(List<CourseNotification> os) {
		return CourseNotificationInstances.addAll(os);
	}
	
	public static boolean deleteCourseNotificationObject(CourseNotification o) {
		return CourseNotificationInstances.remove(o);
	}
	
	public static boolean deleteCourseNotificationObjects(List<CourseNotification> os) {
		return CourseNotificationInstances.removeAll(os);
	}
	public static CourseRecord createCourseRecordObject() {
		CourseRecord o = new CourseRecord();
		return o;
	}
	
	public static boolean addCourseRecordObject(CourseRecord o) {
		return CourseRecordInstances.add(o);
	}
	
	public static boolean addCourseRecordObjects(List<CourseRecord> os) {
		return CourseRecordInstances.addAll(os);
	}
	
	public static boolean deleteCourseRecordObject(CourseRecord o) {
		return CourseRecordInstances.remove(o);
	}
	
	public static boolean deleteCourseRecordObjects(List<CourseRecord> os) {
		return CourseRecordInstances.removeAll(os);
	}
	public static CoursePayment createCoursePaymentObject() {
		CoursePayment o = new CoursePayment();
		return o;
	}
	
	public static boolean addCoursePaymentObject(CoursePayment o) {
		return CoursePaymentInstances.add(o);
	}
	
	public static boolean addCoursePaymentObjects(List<CoursePayment> os) {
		return CoursePaymentInstances.addAll(os);
	}
	
	public static boolean deleteCoursePaymentObject(CoursePayment o) {
		return CoursePaymentInstances.remove(o);
	}
	
	public static boolean deleteCoursePaymentObjects(List<CoursePayment> os) {
		return CoursePaymentInstances.removeAll(os);
	}
  
}

