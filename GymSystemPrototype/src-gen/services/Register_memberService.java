package services;

import entities.*;  
import java.util.List;
import java.time.LocalDate;


public interface Register_memberService {

	/* all system operations of the use case*/
	boolean input_member_info(String id, String name, int age, String phone, String description, String datetime) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	
	/* all get and set functions for temp property*/
	
	/* all get and set functions for temp property*/
	
	/* invariant checking function */
}
