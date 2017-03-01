package com.ericsson.group.dao;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.group.entities.BaseData;

@Local
public interface BaseDAO {
	//--- SELECT ALL ---//
	public Collection<BaseData> getBaseData();
	
	//--- SELECT BY IMSI ---//
	public Collection<BaseData> getBaseDataByImsi(Long imsi);
	
	//--- SELECT BY Date ---//
	public Collection<BaseData> getBaseDataByDate(Date date);

}
