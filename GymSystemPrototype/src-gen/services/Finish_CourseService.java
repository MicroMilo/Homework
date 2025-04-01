package services;

import entities.*;  
import java.util.List;
import java.time.LocalDate;


public interface Finish_CourseService {

	/* all system operations of the use case*/
	List<Course> listAllCourseByTrainer(String trainer_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean confirmCourseFinish(String trainer_id, String course_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean requestBonus(String trainer_id, String course_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	
	/* all get and set functions for temp property*/
	
	/* all get and set functions for temp property*/
	
	/* invariant checking function */
}
