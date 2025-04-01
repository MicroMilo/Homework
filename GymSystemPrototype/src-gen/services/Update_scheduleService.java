package services;

import entities.*;  
import java.util.List;
import java.time.LocalDate;


public interface Update_scheduleService {

	/* all system operations of the use case*/
	boolean listCourseByTrainer(String trainer_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean chooseSpecificCourse(String trainer_id, String course_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean confirmUpdateInfo(String course_id, String course_name, String begin_time, String end_time, String description, String trainer_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	
	/* all get and set functions for temp property*/
	
	/* all get and set functions for temp property*/
	
	/* invariant checking function */
}
