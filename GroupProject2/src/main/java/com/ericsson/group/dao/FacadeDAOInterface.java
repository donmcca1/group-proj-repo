package com.ericsson.group.dao;

import com.ericsson.group.businessEntities.RequestAdapter;

import javax.ejb.Local;
import javax.ejb.Stateless;

//CLASS TO BE INJECTED INTO FACADESERVICEINTERFACE

@Local
public interface FacadeDAOInterface {

	Object processRequest(RequestAdapter reqAdap);

}