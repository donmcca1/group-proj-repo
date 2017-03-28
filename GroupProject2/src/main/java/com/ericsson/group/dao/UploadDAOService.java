package com.ericsson.group.dao;

import javax.ejb.Local;

@Local
public interface UploadDAOService {

	void uploadBase();
	void uploadEC();
	void uploadFC();
	void uploadMCC();
	void uploadUE();
}