package com.ericsson.group.dao;

import java.sql.Date;

import com.ericsson.group.businessEntities.RequestAdapter;
import com.ericsson.group.services.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
@Stateless
@Local
public class FacadeDAO implements FacadeDAOInterface {
	JPABaseDAO baseDao = new JPABaseDAO();
	
	/* (non-Javadoc)
	 * @see com.ericsson.group.dao.FacadeDAOInterface#processRequest(com.ericsson.group.services.RequestAdapter)
	 */
	public Object processRequest(RequestAdapter reqAdap) {
		if(reqAdap.getType().equals("GetAllBaseDataRequest")) {
			return baseDao.getBaseData();
		} else if(reqAdap.getType().equals("GetBaseDataByDateRequest")) {
			Date date = ((GetBaseDataByDateRequest) reqAdap).getDate();
			return baseDao.getBaseDataByDate(date);
		} else if (reqAdap.getType().equals("GetBaseDataByImsiRequest")) {
			Long imsi = ((GetBaseDataByImsiRequest) reqAdap).getImsi();
			return baseDao.getBaseDataByImsi(imsi);
		}
		return null;
	}
}
