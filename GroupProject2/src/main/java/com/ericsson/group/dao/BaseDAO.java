package com.ericsson.group.dao;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.group.entities.BaseData;

@Local
public interface BaseDAO {
	//--- SELECT ALL ---//
	public Collection<?> getBaseData();
	
	//--- SELECT BY IMSI ---//
	public Collection<BaseData> getBaseDataByImsi(Long imsi);
	
	//--- SELECT BY Date ---//
	public Collection<BaseData> getBaseDataByDate(Date startDate, Date endDate);
	
	//--- COUNT BY MODEL AND DATE ---//
	public Long countByModelAndDate(Integer ue_type, Date startDate, Date endDate);

	public Collection<?> getNumFailuresAndDurationByDate(Date startDate, Date endDate);

}
