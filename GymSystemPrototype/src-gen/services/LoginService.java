package services;

import entities.*;  
import java.util.List;
import java.time.LocalDate;


public interface LoginService {

	/* all system operations of the use case*/
	boolean login_trainer(String phone, String password) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean login_frontdesk(String phone, String password) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	boolean login_member(String phone, String password) throws PreconditionException, PostconditionException, ThirdPartyServiceException;
	
	/* all get and set functions for temp property*/
	
	/* all get and set functions for temp property*/
	
	/* invariant checking function */
}
