package services;

import entities.*;  
import java.util.List;
import java.time.LocalDate;


public interface Create_CourseService {

	/* all system operations of the use case*/
	boolean requestCreateCourse(String trainer_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean createRoom(String room_id, String location, int capacity) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean confirmCourseInfo(String room_id, String course_id, String register_time, String register_end_time, String begin_time, String end_time, String course_name, String description, String trainer_id, float cost) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	
	/* all get and set functions for temp property*/
	
	/* all get and set functions for temp property*/
	
	/* invariant checking function */
}
