package com.ericsson.group.services;

import java.sql.Date;
import java.util.Collection;

import com.ericsson.group.entities.BaseData;

public interface BaseService {
	//--- SELECT ALL ---//
	public Collection<BaseData> getAllBaseData();
	
	//--- SELECT BY IMSI ---//
	public Collection<BaseData> getBaseDataByImsi(Long imsi);
	
	//--- SELECT BY DATE ---//
	public Collection<BaseData> getBaseDataByDate(Date startDate, Date endDate);
	
	//--- COUNT BY MODEL AND DATE ---//
	public Long countByModelAndDate(Integer ue_type, Date startDate, Date endDate);
	
}
