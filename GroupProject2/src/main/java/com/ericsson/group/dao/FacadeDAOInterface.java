package com.ericsson.group.dao;

import com.ericsson.group.businessEntities.RequestAdapter;
//CLASS TO BE INJECTED INTO FACADESERVICEINTERFACE
public interface FacadeDAOInterface {

	Object processRequest(RequestAdapter reqAdap);

}