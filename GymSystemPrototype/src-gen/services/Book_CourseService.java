package services;

import entities.*;  
import java.util.List;
import java.time.LocalDate;


public interface Book_CourseService {

	/* all system operations of the use case*/
	List<Course> listAllCourseAvailable(String member_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean chooseOneBooking(String member_id, String course_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean payFee(String member_id, String course_id, String datetime) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	
	/* all get and set functions for temp property*/
	
	/* all get and set functions for temp property*/
	
	/* invariant checking function */
}
