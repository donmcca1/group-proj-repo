package com.ericsson.group.services;

//HEY IS THIS PUSH WORKING??

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.ericsson.group.businessEntities.RequestAdapter;
import com.ericsson.group.dao.FacadeDAOInterface;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)// Ensures Transactions are used for ACID purposes
public class FacadeService implements FacadeServiceInterface {
	@EJB
	private FacadeDAOInterface dao;
	
	
	/* (non-Javadoc)
	 * @see com.ericsson.group.services.FacadeServiceInterface#processRequest(java.lang.String)
	 */
	public Object processRequest(String request) {
		String[] params = request.split(":");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		RequestAdapter reqAdap = null;
		
		if(params[0].equals("GetAllBaseDataCommand")) {
			reqAdap = new GetAllBaseDataRequest(params);
		} else if(params[0].equals("GetBaseDataByImsiCommand")) {
			reqAdap = new GetBaseDataByImsiRequest(params);
		} else if(params[0].equals("GetBaseDataByDateCommand")) {
			reqAdap = new GetBaseDataByDateRequest(params);
		}
		
		return dao.processRequest(reqAdap);
		
		
	}
}
