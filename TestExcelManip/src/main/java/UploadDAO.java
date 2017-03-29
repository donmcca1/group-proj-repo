import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.SQLQuery;



public class UploadDAO {

	private String path;
	
	public UploadDAO() {
	}
	
	public void setPath() {
		this.path = path;
	}
	
	public void uploadTxtFiles() {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("conygrePersistentUnit");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		String loadBaseData = "LOAD DATA LOCAL INFILE ? INTO TABLE base_data FIELDS TERMINATED BY ';' IGNORE 1 LINES (@date_time, event_id, @failure_class, ue_type, market,  operator, cell_id, duration, @cause_code, ne_version, imsi) SET failure_class=REPLACE(@failure_class,'(null)',-1), failure_class=NULLIF(failure_class,-1), cause_code=REPLACE(@cause_code,'(null)',-1), cause_code=NULLIF(cause_code,-1), `date_time`=STR_TO_DATE(@`date_time`,'%m/%d/%Y %H:%i')  ;";
		
		String loadEventCause = "LOAD DATA LOCAL INFILE ? INTO TABLE event_cause FIELDS TERMINATED BY ';' IGNORE 1 LINES (`cause_code`,`event_id`,@`description`) SET `description`=REPLACE(@`description`,'(null)',-1), `description`=NULLIF(`description`,-1);";

		String loadFailureClass = "LOAD DATA LOCAL INFILE ? INTO TABLE failure_class FIELDS TERMINATED BY ';' IGNORE 1 LINES (failure_class, description);";

		String loadMCCMNC = "LOAD DATA LOCAL INFILE ? INTO TABLE `mcc_mnc` FIELDS TERMINATED BY ';' IGNORE 1 LINES (mcc,mnc,country,operator);";

		String loadUE = "LOAD DATA LOCAL INFILE ? INTO TABLE ue FIELDS TERMINATED BY ';' IGNORE 1 LINES (tac,marketing_name,manufacturer,@access_capability, model,vendor_name,@ue_type,@OS,@input_mode) SET ue_type=REPLACE(@ue_type,'(null)',-1), ue_type=NULLIF(ue_type,-1), os=REPLACE(@os,'(null)',-1), os=NULLIF(os,-1), input_mode=REPLACE(@input_mode,'(null)',-1), input_mode=NULLIF(input_mode,-1);";
		
		
		String path = UploadDAO.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.substring(0, path.indexOf("target")) + "upload/";
		
		Query bd = em.createNativeQuery(loadBaseData);
		bd.setParameter(1, path + "base_data_validated.txt");
		//bd.setParameter(1, path + "base_data.txt");
		bd.executeUpdate();
		
		Query ec = em.createNativeQuery(loadEventCause);
		ec.setParameter(1, path + "event_cause.txt");
		ec.executeUpdate();
		
		Query fc = em.createNativeQuery(loadFailureClass);
		fc.setParameter(1, path + "failure_class.txt");
		fc.executeUpdate();
		
		Query m = em.createNativeQuery(loadMCCMNC);
		m.setParameter(1, path + "mcc_mnc.txt");
		m.executeUpdate();
		
		
		Query ue = em.createNativeQuery(loadUE);
		ue.setParameter(1, path + "ue.txt");
		ue.executeUpdate();
		
		
		tx.commit();
	}

	

}


