package com.ericsson.group.services;

import javax.ejb.Local;
import javax.ejb.Stateless;

// CLASS TO BE INJECTED INTO BaseCRUDService

@Local
public interface FacadeServiceInterface {

	Object processRequest(String request);

}