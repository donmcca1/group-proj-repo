package com.ericsson.group.dao;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class UploadDAO implements UploadDAOService {

	@PersistenceContext
	private EntityManager em;
	
	private String path;
	
	public UploadDAO() {
		this.path = UploadDAO.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.substring(0, path.indexOf("classes")) + "upload/";
	}
	
	/* (non-Javadoc)
	 * @see com.ericsson.group.dao.UploadDAOService#uploadBase()
	 */
	@Override
	public void uploadBase() {
		String loadBaseData = "LOAD DATA LOCAL INFILE ? INTO TABLE base_data FIELDS TERMINATED BY ';' "
				+ "IGNORE 1 LINES (@date_time, event_id, @failure_class, ue_type, market,  operator, cell_id, duration, @cause_code, ne_version, imsi) "
				+ "SET failure_class=REPLACE(@failure_class,'(null)',-1), failure_class=NULLIF(failure_class,-1), "
				+ "cause_code=REPLACE(@cause_code,'(null)',-1), cause_code=NULLIF(cause_code,-1), `date_time`=STR_TO_DATE(@`date_time`,'%m/%d/%Y %H:%i')  ;";
		
		Query bd = em.createNativeQuery(loadBaseData);
		bd.setParameter(1, path + "base_data_validated.txt");
		bd.executeUpdate();
	}
	
	public void uploadEC() {
		String loadEventCauseData = "LOAD DATA LOCAL INFILE ? INTO TABLE event_cause FIELDS TERMINATED BY ';' IGNORE 1 LINES (`cause_code`,`event_id`,@`description`) SET `description`=REPLACE(@`description`,'(null)',-1), `description`=NULLIF(`description`,-1);";
		
		Query ec = em.createNativeQuery(loadEventCauseData);
		ec.setParameter(1, path + "event_cause.txt");
		ec.executeUpdate();
	}
	
	public void uploadFC() {
		String loadfailureClassData = "LOAD DATA LOCAL INFILE ? INTO TABLE failure_class FIELDS TERMINATED BY ';' IGNORE 1 LINES (failure_class, description);";
		
		Query fc = em.createNativeQuery(loadfailureClassData);
		fc.setParameter(1, path + "failure_class.txt");
		fc.executeUpdate();
	}
	
	public void uploadMCC() {
		String loadMCCData = "LOAD DATA LOCAL INFILE ? INTO TABLE `mcc_mnc` FIELDS TERMINATED BY ';' IGNORE 1 LINES (mcc,mnc,country,operator);";
		
		Query mc = em.createNativeQuery(loadMCCData);
		mc.setParameter(1, path + "mcc_mnc.txt");
		mc.executeUpdate();
	}
	
	public void uploadUE() {
		String loadUeData = "LOAD DATA LOCAL INFILE ? INTO TABLE ue FIELDS TERMINATED BY ';' IGNORE 1 LINES (tac,marketing_name,manufacturer,@access_capability, model,vendor_name,@ue_type,@OS,@input_mode) SET ue_type=REPLACE(@ue_type,'(null)',-1), ue_type=NULLIF(ue_type,-1), os=REPLACE(@os,'(null)',-1), os=NULLIF(os,-1), input_mode=REPLACE(@input_mode,'(null)',-1), input_mode=NULLIF(input_mode,-1);";
		
		Query bd = em.createNativeQuery(loadUeData);
		bd.setParameter(1, path + "ue.txt");
		bd.executeUpdate();
	}
	
}
