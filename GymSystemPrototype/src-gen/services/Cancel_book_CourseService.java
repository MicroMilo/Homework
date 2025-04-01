package services;

import entities.*;  
import java.util.List;
import java.time.LocalDate;


public interface Cancel_book_CourseService {

	/* all system operations of the use case*/
	List<CourseRecord> listAllCourseBooked(String member_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean confirmCancelBook(String course_id, String member_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean requestRefund(String course_id, String member_id) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	
	/* all get and set functions for temp property*/
	
	/* all get and set functions for temp property*/
	
	/* invariant checking function */
}
