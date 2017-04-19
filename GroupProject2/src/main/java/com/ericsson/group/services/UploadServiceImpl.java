package com.ericsson.group.services;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.ericsson.group.dao.UploadDAOService;
 import com.ericsson.group.utilities.EditExcel;
import com.ericsson.group.utilities.Validation;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class UploadServiceImpl implements UploadService {
	
	@Inject
	private UploadDAOService service;
	
	/* (non-Javadoc)
	 * @see com.ericsson.group.services.UploadServiceEJB#uploadData(java.lang.String)
	 */
	public void uploadData(String path) {
		//Validate file type
		if(path.endsWith("xls")) {
		
			// Edit excel, into deliminated files
			EditExcel ee = new EditExcel();
			ee.setPath(path);
			ArrayList<String> sheets = ee.deliminateFile();
			
			// Validate base data
			Validation validator = new Validation();
			
			// upload data
			if(sheets.contains("Base Data")) {
				service.uploadBase();
			}
			if(sheets.contains("Event-Cause Table")) {
				service.uploadEC();
			}
			if(sheets.contains("Failure Class Table")) {
				service.uploadFC();
			}
			if(sheets.contains("UE Table")) {
				service.uploadUE();
			}
			if(sheets.contains("MCC - MNC Table")) {
				service.uploadMCC();
			}
		
		}
		
	}
}
